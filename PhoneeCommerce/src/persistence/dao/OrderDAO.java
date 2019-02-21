package persistence.dao;

import java.util.Map;
import java.util.Set;

import model.Order;
import model.Product;

public interface OrderDAO {
	
	public void create(Order modelObject);

    public void delete(Order o);

    public void update(Order o);

    public Set<Order> findByUser(long id, Integer offset, Integer limit); //??

    public Order findById(long id);
}