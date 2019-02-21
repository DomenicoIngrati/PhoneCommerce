package persistence.dao.implementation;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Review;
import persistence.dao.ReviewDAO;
import persistence.util.DataSource;
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
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}

	@Override
	public void update(Review r) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update Review SET user_id = ?, product_id = ?, title = ?, text = ?, feedback = ?  WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(update);

			statement.setDate(1, r.getUser().getId());
			statement.setString(2, r.getProduct().getId());
			statement.setString(3, r.getTitle());
			statement.setString(4, r.getText());
			statement.setString(5, r.getFeedback());
			statement.setString(5, r.getId());
			
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

}
