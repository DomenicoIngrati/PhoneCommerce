package persistence.dao;

import java.util.Map;
import java.util.Set;

import model.Order;
import model.Product;

public interface OrderDAO {
	
	public Integer create(Order modelObject);

    public void delete(Order o);

    public void update(Order o);

    public Set<Product> findByUser(Integer id, Integer offset, Integer limit); //??

    public Order findById(Integer id);
}