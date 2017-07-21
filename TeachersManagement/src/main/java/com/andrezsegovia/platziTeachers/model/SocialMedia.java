package com.andrezsegovia.platziTeachers.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="SocialMedia")
public class SocialMedia implements Serializable {
	
	@Id
	@Column(name="id_SocialMedia")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idSocialMedia;
	
	@Column(name="name")
	private String name;
	
	@Column(name="icon")
	private String icon;
	
	@OneToMany
	@JoinColumn(name = "id_SocialMedia")
	@JsonIgnore
	private Set<TeacherSocialMedia> teacherSocialMedias;
	
	public SocialMedia(String name, String icon, Set<TeacherSocialMedia> teacherSocialMedias) {
		super();
		this.name = name;
		this.icon = icon;
		this.teacherSocialMedias = teacherSocialMedias;
	}
		
	public SocialMedia() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getIdSocialMedia() {
		return idSocialMedia;
	}
	public void setIdSocialMedia(Long idSocialMedia) {
		this.idSocialMedia = idSocialMedia;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
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
