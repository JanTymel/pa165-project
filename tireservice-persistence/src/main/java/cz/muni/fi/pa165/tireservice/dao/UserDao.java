package cz.muni.fi.pa165.tireservice.dao;

import cz.muni.fi.pa165.tireservice.entity.User;
import java.util.List;

/**
 *
 * @author Jan Tymel
 */
public interface UserDao {

    /**
     * Creates new user.
     *
     * @param user User that should be created
     */
    public void create(User user);

    /**
     * Returns user with specified id.
     *
     * @param id Id of user
     * @return User with specified id
     */
    public User findById(Long id);

    /**
     * Returns user with specified name.
     *
     * @param name Name of user
     * @return User with specified name
     */
    public List<User> findByName(String name);

    /**
     * Returns all users.
     *
     * @return All users
     */
    public List<User> findAll();

    /**
     * Removes specified user.
     *
     * @param user User that should be removed
     */
    public void remove(User user);

    /**
     * Updates specified user.
     *
     * @param user User that should be updated
     * @return Updated user
     */
    public User update(User user);

    /**
     * Returns all users living in specified address.
     *
     * @param address Address
     * @return All users with specified address
     */
    public List<User> findByAddress(String address);

    /**
     * Returns user with specified phone number.
     *
     * @param phone Phone number
     * @return User with specified phone number
     */
    public User findByPhone(String phone);

    /**
     * Returns all users with admin rights.
     *
     * @return All users with admin rights
     */
    public List<User> findAllAdmins();

    /**
     * Returns all customers aka all users without admin rights.
     *
     * @return All customers
     */
    public List<User> findAllCustomers();

}
