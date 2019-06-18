package persistence.dao;


import model.PositionMap;

import java.util.List;

public interface PositionMapDAO { 
	
	 	public boolean create(PositionMap a); 
	 	
	    public boolean update(PositionMap a); 

	    public boolean delete(PositionMap a); 

	    public PositionMap findById(Long id); 

	    public List<PositionMap> findAll();
}
