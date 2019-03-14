package controller;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;

import model.Type;
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
		
		// read the ajax request
		System.out.println("GATTAAAA");
		BufferedReader br = new BufferedReader(request.getReader());
		String json = "";
		if (br != null) {
			json = br.readLine();
		}
		// get the action
		String action = request.getParameter("action");
		JsonObject result = new JsonObject();
		switch (action) {
		
		case "signup":
			AccountService.signUp(json, result);
			break;
		case "signin":
			User user = AccountService.signIn(json, result);
			if (user != null) {
				HttpSession session = request.getSession();				

				session.setAttribute("user", user);

			}
			break;
		case "logout":
			request.getSession().invalidate();
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/home.jsp");
			dispatcher.forward(request, response);
			break;
		default:
			result.addProperty("result", "FAIL");
			break;
		}
		System.out.println(result.toString());
		
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
