package cz.muni.fi.pa165.tireservice.dao;

import cz.muni.fi.pa165.tireservice.entity.Order;
import cz.muni.fi.pa165.tireservice.entity.User;
import cz.muni.fi.pa165.tireservice.enums.OrderState;
import java.util.List;

/**
 *
 * @author Jan Tymel
 */
public interface CustomerDao {

    /**
     * Sends new order to tire service.
     * @param order Order to be send to tire service
     * @throws IllegalStateException if order cannot be accepted due to illegal state of order
     */
    public void sendOrder(Order order) throws IllegalStateException;

    /**
     * Cancels existing order in tire service. It can be done only if the order hasn't been started processing yet.
     * @param order Order to be cancelled
     */
    public void cancelOrder(Order order);

    /**
     * Edits existing order. Order can be changed only if it hasn't been started processing yet.
     * @param order
     * @return Edited order
     */
    public Order editOrder(Order order);

    /**
     * Returns all orders customer made.
     * Returns empty list if the customer hasn't created any order yet.
     * @param customer customer
     * @return List of customer's orders
     */
    public List<Order> getOrders(User customer);

    /**
     * Returns all customer's orders in given state. Returns empty list if the customer hasn't created any order yet or if
     * there is no order in such state.
     * @param customer Customer
     * @param orderState State of orders
     * @return List of orders in given state
     */
    public List<Order> getOrders(User customer, OrderState orderState);
}
