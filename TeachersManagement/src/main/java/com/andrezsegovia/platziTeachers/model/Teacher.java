/**
 * 
 */
package com.andrezsegovia.platziTeachers.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author ansecru
 *
 */

@Entity
@Table(name="Teacher")
public class Teacher implements Serializable{
	
	@Id
	@Column(name="id_Teacher")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTeacher;
	
	@Column(name="name")
	private String name;
	
	@Column(name="avatar")
	private String avatar;
	
	@OneToMany(mappedBy="teacher")
	@JsonIgnore
	private Set<Course> courses;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_Teacher")
	@JsonIgnore
	private Set<TeacherSocialMedia> teacherSocialMedias;
	
	
	/**
	 * 
	 */
	public Teacher() {
		// TODO Auto-generated constructor stub
	}

	public Teacher(String name, String avatar) {
		super();
		this.name = name;
		this.avatar = avatar;
	}

	public Long getIdTeacher() {
		return idTeacher;
	}

	public void setIdTeacher(Long idTeacher) {
		this.idTeacher = idTeacher;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	/**
	 * @return the courses
	 */
	public Set<Course> getCourses() {
		return courses;
	}

	/**
	 * @param courses the courses to set
	 */
	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	/**
	 * @return the teacherSocialMedias
	 */
	public Set<TeacherSocialMedia> getTeacherSocialMedias() {
		return teacherSocialMedias;
	}

	/**
	 * @param teacherSocialMedias the teacherSocialMedias to set
	 */
	public void setTeacherSocialMedias(Set<TeacherSocialMedia> teacherSocialMedias) {
		this.teacherSocialMedias = teacherSocialMedias;
	}
	
	
	

}
