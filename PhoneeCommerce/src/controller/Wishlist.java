package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;

import model.Cart;
import model.User;
import service.AccountService;

public class Wishlist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Wishlist() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String json = ""; // parse request in json format
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		if (br != null) {
			json = br.readLine();
		}
		
		HttpSession session = request.getSession();
	    Cart cart = (Cart) session.getAttribute("cart");
	    if (cart == null) {
	      cart = new Cart();
	      session.setAttribute("cart", cart);
	    }
	    
	    User user = (User) session.getAttribute("user");
		
		String action = request.getParameter("action");
		JsonObject result = new JsonObject();
		
		switch (action) {
		case "createList": {
			
			result = AccountService.addList(user,json);
			
			break;
		}
		case "AddProductOnList": {
			result = AccountService.addProductOnList(user, json);
			break;
		}
		case "deleteList":
		{
			result=AccountService.deleteList(user, json);
			break;
		}
		case "deleteProductFromList":
		{
			result=AccountService.deleteProductFromList(user, json);
			break;
		}
		
		case "addAllToCart":
		{
			result=AccountService.addAllToCart(AccountService.getDefaultWishlist(user), cart);
			session.setAttribute("cart", cart);
			System.out.println(cart);
			break;
		}
			

		default:
			break;
		}

		response.getWriter().write(result.toString());
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
