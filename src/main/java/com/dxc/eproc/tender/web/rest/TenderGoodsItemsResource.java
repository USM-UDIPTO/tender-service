package com.dxc.eproc.tender.web.rest;

import com.dxc.eproc.tender.repository.TenderGoodsItemsRepository;
import com.dxc.eproc.tender.service.TenderGoodsItemsService;
import com.dxc.eproc.tender.service.dto.TenderGoodsItemsDTO;
import com.dxc.eproc.tender.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.dxc.eproc.tender.domain.TenderGoodsItems}.
 */
@RestController
@RequestMapping("/api")
public class TenderGoodsItemsResource {

    private final Logger log = LoggerFactory.getLogger(TenderGoodsItemsResource.class);

    private static final String ENTITY_NAME = "tenderServiceTenderGoodsItems";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TenderGoodsItemsService tenderGoodsItemsService;

    private final TenderGoodsItemsRepository tenderGoodsItemsRepository;

    public TenderGoodsItemsResource(
        TenderGoodsItemsService tenderGoodsItemsService,
        TenderGoodsItemsRepository tenderGoodsItemsRepository
    ) {
        this.tenderGoodsItemsService = tenderGoodsItemsService;
        this.tenderGoodsItemsRepository = tenderGoodsItemsRepository;
    }

    /**
     * {@code POST  /tender-goods-items} : Create a new tenderGoodsItems.
     *
     * @param tenderGoodsItemsDTO the tenderGoodsItemsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tenderGoodsItemsDTO, or with status {@code 400 (Bad Request)} if the tenderGoodsItems has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tender-goods-items")
    public ResponseEntity<TenderGoodsItemsDTO> createTenderGoodsItems(@Valid @RequestBody TenderGoodsItemsDTO tenderGoodsItemsDTO)
        throws URISyntaxException {
        log.debug("REST request to save TenderGoodsItems : {}", tenderGoodsItemsDTO);
        if (tenderGoodsItemsDTO.getId() != null) {
            throw new BadRequestAlertException("A new tenderGoodsItems cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TenderGoodsItemsDTO result = tenderGoodsItemsService.save(tenderGoodsItemsDTO);
        return ResponseEntity
            .created(new URI("/api/tender-goods-items/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tender-goods-items/:id} : Updates an existing tenderGoodsItems.
     *
     * @param id the id of the tenderGoodsItemsDTO to save.
     * @param tenderGoodsItemsDTO the tenderGoodsItemsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tenderGoodsItemsDTO,
     * or with status {@code 400 (Bad Request)} if the tenderGoodsItemsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tenderGoodsItemsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tender-goods-items/{id}")
    public ResponseEntity<TenderGoodsItemsDTO> updateTenderGoodsItems(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody TenderGoodsItemsDTO tenderGoodsItemsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TenderGoodsItems : {}, {}", id, tenderGoodsItemsDTO);
        if (tenderGoodsItemsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tenderGoodsItemsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tenderGoodsItemsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TenderGoodsItemsDTO result = tenderGoodsItemsService.save(tenderGoodsItemsDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tenderGoodsItemsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tender-goods-items/:id} : Partial updates given fields of an existing tenderGoodsItems, field will ignore if it is null
     *
     * @param id the id of the tenderGoodsItemsDTO to save.
     * @param tenderGoodsItemsDTO the tenderGoodsItemsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tenderGoodsItemsDTO,
     * or with status {@code 400 (Bad Request)} if the tenderGoodsItemsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the tenderGoodsItemsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tenderGoodsItemsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tender-goods-items/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<TenderGoodsItemsDTO> partialUpdateTenderGoodsItems(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody TenderGoodsItemsDTO tenderGoodsItemsDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TenderGoodsItems partially : {}, {}", id, tenderGoodsItemsDTO);
        if (tenderGoodsItemsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tenderGoodsItemsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tenderGoodsItemsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TenderGoodsItemsDTO> result = tenderGoodsItemsService.partialUpdate(tenderGoodsItemsDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tenderGoodsItemsDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /tender-goods-items} : get all the tenderGoodsItems.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tenderGoodsItems in body.
     */
    @GetMapping("/tender-goods-items")
    public List<TenderGoodsItemsDTO> getAllTenderGoodsItems() {
        log.debug("REST request to get all TenderGoodsItems");
        return tenderGoodsItemsService.findAll();
    }

    /**
     * {@code GET  /tender-goods-items/:id} : get the "id" tenderGoodsItems.
     *
     * @param id the id of the tenderGoodsItemsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tenderGoodsItemsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tender-goods-items/{id}")
    public ResponseEntity<TenderGoodsItemsDTO> getTenderGoodsItems(@PathVariable Long id) {
        log.debug("REST request to get TenderGoodsItems : {}", id);
        Optional<TenderGoodsItemsDTO> tenderGoodsItemsDTO = tenderGoodsItemsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tenderGoodsItemsDTO);
    }

    /**
     * {@code DELETE  /tender-goods-items/:id} : delete the "id" tenderGoodsItems.
     *
     * @param id the id of the tenderGoodsItemsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tender-goods-items/{id}")
    public ResponseEntity<Void> deleteTenderGoodsItems(@PathVariable Long id) {
        log.debug("REST request to delete TenderGoodsItems : {}", id);
        tenderGoodsItemsService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
