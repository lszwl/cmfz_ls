package com.baizhi.service;

import com.baizhi.entity.Album;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface AlbumService {
    Map<String,Object> show(Integer page,Integer rows);


    String add(Album album);

    void edit(Album album);

    void delete(HttpServletRequest request,Album album);


    void upload(HttpServletRequest request, MultipartFile cover,String id);
}
