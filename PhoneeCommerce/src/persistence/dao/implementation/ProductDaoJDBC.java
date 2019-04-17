package persistence.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


import model.Product;
import model.ProductCategory;
import persistence.util.PersistenceException;
import persistence.dao.ProductCategoryDAO;
import persistence.dao.ProductDAO;
import persistence.util.*;

public class ProductDaoJDBC implements ProductDAO {

	private DataSource dataSource;
	
	public ProductDaoJDBC(DataSource dataSource) {
		this.dataSource=dataSource;
	}
	
	@Override
	public boolean delete(Product t) {
		Connection connection = this.dataSource.getConnection();
		try {
			 
			String delete = "delete FROM Product WHERE id = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setLong(1, t.getId());
			
			boolean temp = (statement.executeUpdate() > 0) ? true : false;
			
			if(temp)
			{
				ProductCategoryDAO prodCat = new ProductCategoryDaoJDBC(dataSource);
				List <Product> totalProduct = findFromCategory(t.getCategory());
				if(totalProduct.isEmpty())
					prodCat.deleteById(t.getCategory());
			}
			return temp;

		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			DAOUtility.close(connection);
		}
	}

	@Override
	public boolean update(Product t) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update Product SET name = ?, description = ?, price = ?, category = ?, visible = ? WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, t.getName());
			statement.setString(2, t.getDescription());
			statement.setDouble(3,  t.getPrice());
			statement.setLong(4,  t.getCategory().getId());
			statement.setBoolean(5,  t.getVisible());
			
			ProductCategoryDaoJDBC cat = new ProductCategoryDaoJDBC(dataSource);
			ProductCategory pcat = cat.findByName(t.getCategory().getName());
			
			if(pcat == null)
			{
				cat.create(t.getCategory());
			}
			
			statement.setLong(6, t.getId());
			
			boolean ok;
			ok = (statement.executeUpdate() > 0) ? true : false;
			
			if(ok && !t.getVisible()) {
				ProductDaoJDBC daoProd = new ProductDaoJDBC(dataSource);
				List<Product> allProd = daoProd.findByCategory(pcat);
				List<Product> allProdNotVisible = daoProd.findByCategory(pcat, false);
				if(allProd.size() == allProdNotVisible.size()) {
					
					pcat.setVisible(false);
					cat.updateVisible(pcat);
					
				}
			}
			
			return ok;
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			DAOUtility.close(connection);
		}
		
	}

	public boolean updateImage (Product t){
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update Product SET image = ? WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(update);

			statement.setBytes(1, t.getImage());
			statement.setLong(2, t.getId());

			return (statement.executeUpdate() > 0) ? true : false;

		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			DAOUtility.close(connection);
		}
	}

	public Product findById(Long id) {
		Connection connection = this.dataSource.getConnection();
		Product product = null;
		try {
			PreparedStatement statement;
			String query = "select * from Product where id = ?";
			statement = connection.prepareStatement(query);
			statement.setLong(1, id);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				product = new Product();
				product.setId(result.getInt("id"));				
				product.setName(result.getString("name"));
				product.setDescription(result.getString("description"));
				product.setPrice(result.getFloat("price"));
				product.setVisible(result.getBoolean("visible"));
				if(result.getBytes("image") != null) {
					product.setImage(result.getBytes("image"));

//					if(product.getImage() != null)
//						product.setImageString("data:image/jpeg;base64," + Base64.getEncoder().encodeToString(product.getImage()));
					
				}
				

				ProductCategoryDAO ProdCatDao = new ProductCategoryDaoJDBC(dataSource);
				product.setCategory(ProdCatDao.findById(result.getLong("category")));
				
//				product.setReviews(result.getString("reviews"));
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			DAOUtility.close(connection);
		}	
		return product;
	}
	
	public Product findByName(String name) {
		Connection connection = this.dataSource.getConnection();
		Product product = null;
		try {
			PreparedStatement statement;
			String query = "select * from Product where name = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, name);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				product = new Product();
				product.setId(result.getInt("id"));				
				product.setName(result.getString("name"));
				product.setDescription(result.getString("description"));
				product.setPrice(result.getFloat("price"));
				product.setVisible(result.getBoolean("visible"));
				if(result.getBytes("image") != null) {
					product.setImage(result.getBytes("image"));
//					if(product.getImage() != null)
//						product.setImageString("data:image/jpeg;base64," + Base64.getEncoder().encodeToString(product.getImage()));
					
				}
				
				
				ProductCategoryDAO ProdCatDao = new ProductCategoryDaoJDBC(dataSource);
				product.setCategory(ProdCatDao.findById(result.getLong("category")));
				
//				product.setReviews(result.getString("reviews"));
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			DAOUtility.close(connection);
		}	
		return product;
	}
	
	public List<Product> findByPrice(float price){
		
		Connection connection = this.dataSource.getConnection();
		List<Product> products = new ArrayList<Product>();
		try {
			Product product;
			PreparedStatement statement;
			String query = "select * from Product where price=?";
			statement = connection.prepareStatement(query);
			statement.setFloat(1, price);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				product = new Product();
				product.setId(result.getLong("id"));				
				product.setName(result.getString("name"));
				product.setDescription(result.getString("description"));
				product.setPrice(result.getFloat("price"));
				product.setVisible(result.getBoolean("visible"));
				if(result.getBytes("image") != null) {
					product.setImage(result.getBytes("image"));
//					if(product.getImage() != null)
//						product.setImageString("data:image/jpeg;base64," + Base64.getEncoder().encodeToString(product.getImage()));
					
				}
				
				
				ProductCategoryDAO ProdCatDao = new ProductCategoryDaoJDBC(dataSource);
				product.setCategory(ProdCatDao.findById(result.getLong("category")));
				
				products.add(product);
			}
		}
		catch (SQLException e){
			throw new PersistenceException(e.getMessage());
		}
		finally {
			DAOUtility.close(connection);
		}
		return products;
	}
	
	public List<Product> findByCategory(ProductCategory idCategory){
		
		Connection connection = this.dataSource.getConnection();
		List<Product> products = new ArrayList<Product>();
		try {
			Product product;
			PreparedStatement statement;
			String query = "select * from Product where category=?";
			statement = connection.prepareStatement(query);
			statement.setLong(1, idCategory.getId());
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				product = new Product();
				product.setId(result.getLong("id"));				
				product.setName(result.getString("name"));
				product.setDescription(result.getString("description"));
				product.setPrice(result.getFloat("price"));
				product.setVisible(result.getBoolean("visible"));
				if(result.getBytes("image") != null) {
					product.setImage(result.getBytes("image"));
//					if(product.getImage() != null)
//						product.setImageString("data:image/jpeg;base64," + Base64.getEncoder().encodeToString(product.getImage()));
					
				}
				
				
				ProductCategoryDAO ProdCatDao = new ProductCategoryDaoJDBC(dataSource);
				product.setCategory(ProdCatDao.findById(result.getLong("category")));
				
				products.add(product);
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}	 finally {
			DAOUtility.close(connection);
		}
		return products;
	}


	public void updatePriceProduct(Product p) {
		Connection connection = this.dataSource.getConnection();
		try {
			
			String update = "update Product SET price = ? WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setDouble(1, p.getPrice());
			statement.setLong(2, p.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			DAOUtility.close(connection);
		}
	}

	@Override
	public boolean create(Product p) {
		
		Connection connection = this.dataSource.getConnection();
		try {
			Long id = IdBroker.getId(connection);
			p.setId(id); 
			
			String insert = "insert into Product(id, name, description, price, category, image) values (?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);

			statement.setLong(1, p.getId());
			statement.setString(2, p.getName());
			statement.setString(3, p.getDescription());
			statement.setDouble(4, p.getPrice());
			
			ProductCategoryDaoJDBC cat = new ProductCategoryDaoJDBC(dataSource);
			ProductCategory pcat = cat.findByName(p.getCategory().getName());
			if(pcat == null)
			{
				cat.create(p.getCategory());
				pcat = cat.findByName(p.getCategory().getName());
			}
			
			statement.setLong(5, pcat.getId());
			statement.setBytes(6, p.getImage());

			boolean ok = (statement.executeUpdate() > 0) ? true : false;
			if(ok) {
				if(pcat.getVisible() == false) {
					pcat.setVisible(true);
					cat.updateVisible(pcat);
				}
			}
			return ok;

		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch(SQLException excep) {
					throw new PersistenceException(e.getMessage());
				}
			} 
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	return false;
	}

	@Override
	public List<Product> findAll() {
		Connection connection = this.dataSource.getConnection();
		List<Product> products = new ArrayList<Product>();
		try {
			Product product;
			PreparedStatement statement;
			String query = "select * from Product";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				product = new Product();
				product.setId(result.getLong("id"));				
				product.setName(result.getString("name"));
				product.setDescription(result.getString("description"));
				product.setPrice(result.getFloat("price"));
				product.setVisible(result.getBoolean("visible"));
				if(result.getBytes("image") != null) {
					product.setImage(result.getBytes("image"));
//					if(product.getImage() != null)
//						product.setImageString("data:image/jpeg;base64," + Base64.getEncoder().encodeToString(product.getImage()));
					
				}
				
				
				ProductCategoryDAO ProdCatDao = new ProductCategoryDaoJDBC(dataSource);
				product.setCategory(ProdCatDao.findById(result.getLong("category")));
				
				products.add(product);
			}
		}
		catch (SQLException e){
			throw new PersistenceException(e.getMessage());
		}
		finally {
			DAOUtility.close(connection);
		}
		return products;
	}

	@Override
	public List<Product> findFromCategory(ProductCategory pc) {
		Connection connection = this.dataSource.getConnection();
		List<Product> products = new ArrayList<Product>();
		try {
			Product product;
			PreparedStatement statement;
			String query = "select * from product where category = ?";
			
			statement = connection.prepareStatement(query);
			statement.setLong(1, pc.getId());
			
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				product = new Product();
				product.setId(result.getLong("id"));				
				product.setName(result.getString("name"));
				product.setDescription(result.getString("description"));
				product.setPrice(result.getFloat("price"));
				product.setVisible(result.getBoolean("visible"));
				if(result.getBytes("image") != null) {
					product.setImage(result.getBytes("image"));
//					if(product.getImage() != null)
//						product.setImageString("data:image/jpeg;base64," + Base64.getEncoder().encodeToString(product.getImage()));
					
				}
				
				
				ProductCategoryDAO ProdCatDao = new ProductCategoryDaoJDBC(dataSource);
				product.setCategory(ProdCatDao.findById(result.getLong("category")));
				
				products.add(product);
			}
		}
		catch (SQLException e){
			throw new PersistenceException(e.getMessage());
		}
		finally {
			DAOUtility.close(connection);
		}
		return products;
	}

	@Override
	public List<String> findAllNames() {	
		Connection connection = this.dataSource.getConnection();
		List<String> productsNames = new ArrayList<String>();
		try {
			PreparedStatement statement;
			String query = "select product.name from Product";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {

				
				productsNames.add(result.getString("name"));
			}
		}
		catch (SQLException e){
			throw new PersistenceException(e.getMessage());
		}
		finally {
			DAOUtility.close(connection);
		}
		return productsNames;
	}

	@Override
	public Product findById(Long id, boolean visible) {
		Connection connection = this.dataSource.getConnection();
		Product product = null;
		try {
			PreparedStatement statement;
			String query = "select * from Product where id = ? and visible = ?";
			statement = connection.prepareStatement(query);
			statement.setLong(1, id);
			statement.setBoolean(2, visible);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				product = new Product();
				product.setId(result.getInt("id"));				
				product.setName(result.getString("name"));
				product.setDescription(result.getString("description"));
				product.setPrice(result.getFloat("price"));
				product.setVisible(result.getBoolean("visible"));
				if(result.getBytes("image") != null) {
					product.setImage(result.getBytes("image"));
//					if(product.getImage() != null)
//						product.setImageString("data:image/jpeg;base64," + Base64.getEncoder().encodeToString(product.getImage()));
					
				}
				

				ProductCategoryDAO ProdCatDao = new ProductCategoryDaoJDBC(dataSource);
				product.setCategory(ProdCatDao.findById(result.getLong("category")));
				
//				product.setReviews(result.getString("reviews"));
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			DAOUtility.close(connection);
		}	
		return product;
	}

	@Override
	public List<Product> findByCategory(ProductCategory idCategory, boolean visible) {
		Connection connection = this.dataSource.getConnection();
		List<Product> products = new ArrayList<Product>();
		try {
			Product product;
			PreparedStatement statement;
			String query = "select * from Product where category=? and visible = ?";
			statement = connection.prepareStatement(query);
			statement.setLong(1, idCategory.getId());
			statement.setBoolean(2, visible);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				product = new Product();
				product.setId(result.getLong("id"));				
				product.setName(result.getString("name"));
				product.setDescription(result.getString("description"));
				product.setPrice(result.getFloat("price"));
				product.setVisible(result.getBoolean("visible"));
				if(result.getBytes("image") != null) {
					product.setImage(result.getBytes("image"));
//					if(product.getImage() != null)
//						product.setImageString("data:image/jpeg;base64," + Base64.getEncoder().encodeToString(product.getImage()));
					
				}
				
				
				ProductCategoryDAO ProdCatDao = new ProductCategoryDaoJDBC(dataSource);
				product.setCategory(ProdCatDao.findById(result.getLong("category")));
				
				products.add(product);
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}	 finally {
			DAOUtility.close(connection);
		}
		return products;
	}

	@Override
	public List<Product> findAll(boolean visible) {
		Connection connection = this.dataSource.getConnection();
		List<Product> products = new ArrayList<Product>();
		try {
			Product product;
			PreparedStatement statement;
			String query = "select * from Product where visible = ?";
			statement = connection.prepareStatement(query);
			statement.setBoolean(1, visible);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				product = new Product();
				product.setId(result.getLong("id"));				
				product.setName(result.getString("name"));
				product.setDescription(result.getString("description"));
				product.setPrice(result.getFloat("price"));
				product.setVisible(result.getBoolean("visible"));
				
				if(result.getBytes("image") != null) {
					product.setImage(result.getBytes("image"));
//					if(product.getImage() != null)
//						product.setImageString("data:image/jpeg;base64," + Base64.getEncoder().encodeToString(product.getImage()));
					
				}
				
				
				ProductCategoryDAO ProdCatDao = new ProductCategoryDaoJDBC(dataSource);
				product.setCategory(ProdCatDao.findById(result.getLong("category")));
				
				products.add(product);
			}
		}
		catch (SQLException e){
			throw new PersistenceException(e.getMessage());
		}
		finally {
			DAOUtility.close(connection);
		}
		return products;
	}

	@Override
	public List<Product> findFromCategory(ProductCategory pc, boolean visible) {
		Connection connection = this.dataSource.getConnection();
		List<Product> products = new ArrayList<Product>();
		try {
			Product product;
			PreparedStatement statement;
			String query = "select * from product where category = ? and visible = ?";
			
			statement = connection.prepareStatement(query);
			statement.setLong(1, pc.getId());
			statement.setBoolean(2, visible);
			
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				product = new Product();
				product.setId(result.getLong("id"));				
				product.setName(result.getString("name"));
				product.setDescription(result.getString("description"));
				product.setPrice(result.getFloat("price"));
				product.setVisible(result.getBoolean("visible"));
				if(result.getBytes("image") != null) {
					product.setImage(result.getBytes("image"));
//					if(product.getImage() != null)
//						product.setImageString("data:image/jpeg;base64," + Base64.getEncoder().encodeToString(product.getImage()));
					
				}
				
				
				ProductCategoryDAO ProdCatDao = new ProductCategoryDaoJDBC(dataSource);
				product.setCategory(ProdCatDao.findById(result.getLong("category")));
				
				products.add(product);
			}
		}
		catch (SQLException e){
			throw new PersistenceException(e.getMessage());
		}
		finally {
			DAOUtility.close(connection);
		}
		return products;
	}


}
