package service;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
import persistence.util.DatabaseManager;
public class OrderService {


		public static Order makeNewOrder(Cart cart,Address addressChosen,User u,JsonObject result) {
			
			
			Long time= new java.util.Date().getTime();
			DAOfactory factory = DAOfactory.getDAOFactory(DAOfactory.POSTGRESQL);
			OrderDAO dao = factory.getOrderDAO();
			Order newOrder=new Order();
			newOrder.setProducts(cart.getProducts());
			newOrder.setTotal(cart.getTotal());
			newOrder.setUser(u);
			newOrder.setAddress(addressChosen);
			newOrder.setDate(new java.util.Date(time));

			if (dao.create(newOrder)) {
				result.addProperty("result", "SUCCESS");
			} else {
				result.addProperty("result", "FAIL");
			}
			
			return newOrder;
		}

		public static Set<Order> findAllMyOrders(User user) {
			OrderDAO orderDao = DatabaseManager.getInstance().getDaoFactory().getOrderDAO();
			return orderDao.findByUser(user.getId());
		}


		
		public static Order findOrderByID(String json,JsonObject result) {
			Order order = null;
			
			DAOfactory factory = DAOfactory.getDAOFactory(DAOfactory.POSTGRESQL);
			OrderDAO dao = factory.getOrderDAO();
			
			
			order = dao.findById(Long.parseLong(json));
			
			if (order!=null) {
				result.addProperty("result", "SUCCESS");
			} else {
				result.addProperty("result", "FAIL");
			}
			
			return order;	
		}

}





