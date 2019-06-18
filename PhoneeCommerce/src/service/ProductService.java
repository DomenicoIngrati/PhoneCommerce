package service;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import model.Address;
import model.Order;
import model.Product;
import model.ProductCategory;
import model.Review;
import model.ReviewStatistic;
import model.User;
import persistence.dao.AddressDAO;
import persistence.dao.OrderDAO;
import persistence.dao.ProductCategoryDAO;
import persistence.dao.ProductDAO;
import persistence.dao.ReviewDAO;
import persistence.dao.UserDAO;
import persistence.util.DAOfactory;
import persistence.util.DatabaseManager;

public abstract class ProductService {
	
	public static JsonObject createProduct(Product p)
	{
		JsonObject result = new JsonObject();
		ProductDAO dao = DatabaseManager.getInstance().getDaoFactory().getProductDAO();

		if (dao.create(p)) {
			
			result.addProperty("result", "SUCCESS");
			result.addProperty("message", "Product has been insert succefully!");
		} else {
			result.addProperty("result", "FAIL");
			result.addProperty("reason", "Sorry, something went wrong!");
		}


		return result;
	}
	
	public static List<Product> findProductsByCategory(ProductCategory category) {
	    ProductDAO brandProductsDAO=  DatabaseManager.getInstance().getDaoFactory().getProductDAO();//ByDOMENICO
	    return brandProductsDAO.findByCategory(category, true);
	}
	
	public static List<Review> findReviewsByProduct(Product p) {
	    ReviewDAO reviewDao=  DatabaseManager.getInstance().getDaoFactory().getReviewDAO();//ByDOMENICO
	    return reviewDao.findByProduct(p);
	}
	
	public static Product findProductByName(String name) {
	    ProductDAO brandProductsDAO=  DatabaseManager.getInstance().getDaoFactory().getProductDAO();//ByDOMENICO
	    return brandProductsDAO.findByName(name);
	}

    public static Product findProductById(Long id) {
        ProductDAO brandProductsDAO=  DatabaseManager.getInstance().getDaoFactory().getProductDAO();//ByDOMENICO
        return brandProductsDAO.findById(id);
    }
	
	public static List<Product> getLastSixProducts(){
		
		ProductDAO ProductDao = DatabaseManager.getInstance().getDaoFactory().getProductDAO();
		List<Product> products = new ArrayList<Product>();
		products = ProductDao.findAll(true);
		
		Collections.reverse(products);
		
		return products;
		
	}
	public static List<Product> getAllProducts(){
		ProductDAO ProductDao = DatabaseManager.getInstance().getDaoFactory().getProductDAO();
		List<Product> products = new ArrayList<Product>();
		products = ProductDao.findAll(true);
		
		return products;
	}

	public static JsonObject deleteProduct(String json) {
		
		ProductDAO ProductDao = DatabaseManager.getInstance().getDaoFactory().getProductDAO();
		Product p = ProductDao.findById(Long.parseLong(json));
		p.setVisible(false);
		JsonObject result = new JsonObject();
		
		if(ProductDao.update(p))
		{
			result.addProperty("result", "SUCCESS");
			result.addProperty("message", "Product has been deleted (hidden) succefully!");

			List <Product> totalProduct = ProductDao.findFromCategory(p.getCategory(), true);
			if(totalProduct.isEmpty())
				result.addProperty("category", p.getCategory().getId());
		} 
		else {
			result.addProperty("result", "FAIL");
			result.addProperty("reason", "Sorry, something went wrong!");
		}
		
		return result;
	}

	public static JsonObject updateProduct(String json) {
		JsonObject result = new JsonObject();
		JSONObject jsonProduct;
		ProductDAO dao = DatabaseManager.getInstance().getDaoFactory().getProductDAO();
		ProductCategoryDAO catDao = DatabaseManager.getInstance().getDaoFactory().getProductCategoryDAO();
		try {
			jsonProduct = new JSONObject(json);
			
			Product p = new Product();
			p.setName(jsonProduct.getString("name"));
			
			ProductCategory cat = catDao.findByName(jsonProduct.getString("category"));
			if(cat == null)
			{
				catDao.create(new ProductCategory(jsonProduct.getString("category")));
				cat = catDao.findByName(jsonProduct.getString("category"));
			}
				
			p.setCategory(cat);
			
			p.setDescription(jsonProduct.getString("description"));
			p.setPrice(Double.parseDouble(jsonProduct.getString("price")));
//			p.setImage(jsonProduct.getString("image"));
			p.setId(Long.parseLong(jsonProduct.getString("id")));
			p.setVisible(true);
			//nel DB non c'e' l'attributo image

			if (dao.update(p)) {
				
				if(cat.getVisible() == false) {
					cat.setVisible(true);
					catDao.updateVisible(cat);
					
				}
				
				ProductCategory old = catDao.findByName(jsonProduct.getString("oldnamecategory"));
				List <Product> totalProduct = dao.findFromCategory(old,true);
				if(totalProduct.isEmpty())
				{
					old.setVisible(false);
					catDao.updateVisible(old);
					result.addProperty("oldnamecategory", old.getId());
				}
				result.addProperty("result", "SUCCESS");
				result.addProperty("message", "Product has been updated succefully!");
			} else {
				result.addProperty("result", "FAIL");

				result.addProperty("reason", "Sorry, something went wrong!");
			}
			
			
		} catch (JSONException e) {
			e.printStackTrace();
		} 
		
		
		
		return result;
	}

