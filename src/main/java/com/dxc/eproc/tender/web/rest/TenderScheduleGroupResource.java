package com.dxc.eproc.tender.web.rest;

import com.dxc.eproc.tender.repository.TenderScheduleGroupRepository;
import com.dxc.eproc.tender.service.TenderScheduleGroupService;
import com.dxc.eproc.tender.service.dto.TenderScheduleGroupDTO;
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
 * REST controller for managing {@link com.dxc.eproc.tender.domain.TenderScheduleGroup}.
 */
@RestController
@RequestMapping("/api")
public class TenderScheduleGroupResource {

    private final Logger log = LoggerFactory.getLogger(TenderScheduleGroupResource.class);

    private static final String ENTITY_NAME = "tenderServiceTenderScheduleGroup";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TenderScheduleGroupService tenderScheduleGroupService;

    private final TenderScheduleGroupRepository tenderScheduleGroupRepository;

    public TenderScheduleGroupResource(
        TenderScheduleGroupService tenderScheduleGroupService,
        TenderScheduleGroupRepository tenderScheduleGroupRepository
    ) {
        this.tenderScheduleGroupService = tenderScheduleGroupService;
        this.tenderScheduleGroupRepository = tenderScheduleGroupRepository;
    }

    /**
     * {@code POST  /tender-schedule-groups} : Create a new tenderScheduleGroup.
     *
     * @param tenderScheduleGroupDTO the tenderScheduleGroupDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tenderScheduleGroupDTO, or with status {@code 400 (Bad Request)} if the tenderScheduleGroup has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tender-schedule-groups")
    public ResponseEntity<TenderScheduleGroupDTO> createTenderScheduleGroup(
        @Valid @RequestBody TenderScheduleGroupDTO tenderScheduleGroupDTO
    ) throws URISyntaxException {
        log.debug("REST request to save TenderScheduleGroup : {}", tenderScheduleGroupDTO);
        if (tenderScheduleGroupDTO.getId() != null) {
            throw new BadRequestAlertException("A new tenderScheduleGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TenderScheduleGroupDTO result = tenderScheduleGroupService.save(tenderScheduleGroupDTO);
        return ResponseEntity
            .created(new URI("/api/tender-schedule-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tender-schedule-groups/:id} : Updates an existing tenderScheduleGroup.
     *
     * @param id the id of the tenderScheduleGroupDTO to save.
     * @param tenderScheduleGroupDTO the tenderScheduleGroupDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tenderScheduleGroupDTO,
     * or with status {@code 400 (Bad Request)} if the tenderScheduleGroupDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tenderScheduleGroupDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tender-schedule-groups/{id}")
    public ResponseEntity<TenderScheduleGroupDTO> updateTenderScheduleGroup(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody TenderScheduleGroupDTO tenderScheduleGroupDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TenderScheduleGroup : {}, {}", id, tenderScheduleGroupDTO);
        if (tenderScheduleGroupDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tenderScheduleGroupDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tenderScheduleGroupRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TenderScheduleGroupDTO result = tenderScheduleGroupService.save(tenderScheduleGroupDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tenderScheduleGroupDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tender-schedule-groups/:id} : Partial updates given fields of an existing tenderScheduleGroup, field will ignore if it is null
     *
     * @param id the id of the tenderScheduleGroupDTO to save.
     * @param tenderScheduleGroupDTO the tenderScheduleGroupDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tenderScheduleGroupDTO,
     * or with status {@code 400 (Bad Request)} if the tenderScheduleGroupDTO is not valid,
     * or with status {@code 404 (Not Found)} if the tenderScheduleGroupDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tenderScheduleGroupDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tender-schedule-groups/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<TenderScheduleGroupDTO> partialUpdateTenderScheduleGroup(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody TenderScheduleGroupDTO tenderScheduleGroupDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TenderScheduleGroup partially : {}, {}", id, tenderScheduleGroupDTO);
        if (tenderScheduleGroupDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tenderScheduleGroupDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tenderScheduleGroupRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TenderScheduleGroupDTO> result = tenderScheduleGroupService.partialUpdate(tenderScheduleGroupDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tenderScheduleGroupDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /tender-schedule-groups} : get all the tenderScheduleGroups.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tenderScheduleGroups in body.
     */
    @GetMapping("/tender-schedule-groups")
    public List<TenderScheduleGroupDTO> getAllTenderScheduleGroups() {
        log.debug("REST request to get all TenderScheduleGroups");
        return tenderScheduleGroupService.findAll();
    }

    /**
     * {@code GET  /tender-schedule-groups/:id} : get the "id" tenderScheduleGroup.
     *
     * @param id the id of the tenderScheduleGroupDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tenderScheduleGroupDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tender-schedule-groups/{id}")
    public ResponseEntity<TenderScheduleGroupDTO> getTenderScheduleGroup(@PathVariable Long id) {
        log.debug("REST request to get TenderScheduleGroup : {}", id);
        Optional<TenderScheduleGroupDTO> tenderScheduleGroupDTO = tenderScheduleGroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tenderScheduleGroupDTO);
    }

    /**
     * {@code DELETE  /tender-schedule-groups/:id} : delete the "id" tenderScheduleGroup.
     *
     * @param id the id of the tenderScheduleGroupDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tender-schedule-groups/{id}")
    public ResponseEntity<Void> deleteTenderScheduleGroup(@PathVariable Long id) {
        log.debug("REST request to delete TenderScheduleGroup : {}", id);
        tenderScheduleGroupService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
