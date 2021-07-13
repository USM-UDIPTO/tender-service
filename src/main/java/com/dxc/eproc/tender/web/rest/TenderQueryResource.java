package com.dxc.eproc.tender.web.rest;

import com.dxc.eproc.tender.repository.TenderQueryRepository;
import com.dxc.eproc.tender.service.TenderQueryService;
import com.dxc.eproc.tender.service.dto.TenderQueryDTO;
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
 * REST controller for managing {@link com.dxc.eproc.tender.domain.TenderQuery}.
 */
@RestController
@RequestMapping("/api")
public class TenderQueryResource {

    private final Logger log = LoggerFactory.getLogger(TenderQueryResource.class);

    private static final String ENTITY_NAME = "tenderServiceTenderQuery";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TenderQueryService tenderQueryService;

    private final TenderQueryRepository tenderQueryRepository;

    public TenderQueryResource(TenderQueryService tenderQueryService, TenderQueryRepository tenderQueryRepository) {
        this.tenderQueryService = tenderQueryService;
        this.tenderQueryRepository = tenderQueryRepository;
    }

    /**
     * {@code POST  /tender-queries} : Create a new tenderQuery.
     *
     * @param tenderQueryDTO the tenderQueryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tenderQueryDTO, or with status {@code 400 (Bad Request)} if the tenderQuery has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tender-queries")
    public ResponseEntity<TenderQueryDTO> createTenderQuery(@Valid @RequestBody TenderQueryDTO tenderQueryDTO) throws URISyntaxException {
        log.debug("REST request to save TenderQuery : {}", tenderQueryDTO);
        if (tenderQueryDTO.getId() != null) {
            throw new BadRequestAlertException("A new tenderQuery cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TenderQueryDTO result = tenderQueryService.save(tenderQueryDTO);
        return ResponseEntity
            .created(new URI("/api/tender-queries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tender-queries/:id} : Updates an existing tenderQuery.
     *
     * @param id the id of the tenderQueryDTO to save.
     * @param tenderQueryDTO the tenderQueryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tenderQueryDTO,
     * or with status {@code 400 (Bad Request)} if the tenderQueryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tenderQueryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tender-queries/{id}")
    public ResponseEntity<TenderQueryDTO> updateTenderQuery(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody TenderQueryDTO tenderQueryDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TenderQuery : {}, {}", id, tenderQueryDTO);
        if (tenderQueryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tenderQueryDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tenderQueryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TenderQueryDTO result = tenderQueryService.save(tenderQueryDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tenderQueryDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tender-queries/:id} : Partial updates given fields of an existing tenderQuery, field will ignore if it is null
     *
     * @param id the id of the tenderQueryDTO to save.
     * @param tenderQueryDTO the tenderQueryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tenderQueryDTO,
     * or with status {@code 400 (Bad Request)} if the tenderQueryDTO is not valid,
     * or with status {@code 404 (Not Found)} if the tenderQueryDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tenderQueryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tender-queries/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<TenderQueryDTO> partialUpdateTenderQuery(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody TenderQueryDTO tenderQueryDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TenderQuery partially : {}, {}", id, tenderQueryDTO);
        if (tenderQueryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tenderQueryDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tenderQueryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TenderQueryDTO> result = tenderQueryService.partialUpdate(tenderQueryDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tenderQueryDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /tender-queries} : get all the tenderQueries.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tenderQueries in body.
     */
    @GetMapping("/tender-queries")
    public List<TenderQueryDTO> getAllTenderQueries() {
        log.debug("REST request to get all TenderQueries");
        return tenderQueryService.findAll();
    }

    /**
     * {@code GET  /tender-queries/:id} : get the "id" tenderQuery.
     *
     * @param id the id of the tenderQueryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tenderQueryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tender-queries/{id}")
    public ResponseEntity<TenderQueryDTO> getTenderQuery(@PathVariable Long id) {
        log.debug("REST request to get TenderQuery : {}", id);
        Optional<TenderQueryDTO> tenderQueryDTO = tenderQueryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tenderQueryDTO);
    }

    /**
     * {@code DELETE  /tender-queries/:id} : delete the "id" tenderQuery.
     *
     * @param id the id of the tenderQueryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tender-queries/{id}")
    public ResponseEntity<Void> deleteTenderQuery(@PathVariable Long id) {
        log.debug("REST request to delete TenderQuery : {}", id);
        tenderQueryService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
