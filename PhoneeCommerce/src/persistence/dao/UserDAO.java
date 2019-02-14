package persistence.dao;

import model.User;

public interface UserDAO { //interfaccia pattern DAO con metodi CRUD
	
	 	public boolean create(User modelObject); //Create

	    public User findById(Integer id); //Retrice

	    public User findByUsername(String username);

	    public User findByEmail(String email);

	    public User findOrder(Integer id);

	    public User findReview(User user);
	    
//	    public Integer checkSellTicket(User user, Event event);

	    public boolean update(User modelObject); //Update

	    public void delete(User modelObject); //Delete

}
