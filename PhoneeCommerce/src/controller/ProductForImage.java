package controller;

import com.google.gson.JsonObject;
import model.ProductCategory;
import model.Type;
import model.User;
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
public class ProductForImage extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductForImage() {
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
			case "CREATE": {

				if (user.getType() == Type.Organizer) {
					model.Product tmp = new model.Product();
					tmp.setName(request.getParameter("name"));
					tmp.setCategory(new ProductCategory(request.getParameter("category")));
					tmp.setDescription(request.getParameter("description"));
					tmp.setPrice(Double.parseDouble(request.getParameter("price")));

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
					result = ProductService.createProduct(tmp);

				} else {
					result.addProperty("result", "FAIL");
					result.addProperty("message", "You are not administrator!");
				}
				break;
			}
			case "updateImage": {

				Part imagePart = request.getPart("file"); // Retrieves <input type="file" name="file">
				String filename = getFilename(imagePart);
				InputStream fileContent = imagePart.getInputStream();

				byte[] imgbyte = new byte[(int) imagePart.getSize()];

				fileContent.read(imgbyte, 0, imgbyte.length);
				fileContent.close();

				Long idproduct = Long.parseLong(request.getParameter("idproduct"));
				System.out.println(filename);
				System.out.println(idproduct);

				result = ProductService.updateProductImage(imgbyte, idproduct);
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
