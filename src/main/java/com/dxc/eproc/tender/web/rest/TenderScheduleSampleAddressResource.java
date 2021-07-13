package com.dxc.eproc.tender.web.rest;

import com.dxc.eproc.tender.repository.TenderScheduleSampleAddressRepository;
import com.dxc.eproc.tender.service.TenderScheduleSampleAddressService;
import com.dxc.eproc.tender.service.dto.TenderScheduleSampleAddressDTO;
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
 * REST controller for managing {@link com.dxc.eproc.tender.domain.TenderScheduleSampleAddress}.
 */
@RestController
@RequestMapping("/api")
public class TenderScheduleSampleAddressResource {

    private final Logger log = LoggerFactory.getLogger(TenderScheduleSampleAddressResource.class);

    private static final String ENTITY_NAME = "tenderServiceTenderScheduleSampleAddress";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TenderScheduleSampleAddressService tenderScheduleSampleAddressService;

    private final TenderScheduleSampleAddressRepository tenderScheduleSampleAddressRepository;

    public TenderScheduleSampleAddressResource(
        TenderScheduleSampleAddressService tenderScheduleSampleAddressService,
        TenderScheduleSampleAddressRepository tenderScheduleSampleAddressRepository
    ) {
        this.tenderScheduleSampleAddressService = tenderScheduleSampleAddressService;
        this.tenderScheduleSampleAddressRepository = tenderScheduleSampleAddressRepository;
    }

    /**
     * {@code POST  /tender-schedule-sample-addresses} : Create a new tenderScheduleSampleAddress.
     *
     * @param tenderScheduleSampleAddressDTO the tenderScheduleSampleAddressDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tenderScheduleSampleAddressDTO, or with status {@code 400 (Bad Request)} if the tenderScheduleSampleAddress has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tender-schedule-sample-addresses")
    public ResponseEntity<TenderScheduleSampleAddressDTO> createTenderScheduleSampleAddress(
        @Valid @RequestBody TenderScheduleSampleAddressDTO tenderScheduleSampleAddressDTO
    ) throws URISyntaxException {
        log.debug("REST request to save TenderScheduleSampleAddress : {}", tenderScheduleSampleAddressDTO);
        if (tenderScheduleSampleAddressDTO.getId() != null) {
            throw new BadRequestAlertException("A new tenderScheduleSampleAddress cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TenderScheduleSampleAddressDTO result = tenderScheduleSampleAddressService.save(tenderScheduleSampleAddressDTO);
        return ResponseEntity
            .created(new URI("/api/tender-schedule-sample-addresses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tender-schedule-sample-addresses/:id} : Updates an existing tenderScheduleSampleAddress.
     *
     * @param id the id of the tenderScheduleSampleAddressDTO to save.
     * @param tenderScheduleSampleAddressDTO the tenderScheduleSampleAddressDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tenderScheduleSampleAddressDTO,
     * or with status {@code 400 (Bad Request)} if the tenderScheduleSampleAddressDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tenderScheduleSampleAddressDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tender-schedule-sample-addresses/{id}")
    public ResponseEntity<TenderScheduleSampleAddressDTO> updateTenderScheduleSampleAddress(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody TenderScheduleSampleAddressDTO tenderScheduleSampleAddressDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TenderScheduleSampleAddress : {}, {}", id, tenderScheduleSampleAddressDTO);
        if (tenderScheduleSampleAddressDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tenderScheduleSampleAddressDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tenderScheduleSampleAddressRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TenderScheduleSampleAddressDTO result = tenderScheduleSampleAddressService.save(tenderScheduleSampleAddressDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tenderScheduleSampleAddressDTO.getId().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /tender-schedule-sample-addresses/:id} : Partial updates given fields of an existing tenderScheduleSampleAddress, field will ignore if it is null
     *
     * @param id the id of the tenderScheduleSampleAddressDTO to save.
     * @param tenderScheduleSampleAddressDTO the tenderScheduleSampleAddressDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tenderScheduleSampleAddressDTO,
     * or with status {@code 400 (Bad Request)} if the tenderScheduleSampleAddressDTO is not valid,
     * or with status {@code 404 (Not Found)} if the tenderScheduleSampleAddressDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tenderScheduleSampleAddressDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tender-schedule-sample-addresses/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<TenderScheduleSampleAddressDTO> partialUpdateTenderScheduleSampleAddress(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody TenderScheduleSampleAddressDTO tenderScheduleSampleAddressDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TenderScheduleSampleAddress partially : {}, {}", id, tenderScheduleSampleAddressDTO);
        if (tenderScheduleSampleAddressDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tenderScheduleSampleAddressDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tenderScheduleSampleAddressRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TenderScheduleSampleAddressDTO> result = tenderScheduleSampleAddressService.partialUpdate(tenderScheduleSampleAddressDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tenderScheduleSampleAddressDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /tender-schedule-sample-addresses} : get all the tenderScheduleSampleAddresses.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tenderScheduleSampleAddresses in body.
     */
    @GetMapping("/tender-schedule-sample-addresses")
    public List<TenderScheduleSampleAddressDTO> getAllTenderScheduleSampleAddresses() {
        log.debug("REST request to get all TenderScheduleSampleAddresses");
        return tenderScheduleSampleAddressService.findAll();
    }

    /**
     * {@code GET  /tender-schedule-sample-addresses/:id} : get the "id" tenderScheduleSampleAddress.
     *
     * @param id the id of the tenderScheduleSampleAddressDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tenderScheduleSampleAddressDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tender-schedule-sample-addresses/{id}")
    public ResponseEntity<TenderScheduleSampleAddressDTO> getTenderScheduleSampleAddress(@PathVariable Long id) {
        log.debug("REST request to get TenderScheduleSampleAddress : {}", id);
        Optional<TenderScheduleSampleAddressDTO> tenderScheduleSampleAddressDTO = tenderScheduleSampleAddressService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tenderScheduleSampleAddressDTO);
    }

    /**
     * {@code DELETE  /tender-schedule-sample-addresses/:id} : delete the "id" tenderScheduleSampleAddress.
     *
     * @param id the id of the tenderScheduleSampleAddressDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tender-schedule-sample-addresses/{id}")
    public ResponseEntity<Void> deleteTenderScheduleSampleAddress(@PathVariable Long id) {
        log.debug("REST request to delete TenderScheduleSampleAddress : {}", id);
        tenderScheduleSampleAddressService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
