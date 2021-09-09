package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = Util.getSession()) {
            session.beginTransaction();
            String SQL = "CREATE TABLE IF NOT EXISTS users " +
                    "(id INT not NULL AUTO_INCREMENT, " +
                    " name VARCHAR(50) NOT NULL, " +
                    " lastName VARCHAR (50) NOT NULL, " +
                    " age INT not NULL, " +
                    " PRIMARY KEY (id))";
            session.createSQLQuery(SQL).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {

        }


    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSession()) {
            session.beginTransaction();
            String SQL = "DROP TABLE Users";
            session.createSQLQuery(SQL).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {

        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSession()) {
            User user = new User(name, lastName, age);
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {

        }

    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e) {

        }

    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = null;
        try (Session session = Util.getSession()) {
            session.beginTransaction();
            users = session.createQuery("from User").getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {

        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSession()) {
            session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {

        }

    }
}
