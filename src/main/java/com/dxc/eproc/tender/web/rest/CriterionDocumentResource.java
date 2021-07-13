package com.dxc.eproc.tender.web.rest;

import com.dxc.eproc.tender.repository.CriterionDocumentRepository;
import com.dxc.eproc.tender.service.CriterionDocumentService;
import com.dxc.eproc.tender.service.dto.CriterionDocumentDTO;
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
 * REST controller for managing {@link com.dxc.eproc.tender.domain.CriterionDocument}.
 */
@RestController
@RequestMapping("/api")
public class CriterionDocumentResource {

    private final Logger log = LoggerFactory.getLogger(CriterionDocumentResource.class);

    private static final String ENTITY_NAME = "tenderServiceCriterionDocument";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CriterionDocumentService criterionDocumentService;

    private final CriterionDocumentRepository criterionDocumentRepository;

    public CriterionDocumentResource(
        CriterionDocumentService criterionDocumentService,
        CriterionDocumentRepository criterionDocumentRepository
    ) {
        this.criterionDocumentService = criterionDocumentService;
        this.criterionDocumentRepository = criterionDocumentRepository;
    }

    /**
     * {@code POST  /criterion-documents} : Create a new criterionDocument.
     *
     * @param criterionDocumentDTO the criterionDocumentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new criterionDocumentDTO, or with status {@code 400 (Bad Request)} if the criterionDocument has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/criterion-documents")
    public ResponseEntity<CriterionDocumentDTO> createCriterionDocument(@Valid @RequestBody CriterionDocumentDTO criterionDocumentDTO)
        throws URISyntaxException {
        log.debug("REST request to save CriterionDocument : {}", criterionDocumentDTO);
        if (criterionDocumentDTO.getId() != null) {
            throw new BadRequestAlertException("A new criterionDocument cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CriterionDocumentDTO result = criterionDocumentService.save(criterionDocumentDTO);
        return ResponseEntity
            .created(new URI("/api/criterion-documents/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /criterion-documents/:id} : Updates an existing criterionDocument.
     *
     * @param id the id of the criterionDocumentDTO to save.
     * @param criterionDocumentDTO the criterionDocumentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated criterionDocumentDTO,
     * or with status {@code 400 (Bad Request)} if the criterionDocumentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the criterionDocumentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/criterion-documents/{id}")
    public ResponseEntity<CriterionDocumentDTO> updateCriterionDocument(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody CriterionDocumentDTO criterionDocumentDTO
    ) throws URISyntaxException {
        log.debug("REST request to update CriterionDocument : {}, {}", id, criterionDocumentDTO);
        if (criterionDocumentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, criterionDocumentDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!criterionDocumentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        CriterionDocumentDTO result = criterionDocumentService.save(criterionDocumentDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, criterionDocumentDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /criterion-documents/:id} : Partial updates given fields of an existing criterionDocument, field will ignore if it is null
     *
     * @param id the id of the criterionDocumentDTO to save.
     * @param criterionDocumentDTO the criterionDocumentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated criterionDocumentDTO,
     * or with status {@code 400 (Bad Request)} if the criterionDocumentDTO is not valid,
     * or with status {@code 404 (Not Found)} if the criterionDocumentDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the criterionDocumentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/criterion-documents/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<CriterionDocumentDTO> partialUpdateCriterionDocument(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody CriterionDocumentDTO criterionDocumentDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update CriterionDocument partially : {}, {}", id, criterionDocumentDTO);
        if (criterionDocumentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, criterionDocumentDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!criterionDocumentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CriterionDocumentDTO> result = criterionDocumentService.partialUpdate(criterionDocumentDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, criterionDocumentDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /criterion-documents} : get all the criterionDocuments.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of criterionDocuments in body.
     */
    @GetMapping("/criterion-documents")
    public List<CriterionDocumentDTO> getAllCriterionDocuments() {
        log.debug("REST request to get all CriterionDocuments");
        return criterionDocumentService.findAll();
    }

    /**
     * {@code GET  /criterion-documents/:id} : get the "id" criterionDocument.
     *
     * @param id the id of the criterionDocumentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the criterionDocumentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/criterion-documents/{id}")
    public ResponseEntity<CriterionDocumentDTO> getCriterionDocument(@PathVariable Long id) {
        log.debug("REST request to get CriterionDocument : {}", id);
        Optional<CriterionDocumentDTO> criterionDocumentDTO = criterionDocumentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(criterionDocumentDTO);
    }

    /**
     * {@code DELETE  /criterion-documents/:id} : delete the "id" criterionDocument.
     *
     * @param id the id of the criterionDocumentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/criterion-documents/{id}")
    public ResponseEntity<Void> deleteCriterionDocument(@PathVariable Long id) {
        log.debug("REST request to delete CriterionDocument : {}", id);
        criterionDocumentService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
