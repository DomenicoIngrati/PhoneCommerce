package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;

import model.Cart;
import model.Order;
import model.Product;
import model.ProductCategory;
import model.Review;
import model.Type;
import model.User;
import model.Address;
import service.AccountService;
import service.OrderService;
import service.ProductCategoryService;
import service.ProductService;
import service.SearchService;

/* Servlet implementation class Home
 */
public class Home extends HttpServlet {
  private static final long serialVersionUID = 1L;
       
    /* @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
        // TODO Auto-generated constructor stub
    }

  /* @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    ProductCategory productCat=new ProductCategory(); 
    
    List<Product> brandProducts = new ArrayList<Product>();
    
	BufferedReader br = new BufferedReader(request.getReader());
	String json = "";
	if (br != null) {
		json = br.readLine();
	}
	
	JsonObject result = new JsonObject();
	
    Product selectedProduct = new Product();
    String selectedProductName;
    
    List<ProductCategory> cats = new ArrayList<ProductCategory>();
    cats = ProductCategoryService.findAllCategory();
    
    request.setAttribute("brands", cats);
    
//    Wishlist wishlist = null;
    
    HttpSession session = request.getSession();
    
    Cart cart = (Cart) session.getAttribute("cart");
    if (cart == null) {
      cart = new Cart();
      session.setAttribute("cart", cart);
    }
    
    String action = request.getParameter("action");
    String brandName;

    action = (action == null) ? "" : action;
    String page = "";
    User user = (User) session.getAttribute("user");
    
    switch (action) {
    case "index":
    	List <Product> newProducts = ProductService.getLastSixProducts();
    	request.setAttribute("newproducts", newProducts);
    	if(user != null)
    	{
    		request.setAttribute("wishlist", AccountService.getDefaultWishlist(user));
    	}
    	
    	page="index";
    	break;
      
    case "signin":
    	page="login";
		session.setAttribute("comingFromCart", "No");
    	break;
    
    case "registration":
    	
    	page="signup";
    	String value=request.getParameter("fromCart");
    	if(value.equals("no")) {
    		session.setAttribute("comingFromCart", "No");
    	}
    	else
    	{
    		session.setAttribute("comingFromCart", "Yes");	
    	}
    	break;
    
    case "faq":
    	page="faq";
    	break;
    
    case "myAccount":
      
      
    	if(user == null)
    		page = "index";
    	else
    	{
    		if(user.getType() == Type.Customer)
    			page = "myAccount";
    		else
    			page = "index";
    	}
    	break;
    
    case "addProduct":
      
    	if(user == null)
    		page = "index";
    	else
    	{
    		if(user.getType() == Type.Organizer)
    			page = "insert_product";
    		else
    			page = "index";
    	}
    	break;
    
    case "modifydelete":
        
    	if(user == null)
    		page = "index";
    	else
    	{
    		if(user.getType() == Type.Organizer)
    		{
    			List<Product> allproducts = ProductService.getAllProducts();
    			request.setAttribute("allProducts", allproducts);
    			page = "manageproduct";
    		}
    		else
    			page = "index";
    	}
    	break;
      
    case "productsView":
    	page="productsView";
    	brandName=request.getParameter("brandName");
    	productCat=ProductCategoryService.findCategoryByName(brandName);
    	brandProducts=ProductService.findProductsByCategory(productCat);
    	request.setAttribute("brandProducts", brandProducts);
    	request.setAttribute("pageTitle",brandName);
    	
    	if(user != null) {
    		request.setAttribute("wishlist", AccountService.getDefaultWishlist(user));
    	}

    	break;
      
    case "singleProductView":
    	page="product";
    	selectedProductName=request.getParameter("productName");
    	selectedProduct=ProductService.findProductByName(selectedProductName);
    	List<Review> productReviews=ProductService.findReviewsByProduct(selectedProduct);
    	float feedbackAverage=ProductService.countFeedbackAverage(productReviews);
    	request.setAttribute("selectedProduct",selectedProduct);
    	request.setAttribute("productReviews", productReviews);
    	request.setAttribute("feedbackAverage", feedbackAverage);
    	if(user != null)
    	{
    		boolean productBought=ProductService.checkIfUserBoughtProduct(selectedProduct,user);
    		request.setAttribute("productAlreadyBought", productBought);
    		request.setAttribute("wishlist", AccountService.getDefaultWishlist(user));
    	}
    	break;
    
    case "cart":
    	page = "cart";
    	break;
    
    case "cartCheckout":
		session.setAttribute("comingFromCart", "Yes");
    	if(user==null) {
    		page= "login";
    	}
    	else{
    		page="checkout";
    		List<Address> allAddress = AccountService.getAllAddressesFromUser(user);
        	session.setAttribute("allAddress", allAddress);
    	}
    	
    	break;
    
    case "address":
    	if(user == null) {
    		page = "index";
    	}
    	else //if(user.getType() == Type.Customer)
    	{
    		page = "address";
    		List<Address> allAddress = AccountService.getAllAddressesFromUser(user);
//        	List<Address> allAddress = (List<Address>) session.getAttribute("allAddress");
//        	if(allAddress == null) //PER OTTIMIZZARE, SOLO che va resettato ogni volta ch enoi aggiungiamo o eliminiamo un indirizzo altrimenti rimane sempre la stessa pagina
//        		allAddress = AccountService.getAllAddressesFromUser(user);
        	
        	session.setAttribute("allAddress", allAddress);
        	
    		request.setAttribute("wishlist", AccountService.getDefaultWishlist(user));
    	}
    	
    	break;
    	
    case "list":
    	if(user == null) {
    		page = "index";
    	}
    	else //if(user.getType() == Type.Customer)
    	{
    		page = "list";
    		request.setAttribute("wishlist", AccountService.getDefaultWishlist(user));
    	}
    	
    	break;
    	
    case "orderCompleted":
    	if(user == null) {
    		page = "index";
    	}
    	else //if(user.getType() == Type.Customer)
    	{
    		page="orderCompleted";
    	}
    	break;
    	
    case "myOrdersView":
    	if(user != null) {
    		List<Order> myOrders = OrderService.findAllMyOrders(user);
    		session.setAttribute("myOrders",myOrders);
        	page="myOrdersView";
    	}
    	else {
    		page = "index";
    	}
    	
  
    	break;
    
    case "singleOrderView":
    	if(user == null) {
    		page = "index";
    	}
    	else
    	{
    		page="singleOrderView";
    	}
    	break;
    
    case "search":
	    {
	    	List<Product> searchResult=SearchService.findProducts(json, result,session);
	    	
	    	session.setAttribute("searchResult", searchResult);
	    	request.setAttribute("pageTitle",json);
			
	    	result.addProperty("result", "SUCCESS");
			result.addProperty("message", "You have successfully signed-up, will be redirected soon !");
			response.getWriter().write(result.toString()); 
			
	    }
    case "searchCompleted":
	    {
	    	page="searchCompleted";
	    	break;
	    }
	    
//    case "upload":
//    	{
//    		page="upload";
//    		break;
//    	}

    default:
    	List <Product> newProducts2 = ProductService.getLastSixProducts();
    	request.setAttribute("newproducts", newProducts2);
    	if(user != null)
    	{
    		request.setAttribute("wishlist", AccountService.getDefaultWishlist(user));
    	}
    	page="index";
    }
    
    request.setAttribute("page", "content/" + page + ".jsp");

    // this servlet has to forward only to the home.jsp
    RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/home.jsp");
    dispatcher.forward(request, response);
  }

  /* @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO Auto-generated method stub
    doGet(request, response);
  }
}