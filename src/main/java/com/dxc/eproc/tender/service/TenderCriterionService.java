package com.dxc.eproc.tender.service;

import com.dxc.eproc.tender.service.dto.TenderCriterionDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dxc.eproc.tender.domain.TenderCriterion}.
 */
public interface TenderCriterionService {
    /**
     * Save a tenderCriterion.
     *
     * @param tenderCriterionDTO the entity to save.
     * @return the persisted entity.
     */
    TenderCriterionDTO save(TenderCriterionDTO tenderCriterionDTO);

    /**
     * Partially updates a tenderCriterion.
     *
     * @param tenderCriterionDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TenderCriterionDTO> partialUpdate(TenderCriterionDTO tenderCriterionDTO);

    /**
     * Get all the tenderCriteria.
     *
     * @return the list of entities.
     */
    List<TenderCriterionDTO> findAll();

    /**
     * Get the "id" tenderCriterion.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TenderCriterionDTO> findOne(Long id);

    /**
     * Delete the "id" tenderCriterion.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
