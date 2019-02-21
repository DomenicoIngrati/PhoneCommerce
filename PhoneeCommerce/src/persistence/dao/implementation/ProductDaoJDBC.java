package persistence.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import model.Product;
import model.ProductCategory;
import persistence.util.PersistenceException;
import persistence.dao.ProductDAO;
import persistence.util.*;

public class ProductDaoJDBC implements ProductDAO {

	private DataSource dataSource;
	
	public ProductDaoJDBC(DataSource dataSource) {
		this.dataSource=dataSource;
	}
	
	@Override
	public void delete(Product t) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM Product WHERE id = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setLong(1, t.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			DAOUtility.close(connection);
		}
	}

	@Override
	public void update(Product t) {
		Connection connection = this.dataSource.getConnection();
		try {
			//Non mi ricordo come vengono trattati gli array nel database per quanto riguarda reviews
			String update = "update Product SET name = ?, description = ?, price = ?, category = ? WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, t.getName());
			statement.setString(2, t.getDescription());
			statement.setLong(3, (long) t.getPrice());
			statement.setLong(4, t.getCategory().getId());
			statement.setLong(5, t.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			DAOUtility.close(connection);
		}
	}
	
	public Product findById(Long id) {
		Connection connection = this.dataSource.getConnection();
		Product product = null;
		try {
			PreparedStatement statement;
			String query = "select * from Product where id = ?";
			statement = connection.prepareStatement(query);
			statement.setLong(1, id);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				product = new Product();
				product.setId(result.getInt("id"));				
				product.setName(result.getString("name"));
				product.setDescription(result.getString("description"));
				product.setPrice(result.getFloat("prize"));
				//category??
//				product.setReviews(result.getString("reviews"));
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			DAOUtility.close(connection);
		}	
		return product;
	}
	
	public Set<Product> findByPrice(float price){
		
		Connection connection = this.dataSource.getConnection();
		Set<Product> products = new HashSet<Product>();
		try {
			Product product;
			PreparedStatement statement;
			String query = "select * from Product where price=?";
			statement = connection.prepareStatement(query);
			statement.setFloat(1, price);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				product = new Product();
				product.setId(result.getLong("id"));				
				product.setName(result.getString("name"));
				product.setDescription(result.getString("description"));
				product.setPrice(result.getFloat("price"));
				
				products.add(product);
			}
		}
		catch (SQLException e){
			throw new PersistenceException(e.getMessage());
		}
		finally {
			DAOUtility.close(connection);
		}
		return products;
	}
	
	public Set<Product> findByCategory(ProductCategory idCategory,Integer maxRow){
		
		Connection connection = this.dataSource.getConnection();
		Set<Product> products = new HashSet<Product>();
		try {
			Product product;
			PreparedStatement statement;
			String query = "select * from Product where idCategory=?";
			statement = connection.prepareStatement(query);
			statement.setLong(1, idCategory.getId());
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				product = new Product();
				product.setId(result.getLong("id"));				
				product.setName(result.getString("name"));
				product.setDescription(result.getString("description"));
				product.setPrice(result.getFloat("price"));
				
				products.add(product);
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}	 finally {
			DAOUtility.close(connection);
		}
		return products;
	}


	public void updatePriceProduct(Product p) {
		Connection connection = this.dataSource.getConnection();
		try {
			
			String update = "update Product SET price = ? WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setFloat(1, p.getPrice());
			statement.setLong(2, p.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			DAOUtility.close(connection);
		}
	}

	@Override
	public void create(Product p) {
		
		Connection connection = this.dataSource.getConnection();
		try {
			Long id = IdBroker.getId(connection);
			p.setId(id); 
			
			String insert = "insert into Product(id, nome, description, price) values (?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setLong(1, p.getId());
			statement.setString(2, p.getName());
			statement.setString(3, p.getDescription());
			statement.setFloat(4, p.getPrice());
		
			statement.executeUpdate();

		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch(SQLException excep) {
					throw new PersistenceException(e.getMessage());
				}
			} 
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	
	}


}
