package peaksoft.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import peaksoft.model.User;
import peaksoft.util.Util;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {



    @Override
    public void createUsersTable() {
        try {
            Session session = Util.getSession().openSession();
            Transaction transaction = session.beginTransaction();
            String query = """
             CREATE TABLE "users" (
             id SERIAL PRIMARY KEY,
             first_name VARCHAR,
             last_name VARCHAR,
             age SMALLINT )""";
            session.createSQLQuery(query).executeUpdate();

            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            Session session = Util.getSession().openSession();
            Transaction transaction = session.beginTransaction();
            String query = "DROP TABLE IF EXISTS users";
            session.createSQLQuery(query).executeUpdate();
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            Session session = Util.getSession().openSession();
            Transaction transaction = session.beginTransaction();
            String query = """
                insert into users(first_name,last_name,age)
                values(?,?,?)
                    """;
            session.createQuery(query).executeUpdate();
            System.out.println("User : " + name + " success added!" );
            transaction.commit();
            session.close();

        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void removeUserById(long id) {
        try {
            Session session = Util.getSession().openSession();
            Transaction transaction = session.beginTransaction();
            String query = """
           delete from users where id = ?
""";
            session.createQuery(query).executeUpdate();
            transaction.commit();
            session.close();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }


    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try {

            Session session = Util.getSession().openSession();
            Transaction transaction = session.beginTransaction();
            String query = "select * from users u";
            list = session.createQuery(query, User.class).getResultList();
            transaction.commit();
            session.close();


        }catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        try {
            Session session = Util.getSession().openSession();
            Transaction transaction = session.beginTransaction();
            String query = "delete from users";
            session.createQuery(query).executeUpdate();
            transaction.commit();
            session.close();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }

    }
}
