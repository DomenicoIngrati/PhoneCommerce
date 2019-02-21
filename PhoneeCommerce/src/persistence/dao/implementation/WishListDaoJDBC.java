package persistence.dao.implementation;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import model.Product;
import model.Wishlist;
import persistence.dao.WishlistDAO;
import persistence.util.DAOUtility;
import persistence.util.DataSource;
import persistence.util.PersistenceException;

public class WishListDaoJDBC implements WishlistDAO {

	private DataSource dataSource;
	
	public WishListDaoJDBC(DataSource dataSource) {
		this.dataSource=dataSource;
	}
	
	@Override
	public void create(Wishlist w) {
		Connection connection = null;
		String query = null;
		PreparedStatement statement = null;
		try {
			
		    connection = dataSource.getConnection();
		    query = "insert into Wishlist(title,user)  values(?,?)";
		    statement = connection.prepareStatement(query);
		    statement.setString(1, w.getTitle());
		    statement.setLong(2, w.getUser().getId());
		    statement.executeUpdate();

		} catch (

		SQLException e) {
		    e.printStackTrace();
		} finally {
		    DAOUtility.close(connection);
		    
		}
	}

	@Override
	public void delete(Wishlist w) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM WishList WHERE id = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setLong(1, w.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			DAOUtility.close(connection);
		}
	}

	@Override
	public void update(Wishlist w) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update WishList SET title = ?, user = ? WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, w.getTitle());
			statement.setLong(2, w.getUser().getId());
			statement.setLong(3, w.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			DAOUtility.close(connection);
		}

	}

	@Override
	public Wishlist findById(Integer id) {
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
				

			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			DAOUtility.close(connection);
		}	
		return w;
	}

	@Override
	public Set<Wishlist> findByUser(Integer id) {
		Connection connection = this.dataSource.getConnection();
		Set<Wishlist> wishlists = new HashSet<Wishlist>();
		Wishlist w = null;
		try {
			PreparedStatement statement;
			String query = "select * from Wishlist where user = ?";
			statement = connection.prepareStatement(query);
			statement.setLong(1, id);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				w = new Wishlist();
				w.setId(result.getLong("id"));				
				w.setTitle(result.getString("title"));
				
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
	public void updateWishProduct(Wishlist wishlist, Product p) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "insert into CONTIENE (wishlist,product)  values(?,?)";
			PreparedStatement statement = connection.prepareStatement(update);

			statement.setLong(1, wishlist.getId());
			statement.setLong(2, p.getId());
			
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			DAOUtility.close(connection);
		}
		
	}

}
