package com.nikitachizhik91.university.dao.hibernate;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nikitachizhik91.university.dao.DaoException;
import com.nikitachizhik91.university.dao.StudentDao;
import com.nikitachizhik91.university.model.Student;

@Repository
public class StudentDaoHibernate implements StudentDao {

	private final static Logger log = LogManager.getLogger(StudentDaoHibernate.class.getName());

	@Autowired
	private SessionFactory sessionFactory;

	public Student create(Student student) throws DaoException {
		log.trace("Started create() method.");
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			Integer id = (Integer) session.save(student);
			session.getTransaction().commit();
			student.setId(id);
		}
		log.info("Created a Student :" + student);
		log.trace("Finished create() method.");
		return student;
	}

	public Student findById(int id) throws DaoException {
		log.trace("Started findById() method.");
		Student student = null;
		try (Session session = sessionFactory.openSession()) {
			student = session.get(Student.class, id);
		}
		log.info("Found the Student :" + student);
		log.trace("Finished findById() method.");
		return student;
	}

	@SuppressWarnings("unchecked")
	public List<Student> findAll() throws DaoException {
		log.trace("Started findAll() method.");
		List<Student> students = null;
		try (Session session = sessionFactory.openSession()) {
			students = (List<Student>) session.createQuery("from Student").list();
		}
		log.info("Found all Students :");
		log.trace("Finished findAll() method.");
		return students;
	}

	public Student update(Student student) throws DaoException {
		log.trace("Started update() method.");
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.update(student);
			session.getTransaction().commit();
		}
		log.info("Updated Student :" + student);
		log.trace("Finished update() method.");
		return student;
	}

	public void delete(int id) throws DaoException {
		log.trace("Started delete() method.");
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.delete(session.get(Student.class, id));
			session.getTransaction().commit();
		}
		log.info("Deleted Student with id=" + id);
		log.trace("Finished delete() method.");
	}

	@SuppressWarnings("unchecked")
	public List<Student> findStudentsWithoutGroup() throws DaoException {
		log.trace("Started findStudentsWithoutGroup() method.");
		List<Student> students;
		try (Session session = sessionFactory.openSession()) {
			students = (List<Student>) session
					.createQuery("from Student s where not exists (from Group g where s in elements(g.students))")
					.list();
		}
		log.info("Found all Students who are without group.");
		log.trace("Finished findStudentsWithoutGroup() method.");
		return students;
	}
}
