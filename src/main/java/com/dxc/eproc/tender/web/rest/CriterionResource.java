package com.dxc.eproc.tender.web.rest;

import com.dxc.eproc.tender.repository.CriterionRepository;
import com.dxc.eproc.tender.service.CriterionService;
import com.dxc.eproc.tender.service.dto.CriterionDTO;
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
 * REST controller for managing {@link com.dxc.eproc.tender.domain.Criterion}.
 */
@RestController
@RequestMapping("/api")
public class CriterionResource {

    private final Logger log = LoggerFactory.getLogger(CriterionResource.class);

    private static final String ENTITY_NAME = "tenderServiceCriterion";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CriterionService criterionService;

    private final CriterionRepository criterionRepository;

    public CriterionResource(CriterionService criterionService, CriterionRepository criterionRepository) {
        this.criterionService = criterionService;
        this.criterionRepository = criterionRepository;
    }

    /**
     * {@code POST  /criteria} : Create a new criterion.
     *
     * @param criterionDTO the criterionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new criterionDTO, or with status {@code 400 (Bad Request)} if the criterion has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/criteria")
    public ResponseEntity<CriterionDTO> createCriterion(@Valid @RequestBody CriterionDTO criterionDTO) throws URISyntaxException {
        log.debug("REST request to save Criterion : {}", criterionDTO);
        if (criterionDTO.getId() != null) {
            throw new BadRequestAlertException("A new criterion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CriterionDTO result = criterionService.save(criterionDTO);
        return ResponseEntity
            .created(new URI("/api/criteria/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /criteria/:id} : Updates an existing criterion.
     *
     * @param id the id of the criterionDTO to save.
     * @param criterionDTO the criterionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated criterionDTO,
     * or with status {@code 400 (Bad Request)} if the criterionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the criterionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/criteria/{id}")
    public ResponseEntity<CriterionDTO> updateCriterion(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody CriterionDTO criterionDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Criterion : {}, {}", id, criterionDTO);
        if (criterionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, criterionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!criterionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        CriterionDTO result = criterionService.save(criterionDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, criterionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /criteria/:id} : Partial updates given fields of an existing criterion, field will ignore if it is null
     *
     * @param id the id of the criterionDTO to save.
     * @param criterionDTO the criterionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated criterionDTO,
     * or with status {@code 400 (Bad Request)} if the criterionDTO is not valid,
     * or with status {@code 404 (Not Found)} if the criterionDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the criterionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/criteria/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<CriterionDTO> partialUpdateCriterion(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody CriterionDTO criterionDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Criterion partially : {}, {}", id, criterionDTO);
        if (criterionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, criterionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!criterionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CriterionDTO> result = criterionService.partialUpdate(criterionDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, criterionDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /criteria} : get all the criteria.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of criteria in body.
     */
    @GetMapping("/criteria")
    public List<CriterionDTO> getAllCriteria() {
        log.debug("REST request to get all Criteria");
        return criterionService.findAll();
    }

    /**
     * {@code GET  /criteria/:id} : get the "id" criterion.
     *
     * @param id the id of the criterionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the criterionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/criteria/{id}")
    public ResponseEntity<CriterionDTO> getCriterion(@PathVariable Long id) {
        log.debug("REST request to get Criterion : {}", id);
        Optional<CriterionDTO> criterionDTO = criterionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(criterionDTO);
    }

    /**
     * {@code DELETE  /criteria/:id} : delete the "id" criterion.
     *
     * @param id the id of the criterionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/criteria/{id}")
    public ResponseEntity<Void> deleteCriterion(@PathVariable Long id) {
        log.debug("REST request to delete Criterion : {}", id);
        criterionService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
