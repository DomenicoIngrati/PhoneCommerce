package persistence.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
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
			DAOUtility.close(connection);
		}
	}

	@Override
	public void update(Order o) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update Order SET data = ?, users = ?, total = ? WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(update);
			long secs = o.getDate().getTime();
			statement.setDate(1, new java.sql.Date(secs));
			statement.setLong(2, o.getUser().getId());
			statement.setFloat(3, o.getTotal());
			statement.setLong(4, o.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			DAOUtility.close(connection);
		}
	}

	@Override
	public void create(Order o) {
		Connection connection;
		String query;
		PreparedStatement statement;
		connection = dataSource.getConnection();
		
		try {
			
			long id = IdBroker.getId(connection);
			o.setId(id); 
			
		    
		    query = " INSERT INTO Order(date, users, total, id) VALUES (?, ?, ?, ?)";
		    
		    statement = connection.prepareStatement(query);
		    
		    statement.setDate(1, new java.sql.Date(o.getDate().getTime()));
		    statement.setLong(2, o.getUser().getId());
		    statement.setFloat(3, o.getTotal());
		    statement.setLong(4, o.getId());
		    statement.executeUpdate();
		    
		    for(Product p : o.getProducts())
		    {
		    	long idp = IdBroker.getId(connection);
				
		    	
		    	PreparedStatement statement2;
		    	String query2 = "INSERT INTO INCLUDE (product, order, id) VALUES (?, ?, ?)";
		    	statement2 = connection.prepareStatement(query2);
		    	statement2.setLong(1, p.getId());
		    	statement2.setLong(2, o.getId());
		    	statement2.setLong(3, idp);
		    	statement2.executeUpdate();
		    }
		    
		    
		    
		} catch (SQLException e) {
		    e.printStackTrace();
		} finally {
		    DAOUtility.close(connection);
		}
		
	}

	@Override
	public Set<Order> findByUser(long id, Integer offset, Integer limit) {
		Connection connection = this.dataSource.getConnection();
		Set<Order> orders = new HashSet<Order>();
		try {
			Order order;
			PreparedStatement statement;
			String query = "select * from Order where users=?";
			statement = connection.prepareStatement(query);
			statement.setLong(1, id);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				order = new Order();
				order.setId(result.getLong("id"));				
				order.setDate(result.getDate("date"));
				order.setTotal(result.getFloat("total"));
				
//				product.setReviews(result.getString("review"));
				//category
				
				orders.add(order);
			}
		}
		catch (SQLException e){
			throw new PersistenceException(e.getMessage());
		}
		finally {
			DAOUtility.close(connection);
		}
		return orders;

	}

	@Override
	public Order findById(long id) {
		Connection connection = this.dataSource.getConnection();
		Order order = null;
		try {
			PreparedStatement statement;
			String query = "select * from Order where id = ?";
			statement = connection.prepareStatement(query);
			statement.setLong(1, id);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				order = new Order();
				order.setId(result.getInt("id"));				
				order.setDate(result.getDate("date"));
				order.setTotal(result.getFloat("total"));

			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			DAOUtility.close(connection);
		}	
		return order;
	}

}
