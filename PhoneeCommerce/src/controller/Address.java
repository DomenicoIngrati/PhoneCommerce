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

import model.User;
import service.AccountService;

public class Address extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Address() {
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
		model.Address addressChosen=null;
		JsonObject result = new JsonObject();
		
		User user = (User) session.getAttribute("user");

		switch (action) {
		case "CREATE": {
			result = AccountService.addAddress(user, json);
			break;
		}
		case "UPDATE": {
			result = AccountService.updateAddress(user, json);
			break;
		}
		case "DELETE":
		{
			result=AccountService.deleteAddress(user, json);
			break;
		}
		
		case "choseAddress":
		{
			addressChosen=AccountService.getAddressFromId(json);
			
			if(addressChosen!=null) {
				session.setAttribute("addressChosen", addressChosen);
				result.addProperty("result", "SUCCESS");
			}
			else {
				result.addProperty("result", "FAIL");
			}
			
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
