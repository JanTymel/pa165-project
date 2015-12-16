package cz.muni.fi.pa165.tireservice.dao;

import cz.muni.fi.pa165.tireservice.entity.Order;
import cz.muni.fi.pa165.tireservice.entity.User;
import cz.muni.fi.pa165.tireservice.enums.CarType;
import cz.muni.fi.pa165.tireservice.enums.OrderState;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Samuel Baniar
 */
public interface OrderDao {

    /**
     * Creates new order.
     *
     * @param order Order that should be created
     */
    public void create(Order order);

    /**
     * Returns order with specified id.
     *
     * @param id Id of order
     * @return Order with specified id
     */
    public Order findById(Long id);

    /**
     * Returns all orders.
     *
     * @return All orders
     */
    public List<Order> findAll();

    /**
     * Removes specified order.
     *
     * @param order Order that should be removed
     */
    public void remove(Order order);

    /**
     * Returns all orders that specified user created.
     *
     * @param user Customer
     * @return All orders that customer created
     */
    public List<Order> findByUser(User user);

    /**
     * Returns all orders in specified state.
     *
     * @param orderState State of order
     * @return All orders in specified state
     */
    public List<Order> findByState(OrderState orderState);

    /**
     * Returns all orders for specified car type.
     *
     * @param carType Car type
     * @return All orders for specified car type
     */
    public List<Order> findByCarType(CarType carType);

    /**
     * Returns all orders created in specified time period.
     *
     * @param start Lower bound aka "from" or "start"
     * @param end Upper bound aka "to" or "end"
     * @return All orders created in specified time period
     */
    public List<Order> getOrdersCreatedBetween(Date start, Date end);

    public Order update(Order order);
}
