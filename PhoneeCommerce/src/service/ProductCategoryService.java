package service;

import java.util.List;

import model.ProductCategory;
import persistence.dao.ProductCategoryDAO;
import persistence.util.DatabaseManager;

public abstract class ProductCategoryService {
	
	
	
	public static List<ProductCategory> findAllCategory() {
	    ProductCategoryDAO cat = DatabaseManager.getInstance().getDaoFactory().getProductCategoryDAO();
	    return cat.findAll();
	}
	
	public static ProductCategory findCategoryByName(String categoryName) {
		ProductCategoryDAO cat = DatabaseManager.getInstance().getDaoFactory().getProductCategoryDAO();
		return cat.findByName(categoryName);
	}

}
