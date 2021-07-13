package com.dxc.eproc.tender.web.rest;

import com.dxc.eproc.tender.repository.TenderScheduleRepository;
import com.dxc.eproc.tender.service.TenderScheduleService;
import com.dxc.eproc.tender.service.dto.TenderScheduleDTO;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.dxc.eproc.tender.domain.TenderSchedule}.
 */
@RestController
@RequestMapping("/api")
public class TenderScheduleResource {

    private final Logger log = LoggerFactory.getLogger(TenderScheduleResource.class);

    private static final String ENTITY_NAME = "tenderServiceTenderSchedule";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TenderScheduleService tenderScheduleService;

    private final TenderScheduleRepository tenderScheduleRepository;

    public TenderScheduleResource(TenderScheduleService tenderScheduleService, TenderScheduleRepository tenderScheduleRepository) {
        this.tenderScheduleService = tenderScheduleService;
        this.tenderScheduleRepository = tenderScheduleRepository;
    }

    /**
     * {@code POST  /tender-schedules} : Create a new tenderSchedule.
     *
     * @param tenderScheduleDTO the tenderScheduleDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tenderScheduleDTO, or with status {@code 400 (Bad Request)} if the tenderSchedule has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tender-schedules")
    public ResponseEntity<TenderScheduleDTO> createTenderSchedule(@Valid @RequestBody TenderScheduleDTO tenderScheduleDTO)
        throws URISyntaxException {
        log.debug("REST request to save TenderSchedule : {}", tenderScheduleDTO);
        if (tenderScheduleDTO.getId() != null) {
            throw new BadRequestAlertException("A new tenderSchedule cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TenderScheduleDTO result = tenderScheduleService.save(tenderScheduleDTO);
        return ResponseEntity
            .created(new URI("/api/tender-schedules/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tender-schedules/:id} : Updates an existing tenderSchedule.
     *
     * @param id the id of the tenderScheduleDTO to save.
     * @param tenderScheduleDTO the tenderScheduleDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tenderScheduleDTO,
     * or with status {@code 400 (Bad Request)} if the tenderScheduleDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tenderScheduleDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tender-schedules/{id}")
    public ResponseEntity<TenderScheduleDTO> updateTenderSchedule(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody TenderScheduleDTO tenderScheduleDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TenderSchedule : {}, {}", id, tenderScheduleDTO);
        if (tenderScheduleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tenderScheduleDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tenderScheduleRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TenderScheduleDTO result = tenderScheduleService.save(tenderScheduleDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tenderScheduleDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tender-schedules/:id} : Partial updates given fields of an existing tenderSchedule, field will ignore if it is null
     *
     * @param id the id of the tenderScheduleDTO to save.
     * @param tenderScheduleDTO the tenderScheduleDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tenderScheduleDTO,
     * or with status {@code 400 (Bad Request)} if the tenderScheduleDTO is not valid,
     * or with status {@code 404 (Not Found)} if the tenderScheduleDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tenderScheduleDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tender-schedules/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<TenderScheduleDTO> partialUpdateTenderSchedule(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody TenderScheduleDTO tenderScheduleDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TenderSchedule partially : {}, {}", id, tenderScheduleDTO);
        if (tenderScheduleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tenderScheduleDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tenderScheduleRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TenderScheduleDTO> result = tenderScheduleService.partialUpdate(tenderScheduleDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tenderScheduleDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /tender-schedules} : get all the tenderSchedules.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tenderSchedules in body.
     */
    @GetMapping("/tender-schedules")
    public ResponseEntity<List<TenderScheduleDTO>> getAllTenderSchedules(Pageable pageable) {
        log.debug("REST request to get a page of TenderSchedules");
        Page<TenderScheduleDTO> page = tenderScheduleService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /tender-schedules/:id} : get the "id" tenderSchedule.
     *
     * @param id the id of the tenderScheduleDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tenderScheduleDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tender-schedules/{id}")
    public ResponseEntity<TenderScheduleDTO> getTenderSchedule(@PathVariable Long id) {
        log.debug("REST request to get TenderSchedule : {}", id);
        Optional<TenderScheduleDTO> tenderScheduleDTO = tenderScheduleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tenderScheduleDTO);
    }

    /**
     * {@code DELETE  /tender-schedules/:id} : delete the "id" tenderSchedule.
     *
     * @param id the id of the tenderScheduleDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tender-schedules/{id}")
    public ResponseEntity<Void> deleteTenderSchedule(@PathVariable Long id) {
        log.debug("REST request to delete TenderSchedule : {}", id);
        tenderScheduleService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
