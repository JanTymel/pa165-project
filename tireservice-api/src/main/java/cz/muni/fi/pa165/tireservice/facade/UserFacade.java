package cz.muni.fi.pa165.tireservice.facade;

import cz.muni.fi.pa165.tireservice.dto.UserCreateDto;
import cz.muni.fi.pa165.tireservice.dto.UserDto;
import java.util.List;

/**
 *
 * @author Jan Tymel
 */
public interface UserFacade {

    /**
     * Creates new user.
     *
     * @param userCreateDto CreateDTO of new user
     * @return Id of newly created user.
     */
    public Long createUser(UserCreateDto userCreateDto);

    /**
     * Returns all users.
     *
     * @return List of all DTO users.
     */
    public List<UserDto> getAllUsers();

    /**
     * Returns user by specified id.
     *
     * @param userId Id of requested user.
     * @return DTO of user with specified id.
     */
    public UserDto getUserById(Long userId);

    /**
     * Returns users with specified name.
     *
     * @param userName name of user.
     * @return List of user DTOs with specified name.
     */
    public List<UserDto> getUsersByName(String userName);

    /**
     * Returns users living in specified address.
     *
     * @param address address of user.
     * @return List of user DTOs from specified address.
     */
    public List<UserDto> getUsersByAddress(String address);

    /**
     * Returns user by specified phone number.
     *
     * @param phone Phone number of requested user.
     * @return DTO of user with specified phone number.
     */
    public UserDto getUserByPhone(String phone);

    /**
     * Returns all users with admin rights.
     *
     * @return List of user DTOs with admin rights.
     */
    public List<UserDto> getAllAdmins();

    /**
     * Returns all users without admin rights, aka customers.
     *
     * @return List of user DTOs without admin rights.
     */
    public List<UserDto> getAllCustomers();

    /**
     * Deletes user with specified id.
     *
     * @param userId Id of user which should be removed.
     */
    public void deleteUser(Long userId);

    /**
     * Updates user.
     *
     * @param userDto DTO of updated user.
     */
    public void updateUser(UserDto userDto);
}
