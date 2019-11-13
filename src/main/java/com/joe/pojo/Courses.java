package com.joe.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "courses")
public class Courses {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "uuid")
	private String uuid;
	@Column(name = "code")
	private String code;
	@Column(name = "school_id")
	private int school_id;
	@Column(name = "employee_id")
	private int employee_id;
	@Column(name = "educational_range")
	private String educational_range;
	@Column(name = "name")
	private String name;
	@Column(name = "course_type_id")
	private int course_type_id;
	@Column(name = "description")
	private String description;
	@Column(name = "created_at")
	private String created_at;
	@Column(name = "updated_at")
	private String updated_at;
	@Column(name = "deleted_at")
	private String deleted_at;
//	@Column(name = "id")
//	private CourseType courseType;
//
//	public CourseType getCourseType() {
//		return courseType;
//	}
//
//	public void setCourseType(CourseType courseType) {
//		this.courseType = courseType;
//	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getSchool_id() {
		return school_id;
	}

	public void setSchool_id(int school_id) {
		this.school_id = school_id;
	}

	public int getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	public String getEducational_range() {
		return educational_range;
	}

	public void setEducational_range(String educational_range) {
		this.educational_range = educational_range;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCourse_type_id() {
		return course_type_id;
	}

	public void setCourse_type(int course_type_id) {
		this.course_type_id = course_type_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	public String getDeleted_at() {
		return deleted_at;
	}

	public void setDeleted_at(String deleted_at) {
		this.deleted_at = deleted_at;
	}
}
