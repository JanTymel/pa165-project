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
    public void create(TireVendor tv);
    public TireVendor findById(Long id);
    public List<TireVendor> findAll();
    public void remove(TireVendor tv) throws IllegalArgumentException;
    public TireVendor findByName(String namePattern);
    public TireVendor update(TireVendor tireVendor);
}
