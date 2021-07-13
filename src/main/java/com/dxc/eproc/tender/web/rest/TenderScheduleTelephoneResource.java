package com.dxc.eproc.tender.web.rest;

import com.dxc.eproc.tender.repository.TenderScheduleTelephoneRepository;
import com.dxc.eproc.tender.service.TenderScheduleTelephoneService;
import com.dxc.eproc.tender.service.dto.TenderScheduleTelephoneDTO;
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
 * REST controller for managing {@link com.dxc.eproc.tender.domain.TenderScheduleTelephone}.
 */
@RestController
@RequestMapping("/api")
public class TenderScheduleTelephoneResource {

    private final Logger log = LoggerFactory.getLogger(TenderScheduleTelephoneResource.class);

    private static final String ENTITY_NAME = "tenderServiceTenderScheduleTelephone";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TenderScheduleTelephoneService tenderScheduleTelephoneService;

    private final TenderScheduleTelephoneRepository tenderScheduleTelephoneRepository;

    public TenderScheduleTelephoneResource(
        TenderScheduleTelephoneService tenderScheduleTelephoneService,
        TenderScheduleTelephoneRepository tenderScheduleTelephoneRepository
    ) {
        this.tenderScheduleTelephoneService = tenderScheduleTelephoneService;
        this.tenderScheduleTelephoneRepository = tenderScheduleTelephoneRepository;
    }

    /**
     * {@code POST  /tender-schedule-telephones} : Create a new tenderScheduleTelephone.
     *
     * @param tenderScheduleTelephoneDTO the tenderScheduleTelephoneDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tenderScheduleTelephoneDTO, or with status {@code 400 (Bad Request)} if the tenderScheduleTelephone has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tender-schedule-telephones")
    public ResponseEntity<TenderScheduleTelephoneDTO> createTenderScheduleTelephone(
        @Valid @RequestBody TenderScheduleTelephoneDTO tenderScheduleTelephoneDTO
    ) throws URISyntaxException {
        log.debug("REST request to save TenderScheduleTelephone : {}", tenderScheduleTelephoneDTO);
        if (tenderScheduleTelephoneDTO.getId() != null) {
            throw new BadRequestAlertException("A new tenderScheduleTelephone cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TenderScheduleTelephoneDTO result = tenderScheduleTelephoneService.save(tenderScheduleTelephoneDTO);
        return ResponseEntity
            .created(new URI("/api/tender-schedule-telephones/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tender-schedule-telephones/:id} : Updates an existing tenderScheduleTelephone.
     *
     * @param id the id of the tenderScheduleTelephoneDTO to save.
     * @param tenderScheduleTelephoneDTO the tenderScheduleTelephoneDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tenderScheduleTelephoneDTO,
     * or with status {@code 400 (Bad Request)} if the tenderScheduleTelephoneDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tenderScheduleTelephoneDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tender-schedule-telephones/{id}")
    public ResponseEntity<TenderScheduleTelephoneDTO> updateTenderScheduleTelephone(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody TenderScheduleTelephoneDTO tenderScheduleTelephoneDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TenderScheduleTelephone : {}, {}", id, tenderScheduleTelephoneDTO);
        if (tenderScheduleTelephoneDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tenderScheduleTelephoneDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tenderScheduleTelephoneRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TenderScheduleTelephoneDTO result = tenderScheduleTelephoneService.save(tenderScheduleTelephoneDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tenderScheduleTelephoneDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tender-schedule-telephones/:id} : Partial updates given fields of an existing tenderScheduleTelephone, field will ignore if it is null
     *
     * @param id the id of the tenderScheduleTelephoneDTO to save.
     * @param tenderScheduleTelephoneDTO the tenderScheduleTelephoneDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tenderScheduleTelephoneDTO,
     * or with status {@code 400 (Bad Request)} if the tenderScheduleTelephoneDTO is not valid,
     * or with status {@code 404 (Not Found)} if the tenderScheduleTelephoneDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tenderScheduleTelephoneDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tender-schedule-telephones/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<TenderScheduleTelephoneDTO> partialUpdateTenderScheduleTelephone(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody TenderScheduleTelephoneDTO tenderScheduleTelephoneDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TenderScheduleTelephone partially : {}, {}", id, tenderScheduleTelephoneDTO);
        if (tenderScheduleTelephoneDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tenderScheduleTelephoneDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tenderScheduleTelephoneRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TenderScheduleTelephoneDTO> result = tenderScheduleTelephoneService.partialUpdate(tenderScheduleTelephoneDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tenderScheduleTelephoneDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /tender-schedule-telephones} : get all the tenderScheduleTelephones.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tenderScheduleTelephones in body.
     */
    @GetMapping("/tender-schedule-telephones")
    public List<TenderScheduleTelephoneDTO> getAllTenderScheduleTelephones() {
        log.debug("REST request to get all TenderScheduleTelephones");
        return tenderScheduleTelephoneService.findAll();
    }

    /**
     * {@code GET  /tender-schedule-telephones/:id} : get the "id" tenderScheduleTelephone.
     *
     * @param id the id of the tenderScheduleTelephoneDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tenderScheduleTelephoneDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tender-schedule-telephones/{id}")
    public ResponseEntity<TenderScheduleTelephoneDTO> getTenderScheduleTelephone(@PathVariable Long id) {
        log.debug("REST request to get TenderScheduleTelephone : {}", id);
        Optional<TenderScheduleTelephoneDTO> tenderScheduleTelephoneDTO = tenderScheduleTelephoneService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tenderScheduleTelephoneDTO);
    }

    /**
     * {@code DELETE  /tender-schedule-telephones/:id} : delete the "id" tenderScheduleTelephone.
     *
     * @param id the id of the tenderScheduleTelephoneDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tender-schedule-telephones/{id}")
    public ResponseEntity<Void> deleteTenderScheduleTelephone(@PathVariable Long id) {
        log.debug("REST request to delete TenderScheduleTelephone : {}", id);
        tenderScheduleTelephoneService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
