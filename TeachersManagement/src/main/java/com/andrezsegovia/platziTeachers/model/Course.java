package com.andrezsegovia.platziTeachers.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Course")
public class Course implements Serializable {

	@Id
	@Column(name="id_Course")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idCourse;
	
	@Column(name="name")
	private String name;
	
	@Column(name="theme")
	private String theme;
	
	@Column(name="project")
	private String project;

	@ManyToOne(optional=true, fetch=FetchType.EAGER)
	@JoinColumn(name="id_Teacher")
	private Teacher teacher;
	
	public Course() {
		// TODO Auto-generated constructor stub
	}

	public Course(String name, String theme, String project) {
		super();
		this.name = name;
		this.theme = theme;
		this.project = project;
	}

	public Long getIdCourse() {
		return idCourse;
	}

	public void setIdCourse(Long idCourse) {
		this.idCourse = idCourse;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	

}
