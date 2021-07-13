package com.dxc.eproc.tender.web.rest;

import com.dxc.eproc.tender.repository.TenderCorrigendumDetailsRepository;
import com.dxc.eproc.tender.service.TenderCorrigendumDetailsService;
import com.dxc.eproc.tender.service.dto.TenderCorrigendumDetailsDTO;
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
 * REST controller for managing {@link com.dxc.eproc.tender.domain.TenderCorrigendumDetails}.
 */
@RestController
@RequestMapping("/api")
public class TenderCorrigendumDetailsResource {

    private final Logger log = LoggerFactory.getLogger(TenderCorrigendumDetailsResource.class);

    private static final String ENTITY_NAME = "tenderServiceTenderCorrigendumDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TenderCorrigendumDetailsService tenderCorrigendumDetailsService;

    private final TenderCorrigendumDetailsRepository tenderCorrigendumDetailsRepository;

    public TenderCorrigendumDetailsResource(
        TenderCorrigendumDetailsService tenderCorrigendumDetailsService,
        TenderCorrigendumDetailsRepository tenderCorrigendumDetailsRepository
    ) {
        this.tenderCorrigendumDetailsService = tenderCorrigendumDetailsService;
        this.tenderCorrigendumDetailsRepository = tenderCorrigendumDetailsRepository;
    }

    /**
     * {@code POST  /tender-corrigendum-details} : Create a new tenderCorrigendumDetails.
     *
     * @param tenderCorrigendumDetailsDTO the tenderCorrigendumDetailsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tenderCorrigendumDetailsDTO, or with status {@code 400 (Bad Request)} if the tenderCorrigendumDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tender-corrigendum-details")
    public ResponseEntity<TenderCorrigendumDetailsDTO> createTenderCorrigendumDetails(
        @Valid @RequestBody TenderCorrigendumDetailsDTO tenderCorrigendumDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to save TenderCorrigendumDetails : {}", tenderCorrigendumDetailsDTO);
        if (tenderCorrigendumDetailsDTO.getId() != null) {
            throw new BadRequestAlertException("A new tenderCorrigendumDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TenderCorrigendumDetailsDTO result = tenderCorrigendumDetailsService.save(tenderCorrigendumDetailsDTO);
        return ResponseEntity
            .created(new URI("/api/tender-corrigendum-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tender-corrigendum-details/:id} : Updates an existing tenderCorrigendumDetails.
     *
     * @param id the id of the tenderCorrigendumDetailsDTO to save.
     * @param tenderCorrigendumDetailsDTO the tenderCorrigendumDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tenderCorrigendumDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the tenderCorrigendumDetailsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tenderCorrigendumDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tender-corrigendum-details/{id}")
    public ResponseEntity<TenderCorrigendumDetailsDTO> updateTenderCorrigendumDetails(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody TenderCorrigendumDetailsDTO tenderCorrigendumDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TenderCorrigendumDetails : {}, {}", id, tenderCorrigendumDetailsDTO);
        if (tenderCorrigendumDetailsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tenderCorrigendumDetailsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tenderCorrigendumDetailsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TenderCorrigendumDetailsDTO result = tenderCorrigendumDetailsService.save(tenderCorrigendumDetailsDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tenderCorrigendumDetailsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tender-corrigendum-details/:id} : Partial updates given fields of an existing tenderCorrigendumDetails, field will ignore if it is null
     *
     * @param id the id of the tenderCorrigendumDetailsDTO to save.
     * @param tenderCorrigendumDetailsDTO the tenderCorrigendumDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tenderCorrigendumDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the tenderCorrigendumDetailsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the tenderCorrigendumDetailsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tenderCorrigendumDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tender-corrigendum-details/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<TenderCorrigendumDetailsDTO> partialUpdateTenderCorrigendumDetails(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody TenderCorrigendumDetailsDTO tenderCorrigendumDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TenderCorrigendumDetails partially : {}, {}", id, tenderCorrigendumDetailsDTO);
        if (tenderCorrigendumDetailsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tenderCorrigendumDetailsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tenderCorrigendumDetailsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TenderCorrigendumDetailsDTO> result = tenderCorrigendumDetailsService.partialUpdate(tenderCorrigendumDetailsDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tenderCorrigendumDetailsDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /tender-corrigendum-details} : get all the tenderCorrigendumDetails.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tenderCorrigendumDetails in body.
     */
    @GetMapping("/tender-corrigendum-details")
    public List<TenderCorrigendumDetailsDTO> getAllTenderCorrigendumDetails() {
        log.debug("REST request to get all TenderCorrigendumDetails");
        return tenderCorrigendumDetailsService.findAll();
    }

    /**
     * {@code GET  /tender-corrigendum-details/:id} : get the "id" tenderCorrigendumDetails.
     *
     * @param id the id of the tenderCorrigendumDetailsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tenderCorrigendumDetailsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tender-corrigendum-details/{id}")
    public ResponseEntity<TenderCorrigendumDetailsDTO> getTenderCorrigendumDetails(@PathVariable Long id) {
        log.debug("REST request to get TenderCorrigendumDetails : {}", id);
        Optional<TenderCorrigendumDetailsDTO> tenderCorrigendumDetailsDTO = tenderCorrigendumDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tenderCorrigendumDetailsDTO);
    }

    /**
     * {@code DELETE  /tender-corrigendum-details/:id} : delete the "id" tenderCorrigendumDetails.
     *
     * @param id the id of the tenderCorrigendumDetailsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tender-corrigendum-details/{id}")
    public ResponseEntity<Void> deleteTenderCorrigendumDetails(@PathVariable Long id) {
        log.debug("REST request to delete TenderCorrigendumDetails : {}", id);
        tenderCorrigendumDetailsService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
