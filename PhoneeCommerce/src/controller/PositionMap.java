package controller;

import com.google.gson.JsonObject;
import model.User;
import service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PositionMap extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PositionMap() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();

		String json = ""; 
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		if (br != null) {
			json = br.readLine();
		}
		String action = request.getParameter("action");

		ArrayList<JsonObject> result = new ArrayList<JsonObject>();
		
		User user = (User) session.getAttribute("user");

		switch (action) {
			case "CREATE": {
				break;
			}
			case "UPDATE": {
				break;
			}
			case "DELETE":
			{
				break;
			}

			case "RETRIEVEALL":
			{
				result = AccountService.getAllPositionMap();
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
