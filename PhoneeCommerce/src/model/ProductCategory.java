package model;

public class ProductCategory {
	
	private long id;
	private String name;
	private ProductCategory anchestorCategory;
	private boolean visible;
	
	
	public boolean getVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

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
	
	public ProductCategory(String name) {
		this.name = name;
		anchestorCategory = null;
	}
	@Override
	public String toString() {
		return name;
	}
	

}
