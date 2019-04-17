package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.google.gson.JsonObject;

import model.Type;
import model.User;
import model.ProductCategory;
import service.ProductService;

/**
 * Servlet implementation class Product
 */
@MultipartConfig
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
    
    private static String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String json = ""; // parse request in json format
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		if (br != null) {
			json = br.readLine();
		}

		System.out.println(json);

		HttpSession session = request.getSession();
		String action = request.getParameter("action");

		JsonObject result = new JsonObject();
		User user = (User) session.getAttribute("user");
		
		System.out.println(action);
		

		switch (action) {
			case "UPDATE": {

			result = ProductService.updateProduct(json);
				break;
			}

			case "DELETE":
			{
				result=ProductService.deleteProduct(json);
				break;
			}

			case "newReview":
			{
				if(user!=null) {
				result=ProductService.addNewReview(json,user);
				}
				break;
			}
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
