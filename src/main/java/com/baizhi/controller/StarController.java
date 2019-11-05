package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.entity.Star;
import com.baizhi.service.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("star")

public class StarController {

    @Autowired
    private StarService starService;

    @RequestMapping("show")
    public Map<String,Object> show(Integer page,Integer rows){

        Map<String, Object> map = starService.getAll(page, rows);

        return map;
    }

    @RequestMapping("edit")
    public  Map<String,Object> edit(String oper, HttpServletRequest request, Star star){
        Map<String, Object> map = new HashMap<>();
        try {
            if("add".equals(oper)){
                String id = starService.add(star);
                map.put("id",id);
            }
            if("edit".equals(oper)){
                starService.edit(star);
            }
            if("del".equals(oper)){
                starService.delete(star,request);
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
    public void upload(HttpServletRequest request, MultipartFile photo,String id){
        starService.upload(request,photo,id);
    }
    @RequestMapping("showAll")
    public List<Star> showAll(){
        List<Star> list = starService.showAll();
        return list;
    }
}
