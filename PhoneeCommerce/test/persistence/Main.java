package persistence;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import model.*;
import persistence.dao.ProductDAO;
import persistence.dao.UserDAO;
import persistence.dao.WishlistDAO;
import persistence.dao.implementation.*;
import persistence.*;

import persistence.util.*;

public class Main {

	public static void main(String[] args) {
		try {
			Class.forName("org.postgresql.Driver").newInstance();
			DataSource dataSource=new DataSource("jdbc:postgresql://localhost:5432/PhoneCommerce","postgres","postgres");
		
			System.out.println("ALL DONE");
		} 
		catch (Exception e) {
			System.err.println("PostgresDAOFactory.class: failed to load MySQL JDBC driver\n"+e);
			e.printStackTrace();
		}

	}

}
