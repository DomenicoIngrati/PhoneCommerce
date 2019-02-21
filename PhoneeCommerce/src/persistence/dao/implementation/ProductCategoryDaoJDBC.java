package persistence.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.ProductCategory;
import persistence.dao.ProductCategoryDAO;
import persistence.util.DAOUtility;
import persistence.util.DataSource;
import persistence.util.IdBroker;
import persistence.util.PersistenceException;

public class ProductCategoryDaoJDBC implements ProductCategoryDAO{

	private DataSource dataSource;
	
	public ProductCategoryDaoJDBC(DataSource dataSource) {
		this.dataSource=dataSource;
	}
	
	@Override
	public void create(ProductCategory o) {
		Connection connection;
		String query;
		PreparedStatement statement;
		connection = dataSource.getConnection();
		
		try {
			
			long id = IdBroker.getId(connection);
			o.setId(id); 
			
		    
		    query = " INSERT INTO ProductCategory(id, name) VALUES (?, ?)";
		    
		    statement = connection.prepareStatement(query);
		    
		    statement.setLong(1, o.getId());
		    statement.setString(2, o.getName());

		    statement.executeUpdate();
		    
		} catch (SQLException e) {
		    e.printStackTrace();
		} finally {
		    DAOUtility.close(connection);
		}

		
	}

	@Override
	public void delete(ProductCategory tc) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM ProductCategory WHERE id = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setLong(1, tc.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			DAOUtility.close(connection);
		}
		
	}

	@Override
	public void update(ProductCategory tc) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update ProductCategory SET name = ? WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(update);
	
			statement.setString(1, tc.getName());

			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			DAOUtility.close(connection);
		}
		
	}

	@Override
	public ProductCategory findByName(String name) {
		Connection connection = this.dataSource.getConnection();
		ProductCategory category = null;
		try {
			PreparedStatement statement;
			String query = "select * from Order where name = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, name);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				category = new ProductCategory();
				category.setId(result.getInt("id"));				
				category.setName(result.getString("name"));
				

			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			DAOUtility.close(connection);
		}	
		return category;
	}

}
