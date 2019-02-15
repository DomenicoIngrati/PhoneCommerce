package persistencejdbc;

import org.apache.tomcat.jdbc.pool.DataSource;

import model.User;
import persistence.dao.UserDAO;

public class UserDaoJDBC implements UserDAO {

	
	private DataSource dataSource;
	
	public UserDaoJDBC(DataSource dataSource) {
		this.dataSource=dataSource;
	}
	
	@Override
	public boolean create(User modelObject) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User findReview(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(User modelObject) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void delete(User u) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM User WHERE id = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, u.getId());
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
