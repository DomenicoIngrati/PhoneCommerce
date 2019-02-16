package persistencejdbc;

import org.apache.tomcat.jdbc.pool.DataSource;

import model.Product;
import model.Wishlist;
import persistence.dao.WhishlistDAO;

public class WishListDaoJDBC implements WhishlistDAO {

	private DataSource dataSource;
	
	public WishListDaoJDBC(DataSource dataSource) {
		this.dataSource=dataSource;
	}
	
	@Override
	public int create(Wishlist modelObject) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Wishlist w) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM WishList WHERE id = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, w.getId());
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
	public void update(Wishlist w) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update WishList SET title = ?, user_id = ?, products = ? WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, w.getTitle());
			statement.setString(2, w.getUser().getId());
			statement.setDate(3, w.getProducts());
			statement.setString(4, w.getId());
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
	public boolean updateWishProduct(Wishlist wishlist, Product ticket) {
		// TODO Auto-generated method stub
		return false;
	}

}
