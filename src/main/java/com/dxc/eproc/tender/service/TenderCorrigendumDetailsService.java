package com.dxc.eproc.tender.service;

import com.dxc.eproc.tender.service.dto.TenderCorrigendumDetailsDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dxc.eproc.tender.domain.TenderCorrigendumDetails}.
 */
public interface TenderCorrigendumDetailsService {
    /**
     * Save a tenderCorrigendumDetails.
     *
     * @param tenderCorrigendumDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    TenderCorrigendumDetailsDTO save(TenderCorrigendumDetailsDTO tenderCorrigendumDetailsDTO);

    /**
     * Partially updates a tenderCorrigendumDetails.
     *
     * @param tenderCorrigendumDetailsDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TenderCorrigendumDetailsDTO> partialUpdate(TenderCorrigendumDetailsDTO tenderCorrigendumDetailsDTO);

    /**
     * Get all the tenderCorrigendumDetails.
     *
     * @return the list of entities.
     */
    List<TenderCorrigendumDetailsDTO> findAll();

    /**
     * Get the "id" tenderCorrigendumDetails.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TenderCorrigendumDetailsDTO> findOne(Long id);

    /**
     * Delete the "id" tenderCorrigendumDetails.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
