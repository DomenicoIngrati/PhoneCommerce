package persistence.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.tomcat.jdbc.pool.DataSource;

import model.User;
import persistence.PersistenceException;
import persistence.dao.UserDAO;

public class UserDaoJDBC implements UserDAO {

	
	private DataSource dataSource;
	
	public UserDaoJDBC(DataSource dataSource) {
		this.dataSource=dataSource;
	}
	
	@Override
	public boolean create(User modelObject) {
		Connection connection = null;
		String query = null;
		PreparedStatement statement = null;
		try {
		    connection = dataSource.getConnection();
		    query = "insert into User(username, password, email, name, surname, usertype, coins) values(?,?,?,?,?,?,?)";
		    statement = connection.prepareStatement(query);
		    statement.setString(1, modelObject.getUsername());
		    statement.setString(2, modelObject.getPassword());
		    statement.setString(3, modelObject.getEmail());
		    statement.setString(4, modelObject.getName());
		    statement.setString(5, modelObject.getSurname());
		    statement.setString(6, modelObject.getType().name());
		    statement.setLong(7, modelObject.getCoins());
		    return (statement.executeUpdate() > 0) ? true : false;
		} catch (SQLException e) {
		    e.printStackTrace();
		} finally {
		    DAOUtility.close(connection);
		    DAOUtility.close(statement);
		}
		return false;
	}

	//NON HO CAPITO COSA DEVE FARE QUESTO METODO!!
	@Override
	public User findReview(User user) {
	}

	@Override
	public boolean update(User u) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update User SET username = ?, password = ?, email = ?, name = ?, surname = ?, type=?,coins=? WHERE matricola=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, u.getUsername());
			statement.setString(2, u.getPassword());
			statement.setDate(3, u.getEmail());
			statement.setString(4, u.getName());
			statement.setString(5, u.getSurname());
			statement.setString(6, u.getType());
			statement.setString(7, u.getCoins());
			statement.setString(8, u.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}

	@Override
	public void delete(User u) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM User WHERE id = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, u.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}

}
