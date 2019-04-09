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
	
	public void increaseQuantity(int q) {
		this.quantity += q;
	}

	public void decreaseQuantity() {
		if(this.quantity > 0)
			this.quantity -= 1;
		
	}
	

}
