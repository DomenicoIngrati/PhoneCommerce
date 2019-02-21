package persistence.dao;

import model.User;

public interface UserDAO { //interfaccia pattern DAO con metodi CRUD
	
	 	public void create(User user); //Create

	    public User findById(int id); //Retrice

	    public User findByUsername(String username);

	    public User findByEmail(String email);

	    public void update(User user); //Update

	    public void delete(User user); //Delete

}
