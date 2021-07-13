package com.dxc.eproc.tender.service;

import com.dxc.eproc.tender.service.dto.TenderScheduleGroupDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dxc.eproc.tender.domain.TenderScheduleGroup}.
 */
public interface TenderScheduleGroupService {
    /**
     * Save a tenderScheduleGroup.
     *
     * @param tenderScheduleGroupDTO the entity to save.
     * @return the persisted entity.
     */
    TenderScheduleGroupDTO save(TenderScheduleGroupDTO tenderScheduleGroupDTO);

    /**
     * Partially updates a tenderScheduleGroup.
     *
     * @param tenderScheduleGroupDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TenderScheduleGroupDTO> partialUpdate(TenderScheduleGroupDTO tenderScheduleGroupDTO);

    /**
     * Get all the tenderScheduleGroups.
     *
     * @return the list of entities.
     */
    List<TenderScheduleGroupDTO> findAll();

    /**
     * Get the "id" tenderScheduleGroup.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TenderScheduleGroupDTO> findOne(Long id);

    /**
     * Delete the "id" tenderScheduleGroup.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
