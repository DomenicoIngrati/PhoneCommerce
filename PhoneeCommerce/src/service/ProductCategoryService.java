package service;

import java.util.List;

import model.ProductCategory;
import persistence.dao.ProductCategoryDAO;
import persistence.util.DatabaseManager;

public class ProductCategoryService {
	
	
	
	public List<ProductCategory> findAllCategory() {
	    ProductCategoryDAO cat = DatabaseManager.getInstance().getDaoFactory().getProductCategoryDAO();
	    return cat.findAll();
	}
	
	public ProductCategory findCategoryByName(String categoryName) {
		ProductCategoryDAO cat = DatabaseManager.getInstance().getDaoFactory().getProductCategoryDAO();
		return cat.findByName(categoryName);
	}

}
