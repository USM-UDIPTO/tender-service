package com.dxc.eproc.tender.web.rest;

import com.dxc.eproc.tender.repository.TenderCorrigendumRepository;
import com.dxc.eproc.tender.service.TenderCorrigendumService;
import com.dxc.eproc.tender.service.dto.TenderCorrigendumDTO;
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
 * REST controller for managing {@link com.dxc.eproc.tender.domain.TenderCorrigendum}.
 */
@RestController
@RequestMapping("/api")
public class TenderCorrigendumResource {

    private final Logger log = LoggerFactory.getLogger(TenderCorrigendumResource.class);

    private static final String ENTITY_NAME = "tenderServiceTenderCorrigendum";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TenderCorrigendumService tenderCorrigendumService;

    private final TenderCorrigendumRepository tenderCorrigendumRepository;

    public TenderCorrigendumResource(
        TenderCorrigendumService tenderCorrigendumService,
        TenderCorrigendumRepository tenderCorrigendumRepository
    ) {
        this.tenderCorrigendumService = tenderCorrigendumService;
        this.tenderCorrigendumRepository = tenderCorrigendumRepository;
    }

    /**
     * {@code POST  /tender-corrigendums} : Create a new tenderCorrigendum.
     *
     * @param tenderCorrigendumDTO the tenderCorrigendumDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tenderCorrigendumDTO, or with status {@code 400 (Bad Request)} if the tenderCorrigendum has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tender-corrigendums")
    public ResponseEntity<TenderCorrigendumDTO> createTenderCorrigendum(@Valid @RequestBody TenderCorrigendumDTO tenderCorrigendumDTO)
        throws URISyntaxException {
        log.debug("REST request to save TenderCorrigendum : {}", tenderCorrigendumDTO);
        if (tenderCorrigendumDTO.getId() != null) {
            throw new BadRequestAlertException("A new tenderCorrigendum cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TenderCorrigendumDTO result = tenderCorrigendumService.save(tenderCorrigendumDTO);
        return ResponseEntity
            .created(new URI("/api/tender-corrigendums/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tender-corrigendums/:id} : Updates an existing tenderCorrigendum.
     *
     * @param id the id of the tenderCorrigendumDTO to save.
     * @param tenderCorrigendumDTO the tenderCorrigendumDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tenderCorrigendumDTO,
     * or with status {@code 400 (Bad Request)} if the tenderCorrigendumDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tenderCorrigendumDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tender-corrigendums/{id}")
    public ResponseEntity<TenderCorrigendumDTO> updateTenderCorrigendum(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody TenderCorrigendumDTO tenderCorrigendumDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TenderCorrigendum : {}, {}", id, tenderCorrigendumDTO);
        if (tenderCorrigendumDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tenderCorrigendumDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tenderCorrigendumRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TenderCorrigendumDTO result = tenderCorrigendumService.save(tenderCorrigendumDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tenderCorrigendumDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tender-corrigendums/:id} : Partial updates given fields of an existing tenderCorrigendum, field will ignore if it is null
     *
     * @param id the id of the tenderCorrigendumDTO to save.
     * @param tenderCorrigendumDTO the tenderCorrigendumDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tenderCorrigendumDTO,
     * or with status {@code 400 (Bad Request)} if the tenderCorrigendumDTO is not valid,
     * or with status {@code 404 (Not Found)} if the tenderCorrigendumDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tenderCorrigendumDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tender-corrigendums/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<TenderCorrigendumDTO> partialUpdateTenderCorrigendum(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody TenderCorrigendumDTO tenderCorrigendumDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TenderCorrigendum partially : {}, {}", id, tenderCorrigendumDTO);
        if (tenderCorrigendumDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tenderCorrigendumDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tenderCorrigendumRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TenderCorrigendumDTO> result = tenderCorrigendumService.partialUpdate(tenderCorrigendumDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tenderCorrigendumDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /tender-corrigendums} : get all the tenderCorrigendums.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tenderCorrigendums in body.
     */
    @GetMapping("/tender-corrigendums")
    public List<TenderCorrigendumDTO> getAllTenderCorrigendums() {
        log.debug("REST request to get all TenderCorrigendums");
        return tenderCorrigendumService.findAll();
    }

    /**
     * {@code GET  /tender-corrigendums/:id} : get the "id" tenderCorrigendum.
     *
     * @param id the id of the tenderCorrigendumDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tenderCorrigendumDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tender-corrigendums/{id}")
    public ResponseEntity<TenderCorrigendumDTO> getTenderCorrigendum(@PathVariable Long id) {
        log.debug("REST request to get TenderCorrigendum : {}", id);
        Optional<TenderCorrigendumDTO> tenderCorrigendumDTO = tenderCorrigendumService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tenderCorrigendumDTO);
    }

    /**
     * {@code DELETE  /tender-corrigendums/:id} : delete the "id" tenderCorrigendum.
     *
     * @param id the id of the tenderCorrigendumDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tender-corrigendums/{id}")
    public ResponseEntity<Void> deleteTenderCorrigendum(@PathVariable Long id) {
        log.debug("REST request to delete TenderCorrigendum : {}", id);
        tenderCorrigendumService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
