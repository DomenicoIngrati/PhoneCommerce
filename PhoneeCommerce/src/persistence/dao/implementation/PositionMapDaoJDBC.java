package persistence.dao.implementation;

import model.Address;
import model.PositionMap;
import persistence.dao.PositionMapDAO;
import persistence.dao.UserDAO;
import persistence.util.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PositionMapDaoJDBC implements PositionMapDAO {


    private DataSource dataSource;

    public PositionMapDaoJDBC(DataSource dataSource) {
        this.dataSource=dataSource;
    }

    @Override
    public boolean create(PositionMap a) {
        Connection connection = dataSource.getConnection();;
        String query;
        PreparedStatement statement;
        try {

            long id = IdBroker.getId(connection);
            a.setId(id);
            query = "insert into posistionmap (id, latitude, longitude) values (?,?,?)";
            statement = connection.prepareStatement(query);
            statement.setDouble(2, a.getLatitude());
            statement.setDouble(3, a.getLongitude());
            statement.setLong(1, a.getId());


            return (statement.executeUpdate() > 0) ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            DAOUtility.close(connection);
        }
        return false;
    }

    @Override
    public boolean update(PositionMap a) {
        Connection connection = this.dataSource.getConnection();
        try {

            String update = "update positionmap SET latitude = ?, longitude = ? WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(update);
            statement = connection.prepareStatement(update);
            statement.setDouble(1, a.getLatitude());
            statement.setDouble(2, a.getLongitude());

            statement.setLong(3, a.getId());


            return (statement.executeUpdate() > 0) ? true : false;
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        } finally {
            DAOUtility.close(connection);
        }
    }

    @Override
    public boolean delete(PositionMap a) {
        Connection connection = this.dataSource.getConnection();
        try {
            String delete = "delete FROM positionmap WHERE id = ? ";
            PreparedStatement statement = connection.prepareStatement(delete);
            statement.setLong(1, a.getId());
            return (statement.executeUpdate() > 0) ? true : false;
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        } finally {
            DAOUtility.close(connection);
        }
    }

    @Override
    public PositionMap findById(Long id) {
        Connection connection = this.dataSource.getConnection();
        PositionMap u = null;
        try {
            PreparedStatement statement;
            String query = "select * from positionmap where id = ?";
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                u = new PositionMap();
                u.setId(result.getLong("id"));
                u.setLatitude(result.getDouble("latitude"));
                u.setLongitude(result.getDouble("longitude"));
            }
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        } finally {
            DAOUtility.close(connection);
        }
        return u;
    }

    @Override
    public List<PositionMap> findAll() {
        Connection connection = this.dataSource.getConnection();
        List<PositionMap> positions = new ArrayList<PositionMap>();
        PositionMap u = null;
        try {
            PreparedStatement statement;
            String query = "select * from positionmap";
            statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                u = new PositionMap();
                u.setId(result.getLong("id"));
                u.setLongitude(result.getDouble("longitude"));
                u.setLatitude(result.getDouble("latitude"));

                positions.add(u);
            }
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        } finally {
            DAOUtility.close(connection);
        }
        return positions;
    }
}
