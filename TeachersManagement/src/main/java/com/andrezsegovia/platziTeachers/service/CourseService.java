package com.andrezsegovia.platziTeachers.service;

import java.util.List;

import com.andrezsegovia.platziTeachers.model.Course;

public interface CourseService {

	void saveCourse(Course course);
	
	void delateCourse(Long id);
	
	void updateCourse(Course course);
	
	List<Course> findAllCourse();
	
	Course findCourseById(Long id);
	
	Course findByName(String name);
	
	List<Course> findByTeacher(Long idTeacher);
}
