package com.dxc.eproc.tender.service;

import com.dxc.eproc.tender.service.dto.ObjectStoreDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dxc.eproc.tender.domain.ObjectStore}.
 */
public interface ObjectStoreService {
    /**
     * Save a objectStore.
     *
     * @param objectStoreDTO the entity to save.
     * @return the persisted entity.
     */
    ObjectStoreDTO save(ObjectStoreDTO objectStoreDTO);

    /**
     * Partially updates a objectStore.
     *
     * @param objectStoreDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ObjectStoreDTO> partialUpdate(ObjectStoreDTO objectStoreDTO);

    /**
     * Get all the objectStores.
     *
     * @return the list of entities.
     */
    List<ObjectStoreDTO> findAll();

    /**
     * Get the "id" objectStore.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ObjectStoreDTO> findOne(Long id);

    /**
     * Delete the "id" objectStore.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
