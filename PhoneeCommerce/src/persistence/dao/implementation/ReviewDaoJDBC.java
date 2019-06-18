package persistence.dao.implementation;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Product;
import model.ProductCategory;
import model.Review;
import model.User;
import persistence.dao.ReviewDAO;
import persistence.util.DAOUtility;
import persistence.util.DataSource;
import persistence.util.IdBroker;
import persistence.util.PersistenceException;

public class ReviewDaoJDBC implements ReviewDAO {

	private DataSource dataSource;
	
	public ReviewDaoJDBC(DataSource dataSource) {
		this.dataSource=dataSource;
	}
	
	@Override
	public void delete(Review r) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM Review WHERE id = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setLong(1, r.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			DAOUtility.close(connection);
		}
	}

	@Override
	public void update(Review r) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update Review SET users = ?, product = ?, title = ?, text = ?, feedback = ?  WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(update);

			statement.setLong(1, r.getUser().getId());
			statement.setLong(2, r.getProduct().getId());
			statement.setString(3, r.getTitle());
			statement.setString(4, r.getText());
			statement.setInt(5, r.getFeedback());
			statement.setLong(6, r.getId());
			
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			DAOUtility.close(connection);
		}

	}
	
	@Override
	public boolean create(Review r) {
		
		Connection connection;
		String query;
		PreparedStatement statement;
		connection = dataSource.getConnection();
		
		try {
			Long id = IdBroker.getId(connection);
			
			query = " INSERT INTO Review(id, title, text, feedback, users, product) VALUES (?, ?, ?, ?, ?, ?)";
		    
			statement = connection.prepareStatement(query);
		    
			statement.setLong(1, id);
			statement.setString(2, r.getTitle());
			statement.setString(3, r.getText());
			statement.setInt(4, r.getFeedback());
		    statement.setLong(5, r.getUser().getId());
			statement.setLong(6, r.getProduct().getId());
			
			boolean ok = (statement.executeUpdate() > 0) ? true : false;
			
			return ok;

		} catch (SQLException e) {
		    e.printStackTrace();

		} finally {
		    DAOUtility.close(connection);
		}
	return false;
	}

	@Override
	public Review findById(Long id) {
		Connection connection = this.dataSource.getConnection();
		Review r = null;
		try {
			PreparedStatement statement;
			String query = "select * from Review where id = ?";
			statement = connection.prepareStatement(query);
			statement.setLong(1, id);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				r = new Review();
				r.setId(result.getLong("id"));				
				r.setText(result.getString("text"));
				r.setTitle(result.getString("title"));
				r.setFeedback(result.getInt("feedback"));
				

			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			DAOUtility.close(connection);
		}	
		return r;
	}

	@Override
	public List<Review> findByUser(User user) {
		Connection connection = this.dataSource.getConnection();
		List<Review> reviews = new ArrayList<Review>();
		try {
			Review r;
			PreparedStatement statement;
			String query = "select * from Review where users=?";
			statement = connection.prepareStatement(query);
			statement.setLong(1, user.getId());
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				r = new Review();
				r.setId(result.getLong("id"));				
				r.setText(result.getString("text"));
				r.setTitle(result.getString("title"));
				r.setFeedback(result.getInt("feedback"));
				r.setUser(user);
				
				ProductDaoJDBC cat = new ProductDaoJDBC(dataSource);
				Product  prod = cat.findById(result.getLong("product"), true);
				r.setProduct(prod);

				reviews.add(r);
			}
		}
		catch (SQLException e){
			throw new PersistenceException(e.getMessage());
		}
		finally {
			DAOUtility.close(connection);
		}
		return reviews;
	}
	
	@Override
	public List<Review> findByProduct(Product p) {
		Connection connection = this.dataSource.getConnection();
		List<Review> reviews = new ArrayList<Review>();
		try {
			Review r;
			PreparedStatement statement;
			String query = "select * from Review where product=?";
			statement = connection.prepareStatement(query);
			statement.setLong(1, p.getId());
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				r = new Review();
				r.setId(result.getLong("id"));				
				r.setText(result.getString("text"));
				r.setTitle(result.getString("title"));
				r.setFeedback(result.getInt("feedback"));
				r.setProduct(p);
				
				UserDaoJDBC dao = new UserDaoJDBC(dataSource);
				User u = dao.findById(result.getLong("users"));
				r.setUser(u);

				reviews.add(r);
			}
		}
		catch (SQLException e){
			throw new PersistenceException(e.getMessage());
		}
		finally {
			DAOUtility.close(connection);
		}
		return reviews;
	}

	@Override
	public List<Review> findByTitle(String title) {
		Connection connection = this.dataSource.getConnection();
		List<Review> reviews = new ArrayList<Review>();
		try {
			Review r;
			PreparedStatement statement;
			String query = "select * from Review where title=?";
			statement = connection.prepareStatement(query);
			statement.setString(1, title);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				r = new Review();
				r.setId(result.getLong("id"));				
				r.setText(result.getString("text"));
				r.setTitle(result.getString("title"));
				r.setFeedback(result.getInt("feedback"));

				reviews.add(r);
			}
		}
		catch (SQLException e){
			throw new PersistenceException(e.getMessage());
		}
		finally {
			DAOUtility.close(connection);
		}
		return reviews;
	}

	@Override
	public List<Review> findByFeedback(int feedback) {
		Connection connection = this.dataSource.getConnection();
		List<Review> reviews = new ArrayList<Review>();
		try {
			Review r;
			PreparedStatement statement;
			String query = "select * from Review where feedback=?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, feedback);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				r = new Review();
				r.setId(result.getLong("id"));				
				r.setText(result.getString("text"));
				r.setTitle(result.getString("title"));
				r.setFeedback(result.getInt("feedback"));

				reviews.add(r);
			}
		}
		catch (SQLException e){
			throw new PersistenceException(e.getMessage());
		}
		finally {
			DAOUtility.close(connection);
		}
		return reviews;
	}


}
