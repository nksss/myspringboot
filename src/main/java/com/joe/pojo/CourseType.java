package com.joe.pojo;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "courses_type")
public class CourseType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "uuid")
	private String uuid;
	@Column(name = "name")
	private String name;
	@Column(name = "school_id")
	private int school_id;
	@Column(name = "created_at")
	private String created_at;
	@Column(name = "updated_at")
	private String updated_at;
	@Column(name = "deleted_at")
	private String deleted_at;
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name="course_type_id")
	private Set<Courses> courses;
//
	public Set<Courses> getCourses() {
		return courses;
	}
//
	public void setCourses(Set<Courses> courses) {
		this.courses = courses;
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getschool_id() {
		return school_id;
	}

	public void setschool_id(int school_id) {
		this.school_id = school_id;
	}

	public String getcreated_at() {
		return created_at;
	}

	public void setcreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getupdated_at() {
		return updated_at;
	}

	public void setupdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	public String getdeleted_at() {
		return deleted_at;
	}

	public void setdeleted_at(String deleted_at) {
		this.deleted_at = deleted_at;
	}
}
