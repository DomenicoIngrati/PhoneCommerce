package model;

public class Item {
	
	private Product product;
	private int quantity;
	
	public Item() {
		product=null;
		quantity=0;
	}
	
	public Item(Product p, int q) {
		product=p;
		quantity=q;
	}
	
	public Product getProduct() {
		return product;
	}
	
	public int getQuantity() {
		return quantity;
	}

}
