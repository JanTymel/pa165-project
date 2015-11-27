package cz.muni.fi.pa165.tireservice.sevice;

import cz.muni.fi.pa165.tireservice.dao.UserDao;
import cz.muni.fi.pa165.tireservice.entity.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Jan Tymel
 */
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public List<User> findByName(String userName) {
        return userDao.findByName(userName);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public void create(User user) {
        userDao.create(user);
    }

    @Override
    public void remove(User user) {
        userDao.remove(user);
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }

    @Override
    public List<User> findByAddress(String address) {
        return userDao.findByAddress(address);
    }

    @Override
    public User findByPhone(String phone) {
        return userDao.findByPhone(phone);
    }

    @Override
    public List<User> findAllAdmins() {
        return userDao.findAllAdmins();
    }

    @Override
    public List<User> findAllCustomers() {
        return userDao.findAllCustomers();
    }

}
