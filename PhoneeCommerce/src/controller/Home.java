package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cart;
import model.Product;
import model.ProductCategory;
import model.Type;
import model.User;
import model.Address;
import service.AccountService;
import service.ProductCategoryService;
import service.ProductService;

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
    Set<Product> brandProducts = new HashSet<Product>();
    
 
    Product selectedProduct = new Product();
    String selectedProductName;
    
    List<ProductCategory> cats = new ArrayList<ProductCategory>();
    cats = ProductCategoryService.findAllCategory();
    
    request.setAttribute("brands", cats);
    
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
    	
//    	ProductCategory old = catDao.findByName(jsonProduct.getString("oldnamecategory"));
//		List <Product> totalProduct = dao.findFormCategory(old);
//		if(totalProduct.isEmpty())
//		{
//			catDao.deleteById(old);
//			result.addProperty("oldnamecategory", old.getId());
//		}
    	
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

    	break;
      
    case "singleProductView":
    	page="product";
    	selectedProductName=request.getParameter("productName");
    	selectedProduct=ProductService.findProductByName(selectedProductName);
    	request.setAttribute("selectedProduct",selectedProduct);
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
        	request.setAttribute("allAddress", allAddress);
    	}
    	
    	break;

    default:
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