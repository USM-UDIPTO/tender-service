package com.dxc.eproc.tender.web.rest;

import com.dxc.eproc.tender.repository.TenderScrutinyMasterRepository;
import com.dxc.eproc.tender.service.TenderScrutinyMasterService;
import com.dxc.eproc.tender.service.dto.TenderScrutinyMasterDTO;
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
 * REST controller for managing {@link com.dxc.eproc.tender.domain.TenderScrutinyMaster}.
 */
@RestController
@RequestMapping("/api")
public class TenderScrutinyMasterResource {

    private final Logger log = LoggerFactory.getLogger(TenderScrutinyMasterResource.class);

    private static final String ENTITY_NAME = "tenderServiceTenderScrutinyMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TenderScrutinyMasterService tenderScrutinyMasterService;

    private final TenderScrutinyMasterRepository tenderScrutinyMasterRepository;

    public TenderScrutinyMasterResource(
        TenderScrutinyMasterService tenderScrutinyMasterService,
        TenderScrutinyMasterRepository tenderScrutinyMasterRepository
    ) {
        this.tenderScrutinyMasterService = tenderScrutinyMasterService;
        this.tenderScrutinyMasterRepository = tenderScrutinyMasterRepository;
    }

    /**
     * {@code POST  /tender-scrutiny-masters} : Create a new tenderScrutinyMaster.
     *
     * @param tenderScrutinyMasterDTO the tenderScrutinyMasterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tenderScrutinyMasterDTO, or with status {@code 400 (Bad Request)} if the tenderScrutinyMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tender-scrutiny-masters")
    public ResponseEntity<TenderScrutinyMasterDTO> createTenderScrutinyMaster(
        @Valid @RequestBody TenderScrutinyMasterDTO tenderScrutinyMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to save TenderScrutinyMaster : {}", tenderScrutinyMasterDTO);
        if (tenderScrutinyMasterDTO.getId() != null) {
            throw new BadRequestAlertException("A new tenderScrutinyMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TenderScrutinyMasterDTO result = tenderScrutinyMasterService.save(tenderScrutinyMasterDTO);
        return ResponseEntity
            .created(new URI("/api/tender-scrutiny-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tender-scrutiny-masters/:id} : Updates an existing tenderScrutinyMaster.
     *
     * @param id the id of the tenderScrutinyMasterDTO to save.
     * @param tenderScrutinyMasterDTO the tenderScrutinyMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tenderScrutinyMasterDTO,
     * or with status {@code 400 (Bad Request)} if the tenderScrutinyMasterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tenderScrutinyMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tender-scrutiny-masters/{id}")
    public ResponseEntity<TenderScrutinyMasterDTO> updateTenderScrutinyMaster(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody TenderScrutinyMasterDTO tenderScrutinyMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TenderScrutinyMaster : {}, {}", id, tenderScrutinyMasterDTO);
        if (tenderScrutinyMasterDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tenderScrutinyMasterDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tenderScrutinyMasterRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TenderScrutinyMasterDTO result = tenderScrutinyMasterService.save(tenderScrutinyMasterDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tenderScrutinyMasterDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tender-scrutiny-masters/:id} : Partial updates given fields of an existing tenderScrutinyMaster, field will ignore if it is null
     *
     * @param id the id of the tenderScrutinyMasterDTO to save.
     * @param tenderScrutinyMasterDTO the tenderScrutinyMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tenderScrutinyMasterDTO,
     * or with status {@code 400 (Bad Request)} if the tenderScrutinyMasterDTO is not valid,
     * or with status {@code 404 (Not Found)} if the tenderScrutinyMasterDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tenderScrutinyMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tender-scrutiny-masters/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<TenderScrutinyMasterDTO> partialUpdateTenderScrutinyMaster(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody TenderScrutinyMasterDTO tenderScrutinyMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TenderScrutinyMaster partially : {}, {}", id, tenderScrutinyMasterDTO);
        if (tenderScrutinyMasterDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tenderScrutinyMasterDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tenderScrutinyMasterRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TenderScrutinyMasterDTO> result = tenderScrutinyMasterService.partialUpdate(tenderScrutinyMasterDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tenderScrutinyMasterDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /tender-scrutiny-masters} : get all the tenderScrutinyMasters.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tenderScrutinyMasters in body.
     */
    @GetMapping("/tender-scrutiny-masters")
    public List<TenderScrutinyMasterDTO> getAllTenderScrutinyMasters() {
        log.debug("REST request to get all TenderScrutinyMasters");
        return tenderScrutinyMasterService.findAll();
    }

    /**
     * {@code GET  /tender-scrutiny-masters/:id} : get the "id" tenderScrutinyMaster.
     *
     * @param id the id of the tenderScrutinyMasterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tenderScrutinyMasterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tender-scrutiny-masters/{id}")
    public ResponseEntity<TenderScrutinyMasterDTO> getTenderScrutinyMaster(@PathVariable Long id) {
        log.debug("REST request to get TenderScrutinyMaster : {}", id);
        Optional<TenderScrutinyMasterDTO> tenderScrutinyMasterDTO = tenderScrutinyMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tenderScrutinyMasterDTO);
    }

    /**
     * {@code DELETE  /tender-scrutiny-masters/:id} : delete the "id" tenderScrutinyMaster.
     *
     * @param id the id of the tenderScrutinyMasterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tender-scrutiny-masters/{id}")
    public ResponseEntity<Void> deleteTenderScrutinyMaster(@PathVariable Long id) {
        log.debug("REST request to delete TenderScrutinyMaster : {}", id);
        tenderScrutinyMasterService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
