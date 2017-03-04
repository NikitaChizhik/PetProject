package com.nikitachizhik91.university.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.nikitachizhik91.university.domain.Subject;

public class SubjectDAOImpl implements SubjectDAO {

	private static final String INSERT_SUBJECT = "insert into subjects (name) values(?)";
	private static final String FIND_SUBJECT_BY_ID = "select * from subjects where id=?";
	private static final String FIND_ALL_SUBJECTS = "select * from subjects";
	private static final String UPDATE_SUBJECT = "update subjects set name=? where id =?";
	private static final String DELETE_SUBJECT = "delete from subjects where id =?";

	public Subject create(Subject subject) {
		Subject newSubject = null;
		try (Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_SUBJECT,
						Statement.RETURN_GENERATED_KEYS);) {

			statement.setString(1, subject.getName());
			statement.executeUpdate();

			try (ResultSet resultSet = statement.getGeneratedKeys();) {
				while (resultSet.next()) {
					newSubject = new Subject();
					newSubject.setId(resultSet.getInt("id"));
					newSubject.setName(resultSet.getString("name"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return newSubject;
	}

	public Subject findById(int id) {
		Subject subjectRecieved = new Subject();

		try (Connection connection = Connector.getConnection();

		PreparedStatement statement = connection.prepareStatement(FIND_SUBJECT_BY_ID)) {

			statement.setInt(1, id);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {

					subjectRecieved.setId(resultSet.getInt("id"));
					subjectRecieved.setName(resultSet.getString("name"));
				}
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return subjectRecieved;
	}

	public List<Subject> findAll() {
		List<Subject> subjectsRecieved = new ArrayList<Subject>();

		try (Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_ALL_SUBJECTS);
				ResultSet resultSet = statement.executeQuery();) {

			while (resultSet.next()) {
				Subject subject = new Subject();
				subject.setId(resultSet.getInt("id"));
				subject.setName(resultSet.getString("name"));
				subjectsRecieved.add(subject);
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return subjectsRecieved;
	}

	public Subject update(Subject subject) {
		Subject newSubject = null;
		try (Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_SUBJECT,
						Statement.RETURN_GENERATED_KEYS);) {

			statement.setString(1, subject.getName());
			statement.setInt(2, subject.getId());
			statement.executeUpdate();

			try (ResultSet resultSet = statement.getGeneratedKeys();) {
				while (resultSet.next()) {
					newSubject = new Subject();
					newSubject.setId(resultSet.getInt("id"));
					newSubject.setName(resultSet.getString("name"));
				}
			}

		} catch (SQLException e) {
			e.getMessage();
		}
		return newSubject;
	}

	public void delete(int id) {
		try (Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_SUBJECT);) {

			statement.setInt(1, id);

			statement.executeUpdate();

		} catch (SQLException e) {
			e.getMessage();
		}
	}

}
