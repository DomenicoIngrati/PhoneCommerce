package persistence.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
		Connection connection = dataSource.getConnection();
		String query;
		PreparedStatement statement;
		
		try {
			
			long id = IdBroker.getId(connection);
			o.setId(id);
			
		    query = " INSERT INTO ProductCategory ( name) VALUES ( ?)";
		    
		    statement = connection.prepareStatement(query);
		    
		    statement.setLong(1, o.getId());
		    statement.setString(1, o.getName());

		    statement.executeUpdate();
		    
		} catch (SQLException e) {
		    e.printStackTrace();
		} finally {
		    DAOUtility.close(connection);
		}

		
	}

	@Override
	public void deleteById(ProductCategory tc) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM ProductCategory WHERE name = ? ";
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
	public void deleteByName(ProductCategory tc) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM ProductCategory WHERE name = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, tc.getName());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			DAOUtility.close(connection);
		}
		
	}

	@Override
	public void updateName(ProductCategory tc) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update ProductCategory SET name = ? WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(update);
	
			statement.setString(1, tc.getName());
			statement.setLong(2, tc.getId());

			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			DAOUtility.close(connection);
		}
		
	}
	
	@Override
	public void updateSubcategory(ProductCategory tc) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update ProductCategory SET subcategory = ? WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(update);
			
			ProductCategoryDaoJDBC cat = new ProductCategoryDaoJDBC(dataSource);
			ProductCategory pcat = cat.findByName(tc.getAnchestorCategory().getName());
			
			if(pcat == null)
			{
				cat.create(tc.getAnchestorCategory());
				pcat = cat.findByName(tc.getAnchestorCategory().getName());
			}
			
			statement.setLong(1, pcat.getId());
			statement.setLong(2, tc.getId());

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
			String query = "select * from ProductCategory where name = ?";
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

	@Override
	public List <ProductCategory> findAll() {
		Connection connection = this.dataSource.getConnection();
		List <ProductCategory> categories = new ArrayList<ProductCategory>();
		try {
			PreparedStatement statement;
			String query = "select * from ProductCategory";
			statement = connection.prepareStatement(query);

			ResultSet result = statement.executeQuery();
			while (result.next()) {
				ProductCategory cat = new ProductCategory();
				
				cat.setId(result.getInt("id"));				
				cat.setName(result.getString("name"));
				categories.add(cat);
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			DAOUtility.close(connection);
		}	
		return categories;
	}

}
