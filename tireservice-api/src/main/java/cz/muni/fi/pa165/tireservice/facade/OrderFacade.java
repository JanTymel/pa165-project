package cz.muni.fi.pa165.tireservice.facade;

import cz.muni.fi.pa165.tireservice.dto.OrderDto;
import cz.muni.fi.pa165.tireservice.enums.CarType;
import cz.muni.fi.pa165.tireservice.enums.OrderState;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Samuel Baniar
 */
public interface OrderFacade {
    public List<OrderDto> getAllOrders();
    public List<OrderDto> getOrdersByUser(Long userId);
    public List<OrderDto> getOrdersByState(OrderState state);
    public OrderDto getOrderById(Long id);
    public List<OrderDto> findByCarType(CarType ct);
    public List<OrderDto> getOrdersCreatedBetween(Date start,Date end);
    public void startProcessingOrder(Long id);
    public void finishOrder(Long id);
    public void cancelOrder(Long id);
    public BigDecimal getOrderTotalPrice(long id);
}
