package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cart;
import model.ProductCategory;
import persistence.dao.ProductCategoryDAO;
import persistence.util.DatabaseManager;

/**
 * Servlet implementation class Home
 */
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//CARICO LE CATEGORIE NELLA NAVBAR HOME
		ProductCategoryDAO cat = DatabaseManager.getInstance().getDaoFactory().getProductCategoryDAO();

		List<ProductCategory> cats = new ArrayList<ProductCategory>();
		cats = cat.findAll();
		
		request.setAttribute("brands", cats);
		
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
		
		String action = request.getParameter("action");

		action = (action == null) ? "" : action;
		String page = "";
		
		switch (action) {
		case "index":
			
			// fare le cose relative alla home, come caricare gli ultimi 10 prodotti inseriti nel sito
			page="index";
			break;
			
		case "signin":
			page="login";
			break;
		
		case "registration":
			page="signup";
			break;
		
		case "faq":
			page="faq";
			break;

		default:
			page="index";
		}
		
		request.setAttribute("page", "content/" + page + ".jsp");

		// this servlet has to forward only to the home.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/home.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
