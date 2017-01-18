package com.example.webmvc.inter;

import com.example.webmvc.entity.Dept;

public interface DeptMapper {
    int deleteByPrimaryKey(String deptcode);

    int insert(Dept record);

    int insertSelective(Dept record);

    Dept selectByPrimaryKey(String deptcode);

    int updateByPrimaryKeySelective(Dept record);

    int updateByPrimaryKey(Dept record);
}