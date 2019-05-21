package persistence.dao;

import model.Address;
import model.PositionMap;
import model.User;

import java.util.List;

public interface PositionMapDAO { //interfaccia pattern DAO con metodi CRUD
	
	 	public boolean create(PositionMap a); //Create
	 	
	    public boolean update(PositionMap a); //Update

	    public boolean delete(PositionMap a); //Delete

	    public PositionMap findById(Long id); //Retrive

	    public List<PositionMap> findAll();
}
