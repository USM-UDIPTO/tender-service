package com.dxc.eproc.tender.web.rest;

import com.dxc.eproc.tender.repository.TenderScrutinyCommitteeRepository;
import com.dxc.eproc.tender.service.TenderScrutinyCommitteeService;
import com.dxc.eproc.tender.service.dto.TenderScrutinyCommitteeDTO;
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
 * REST controller for managing {@link com.dxc.eproc.tender.domain.TenderScrutinyCommittee}.
 */
@RestController
@RequestMapping("/api")
public class TenderScrutinyCommitteeResource {

    private final Logger log = LoggerFactory.getLogger(TenderScrutinyCommitteeResource.class);

    private static final String ENTITY_NAME = "tenderServiceTenderScrutinyCommittee";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TenderScrutinyCommitteeService tenderScrutinyCommitteeService;

    private final TenderScrutinyCommitteeRepository tenderScrutinyCommitteeRepository;

    public TenderScrutinyCommitteeResource(
        TenderScrutinyCommitteeService tenderScrutinyCommitteeService,
        TenderScrutinyCommitteeRepository tenderScrutinyCommitteeRepository
    ) {
        this.tenderScrutinyCommitteeService = tenderScrutinyCommitteeService;
        this.tenderScrutinyCommitteeRepository = tenderScrutinyCommitteeRepository;
    }

    /**
     * {@code POST  /tender-scrutiny-committees} : Create a new tenderScrutinyCommittee.
     *
     * @param tenderScrutinyCommitteeDTO the tenderScrutinyCommitteeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tenderScrutinyCommitteeDTO, or with status {@code 400 (Bad Request)} if the tenderScrutinyCommittee has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tender-scrutiny-committees")
    public ResponseEntity<TenderScrutinyCommitteeDTO> createTenderScrutinyCommittee(
        @Valid @RequestBody TenderScrutinyCommitteeDTO tenderScrutinyCommitteeDTO
    ) throws URISyntaxException {
        log.debug("REST request to save TenderScrutinyCommittee : {}", tenderScrutinyCommitteeDTO);
        if (tenderScrutinyCommitteeDTO.getId() != null) {
            throw new BadRequestAlertException("A new tenderScrutinyCommittee cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TenderScrutinyCommitteeDTO result = tenderScrutinyCommitteeService.save(tenderScrutinyCommitteeDTO);
        return ResponseEntity
            .created(new URI("/api/tender-scrutiny-committees/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tender-scrutiny-committees/:id} : Updates an existing tenderScrutinyCommittee.
     *
     * @param id the id of the tenderScrutinyCommitteeDTO to save.
     * @param tenderScrutinyCommitteeDTO the tenderScrutinyCommitteeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tenderScrutinyCommitteeDTO,
     * or with status {@code 400 (Bad Request)} if the tenderScrutinyCommitteeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tenderScrutinyCommitteeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tender-scrutiny-committees/{id}")
    public ResponseEntity<TenderScrutinyCommitteeDTO> updateTenderScrutinyCommittee(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody TenderScrutinyCommitteeDTO tenderScrutinyCommitteeDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TenderScrutinyCommittee : {}, {}", id, tenderScrutinyCommitteeDTO);
        if (tenderScrutinyCommitteeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tenderScrutinyCommitteeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tenderScrutinyCommitteeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TenderScrutinyCommitteeDTO result = tenderScrutinyCommitteeService.save(tenderScrutinyCommitteeDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tenderScrutinyCommitteeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tender-scrutiny-committees/:id} : Partial updates given fields of an existing tenderScrutinyCommittee, field will ignore if it is null
     *
     * @param id the id of the tenderScrutinyCommitteeDTO to save.
     * @param tenderScrutinyCommitteeDTO the tenderScrutinyCommitteeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tenderScrutinyCommitteeDTO,
     * or with status {@code 400 (Bad Request)} if the tenderScrutinyCommitteeDTO is not valid,
     * or with status {@code 404 (Not Found)} if the tenderScrutinyCommitteeDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tenderScrutinyCommitteeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tender-scrutiny-committees/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<TenderScrutinyCommitteeDTO> partialUpdateTenderScrutinyCommittee(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody TenderScrutinyCommitteeDTO tenderScrutinyCommitteeDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TenderScrutinyCommittee partially : {}, {}", id, tenderScrutinyCommitteeDTO);
        if (tenderScrutinyCommitteeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tenderScrutinyCommitteeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tenderScrutinyCommitteeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TenderScrutinyCommitteeDTO> result = tenderScrutinyCommitteeService.partialUpdate(tenderScrutinyCommitteeDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tenderScrutinyCommitteeDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /tender-scrutiny-committees} : get all the tenderScrutinyCommittees.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tenderScrutinyCommittees in body.
     */
    @GetMapping("/tender-scrutiny-committees")
    public List<TenderScrutinyCommitteeDTO> getAllTenderScrutinyCommittees() {
        log.debug("REST request to get all TenderScrutinyCommittees");
        return tenderScrutinyCommitteeService.findAll();
    }

    /**
     * {@code GET  /tender-scrutiny-committees/:id} : get the "id" tenderScrutinyCommittee.
     *
     * @param id the id of the tenderScrutinyCommitteeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tenderScrutinyCommitteeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tender-scrutiny-committees/{id}")
    public ResponseEntity<TenderScrutinyCommitteeDTO> getTenderScrutinyCommittee(@PathVariable Long id) {
        log.debug("REST request to get TenderScrutinyCommittee : {}", id);
        Optional<TenderScrutinyCommitteeDTO> tenderScrutinyCommitteeDTO = tenderScrutinyCommitteeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tenderScrutinyCommitteeDTO);
    }

    /**
     * {@code DELETE  /tender-scrutiny-committees/:id} : delete the "id" tenderScrutinyCommittee.
     *
     * @param id the id of the tenderScrutinyCommitteeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tender-scrutiny-committees/{id}")
    public ResponseEntity<Void> deleteTenderScrutinyCommittee(@PathVariable Long id) {
        log.debug("REST request to delete TenderScrutinyCommittee : {}", id);
        tenderScrutinyCommitteeService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
