package com.dxc.eproc.tender.service;

import com.dxc.eproc.tender.service.dto.TenderCorrigendumDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dxc.eproc.tender.domain.TenderCorrigendum}.
 */
public interface TenderCorrigendumService {
    /**
     * Save a tenderCorrigendum.
     *
     * @param tenderCorrigendumDTO the entity to save.
     * @return the persisted entity.
     */
    TenderCorrigendumDTO save(TenderCorrigendumDTO tenderCorrigendumDTO);

    /**
     * Partially updates a tenderCorrigendum.
     *
     * @param tenderCorrigendumDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TenderCorrigendumDTO> partialUpdate(TenderCorrigendumDTO tenderCorrigendumDTO);

    /**
     * Get all the tenderCorrigendums.
     *
     * @return the list of entities.
     */
    List<TenderCorrigendumDTO> findAll();

    /**
     * Get the "id" tenderCorrigendum.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TenderCorrigendumDTO> findOne(Long id);

    /**
     * Delete the "id" tenderCorrigendum.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
