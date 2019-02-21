package persistence.dao;

import java.util.Set;

import model.Product;
import model.Wishlist;

public interface WishlistDAO {
	
	public void create(Wishlist modelObject);

    public void delete(Wishlist wishlist);

    public void update(Wishlist wishlist);

    public Wishlist findById(Integer id);

    public Set<Wishlist> findByUser(Integer id);

    public void updateWishProduct(Wishlist wishlist, Product ticket);

}
