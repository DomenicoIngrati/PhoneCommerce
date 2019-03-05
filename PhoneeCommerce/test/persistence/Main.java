package persistence;

import java.util.HashSet;
import java.util.Set;

import model.*;
import persistence.dao.implementation.*;

import persistence.util.*;

public class Main {

	public static void main(String[] args) {
		try {
			Class.forName("org.postgresql.Driver").newInstance();
			DataSource dataSource=new DataSource("jdbc:postgresql://localhost:5432/PhoneCommerce","postgres","postgres");
			
			
//			UserDaoJDBC userDAO = new UserDaoJDBC(dataSource);
//			
//			User user1 = new User();
//			user1.setName("Rocco");
//			user1.setSurname("Palermiti");
//			user1.setEmail("roky@ciao.it");
//			user1.setUsername("billu");
//			user1.setPassword("gattuzza");
//			user1.setType(Type.Customer);
////			userDAO.create(user1);
//			
//			WishListDaoJDBC wishDAO = new WishListDaoJDBC(dataSource);
//			Wishlist wish = new Wishlist();
//			wish.setTitle("telefoni");
//			wish.setUser(user1);
//			wish.setId(25);
			
//			wishDAO.delete(wish);
			
//			
//			ProductCategoryDaoJDBC catDAO = new ProductCategoryDaoJDBC(dataSource);
//			ProductCategory apple = new ProductCategory();
//			apple.setName("Apple");
////			catDAO.create(xiaomi);
//			
//			ProductDaoJDBC productDAO = new ProductDaoJDBC(dataSource);
//			Product p = new Product();
//			p.setCategory(apple);
//			p.setName("iPhone X");
//			p.setDescription("telefono");
//			p.setPrice(950);
//			productDAO.create(p);
//			
//			
//			
//			AdministratorDaoJDBC adminDAO = new AdministratorDaoJDBC(dataSource);
//			User admin = new User();
//			admin.setEmail("admin@admin.it");
//			admin.setPassword("1234");
//			
//			adminDAO.create(admin);
//			
//			Set<User> admins = new HashSet<User>();
//			admins = adminDAO.findAll();
//			
//			for(User ad : admins)
//			{
//				adminDAO.delete(ad);
//			}
//////		
			
			
					
			
			
			
			
					
			
			System.out.println("ALL DONE");
		} 
		catch (Exception e) {
			System.err.println("PostgresDAOFactory.class: failed to load MySQL JDBC driver\n"+e);
			e.printStackTrace();
		}

	}

}
