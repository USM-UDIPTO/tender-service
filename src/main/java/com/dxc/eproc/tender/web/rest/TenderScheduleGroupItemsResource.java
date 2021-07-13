package com.dxc.eproc.tender.web.rest;

import com.dxc.eproc.tender.repository.TenderScheduleGroupItemsRepository;
import com.dxc.eproc.tender.service.TenderScheduleGroupItemsService;
import com.dxc.eproc.tender.service.dto.TenderScheduleGroupItemsDTO;
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
 * REST controller for managing {@link com.dxc.eproc.tender.domain.TenderScheduleGroupItems}.
 */
@RestController
@RequestMapping("/api")
public class TenderScheduleGroupItemsResource {

    private final Logger log = LoggerFactory.getLogger(TenderScheduleGroupItemsResource.class);

    private static final String ENTITY_NAME = "tenderServiceTenderScheduleGroupItems";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TenderScheduleGroupItemsService tenderScheduleGroupItemsService;

    private final TenderScheduleGroupItemsRepository tenderScheduleGroupItemsRepository;

    public TenderScheduleGroupItemsResource(
        TenderScheduleGroupItemsService tenderScheduleGroupItemsService,
        TenderScheduleGroupItemsRepository tenderScheduleGroupItemsRepository
    ) {
        this.tenderScheduleGroupItemsService = tenderScheduleGroupItemsService;
        this.tenderScheduleGroupItemsRepository = tenderScheduleGroupItemsRepository;
    }

    /**
     * {@code POST  /tender-schedule-group-items} : Create a new tenderScheduleGroupItems.
     *
     * @param tenderScheduleGroupItemsDTO the tenderScheduleGroupItemsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tenderScheduleGroupItemsDTO, or with status {@code 400 (Bad Request)} if the tenderScheduleGroupItems has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tender-schedule-group-items")
    public ResponseEntity<TenderScheduleGroupItemsDTO> createTenderScheduleGroupItems(
        @Valid @RequestBody TenderScheduleGroupItemsDTO tenderScheduleGroupItemsDTO
    ) throws URISyntaxException {
        log.debug("REST request to save TenderScheduleGroupItems : {}", tenderScheduleGroupItemsDTO);
        if (tenderScheduleGroupItemsDTO.getId() != null) {
            throw new BadRequestAlertException("A new tenderScheduleGroupItems cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TenderScheduleGroupItemsDTO result = tenderScheduleGroupItemsService.save(tenderScheduleGroupItemsDTO);
        return ResponseEntity
            .created(new URI("/api/tender-schedule-group-items/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tender-schedule-group-items/:id} : Updates an existing tenderScheduleGroupItems.
     *
     * @param id the id of the tenderScheduleGroupItemsDTO to save.
     * @param tenderScheduleGroupItemsDTO the tenderScheduleGroupItemsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tenderScheduleGroupItemsDTO,
     * or with status {@code 400 (Bad Request)} if the tenderScheduleGroupItemsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tenderScheduleGroupItemsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tender-schedule-group-items/{id}")
    public ResponseEntity<TenderScheduleGroupItemsDTO> updateTenderScheduleGroupItems(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody TenderScheduleGroupItemsDTO tenderScheduleGroupItemsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TenderScheduleGroupItems : {}, {}", id, tenderScheduleGroupItemsDTO);
        if (tenderScheduleGroupItemsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tenderScheduleGroupItemsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tenderScheduleGroupItemsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TenderScheduleGroupItemsDTO result = tenderScheduleGroupItemsService.save(tenderScheduleGroupItemsDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tenderScheduleGroupItemsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tender-schedule-group-items/:id} : Partial updates given fields of an existing tenderScheduleGroupItems, field will ignore if it is null
     *
     * @param id the id of the tenderScheduleGroupItemsDTO to save.
     * @param tenderScheduleGroupItemsDTO the tenderScheduleGroupItemsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tenderScheduleGroupItemsDTO,
     * or with status {@code 400 (Bad Request)} if the tenderScheduleGroupItemsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the tenderScheduleGroupItemsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tenderScheduleGroupItemsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tender-schedule-group-items/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<TenderScheduleGroupItemsDTO> partialUpdateTenderScheduleGroupItems(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody TenderScheduleGroupItemsDTO tenderScheduleGroupItemsDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TenderScheduleGroupItems partially : {}, {}", id, tenderScheduleGroupItemsDTO);
        if (tenderScheduleGroupItemsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tenderScheduleGroupItemsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tenderScheduleGroupItemsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TenderScheduleGroupItemsDTO> result = tenderScheduleGroupItemsService.partialUpdate(tenderScheduleGroupItemsDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tenderScheduleGroupItemsDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /tender-schedule-group-items} : get all the tenderScheduleGroupItems.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tenderScheduleGroupItems in body.
     */
    @GetMapping("/tender-schedule-group-items")
    public List<TenderScheduleGroupItemsDTO> getAllTenderScheduleGroupItems() {
        log.debug("REST request to get all TenderScheduleGroupItems");
        return tenderScheduleGroupItemsService.findAll();
    }

    /**
     * {@code GET  /tender-schedule-group-items/:id} : get the "id" tenderScheduleGroupItems.
     *
     * @param id the id of the tenderScheduleGroupItemsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tenderScheduleGroupItemsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tender-schedule-group-items/{id}")
    public ResponseEntity<TenderScheduleGroupItemsDTO> getTenderScheduleGroupItems(@PathVariable Long id) {
        log.debug("REST request to get TenderScheduleGroupItems : {}", id);
        Optional<TenderScheduleGroupItemsDTO> tenderScheduleGroupItemsDTO = tenderScheduleGroupItemsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tenderScheduleGroupItemsDTO);
    }

    /**
     * {@code DELETE  /tender-schedule-group-items/:id} : delete the "id" tenderScheduleGroupItems.
     *
     * @param id the id of the tenderScheduleGroupItemsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tender-schedule-group-items/{id}")
    public ResponseEntity<Void> deleteTenderScheduleGroupItems(@PathVariable Long id) {
        log.debug("REST request to delete TenderScheduleGroupItems : {}", id);
        tenderScheduleGroupItemsService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
