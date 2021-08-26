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
        try (Connection connect = Util.connection();
             Statement stat = connect.createStatement()) {
            stat.executeUpdate("CREATE TABLE IF NOT EXISTS users " +
                    "(id INT not NULL AUTO_INCREMENT, " +
                    " name VARCHAR(50) NOT NULL, " +
                    " lastName VARCHAR (50) NOT NULL, " +
                    " age INT not NULL, " +
                    " PRIMARY KEY (id))");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.connection(); Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE Users");
        } catch (SQLException ignored) {

        }

    }

    public void saveUser(String name, String lastName, byte age) {
//        String SQL = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
        try (Connection con = Util.connection()) {
            PreparedStatement statement = con.prepareStatement(
                    "INSERT INTO users(name, lastname, age) VALUES (?,?,?)");
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String SQL = "DELETE FROM users WHERE id=?";
        try (Connection connect = Util.connection();
             PreparedStatement preparedStatement = connect.prepareStatement(SQL)) {
            preparedStatement.setLong(1, id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connect = Util.connection();
             Statement stat = connect.createStatement()){
            String SQL = "SELECT * FROM users";

            ResultSet rs = stat.executeQuery(SQL);

            while(rs.next()){
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastName"));
                user.setAge(rs.getByte("age"));
                users.add(user);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Connection connect = Util.connection();
             Statement stat = connect.createStatement()) {
            String SQL = "DELETE FROM users";
            stat.executeUpdate(SQL);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}
