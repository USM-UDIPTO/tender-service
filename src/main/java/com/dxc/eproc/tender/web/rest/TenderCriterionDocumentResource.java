package com.dxc.eproc.tender.web.rest;

import com.dxc.eproc.tender.repository.TenderCriterionDocumentRepository;
import com.dxc.eproc.tender.service.TenderCriterionDocumentService;
import com.dxc.eproc.tender.service.dto.TenderCriterionDocumentDTO;
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
 * REST controller for managing {@link com.dxc.eproc.tender.domain.TenderCriterionDocument}.
 */
@RestController
@RequestMapping("/api")
public class TenderCriterionDocumentResource {

    private final Logger log = LoggerFactory.getLogger(TenderCriterionDocumentResource.class);

    private static final String ENTITY_NAME = "tenderServiceTenderCriterionDocument";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TenderCriterionDocumentService tenderCriterionDocumentService;

    private final TenderCriterionDocumentRepository tenderCriterionDocumentRepository;

    public TenderCriterionDocumentResource(
        TenderCriterionDocumentService tenderCriterionDocumentService,
        TenderCriterionDocumentRepository tenderCriterionDocumentRepository
    ) {
        this.tenderCriterionDocumentService = tenderCriterionDocumentService;
        this.tenderCriterionDocumentRepository = tenderCriterionDocumentRepository;
    }

    /**
     * {@code POST  /tender-criterion-documents} : Create a new tenderCriterionDocument.
     *
     * @param tenderCriterionDocumentDTO the tenderCriterionDocumentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tenderCriterionDocumentDTO, or with status {@code 400 (Bad Request)} if the tenderCriterionDocument has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tender-criterion-documents")
    public ResponseEntity<TenderCriterionDocumentDTO> createTenderCriterionDocument(
        @Valid @RequestBody TenderCriterionDocumentDTO tenderCriterionDocumentDTO
    ) throws URISyntaxException {
        log.debug("REST request to save TenderCriterionDocument : {}", tenderCriterionDocumentDTO);
        if (tenderCriterionDocumentDTO.getId() != null) {
            throw new BadRequestAlertException("A new tenderCriterionDocument cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TenderCriterionDocumentDTO result = tenderCriterionDocumentService.save(tenderCriterionDocumentDTO);
        return ResponseEntity
            .created(new URI("/api/tender-criterion-documents/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tender-criterion-documents/:id} : Updates an existing tenderCriterionDocument.
     *
     * @param id the id of the tenderCriterionDocumentDTO to save.
     * @param tenderCriterionDocumentDTO the tenderCriterionDocumentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tenderCriterionDocumentDTO,
     * or with status {@code 400 (Bad Request)} if the tenderCriterionDocumentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tenderCriterionDocumentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tender-criterion-documents/{id}")
    public ResponseEntity<TenderCriterionDocumentDTO> updateTenderCriterionDocument(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody TenderCriterionDocumentDTO tenderCriterionDocumentDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TenderCriterionDocument : {}, {}", id, tenderCriterionDocumentDTO);
        if (tenderCriterionDocumentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tenderCriterionDocumentDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tenderCriterionDocumentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TenderCriterionDocumentDTO result = tenderCriterionDocumentService.save(tenderCriterionDocumentDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tenderCriterionDocumentDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tender-criterion-documents/:id} : Partial updates given fields of an existing tenderCriterionDocument, field will ignore if it is null
     *
     * @param id the id of the tenderCriterionDocumentDTO to save.
     * @param tenderCriterionDocumentDTO the tenderCriterionDocumentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tenderCriterionDocumentDTO,
     * or with status {@code 400 (Bad Request)} if the tenderCriterionDocumentDTO is not valid,
     * or with status {@code 404 (Not Found)} if the tenderCriterionDocumentDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tenderCriterionDocumentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tender-criterion-documents/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<TenderCriterionDocumentDTO> partialUpdateTenderCriterionDocument(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody TenderCriterionDocumentDTO tenderCriterionDocumentDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TenderCriterionDocument partially : {}, {}", id, tenderCriterionDocumentDTO);
        if (tenderCriterionDocumentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tenderCriterionDocumentDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tenderCriterionDocumentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TenderCriterionDocumentDTO> result = tenderCriterionDocumentService.partialUpdate(tenderCriterionDocumentDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tenderCriterionDocumentDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /tender-criterion-documents} : get all the tenderCriterionDocuments.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tenderCriterionDocuments in body.
     */
    @GetMapping("/tender-criterion-documents")
    public List<TenderCriterionDocumentDTO> getAllTenderCriterionDocuments() {
        log.debug("REST request to get all TenderCriterionDocuments");
        return tenderCriterionDocumentService.findAll();
    }

    /**
     * {@code GET  /tender-criterion-documents/:id} : get the "id" tenderCriterionDocument.
     *
     * @param id the id of the tenderCriterionDocumentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tenderCriterionDocumentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tender-criterion-documents/{id}")
    public ResponseEntity<TenderCriterionDocumentDTO> getTenderCriterionDocument(@PathVariable Long id) {
        log.debug("REST request to get TenderCriterionDocument : {}", id);
        Optional<TenderCriterionDocumentDTO> tenderCriterionDocumentDTO = tenderCriterionDocumentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tenderCriterionDocumentDTO);
    }

    /**
     * {@code DELETE  /tender-criterion-documents/:id} : delete the "id" tenderCriterionDocument.
     *
     * @param id the id of the tenderCriterionDocumentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tender-criterion-documents/{id}")
    public ResponseEntity<Void> deleteTenderCriterionDocument(@PathVariable Long id) {
        log.debug("REST request to delete TenderCriterionDocument : {}", id);
        tenderCriterionDocumentService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
