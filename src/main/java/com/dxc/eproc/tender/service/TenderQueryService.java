package com.dxc.eproc.tender.service;

import com.dxc.eproc.tender.service.dto.TenderQueryDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dxc.eproc.tender.domain.TenderQuery}.
 */
public interface TenderQueryService {
    /**
     * Save a tenderQuery.
     *
     * @param tenderQueryDTO the entity to save.
     * @return the persisted entity.
     */
    TenderQueryDTO save(TenderQueryDTO tenderQueryDTO);

    /**
     * Partially updates a tenderQuery.
     *
     * @param tenderQueryDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TenderQueryDTO> partialUpdate(TenderQueryDTO tenderQueryDTO);

    /**
     * Get all the tenderQueries.
     *
     * @return the list of entities.
     */
    List<TenderQueryDTO> findAll();

    /**
     * Get the "id" tenderQuery.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TenderQueryDTO> findOne(Long id);

    /**
     * Delete the "id" tenderQuery.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
