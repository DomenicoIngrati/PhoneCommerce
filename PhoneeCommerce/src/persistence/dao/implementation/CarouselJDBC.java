package persistence.dao.implementation;

import model.Carousel;
import persistence.dao.CarouselDAO;
import persistence.util.DAOUtility;
import persistence.util.DataSource;
import persistence.util.IdBroker;
import persistence.util.PersistenceException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarouselJDBC implements CarouselDAO {

    private DataSource dataSource;

    public CarouselJDBC(DataSource dataSource) {
        this.dataSource=dataSource;
    }

    @Override
    public boolean create(Carousel c) {
        Connection connection = dataSource.getConnection();;
        String query;
        PreparedStatement statement;
        try {
            long id = IdBroker.getId(connection);
            c.setId(id);

            query = "insert into carousel (title, description, image, id) values (?,?,?,?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, c.getTitle());
            statement.setString(2, c.getDescription());
            statement.setBytes(3, c.getImage());
            statement.setLong(4, c.getId());

            return  (statement.executeUpdate() > 0) ? true : false;

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            DAOUtility.close(connection);
        }
        return false;
    }

    @Override
    public Carousel findById(long id) {
        Connection connection = dataSource.getConnection();;
        String query;
        PreparedStatement statement;
        Carousel carousel = null;
        try {
            query = "select * from carousel where id = ?";
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);

            ResultSet result = statement.executeQuery();
            if (result.next()) {
                carousel = new Carousel();
                carousel.setId(result.getLong("id"));
                carousel.setTitle(result.getString("title"));
                carousel.setDescription(result.getString("description"));
                carousel.setImage(result.getBytes("image"));
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            DAOUtility.close(connection);
        }
        return carousel;
    }

    @Override
    public List<Carousel> findAll() {
        Connection connection = dataSource.getConnection();;
        String query;
        PreparedStatement statement;
        List <Carousel> carousels = new ArrayList<Carousel>();
        try {
            query = "select * from carousel";
            statement = connection.prepareStatement(query);

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Carousel carousel = new Carousel();
                carousel.setId(result.getLong("id"));
                carousel.setTitle(result.getString("title"));
                carousel.setDescription(result.getString("description"));
                carousel.setImage(result.getBytes("image"));

                carousels.add(carousel);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            DAOUtility.close(connection);
        }
        return carousels;
    }

    @Override
    public boolean updateTexts(Carousel c) {
        Connection connection = dataSource.getConnection();;
        String query;
        PreparedStatement statement;
        try {
            query = "update carousel SET title = ?, description = ?  WHERE id=?";

            statement = connection.prepareStatement(query);
            statement.setString(1, c.getTitle());
            statement.setString(2, c.getDescription());
            statement.setLong(3, c.getId());

            return (statement.executeUpdate() > 0) ? true : false;

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            DAOUtility.close(connection);
        }
        return false;
    }

    @Override
    public boolean updateImage(Carousel c) {
        Connection connection = dataSource.getConnection();;
        String query;
        PreparedStatement statement;
        try {
            query = "update carousel SET image = ?  WHERE id=?";

            statement = connection.prepareStatement(query);
            statement.setBytes(1, c.getImage());
            statement.setLong(2, c.getId());

            return (statement.executeUpdate() > 0) ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            DAOUtility.close(connection);
        }
        return false;
    }

    @Override
    public boolean delete(long c) {
        Connection connection = this.dataSource.getConnection();
        try {
            String delete = "delete FROM carousel WHERE id = ? ";
            PreparedStatement statement = connection.prepareStatement(delete);
            statement.setLong(1, c);

            return (statement.executeUpdate() > 0) ? true : false;
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        } finally {
            DAOUtility.close(connection);
        }
    }
}
