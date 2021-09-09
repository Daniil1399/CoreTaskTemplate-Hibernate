package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {
        UserDaoHibernateImpl ud = new UserDaoHibernateImpl();
//        ud.createUsersTable();
//        ud.saveUser("name", "lastname", (byte) 15);
//
//        Session session = Util.getSession();
//        User user = new User("Daniil", "Daniil", (byte) 22);
//        session.beginTransaction();
//        session.save(user);
//        session.getTransaction().commit();

        ud.dropUsersTable();
        ud.createUsersTable();

    }
}
