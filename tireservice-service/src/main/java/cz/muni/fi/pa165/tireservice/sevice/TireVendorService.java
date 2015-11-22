package cz.muni.fi.pa165.tireservice.sevice;

import cz.muni.fi.pa165.tireservice.entity.TireVendor;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Service layer interface for entity TireVendor.
 *
 * @author Jan Tymel
 */
@Service
public interface TireVendorService {

    /**
     * Returns tire vendor with specified id.
     * @param id Id of tire vendor
     * @return Tire vendor with specified id
     */
    TireVendor findById(Long id);

    /**
     * Returns tire vendor with specified name.
     * @param tireVendorName Name of tire vendor
     * @return Tire vendor with specified name
     */
    TireVendor findByName(String tireVendorName);

    /**
     * Returns all tire vendors.
     * @return List of all tire vendors
     */
    List<TireVendor> findAll();

    /**
     * Creates new tire vendor.
     * @param tireVendor Tire vendor that should be created
     */
    void create(TireVendor tireVendor);

    /**
     * Removes specified tire vendor.
     * @param tireVendor Tire vendor that should be removed.
     */
    void remove(TireVendor tireVendor);

    /**
     * Updates specified tire vendor.
     * @param tireVendor Tire vendor that should be updated.
     * @return Updated tire vendor
     */
    TireVendor update(TireVendor tireVendor);
}
