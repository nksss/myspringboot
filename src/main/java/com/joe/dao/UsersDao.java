package com.joe.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.joe.pojo.Users;

public interface UsersDao extends JpaRepository<Users,Integer> {

}
