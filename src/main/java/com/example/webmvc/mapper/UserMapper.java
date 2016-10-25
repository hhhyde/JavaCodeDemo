package com.example.webmvc.mapper;

import com.example.webmvc.model.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

/**
 * Created by Administrator on 2016/10/25.
 */
public interface UserMapper {
	@Select("SELECT * FROM users WHERE id = #{userId}")
	User getUser(@Param("userId") String userId);
}
