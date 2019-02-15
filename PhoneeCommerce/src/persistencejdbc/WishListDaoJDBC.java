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
	public int delete(Wishlist wishlist) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(Wishlist wishlist) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean updateWishProduct(Wishlist wishlist, Product ticket) {
		// TODO Auto-generated method stub
		return false;
	}

}
