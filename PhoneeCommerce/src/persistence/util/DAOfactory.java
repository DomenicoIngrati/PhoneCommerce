package persistence.util;

import persistence.dao.*;


public abstract class DAOfactory {
	
	// --- List of supported DAO types ---

	
		/**
		 * Numeric constant '1' corresponds to explicit MySQL choice
		 */
		public static final int MYSQL = 1;
		
		/**
		 * Numeric constant '2' corresponds to explicit Postgres choice
		 */
		public static final int POSTGRESQL = 2;
		
		
		// --- Actual factory method ---
		
		/**
		 * Depending on the input parameter
		 * this method returns one out of several possible 
		 * implementations of this factory spec 
		 */
		public static DAOfactory getDAOFactory(int whichFactory) {
			switch ( whichFactory ) {
			
			case MYSQL:
				return null;//new HsqldbDAOFactory();
			case POSTGRESQL:
				return new PostgresDAOFactory();
			default:
				return null;
			}
		}
		
		
		
		// --- Factory specification: concrete factories implementing this spec must provide this methods! ---
		
		/**
		 * Method to obtain a DATA ACCESS OBJECT
		 * for the datatype 'Student'
		 */
		public abstract OrderDAO getOrderDao();
		
		public abstract ProductDAO getProductDao();
		
		public abstract ReviewDAO getReviewDao();
		
		public abstract UserDAO getUserDao();
		
		public abstract WishlistDAO getWishlistDao();

//		public abstract persistence.UtilDao getUtilDAO(); //???
	
}
