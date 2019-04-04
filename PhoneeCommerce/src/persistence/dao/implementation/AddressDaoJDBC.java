package persistence.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Address;
import model.User;
import persistence.util.PersistenceException;
import persistence.dao.AddressDAO;
import persistence.dao.UserDAO;
import persistence.util.DAOUtility;
import persistence.util.DAOfactory;
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
			System.out.println(u);
		    query = "insert into address (namelastname, address, city, province, zipcode, tel, id, users) values (?,?,?,?,?,?,?,?)";
		    statement = connection.prepareStatement(query);
		    statement.setString(1, u.getNamelastname());
		    statement.setString(2, u.getAddress());
		    statement.setString(3, u.getCity());
		    statement.setString(4, u.getProvince());
		    statement.setString(5, u.getZipcode());
		    statement.setString(6, u.getTel());
		    statement.setLong(7, u.getId());
		    statement.setLong(8, u.getUser().getId());

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
		Address u = null;
		try {
			PreparedStatement statement;
			String query = "select * from address where id = ?";
			statement = connection.prepareStatement(query);
			statement.setLong(1, id);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				u = new Address();
				u.setId(result.getLong("id"));
				u.setNamelastname(result.getString("namelastname"));
				u.setAddress(result.getString("address"));
				u.setCity(result.getString("city"));
				u.setProvince(result.getString("province"));
				u.setZipcode(result.getString("zipcode"));
				u.setTel(result.getString("tel"));
				
				DAOfactory factory = DAOfactory.getDAOFactory(DAOfactory.POSTGRESQL);
				UserDAO dao = factory.getUserDAO();
				
				u.setUser(dao.findById(result.getLong("users")));
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			DAOUtility.close(connection);
		}	
		return u;
	}

	@Override
	public List<Address> findByUserId(User user) {

		Connection connection = this.dataSource.getConnection();
		List<Address> addresses = new ArrayList<Address>();
		try {
			Address address;
			PreparedStatement statement;
			String query = "select * from address where users=?";
			statement = connection.prepareStatement(query);
			statement.setLong(1, user.getId());
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				
				address = new Address();
				address.setId(result.getLong("id"));
				address.setNamelastname(result.getString("namelastname"));
				address.setAddress(result.getString("address"));
				address.setCity(result.getString("city"));
				address.setProvince(result.getString("province"));
				address.setZipcode(result.getString("zipcode"));
				address.setTel(result.getString("tel"));
				DAOfactory factory = DAOfactory.getDAOFactory(DAOfactory.POSTGRESQL);
				UserDAO dao = factory.getUserDAO();
				address.setUser(dao.findById(result.getLong("users")));
				
				
				addresses.add(address);
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}	 finally {
			DAOUtility.close(connection);
		}
		return addresses;
	}
	
	public boolean update(Address a) {
		Connection connection = this.dataSource.getConnection();
		try {
			
			String update = "update address SET namelastname = ?, address = ?, city = ?, province = ?, zipcode=?, tel=?, users=? WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, a.getNamelastname());
			statement.setString(2, a.getAddress());
			statement.setString(3,  a.getCity());
			statement.setString(4, a.getProvince());
			statement.setString(5, a.getZipcode());
			statement.setString(6,  a.getTel());
			statement.setLong(7, a.getUser().getId());
			statement.setLong(8, a.getId());

			
			return (statement.executeUpdate() > 0) ? true : false;
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			DAOUtility.close(connection);
		}
		
	}

}
