package persistencejdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.tomcat.jdbc.pool.DataSource;

import model.Order;
import model.Product;
import model.ProductCategory;
import model.Scuola;
import model.Studente;
import persistence.PersistenceException;
import persistence.ScuolaDaoJDBC;
import persistence.dao.Integer;
import persistence.dao.OrderDAO;
import persistence.dao.ProductDAO;
import persistence.dao.ScuolaDao;
import persistence.dao.Set;

public class ProductDaoJDBC implements ProductDAO {

	private DataSource dataSource;
	
	public public ProductDaoJDBC(DataSource dataSource) {
		this.dataSource=dataSource;
	}
	
	@Override
	public void delete(Product t) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM Product WHERE id = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, t.getId());
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
			String update = "update Product SET name = ?, description = ?, prize = ?, reviews = ? WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, t.getName());
			statement.setString(2, t.getDescription());
			statement.setString(3, t.getPrize());
			statement.setString(4, t.getReviews());
			statement.setString(5, t.getId());
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
			statement.setString(1, id);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				product = new Product();
				product.setId(result.getString("id"));				
				product.setNome(result.getString("name"));
				product.setDescription(result.getString("description"));
				product.setPrize(result.getString("prize"));
				product.setReviews(result.getString("reviews"));
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}	
		return studente;
	}
	
	public Set<Product> findByPrice(float price);
	
	public Set<Product> findByCategory(ProductCategory idCategory,Integer maxRow);

//	public Integer soldProduct(Event e, ProductCategory idCategory);

	public int updatePriceProduct(Product product);

}
