package persistence.dao;

import model.Carousel;

import java.util.List;

public interface CarouselDAO { 
	
	 	public boolean create(Carousel carousel);
	    public Carousel findById(long id);
	    public List<Carousel> findAll();
	    public boolean updateTexts(Carousel carousel);
		public boolean updateImage(Carousel carousel);
	    public boolean delete(long id); 

}
