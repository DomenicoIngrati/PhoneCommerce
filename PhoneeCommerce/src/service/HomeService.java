package service;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import model.Cart;
import model.ProductCategory;
import model.Type;
import model.User;
import persistence.dao.ProductCategoryDAO;
import persistence.dao.UserDAO;
import persistence.util.DAOfactory;
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
