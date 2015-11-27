package cz.muni.fi.pa165.tireservice.sevice;

import cz.muni.fi.pa165.tireservice.dao.OrderDao;
import cz.muni.fi.pa165.tireservice.entity.Order;
import cz.muni.fi.pa165.tireservice.entity.Service;
import cz.muni.fi.pa165.tireservice.entity.Tire;
import cz.muni.fi.pa165.tireservice.entity.User;
import cz.muni.fi.pa165.tireservice.enums.CarType;
import cz.muni.fi.pa165.tireservice.enums.OrderState;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Samuel Baniar
 */
@org.springframework.stereotype.Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public void createOrder(Order order) {
        orderDao.create(order);
    }

    @Override
    public List<Order> findAll() {
        return orderDao.findAll();
    }

    @Override
    public List<Order> findByUser(User u) {
        return orderDao.findByUser(u);
    }

    @Override
    public List<Order> findByState(OrderState state) {
        return orderDao.findByState(state);
    }

    @Override
    public Order findById(Long id) {
        return orderDao.findById(id);
    }

    @Override
    public List<Order> findByCarType(CarType carType) {
        return orderDao.findByCarType(carType);
    }

    @Override
    public List<Order> getOrdersCreatedBetween(Date start, Date end) {
        return orderDao.getOrdersCreatedBetween(start, end);
    }

    @Override
    public void startProcessingOrder(Long id) {
        Order order = orderDao.findById(id);
        order.setState(OrderState.IN_PROGRESS);

        orderDao.update(order);
    }

    @Override
    public void finishOrder(Long id) {
        Order order = orderDao.findById(id);
        order.setState(OrderState.COMPLETED);

        orderDao.update(order);
    }

    @Override
    public void cancelOrder(Long id) {
        Order order = orderDao.findById(id);
        order.setState(OrderState.CANCELLED);

        orderDao.update(order);
    }

    @Override
    public BigDecimal getOrderTotalPrice(long orderId) {
        Order order = orderDao.findById(orderId);

        BigDecimal totPrice = BigDecimal.ZERO;

        for (Tire tire : order.getTires()) {
            totPrice.add(tire.getPrice());
        }

        for (Service service : order.getServices()) {
            totPrice.add(service.getPrice());
        }

        return totPrice;
    }

    @Override
    public List<Order> getOrderLastWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        Date today = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, -7);
        Date lastWeek = calendar.getTime();

        List<Order> orders = orderDao.getOrdersCreatedBetween(lastWeek, today);
        return orders;
    }

}
