package model;

import java.util.Date;
import java.util.List;


public class Order {
	
	private long id;
    private Date date;
    private User user;
    private float total;
    private List<Item> products;
    private Address address;
    
    public Order() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date2) {
		this.date = date2;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(double d) {
		this.total = (float) d;
	}

	public List<Item> getProducts() {
		return products;
	}

	public void setProducts(List<Item> products) {
		this.products = products;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address a) {
		this.address=a;
	}
	
	public boolean checkIfOrderContainsProduct(Product p) {
		
		for(int i=0;i<products.size();i++) {
			if(products.get(i).getProduct().getId()==p.getId())
			{
				return true;
			}		
		}
		return false;
	}
    
    
}
