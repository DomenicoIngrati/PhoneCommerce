package model;

import java.util.ArrayList;
import java.util.List;

public class Wishlist {
	private long id;
    private String title;
    private User user;
    private String type;
	private List <Product> products;
    
    public Wishlist() {
		this.products = new ArrayList<Product>();
	}
    
    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	@Override
	public String toString() {
		String out = "id: " + id + ", title: " + title + ", user: " + user;
		return out;
 	}
	
}
