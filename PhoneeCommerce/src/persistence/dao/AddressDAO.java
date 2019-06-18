package persistence.dao;

import java.util.List;
import model.Address;
import model.User;

public interface AddressDAO { 
	
	 	public boolean create(Address a); 
	    public boolean update(Address a); 
	    public boolean delete(Address a); 
	    public Address findById(Long id); 
	    public List<Address> findByUserId(User u);
}
