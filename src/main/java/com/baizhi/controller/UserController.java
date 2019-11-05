package com.baizhi.controller;

import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;


    @RequestMapping("show")
    public Map<String,Object> show(Integer page,Integer rows,String starId){
        Map<String, Object> map = userService.getAll(page,rows,starId);
        return map;
    }

}
