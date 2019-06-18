package persistence.dao;

import java.util.List;

import model.User;

public interface UserDAO {
	
	 	public boolean create(User user); 

	    public User findById(long id);

	    public User findByUsername(String username);

	    public User findByEmail(String email);
	    
	    public List<User> findAll();

	    public void update(User user);

	    public void delete(User user);

}
