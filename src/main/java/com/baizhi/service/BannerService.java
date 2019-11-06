package com.baizhi.service;

import com.baizhi.entity.Banner;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

public interface BannerService {
    public String addBanner(Banner banner);

    public Map<String, Object> show(Integer page, Integer rows);

    void fileUpload(HttpServletRequest request, MultipartFile file,String id) throws IOException;

    void deleteBanner(Banner banner,HttpServletRequest request);


    void edit(Banner banner);
}
