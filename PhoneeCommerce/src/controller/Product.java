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

import model.Type;
import model.User;
import service.ProductService;

/**
 * Servlet implementation class Product
 */
public class Product extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Product() {
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
		
		
		User user = (User) session.getAttribute("user");
		System.out.println("ciao");
		switch (action) {
		case "CREATE": {
			if(user.getType() == Type.Organizer)
				result = ProductService.createProduct(json);
			else
			{
				result.addProperty("result", "FAIL");
				result.addProperty("message", "You are not administrator!");
			}
			break;
		}
//		case "UPDATE": {
//			EventService eventService1 = new EventService(json);
//			result = eventService1.updateEvent();
//			break;
//		}
//		case "DELETE":
//		{
//			EventService eventservice = new EventService(json);
//			result=eventservice.deleteEvent();
//			break;
//		}
//		case "SUSPEND":
//		{
//			EventService eventservice = new EventService(json);
//			result=eventservice.suspendEvent();
//			break;
//		}
//		case "showCategory": {
//			EventService eventService2 = new EventService(json);
//			result = eventService2.showCategory();
//			break;
//		}
//		case "showTicketCategory": {
//			EventService service = new EventService(json);
//			result = service.showTicketCategory();
//			break;
//		}
//		case "showReviews": {
//			Event event = (Event) session.getAttribute("idevent");
//			EventService service = new EventService();
//			result = service.showReview(event);
//			break;
//		}
//		case "addReview": {
//			Event event = (Event) session.getAttribute("idevent");
//			User user = (User) session.getAttribute("user");
//			EventService service = new EventService();
//			result = service.addReview(user, event, json);
//			break;
//		}
//		case "showTicket": {
//			EventService eventservice = new EventService(json);
//			result = eventservice.showTicketOrganizer();			
//			break;
//
//		}
//		case "showGuest":{
//			EventService eventService= new EventService(json);
//			result=eventService.showGuest();
//			break;
//			
//		}
//		case "deleteguest":
//		{
//			EventService eventService= new EventService();		
//			result=eventService.deleteGuest(json);
//			break;
//		}
//		case "editguest":{
//			EventService eventservice = new EventService();
//			result = eventservice.editGuest(json);
//			break;
//		}
//		case "updatePrice":
//		{
//			EventService eventservice= new EventService();
//			result=eventservice.updatePriceTicketCategory(json);
//			break;
//		}

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
