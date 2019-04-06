package service;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import model.Type;
import model.User;
import model.Wishlist;
import model.Address;
import model.Product;
import persistence.dao.AddressDAO;
import persistence.dao.ProductDAO;
import persistence.dao.UserDAO;
import persistence.dao.WishlistDAO;
import persistence.util.DAOfactory;

public class AccountService {
	
	private AccountService() {
	}

	public static User signUp(String data, JsonObject result) {
		DAOfactory factory = DAOfactory.getDAOFactory(DAOfactory.POSTGRESQL);
		UserDAO dao = factory.getUserDAO();
		Gson gson = new Gson();
		User source = gson.fromJson(data, User.class);
		if (dao.create(source)) {
			result.addProperty("result", "SUCCESS");
			result.addProperty("message", "You have successfully signed-up, will be redirected soon !");
			System.err.println(source);
			return source;
		} else {
			result.addProperty("result", "FAIL");
			if (dao.findByUsername(source.getUsername()) == null) {
				result.addProperty("reason", "Username already exists, try agan with a new one!");
			} else if (dao.findByEmail(source.getEmail()) == null) {
				result.addProperty("reason", "Email already exists, try agan with a different one");
			} else {
				result.addProperty("reason", "Sorry, something went wrong, try again within few minutes");
			}
			System.err.println(source);
			return null;
		}
	}

	public static User signIn(String data, JsonObject result) {
		User user = null;
		DAOfactory factory = DAOfactory.getDAOFactory(DAOfactory.POSTGRESQL);
		UserDAO dao = factory.getUserDAO();
		Gson gson = new Gson();
		User tmp = gson.fromJson(data, User.class);
		System.out.println(tmp);
		
		if (!tmp.getEmail().equals("") || !tmp.getPassword().equals("")) {
			user = dao.findByEmail(tmp.getEmail());
			if (user != null && user.getPassword().equals(tmp.getPassword()) && user.getType().equals(Type.Customer)) {
				result.addProperty("result", "SUCCESS");
				result.addProperty("message", "You have successfully signed-in, will be redirected soon !");
				return user;
			} else if (user != null && user.getPassword().equals(tmp.getPassword())
					&& user.getType().equals(Type.Organizer)) {
				result.addProperty("result", "SUCCESS ORGANIZER");
				result.addProperty("message", "You have successfully signed-in, will be redirected soon !");
				return user;
			}

			result.addProperty("result", "FAIL");
			result.addProperty("reason", "Sorry, your email/password doesn't exists in our database !");
			return null;
		}else{
			result.addProperty("result", "FAIL");
			result.addProperty("reason", "Sorry,you have to fill all fields!");
			return null;
		}
	}

	public static JsonObject addAddress(User user, String json) {
		
		DAOfactory factory = DAOfactory.getDAOFactory(DAOfactory.POSTGRESQL);
		AddressDAO dao = factory.getAddressDAO();
		UserDAO daoUser = factory.getUserDAO();
		
		Gson gson = new Gson();
		Address tmp = gson.fromJson(json, Address.class);
		
		JsonObject result = new JsonObject();
	
		tmp.setUser(daoUser.findByEmail(user.getEmail()));
		if(dao.create(tmp)) {
			result.addProperty("result", "SUCCESS");
			result.addProperty("message", "Address added to your account!");
			System.err.println(tmp);
		} else {
			result.addProperty("result", "FAIL");
			result.addProperty("reason", "Sorry, something went wrong, try again within few minutes!");
		}	
		return result;
	}

	public static List<Address> getAllAddressesFromUser(User user) {
		List<Address> addresses = null;
		
		DAOfactory factory = DAOfactory.getDAOFactory(DAOfactory.POSTGRESQL);
		AddressDAO dao = factory.getAddressDAO();
		
		addresses = dao.findByUserId(user);
		return addresses;
		
	}

	public static JsonObject deleteAddress(User user, String id) {
		
		JsonObject result = new JsonObject();

		DAOfactory factory = DAOfactory.getDAOFactory(DAOfactory.POSTGRESQL);
		AddressDAO dao = factory.getAddressDAO();
		
		Address tmp = dao.findById(Long.parseLong(id));
		
		if(dao.delete(tmp)) {
			result.addProperty("result", "SUCCESS");
			result.addProperty("message", "Product has been deleted succefully!");
		} 
		else {
			result.addProperty("result", "FAIL");
			result.addProperty("reason", "Sorry, something went wrong!");
		}
		
		
		return result;
		
	}

