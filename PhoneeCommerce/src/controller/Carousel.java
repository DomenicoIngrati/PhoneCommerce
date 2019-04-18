package controller;

import com.google.gson.JsonObject;
import model.ProductCategory;
import model.Type;
import model.User;
import service.CarouselService;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * Servlet implementation class Product
 */
@MultipartConfig
public class Carousel extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Carousel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    private static String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String action = request.getParameter("action");

		JsonObject result = new JsonObject();
		User user = (User) session.getAttribute("user");
		
		System.out.println(action);

		switch (action) {
			case "create": {

				if (user.getType() == Type.Organizer) {
					model.Carousel tmp = new model.Carousel();
					tmp.setTitle(request.getParameter("title"));
					tmp.setDescription(request.getParameter("description"));

					Part imagePart = request.getPart("file"); // Retrieves <input type="file" name="file">
					String filename = getFilename(imagePart);
					InputStream fileContent = imagePart.getInputStream();

					byte[] imgbyte = new byte[(int) imagePart.getSize()];

					fileContent.read(imgbyte, 0, imgbyte.length);
					fileContent.close();

					tmp.setImage(imgbyte);

//					System.out.println(Base64.getEncoder().encodeToString(tmp.getImage()));

//				    System.out.println(filename);
//					System.out.println(tmp.toString());
					result = CarouselService.createBanner(tmp);
//					result.addProperty("result", "FAIL");
				} else {
					result.addProperty("result", "FAIL");
					result.addProperty("message", "You are not administrator!");
				}
				break;
			}

			case "updatetexts": {
				if (user.getType() == Type.Organizer) {
					model.Carousel tmp = new model.Carousel();
					tmp.setId(Long.parseLong(request.getParameter("id")));
					tmp.setTitle(request.getParameter("title"));
					tmp.setDescription(request.getParameter("description"));

//					System.out.println(tmp);

					result = CarouselService.updateTexts(tmp);
//					result.addProperty("result", "FAIL");
				} else {
					result.addProperty("result", "FAIL");
					result.addProperty("message", "You are not administrator!");
				}
				break;
			}
			case "updateimage": {

				if (user.getType() == Type.Organizer) {
					model.Carousel tmp = new model.Carousel();

					tmp.setId( Long.parseLong(request.getParameter("id")));

					Part imagePart = request.getPart("file"); // Retrieves <input type="file" name="file">
					String filename = getFilename(imagePart);
					InputStream fileContent = imagePart.getInputStream();

					byte[] imgbyte = new byte[(int) imagePart.getSize()];

					fileContent.read(imgbyte, 0, imgbyte.length);
					fileContent.close();

					tmp.setImage(imgbyte);

					result = CarouselService.updateImage(tmp);
//					result.addProperty("result", "FAIL");
				} else {
					result.addProperty("result", "FAIL");
					result.addProperty("message", "You are not administrator!");
				}
				break;

			}
			case "deletebanner": {

				if (user.getType() == Type.Organizer) {

					long id = Long.parseLong(request.getParameter("id"));

					result = CarouselService.deleteBanner(id);
//					result.addProperty("result", "FAIL");
				} else {
					result.addProperty("result", "FAIL");
					result.addProperty("message", "You are not administrator!");
				}

				break;
			}
		}

		response.getWriter().write(result.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
