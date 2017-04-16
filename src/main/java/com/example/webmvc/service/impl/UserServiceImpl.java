package com.example.webmvc.service.impl;

import com.example.webmvc.dao.UsersMapper;
import com.example.webmvc.entity.Users;
import com.example.webmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UsersMapper usersMapper;

    @Override
    public Users selectByPrimaryKey(String account) {
        return usersMapper.selectByPrimaryKey(account);
    }
}
