package controller;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;

import model.User;
import service.AccountService;

/**
 * Servlet implementation class Account
 */
public class Account extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Account() {
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
		
		String action = request.getParameter("action");
		HttpSession session=request.getSession();
		String comingFromCart= (String) session.getAttribute("comingFromCart");
		User user=null;
		
		JsonObject result = new JsonObject();
		switch (action) {
		
		case "signup":
			user=AccountService.signUp(json, result);
			session.setAttribute("user", user);
			
			if(comingFromCart=="Yes" && user!=null) {
				result.addProperty("comingFromCart", true);
			}
			else {
				result.addProperty("comingFromCart", false);	
			}
			response.getWriter().write(result.toString());
			break;
		case "signin":
			user = AccountService.signIn(json, result);
			if (user != null) {	
				session.setAttribute("user", user);
			}
			if(comingFromCart=="Yes") {
				result.addProperty("comingFromCart", true);
			}
			else {
				result.addProperty("comingFromCart", false);	
			}
			response.getWriter().write(result.toString());
			break;
		case "logout":
			request.getSession().invalidate();
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home");
			dispatcher.forward(request, response);
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
