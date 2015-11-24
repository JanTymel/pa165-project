package cz.muni.fi.pa165.tireservice.sevice;

import cz.muni.fi.pa165.tireservice.entity.User;
import java.util.List;

/**
 *
 * @author Jan Tymel
 */
public interface UserService {

    /**
     * Returns user with specified id.
     *
     * @param id Id of user
     * @return User with specified id
     */
    User findById(Long id);

    /**
     * Returns users with specified name.
     *
     * @param userName Name of user
     * @return List of users with specified name
     */
    List<User> findByName(String userName);

    /**
     * Returns all users.
     *
     * @return List of all users
     */
    List<User> findAll();

    /**
     * Creates new user.
     *
     * @param user User that should be created
     */
    void create(User user);

    /**
     * Removes specified user.
     *
     * @param user User that should be removed.
     */
    void remove(User user);

    /**
     * Updates specified user.
     *
     * @param user User that should be updated.
     * @return Updated user
     */
    User update(User user);

    /**
     * Returns users living in specified address.
     *
     * @param address address of user.
     * @return List of users from specified address.
     */
    public List<User> findByAddress(String address);

    /**
     * Returns user by specified phone number.
     *
     * @param phone Phone number of requested user.
     * @return User with specified phone number.
     */
    public User findByPhone(String phone);

    /**
     * Returns all users with admin rights.
     *
     * @return List of users with admin rights.
     */
    public List<User> findAllAdmins();

    /**
     * Returns all users without admin rights, aka customers.
     *
     * @return List of users without admin rights.
     */
    public List<User> findAllCustomers();
}
