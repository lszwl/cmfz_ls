package com.baizhi.service;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service("adminService")
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;
    @Override
    public void login(Admin admin, String code, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Admin selectAdmin = adminDao.selectOne(admin);
        String securityCode = (String)session.getAttribute("SecurityCode");
        System.out.println(securityCode);
        System.out.println(securityCode.equalsIgnoreCase(code));
        if(securityCode.equalsIgnoreCase(code)){
            if(selectAdmin!=null){
                session.setAttribute("admin",selectAdmin);
            }else{
                throw new RuntimeException("你输入的账号或密码有误！请重新输入");
            }
        }else{
            throw new RuntimeException("你输入的验证码有误！");
        }
    }
}
