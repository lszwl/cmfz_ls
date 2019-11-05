package com.baizhi.service;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;


    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map<String, Object> getAll(Integer page, Integer rows, String starId) {
        Map<String, Object> map = new HashMap<>();
        User user = new User();
        user.setStarId(starId);
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);

        List<User> users = userDao.selectByRowBounds(user, rowBounds);


        int count = userDao.selectCount(user);

        map.put("page",page);
        map.put("rows",users);
        map.put("count",count%rows==0?count/rows:count/rows+1);
        map.put("records",count);

        return map;
    }
}
