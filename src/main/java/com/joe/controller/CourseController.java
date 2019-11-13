package com.joe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joe.dao.CourseTypeDao;
import com.joe.pojo.CourseType;

@RestController
public class CourseController {

	@Autowired
	CourseTypeDao courseTypeDao;
//	@Autowired
//	CoursesMapper coursesMapper;

//	@GetMapping("/courses_type/{typeId}")
//	public CourseType listCourseType(@PathVariable int typeId) throws Exception {
//
//		CourseType result = courseTypeMapper.findById(typeId);
//		return result;
//	}

	@GetMapping("api/courses_type")
	public List<CourseType> list(Model m, @RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "pageSize", defaultValue = "1") int pageSize) throws Exception {
//		PageHelper.startPage(page, pageSize);
//		Pageable pageable = new PageRequest(page,pageSize);
		List<CourseType> result = courseTypeDao.findAll();

//		PageInfo<CourseType> finalResult = new PageInfo<>(result);
		return result;
	}

//	@GetMapping("/courses/{id}")
//	public Courses findCourse(@PathVariable int id) throws Exception {
//		Courses result = coursesMapper.find(id);
//		return result;
//	}

}
