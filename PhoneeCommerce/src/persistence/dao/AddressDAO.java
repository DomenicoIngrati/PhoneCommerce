package persistence.dao;

import java.util.List;
import model.Address;
import model.User;

public interface AddressDAO { //interfaccia pattern DAO con metodi CRUD
	
	 	public boolean create(Address a); //Create
	 	
	    public boolean update(Address a); //Update

	    public boolean delete(Address a); //Delete

	    public Address findById(int id); //Retrive

	    public List<Address> findByUserId(User u);
}
