Gpackage persistencejdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.tomcat.jdbc.pool.DataSource;

import model.Order;
import persistence.PersistenceException;
import persistence.dao.OrderDAO;

public class OrderDaoJDBC implements OrderDAO {
	
	private DataSource dataSource;
	
	public OrderDaoJDBC(DataSource dataSource) {
		this.dataSource=dataSource;
	}

	@Override
	public void delete(Order o) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM Order WHERE id = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, o.getId());
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
	public void update(Order o) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update studente SET data = ?, user_id = ?, totale = ? WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(update);
			long secs = o.getDate().getTime();
			statement.setDate(1, new java.sql.Date(secs));
			statement.setString(2, o.getUser().getId());
			statement.setString(3, o.getTotal());
			statement.setString(4, o.getId());
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
