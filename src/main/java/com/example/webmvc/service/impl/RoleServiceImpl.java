package com.example.webmvc.service.impl;

import com.example.webmvc.dao.RoleDao;
import com.example.webmvc.entity.Role;
import com.example.webmvc.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;

    @Override
    public Role selectByPrimaryKey(long roleID) {
        return roleDao.selectByPrimaryKey(roleID);
    }
}
