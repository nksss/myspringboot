package com.joe.mapper;

import java.util.List;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.jooq.impl.DSL;

import com.joe.jooq.tables.Users;

@Service
public class UsersMapper {
	
	@Autowired
	DSLContext dsl;
	
	com.joe.jooq.tables.Users u = Users.USERS.as("u");

	public com.joe.jooq.tables.pojos.Users findByUsername(String username) {
		com.joe.jooq.tables.pojos.Users result = dsl.select()
				.from(u)
				.where(u.USERNAME.eq(username))
				.fetchOne()
				.into(com.joe.jooq.tables.pojos.Users.class);
		return result;
	}
	
	public List<com.joe.jooq.tables.pojos.Permission> findPermissionsByUserId(int userId) {
		List<com.joe.jooq.tables.pojos.Permission> result = dsl.resultQuery("select p.* "
				+ "from users as u "
				+ "left join user_role as ur on ur.user_id = u.id "
				+ "left join role_permission as rp on rp.role_id = ur.role_id "
				+ "left join permission as p on p.id = rp.permission_id "
				+ "where u.id = ?", userId).fetch()
				.into(com.joe.jooq.tables.pojos.Permission.class);
		return result;
	}
	
	public int insertUser(com.joe.jooq.tables.pojos.Users user) {
		return dsl.insertInto(u)
			.set(u.USERNAME, user.getUsername())
			.set(u.PASSWORD, user.getPassword())
			.set(u.CREATED_AT, DSL.currentTimestamp())
			.set(u.UPDATED_AT, DSL.currentTimestamp())
			.execute();
	}
}
