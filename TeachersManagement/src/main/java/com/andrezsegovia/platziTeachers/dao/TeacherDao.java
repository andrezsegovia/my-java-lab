package com.andrezsegovia.platziTeachers.dao;

import java.util.List;

import com.andrezsegovia.platziTeachers.model.Teacher;


public interface TeacherDao {
	
	void saveTeacher(Teacher teacher);
	
	void delateTeacher(Long id);
	
	void updateTeacher(Teacher teacher);
	
	List<Teacher> findAllTeachers();
	
	Teacher findTeacherById(Long id);
	
	Teacher findTeacherByName(String name);

}
