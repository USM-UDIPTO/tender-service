package com.dxc.eproc.tender.service;

import com.dxc.eproc.tender.service.dto.TenderScheduleSampleDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dxc.eproc.tender.domain.TenderScheduleSample}.
 */
public interface TenderScheduleSampleService {
    /**
     * Save a tenderScheduleSample.
     *
     * @param tenderScheduleSampleDTO the entity to save.
     * @return the persisted entity.
     */
    TenderScheduleSampleDTO save(TenderScheduleSampleDTO tenderScheduleSampleDTO);

    /**
     * Partially updates a tenderScheduleSample.
     *
     * @param tenderScheduleSampleDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TenderScheduleSampleDTO> partialUpdate(TenderScheduleSampleDTO tenderScheduleSampleDTO);

    /**
     * Get all the tenderScheduleSamples.
     *
     * @return the list of entities.
     */
    List<TenderScheduleSampleDTO> findAll();

    /**
     * Get the "id" tenderScheduleSample.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TenderScheduleSampleDTO> findOne(Long id);

    /**
     * Delete the "id" tenderScheduleSample.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
