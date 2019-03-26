package persistence.dao;

import java.util.List;
import java.util.Set;

import model.ProductCategory;

public interface ProductCategoryDAO {
	
	public void create(ProductCategory modelObject);
	
	public void deleteById(ProductCategory tc);
	
	public void deleteByName(ProductCategory tc);
	
	public void updateName(ProductCategory tc);
	
	public void updateSubcategory(ProductCategory tc);
	
	public List<ProductCategory> findAll();
	
	public ProductCategory findById(Long id);
	
	public ProductCategory findByName(String name);
	
//	public Set<ProductCategory> showCategory();

}
