package service;
import java.util.List;

import com.google.gson.JsonObject;
import model.User;
import model.Address;
import model.Cart;
import model.Order;
import persistence.dao.OrderDAO;
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

		public static List<Order> findAllMyOrders(User user) {
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





