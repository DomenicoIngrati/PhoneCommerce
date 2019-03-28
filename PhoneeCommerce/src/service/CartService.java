package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.JsonObject;

import model.Cart;
import model.Item;
import model.Product;
import model.ProductCategory;
import persistence.dao.ProductCategoryDAO;
import persistence.dao.ProductDAO;
import persistence.util.DatabaseManager;

public abstract class CartService {

	public static JsonObject addProductOnCart(String json,model.Cart cart) {
		JsonObject result = new JsonObject();
		JSONObject jsonProduct;
		ProductDAO dao = DatabaseManager.getInstance().getDaoFactory().getProductDAO();
		try {
			jsonProduct = new JSONObject(json);
			Product p = new Product();
			p=dao.findByName(jsonProduct.getString("name"));
			int quantity=jsonProduct.getInt("quantity");

			cart.addProducts(p, quantity);
			result.addProperty("result", "SUCCESS");
			result.addProperty("message", "Product has been added into the cart successfully!");
		} catch (JSONException e) {
			e.printStackTrace();
		} 
		return result;
	}
}
