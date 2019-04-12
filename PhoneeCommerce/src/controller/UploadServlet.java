package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.StandardCopyOption;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

/**
 * Servlet implementation class UploadServlet
 */
@MultipartConfig
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part file = request.getPart("file");
        String filename = getFilename(file);
        InputStream filecontent = file.getInputStream();
        
        File fileimg = new File("/Users/RokyMac/git/PhoneCommerce/PhoneeCommerce/WebContent/img/products/" + filename);
        FileUtils.copyInputStreamToFile(filecontent, fileimg);

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("File " + filename + " successfully uploaded");
    }

    private static String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }

}
