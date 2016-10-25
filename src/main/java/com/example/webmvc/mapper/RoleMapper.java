package com.example.webmvc.mapper;

import com.example.webmvc.model.Role;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

/**
 * Created by Administrator on 2016/10/25.
 */
public interface RoleMapper {
	@Select("SELECT * FROM role WHERE roleid = #{roleid}")
	Role getRole(@Param("roleid") String roleid);
}
