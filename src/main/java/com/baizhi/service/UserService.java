package com.baizhi.service;

import java.util.Map;

public interface UserService {
    Map<String, Object> show(Integer page, Integer rows, String starId);
}
