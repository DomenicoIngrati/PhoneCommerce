package persistence.dao;

import java.util.List;

import model.User;

public interface UserDAO { //interfaccia pattern DAO con metodi CRUD
	
	 	public boolean create(User user); //Create

	    public User findById(long id); //Retrieve

	    public User findByUsername(String username);

	    public User findByEmail(String email);
	    
	    public List<User> findAll();

	    public void update(User user); //Update

	    public void delete(User user); //Delete

}
