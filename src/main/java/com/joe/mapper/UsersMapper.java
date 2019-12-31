package com.joe.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.joe.pojo.Users;
import com.joe.pojo.Permission;;

@Mapper
public interface UsersMapper {

	Users findByUsername(String username);
	
	List<Permission> findPermissionsByUserId(int userId);
}
