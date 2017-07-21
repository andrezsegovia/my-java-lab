package com.andrezsegovia.platziTeachers.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andrezsegovia.platziTeachers.dao.TeacherDao;
import com.andrezsegovia.platziTeachers.model.Teacher;

@Service("teacherService")
@Transactional
public class TeacherServiceImple implements TeacherService {

	@Autowired
	private TeacherDao _teacherDao;
	
	@Override
	public void saveTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		_teacherDao.saveTeacher(teacher);
	}

	@Override
	public void delateTeacher(Long id) {
		// TODO Auto-generated method stub
		_teacherDao.delateTeacher(id);
	}

	@Override
	public void updateTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		_teacherDao.updateTeacher(teacher);
	}

	@Override
	public List<Teacher> findAllTeachers() {
		// TODO Auto-generated method stub
		return _teacherDao.findAllTeachers();
	}

	@Override
	public Teacher findTeacherById(Long id) {
		// TODO Auto-generated method stub
		return _teacherDao.findTeacherById(id);
	}

	@Override
	public Teacher findTeacherByName(String name) {
		// TODO Auto-generated method stub
		return _teacherDao.findTeacherByName(name);
	}

}
