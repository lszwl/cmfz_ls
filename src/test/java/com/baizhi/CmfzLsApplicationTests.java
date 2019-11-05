package com.baizhi;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CmfzLsApplicationTests {
    @Autowired
    private AdminService adminService;
    @Test
    void contextLoads() {
        String filename="11.exe";
        String prefix=filename.substring(filename.lastIndexOf(".")+1);
        System.out.println(prefix);
    }

}
