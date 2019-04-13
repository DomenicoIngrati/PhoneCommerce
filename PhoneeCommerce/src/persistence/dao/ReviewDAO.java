package persistence.dao;

import java.util.List;

import model.Product;
import model.Review;
import model.User;

public interface ReviewDAO {
	
	public boolean create(Review modelObject);
	
	public void delete(Review r);
	
	public void update(Review r);
	
	public Review findById(Long id);
	
	public List<Review> findByUser(User name);
	
	public List<Review>findByTitle(String name);
	
	public List<Review> findByFeedback(int feedback);

	List<Review> findByProduct(Product p);

//	public int getAVGbyId(Integer id);

}
