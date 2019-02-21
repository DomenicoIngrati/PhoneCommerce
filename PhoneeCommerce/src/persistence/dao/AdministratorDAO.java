package persistence.dao;

import java.util.Set;

import model.Order;
import model.Product;
import model.User;

public interface AdministratorDAO {
	
	public void create(User admin);

    public void delete(User admin);

    public void update(User admin);

    public User findByEmail(String email);

    public User findById(long id);
    
    public void putProduct(User admin, Product product);
    
//    public void putProducts(Set <Product> product);
    
    public void deleteProduct(Product product);
    
    
    
    

}
