package persistence.dao;

import java.util.List;
import java.util.Set;

import model.Product;
import model.ProductCategory;

public interface ProductDAO {
	
	public boolean create(Product modelObject);
	
	public boolean delete(Product t);
	
	public boolean update (Product t);
	
	public Product findById(Long id);
	
	public Product findByName(String name);
	
	public Set<Product> findByPrice(float price);
	
	public Set<Product> findByCategory(ProductCategory idCategory);
	
	public List<Product> findAll();
	
	public List<Product> findFormCategory(ProductCategory pc);
	
	public Set<String> findAllNames();

//	public Integer soldProduct(Event e, ProductCategory idCategory);

	public void updatePriceProduct(Product product);
	
}
