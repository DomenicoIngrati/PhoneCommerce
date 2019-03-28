package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class Cart {
	private User user;
	private Map<Product,Integer> products; //prodotto, quantita
	
	private Set<Item> itemsOnCart;
	
	private double total;
	
	public Cart() {
		user = null;
		products = new HashMap<Product, Integer>();
		itemsOnCart=new HashSet<Item>();
		total = 0;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Item> getProducts() {
		return itemsOnCart;
	}

	public void setProducts(Map<Product, Integer> products) {
		this.products = products;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	public void sumTotal(double f)
    {
    	this.total+=f;
    }
    
    public void decrementTotal(double f)
    {
    	this.total-=f;
    }
    
    public void addProducts(Product p,int quantita) {
    	Item i=new Item(p,quantita);
    	sumTotal(p.getPrice()*quantita);
    	products.put(p, quantita);
    	itemsOnCart.add(i);
    }
    

}
