package persistence.dao;

import java.util.List;

import model.Address;

public interface AddressDAO { //interfaccia pattern DAO con metodi CRUD
	
	 	public boolean create(Address user); //Create
	 	
	    public boolean update(Address user); //Update

	    public boolean delete(Address user); //Delete

	    public Address findById(int id); //Retrive

	    public List<Address> findByUser(String username);
}
