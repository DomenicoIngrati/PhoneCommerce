package persistence.dao;

import java.util.List;

import model.Product;
import model.ProductCategory;

public interface ProductDAO {
	
	public boolean create(Product modelObject);
	
	public boolean delete(Product t);
	
	public boolean update (Product t);

	public boolean updateImage (Product t);
	
	public Product findById(Long id);
	
	public Product findById(Long id, boolean visible);
	
	public Product findByName(String name);
	
	public List<Product> findByPrice(float price);
	
	public List<Product> findByCategory(ProductCategory category);
	
	public List<Product> findByCategory(ProductCategory category, boolean visible);
	
	public List<Product> findAll();
	
	public List<Product> findAll(boolean visible);
	
	public List<Product> findFromCategory(ProductCategory pc);
	
	public List<Product> findFromCategory(ProductCategory pc, boolean visible);
	
	public List<String> findAllNames();

	public void updatePriceProduct(Product product);
	
}
