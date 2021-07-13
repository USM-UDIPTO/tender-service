package com.dxc.eproc.tender.service;

import com.dxc.eproc.tender.service.dto.TenderGoodsItemsDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dxc.eproc.tender.domain.TenderGoodsItems}.
 */
public interface TenderGoodsItemsService {
    /**
     * Save a tenderGoodsItems.
     *
     * @param tenderGoodsItemsDTO the entity to save.
     * @return the persisted entity.
     */
    TenderGoodsItemsDTO save(TenderGoodsItemsDTO tenderGoodsItemsDTO);

    /**
     * Partially updates a tenderGoodsItems.
     *
     * @param tenderGoodsItemsDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TenderGoodsItemsDTO> partialUpdate(TenderGoodsItemsDTO tenderGoodsItemsDTO);

    /**
     * Get all the tenderGoodsItems.
     *
     * @return the list of entities.
     */
    List<TenderGoodsItemsDTO> findAll();

    /**
     * Get the "id" tenderGoodsItems.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TenderGoodsItemsDTO> findOne(Long id);

    /**
     * Delete the "id" tenderGoodsItems.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
