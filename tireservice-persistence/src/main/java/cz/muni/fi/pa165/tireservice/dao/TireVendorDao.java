/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.tireservice.dao;

import cz.muni.fi.pa165.tireservice.entity.TireVendor;
import java.util.List;

/**
 *
 * @author Samuel Baniar
 */
public interface TireVendorDao {

    /**
     * Creates new tire vendor.
     *
     * @param tireVendor Tire vendor that should be created
     */
    public void create(TireVendor tireVendor);

    /**
     * Returns tire vendor with specified id.
     *
     * @param id Id of tire vendor
     * @return Tire vendor with specified id
     */
    public TireVendor findById(Long id);

    /**
     * Returns all tire vendors.
     *
     * @return All tire vendors
     */
    public List<TireVendor> findAll();

    /**
     * Removes specified tire vendor.
     *
     * @param tireVendor Tire vendor that should be removed
     */
    public void remove(TireVendor tireVendor);

    /**
     * Returns tire vendor with specified name.
     *
     * @param name Name of tire vendor
     * @return Tire vendor with specified name
     */
    public TireVendor findByName(String name);

    /**
     * Updates specified tire vendor.
     *
     * @param tireVendor Tire vendor that should be updated
     * @return Updated tire vendor
     */
    public TireVendor update(TireVendor tireVendor);
}
