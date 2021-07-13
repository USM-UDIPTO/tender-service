package com.dxc.eproc.tender.service;

import com.dxc.eproc.tender.service.dto.SeriesTableDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dxc.eproc.tender.domain.SeriesTable}.
 */
public interface SeriesTableService {
    /**
     * Save a seriesTable.
     *
     * @param seriesTableDTO the entity to save.
     * @return the persisted entity.
     */
    SeriesTableDTO save(SeriesTableDTO seriesTableDTO);

    /**
     * Partially updates a seriesTable.
     *
     * @param seriesTableDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<SeriesTableDTO> partialUpdate(SeriesTableDTO seriesTableDTO);

    /**
     * Get all the seriesTables.
     *
     * @return the list of entities.
     */
    List<SeriesTableDTO> findAll();

    /**
     * Get the "id" seriesTable.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SeriesTableDTO> findOne(Long id);

    /**
     * Delete the "id" seriesTable.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
