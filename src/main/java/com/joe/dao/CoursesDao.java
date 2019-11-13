package com.joe.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joe.pojo.Courses;

public interface CoursesDao extends JpaRepository<Courses,Integer> {

}
