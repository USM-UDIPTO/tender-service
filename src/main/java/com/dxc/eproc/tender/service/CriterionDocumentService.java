package com.dxc.eproc.tender.service;

import com.dxc.eproc.tender.service.dto.CriterionDocumentDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dxc.eproc.tender.domain.CriterionDocument}.
 */
public interface CriterionDocumentService {
    /**
     * Save a criterionDocument.
     *
     * @param criterionDocumentDTO the entity to save.
     * @return the persisted entity.
     */
    CriterionDocumentDTO save(CriterionDocumentDTO criterionDocumentDTO);

    /**
     * Partially updates a criterionDocument.
     *
     * @param criterionDocumentDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<CriterionDocumentDTO> partialUpdate(CriterionDocumentDTO criterionDocumentDTO);

    /**
     * Get all the criterionDocuments.
     *
     * @return the list of entities.
     */
    List<CriterionDocumentDTO> findAll();

    /**
     * Get the "id" criterionDocument.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CriterionDocumentDTO> findOne(Long id);

    /**
     * Delete the "id" criterionDocument.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
