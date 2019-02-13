package model;

import java.util.HashMap;
import java.util.Map;


public class Cart {
	private User user;
	private Map<Product,Integer> products; //prodotto, quantita
	
	private float total;
	
	public Cart() {
		user = null;
		products = new HashMap<Product, Integer>();
		total = 0;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Map<Product, Integer> getProducts() {
		return products;
	}

	public void setProducts(Map<Product, Integer> products) {
		this.products = products;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}
	
	public void sumTotal(float f)
    {
    	this.total+=f;
    }
    
    public void decrementTotal(float f)
    {
    	this.total-=f;
    }
}
