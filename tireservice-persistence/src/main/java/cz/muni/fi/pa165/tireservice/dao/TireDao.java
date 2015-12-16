package cz.muni.fi.pa165.tireservice.dao;

import cz.muni.fi.pa165.tireservice.entity.Tire;
import cz.muni.fi.pa165.tireservice.entity.TireVendor;
import cz.muni.fi.pa165.tireservice.enums.CarType;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Samuel Baniar
 */
public interface TireDao {

    /**
     * Creates new tire.
     *
     * @param tire Tire that should be creates
     */
    public void create(Tire tire);

    /**
     * Returns tire with specified id.
     *
     * @param id ID of tire
     * @return Tire with specified id
     */
    public Tire findById(Long id);

    /**
     * Returns all tires.
     *
     * @return All tires
     */
    public List<Tire> findAll();

    /**
     * Removes specified tire.
     *
     * @param tire Tire that should be removed
     */
    public void remove(Tire tire);

    /**
     * Returns all tires with specified name.
     *
     * @param name Name of tire
     * @return All tires with specified name
     */
    public List<Tire> findByName(String name);

    /**
     * Returns all tires for specified car type.
     *
     * @param carType Car type
     * @return All tires for specified car type
     */
    public List<Tire> findByCarType(CarType carType);

    /**
     * Returns all tires with specified diameter.
     *
     * @param diameter Diameter of tire
     * @return All tires with specified diameter
     */
    public List<Tire> findByDiameter(int diameter);

    /**
     * Returns all tires with specified width.
     *
     * @param width Width of tire
     * @return All tires with specified width
     */
    public List<Tire> findByWidth(int width);

    /**
     * Returns all tires from specified tire vendor.
     *
     * @param tireVendor Tire vendor
     * @return All tires produced by specified tire vendor
     */
    public List<Tire> findByTireVendor(TireVendor tireVendor);

    /**
     * Returns all tires with specified speed index.
     *
     * @param speedIndex Speed index
     * @return All tires with specified speed index
     */
    public List<Tire> findBySpeedIndex(String speedIndex);

    /**
     * Returns all tires with price greater than or equal to leftLimit and less than rightLimit.
     *
     * @param leftLimit Lower bound of price
     * @param rightLimit Upper bound of price
     * @return All tires in specified price limit
     */
    public List<Tire> getTiresWithPriceBetween(BigDecimal leftLimit, BigDecimal rightLimit);

    /**
     * Updates specified tire.
     *
     * @param tire Tire that should be updated
     * @return Updated tire
     */
    public Tire update(Tire tire);
}
