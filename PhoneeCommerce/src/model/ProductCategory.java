package model;

public class ProductCategory {
	
	private long id;
	private String name;
	private ProductCategory anchestorCategory;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ProductCategory getAnchestorCategory() {
		return anchestorCategory;
	}

	public void setAnchestorCategory(ProductCategory anchestorCategory) {
		this.anchestorCategory = anchestorCategory;
	}

	public ProductCategory() {
		
		name = "";
		anchestorCategory = null;
	}
	

}
