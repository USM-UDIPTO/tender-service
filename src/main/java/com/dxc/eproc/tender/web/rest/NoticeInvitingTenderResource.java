package com.dxc.eproc.tender.web.rest;

import com.dxc.eproc.tender.repository.NoticeInvitingTenderRepository;
import com.dxc.eproc.tender.service.NoticeInvitingTenderService;
import com.dxc.eproc.tender.service.dto.NoticeInvitingTenderDTO;
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
 * REST controller for managing {@link com.dxc.eproc.tender.domain.NoticeInvitingTender}.
 */
@RestController
@RequestMapping("/api")
public class NoticeInvitingTenderResource {

    private final Logger log = LoggerFactory.getLogger(NoticeInvitingTenderResource.class);

    private static final String ENTITY_NAME = "tenderServiceNoticeInvitingTender";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NoticeInvitingTenderService noticeInvitingTenderService;

    private final NoticeInvitingTenderRepository noticeInvitingTenderRepository;

    public NoticeInvitingTenderResource(
        NoticeInvitingTenderService noticeInvitingTenderService,
        NoticeInvitingTenderRepository noticeInvitingTenderRepository
    ) {
        this.noticeInvitingTenderService = noticeInvitingTenderService;
        this.noticeInvitingTenderRepository = noticeInvitingTenderRepository;
    }

    /**
     * {@code POST  /notice-inviting-tenders} : Create a new noticeInvitingTender.
     *
     * @param noticeInvitingTenderDTO the noticeInvitingTenderDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new noticeInvitingTenderDTO, or with status {@code 400 (Bad Request)} if the noticeInvitingTender has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/notice-inviting-tenders")
    public ResponseEntity<NoticeInvitingTenderDTO> createNoticeInvitingTender(
        @Valid @RequestBody NoticeInvitingTenderDTO noticeInvitingTenderDTO
    ) throws URISyntaxException {
        log.debug("REST request to save NoticeInvitingTender : {}", noticeInvitingTenderDTO);
        if (noticeInvitingTenderDTO.getId() != null) {
            throw new BadRequestAlertException("A new noticeInvitingTender cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NoticeInvitingTenderDTO result = noticeInvitingTenderService.save(noticeInvitingTenderDTO);
        return ResponseEntity
            .created(new URI("/api/notice-inviting-tenders/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /notice-inviting-tenders/:id} : Updates an existing noticeInvitingTender.
     *
     * @param id the id of the noticeInvitingTenderDTO to save.
     * @param noticeInvitingTenderDTO the noticeInvitingTenderDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated noticeInvitingTenderDTO,
     * or with status {@code 400 (Bad Request)} if the noticeInvitingTenderDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the noticeInvitingTenderDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/notice-inviting-tenders/{id}")
    public ResponseEntity<NoticeInvitingTenderDTO> updateNoticeInvitingTender(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody NoticeInvitingTenderDTO noticeInvitingTenderDTO
    ) throws URISyntaxException {
        log.debug("REST request to update NoticeInvitingTender : {}, {}", id, noticeInvitingTenderDTO);
        if (noticeInvitingTenderDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, noticeInvitingTenderDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!noticeInvitingTenderRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        NoticeInvitingTenderDTO result = noticeInvitingTenderService.save(noticeInvitingTenderDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, noticeInvitingTenderDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /notice-inviting-tenders/:id} : Partial updates given fields of an existing noticeInvitingTender, field will ignore if it is null
     *
     * @param id the id of the noticeInvitingTenderDTO to save.
     * @param noticeInvitingTenderDTO the noticeInvitingTenderDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated noticeInvitingTenderDTO,
     * or with status {@code 400 (Bad Request)} if the noticeInvitingTenderDTO is not valid,
     * or with status {@code 404 (Not Found)} if the noticeInvitingTenderDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the noticeInvitingTenderDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/notice-inviting-tenders/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<NoticeInvitingTenderDTO> partialUpdateNoticeInvitingTender(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody NoticeInvitingTenderDTO noticeInvitingTenderDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update NoticeInvitingTender partially : {}, {}", id, noticeInvitingTenderDTO);
        if (noticeInvitingTenderDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, noticeInvitingTenderDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!noticeInvitingTenderRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<NoticeInvitingTenderDTO> result = noticeInvitingTenderService.partialUpdate(noticeInvitingTenderDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, noticeInvitingTenderDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /notice-inviting-tenders} : get all the noticeInvitingTenders.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of noticeInvitingTenders in body.
     */
    @GetMapping("/notice-inviting-tenders")
    public ResponseEntity<List<NoticeInvitingTenderDTO>> getAllNoticeInvitingTenders(Pageable pageable) {
        log.debug("REST request to get a page of NoticeInvitingTenders");
        Page<NoticeInvitingTenderDTO> page = noticeInvitingTenderService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /notice-inviting-tenders/:id} : get the "id" noticeInvitingTender.
     *
     * @param id the id of the noticeInvitingTenderDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the noticeInvitingTenderDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/notice-inviting-tenders/{id}")
    public ResponseEntity<NoticeInvitingTenderDTO> getNoticeInvitingTender(@PathVariable Long id) {
        log.debug("REST request to get NoticeInvitingTender : {}", id);
        Optional<NoticeInvitingTenderDTO> noticeInvitingTenderDTO = noticeInvitingTenderService.findOne(id);
        return ResponseUtil.wrapOrNotFound(noticeInvitingTenderDTO);
    }

    /**
     * {@code DELETE  /notice-inviting-tenders/:id} : delete the "id" noticeInvitingTender.
     *
     * @param id the id of the noticeInvitingTenderDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/notice-inviting-tenders/{id}")
    public ResponseEntity<Void> deleteNoticeInvitingTender(@PathVariable Long id) {
        log.debug("REST request to delete NoticeInvitingTender : {}", id);
        noticeInvitingTenderService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
