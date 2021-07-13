package com.dxc.eproc.tender.service;

import com.dxc.eproc.tender.service.dto.TenderScheduleTelephoneDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dxc.eproc.tender.domain.TenderScheduleTelephone}.
 */
public interface TenderScheduleTelephoneService {
    /**
     * Save a tenderScheduleTelephone.
     *
     * @param tenderScheduleTelephoneDTO the entity to save.
     * @return the persisted entity.
     */
    TenderScheduleTelephoneDTO save(TenderScheduleTelephoneDTO tenderScheduleTelephoneDTO);

    /**
     * Partially updates a tenderScheduleTelephone.
     *
     * @param tenderScheduleTelephoneDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TenderScheduleTelephoneDTO> partialUpdate(TenderScheduleTelephoneDTO tenderScheduleTelephoneDTO);

    /**
     * Get all the tenderScheduleTelephones.
     *
     * @return the list of entities.
     */
    List<TenderScheduleTelephoneDTO> findAll();

    /**
     * Get the "id" tenderScheduleTelephone.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TenderScheduleTelephoneDTO> findOne(Long id);

    /**
     * Delete the "id" tenderScheduleTelephone.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
