package cz.muni.fi.pa165.tireservice.service.facade;

import cz.muni.fi.pa165.tireservice.dto.OrderDto;
import cz.muni.fi.pa165.tireservice.entity.User;
import cz.muni.fi.pa165.tireservice.enums.CarType;
import cz.muni.fi.pa165.tireservice.enums.OrderState;
import cz.muni.fi.pa165.tireservice.facade.OrderFacade;
import cz.muni.fi.pa165.tireservice.sevice.BeanMappingService;
import cz.muni.fi.pa165.tireservice.sevice.OrderService;
import cz.muni.fi.pa165.tireservice.sevice.UserService;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Samuel Baniar
 */
@org.springframework.stereotype.Service
@Transactional
public class OrderFacadeImpl implements OrderFacade{

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public List<OrderDto> getAllOrders() {
        return beanMappingService.mapTo(orderService.findAll(), OrderDto.class);
    }

    @Override
    public List<OrderDto> getOrdersByUser(Long userId) {
        User user = userService.findById(userId);
        return beanMappingService.mapTo(orderService.findByUser(user), OrderDto.class);
    }

    @Override
    public List<OrderDto> getOrdersByState(OrderState state) {
        return beanMappingService.mapTo(orderService.findByState(state), OrderDto.class);
    }

    @Override
    public OrderDto getOrderById(Long id) {
        return beanMappingService.mapTo(orderService.findById(id), OrderDto.class);
    }

    @Override
    public List<OrderDto> findByCarType(CarType ct) {
        return beanMappingService.mapTo(orderService.findByCarType(ct), OrderDto.class);
    }

    @Override
    public List<OrderDto> getOrdersCreatedBetween(Date start, Date end) {
        return beanMappingService.mapTo(orderService.getOrdersCreatedBetween(start, end), OrderDto.class);
    }

    @Override
    public void startProcessingOrder(Long id) {
        orderService.startProcessingOrder(orderService.findById(id));
    }

    @Override
    public void finishOrder(Long id) {
        orderService.finishOrder(orderService.findById(id));
    }

    @Override
    public void cancelOrder(Long id) {
        orderService.cancelOrder(orderService.findById(id));
    }

    @Override
    public BigDecimal getOrderTotalPrice(long id) {
        return orderService.getOrderTotalPrice(id);
    }

}
