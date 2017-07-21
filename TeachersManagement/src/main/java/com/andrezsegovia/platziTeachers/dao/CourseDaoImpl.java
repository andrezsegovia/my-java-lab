package com.andrezsegovia.platziTeachers.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.andrezsegovia.platziTeachers.model.Course;

@Repository
@Transactional
public class CourseDaoImpl extends AbstractSession implements CourseDao {

	@Override
	public void saveCourse(Course course) {
		// TODO Auto-generated method stub
		getSession().persist(course);
	}

	@Override
	public void delateCourse(Long id) {
		// TODO Auto-generated method stub
		Course course = new Course();
		if(course != null) {
			getSession().delete(course);
		}
	}

	@Override
	public void updateCourse(Course course) {
		// TODO Auto-generated method stub
		getSession().update(course);
	}

	@Override
	public List<Course> findAllCourse() {
		// TODO Auto-generated method stub
		return getSession().createQuery("from Course").list();
	}

	@Override
	public Course findCourseById(Long id) {
		// TODO Auto-generated method stub
		return getSession().get(Course.class, id);
	}

	@Override
	public Course findByName(String name) {
		// TODO Auto-generated method stub
		return (Course)getSession().createQuery("from Course where name = :name")
				.setParameter("name", name).uniqueResult();
	}

	@Override
	public List<Course> findByTeacher(Long idTeacher) {
		// TODO Auto-generated method stub
		return (List<Course>)getSession().createQuery("from Course c join c.teacher t where t.idTeacher = :idTeacher")
				.setParameter("idTeacher", idTeacher).list();
	}

}
