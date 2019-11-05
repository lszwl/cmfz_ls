package com.baizhi.service;

import com.baizhi.dao.AlbumDao;
import com.baizhi.dao.StarDao;
import com.baizhi.entity.Album;
import com.baizhi.entity.Article;
import com.baizhi.entity.Star;
import org.apache.commons.io.FilenameUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Service("albumService")
@Transactional
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumDao albumDao;
    @Autowired
    private StarDao starDao;


    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map<String, Object> show(Integer page,Integer rows) {
        Map<String, Object> map = new HashMap<>();
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        Album album = new Album();
        List<Album> list = albumDao.selectByRowBounds(album, rowBounds);


        int count = albumDao.selectCount(album);

        map.put("page",page);
        map.put("rows",list);
        map.put("count",count%rows==0?count/rows:count/rows+1);
        map.put("records",count);
        return map;
    }



    @Override
    public String add(Album album) {

        Star star = new Star();
        star.setId(album.getStarId());
        Star star1 = starDao.selectOne(star);

        String id = UUID.randomUUID().toString();

        album.setId(id).setCreateDate(new Date()).setAuthor(star1.getNickname());

        int insert = albumDao.insert(album);
        if(insert!=1) throw new RuntimeException("添加专辑失败！");
        return id;
    }

    @Override
    public void edit(Album album) {
        if("".equals(album.getCover())){
            album.setCover(null);
        }
        int i = albumDao.updateByPrimaryKeySelective(album);
        if(i!=1) throw new RuntimeException("修改专辑失败！");
    }

    @Override
    public void delete(HttpServletRequest request, Album album) {
        Album one = albumDao.selectOne(album);
        try {
            int i = albumDao.deleteByPrimaryKey(album);
            if(i!=1) throw new RuntimeException("删除失败");
            String cover = one.getCover();
            String realPath = request.getServletContext().getRealPath("back/album/images");
            new File(realPath,cover).delete();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void upload(HttpServletRequest request, MultipartFile cover, String id) {
        String originalFilename = cover.getOriginalFilename();
        String filename = UUID.randomUUID().toString()+"."+ FilenameUtils.getExtension(originalFilename);
        System.out.println(filename);
        Album album = new Album();
        album.setId(id).setCover(filename);

        String realPath = request.getServletContext().getRealPath("/back/album/images");

        try {
            cover.transferTo(new File(realPath,filename));
            int i = albumDao.updateByPrimaryKeySelective(album);
        } catch (IOException e) {
            e.printStackTrace();
          albumDao.deleteByPrimaryKey(album);

        }





    }


}
