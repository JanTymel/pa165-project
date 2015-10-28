package cz.muni.fi.pa165.tireservice.dao;

import cz.muni.fi.pa165.tireservice.entity.Service;
import cz.muni.fi.pa165.tireservice.entity.Tire;
import cz.muni.fi.pa165.tireservice.enums.CarType;
import cz.muni.fi.pa165.tireservice.enums.OrderState;
import java.math.BigDecimal;

/**
 *
 * @author Samuel Baniar
 */
public interface OrderDao {

    /**
     * Adds new Tire to order
     * @param tire
     */
    public void addTire(Tire tire);

    /**
     * Adds new Service to order
     * @param service Service to be added
     */
    public void addService(Service service);

    /**
     * Removes Tire from order if exists
     * @param tire Tire to be added
     * @return True if Tire is in the order and can be removed, False if not
     */
    public boolean removeTire(Tire tire);

    /**
     * Removes Service from order if exists
     * @param service Service to be removed
     * @return True if service is in the order and can be removed, False if not
     */
    public boolean removeService(Service service);

    /**
     * Edits state of order
     * @param state Change order to this state
     * @return True if change is possible, False if not
     */
    public boolean editState(OrderState state);

    /**
     * Edits carType of order
     * @param carType Change carType to this carType
     * @return True if change is possible, False if not
     */
    public boolean editCarType(CarType carType);

    /**
     * Gets total price of whole order including Tires and Services
     * @return Total price of order
     */
    public BigDecimal getTotalOrderPrice();
}
