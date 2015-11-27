package cz.muni.fi.pa165.tireservice.sevice;

import cz.muni.fi.pa165.tireservice.entity.Order;
import cz.muni.fi.pa165.tireservice.entity.User;
import cz.muni.fi.pa165.tireservice.enums.CarType;
import cz.muni.fi.pa165.tireservice.enums.OrderState;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Samuel Baniar
 */

@Service
public interface OrderService {
    
    void createOrder(Order order);
    
    public List<Order> findAll();
    
    public List<Order> findByUser(User u);
    
    public List<Order> findByState(OrderState state);
    
    public Order findById(Long id);
    
    public List<Order> findByCarType(CarType carType);
    
    public List<Order> getOrdersCreatedBetween(Date start,Date end);
    
    public void startProcessingOrder(Order order);
    
    public void finishOrder(Order order);
    
    public void cancelOrder(Order order);
    
    public BigDecimal getOrderTotalPrice(long orderId);
}
