package com.joe.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.joe.pojo.CourseType;

public interface CourseTypeDao extends JpaRepository<CourseType,Integer> {

}
