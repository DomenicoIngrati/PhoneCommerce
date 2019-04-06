package persistence.dao;

import java.util.List;

import model.Product;
import model.User;
import model.Wishlist;

public interface WishlistDAO {
	
	public boolean create(Wishlist modelObject);

    public boolean delete(Wishlist wishlist);

    public boolean update(Wishlist wishlist);

    public Wishlist findById(Long id);

    public List<Wishlist> findByUser(User user);
    
    public Wishlist findDefaultWishlist(User user);

    public boolean updateWishProduct(Wishlist wishlist, Product p);
    
    public boolean deleteProductInWishlist(Wishlist wishlist, Product p);

}