	public static JsonObject updateAddress(User user, String json) {
		
		JsonObject result = new JsonObject();

		DAOfactory factory = DAOfactory.getDAOFactory(DAOfactory.POSTGRESQL);
		AddressDAO dao = factory.getAddressDAO();
		
		Gson gson = new Gson();
		Address tmp = gson.fromJson(json, Address.class);

		tmp.setUser(dao.findById(tmp.getId()).getUser());
		
		if(dao.update(tmp)) {
			result.addProperty("result", "SUCCESS");
			result.addProperty("message", "Product has been updated succefully!");
		}
		else {
			result.addProperty("result", "FAIL");
			result.addProperty("reason", "Sorry, something went wrong!");
		}
		
		return result;
	}

	public static List<Wishlist> getAllListFromUser(User user) {
		DAOfactory factory = DAOfactory.getDAOFactory(DAOfactory.POSTGRESQL);
		WishlistDAO dao = factory.getWishlistDAO();
		return dao.findByUser(user);
	}
	

	public static Wishlist getDefaultWishlist(User user) {
		DAOfactory factory = DAOfactory.getDAOFactory(DAOfactory.POSTGRESQL);
		WishlistDAO dao = factory.getWishlistDAO();
		return dao.findDefaultWishlist(user);
	}

	public static JsonObject addList(User user, String json) {
		JsonObject result = new JsonObject();

		DAOfactory factory = DAOfactory.getDAOFactory(DAOfactory.POSTGRESQL);
		WishlistDAO dao = factory.getWishlistDAO();
		
		Gson gson = new Gson();
		Wishlist tmp = gson.fromJson(json, Wishlist.class);
		
		tmp.setUser(user);
		if(dao.create(tmp)) {
			
			result.addProperty("result", "SUCCESS");
			result.addProperty("message", "Product has been created succefully!");
		}
		else {
			result.addProperty("result", "FAIL");
			result.addProperty("reason", "Sorry, something went wrong!");
		}
		
		return result;
		
		
	}

	public static JsonObject addProductOnList(User user, String json) {
		JsonObject result = new JsonObject();
		try {
			JSONObject jObject = new JSONObject(json);
			DAOfactory factory = DAOfactory.getDAOFactory(DAOfactory.POSTGRESQL);
			
			WishlistDAO dao = factory.getWishlistDAO();
			ProductDAO productDao = factory.getProductDAO();
			
			Product tmpProduct = productDao.findById(jObject.getLong("id-product"));
			Wishlist tmpWishlist = dao.findById(jObject.getLong("id-wishlist"));
			if(dao.updateWishProduct(tmpWishlist, tmpProduct)) {
				
				result.addProperty("result", "SUCCESS");
				result.addProperty("message", "Product has been added succefully to your wishlist!");
			}
			else {
				result.addProperty("result", "FAIL");
				result.addProperty("reason", "Sorry, something went wrong!");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		
		return result;
	}

	public static JsonObject deleteList(User user, String json) {
		JsonObject result = new JsonObject();
		try {
			JSONObject jObject = new JSONObject(json);
			DAOfactory factory = DAOfactory.getDAOFactory(DAOfactory.POSTGRESQL);
			
			WishlistDAO dao = factory.getWishlistDAO();
			ProductDAO productDao = factory.getProductDAO();
			
			Wishlist tmpWishlist = dao.findById(jObject.getLong("id-wishlist"));
			Product tmpProduct = productDao.findById(jObject.getLong("id-product"));
			if(tmpWishlist.getType() != "default")
			{
				if(dao.deleteProductInWishlist(tmpWishlist, tmpProduct)) {
					
					result.addProperty("result", "SUCCESS");
					result.addProperty("message", "Product has been added succefully to your wishlist!");
				}
				else {
					result.addProperty("result", "FAIL");
					result.addProperty("reason", "Sorry, something went wrong!");
				}
			}
			else {
				result.addProperty("result", "FAIL");
				result.addProperty("reason", "Sorry, something went wrong!");
			}
			
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static JsonObject deleteProductFromList(User user, String json) {
		JsonObject result = new JsonObject();
		try {
			JSONObject jObject = new JSONObject(json);
			DAOfactory factory = DAOfactory.getDAOFactory(DAOfactory.POSTGRESQL);
			
			WishlistDAO dao = factory.getWishlistDAO();
			ProductDAO productDao = factory.getProductDAO();
			
			Product tmpProduct = productDao.findById(jObject.getLong("id-product"));
			Wishlist tmpWishlist = dao.findById(jObject.getLong("id-wishlist"));
			if(dao.deleteProductInWishlist(tmpWishlist, tmpProduct)) {
				
				result.addProperty("result", "SUCCESS");
				result.addProperty("message", "Product has been deleted succefully from your wishlist!");
			}
			else {
				result.addProperty("result", "FAIL");
				result.addProperty("reason", "Sorry, something went wrong!");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		
		return result;
	}
}
