package com.baizhi.controller;

import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import com.sun.org.apache.bcel.internal.generic.NEW;
import it.sauronsoftware.jave.Encoder;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("chapter")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @RequestMapping("show")
    @ResponseBody
    public Map<String,Object> show(Integer page,Integer rows,String albumId){
        Map<String, Object> map = chapterService.show(page, rows,albumId);
        return map;
    }

    @RequestMapping("edit")
    @ResponseBody
    public Map<String,Object> edit(String oper,Chapter chapter){
        Map<String, Object> map = new HashMap<>();
        try {
            if("add".equals(oper)){
                String id = chapterService.add(chapter);
                map.put("id",id);
            }
            if("edit".equals(oper)){}
            if("del".equals(oper)){}
            map.put("status",true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status",false);
        }
        return map;
    }

    @RequestMapping("upload")
    @ResponseBody
    public Map<String,Object> upload(HttpServletRequest request, String id, String albumId, MultipartFile name){
        System.out.println(id);
        Map<String, Object> map = new HashMap<>();
       /* String  extension= FilenameUtils.getExtension(name.getOriginalFilename());
        String filename = UUID.randomUUID().toString()+"."+extension;*/
        File file = new File(request.getServletContext().getRealPath("back/album/audio"), name.getOriginalFilename());
        try {
            name.transferTo(file);
            Chapter chapter = new Chapter();
            //获取文件大小
            BigDecimal bigDecimal = new BigDecimal(name.getSize());
            BigDecimal mod = new BigDecimal(1024);
            BigDecimal realsize = bigDecimal.divide(mod).divide(mod).setScale(2, BigDecimal.ROUND_HALF_UP);
            //获取文件时长
            Encoder encoder = new Encoder();
            long  duration1= encoder.getInfo(file).getDuration();
            String duration = duration1/1000/60+":"+duration1/1000%60;

            chapter.setDuration(duration);
            chapter.setName(name.getOriginalFilename());
            chapter.setSize(realsize+"MB");
            chapter.setAlbumId(albumId);
            chapter.setId(id);
            chapterService.update(chapter);
            map.put("status",true);
        } catch (Exception e) {
            file.delete();
            e.printStackTrace();
            map.put("status",false);
            map.put("message",e.getMessage());
        }
        return null;
    }

}
