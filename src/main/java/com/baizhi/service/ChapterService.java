package com.baizhi.service;

import com.baizhi.entity.Chapter;

import java.util.Map;

public interface ChapterService {
    Map<String,Object> show(Integer page, Integer rows, String albumId);


    void update(Chapter chapter);

    String add(Chapter chapter);
}
