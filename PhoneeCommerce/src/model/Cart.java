package model;

import java.util.HashSet;
import java.util.Set;

public class Cart {
	private User user;
	
	private Set<Item> itemsOnCart;
	
	private double total;
	
	private int size;
	
	public Cart() {
		user = null;
		itemsOnCart=new HashSet<Item>();
		total = 0;
		size = 0;
	}

	public void setProducts(Set<Item> itemsOnCart) {
		this.itemsOnCart = itemsOnCart;
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
    	boolean ce = false;
    	for(Item prod: this.itemsOnCart)
    	{
    		if(prod.getProduct().equals(p))
    		{
    			prod.increaseQuantity(quantita);
    			ce = true;
    		}
    	}
    	if(!ce)
    	{
    		Item i=new Item(p,quantita);
        	sumTotal(p.getPrice()*quantita);
        	itemsOnCart.add(i);
    	}
    }
   @Override
	public String toString() {
	   String out = "";
	   for(Item prod: this.itemsOnCart) {
		   out += prod.getProduct().getName() + ": " + prod.getQuantity()+"\n";
	   }
	   return out;
	}

	public int getSize() {
		this.size = 0;
		for(Item it: this.itemsOnCart) {
			this.size += it.getQuantity();
		}
		return this.size;
	}
}
