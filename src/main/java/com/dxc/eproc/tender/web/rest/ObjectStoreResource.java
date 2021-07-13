package com.dxc.eproc.tender.web.rest;

import com.dxc.eproc.tender.repository.ObjectStoreRepository;
import com.dxc.eproc.tender.service.ObjectStoreService;
import com.dxc.eproc.tender.service.dto.ObjectStoreDTO;
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
 * REST controller for managing {@link com.dxc.eproc.tender.domain.ObjectStore}.
 */
@RestController
@RequestMapping("/api")
public class ObjectStoreResource {

    private final Logger log = LoggerFactory.getLogger(ObjectStoreResource.class);

    private static final String ENTITY_NAME = "tenderServiceObjectStore";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ObjectStoreService objectStoreService;

    private final ObjectStoreRepository objectStoreRepository;

    public ObjectStoreResource(ObjectStoreService objectStoreService, ObjectStoreRepository objectStoreRepository) {
        this.objectStoreService = objectStoreService;
        this.objectStoreRepository = objectStoreRepository;
    }

    /**
     * {@code POST  /object-stores} : Create a new objectStore.
     *
     * @param objectStoreDTO the objectStoreDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new objectStoreDTO, or with status {@code 400 (Bad Request)} if the objectStore has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/object-stores")
    public ResponseEntity<ObjectStoreDTO> createObjectStore(@Valid @RequestBody ObjectStoreDTO objectStoreDTO) throws URISyntaxException {
        log.debug("REST request to save ObjectStore : {}", objectStoreDTO);
        if (objectStoreDTO.getId() != null) {
            throw new BadRequestAlertException("A new objectStore cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ObjectStoreDTO result = objectStoreService.save(objectStoreDTO);
        return ResponseEntity
            .created(new URI("/api/object-stores/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /object-stores/:id} : Updates an existing objectStore.
     *
     * @param id the id of the objectStoreDTO to save.
     * @param objectStoreDTO the objectStoreDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated objectStoreDTO,
     * or with status {@code 400 (Bad Request)} if the objectStoreDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the objectStoreDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/object-stores/{id}")
    public ResponseEntity<ObjectStoreDTO> updateObjectStore(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ObjectStoreDTO objectStoreDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ObjectStore : {}, {}", id, objectStoreDTO);
        if (objectStoreDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, objectStoreDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!objectStoreRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ObjectStoreDTO result = objectStoreService.save(objectStoreDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, objectStoreDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /object-stores/:id} : Partial updates given fields of an existing objectStore, field will ignore if it is null
     *
     * @param id the id of the objectStoreDTO to save.
     * @param objectStoreDTO the objectStoreDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated objectStoreDTO,
     * or with status {@code 400 (Bad Request)} if the objectStoreDTO is not valid,
     * or with status {@code 404 (Not Found)} if the objectStoreDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the objectStoreDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/object-stores/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<ObjectStoreDTO> partialUpdateObjectStore(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ObjectStoreDTO objectStoreDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ObjectStore partially : {}, {}", id, objectStoreDTO);
        if (objectStoreDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, objectStoreDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!objectStoreRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ObjectStoreDTO> result = objectStoreService.partialUpdate(objectStoreDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, objectStoreDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /object-stores} : get all the objectStores.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of objectStores in body.
     */
    @GetMapping("/object-stores")
    public List<ObjectStoreDTO> getAllObjectStores() {
        log.debug("REST request to get all ObjectStores");
        return objectStoreService.findAll();
    }

    /**
     * {@code GET  /object-stores/:id} : get the "id" objectStore.
     *
     * @param id the id of the objectStoreDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the objectStoreDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/object-stores/{id}")
    public ResponseEntity<ObjectStoreDTO> getObjectStore(@PathVariable Long id) {
        log.debug("REST request to get ObjectStore : {}", id);
        Optional<ObjectStoreDTO> objectStoreDTO = objectStoreService.findOne(id);
        return ResponseUtil.wrapOrNotFound(objectStoreDTO);
    }

    /**
     * {@code DELETE  /object-stores/:id} : delete the "id" objectStore.
     *
     * @param id the id of the objectStoreDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/object-stores/{id}")
    public ResponseEntity<Void> deleteObjectStore(@PathVariable Long id) {
        log.debug("REST request to delete ObjectStore : {}", id);
        objectStoreService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
