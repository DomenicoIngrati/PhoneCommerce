package persistence;

import model.*;
import persistence.dao.implementation.*;

import persistence.util.*;

public class Main {

	public static void main(String[] args) {
		try {
			Class.forName("org.postgresql.Driver").newInstance();
			DataSource dataSource=new DataSource("jdbc:postgresql://localhost:5432/PhoneCommerce","postgres","postgres");
			
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
			catDAO.create(xiaomi);
			
			
					
			
			System.out.println("ALL DONE");
		} 
		catch (Exception e) {
			System.err.println("PostgresDAOFactory.class: failed to load MySQL JDBC driver\n"+e);
			e.printStackTrace();
		}

	}

}
