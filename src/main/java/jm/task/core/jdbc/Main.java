package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {
        UserDaoJDBCImpl ud = new UserDaoJDBCImpl();
        ud.createUsersTable();
        ud.saveUser("name", "lastname", (byte) 15);
    }
}
