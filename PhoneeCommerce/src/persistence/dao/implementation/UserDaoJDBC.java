package persistence.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Type;
import model.User;
import persistence.util.PersistenceException;
import persistence.dao.UserDAO;
import persistence.util.DAOUtility;
import persistence.util.DataSource;
import persistence.util.IdBroker;

public class UserDaoJDBC implements UserDAO {

	
	private DataSource dataSource;
	
	public UserDaoJDBC(DataSource dataSource) {
		this.dataSource=dataSource;
	}
	
	@Override
	public void create(User user) {
		Connection connection = null;
		String query = null;
		PreparedStatement statement = null;
		try {
			
		    connection = dataSource.getConnection();
		    
//		    long id = IdBroker.getId(connection);
//			user.setId(id);
		    
		    query = "insert into User (username, password, email, name, surname) values(?,?,?,?,?)";
		    statement = connection.prepareStatement(query);
		    statement.setString(1, user.getUsername());
		    statement.setString(2, user.getPassword());
		    statement.setString(3, user.getEmail());
		    statement.setString(4, user.getName());
		    statement.setString(5, user.getSurname());
		    statement.executeUpdate();
		} catch (SQLException e) {
		    e.printStackTrace();
		} finally {
		    DAOUtility.close(connection);
		    
		}
	}


	@Override
	public void delete(User u) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM User WHERE id = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setLong(1, u.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			DAOUtility.close(connection);
		}
	}

	@Override
	public User findById(int id) {
		Connection connection = this.dataSource.getConnection();
		User u = null;
		try {
			PreparedStatement statement;
			String query = "select * from User where id = ?";
			statement = connection.prepareStatement(query);
			statement.setLong(1, id);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				u = new User();
				u.setId(result.getLong("id"));				
				u.setName(result.getString("name"));
				u.setSurname(result.getString("surname"));
				u.setUsername(result.getString("username"));
				u.setEmail(result.getString("email"));
				u.setPassword(result.getString("password"));
				
				u.setType(Type.Customer);
				
				

			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			DAOUtility.close(connection);
		}	
		return u;
	}

	@Override
	public User findByUsername(String username) {
		Connection connection = this.dataSource.getConnection();
		User u = null;
		try {
			PreparedStatement statement;
			String query = "select * from User where username = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, username);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				u = new User();
				u.setId(result.getLong("id"));				
				u.setName(result.getString("name"));
				u.setSurname(result.getString("surname"));
				u.setUsername(result.getString("username"));
				u.setEmail(result.getString("email"));
				u.setPassword(result.getString("password"));
				u.setType(Type.Customer);

				
				

			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			DAOUtility.close(connection);
		}	
		return u;
	}

	@Override
	public User findByEmail(String email) {
		Connection connection = this.dataSource.getConnection();
		User u = null;
		try {
			PreparedStatement statement;
			String query = "select * from User where email = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, email);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				u = new User();
				u.setId(result.getLong("id"));				
				u.setName(result.getString("name"));
				u.setSurname(result.getString("surname"));
				u.setUsername(result.getString("username"));
				u.setEmail(result.getString("email"));
				u.setPassword(result.getString("password"));
				u.setType(Type.Customer);
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			DAOUtility.close(connection);
		}	
		return u;
	}

	@Override
	public void update(User user) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update User SET username = ?, name = ?, surname = ?, email = ?, password = ?  WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(update);

			statement.setString(1, user.getUsername());
			statement.setString(2, user.getName());
			statement.setString(3, user.getSurname());
			statement.setString(4, user.getEmail());
			statement.setString(5, user.getPassword());
			statement.setLong(6, user.getId());
			
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			DAOUtility.close(connection);
		}
	}

}