	public static JsonObject updateProductImage(byte[] imgbyte, Long idProduct) {
		JsonObject result = new JsonObject();
		ProductDAO dao = DatabaseManager.getInstance().getDaoFactory().getProductDAO();

		Product p = dao.findById(idProduct);

		p.setImage(imgbyte);

		if (dao.updateImage(p)) {
			result.addProperty("result", "SUCCESS");
			result.addProperty("message", "Product has been updated succefully!");
		} else {
			result.addProperty("result", "FAIL");
			result.addProperty("reason", "Sorry, something went wrong!");
		}

		return result;
	}

	public static boolean checkIfUserBoughtProduct(Product selectedProduct,User user) {
		
		OrderDAO orderDao = DatabaseManager.getInstance().getDaoFactory().getOrderDAO();
		List<Order> userOrders= orderDao.findByUser(user.getId());
		
		for (int i = 0; i < userOrders.size(); i++) {
			Order o=orderDao.findById(userOrders.get(i).getId());
			
			if(o.checkIfOrderContainsProduct(selectedProduct)) {
				return true;
			}
		}
		return false;
	}
	
	public static JsonObject addNewReview(String json,User user) {
		
		DAOfactory factory = DAOfactory.getDAOFactory(DAOfactory.POSTGRESQL);
		ReviewDAO dao = factory.getReviewDAO();
		ProductDAO productDao=factory.getProductDAO();
		JsonObject result = new JsonObject();
		
		try {
		JSONObject jsonProduct=new JSONObject(json);
		
		Review tmp=new Review();
		
		tmp.setTitle(jsonProduct.getString("title"));
		tmp.setText(jsonProduct.getString("text"));
		tmp.setUser(user);
		tmp.setProduct(productDao.findById(Long.parseLong(jsonProduct.getString("id"))));
		tmp.setFeedback(jsonProduct.getInt("feedback"));
		

		if(dao.create(tmp)) {
			result.addProperty("result", "SUCCESS");
			result.addProperty("message", "Recensione aggiunta con successo!");
		} else {
			result.addProperty("result", "FAIL");
			result.addProperty("reason", "Ops, qualcosa � andato storto!");
		}	
			
		} catch (JSONException e) {
			e.printStackTrace();
		} 
		
		return result;
	}
	
	public static float countFeedbackAverage(List<Review> reviews) {
		
		float total=0;
		if(!reviews.isEmpty())
        {
            for(int i=0;i<reviews.size();i++) {
                total+=reviews.get(i).getFeedback();
            }
            total=(total/reviews.size());
        }

        NumberFormat formatter = NumberFormat.getInstance(Locale.US);
        formatter.setMaximumFractionDigits(1);
        formatter.setMinimumFractionDigits(1);
        formatter.setRoundingMode(RoundingMode.HALF_UP);

		Float formatedFloat = new Float(formatter.format(total));

        return formatedFloat;
	}

	public static List<ReviewStatistic> findReviewsStatistic(List<Review> productReviews){
		
		int cont=0;
		float p=0;
		int feedback=0;
		List<ReviewStatistic> reviewsStatistic=new ArrayList<ReviewStatistic>();
		
		for(int i=5;i>0;i--) {
			
			cont=0;
			p=0;
			feedback=i;
			
			for(int j=0;j<productReviews.size();j++) {
				
				if(productReviews.get(j).getFeedback()==i) {
					cont++;
				}
			}
			
			if(productReviews.size()!=0) {
			
				 p=(cont*100)/productReviews.size();
			}

			
			reviewsStatistic.add(new ReviewStatistic(feedback,p,cont));
			
		}
		
		return reviewsStatistic;
	}
}
