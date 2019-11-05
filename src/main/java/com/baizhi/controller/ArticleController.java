package com.baizhi.controller;

import com.baizhi.service.ArticleService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @RequestMapping("show")
    @ResponseBody
    public Map<String,Object> show(Integer page,Integer rows){
        Map<String, Object> show = articleService.show(page, rows);
        return show;
    }

    @RequestMapping("upload")
    @ResponseBody
    public Map<String,Object> upload(MultipartFile articleImg, HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        File file = new File(request.getServletContext().getRealPath("/back/article/img"), articleImg.getOriginalFilename());
        try {
            articleImg.transferTo(file);
            map.put("error",0);
            map.put("url","http://localhost:8989/star/article/img/"+articleImg.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
            map.put("error",1);
        }
        return map;
    }
    @RequestMapping("browse")
    @ResponseBody
    public Map<String,Object> browse(HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        File file = new File(request.getServletContext().getRealPath("article/img"));
        File[] files = file.listFiles();
        List<Object> list = new ArrayList<>();
        for (File img : files) {
            Map<String, Object> imgObject = new HashMap<>();
            imgObject.put("is_dir",false);
            imgObject.put("has_file",false);
            imgObject.put("filesize",img.length());
            imgObject.put("is_photo",true);
            imgObject.put("filetype", FilenameUtils.getExtension(img.getName()));
            imgObject.put("filename",img.getName());
            imgObject.put("datetime","2018-06-06 00:36:39");
            list.add(imgObject);
        }
        map.put("file_list",list);
        map.put("total_count",list.size());
        map.put("current_url","http://localhost:8989/star/article/img/");
        return map;
    }

}
