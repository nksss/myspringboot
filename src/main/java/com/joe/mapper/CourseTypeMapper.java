package com.joe.mapper;

import java.util.List;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joe.jooq.tables.CoursesType;

@Service
public class CourseTypeMapper {
	
	@Autowired
	DSLContext dsl;
	
	com.joe.jooq.tables.CoursesType c = CoursesType.COURSES_TYPE.as("c");

	public List<com.joe.jooq.tables.pojos.CoursesType> searchCourseType(int page, int pageSize) {
		List<com.joe.jooq.tables.pojos.CoursesType> result = dsl.select()
			.from(c)
			.limit(pageSize)
			.offset(page - 1)
			.fetch()
			.into(com.joe.jooq.tables.pojos.CoursesType.class);
		return result;
	}
}
