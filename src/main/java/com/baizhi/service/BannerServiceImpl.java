package com.baizhi.service;

import com.baizhi.dao.BannerDao;
import com.baizhi.entity.Banner;
import com.baizhi.redis.RedisCache;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Service("bannerService")
@Transactional
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerDao bannerDao;
    @Transactional
    public String addBanner(Banner banner){

        String id = UUID.randomUUID().toString();
        banner.setId(id).setCreateDate(new Date());
        int i = bannerDao.insert(banner);
        if(i!=1){
            throw new RuntimeException("图片添加失败");

        }
        return id;
    }

    @RedisCache
    @Transactional
    @Override
    public Map<String, Object> show(Integer page, Integer rows) {
        //定义返回值
        HashMap<String, Object> map = new HashMap<>();
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        Banner banner = new Banner();
        //查询到的所有图片
        List<Banner> list = bannerDao.selectByRowBounds(banner, rowBounds);

        int total = bannerDao.selectCount(banner);                  //查询到的图片总数

        int totalCount = total%rows==0?total/rows:total/rows+1;     //获取图片的总页数
        map.put("page",page);
        map.put("rows",list);
        map.put("total",totalCount);
        map.put("records",total);
        return map;
    }


    @Override
    @Transactional
    public void fileUpload(HttpServletRequest request, MultipartFile cover,String id) {
        String filename1 = cover.getOriginalFilename();
        String prefix=filename1.substring(filename1.lastIndexOf(".")+1);
       // String name = FilenameUtils.getBaseName(filename1);
        Banner banner = new Banner();
        banner.setId(id);

        String filename = UUID.randomUUID().toString();
        banner.setCover(filename+"."+prefix);

        if("".equals(banner.getCover())){
            banner.setCover(null);
        }
        int i = bannerDao.updateByPrimaryKeySelective(banner);


        String realPath = request.getServletContext().getRealPath("back/image");

        //File file1 = new File(realPath);
        try {
            cover.transferTo(new File(realPath,filename+"."+prefix));
        } catch (IOException e) {
           throw new RuntimeException("图片上传失败!");
        }

    }

    @Override
    public void deleteBanner(Banner banner,HttpServletRequest request) {
        Banner banner1 = bannerDao.selectByPrimaryKey(banner);

        int i = bannerDao.deleteByPrimaryKey(banner);
        if(i!=1){
            throw new RuntimeException("删除失败");
        }else {
            String cover = banner1.getCover();
            File file = new File(request.getServletContext().getRealPath("back/image"), cover);
            file.delete();
        }
    }


    public void edit(Banner banner){
        if("".equals(banner.getCover())){
            banner.setCover(null);
        }
        try {
            int i = bannerDao.updateByPrimaryKeySelective(banner);
            if(i!=1) throw new RuntimeException("修改失败");
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

}
