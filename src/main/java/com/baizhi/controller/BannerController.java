package com.baizhi.controller;

import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @RequestMapping("edit")
    public Map<String, Object> edit(HttpServletRequest request, String oper, Banner banner){
        Map<String,Object> map = new HashMap<>();
        try {
            if("add".equals(oper)){
                String s = bannerService.addBanner(banner);
                map.put("id",s);
            }
            if("del".equals(oper)){
                bannerService.deleteBanner(banner,request);
            }
            if("edit".equals(oper)){
                bannerService.edit(banner);
            }
            map.put("status",true);
        } catch (Exception e) {
            map.put("status",false);
            map.put("message",e.getMessage());
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping("show")
    public Map<String, Object> show(Integer page, Integer rows){
        Map<String, Object> map = bannerService.show(page, rows);
        return map;
    }


    @RequestMapping("upload")
    public Map<String,Object> upload(HttpServletRequest request,MultipartFile cover,String id){
        HashMap<String, Object> map = new HashMap<>();
        try {
            bannerService.fileUpload(request,cover,id);
            map.put("status",true);
        } catch (IOException e) {
            map.put("status",false);
            map.put("message",e.getMessage());

        }
        return map;
    }

}
