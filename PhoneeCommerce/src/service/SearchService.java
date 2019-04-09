package service;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;

import model.Item;
import model.Product;
import model.ProductCategory;
import persistence.dao.ProductCategoryDAO;
import persistence.dao.ProductDAO;
import persistence.dao.UserDAO;
import persistence.util.DAOfactory;

public class SearchService {
	
	private SearchService() {
		
	}
	
	public static Set<Product> findProducts(String json,JsonObject result, HttpSession session){
		
		DAOfactory factory = DAOfactory.getDAOFactory(DAOfactory.POSTGRESQL);
		ProductDAO dao = factory.getProductDAO();
		ProductCategoryDAO catDao = factory.getProductCategoryDAO();
		
		Set<Product> productsFound=new HashSet<Product>();
		
		Set<String> productsNames=dao.findAllNames();
		
		Set<String> categoriesNames=catDao.findAllNames();
		
		String stringToBeFound;
		
		if(json.charAt(0)=='"' && json.charAt(json.length()-1)=='"') {
			
			 stringToBeFound= json.substring(1, json.length()-1);
		}
		else {
			stringToBeFound=json;
		}
		
		boolean foundSomething=false;
		
		String[] splited = stringToBeFound.split("\\s+");
		
		///////////////////////////////////////////
		/////// primo caso nome di cateogoria//////
		///////////////////////////////////////////
		
		ProductCategory productCat=null;
		
		for(String n: categoriesNames) {
			if(n.equalsIgnoreCase(stringToBeFound)) {
				productCat=catDao.findByName(n);
				session.setAttribute("suggestion", n);
				return dao.findByCategory(productCat);
			}
		}
		
		///////////////////////////////////////////
		/////// secondo caso nome di prodotto//////
		///////////////////////////////////////////
		
		for(String n: productsNames) {
			if(n.equalsIgnoreCase(stringToBeFound)) {	
				productsFound.add(dao.findByName(n));
				session.setAttribute("suggestion", n);
				return productsFound;
			}
		}
		
		
		
		
		
		if(splited.length>1) {
			session.setAttribute("suggestion", null);
			return null;
		}else {
			
			
			////////////////////////////////////////
			/////// subStringa della categoria /////
			////////////////////////////////////////
			for(String n: categoriesNames) {
				
				
				if(n.toLowerCase().contains(stringToBeFound.toLowerCase()) && (n.length()- stringToBeFound.length())<=2) {
					
					session.setAttribute("suggestion", n);
					return findProducts(n, result, session);
				}
			}
			
			
			////////////////////////////////////////
			/////// subStringa del prodotto ////////
			////////////////////////////////////////
			
			
			for(String n: productsNames) {
					
				String[] splitedProductName = n.split("\\s+");
				
				if(splitedProductName.length>1) {
					
					for(int i=0;i<splitedProductName.length;i++) {
						if(splitedProductName[i].equalsIgnoreCase(stringToBeFound)) {
							Product productToBeAdded=dao.findByName(n);
							if(!productsFound.contains(productToBeAdded)) {
								productsFound.add(dao.findByName(n));
								foundSomething=true;
							}
						}
					}
				}
			}
			
			if(foundSomething) {
			session.setAttribute("suggestion", stringToBeFound);
			return productsFound;
			}
			else {
				for(String n: productsNames) {
					if(n.toLowerCase().contains(stringToBeFound.toLowerCase()) && (n.length()- stringToBeFound.length())<=2) {
						session.setAttribute("suggestion", n);
						return null;
					}
				}
			}
						
		}
		return null;
	}
	
	
}
