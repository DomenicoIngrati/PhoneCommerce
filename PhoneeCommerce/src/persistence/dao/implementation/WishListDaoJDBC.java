package persistence.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Product;
import model.User;
import model.Wishlist;
import persistence.dao.ProductDAO;
import persistence.dao.UserDAO;
import persistence.dao.WishlistDAO;
import persistence.util.DAOUtility;
import persistence.util.DAOfactory;
import persistence.util.DataSource;
import persistence.util.IdBroker;
import persistence.util.PersistenceException;

public class WishListDaoJDBC implements WishlistDAO {

	private DataSource dataSource;
	
	public WishListDaoJDBC(DataSource dataSource) {
		this.dataSource=dataSource;
	}
	
	@Override
	public boolean create(Wishlist w) {
		Connection connection = null;
		String query = null;
		PreparedStatement statement = null;
		try {
			
		    connection = dataSource.getConnection();
		    
		    long id = IdBroker.getId(connection);
			w.setId(id);
			
			query = "insert into Wishlist(title,users,id,type)  values(?,?,?,?)";
		    statement = connection.prepareStatement(query);
			
			if(findByUser(w.getUser()).isEmpty())
				
			{	w.setTitle("Lista dei desideri");
			    statement.setString(1, w.getTitle());
			    
			    UserDaoJDBC userDAO = new UserDaoJDBC(dataSource);
			    
			    statement.setLong(2, userDAO.findByEmail(w.getUser().getEmail()).getId());
			    statement.setLong(3, w.getId());
			    statement.setString(4, "default");
			}
			else
			{
			    statement.setString(1, w.getTitle());
			    
			    UserDaoJDBC userDAO = new UserDaoJDBC(dataSource);
			  
			    statement.setLong(2, userDAO.findByEmail(w.getUser().getEmail()).getId());
			    statement.setLong(3, w.getId());
			    statement.setString(4, "custom");
			}
			    
		    
		    
			boolean ok =  (statement.executeUpdate() > 0) ? true : false;
		    
			
		    if(w.getProducts() != null && ok)
		    {
		    	for(Product p : w.getProducts())
			    {
			    	this.updateWishProduct(w, p);
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
	public boolean delete(Wishlist w) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM WishList WHERE id = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setLong(1, w.getId());
			return (statement.executeUpdate() > 0) ? true : false;
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			DAOUtility.close(connection);
		}
	}

	@Override
	public boolean update(Wishlist w) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update WishList SET title = ?, users = ? WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, w.getTitle());
			statement.setLong(2, w.getUser().getId());
			statement.setLong(3, w.getId());
			return (statement.executeUpdate() > 0) ? true : false;
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			DAOUtility.close(connection);
		}

	}

	@Override
	public Wishlist findById(Long id) {
		Connection connection = this.dataSource.getConnection();
		Wishlist w = null;
		try {
			PreparedStatement statement;
			String query = "select * from Wishlist where id = ?";
			statement = connection.prepareStatement(query);
			statement.setLong(1, id);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				w = new Wishlist();
				w.setId(result.getLong("id"));				
				w.setTitle(result.getString("title"));
				w.setType(result.getString("type"));
				DAOfactory factory = DAOfactory.getDAOFactory(DAOfactory.POSTGRESQL);
				UserDAO dao = factory.getUserDAO();
				w.setUser(dao.findById(result.getLong("users")));
				
				ProductDAO productDao = factory.getProductDAO();
				
				PreparedStatement instatement;
				String inquery = "select * from CONTIENE where wishlist = ?";
				instatement = connection.prepareStatement(inquery);
				instatement.setLong(1, id);
				ResultSet inresult = instatement.executeQuery();
				while(inresult.next()) {
					Product p = productDao.findById(inresult.getLong("product"));
					w.getProducts().add(p);
				}
				
				
				
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			DAOUtility.close(connection);
		}	
		return w;
	}

	@Override
	public List<Wishlist> findByUser(User user) {
		Connection connection = this.dataSource.getConnection();
		List<Wishlist> wishlists = new ArrayList<Wishlist>();
		Wishlist w = null;
		try {
			PreparedStatement statement;
			String query = "select * from Wishlist where users = ?";
			statement = connection.prepareStatement(query);
			statement.setLong(1, user.getId());
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				w = new Wishlist();
				w.setId(result.getLong("id"));				
				w.setTitle(result.getString("title"));
				w.setType(result.getString("type"));
				
				DAOfactory factory = DAOfactory.getDAOFactory(DAOfactory.POSTGRESQL);
				UserDAO dao = factory.getUserDAO();
				
				w.setUser(dao.findById(result.getLong("users")));
				
				ProductDAO productDao = factory.getProductDAO();
				
				PreparedStatement instatement;
				String inquery = "select * from CONTIENE where wishlist = ?";
				instatement = connection.prepareStatement(inquery);
				instatement.setLong(1, w.getId());
				ResultSet inresult = instatement.executeQuery();
				while(inresult.next()) {
					Product p = productDao.findById(inresult.getLong("product"));
					w.getProducts().add(p);
				}
				
				
				wishlists.add(w);
				

			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			DAOUtility.close(connection);
		}	
		return wishlists;
	}

	@Override
	public boolean updateWishProduct(Wishlist wishlist, Product p) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "insert into CONTIENE (wishlist,product)  values(?,?)";
			PreparedStatement statement = connection.prepareStatement(update);

			statement.setLong(1, wishlist.getId());
			statement.setLong(2, p.getId());
			
			return (statement.executeUpdate() > 0) ? true : false;
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			DAOUtility.close(connection);
		}
		
	}
	
	@Override
	public boolean deleteProductInWishlist(Wishlist wishlist, Product p) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "delete from CONTIENE where product = ? and wishlist = ?)";
			PreparedStatement statement = connection.prepareStatement(update);

			statement.setLong(2, wishlist.getId());
			statement.setLong(1, p.getId());
			
			return (statement.executeUpdate() > 0) ? true : false;
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			DAOUtility.close(connection);
		}
		
	}

	@Override
	public Wishlist findDefaultWishlist(User user) {
		Connection connection = this.dataSource.getConnection();
		Wishlist w = null;
		try {
			PreparedStatement statement;
			String query = "select * from Wishlist where users = ? and type = default";
			statement = connection.prepareStatement(query);
			statement.setLong(1, user.getId());
			
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				w = new Wishlist();
				w.setId(result.getLong("id"));				
				w.setTitle(result.getString("title"));
				w.setType(result.getString("type"));
				
				DAOfactory factory = DAOfactory.getDAOFactory(DAOfactory.POSTGRESQL);
				UserDAO dao = factory.getUserDAO();
				
				w.setUser(dao.findById(result.getLong("users")));
				
				ProductDAO productDao = factory.getProductDAO();
				
				PreparedStatement instatement;
				String inquery = "select * from CONTIENE where wishlist = ?";
				instatement = connection.prepareStatement(inquery);
				instatement.setLong(1, w.getId());
				ResultSet inresult = instatement.executeQuery();
				while(inresult.next()) {
					Product p = productDao.findById(inresult.getLong("product"));
					w.getProducts().add(p);
				}
				

			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			DAOUtility.close(connection);
		}	
		return w;
	}


}
