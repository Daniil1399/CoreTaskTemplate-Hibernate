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
        Connection connect = Util.connection();
        try (Statement stat = connect.createStatement()) {
            connect.setAutoCommit(false);
            stat.executeUpdate("CREATE TABLE IF NOT EXISTS users " +
                    "(id INT not NULL AUTO_INCREMENT, " +
                    " name VARCHAR(50) NOT NULL, " +
                    " lastName VARCHAR (50) NOT NULL, " +
                    " age INT not NULL, " +
                    " PRIMARY KEY (id))");
            connect.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                connect.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            connect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        Connection connect = Util.connection();
        try (Statement statement = connect.createStatement()) {
            connect.setAutoCommit(false);

            statement.execute("DROP TABLE Users");
        } catch (SQLException ignored) {
            try {
                connect.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            connect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
//        String SQL = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
        Connection connect = Util.connection();
        try (PreparedStatement statement = connect.prepareStatement(
                "INSERT INTO users(name, lastname, age) VALUES (?,?,?)")) {
            connect.setAutoCommit(false);
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connect.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        try {
            connect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String SQL = "DELETE FROM users WHERE id=?";
        Connection connect = Util.connection();
        try (PreparedStatement preparedStatement = connect.prepareStatement(SQL)) {
            connect.setAutoCommit(false);
            preparedStatement.setLong(1, id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                connect.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            connect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Connection connect = Util.connection();
        try (Statement stat = connect.createStatement()) {
            connect.setAutoCommit(false);
            String SQL = "SELECT * FROM users";

            ResultSet rs = stat.executeQuery(SQL);

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastName"));
                user.setAge(rs.getByte("age"));
                users.add(user);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                connect.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            connect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        Connection connect = Util.connection();
        try (Statement stat = connect.createStatement()) {
            String SQL = "DELETE FROM users";
            stat.executeUpdate(SQL);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                connect.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            connect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
