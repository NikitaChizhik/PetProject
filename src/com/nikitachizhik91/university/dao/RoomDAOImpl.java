package com.nikitachizhik91.university.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.nikitachizhik91.university.domain.Room;

public class RoomDAOImpl implements RoomDAO {
	private static final String create = "insert into rooms (number) values(?)";
	private static final String findById = "select * from rooms where room_id=?";
	private static final String findAll = "select * from rooms order by number";
	private static final String update = "update rooms set number=? where room_id =?";
	private static final String delete = "delete from rooms where room_id =?";

	public Room create(Room room) {
		Room newRoom = null;
		try (Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(create, Statement.RETURN_GENERATED_KEYS);) {

			statement.setString(1, room.getNumber());
			statement.executeUpdate();

			try (ResultSet resultSet = statement.getGeneratedKeys();) {
				while (resultSet.next()) {
					newRoom = new Room();
					newRoom.setId(resultSet.getInt("room_id"));
					newRoom.setNumber(resultSet.getString("number"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return newRoom;
	}

	public Room findById(int id) {
		Room roomRecieved = new Room();

		try (Connection connection = Connector.getConnection();

		PreparedStatement statement = connection.prepareStatement(findById)) {

			statement.setInt(1, id);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {

					roomRecieved.setId(resultSet.getInt("room_id"));
					roomRecieved.setNumber(resultSet.getString("number"));
				}
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return roomRecieved;
	}

	public List<Room> findAll() {
		List<Room> roomsRecieved = new ArrayList<Room>();

		try (Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(findAll);
				ResultSet resultSet = statement.executeQuery();) {

			while (resultSet.next()) {
				Room room = new Room();
				room.setId(resultSet.getInt("room_id"));
				room.setNumber(resultSet.getString("number"));
				roomsRecieved.add(room);
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return roomsRecieved;
	}

	public Room update(Room room) {
		Room newRoom = null;
		try (Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(update, Statement.RETURN_GENERATED_KEYS);) {

			statement.setString(1, room.getNumber());
			statement.setInt(2, room.getId());
			statement.executeUpdate();

			try (ResultSet resultSet = statement.getGeneratedKeys();) {
				while (resultSet.next()) {
					newRoom = new Room();
					newRoom.setId(resultSet.getInt("room_id"));
					newRoom.setNumber(resultSet.getString("number"));
				}
			}

		} catch (SQLException e) {
			e.getMessage();
		}
		return newRoom;
	}

	public void delete(int id) {
		try (Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(delete);) {

			statement.setInt(1, id);

			statement.executeUpdate();

		} catch (SQLException e) {
			e.getMessage();
		}
	}

	

}
