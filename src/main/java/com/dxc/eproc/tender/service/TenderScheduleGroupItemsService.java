package com.dxc.eproc.tender.service;

import com.dxc.eproc.tender.service.dto.TenderScheduleGroupItemsDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dxc.eproc.tender.domain.TenderScheduleGroupItems}.
 */
public interface TenderScheduleGroupItemsService {
    /**
     * Save a tenderScheduleGroupItems.
     *
     * @param tenderScheduleGroupItemsDTO the entity to save.
     * @return the persisted entity.
     */
    TenderScheduleGroupItemsDTO save(TenderScheduleGroupItemsDTO tenderScheduleGroupItemsDTO);

    /**
     * Partially updates a tenderScheduleGroupItems.
     *
     * @param tenderScheduleGroupItemsDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TenderScheduleGroupItemsDTO> partialUpdate(TenderScheduleGroupItemsDTO tenderScheduleGroupItemsDTO);

    /**
     * Get all the tenderScheduleGroupItems.
     *
     * @return the list of entities.
     */
    List<TenderScheduleGroupItemsDTO> findAll();

    /**
     * Get the "id" tenderScheduleGroupItems.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TenderScheduleGroupItemsDTO> findOne(Long id);

    /**
     * Delete the "id" tenderScheduleGroupItems.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
