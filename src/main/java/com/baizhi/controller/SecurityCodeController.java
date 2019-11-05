package com.baizhi.controller;

import com.baizhi.util.SecurityCodeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
@Controller
@RequestMapping("code")
public class SecurityCodeController {

    @RequestMapping("getCode")
    public void getCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("image/png");
        String code = SecurityCodeUtil.generateVerifyCode(4);
        request.getSession().setAttribute("SecurityCode",code);
        BufferedImage image = SecurityCodeUtil.getImage(100, 40, code);
        ImageIO.write(image,"png",response.getOutputStream());
        System.out.println(111);
    }
}
