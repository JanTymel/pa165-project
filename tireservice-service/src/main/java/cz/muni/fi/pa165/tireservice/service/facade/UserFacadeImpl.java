package cz.muni.fi.pa165.tireservice.service.facade;

import cz.muni.fi.pa165.tireservice.dto.UserCreateDto;
import cz.muni.fi.pa165.tireservice.dto.UserDto;
import cz.muni.fi.pa165.tireservice.entity.User;
import cz.muni.fi.pa165.tireservice.facade.UserFacade;
import cz.muni.fi.pa165.tireservice.sevice.BeanMappingService;
import cz.muni.fi.pa165.tireservice.sevice.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Jan Tymel
 */
public class UserFacadeImpl implements UserFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public Long createUser(UserCreateDto userCreateDto) {
        User user = new User();
        user.setAddress(userCreateDto.getAddress());
        user.setIsAdmin(userCreateDto.getIsAdmin());
        user.setName(userCreateDto.getName());
        user.setPhone(userCreateDto.getPhone());

        userService.create(user);
        return user.getId();
    }

    @Override
    public List<UserDto> getAllUsers() {
        return beanMappingService.mapTo(userService.findAll(), UserDto.class);
    }

    @Override
    public UserDto getUserById(Long userId) {
        return beanMappingService.mapTo(userService.findById(userId), UserDto.class);
    }

    @Override
    public List<UserDto> getUsersByName(String userName) {
        return beanMappingService.mapTo(userService.findByName(userName), UserDto.class);
    }

    @Override
    public List<UserDto> getUsersByAddress(String address) {
        return beanMappingService.mapTo(userService.findByAddress(address), UserDto.class);
    }

    @Override
    public UserDto getUserByPhone(String phone) {
        return beanMappingService.mapTo(userService.findByPhone(phone), UserDto.class);
    }

    @Override
    public List<UserDto> getAllAdmins() {
        return beanMappingService.mapTo(userService.findAllAdmins(), UserDto.class);
    }

    @Override
    public List<UserDto> getAllCustomers() {
        return beanMappingService.mapTo(userService.findAllCustomers(), UserDto.class);
    }

    @Override
    public void deleteUser(Long userId) {
        userService.remove(new User(userId));
    }

    @Override
    public void updateUser(UserDto userDto) {
        userService.update(beanMappingService.mapTo(userDto, User.class));
    }

}
