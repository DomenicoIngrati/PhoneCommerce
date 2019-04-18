package model;

import java.util.Base64;

public class Carousel {

    private long id;
    private byte[] image;
    private String title;
    private String description;
	private String imageString;

	public String getImageString() {
		return imageString;
	}

	public void setImageString(String imageString) {
		this.imageString = imageString;
	}

	public Carousel() {
		title = "";
		description = "";

	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
		this.imageString = "data:image/png;base64," + Base64.getEncoder().encodeToString(image);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	@Override
    public String toString() {
	return "Carousel [id=" + id + ", title=" + title + ", discription=" + description + "]";
    }

}