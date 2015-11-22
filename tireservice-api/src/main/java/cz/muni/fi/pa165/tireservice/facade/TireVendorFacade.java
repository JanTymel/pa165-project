package cz.muni.fi.pa165.tireservice.facade;

import cz.muni.fi.pa165.tireservice.dto.TireVendorCreateDto;
import cz.muni.fi.pa165.tireservice.dto.TireVendorDto;
import java.util.List;

/**
 * Facade layer interface for TireVendor entity.
 *
 * @author Jan Tymel
 */
public interface TireVendorFacade {

    /**
     * Creates new tire vendor.
     *
     * @param tireVendorCreateDto CreateDTO of new tire vendor
     * @return Id of newly created tire vendor.
     */
    public Long createTireVendor(TireVendorCreateDto tireVendorCreateDto);

    /**
     * Returns all tire vendors.
     *
     * @return List of all DTO tire vendors.
     */
    public List<TireVendorDto> getAllTireVendors();

    /**
     * Returns tire vendor by specified id.
     *
     * @param tireVendorId Id of requested tire vendor.
     * @return DTO of tire vendor with specified id.
     */
    public TireVendorDto getTireVendorById(Long tireVendorId);

    /**
     * Returns tire vendor by specified name.
     *
     * @param  tireVendorName name of tire vendor.
     * @return DTO of tire vendor with specified name.
     */
    public TireVendorDto getTireVendorByName(String tireVendorName);

    /**
     * Deletes tire vendor with specified id.
     *
     * @param tireVendorId Id of tire vendor which should be removed.
     */
    public void deleteTireVendor(Long tireVendorId);

    /**
     * Updates tire vendor.
     *
     * @param tireVendorDto DTO of updated tire vendor.
     */
    public void updateTireVendor(TireVendorDto tireVendorDto);
}
