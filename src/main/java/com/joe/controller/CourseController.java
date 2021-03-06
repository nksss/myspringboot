package com.joe.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joe.jooq.tables.pojos.CoursesType;
import com.joe.mapper.CourseTypeMapper;
import com.joe.request.CourseTypeForm;

@RestController
public class CourseController {

	@Autowired
	CourseTypeMapper courseTypeMapper;
//	@Autowired
//	CoursesMapper coursesMapper;

//	@GetMapping("/courses_type/{typeId}")
//	public CourseType listCourseType(@PathVariable int typeId) throws Exception {
//
//		CourseType result = courseTypeMapper.findById(typeId);
//		return result;
//	}

	@GetMapping("api/courses_type")
	public List<CoursesType> list(Model m, @RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "pageSize", defaultValue = "1") int pageSize) throws Exception {
		List<CoursesType> result = courseTypeMapper.searchCourseType(page, pageSize);
		return result;
	}

//	@PostMapping("api/courses_type")
//	public List createCourseType(@Validated CourseTypeForm form, BindingResult result) {
//		if (!result.hasErrors()) {
//			CourseType courseType = new CourseType();
//			courseType.setName(form.getName());
//			courseType.setSchoolId(form.getSchoolId());
//			courseTypeDao.save(courseType);
//			List finalR = new ArrayList();
//			return finalR;
//		}
//		return null;
//	}

//	@Autowired
//	StringRedisTemplate template;

//	@GetMapping("api/test")
//	public void test() {
//		template.convertAndSend("chat", "hello");
//	}

//	@GetMapping("api/courses_type/{id}")
//	public CoursesType findCourse(@PathVariable("id") int id) throws Exception {
//		CourseType result = courseTypeDao.getOne(id);
//		return null;
//	}

}
