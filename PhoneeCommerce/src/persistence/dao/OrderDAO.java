package persistence.dao;

import java.util.List;

import model.Order;

public interface OrderDAO {
	
	public boolean create(Order modelObject);

    public void delete(Order o);

    public void update(Order o);

    public List<Order> findByUser(long id);

    public Order findById(long id);
}