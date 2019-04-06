package model;

import java.util.Date;
import java.util.Set;


public class Order {
	
	private long id;
    private Date date;
    private User user;
    private float total;
    private Set<Item> products;
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

	public Set<Item> getProducts() {
		return products;
	}

	public void setProducts(Set<Item> products) {
		this.products = products;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address a) {
		this.address=a;
	}
    
    
}
