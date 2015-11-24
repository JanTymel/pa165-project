package cz.muni.fi.pa165.tireservice.dao;

import cz.muni.fi.pa165.tireservice.entity.User;
import java.util.List;

/**
 *
 * @author Jan Tymel
 */
public interface UserDao {

    public void create(User u);

    public User findById(Long id);

    public List<User> findUsersByName(String name);

    public List<User> findAll();

    public void remove(User user);

    public User update(User user);

    public List<User> findByAddress(String address);

    public User findByPhone(String phone);

    public List<User> findAllAdmins();

    public List<User> findAllCustomers();

}
