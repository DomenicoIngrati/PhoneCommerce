package persistence.dao;

import java.util.Set;

import model.Product;
import model.ProductCategory;

public interface ProductDAO {
	
public void create(Product modelObject);
	
	public void delete(Product t);
	
	public void update (Product t);
	
	public Product findById(Integer id);
	
	public Set<Product> findByPrice(float price);
	
	public Set<Product> findByCategory(ProductCategory idCategory,Integer maxRow);

//	public Integer soldProduct(Event e, ProductCategory idCategory);

	public void updatePriceProduct(Product product);
	
}
