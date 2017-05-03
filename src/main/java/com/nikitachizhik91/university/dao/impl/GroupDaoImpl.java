package com.nikitachizhik91.university.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nikitachizhik91.university.dao.DaoException;
import com.nikitachizhik91.university.dao.GroupDao;
import com.nikitachizhik91.university.model.Group;

@Repository
public class GroupDaoImpl implements GroupDao {

	private final static Logger log = LogManager.getLogger(GroupDaoImpl.class.getName());

	@Autowired
	private SessionFactory sessionFactory;

	public Group create(Group group) throws DaoException {

		log.trace("Started create() method.");

		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			Integer id = (Integer) session.save(group);
			session.getTransaction().commit();
			group.setId(id);
		}

		log.info("Created a Group :" + group);
		log.trace("Finished create() method.");

		return group;
	}

	public Group findById(int id) throws DaoException {
		log.trace("Started findById() method.");

		Group group = null;

		try (Session session = sessionFactory.openSession()) {
			group = session.get(Group.class, id);
		}

		log.info("Found the Group :" + group);
		log.trace("Finished findById() method.");

		return group;
	}

	@SuppressWarnings("unchecked")
	public List<Group> findAll() throws DaoException {
		log.trace("Started findAll() method.");

		List<Group> groups = null;
		try (Session session = sessionFactory.openSession()) {
			groups = (List<Group>) session.createQuery("from Group").list();
		}

		log.info("Found all Groups :");
		log.trace("Finished findAll() method.");

		return groups;
	}

	public Group update(Group group) throws DaoException {
		log.trace("Started update() method.");
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.update(group);
			session.getTransaction().commit();
		}
		log.info("Updated Group :" + group);
		log.trace("Finished update() method.");
		return group;
	}

	public void delete(int id) throws DaoException {
		log.trace("Started delete() method.");

		try (Session session = sessionFactory.openSession()) {

			session.beginTransaction();
			session.delete(session.get(Group.class, id));
			session.getTransaction().commit();
		}

		log.info("Deleted Group with id=" + id);
		log.trace("Finished delete() method.");
	}

	// SQL- insert into groups_students (group_id,student_id) values (?,?)
	public void addStudent(int studentId, int groupId) throws DaoException {
		log.trace("Started addStudent() method.");

		// "INSERT INTO Employee(firstName, lastName, salary)" + "SELECT
		// firstName, lastName, salary FROM old_employee";

		try (Session session = sessionFactory.openSession()) {
			session.createQuery("insert into Group.students (group_id,student_id) values (groupId,studentId)")
					.setParameter("groupId", groupId).setParameter("studentId", studentId).executeUpdate();
		}

		log.info("Added Student with id=" + studentId + " to the group with id=" + groupId);
		log.trace("Finished addStudent() method.");
	}

	// SQL-delete from groups_students where student_id=?
	public void deleteStudentFromGroup(int studentId) throws DaoException {
		log.trace("Started deleteStudentFromGroup() method.");

		try (Session session = sessionFactory.openSession()) {
			session.createQuery("delete from Group where Student = :studentId").setParameter("studentId", studentId)
					.executeUpdate();
		}

		log.info("Deleted Student with id=" + studentId);
		log.trace("Finished deleteStudentFromGroup() method.");

	}

	// SQL-"SELECT id FROM groups g WHERE NOT EXISTS(SELECT NULL FROM
	// faculties_groups fg WHERE fg.group_id = g.id)"
	@SuppressWarnings("unchecked")
	public List<Group> findGroupsWithoutFaculty() throws DaoException {
		log.trace("Started findGroupsWithoutFaculty() method.");

		List<Group> groups;

		try (Session session = sessionFactory.openSession()) {
			groups = (List<Group>) session.createQuery("from Group").list();
		}

		log.info("Found all groups which are without faculty.");
		log.trace("Finished findGroupsWithoutFaculty() method.");

		return groups;
	}
}
