package com.baizhi.service;

import com.baizhi.dao.StarDao;
import com.baizhi.entity.Album;
import com.baizhi.entity.Star;
import org.apache.commons.io.FilenameUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Service("starService")
@Transactional
public class StarServiceImpl implements StarService{

    @Autowired
    private StarDao starDao;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map<String, Object> getAll(Integer page,Integer rows) {
        Star star = new Star();
        RowBounds rowBounds = new RowBounds((page-1)*rows, rows);

        Map<String, Object> map = new HashMap<>();
        List<Star> list = starDao.selectByRowBounds(star, rowBounds);


        int count = starDao.selectCount(star);

        map.put("page",page);
        map.put("rows",list);
        map.put("count",count%rows==0?count/rows:count/rows+1);
        map.put("records",count);

        return map;
    }

    @Override
    public String add( Star star) {
        String id = UUID.randomUUID().toString();
        star.setId(id);
        star.setCreateDate(new Date());
        int insert = starDao.insert(star);
        if(insert!=1){
            throw new RuntimeException("添加失败！");
        }
        return id;
    }

    @Override
    public void upload(HttpServletRequest request, MultipartFile photo, String id) {
        //获取服务器路径地址
        String realPath = request.getServletContext().getRealPath("/back/star/images");

        String originalFilename = photo.getOriginalFilename();
        //获取文件后缀名
        String name = FilenameUtils.getBaseName(originalFilename);

        String fileName = UUID.randomUUID().toString()+name;
        System.out.println(fileName);

        Star star = new Star();
        star.setId(id).setPhoto(fileName);
        try {
            photo.transferTo(new File(realPath,fileName));
            starDao.updateByPrimaryKeySelective(star);

        } catch (IOException e) {
            starDao.deleteByPrimaryKey(star);
            e.printStackTrace();
        }

    }

    @Override
    public void edit(Star star) {
        if("".equals(star.getPhoto())){
            star.setPhoto(null);
        }
        int i = starDao.updateByPrimaryKeySelective(star);
        if(i!=1) throw new RuntimeException("修改失败");
    }

    @Override
    public void delete(Star star,HttpServletRequest request) {
        Star star1 = starDao.selectByPrimaryKey(star);


        int i = starDao.deleteByPrimaryKey(star);
        if(i==1){
            String photo = star1.getPhoto();
            String realPath = request.getServletContext().getRealPath("back/star/images");
            File file = new File(realPath, photo);
            file.delete();
        }else {
            throw new RuntimeException("删除失败！");
        }

    }
    @Override
    public List<Star> showAll() {
        List<Star> list = starDao.selectAll();
        return list;
    }

}
