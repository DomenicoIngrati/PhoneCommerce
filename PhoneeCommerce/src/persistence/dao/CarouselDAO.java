package persistence.dao;

import model.Carousel;

import java.util.List;

public interface CarouselDAO { //interfaccia pattern DAO con metodi CRUD
	
	 	public boolean create(Carousel carousel); //Create

	    public Carousel findById(long id); //Retrieve
	    
	    public List<Carousel> findAll();

	    public boolean updateTexts(Carousel carousel); //Update

		public boolean updateImage(Carousel carousel);

	    public boolean delete(long id); //Delete

}
