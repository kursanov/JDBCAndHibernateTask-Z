package peaksoft.service;

import peaksoft.dao.UserDao;
import peaksoft.dao.UserDaoHibernateImpl;
import peaksoft.dao.UserDaoJdbcImpl;
import peaksoft.model.User;

import java.util.List;
public class UserServiceImpl implements UserService {

    private final UserDao dao = new  UserDaoJdbcImpl();

    private final UserDao userDao = new UserDaoHibernateImpl();




    @Override
    public void createUsersTable() {
        dao.createUsersTable();
//        userDao.createUsersTable();


    }

    public void dropUsersTable() {
        dao.dropUsersTable();
//        userDao.dropUsersTable();

    }

    public void saveUser(String name, String lastName, byte age) {
        dao.saveUser(name,lastName,age);
//        userDao.saveUser(name,lastName,age);

    }

    public void removeUserById(long id) {
        dao.removeUserById(id);
//        userDao.removeUserById(id);

    }

    public List<User> getAllUsers() {
        return dao.getAllUsers() ;
    }

    public void cleanUsersTable() {
        dao.cleanUsersTable();
    }
}
