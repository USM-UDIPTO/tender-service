package com.dxc.eproc.tender.web.rest;

import com.dxc.eproc.tender.repository.TenderQueryResponseRepository;
import com.dxc.eproc.tender.service.TenderQueryResponseService;
import com.dxc.eproc.tender.service.dto.TenderQueryResponseDTO;
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
 * REST controller for managing {@link com.dxc.eproc.tender.domain.TenderQueryResponse}.
 */
@RestController
@RequestMapping("/api")
public class TenderQueryResponseResource {

    private final Logger log = LoggerFactory.getLogger(TenderQueryResponseResource.class);

    private static final String ENTITY_NAME = "tenderServiceTenderQueryResponse";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TenderQueryResponseService tenderQueryResponseService;

    private final TenderQueryResponseRepository tenderQueryResponseRepository;

    public TenderQueryResponseResource(
        TenderQueryResponseService tenderQueryResponseService,
        TenderQueryResponseRepository tenderQueryResponseRepository
    ) {
        this.tenderQueryResponseService = tenderQueryResponseService;
        this.tenderQueryResponseRepository = tenderQueryResponseRepository;
    }

    /**
     * {@code POST  /tender-query-responses} : Create a new tenderQueryResponse.
     *
     * @param tenderQueryResponseDTO the tenderQueryResponseDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tenderQueryResponseDTO, or with status {@code 400 (Bad Request)} if the tenderQueryResponse has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tender-query-responses")
    public ResponseEntity<TenderQueryResponseDTO> createTenderQueryResponse(
        @Valid @RequestBody TenderQueryResponseDTO tenderQueryResponseDTO
    ) throws URISyntaxException {
        log.debug("REST request to save TenderQueryResponse : {}", tenderQueryResponseDTO);
        if (tenderQueryResponseDTO.getId() != null) {
            throw new BadRequestAlertException("A new tenderQueryResponse cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TenderQueryResponseDTO result = tenderQueryResponseService.save(tenderQueryResponseDTO);
        return ResponseEntity
            .created(new URI("/api/tender-query-responses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tender-query-responses/:id} : Updates an existing tenderQueryResponse.
     *
     * @param id the id of the tenderQueryResponseDTO to save.
     * @param tenderQueryResponseDTO the tenderQueryResponseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tenderQueryResponseDTO,
     * or with status {@code 400 (Bad Request)} if the tenderQueryResponseDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tenderQueryResponseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tender-query-responses/{id}")
    public ResponseEntity<TenderQueryResponseDTO> updateTenderQueryResponse(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody TenderQueryResponseDTO tenderQueryResponseDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TenderQueryResponse : {}, {}", id, tenderQueryResponseDTO);
        if (tenderQueryResponseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tenderQueryResponseDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tenderQueryResponseRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TenderQueryResponseDTO result = tenderQueryResponseService.save(tenderQueryResponseDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tenderQueryResponseDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tender-query-responses/:id} : Partial updates given fields of an existing tenderQueryResponse, field will ignore if it is null
     *
     * @param id the id of the tenderQueryResponseDTO to save.
     * @param tenderQueryResponseDTO the tenderQueryResponseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tenderQueryResponseDTO,
     * or with status {@code 400 (Bad Request)} if the tenderQueryResponseDTO is not valid,
     * or with status {@code 404 (Not Found)} if the tenderQueryResponseDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tenderQueryResponseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tender-query-responses/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<TenderQueryResponseDTO> partialUpdateTenderQueryResponse(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody TenderQueryResponseDTO tenderQueryResponseDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TenderQueryResponse partially : {}, {}", id, tenderQueryResponseDTO);
        if (tenderQueryResponseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tenderQueryResponseDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tenderQueryResponseRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TenderQueryResponseDTO> result = tenderQueryResponseService.partialUpdate(tenderQueryResponseDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tenderQueryResponseDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /tender-query-responses} : get all the tenderQueryResponses.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tenderQueryResponses in body.
     */
    @GetMapping("/tender-query-responses")
    public List<TenderQueryResponseDTO> getAllTenderQueryResponses() {
        log.debug("REST request to get all TenderQueryResponses");
        return tenderQueryResponseService.findAll();
    }

    /**
     * {@code GET  /tender-query-responses/:id} : get the "id" tenderQueryResponse.
     *
     * @param id the id of the tenderQueryResponseDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tenderQueryResponseDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tender-query-responses/{id}")
    public ResponseEntity<TenderQueryResponseDTO> getTenderQueryResponse(@PathVariable Long id) {
        log.debug("REST request to get TenderQueryResponse : {}", id);
        Optional<TenderQueryResponseDTO> tenderQueryResponseDTO = tenderQueryResponseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tenderQueryResponseDTO);
    }

    /**
     * {@code DELETE  /tender-query-responses/:id} : delete the "id" tenderQueryResponse.
     *
     * @param id the id of the tenderQueryResponseDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tender-query-responses/{id}")
    public ResponseEntity<Void> deleteTenderQueryResponse(@PathVariable Long id) {
        log.debug("REST request to delete TenderQueryResponse : {}", id);
        tenderQueryResponseService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
