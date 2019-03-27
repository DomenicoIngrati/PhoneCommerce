package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.JsonObject;

import model.Product;
import model.ProductCategory;
import persistence.dao.ProductCategoryDAO;
import persistence.dao.ProductDAO;
import persistence.dao.implementation.ProductCategoryDaoJDBC;
import persistence.util.DAOfactory;
import persistence.util.DatabaseManager;

public abstract class ProductService {
	
	public static JsonObject createProduct(String product)
	{
		JsonObject result = new JsonObject();
		DAOfactory factory = DAOfactory.getDAOFactory(DAOfactory.POSTGRESQL);
		ProductDAO dao = factory.getProductDAO();
		JSONObject jsonProduct;
		try {
			jsonProduct = new JSONObject(product);
			
			Product source = new Product();
			source.setName(jsonProduct.getString("name"));
			source.setCategory(new ProductCategory(jsonProduct.getString("category")));
			source.setDescription(jsonProduct.getString("description"));
			source.setPrice(Double.parseDouble(jsonProduct.getString("price")));
			source.setImage(jsonProduct.getString("image"));
			
			Product destination = source;
			if (dao.create(destination)) {
				
				result.addProperty("result", "SUCCESS");
				result.addProperty("message", "Product has been insert succefully!");
			} else {
				result.addProperty("result", "FAIL");
				destination = source;
				result.addProperty("reason", "Sorry, something went wrong!");
			}
			System.err.println(source);	
			
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public static Set<Product> findProductsByCategory(ProductCategory category) {
	    ProductDAO brandProductsDAO=  DatabaseManager.getInstance().getDaoFactory().getProductDAO();//ByDOMENICO
	    return brandProductsDAO.findByCategory(category);
	}
	
	public static Product findProductByName(String name) {
	    ProductDAO brandProductsDAO=  DatabaseManager.getInstance().getDaoFactory().getProductDAO();//ByDOMENICO
	    return brandProductsDAO.findByName(name);
	}
	
	public static List<Product> getLastSixProducts(){
		
		ProductDAO ProductDao = DatabaseManager.getInstance().getDaoFactory().getProductDAO();
		List<Product> products = new ArrayList<Product>();
		products = ProductDao.findAll();
		
		Collections.reverse(products);
		
		return products;
		
	}
	public static List<Product> getAllProducts(){
		ProductDAO ProductDao = DatabaseManager.getInstance().getDaoFactory().getProductDAO();
		List<Product> products = new ArrayList<Product>();
		products = ProductDao.findAll();
		
		return products;
	}

	public static JsonObject deleteProduct(String json) {
		
		ProductDAO ProductDao = DatabaseManager.getInstance().getDaoFactory().getProductDAO();
		Product p = ProductDao.findById(Long.parseLong(json));
		JsonObject result = new JsonObject();
		
		if(ProductDao.delete(p))
		{
			result.addProperty("result", "SUCCESS");
			result.addProperty("message", "Product has been insert succefully!");

			List <Product> totalProduct = ProductDao.findFormCategory(p.getCategory());
			if(totalProduct.isEmpty())
				result.addProperty("category", p.getCategory().getId());
		} 
		else {
			result.addProperty("result", "FAIL");
			result.addProperty("reason", "Sorry, something went wrong!");
		}
		
		return result;
	}

}
