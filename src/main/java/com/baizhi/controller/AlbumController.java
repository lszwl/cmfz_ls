package com.baizhi.controller;

import com.baizhi.dao.AlbumDao;
import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @RequestMapping("show")
    public Map<String,Object> show(Integer page,Integer rows){
        Map<String, Object> show = albumService.show(page, rows);
        return show;
    }

    @RequestMapping("edit")
    public Map<String,Object> edit(HttpServletRequest request, Album album,String oper){
        Map<String, Object> map = new HashMap<>();
        try {
            if("edit".equals(oper)){

            }
            if("add".equals(oper)){
                String id = albumService.add(album);
                map.put("id",id);
            }
            if("del".equals(oper)){
                albumService.delete(request,album);
            }
            map.put("status",true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status",false);
            map.put("message",e.getMessage());
        }


        return map;
    }

    @RequestMapping("upload")
    public void upload(HttpServletRequest request, MultipartFile cover,String id){
        albumService.upload(request,cover,id);
    }



}
