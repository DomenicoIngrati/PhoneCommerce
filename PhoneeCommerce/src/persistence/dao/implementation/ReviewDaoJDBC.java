package persistence.dao.implementation;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

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
	public void create(Review r) {
		Connection connection;
		String query;
		PreparedStatement statement;
		connection = dataSource.getConnection();
		
		try {
			
			long id = IdBroker.getId(connection);
			r.setId(id); 
			
		    
		    query = " INSERT INTO Review(title, text, feedback, users, product, id) VALUES (?, ?, ?, ?, ?, ?)";
		    
		    statement = connection.prepareStatement(query);
		    
		    statement.setLong(4, r.getUser().getId());
			statement.setLong(5, r.getProduct().getId());
			statement.setString(1, r.getTitle());
			statement.setString(2, r.getText());
			statement.setInt(3, r.getFeedback());
			statement.setLong(6, r.getId());
		    statement.executeUpdate();
		    
		} catch (SQLException e) {
		    e.printStackTrace();
		} finally {
		    DAOUtility.close(connection);
		}
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
	public Set<Review> findByUser(User user) {
		Connection connection = this.dataSource.getConnection();
		Set<Review> reviews = new HashSet<Review>();
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
	public Set<Review> findByTitle(String title) {
		Connection connection = this.dataSource.getConnection();
		Set<Review> reviews = new HashSet<Review>();
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
	public Set<Review> findByFeedback(int feedback) {
		Connection connection = this.dataSource.getConnection();
		Set<Review> reviews = new HashSet<Review>();
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

//	@Override
//	public int getAVGbyId(Integer id) {
//		// TODO Auto-generated method stub
//		return 0;
//	}

}
