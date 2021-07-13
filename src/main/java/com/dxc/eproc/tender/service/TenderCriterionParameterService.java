package com.dxc.eproc.tender.service;

import com.dxc.eproc.tender.service.dto.TenderCriterionParameterDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dxc.eproc.tender.domain.TenderCriterionParameter}.
 */
public interface TenderCriterionParameterService {
    /**
     * Save a tenderCriterionParameter.
     *
     * @param tenderCriterionParameterDTO the entity to save.
     * @return the persisted entity.
     */
    TenderCriterionParameterDTO save(TenderCriterionParameterDTO tenderCriterionParameterDTO);

    /**
     * Partially updates a tenderCriterionParameter.
     *
     * @param tenderCriterionParameterDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TenderCriterionParameterDTO> partialUpdate(TenderCriterionParameterDTO tenderCriterionParameterDTO);

    /**
     * Get all the tenderCriterionParameters.
     *
     * @return the list of entities.
     */
    List<TenderCriterionParameterDTO> findAll();

    /**
     * Get the "id" tenderCriterionParameter.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TenderCriterionParameterDTO> findOne(Long id);

    /**
     * Delete the "id" tenderCriterionParameter.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
