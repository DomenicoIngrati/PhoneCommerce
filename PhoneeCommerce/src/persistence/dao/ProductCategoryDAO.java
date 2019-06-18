package persistence.dao;

import java.util.List;

import model.ProductCategory;

public interface ProductCategoryDAO {
	
	public void create(ProductCategory modelObject);
	
	public void deleteById(ProductCategory tc);
	
	public void deleteByName(ProductCategory tc);
	
	public void updateName(ProductCategory tc);
	
	public void updateVisible(ProductCategory tc);
	
	public void updateSubcategory(ProductCategory tc);
	
	public List<ProductCategory> findAll();
	
	public List<ProductCategory> findAll(boolean visible);
	
	public ProductCategory findById(Long id);
	
	public ProductCategory findByName(String name);
	
	public List<String> findAllNames();
	
	public List<String> findAllNames(boolean visible);


}
