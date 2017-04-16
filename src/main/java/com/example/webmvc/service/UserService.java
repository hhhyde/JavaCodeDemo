package com.example.webmvc.service;

import com.example.webmvc.entity.Users;

public interface UserService {
    Users selectByPrimaryKey(String account);
}
