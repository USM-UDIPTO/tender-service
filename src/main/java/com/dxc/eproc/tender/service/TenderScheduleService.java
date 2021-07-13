package com.dxc.eproc.tender.service;

import com.dxc.eproc.tender.service.dto.TenderScheduleDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.dxc.eproc.tender.domain.TenderSchedule}.
 */
public interface TenderScheduleService {
    /**
     * Save a tenderSchedule.
     *
     * @param tenderScheduleDTO the entity to save.
     * @return the persisted entity.
     */
    TenderScheduleDTO save(TenderScheduleDTO tenderScheduleDTO);

    /**
     * Partially updates a tenderSchedule.
     *
     * @param tenderScheduleDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TenderScheduleDTO> partialUpdate(TenderScheduleDTO tenderScheduleDTO);

    /**
     * Get all the tenderSchedules.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TenderScheduleDTO> findAll(Pageable pageable);

    /**
     * Get the "id" tenderSchedule.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TenderScheduleDTO> findOne(Long id);

    /**
     * Delete the "id" tenderSchedule.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
