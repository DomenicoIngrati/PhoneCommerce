package service;
import java.util.ArrayList;
import java.util.List;

import model.ProductCategory;
import persistence.dao.ProductCategoryDAO;
import persistence.util.DatabaseManager;

public class HomeService {

		private HomeService() {
		}

		public static List<ProductCategory> loadCategories() {
			
			ProductCategoryDAO cat = DatabaseManager.getInstance().getDaoFactory().getProductCategoryDAO();
			List<ProductCategory> cats = new ArrayList<ProductCategory>();
			cats = cat.findAll();
			return cats;
		}
}
