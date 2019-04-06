package service;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import model.Type;
import model.User;
import model.Address;
import model.Cart;
import model.Order;
import persistence.dao.AddressDAO;
import persistence.dao.OrderDAO;
import persistence.dao.UserDAO;
import persistence.util.DAOfactory;
public class OrderService {


		public static JsonObject makeNewOrder(Cart cart,Address addressChosen) {
			
			
			Long time= new java.util.Date().getTime();
			DAOfactory factory = DAOfactory.getDAOFactory(DAOfactory.POSTGRESQL);
			OrderDAO dao = factory.getOrderDAO();
			Order newOrder=new Order();
			newOrder.setProducts(cart.getProducts());
			newOrder.setTotal(cart.getTotal());
			newOrder.setUser(cart.getUser());
			newOrder.setAddress(addressChosen);
			newOrder.setDate(new java.util.Date(time));
			JsonObject result = new JsonObject();

			if (dao.create(newOrder)) {
				result.addProperty("result", "SUCCESS");
				result.addProperty("message", "You have successfully signed-up, will be redirected soon !");
			} else {
				result.addProperty("result", "FAIL");
			}
			
			return result;
		}

}





