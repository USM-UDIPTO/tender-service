package com.dxc.eproc.tender.service;

import com.dxc.eproc.tender.service.dto.TenderScrutinyCommitteeDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dxc.eproc.tender.domain.TenderScrutinyCommittee}.
 */
public interface TenderScrutinyCommitteeService {
    /**
     * Save a tenderScrutinyCommittee.
     *
     * @param tenderScrutinyCommitteeDTO the entity to save.
     * @return the persisted entity.
     */
    TenderScrutinyCommitteeDTO save(TenderScrutinyCommitteeDTO tenderScrutinyCommitteeDTO);

    /**
     * Partially updates a tenderScrutinyCommittee.
     *
     * @param tenderScrutinyCommitteeDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TenderScrutinyCommitteeDTO> partialUpdate(TenderScrutinyCommitteeDTO tenderScrutinyCommitteeDTO);

    /**
     * Get all the tenderScrutinyCommittees.
     *
     * @return the list of entities.
     */
    List<TenderScrutinyCommitteeDTO> findAll();

    /**
     * Get the "id" tenderScrutinyCommittee.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TenderScrutinyCommitteeDTO> findOne(Long id);

    /**
     * Delete the "id" tenderScrutinyCommittee.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
