package persistence.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Item;
import model.Order;
import persistence.util.*;
import persistence.dao.AddressDAO;
import persistence.dao.OrderDAO;
import persistence.dao.ProductDAO;
import persistence.dao.UserDAO;

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

//	@Override
//	public boolean create(Order o) {
//		Connection connection;
//		String query;
//		PreparedStatement statement;
//		connection = dataSource.getConnection();
//		
//		try {
//			
//			long id = IdBroker.getId(connection);
//			o.setId(id); 
//			
//		    
//		    query = " INSERT INTO Order(date, users, total, id) VALUES (?, ?, ?, ?)";
//		    
//		    statement = connection.prepareStatement(query);
//		    
//		    statement.setDate(1, new java.sql.Date(o.getDate().getTime()));
//		    statement.setLong(2, o.getUser().getId());
//		    statement.setFloat(3, o.getTotal());
//		    statement.setLong(4, o.getId());
//		    statement.executeUpdate();
//		    
//		    for(Item p : o.getProducts())
//		    {
//		    	long idp = IdBroker.getId(connection);
//		    	
//		    	PreparedStatement statement2;
//		    	String query2 = "INSERT INTO INCLUDE (product, order, id) VALUES (?, ?, ?)";
//		    	statement2 = connection.prepareStatement(query2);
//		    	statement2.setLong(1, p.getId());
//		    	statement2.setLong(2, o.getId());
//		    	statement2.setLong(3, idp);
//		    	statement2.executeUpdate();
//		    }
//		    
//		    return ok;
//		    
//		} catch (SQLException e) {
//		    e.printStackTrace();
//		} finally {
//		    DAOUtility.close(connection);
//		}
//		
//		return false;
//		
//	}
	
	
	public boolean create(Order order) {
		Connection connection = null;
		String query = null;
		PreparedStatement statement = null;
		try {
			
		    connection = dataSource.getConnection();
		    
		    long id = IdBroker.getId(connection);
			order.setId(id);
			
			query = "insert into orders(id,date,total,users,address)  values(?,?,?,?,?)";
		    statement = connection.prepareStatement(query);
			
		    statement.setLong(1, order.getId());
		    statement.setDate(2,new java.sql.Date(order.getDate().getTime()));
		    statement.setLong(3, (long) order.getTotal());
		    
		    UserDAO usersDAO = DatabaseManager.getInstance().getDaoFactory().getUserDAO();   
		    statement.setLong(4, usersDAO.findByEmail(order.getUser().getEmail()).getId());
		    statement.setLong(5, order.getAddress().getId());
			    
		    boolean ok =  (statement.executeUpdate() > 0) ? true : false;
		    
			
		    if(ok) {
			    for(Item i : order.getProducts())
				    {
			    	
				    	long idp = IdBroker.getId(connection);
				    	
				    	PreparedStatement statement2;
				    	String query2 = "INSERT INTO INCLUDE (id, product,orders,quantity) VALUES (?, ?, ?,?)";
				    	statement2 = connection.prepareStatement(query2);
				    	statement2.setLong(1, idp);
				    	statement2.setLong(2, i.getProduct().getId());
				    	statement2.setLong(3, order.getId());
				    	statement2.setLong(4, i.getQuantity());
				    	statement2.executeUpdate();
				    }
		    }
		    
		    return ok;
		} catch (

		SQLException e) {
		    e.printStackTrace();
		} finally {
		    DAOUtility.close(connection);   
		}
		return false;
	}

	@Override
	public List<Order> findByUser(long id) {
		Connection connection = this.dataSource.getConnection();
		List<Order> orders = new ArrayList<Order>();
		try {
			Order order;
			PreparedStatement statement;
			String query = "select * from Orders where users=?";
			statement = connection.prepareStatement(query);
			statement.setLong(1, id);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				order = new Order();
				order.setId(result.getLong("id"));				
				order.setDate(result.getDate("date"));
				order.setTotal(result.getFloat("total"));
				
				UserDAO usersDAO = DatabaseManager.getInstance().getDaoFactory().getUserDAO();
				order.setUser(usersDAO.findById(id));
				
				AddressDAO addressDao = DatabaseManager.getInstance().getDaoFactory().getAddressDAO();
				order.setAddress(addressDao.findById(result.getLong("address")));
			
				
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
			String query = "select * from Orders where id = ?";
			statement = connection.prepareStatement(query);
			statement.setLong(1, id);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				order = new Order();
				order.setId(id);				
				order.setDate(result.getDate("date"));
				order.setTotal(result.getFloat("total"));
				
				UserDAO usersDAO = DatabaseManager.getInstance().getDaoFactory().getUserDAO();
				order.setUser(usersDAO.findById(result.getLong("users")));
				
				AddressDAO addressDao = DatabaseManager.getInstance().getDaoFactory().getAddressDAO();
				order.setAddress(addressDao.findById(result.getLong("address")));
				
				ProductDAO productDao = DatabaseManager.getInstance().getDaoFactory().getProductDAO();
				
				PreparedStatement instatement;
				String inquery = "select * from include where orders = ?";
				instatement = connection.prepareStatement(inquery);
				instatement.setLong(1, id);
				ResultSet inresult = instatement.executeQuery();
				List<Item> products= new ArrayList<Item>();
				while(inresult.next()) {
					Item i=new Item(productDao.findById(inresult.getLong("product")),inresult.getInt("quantity"));
					products.add(i);
				}
				
				order.setProducts(products);
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			DAOUtility.close(connection);
		}	
		return order;
	}

}
