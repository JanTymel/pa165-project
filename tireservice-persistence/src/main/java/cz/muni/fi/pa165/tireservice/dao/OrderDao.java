package cz.muni.fi.pa165.tireservice.dao;

import cz.muni.fi.pa165.tireservice.entity.Order;
import cz.muni.fi.pa165.tireservice.entity.Service;
import cz.muni.fi.pa165.tireservice.entity.Tire;
import cz.muni.fi.pa165.tireservice.entity.User;
import cz.muni.fi.pa165.tireservice.enums.CarType;
import cz.muni.fi.pa165.tireservice.enums.OrderState;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Samuel Baniar
 */
public interface OrderDao {
    public void create(Order o);
    public Order findById(Long id);
    public List<Order> findAll();
    public void remove(Order o) throws IllegalArgumentException;
    public List<Order> findByUser(User u);
    public List<Order> findByState(OrderState state);
    public List<Order> findByCarType(CarType ct);
    public List<Order> getOrdersCreatedBetween(Date start,Date end);
}
