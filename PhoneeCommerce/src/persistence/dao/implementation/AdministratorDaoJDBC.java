package persistence.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Product;
import model.Type;
import model.User;
import persistence.dao.AdministratorDAO;
import persistence.util.DAOUtility;
import persistence.util.DataSource;
import persistence.util.IdBroker;
import persistence.util.PersistenceException;;

public class AdministratorDaoJDBC implements AdministratorDAO {
	
	private DataSource dataSource;
	
	public AdministratorDaoJDBC(DataSource dataSource) {
		this.dataSource=dataSource;
	}
	
	@Override
	public void create(User admin) {
		Connection connection = null;
		String query = null;
		PreparedStatement statement = null;
		try {

		    connection = dataSource.getConnection();
		    
		    long id = IdBroker.getId(connection);
			admin.setId(id);
			
		    query = "insert into Administrator(email, password, id)  values(?,?,?)";
		    statement = connection.prepareStatement(query);
		    statement.setString(1, admin.getEmail());
		    statement.setString(2, admin.getPassword());
		    statement.setLong(3, admin.getId());
		    statement.executeUpdate();

		} catch (

		SQLException e) {
		    e.printStackTrace();
		} finally {
		    DAOUtility.close(connection);
		    
		}
		
	}

	@Override
	public void delete(User admin) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM Administrator WHERE id = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setLong(1, admin.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			DAOUtility.close(connection);
		}
		
	}

	@Override
	public void update(User admin) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update Administrator SET user = ?, password = ? WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, admin.getEmail());
			statement.setString(2, admin.getPassword());
			statement.setLong(3, admin.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			DAOUtility.close(connection);
		}
		
	}

	@Override
	public User findByEmail(String email) {
		Connection connection = this.dataSource.getConnection();
		User u = null;
		try {
			PreparedStatement statement;
			String query = "select * from Administrator where email = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, email);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				u = new User();
				u.setId(result.getLong("id"));				
				u.setEmail(result.getString("email"));
				u.setPassword(result.getString("password"));
				u.setType(Type.Organizer);
			}
		}catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			DAOUtility.close(connection);
		}	
		return u;
	}

	@Override
	public User findById(long id) {
		Connection connection = this.dataSource.getConnection();
		User u = null;
		try {
			PreparedStatement statement;
			String query = "select * from Administrator where id = ?";
			statement = connection.prepareStatement(query);
			statement.setLong(1, id);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				u = new User();
				u.setId(result.getLong("id"));				
				u.setEmail(result.getString("email"));
				u.setPassword(result.getString("password"));
				u.setType(Type.Organizer);
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			DAOUtility.close(connection);
		}	
		return u;
	}

	
	public void putProduct(User admin, Product product) {
		
		Connection connection = dataSource.getConnection();
		String query = null;
		PreparedStatement statement = null;
		try {
			
		    long id = IdBroker.getId(connection);
		    
		    query = "insert into INSERISCE(admin, product, id)  values(?,?,?)";
		    statement = connection.prepareStatement(query);
		    
		    statement.setLong(1, admin.getId());
		    statement.setLong(2, product.getId());
		    statement.setLong(3, id);
		    statement.executeUpdate();
		    
		    ProductDaoJDBC p = new ProductDaoJDBC(dataSource);
		    p.create(product);
		    
		} catch (

		SQLException e) {
		    e.printStackTrace();
		} finally {
		    DAOUtility.close(connection);
		    
		}
		
	}

//	@Override
//	public void putProducts(List<Product> product) {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	public void deleteProduct(Product product) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM INSERISCE WHERE product = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setLong(1, product.getId());
			statement.executeUpdate();
			
			ProductDaoJDBC p = new ProductDaoJDBC(dataSource);
		    p.delete(product);
			
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			DAOUtility.close(connection);
		}
		
	}

	@Override
	public List<User> findAll() {
		Connection connection = this.dataSource.getConnection();
		List<User> admins = new ArrayList<User>();
		try {
			PreparedStatement statement;
			String query = "select * from Administrator";
			statement = connection.prepareStatement(query);

			ResultSet result = statement.executeQuery();
			while (result.next()) {
				User u = new User();
				u.setId(result.getLong("id"));				
				u.setEmail(result.getString("email"));
				u.setPassword(result.getString("password"));
				u.setType(Type.Organizer);
				
				admins.add(u);
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			DAOUtility.close(connection);
		}	
		return admins;
	}

}
