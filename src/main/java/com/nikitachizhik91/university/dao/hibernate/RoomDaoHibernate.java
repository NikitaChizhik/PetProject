package com.nikitachizhik91.university.dao.hibernate;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nikitachizhik91.university.dao.DaoException;
import com.nikitachizhik91.university.dao.RoomDao;
import com.nikitachizhik91.university.model.Room;

@Repository
public class RoomDaoHibernate implements RoomDao {

	private final static Logger log = LogManager.getLogger(RoomDaoHibernate.class.getName());

	@Autowired
	private SessionFactory sessionFactory;

	public Room create(Room room) throws DaoException {
		log.trace("Started create() method.");
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			Integer id = (Integer) session.save(room);
			session.getTransaction().commit();
			room.setId(id);
		}
		log.info("Created a Room :" + room);
		log.trace("Finished create() method.");
		return room;
	}

	public Room findById(int id) throws DaoException {
		log.trace("Started findById() method.");
		Room room = null;
		try (Session session = sessionFactory.openSession()) {
			room = session.get(Room.class, id);
		}
		log.info("Found the Room :" + room);
		log.trace("Finished findById() method.");
		return room;
	}

	@SuppressWarnings("unchecked")
	public List<Room> findAll() throws DaoException {
		log.trace("Started findAll() method.");
		List<Room> rooms = null;
		try (Session session = sessionFactory.openSession()) {
			rooms = (List<Room>) session.createQuery("from Room").list();
		}
		log.info("Found all rooms :");
		log.trace("Finished findAll() method.");
		return rooms;
	}

	public Room update(Room room) throws DaoException {
		log.trace("Started update() method.");
		try (Session session = sessionFactory.openSession()) {

			session.beginTransaction();
			session.update(room);
			session.getTransaction().commit();
		}
		log.info("Updated Room :" + room);
		log.trace("Finished update() method.");
		return room;
	}

	public void delete(int id) throws DaoException {
		log.trace("Started delete() method.");
		try (Session session = sessionFactory.openSession()) {

			session.beginTransaction();
			session.delete(session.get(Room.class, id));
			session.getTransaction().commit();
		}
		log.info("Deleted Room with id=" + id);
		log.trace("Finished delete() method.");
	}
}
