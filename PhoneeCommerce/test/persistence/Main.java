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
			
			if(dataSource == null)
			{
				System.out.println("NULLL");
			}
			
			UserDaoJDBC userDAO = new UserDaoJDBC(dataSource);
			
			User user1 = new User();
			user1.setName("Rocco");
			user1.setSurname("Palermiti");
			user1.setEmail("gata@bau.it");
			user1.setUsername("billu");
			user1.setPassword("gattuzza");
			user1.setType(Type.Customer);
//			userDAO.create(user1);
			
			ProductCategoryDaoJDBC catDAO = new ProductCategoryDaoJDBC(dataSource);
			ProductCategory xiaomi = new ProductCategory();
			xiaomi.setName("XIAOMI");
//			catDAO.create(xiaomi);
			
			AdministratorDaoJDBC adminDAO = new AdministratorDaoJDBC(dataSource);
			User admin = new User();
			admin.setEmail("admin@admin.it");
			admin.setPassword("1234");
			
			adminDAO.create(admin);
			
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
