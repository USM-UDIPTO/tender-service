package com.dxc.eproc.tender.service;

import com.dxc.eproc.tender.service.dto.TenderCriterionDocumentDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dxc.eproc.tender.domain.TenderCriterionDocument}.
 */
public interface TenderCriterionDocumentService {
    /**
     * Save a tenderCriterionDocument.
     *
     * @param tenderCriterionDocumentDTO the entity to save.
     * @return the persisted entity.
     */
    TenderCriterionDocumentDTO save(TenderCriterionDocumentDTO tenderCriterionDocumentDTO);

    /**
     * Partially updates a tenderCriterionDocument.
     *
     * @param tenderCriterionDocumentDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TenderCriterionDocumentDTO> partialUpdate(TenderCriterionDocumentDTO tenderCriterionDocumentDTO);

    /**
     * Get all the tenderCriterionDocuments.
     *
     * @return the list of entities.
     */
    List<TenderCriterionDocumentDTO> findAll();

    /**
     * Get the "id" tenderCriterionDocument.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TenderCriterionDocumentDTO> findOne(Long id);

    /**
     * Delete the "id" tenderCriterionDocument.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
