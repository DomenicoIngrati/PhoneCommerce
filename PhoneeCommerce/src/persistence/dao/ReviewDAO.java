package persistence.dao;

import java.util.Set;

import model.Review;
import model.User;

public interface ReviewDAO {
	
	public void create(Review modelObject);
	
	public void delete(Review r);
	
	public void update(Review r);
	
	public Review findById(Long id);
	
	public Set<Review> findByUser(User name);
	
	public Set<Review>findByTitle(String name);
	
	public Set<Review> findByFeedback(int feedback);

//	public int getAVGbyId(Integer id);

}
