package persistence.dao;

import java.util.Set;

import model.ProductCategory;

public interface ProductCategoryDAO {
	
	public void create(ProductCategory modelObject);
	
	public void delete(ProductCategory tc);
	
	public void update(ProductCategory tc);
	
//	public Set<ProductCategory> findById(Long id);
	
	public Set<ProductCategory> findByName(String name);
	
//	public Set<ProductCategory> showCategory();

}
