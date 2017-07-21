package com.andrezsegovia.platziTeachers.service;

import java.util.List;

import com.andrezsegovia.platziTeachers.model.Teacher;

public interface TeacherService {
	
	void saveTeacher(Teacher teacher);
	
	void delateTeacher(Long id);
	
	void updateTeacher(Teacher teacher);
	
	List<Teacher> findAllTeachers();
	
	Teacher findTeacherById(Long id);
	
	Teacher findTeacherByName(String name);
}
