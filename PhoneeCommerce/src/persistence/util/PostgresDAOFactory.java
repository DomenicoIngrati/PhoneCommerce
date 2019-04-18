package persistence.util;

import persistence.dao.*;
import persistence.util.DataSource;
import persistence.dao.implementation.*;

public class PostgresDAOFactory extends DAOfactory {
	
private static  DataSource dataSource;
	

	// --------------------------------------------

	static {
		try {
			Class.forName("org.postgresql.Driver").newInstance();
			//questi vanno messi in file di configurazione!!!	
//			dataSource=new DataSource("jdbc:postgresql://52.39.164.176:5432/xx","xx","p@xx");
			
			
			//DA SOSTITUIRE CON I NOSTRI DATI 
			dataSource=new DataSource("jdbc:postgresql://localhost:5432/PhoneCommerce","postgres","postgres");
		} 
		catch (Exception e) {
			System.err.println("PostgresDAOFactory.class: failed to load Postres JDBC driver\n"+e);
			e.printStackTrace();
		}
	}

	
	// --------------------------------------------
	

	@Override
	public OrderDAO getOrderDAO() {
		return new OrderDaoJDBC(dataSource);
	}

	@Override
	public ProductDAO getProductDAO() {
		return new ProductDaoJDBC(dataSource);
	}

	@Override
	public ReviewDAO getReviewDAO() {
		return new ReviewDaoJDBC(dataSource);
	}

	@Override
	public UserDAO getUserDAO() {
		return new UserDaoJDBC(dataSource);
	}

	@Override
	public WishlistDAO getWishlistDAO() {
		return new WishListDaoJDBC(dataSource);
	}

	@Override
	public AdministratorDAO getAdministratorDAO() {
		return new AdministratorDaoJDBC(dataSource);
	}

	@Override
	public ProductCategoryDAO getProductCategoryDAO() {
		return new ProductCategoryDaoJDBC(dataSource);
	}

	@Override
	public CarouselDAO getCarouselDAO() { return new CarouselJDBC(dataSource); }

	@Override
	public AddressDAO getAddressDAO() {
		return new AddressDaoJDBC(dataSource);
	}

}
