package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class User {

    private int id;
    private String username;
    private String password;
    private String email;
    private String name;
    private String surname;
    private Type type;
    private Long coins;

    private Set<Order> orders;
    private Set<Wishlist> whishlists;
    private Set<Review> reviews;
    private Cart cart;

    public User() {
	username = "";
	password = "";
	email = "";
	name = "";
	surname = "";
	coins = 0L;
	orders = new HashSet<>();
	reviews = new HashSet<>();
	whishlists = new HashSet<>();
	type = Type.Customer;

    }



    public int getId() {
		return id;
	}



	public void setId(int id) {
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



	public Long getCoins() {
		return coins;
	}



	public void setCoins(Long coins) {
		this.coins = coins;
	}



	public Set<Order> getOrders() {
		return orders;
	}



	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}



	public Set<Wishlist> getWhishlists() {
		return whishlists;
	}



	public void setWhishlists(Set<Wishlist> whishlists) {
		this.whishlists = whishlists;
	}



	public Set<Review> getReviews() {
		return reviews;
	}



	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
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
		+ name + ", surname=" + surname + ", type=" + type + ", coins=" + coins + "]";
    }

}