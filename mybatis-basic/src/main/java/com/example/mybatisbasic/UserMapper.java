package com.example.mybatisbasic;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

// @Mapper
@Repository
public interface UserMapper {

	@Select("SELECT * FROM users WHERE id = #{userId}")
	User getUser(@Param("userId") Long userId);
}
