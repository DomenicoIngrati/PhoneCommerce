package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;

import model.Cart;
import model.Item;
import model.Order;
import model.User;
import service.AccountService;
import service.OrderService;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet({ "/OrderServlet", "/order" })
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	
	
    public OrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		String json = ""; // parse request in json format
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		if (br != null) {
			json = br.readLine();
		}
		String action = request.getParameter("action");	
		JsonObject result = new JsonObject();
		model.Address addressChosen= (model.Address) session.getAttribute("addressChosen");
		Cart cart=(Cart) session.getAttribute("cart");
		User user=(User) session.getAttribute("user");
		Order orderCompleted=null;
		Order orderSelected=null;
		
		switch (action) {
		case "newOrder": 
		{
			
			 orderCompleted = OrderService.makeNewOrder(cart,addressChosen,user,result);
			 session.setAttribute("orderCompleted", orderCompleted);
			 
			break;
		}
		
		case "myOrdersView":
		{
			
			Set<Order> myOrders = OrderService.findAllMyOrders(user,result);
			session.setAttribute("myOrders",myOrders);
			break;
		}
		
		case "summaryOrder":
		{
			orderSelected=OrderService.findOrderByID(json,result);
			session.setAttribute("orderSelected", orderSelected);
			
			break;
		}
		case "UPDATE": 
		{

			break;
		}
		case "DELETE":
		{
			break;
		}

		default:
			break;
		}

		response.getWriter().write(result.toString());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
