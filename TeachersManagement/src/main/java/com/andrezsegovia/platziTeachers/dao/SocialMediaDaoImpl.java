package com.andrezsegovia.platziTeachers.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.andrezsegovia.platziTeachers.model.SocialMedia;
import com.andrezsegovia.platziTeachers.model.TeacherSocialMedia;

@Repository
@Transactional
public class SocialMediaDaoImpl extends AbstractSession implements SocialMediaDao {

	@Override
	public void saveSocialMedia(SocialMedia socialMedia) {
		// TODO Auto-generated method stub
		getSession().persist(socialMedia);
	}

	@Override
	public void deleteSocialMedia(Long id) {
		// TODO Auto-generated method stub
		SocialMedia socialMedia = findSocialMediaById(id);
		if(socialMedia != null) {
			getSession().delete(socialMedia);
		}
	}

	@Override
	public void updateSocialMedia(SocialMedia socialMedia) {
		// TODO Auto-generated method stub
		getSession().update(socialMedia);
	}

	@Override
	public List<SocialMedia> findAllSocialMedia() {
		// TODO Auto-generated method stub
		return getSession().createQuery("from SocialMedia").list();
	}

	@Override
	public SocialMedia findSocialMediaById(Long id) {
		// TODO Auto-generated method stub
		return getSession().get(SocialMedia.class, id);
	}

	@Override
	public SocialMedia findByName(String name) {
		// TODO Auto-generated method stub
		return (SocialMedia)getSession().createQuery("from SocialMedia where name = :name")
				.setParameter("name", name).uniqueResult();
	}

	@Override
	public TeacherSocialMedia findSocialMediaByIdAndNickName(Long idSocialMedia, String nickName) {
		// TODO Auto-generated method stub
		List<Object[]> objects = getSession().createQuery(
				"from TeacherSocialMedia tsm join tsm.socialMedia sm"
				+ " where sm.idSocialMedia = :idSocialMedia "
				+ "and tsm.nickName = :nickName").setParameter("idSocialMedia", idSocialMedia)
				.setParameter("nickName", nickName).list();
		
		if(objects.size() > 0 ) {
			for(Object[] object2: objects) {
				for(Object object: object2) {
					if(object instanceof TeacherSocialMedia) {
						return (TeacherSocialMedia) object;
					}
				}
			}
		}
		return null;
	}

}
