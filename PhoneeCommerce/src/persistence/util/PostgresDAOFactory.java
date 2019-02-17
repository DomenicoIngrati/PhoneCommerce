package persistence.util;

import persistence.util.DataSource;
import persistence.dao.OrderDAO;
import persistence.dao.ProductDAO;
import persistence.dao.ReviewDAO;
import persistence.dao.UserDAO;
import persistence.dao.WishlistDAO;

public class PostgresDAOFactory extends DAOfactory {
	
private static  DataSource dataSource;
	

	// --------------------------------------------

	static {
		try {
			Class.forName("org.postgresql.Driver").newInstance();
			//questi vanno messi in file di configurazione!!!	
//			dataSource=new DataSource("jdbc:postgresql://52.39.164.176:5432/xx","xx","p@xx");
			dataSource=new DataSource("jdbc:postgresql://localhost:5432/Segreteria2019","postgres","postgres");
		} 
		catch (Exception e) {
			System.err.println("PostgresDAOFactory.class: failed to load MySQL JDBC driver\n"+e);
			e.printStackTrace();
		}
	}

	
	// --------------------------------------------
	

	@Override
	public OrderDAO getStudenteDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductDAO getScuolaDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReviewDAO getCorsoDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDAO getCorsoDiLaureaDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WishlistDAO getDipartimentoDAO() {
		// TODO Auto-generated method stub
		return null;
	}

}
