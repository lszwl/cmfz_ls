package com.baizhi.service;

import com.baizhi.dao.ArticleDao;
import com.baizhi.entity.Article;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service("articleService")
@Transactional
public class ArticleServiceImpl implements ArticleService{
    @Autowired
    private ArticleDao articleDao;

    @Override
    public Map<String, Object> show(Integer page,Integer rows) {
        Map<String, Object> map = new HashMap<>();
        Article article = new Article();
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        List<Article> list = articleDao.selectByRowBounds(article, rowBounds);

        int i = articleDao.selectCount(article);

        map.put("page",page);
        map.put("rows",list);
        map.put("count",i%rows==0?i/rows:i/rows+1);
        map.put("records",i);

        return map;
    }
}
