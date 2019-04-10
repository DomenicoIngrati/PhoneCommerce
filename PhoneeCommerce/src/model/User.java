package model;

import java.util.ArrayList;
import java.util.List;

public class User {

    private long id;
    private String username;
    private String password;
    private String email;
    private String name;
    private String surname;
    private Type type;

    private List<Order> orders;
    private List<Wishlist> whishlists;
    private List<Review> reviews;
    private Cart cart;

    public User() {
	username = "";
	password = "";
	email = "";
	name = "";
	surname = "";
	orders = new ArrayList<>();
	reviews = new ArrayList<>();
	whishlists = new ArrayList<>();
	type = Type.Customer;

    }



    public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getSurname() {
		return surname;
	}



	public void setSurname(String surname) {
		this.surname = surname;
	}


	public List<Order> getOrders() {
		return orders;
	}



	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}



	public List<Wishlist> getWhishlists() {
		return whishlists;
	}



	public void setWhishlists(List<Wishlist> whishlists) {
		this.whishlists = whishlists;
	}



	public List<Review> getReviews() {
		return reviews;
	}



	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}



	public void setType(Type type) {
		this.type = type;
	}



	public Cart getCart() {
		return cart;
	}



	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	public Type getType() {
		return type;
	}



	@Override
    public String toString() {
	return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", name="
		+ name + ", surname=" + surname + ", type=" + type + "]";
    }

}