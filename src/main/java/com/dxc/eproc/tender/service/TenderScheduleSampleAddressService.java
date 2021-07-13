package com.dxc.eproc.tender.service;

import com.dxc.eproc.tender.service.dto.TenderScheduleSampleAddressDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dxc.eproc.tender.domain.TenderScheduleSampleAddress}.
 */
public interface TenderScheduleSampleAddressService {
    /**
     * Save a tenderScheduleSampleAddress.
     *
     * @param tenderScheduleSampleAddressDTO the entity to save.
     * @return the persisted entity.
     */
    TenderScheduleSampleAddressDTO save(TenderScheduleSampleAddressDTO tenderScheduleSampleAddressDTO);

    /**
     * Partially updates a tenderScheduleSampleAddress.
     *
     * @param tenderScheduleSampleAddressDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TenderScheduleSampleAddressDTO> partialUpdate(TenderScheduleSampleAddressDTO tenderScheduleSampleAddressDTO);

    /**
     * Get all the tenderScheduleSampleAddresses.
     *
     * @return the list of entities.
     */
    List<TenderScheduleSampleAddressDTO> findAll();

    /**
     * Get the "id" tenderScheduleSampleAddress.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TenderScheduleSampleAddressDTO> findOne(Long id);

    /**
     * Delete the "id" tenderScheduleSampleAddress.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
