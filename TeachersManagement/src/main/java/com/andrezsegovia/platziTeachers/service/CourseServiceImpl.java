package com.andrezsegovia.platziTeachers.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andrezsegovia.platziTeachers.dao.CourseDao;
import com.andrezsegovia.platziTeachers.model.Course;

@Service("courseService")
@Transactional
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseDao courseDao;
	
	@Override
	public void saveCourse(Course course) {
		// TODO Auto-generated method stub
		courseDao.saveCourse(course);
	}

	@Override
	public void delateCourse(Long id) {
		// TODO Auto-generated method stub
		courseDao.delateCourse(id);
	}

	@Override
	public void updateCourse(Course course) {
		// TODO Auto-generated method stub
		courseDao.updateCourse(course);
	}

	@Override
	public List<Course> findAllCourse() {
		// TODO Auto-generated method stub
		return courseDao.findAllCourse();
	}

	@Override
	public Course findCourseById(Long id) {
		// TODO Auto-generated method stub
		return courseDao.findCourseById(id);
	}

	@Override
	public Course findByName(String name) {
		// TODO Auto-generated method stub
		return courseDao.findByName(name);
	}

	@Override
	public List<Course> findByTeacher(Long idTeacher) {
		// TODO Auto-generated method stub
		return courseDao.findByTeacher(idTeacher);
	}

}
