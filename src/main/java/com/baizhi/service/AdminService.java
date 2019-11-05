package com.baizhi.service;

import com.baizhi.entity.Admin;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;


public interface AdminService {
    void login(Admin admin, String code, HttpServletRequest request);
}
