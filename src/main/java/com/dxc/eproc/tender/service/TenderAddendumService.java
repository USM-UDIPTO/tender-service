package com.dxc.eproc.tender.service;

import com.dxc.eproc.tender.service.dto.TenderAddendumDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dxc.eproc.tender.domain.TenderAddendum}.
 */
public interface TenderAddendumService {
    /**
     * Save a tenderAddendum.
     *
     * @param tenderAddendumDTO the entity to save.
     * @return the persisted entity.
     */
    TenderAddendumDTO save(TenderAddendumDTO tenderAddendumDTO);

    /**
     * Partially updates a tenderAddendum.
     *
     * @param tenderAddendumDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TenderAddendumDTO> partialUpdate(TenderAddendumDTO tenderAddendumDTO);

    /**
     * Get all the tenderAddenda.
     *
     * @return the list of entities.
     */
    List<TenderAddendumDTO> findAll();

    /**
     * Get the "id" tenderAddendum.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TenderAddendumDTO> findOne(Long id);

    /**
     * Delete the "id" tenderAddendum.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
