package com.example.webmvc.dao;

import com.example.webmvc.entity.Role;


public interface RoleDao {
    int deleteByPrimaryKey(Long roleid);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long roleid);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}