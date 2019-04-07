package controller;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;

import model.Cart;
import service.CartService;

/**
 * Servlet implementation class CartServlet
 */
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader br = new BufferedReader(request.getReader());
		String json = "";
		if (br != null) {
			json = br.readLine();
		}
		
	    HttpSession session = request.getSession();
	    Cart cart = (Cart) session.getAttribute("cart");
	    if (cart == null) {
	      cart = new Cart();
	      session.setAttribute("cart", cart);
	    }
	    
	    
	    	    
	    String action = request.getParameter("action");
	    action = (action == null) ? "" : action;
		JsonObject result = new JsonObject();
	    
	    switch (action) {
	    case "addProductOnCart":
	    	result=CartService.addProductOnCart(json, cart);
	    	session.setAttribute("cart", cart);
	    	response.getWriter().write(result.toString());
	    	break;
	    	
	    case "deleteProduct":
	    	  result=CartService.deleteProductFromCart(json,cart);
	    	  session.setAttribute("cart", cart);
	    	  response.getWriter().write(result.toString());
	    	break;
	    
	    default:
	    	result.addProperty("result", "FAIL");
	    	response.getWriter().write(result.toString());
	    	break;
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
