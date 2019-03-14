package service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import model.Type;
import model.User;
import persistence.dao.UserDAO;
import persistence.util.DAOfactory;

public class AccountService {
	
	private AccountService() {
	}

	public static void signUp(String data, JsonObject result) {
		DAOfactory factory = DAOfactory.getDAOFactory(DAOfactory.POSTGRESQL);
		UserDAO dao = factory.getUserDAO();
		Gson gson = new Gson();
		User source = gson.fromJson(data, User.class);
		User destination = source;
		if (dao.create(destination)) {
			result.addProperty("result", "SUCCESS");
			result.addProperty("message", "You have successfully signed-up, will be redirected soon !");
		} else {
			result.addProperty("result", "FAIL");
			destination = source;
			if (dao.findByUsername(destination.getUsername()) == null) {
				result.addProperty("reason", "Username already exists, try agan with a new one!");
			} else if (dao.findByEmail(destination.getEmail()) == null) {
				result.addProperty("reason", "Email already exists, try agan with a different one");
			} else {
				result.addProperty("reason", "Sorry, something went wrong, try again within few minutes");
			}
		}
		System.err.println(source);
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

}
