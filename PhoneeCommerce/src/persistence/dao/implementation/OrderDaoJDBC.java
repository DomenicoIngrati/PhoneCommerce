package persistence.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;


import model.Order;
import model.Product;
import persistence.util.*;
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
			statement.setLong(1, o.getId());
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
			statement.setLong(2, o.getUser().getId());
			statement.setLong(3, (long) o.getTotal());
			statement.setLong(4, o.getId());
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
	public void create(Order modelObject) {
		Connection connection = null;
		String query = null;
		PreparedStatement statement = null;
		Integer id=null;

		try {
		    connection = dataSource.getConnection();
		    query = " INSERT INTO orders( date, user_id, totalcost) VALUES (?, ?, ?)";
		    statement = connection.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
		    statement.setDate(1, new java.sql.Date(modelObject.getDate().getTime()));
		    statement.setInt(2, modelObject.getUser().getId());
		    statement.setFloat(3, modelObject.getTotal());
		    statement.executeUpdate();
		    ResultSet rs = statement.getGeneratedKeys();
	        if(rs.next())
	        {
	             id = rs.getInt(1);
	        }
		} catch (SQLException e) {
		    e.printStackTrace();
		} finally {
		    DAOUtility.close(connection);
		    DAOUtility.close(statement);
		}
//		return id;
		
	}

	@Override
	public Set<Product> findByUser(Integer id, Integer offset, Integer limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
