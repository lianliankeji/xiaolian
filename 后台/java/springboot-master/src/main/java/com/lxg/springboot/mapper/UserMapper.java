package com.lxg.springboot.mapper;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.lxg.springboot.bean.User;

@MapperScan
public interface UserMapper {

	int save(User user);

	User selectById(Integer id);

	int updateById(User user);

	int deleteById(Integer id);

	List<User> queryAll();

}