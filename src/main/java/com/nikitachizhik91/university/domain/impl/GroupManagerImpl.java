package com.nikitachizhik91.university.domain.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nikitachizhik91.university.dao.DaoException;
import com.nikitachizhik91.university.dao.impl.GroupDaoImpl;
import com.nikitachizhik91.university.domain.DomainException;
import com.nikitachizhik91.university.domain.GroupManager;
import com.nikitachizhik91.university.model.Group;
import com.nikitachizhik91.university.model.Student;

public class GroupManagerImpl implements GroupManager {
	private final static Logger log = LogManager.getLogger(GroupManagerImpl.class.getName());
	private GroupDaoImpl groupDao;

	public GroupManagerImpl() {
		groupDao = new GroupDaoImpl();
	}

	@Override
	public Group create(Group group) throws DomainException {
		log.trace("Started create() method.");

		Group groupTemp = null;
		try {
			log.trace("Creating group.");

			groupTemp = groupDao.create(group);

		} catch (DaoException e) {
			log.error("Cannot create group.", e);
			throw new DomainException("Cannot create group.", e);
		}
		log.trace("Finished create() method.");

		return groupTemp;
	}

	@Override
	public Group findById(int id) throws DomainException {
		log.trace("Started findById() method.");

		Group group = null;
		try {
			log.trace("Finding group by id.");

			group = groupDao.findById(id);

		} catch (DaoException e) {
			log.error("Cannot group by id.", e);
			throw new DomainException("Cannot group by id.", e);
		}
		log.trace("Finished findById() method.");

		return group;
	}

	@Override
	public List<Group> findAll() throws DomainException {
		log.trace("Started findAll() method.");

		List<Group> groups = null;
		try {
			log.trace("Finding all groups");

			groups = groupDao.findAll();

		} catch (DaoException e) {
			log.error("Cannot find all groups.", e);
			throw new DomainException("Cannot find all groups.", e);
		}
		log.trace("Finished findAll() method.");

		return groups;
	}

	@Override
	public Group update(Group group) throws DomainException {
		log.trace("Started update() method.");

		Group groupTemp = null;
		try {
			log.trace("Updating group.");

			groupTemp = groupDao.update(group);

		} catch (DaoException e) {
			log.error("Cannot update group.", e);
			throw new DomainException("Cannot update group.", e);
		}
		log.trace("Finished update() method.");

		return groupTemp;
	}

	@Override
	public void delete(int id) throws DomainException {
		log.trace("Started delete() method.");

		try {
			log.trace("Deleting group by id.");

			groupDao.delete(id);

		} catch (DaoException e) {
			log.error("Cannot delete group by id.", e);
			throw new DomainException("Cannot delete group by id.", e);
		}
		log.trace("Finished delete() method.");
	}

	@Override
	public void addStudent(int studentId, int groupId) throws DomainException {

		log.trace("Started addStudent() method.");

		try {
			log.trace("Adding student.");

			groupDao.addStudent(studentId, groupId);

		} catch (DaoException e) {
			log.error("Cannot add student with id=" + studentId + " to group with id=" + groupId, e);
			throw new DomainException("Cannot add student with id=" + studentId + " to group with id=" + groupId, e);
		}
		log.trace("Finished addStudent() method.");
	}

	@Override
	public List<Student> findStudentsByGroupId(int groupId) throws DomainException {
		return null;
	}

	@Override
	public void deleteAllStudentsFromGroup(int groupId) throws DomainException {
	}

	@Override
	public void deleteStudentFromGroup(int studentId) throws DomainException {

		log.trace("Started deleteStudentFromGroup() method.");

		try {
			log.trace("Deleting student from group.");

			groupDao.deleteStudentFromGroup(studentId);

		} catch (DaoException e) {
			log.error("Cannot delete student with id=" + studentId + " from group", e);
			throw new DomainException("Cannot delete student with id=" + studentId + " from group", e);
		}
		log.trace("Finished deleteStudentFromGroup() method.");
	}

}
