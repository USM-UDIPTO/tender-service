package com.dxc.eproc.tender.web.rest;

import com.dxc.eproc.tender.repository.SeriesTableRepository;
import com.dxc.eproc.tender.service.SeriesTableService;
import com.dxc.eproc.tender.service.dto.SeriesTableDTO;
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
 * REST controller for managing {@link com.dxc.eproc.tender.domain.SeriesTable}.
 */
@RestController
@RequestMapping("/api")
public class SeriesTableResource {

    private final Logger log = LoggerFactory.getLogger(SeriesTableResource.class);

    private static final String ENTITY_NAME = "tenderServiceSeriesTable";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SeriesTableService seriesTableService;

    private final SeriesTableRepository seriesTableRepository;

    public SeriesTableResource(SeriesTableService seriesTableService, SeriesTableRepository seriesTableRepository) {
        this.seriesTableService = seriesTableService;
        this.seriesTableRepository = seriesTableRepository;
    }

    /**
     * {@code POST  /series-tables} : Create a new seriesTable.
     *
     * @param seriesTableDTO the seriesTableDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new seriesTableDTO, or with status {@code 400 (Bad Request)} if the seriesTable has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/series-tables")
    public ResponseEntity<SeriesTableDTO> createSeriesTable(@Valid @RequestBody SeriesTableDTO seriesTableDTO) throws URISyntaxException {
        log.debug("REST request to save SeriesTable : {}", seriesTableDTO);
        if (seriesTableDTO.getId() != null) {
            throw new BadRequestAlertException("A new seriesTable cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SeriesTableDTO result = seriesTableService.save(seriesTableDTO);
        return ResponseEntity
            .created(new URI("/api/series-tables/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /series-tables/:id} : Updates an existing seriesTable.
     *
     * @param id the id of the seriesTableDTO to save.
     * @param seriesTableDTO the seriesTableDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated seriesTableDTO,
     * or with status {@code 400 (Bad Request)} if the seriesTableDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the seriesTableDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/series-tables/{id}")
    public ResponseEntity<SeriesTableDTO> updateSeriesTable(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody SeriesTableDTO seriesTableDTO
    ) throws URISyntaxException {
        log.debug("REST request to update SeriesTable : {}, {}", id, seriesTableDTO);
        if (seriesTableDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, seriesTableDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!seriesTableRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        SeriesTableDTO result = seriesTableService.save(seriesTableDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, seriesTableDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /series-tables/:id} : Partial updates given fields of an existing seriesTable, field will ignore if it is null
     *
     * @param id the id of the seriesTableDTO to save.
     * @param seriesTableDTO the seriesTableDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated seriesTableDTO,
     * or with status {@code 400 (Bad Request)} if the seriesTableDTO is not valid,
     * or with status {@code 404 (Not Found)} if the seriesTableDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the seriesTableDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/series-tables/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<SeriesTableDTO> partialUpdateSeriesTable(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody SeriesTableDTO seriesTableDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update SeriesTable partially : {}, {}", id, seriesTableDTO);
        if (seriesTableDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, seriesTableDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!seriesTableRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SeriesTableDTO> result = seriesTableService.partialUpdate(seriesTableDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, seriesTableDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /series-tables} : get all the seriesTables.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of seriesTables in body.
     */
    @GetMapping("/series-tables")
    public List<SeriesTableDTO> getAllSeriesTables() {
        log.debug("REST request to get all SeriesTables");
        return seriesTableService.findAll();
    }

    /**
     * {@code GET  /series-tables/:id} : get the "id" seriesTable.
     *
     * @param id the id of the seriesTableDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the seriesTableDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/series-tables/{id}")
    public ResponseEntity<SeriesTableDTO> getSeriesTable(@PathVariable Long id) {
        log.debug("REST request to get SeriesTable : {}", id);
        Optional<SeriesTableDTO> seriesTableDTO = seriesTableService.findOne(id);
        return ResponseUtil.wrapOrNotFound(seriesTableDTO);
    }

    /**
     * {@code DELETE  /series-tables/:id} : delete the "id" seriesTable.
     *
     * @param id the id of the seriesTableDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/series-tables/{id}")
    public ResponseEntity<Void> deleteSeriesTable(@PathVariable Long id) {
        log.debug("REST request to delete SeriesTable : {}", id);
        seriesTableService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
