package persistence.util;

public class DatabaseManager {
	private static DatabaseManager instance = null;
	
	public static DatabaseManager getInstance(){
		if (instance == null){
			instance = new DatabaseManager();
		}
		return instance;
	}
	
	private DAOfactory daoFactory;
		
	private DatabaseManager() {
		daoFactory = DAOfactory.getDAOFactory(DAOfactory.POSTGRESQL);
	}
	
	public DAOfactory getDaoFactory() {
		return daoFactory;
	}
	
	

}
