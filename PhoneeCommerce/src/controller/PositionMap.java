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

public class PositionMap extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PositionMap() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();

		String json = ""; // parse request in json format
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		if (br != null) {
			json = br.readLine();
		}
		String action = request.getParameter("action");

		JsonObject result = new JsonObject();
		
		User user = (User) session.getAttribute("user");

		switch (action) {
			case "CREATE": {
//				result = AccountService.addAddress(user, json);
				break;
			}
			case "UPDATE": {
//				result = AccountService.updateAddress(user, json);
				break;
			}
			case "DELETE":
			{
//				result=AccountService.deleteAddress(user, json);
				break;
			}

			case "RETRIVEALL":
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
