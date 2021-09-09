package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/mybd";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "Seven1941";

    private static Connection connect;

    public static synchronized Connection connection() {
        if (connect == null) {
            try {
                Connection connect = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connect;
    }

    private static SessionFactory concreteSessionFactory;
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";


    public static Session getSession() {

        if (concreteSessionFactory == null) {
            try {
                Properties prop = new Properties();
                prop.setProperty(Environment.URL, URL);
                prop.setProperty(Environment.USER, LOGIN);
                prop.setProperty(Environment.PASS, PASSWORD);
                prop.setProperty(Environment.DRIVER, DRIVER);

                concreteSessionFactory = new org.hibernate.cfg.Configuration()
                        .addProperties(prop)
                        .addAnnotatedClass(User.class)
                        .buildSessionFactory();
            } catch (Exception ex) {

            }
        }
        return concreteSessionFactory.openSession();
    }

}
