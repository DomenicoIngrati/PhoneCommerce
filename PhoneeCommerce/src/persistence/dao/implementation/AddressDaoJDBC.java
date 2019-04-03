package persistence.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Address;
import model.Type;
import model.User;
import persistence.util.PersistenceException;
import persistence.dao.AddressDAO;
import persistence.dao.UserDAO;
import persistence.util.DAOUtility;
import persistence.util.DataSource;
import persistence.util.IdBroker;

public class AddressDaoJDBC implements AddressDAO {

	
	private DataSource dataSource;
	
	public AddressDaoJDBC(DataSource dataSource) {
		this.dataSource=dataSource;
	}
	
	@Override
	public boolean create(Address u) {
		Connection connection = dataSource.getConnection();;
		String query;
		PreparedStatement statement;
		try {

		    long id = IdBroker.getId(connection);
			u.setId(id);

		    query = "insert into users (namelastname, address, city, province, zipcode, tel, id) values (?,?,?,?,?,?)";
		    statement = connection.prepareStatement(query);
		    statement.setString(1, u.getNamelastname());
		    statement.setString(2, u.getAddress());
		    statement.setString(3, u.getCity());
		    statement.setString(4, u.getProvince());
		    statement.setString(5, u.getZipcode());
		    statement.setString(6, u.getTel());
		    statement.setLong(7, u.getId());

		    return (statement.executeUpdate() > 0) ? true : false;
		} catch (SQLException e) {
		    e.printStackTrace();

		} finally {
		    DAOUtility.close(connection);
		}
		return false;
	}


	@Override
	public boolean delete(Address u) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM address WHERE id = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setLong(1, u.getId());
			return (statement.executeUpdate() > 0) ? true : false;
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			DAOUtility.close(connection);
		}
	}

	@Override
	public Address findById(int id) {
		Connection connection = this.dataSource.getConnection();
		User u = null;
		try {
			PreparedStatement statement;
			String query = "select * from address where id = ?";
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
				
				if(result.getBoolean("admin"))
					u.setType(Type.Organizer);
				else
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
			String query = "select * from users where username = ?";
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
				
				if(result.getBoolean("admin"))
					u.setType(Type.Organizer);
				else
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
			String query = "select * from users where email = ?";
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

				if(result.getBoolean("admin"))
					u.setType(Type.Organizer);
				else
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
			String update = "update users SET username = ?, name = ?, surname = ?, email = ?, password = ?  WHERE id=?";
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
