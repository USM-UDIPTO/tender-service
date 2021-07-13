package com.dxc.eproc.tender.web.rest;

import com.dxc.eproc.tender.repository.TenderCriterionParameterRepository;
import com.dxc.eproc.tender.service.TenderCriterionParameterService;
import com.dxc.eproc.tender.service.dto.TenderCriterionParameterDTO;
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
 * REST controller for managing {@link com.dxc.eproc.tender.domain.TenderCriterionParameter}.
 */
@RestController
@RequestMapping("/api")
public class TenderCriterionParameterResource {

    private final Logger log = LoggerFactory.getLogger(TenderCriterionParameterResource.class);

    private static final String ENTITY_NAME = "tenderServiceTenderCriterionParameter";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TenderCriterionParameterService tenderCriterionParameterService;

    private final TenderCriterionParameterRepository tenderCriterionParameterRepository;

    public TenderCriterionParameterResource(
        TenderCriterionParameterService tenderCriterionParameterService,
        TenderCriterionParameterRepository tenderCriterionParameterRepository
    ) {
        this.tenderCriterionParameterService = tenderCriterionParameterService;
        this.tenderCriterionParameterRepository = tenderCriterionParameterRepository;
    }

    /**
     * {@code POST  /tender-criterion-parameters} : Create a new tenderCriterionParameter.
     *
     * @param tenderCriterionParameterDTO the tenderCriterionParameterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tenderCriterionParameterDTO, or with status {@code 400 (Bad Request)} if the tenderCriterionParameter has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tender-criterion-parameters")
    public ResponseEntity<TenderCriterionParameterDTO> createTenderCriterionParameter(
        @Valid @RequestBody TenderCriterionParameterDTO tenderCriterionParameterDTO
    ) throws URISyntaxException {
        log.debug("REST request to save TenderCriterionParameter : {}", tenderCriterionParameterDTO);
        if (tenderCriterionParameterDTO.getId() != null) {
            throw new BadRequestAlertException("A new tenderCriterionParameter cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TenderCriterionParameterDTO result = tenderCriterionParameterService.save(tenderCriterionParameterDTO);
        return ResponseEntity
            .created(new URI("/api/tender-criterion-parameters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tender-criterion-parameters/:id} : Updates an existing tenderCriterionParameter.
     *
     * @param id the id of the tenderCriterionParameterDTO to save.
     * @param tenderCriterionParameterDTO the tenderCriterionParameterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tenderCriterionParameterDTO,
     * or with status {@code 400 (Bad Request)} if the tenderCriterionParameterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tenderCriterionParameterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tender-criterion-parameters/{id}")
    public ResponseEntity<TenderCriterionParameterDTO> updateTenderCriterionParameter(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody TenderCriterionParameterDTO tenderCriterionParameterDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TenderCriterionParameter : {}, {}", id, tenderCriterionParameterDTO);
        if (tenderCriterionParameterDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tenderCriterionParameterDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tenderCriterionParameterRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TenderCriterionParameterDTO result = tenderCriterionParameterService.save(tenderCriterionParameterDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tenderCriterionParameterDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tender-criterion-parameters/:id} : Partial updates given fields of an existing tenderCriterionParameter, field will ignore if it is null
     *
     * @param id the id of the tenderCriterionParameterDTO to save.
     * @param tenderCriterionParameterDTO the tenderCriterionParameterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tenderCriterionParameterDTO,
     * or with status {@code 400 (Bad Request)} if the tenderCriterionParameterDTO is not valid,
     * or with status {@code 404 (Not Found)} if the tenderCriterionParameterDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tenderCriterionParameterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tender-criterion-parameters/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<TenderCriterionParameterDTO> partialUpdateTenderCriterionParameter(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody TenderCriterionParameterDTO tenderCriterionParameterDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TenderCriterionParameter partially : {}, {}", id, tenderCriterionParameterDTO);
        if (tenderCriterionParameterDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tenderCriterionParameterDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tenderCriterionParameterRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TenderCriterionParameterDTO> result = tenderCriterionParameterService.partialUpdate(tenderCriterionParameterDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tenderCriterionParameterDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /tender-criterion-parameters} : get all the tenderCriterionParameters.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tenderCriterionParameters in body.
     */
    @GetMapping("/tender-criterion-parameters")
    public List<TenderCriterionParameterDTO> getAllTenderCriterionParameters() {
        log.debug("REST request to get all TenderCriterionParameters");
        return tenderCriterionParameterService.findAll();
    }

    /**
     * {@code GET  /tender-criterion-parameters/:id} : get the "id" tenderCriterionParameter.
     *
     * @param id the id of the tenderCriterionParameterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tenderCriterionParameterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tender-criterion-parameters/{id}")
    public ResponseEntity<TenderCriterionParameterDTO> getTenderCriterionParameter(@PathVariable Long id) {
        log.debug("REST request to get TenderCriterionParameter : {}", id);
        Optional<TenderCriterionParameterDTO> tenderCriterionParameterDTO = tenderCriterionParameterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tenderCriterionParameterDTO);
    }

    /**
     * {@code DELETE  /tender-criterion-parameters/:id} : delete the "id" tenderCriterionParameter.
     *
     * @param id the id of the tenderCriterionParameterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tender-criterion-parameters/{id}")
    public ResponseEntity<Void> deleteTenderCriterionParameter(@PathVariable Long id) {
        log.debug("REST request to delete TenderCriterionParameter : {}", id);
        tenderCriterionParameterService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
