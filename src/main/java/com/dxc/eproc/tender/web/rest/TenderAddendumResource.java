package com.dxc.eproc.tender.web.rest;

import com.dxc.eproc.tender.repository.TenderAddendumRepository;
import com.dxc.eproc.tender.service.TenderAddendumService;
import com.dxc.eproc.tender.service.dto.TenderAddendumDTO;
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
 * REST controller for managing {@link com.dxc.eproc.tender.domain.TenderAddendum}.
 */
@RestController
@RequestMapping("/api")
public class TenderAddendumResource {

    private final Logger log = LoggerFactory.getLogger(TenderAddendumResource.class);

    private static final String ENTITY_NAME = "tenderServiceTenderAddendum";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TenderAddendumService tenderAddendumService;

    private final TenderAddendumRepository tenderAddendumRepository;

    public TenderAddendumResource(TenderAddendumService tenderAddendumService, TenderAddendumRepository tenderAddendumRepository) {
        this.tenderAddendumService = tenderAddendumService;
        this.tenderAddendumRepository = tenderAddendumRepository;
    }

    /**
     * {@code POST  /tender-addenda} : Create a new tenderAddendum.
     *
     * @param tenderAddendumDTO the tenderAddendumDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tenderAddendumDTO, or with status {@code 400 (Bad Request)} if the tenderAddendum has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tender-addenda")
    public ResponseEntity<TenderAddendumDTO> createTenderAddendum(@Valid @RequestBody TenderAddendumDTO tenderAddendumDTO)
        throws URISyntaxException {
        log.debug("REST request to save TenderAddendum : {}", tenderAddendumDTO);
        if (tenderAddendumDTO.getId() != null) {
            throw new BadRequestAlertException("A new tenderAddendum cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TenderAddendumDTO result = tenderAddendumService.save(tenderAddendumDTO);
        return ResponseEntity
            .created(new URI("/api/tender-addenda/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tender-addenda/:id} : Updates an existing tenderAddendum.
     *
     * @param id the id of the tenderAddendumDTO to save.
     * @param tenderAddendumDTO the tenderAddendumDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tenderAddendumDTO,
     * or with status {@code 400 (Bad Request)} if the tenderAddendumDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tenderAddendumDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tender-addenda/{id}")
    public ResponseEntity<TenderAddendumDTO> updateTenderAddendum(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody TenderAddendumDTO tenderAddendumDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TenderAddendum : {}, {}", id, tenderAddendumDTO);
        if (tenderAddendumDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tenderAddendumDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tenderAddendumRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TenderAddendumDTO result = tenderAddendumService.save(tenderAddendumDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tenderAddendumDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tender-addenda/:id} : Partial updates given fields of an existing tenderAddendum, field will ignore if it is null
     *
     * @param id the id of the tenderAddendumDTO to save.
     * @param tenderAddendumDTO the tenderAddendumDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tenderAddendumDTO,
     * or with status {@code 400 (Bad Request)} if the tenderAddendumDTO is not valid,
     * or with status {@code 404 (Not Found)} if the tenderAddendumDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tenderAddendumDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tender-addenda/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<TenderAddendumDTO> partialUpdateTenderAddendum(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody TenderAddendumDTO tenderAddendumDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TenderAddendum partially : {}, {}", id, tenderAddendumDTO);
        if (tenderAddendumDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tenderAddendumDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tenderAddendumRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TenderAddendumDTO> result = tenderAddendumService.partialUpdate(tenderAddendumDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tenderAddendumDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /tender-addenda} : get all the tenderAddenda.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tenderAddenda in body.
     */
    @GetMapping("/tender-addenda")
    public List<TenderAddendumDTO> getAllTenderAddenda() {
        log.debug("REST request to get all TenderAddenda");
        return tenderAddendumService.findAll();
    }

    /**
     * {@code GET  /tender-addenda/:id} : get the "id" tenderAddendum.
     *
     * @param id the id of the tenderAddendumDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tenderAddendumDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tender-addenda/{id}")
    public ResponseEntity<TenderAddendumDTO> getTenderAddendum(@PathVariable Long id) {
        log.debug("REST request to get TenderAddendum : {}", id);
        Optional<TenderAddendumDTO> tenderAddendumDTO = tenderAddendumService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tenderAddendumDTO);
    }

    /**
     * {@code DELETE  /tender-addenda/:id} : delete the "id" tenderAddendum.
     *
     * @param id the id of the tenderAddendumDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tender-addenda/{id}")
    public ResponseEntity<Void> deleteTenderAddendum(@PathVariable Long id) {
        log.debug("REST request to delete TenderAddendum : {}", id);
        tenderAddendumService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
