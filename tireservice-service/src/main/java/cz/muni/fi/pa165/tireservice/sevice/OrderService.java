package cz.muni.fi.pa165.tireservice.sevice;

import cz.muni.fi.pa165.tireservice.entity.Order;
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
public interface OrderService {

    void createOrder(Order order);

    public List<Order> findAll();

    public List<Order> findByUser(User u);

    public List<Order> findByState(OrderState state);

    public Order findById(Long id);

    public List<Order> findByCarType(CarType carType);

    public List<Order> getOrdersCreatedBetween(Date start,Date end);

    public void startProcessingOrder(Long id);

    public void finishOrder(Long id);

    public void cancelOrder(Long id);

    public BigDecimal getOrderTotalPrice(long orderId);

    public List<Order> getOrderLastWeek();
}
