package service;

import com.google.gson.JsonObject;
import model.Carousel;
import model.ProductCategory;
import persistence.dao.CarouselDAO;
import persistence.dao.ProductCategoryDAO;
import persistence.util.DatabaseManager;

import java.util.ArrayList;
import java.util.List;

public class CarouselService {

		private CarouselService() {
		}

		public static List<Carousel> getAllBanners() {
			
			CarouselDAO cat = DatabaseManager.getInstance().getDaoFactory().getCarouselDAO();
			List<Carousel> tmp = new ArrayList<Carousel>();
			tmp = cat.findAll();
			return tmp;
		}

	public static JsonObject createBanner(Carousel c) {

		JsonObject result = new JsonObject();
		CarouselDAO dao = DatabaseManager.getInstance().getDaoFactory().getCarouselDAO();

		if (dao.create(c)) {
			result.addProperty("result", "SUCCESS");
			result.addProperty("message", "Banner has been insert in to carousel succefully!");
		} else {
			result.addProperty("result", "FAIL");
			result.addProperty("reason", "Sorry, something went wrong!");
		}
		System.err.println(c);
		return result;
	}

	public static JsonObject updateTexts(Carousel c) {

		JsonObject result = new JsonObject();
		CarouselDAO dao = DatabaseManager.getInstance().getDaoFactory().getCarouselDAO();



		if (dao.updateTexts(c)) {
			result.addProperty("result", "SUCCESS");
			result.addProperty("message", "Banner has been insert in to carousel succefully!");
		} else {
			result.addProperty("result", "FAIL");
			result.addProperty("reason", "Sorry, something went wrong!");
		}
		System.err.println(c);
		return result;

	}

	public static JsonObject updateImage(Carousel c) {
		JsonObject result = new JsonObject();
		CarouselDAO dao = DatabaseManager.getInstance().getDaoFactory().getCarouselDAO();

		if (dao.updateImage(c)) {
			result.addProperty("result", "SUCCESS");
			result.addProperty("message", "Banner has been updated in to carousel succefully!");
		} else {
			result.addProperty("result", "FAIL");
			result.addProperty("reason", "Sorry, something went wrong!");
		}
		System.err.println(c);
		return result;
	}

	public static JsonObject deleteBanner(long id) {
		JsonObject result = new JsonObject();
		CarouselDAO dao = DatabaseManager.getInstance().getDaoFactory().getCarouselDAO();

		if (dao.delete(id)) {
			result.addProperty("result", "SUCCESS");
			result.addProperty("message", "Banner has been updated in to carousel succefully!");
		} else {
			result.addProperty("result", "FAIL");
			result.addProperty("reason", "Sorry, something went wrong!");
		}

		return result;
	}
}
