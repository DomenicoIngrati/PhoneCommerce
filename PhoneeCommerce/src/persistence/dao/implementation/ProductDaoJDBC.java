package persistence.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import model.Order;
import model.Product;
import model.ProductCategory;
import persistence.IdBroker;
import persistence.PersistenceException;
import persistence.dao.OrderDAO;
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
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}

	@Override
	public void update(Product t) {
		Connection connection = this.dataSource.getConnection();
		try {
			//Non mi ricordo come vengono trattati gli array nel database per quanto riguarda reviews
			String update = "update Product SET name = ?, description = ?, prize = ?, WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, t.getName());
			statement.setString(2, t.getDescription());
			statement.setLong(3, (long) t.getPrize());
			statement.setLong(5, t.getId());
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
	
	public Product findById(Integer id) {
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
				product.setPrize(result.getFloat("prize"));
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
			String query = "select * from Product where prize=?";
			statement = connection.prepareStatement(query);
			statement.setFloat(1, price);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				product = new Product();
				product.setId(result.getLong("id"));				
				product.setName(result.getString("name"));
				product.setDescription(result.getString("description"));
				product.setPrize(result.getFloat("prize"));
//				product.setReviews(result.getString("review"));
				
				products.add(product);
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}	 finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
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
				product.setPrize(result.getFloat("prize"));
//				product.setReviews(result.getString("review"));
				
				products.add(product);
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}	 finally {
			DAOUtility.close(connection);
		}
		return products;
	}

//	public Integer soldProduct(Event e, ProductCategory idCategory);

	public void updatePriceProduct(Product p) {
		Connection connection = this.dataSource.getConnection();
		try {
			//Non mi ricordo come vengono trattati gli array nel database per quanto riguarda reviews
			String update = "update Product SET prize = ? WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setFloat(1, p.getPrize());
			statement.setLong(2, p.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			DAOUtility.close(connection);
		}
	}

	@Override
	public void create(Product modelObject) {
		
		Connection connection = this.dataSource.getConnection();
		try {
			Long id = IdBroker.getId(connection);
			corso.setCodice(id); 
			String insert = "insert into corso(codice, nome) values (?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setLong(1, corso.getCodice());
			statement.setString(2, corso.getNome());

			//connection.setAutoCommit(false);
			//serve in caso gli studenti non siano stati salvati. Il DAO studente apre e chiude una transazione nuova.
			//connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);			
			statement.executeUpdate();
			// salviamo anche tutti gli studenti del gruppo in CASACATA
			this.updateStudenti(corso, connection);
			//connection.commit();
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
