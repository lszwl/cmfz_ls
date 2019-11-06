package com.baizhi.service;

import com.baizhi.entity.Star;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface StarService {
    Map<String, Object> show(Integer page, Integer rows);

    String  add( Star star);

    void upload(HttpServletRequest request, MultipartFile photo,String id);

    void edit(Star star);

    void delete(Star star,HttpServletRequest request);

    List<Star> showAll();
}
