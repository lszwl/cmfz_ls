package com.baizhi.service;

import com.baizhi.dao.ChapterDao;
import com.baizhi.entity.Chapter;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.*;

@Service("chapterService")
@Transactional
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    private ChapterDao chapterDao;

    @Override
    public Map<String, Object> show(Integer page, Integer rows, String albumId) {
        Map<String, Object> map = new HashMap<>();

        Chapter chapter = new Chapter();
        chapter.setAlbumId(albumId);
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);

        List<Chapter> list = chapterDao.selectByRowBounds(chapter, rowBounds);
        int count = chapterDao.selectCount(chapter);

        map.put("page",page);
        map.put("rows",list);
        map.put("count",count%page==0?count/page:count/page+1);
        map.put("records",count);
        return map;
    }

    @Override
    public void update(Chapter chapter) {
        if("".equals(chapter.getName())){
            chapter.setName(null);
        }
        System.out.println(chapter);
        int i = chapterDao.updateByPrimaryKeySelective(chapter);
        if(i==0){
            chapterDao.deleteByPrimaryKey(chapter);
            throw  new RuntimeException("上传文件失败");
        }
    }

    @Override
    public String add(Chapter chapter) {
        System.out.println(chapter);
        String id = UUID.randomUUID().toString();
        chapter.setId(id);
        chapter.setCreateDate(new Date());
        chapterDao.insert(chapter);
        return  id;
    }


}
