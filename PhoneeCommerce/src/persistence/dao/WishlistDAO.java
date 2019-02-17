package persistence.dao;

import java.util.Set;

import model.Product;
import model.Wishlist;

public interface WishlistDAO {
	
	public int create(Wishlist modelObject);

    public int delete(Wishlist wishlist);

    public void update(Wishlist wishlist);

    public Wishlist findById(Integer id);

    public Set<Wishlist> findByUser(Integer id, Integer offset, Integer limit);

    public boolean updateWishProduct(Wishlist wishlist, Product ticket);

}
