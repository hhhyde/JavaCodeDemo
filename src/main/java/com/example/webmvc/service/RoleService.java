package com.example.webmvc.service;

import com.example.webmvc.entity.Role;

/**
 * Created by Administrator on 2017/1/23.
 */
public interface RoleService {
    Role selectByPrimaryKey(long roleID);
}
