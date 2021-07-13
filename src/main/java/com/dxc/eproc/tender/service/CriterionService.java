package com.dxc.eproc.tender.service;

import com.dxc.eproc.tender.service.dto.CriterionDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dxc.eproc.tender.domain.Criterion}.
 */
public interface CriterionService {
    /**
     * Save a criterion.
     *
     * @param criterionDTO the entity to save.
     * @return the persisted entity.
     */
    CriterionDTO save(CriterionDTO criterionDTO);

    /**
     * Partially updates a criterion.
     *
     * @param criterionDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<CriterionDTO> partialUpdate(CriterionDTO criterionDTO);

    /**
     * Get all the criteria.
     *
     * @return the list of entities.
     */
    List<CriterionDTO> findAll();

    /**
     * Get the "id" criterion.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CriterionDTO> findOne(Long id);

    /**
     * Delete the "id" criterion.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
