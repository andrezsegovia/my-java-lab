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
@Table(name="Teacher_Social_Media")
public class TeacherSocialMedia implements Serializable {
	
	@Id
	@Column(name="id_Teacher_Social_Media")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idTeacherSocialMedia;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_Teacher")
	private Teacher teacher;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_SocialMedia")
	private SocialMedia socialMedia;
	
	@Column(name="nickName")
	private String nickName;

	
	public TeacherSocialMedia() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param teacher
	 * @param socialMedia
	 * @param nickName
	 */
	public TeacherSocialMedia(Teacher teacher, SocialMedia socialMedia, String nickName) {
		super();
		this.teacher = teacher;
		this.socialMedia = socialMedia;
		this.nickName = nickName;
	}

	/**
	 * @return the idTeacherSocialMedia
	 */
	public Long getIdTeacherSocialMedia() {
		return idTeacherSocialMedia;
	}

	/**
	 * @param idTeacherSocialMedia the idTeacherSocialMedia to set
	 */
	public void setIdTeacherSocialMedia(Long idTeacherSocialMedia) {
		this.idTeacherSocialMedia = idTeacherSocialMedia;
	}

	/**
	 * @return the teacher
	 */
	public Teacher getTeacher() {
		return teacher;
	}

	/**
	 * @param teacher the teacher to set
	 */
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	/**
	 * @return the socialMedia
	 */
	public SocialMedia getSocialMedia() {
		return socialMedia;
	}

	/**
	 * @param socialMedia the socialMedia to set
	 */
	public void setSocialMedia(SocialMedia socialMedia) {
		this.socialMedia = socialMedia;
	}

	/**
	 * @return the nickName
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * @param nickName the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	

}
