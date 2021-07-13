package com.dxc.eproc.tender.web.rest;

import com.dxc.eproc.tender.repository.TenderCriterionRepository;
import com.dxc.eproc.tender.service.TenderCriterionService;
import com.dxc.eproc.tender.service.dto.TenderCriterionDTO;
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
 * REST controller for managing {@link com.dxc.eproc.tender.domain.TenderCriterion}.
 */
@RestController
@RequestMapping("/api")
public class TenderCriterionResource {

    private final Logger log = LoggerFactory.getLogger(TenderCriterionResource.class);

    private static final String ENTITY_NAME = "tenderServiceTenderCriterion";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TenderCriterionService tenderCriterionService;

    private final TenderCriterionRepository tenderCriterionRepository;

    public TenderCriterionResource(TenderCriterionService tenderCriterionService, TenderCriterionRepository tenderCriterionRepository) {
        this.tenderCriterionService = tenderCriterionService;
        this.tenderCriterionRepository = tenderCriterionRepository;
    }

    /**
     * {@code POST  /tender-criteria} : Create a new tenderCriterion.
     *
     * @param tenderCriterionDTO the tenderCriterionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tenderCriterionDTO, or with status {@code 400 (Bad Request)} if the tenderCriterion has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tender-criteria")
    public ResponseEntity<TenderCriterionDTO> createTenderCriterion(@Valid @RequestBody TenderCriterionDTO tenderCriterionDTO)
        throws URISyntaxException {
        log.debug("REST request to save TenderCriterion : {}", tenderCriterionDTO);
        if (tenderCriterionDTO.getId() != null) {
            throw new BadRequestAlertException("A new tenderCriterion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TenderCriterionDTO result = tenderCriterionService.save(tenderCriterionDTO);
        return ResponseEntity
            .created(new URI("/api/tender-criteria/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tender-criteria/:id} : Updates an existing tenderCriterion.
     *
     * @param id the id of the tenderCriterionDTO to save.
     * @param tenderCriterionDTO the tenderCriterionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tenderCriterionDTO,
     * or with status {@code 400 (Bad Request)} if the tenderCriterionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tenderCriterionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tender-criteria/{id}")
    public ResponseEntity<TenderCriterionDTO> updateTenderCriterion(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody TenderCriterionDTO tenderCriterionDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TenderCriterion : {}, {}", id, tenderCriterionDTO);
        if (tenderCriterionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tenderCriterionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tenderCriterionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TenderCriterionDTO result = tenderCriterionService.save(tenderCriterionDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tenderCriterionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tender-criteria/:id} : Partial updates given fields of an existing tenderCriterion, field will ignore if it is null
     *
     * @param id the id of the tenderCriterionDTO to save.
     * @param tenderCriterionDTO the tenderCriterionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tenderCriterionDTO,
     * or with status {@code 400 (Bad Request)} if the tenderCriterionDTO is not valid,
     * or with status {@code 404 (Not Found)} if the tenderCriterionDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tenderCriterionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tender-criteria/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<TenderCriterionDTO> partialUpdateTenderCriterion(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody TenderCriterionDTO tenderCriterionDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TenderCriterion partially : {}, {}", id, tenderCriterionDTO);
        if (tenderCriterionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tenderCriterionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tenderCriterionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TenderCriterionDTO> result = tenderCriterionService.partialUpdate(tenderCriterionDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tenderCriterionDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /tender-criteria} : get all the tenderCriteria.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tenderCriteria in body.
     */
    @GetMapping("/tender-criteria")
    public List<TenderCriterionDTO> getAllTenderCriteria() {
        log.debug("REST request to get all TenderCriteria");
        return tenderCriterionService.findAll();
    }

    /**
     * {@code GET  /tender-criteria/:id} : get the "id" tenderCriterion.
     *
     * @param id the id of the tenderCriterionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tenderCriterionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tender-criteria/{id}")
    public ResponseEntity<TenderCriterionDTO> getTenderCriterion(@PathVariable Long id) {
        log.debug("REST request to get TenderCriterion : {}", id);
        Optional<TenderCriterionDTO> tenderCriterionDTO = tenderCriterionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tenderCriterionDTO);
    }

    /**
     * {@code DELETE  /tender-criteria/:id} : delete the "id" tenderCriterion.
     *
     * @param id the id of the tenderCriterionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tender-criteria/{id}")
    public ResponseEntity<Void> deleteTenderCriterion(@PathVariable Long id) {
        log.debug("REST request to delete TenderCriterion : {}", id);
        tenderCriterionService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
