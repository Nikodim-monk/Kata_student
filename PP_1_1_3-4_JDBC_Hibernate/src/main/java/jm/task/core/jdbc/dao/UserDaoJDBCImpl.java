package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Connection connection = Util.mineConnection()) {
            connection.createStatement().executeUpdate("DROP TABLE IF EXISTS users");
            connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS users " +
                    "(UserID INT PRIMARY KEY AUTO_INCREMENT, FirstName varchar(15),LastName varchar(15), Age int)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.mineConnection()) {
            connection.createStatement().executeUpdate("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.mineConnection()) {
            DatabaseMetaData meta = connection.getMetaData();
            ResultSet resultSet = meta.getTables(null, null, "users", new String[]{"TABLE"});
            if (resultSet.next()) {
                connection.createStatement().executeUpdate("INSERT users(FirstName, LastName, Age) VALUES ('"
                        + name + "','" + lastName + "'," + age + ")");
                System.out.println("User с именем Ц " + name + " добавлен в базу данных");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.mineConnection()) {
            DatabaseMetaData meta = connection.getMetaData();
            ResultSet resultSet = meta.getTables(null, null, "users", new String[]{"TABLE"});
            if (resultSet.next()) {
                connection.createStatement().executeUpdate("DELETE FROM users WHERE UserID = " + id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.mineConnection()) {
            DatabaseMetaData meta = connection.getMetaData();
            ResultSet resultSet = meta.getTables(null, null, "users", new String[]{"TABLE"});
            if (resultSet.next()) {
                connection.createStatement().executeUpdate("DELETE FROM Users");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = Util.mineConnection()) {
            DatabaseMetaData meta = connection.getMetaData();
            ResultSet resultSet = meta.getTables(null, null, "users", new String[]{"TABLE"});
            if (resultSet.next()) {
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users");
                ResultSet rS = preparedStatement.executeQuery();
                while (rS.next()) {
                    users.add(new User(rS.getString(2), rS.getString(3), (byte) rS.getInt(4)));
                }
                System.out.println(users);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }
}
