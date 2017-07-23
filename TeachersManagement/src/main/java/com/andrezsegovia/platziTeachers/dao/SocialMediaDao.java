package com.andrezsegovia.platziTeachers.dao;

import java.util.List;

import com.andrezsegovia.platziTeachers.model.SocialMedia;
import com.andrezsegovia.platziTeachers.model.TeacherSocialMedia;

public interface SocialMediaDao {
	
	void saveSocialMedia(SocialMedia socialMedia);
	
	void deleteSocialMedia(Long id);
	
	void updateSocialMedia(SocialMedia socialMedia);
	
	List<SocialMedia> findAllSocialMedia();
	
	SocialMedia findSocialMediaById(Long id);
	
	SocialMedia findByName(String name);
	
	TeacherSocialMedia findSocialMediaByIdAndNickName(Long idSocialMedia, String nickName);
	
	TeacherSocialMedia findByIdSocialMediaAndIdTeacher(Long idSocialMedia, Long idTeacher);

}
