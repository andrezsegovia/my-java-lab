package com.andrezsegovia.platziTeachers.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andrezsegovia.platziTeachers.dao.SocialMediaDao;
import com.andrezsegovia.platziTeachers.model.SocialMedia;
import com.andrezsegovia.platziTeachers.model.TeacherSocialMedia;

@Service("socialMediaService")
@Transactional
public class SocialMediaServiceImpl implements SocialMediaService {

	@Autowired
	private SocialMediaDao _socialMediaDao;
	
	@Override
	public void saveSocialMedia(SocialMedia socialMedia) {
		// TODO Auto-generated method stub
		_socialMediaDao.saveSocialMedia(socialMedia);
	}

	@Override
	public void deleteSocialMedia(Long id) {
		// TODO Auto-generated method stub
		_socialMediaDao.deleteSocialMedia(id);
	}

	@Override
	public void updateSocialMedia(SocialMedia socialMedia) {
		// TODO Auto-generated method stub
		_socialMediaDao.updateSocialMedia(socialMedia);
	}

	@Override
	public List<SocialMedia> findAllSocialMedia() {
		// TODO Auto-generated method stub
		return _socialMediaDao.findAllSocialMedia();
	}

	@Override
	public SocialMedia findSocialMediaById(Long id) {
		// TODO Auto-generated method stub
		return _socialMediaDao.findSocialMediaById(id);
	}

	@Override
	public SocialMedia findByName(String name) {
		// TODO Auto-generated method stub
		return _socialMediaDao.findByName(name);
	}

	@Override
	public TeacherSocialMedia findSocialMediaByIdAndNickName(Long idSocialMedia, String nickName) {
		// TODO Auto-generated method stub
		return _socialMediaDao.findSocialMediaByIdAndNickName(idSocialMedia, nickName);
	}

}
