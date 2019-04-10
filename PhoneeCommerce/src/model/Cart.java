package model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	private User user;
	
	private List<Item> products;
	
	private double total;
	
	private int size;
	
	public Cart() {
		user = null;
		products=new ArrayList<Item>();
		total = 0;
		size = 0;
	}

	public void setProducts(List<Item> products) {
		this.products = products;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Item> getProducts() {
		return products;
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
    
    public void calculateTotal() {
    	double tmp = 0;
    	for(Item prod: this.products) {
    		tmp += (prod.getQuantity() * prod.getProduct().getPrice());
    	}
    	this.total = tmp;
    }
    
    public void decreaseProduct(Product p) {
    	for(Item prod: this.products)
    	{
    		if(prod.getProduct().equals(p))
    		{
    			if(prod.getQuantity() > 1)
    				prod.decreaseQuantity();
    			break;
    			
    		}
    	}
    	calculateTotal();
    }
    
    public void addProducts(Product p,int quantita) {
    	boolean ce = false;
    	for(Item prod: this.products)
    	{
    		if(prod.getProduct().equals(p))
    		{
    			if(prod.getQuantity() < 10)
    			{
    				prod.increaseQuantity(quantita);
    				sumTotal(p.getPrice()*quantita);
    			}
    			ce = true;
    			
    		}
    	}
    	if(!ce)
    	{
    		Item i=new Item(p,quantita);
        	sumTotal(p.getPrice()*quantita);
        	products.add(i);
    	}
    }
    
    public void deleteProduct(Product p) {
        // TODO Auto-generated method stub
    	  List<Item> toRemove = new ArrayList<Item>();
          for (Item i : this.products){
            if(i.getProduct().equals(p)){
               decrementTotal(i.getProduct().getPrice()*i.getQuantity());
               toRemove.add(i);
            }
          }  
          this.products.removeAll(toRemove);
    }
    
   @Override
	public String toString() {
	   String out = "";
	   for(Item prod: this.products) {
		   out += prod.getProduct().getName() + ": " + prod.getQuantity()+"\n";
	   }
	   return out;
	}

	public int getSize() {
		this.size = 0;
		for(Item it: this.products) {
			this.size += it.getQuantity();
		}
		return this.size;
	}
}
