package com.dxc.eproc.tender.web.rest;

import com.dxc.eproc.tender.repository.TenderScheduleSampleRepository;
import com.dxc.eproc.tender.service.TenderScheduleSampleService;
import com.dxc.eproc.tender.service.dto.TenderScheduleSampleDTO;
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
 * REST controller for managing {@link com.dxc.eproc.tender.domain.TenderScheduleSample}.
 */
@RestController
@RequestMapping("/api")
public class TenderScheduleSampleResource {

    private final Logger log = LoggerFactory.getLogger(TenderScheduleSampleResource.class);

    private static final String ENTITY_NAME = "tenderServiceTenderScheduleSample";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TenderScheduleSampleService tenderScheduleSampleService;

    private final TenderScheduleSampleRepository tenderScheduleSampleRepository;

    public TenderScheduleSampleResource(
        TenderScheduleSampleService tenderScheduleSampleService,
        TenderScheduleSampleRepository tenderScheduleSampleRepository
    ) {
        this.tenderScheduleSampleService = tenderScheduleSampleService;
        this.tenderScheduleSampleRepository = tenderScheduleSampleRepository;
    }

    /**
     * {@code POST  /tender-schedule-samples} : Create a new tenderScheduleSample.
     *
     * @param tenderScheduleSampleDTO the tenderScheduleSampleDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tenderScheduleSampleDTO, or with status {@code 400 (Bad Request)} if the tenderScheduleSample has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tender-schedule-samples")
    public ResponseEntity<TenderScheduleSampleDTO> createTenderScheduleSample(
        @Valid @RequestBody TenderScheduleSampleDTO tenderScheduleSampleDTO
    ) throws URISyntaxException {
        log.debug("REST request to save TenderScheduleSample : {}", tenderScheduleSampleDTO);
        if (tenderScheduleSampleDTO.getId() != null) {
            throw new BadRequestAlertException("A new tenderScheduleSample cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TenderScheduleSampleDTO result = tenderScheduleSampleService.save(tenderScheduleSampleDTO);
        return ResponseEntity
            .created(new URI("/api/tender-schedule-samples/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tender-schedule-samples/:id} : Updates an existing tenderScheduleSample.
     *
     * @param id the id of the tenderScheduleSampleDTO to save.
     * @param tenderScheduleSampleDTO the tenderScheduleSampleDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tenderScheduleSampleDTO,
     * or with status {@code 400 (Bad Request)} if the tenderScheduleSampleDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tenderScheduleSampleDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tender-schedule-samples/{id}")
    public ResponseEntity<TenderScheduleSampleDTO> updateTenderScheduleSample(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody TenderScheduleSampleDTO tenderScheduleSampleDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TenderScheduleSample : {}, {}", id, tenderScheduleSampleDTO);
        if (tenderScheduleSampleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tenderScheduleSampleDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tenderScheduleSampleRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TenderScheduleSampleDTO result = tenderScheduleSampleService.save(tenderScheduleSampleDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tenderScheduleSampleDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tender-schedule-samples/:id} : Partial updates given fields of an existing tenderScheduleSample, field will ignore if it is null
     *
     * @param id the id of the tenderScheduleSampleDTO to save.
     * @param tenderScheduleSampleDTO the tenderScheduleSampleDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tenderScheduleSampleDTO,
     * or with status {@code 400 (Bad Request)} if the tenderScheduleSampleDTO is not valid,
     * or with status {@code 404 (Not Found)} if the tenderScheduleSampleDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tenderScheduleSampleDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tender-schedule-samples/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<TenderScheduleSampleDTO> partialUpdateTenderScheduleSample(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody TenderScheduleSampleDTO tenderScheduleSampleDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TenderScheduleSample partially : {}, {}", id, tenderScheduleSampleDTO);
        if (tenderScheduleSampleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tenderScheduleSampleDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tenderScheduleSampleRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TenderScheduleSampleDTO> result = tenderScheduleSampleService.partialUpdate(tenderScheduleSampleDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tenderScheduleSampleDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /tender-schedule-samples} : get all the tenderScheduleSamples.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tenderScheduleSamples in body.
     */
    @GetMapping("/tender-schedule-samples")
    public List<TenderScheduleSampleDTO> getAllTenderScheduleSamples() {
        log.debug("REST request to get all TenderScheduleSamples");
        return tenderScheduleSampleService.findAll();
    }

    /**
     * {@code GET  /tender-schedule-samples/:id} : get the "id" tenderScheduleSample.
     *
     * @param id the id of the tenderScheduleSampleDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tenderScheduleSampleDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tender-schedule-samples/{id}")
    public ResponseEntity<TenderScheduleSampleDTO> getTenderScheduleSample(@PathVariable Long id) {
        log.debug("REST request to get TenderScheduleSample : {}", id);
        Optional<TenderScheduleSampleDTO> tenderScheduleSampleDTO = tenderScheduleSampleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tenderScheduleSampleDTO);
    }

    /**
     * {@code DELETE  /tender-schedule-samples/:id} : delete the "id" tenderScheduleSample.
     *
     * @param id the id of the tenderScheduleSampleDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tender-schedule-samples/{id}")
    public ResponseEntity<Void> deleteTenderScheduleSample(@PathVariable Long id) {
        log.debug("REST request to delete TenderScheduleSample : {}", id);
        tenderScheduleSampleService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
