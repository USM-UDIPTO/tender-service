package com.dxc.eproc.tender.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dxc.eproc.tender.IntegrationTest;
import com.dxc.eproc.tender.domain.TenderCriterionDocument;
import com.dxc.eproc.tender.repository.TenderCriterionDocumentRepository;
import com.dxc.eproc.tender.service.dto.TenderCriterionDocumentDTO;
import com.dxc.eproc.tender.service.mapper.TenderCriterionDocumentMapper;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link TenderCriterionDocumentResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TenderCriterionDocumentResourceIT {

    private static final Long DEFAULT_NIT_ID = 1L;
    private static final Long UPDATED_NIT_ID = 2L;

    private static final Long DEFAULT_TENDER_CRITERION_ID = 1L;
    private static final Long UPDATED_TENDER_CRITERION_ID = 2L;

    private static final Long DEFAULT_CRITERION_ID = 1L;
    private static final Long UPDATED_CRITERION_ID = 2L;

    private static final String DEFAULT_DOCUMENT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DOCUMENT_NAME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_OPTIONAL = false;
    private static final Boolean UPDATED_OPTIONAL = true;

    private static final String ENTITY_API_URL = "/api/tender-criterion-documents";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TenderCriterionDocumentRepository tenderCriterionDocumentRepository;

    @Autowired
    private TenderCriterionDocumentMapper tenderCriterionDocumentMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTenderCriterionDocumentMockMvc;

    private TenderCriterionDocument tenderCriterionDocument;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TenderCriterionDocument createEntity(EntityManager em) {
        TenderCriterionDocument tenderCriterionDocument = new TenderCriterionDocument()
            .nitId(DEFAULT_NIT_ID)
            .tenderCriterionId(DEFAULT_TENDER_CRITERION_ID)
            .criterionId(DEFAULT_CRITERION_ID)
            .documentName(DEFAULT_DOCUMENT_NAME)
            .optional(DEFAULT_OPTIONAL);
        return tenderCriterionDocument;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TenderCriterionDocument createUpdatedEntity(EntityManager em) {
        TenderCriterionDocument tenderCriterionDocument = new TenderCriterionDocument()
            .nitId(UPDATED_NIT_ID)
            .tenderCriterionId(UPDATED_TENDER_CRITERION_ID)
            .criterionId(UPDATED_CRITERION_ID)
            .documentName(UPDATED_DOCUMENT_NAME)
            .optional(UPDATED_OPTIONAL);
        return tenderCriterionDocument;
    }

    @BeforeEach
    public void initTest() {
        tenderCriterionDocument = createEntity(em);
    }

    @Test
    @Transactional
    void createTenderCriterionDocument() throws Exception {
        int databaseSizeBeforeCreate = tenderCriterionDocumentRepository.findAll().size();
        // Create the TenderCriterionDocument
        TenderCriterionDocumentDTO tenderCriterionDocumentDTO = tenderCriterionDocumentMapper.toDto(tenderCriterionDocument);
        restTenderCriterionDocumentMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCriterionDocumentDTO))
            )
            .andExpect(status().isCreated());

        // Validate the TenderCriterionDocument in the database
        List<TenderCriterionDocument> tenderCriterionDocumentList = tenderCriterionDocumentRepository.findAll();
        assertThat(tenderCriterionDocumentList).hasSize(databaseSizeBeforeCreate + 1);
        TenderCriterionDocument testTenderCriterionDocument = tenderCriterionDocumentList.get(tenderCriterionDocumentList.size() - 1);
        assertThat(testTenderCriterionDocument.getNitId()).isEqualTo(DEFAULT_NIT_ID);
        assertThat(testTenderCriterionDocument.getTenderCriterionId()).isEqualTo(DEFAULT_TENDER_CRITERION_ID);
        assertThat(testTenderCriterionDocument.getCriterionId()).isEqualTo(DEFAULT_CRITERION_ID);
        assertThat(testTenderCriterionDocument.getDocumentName()).isEqualTo(DEFAULT_DOCUMENT_NAME);
        assertThat(testTenderCriterionDocument.getOptional()).isEqualTo(DEFAULT_OPTIONAL);
    }

    @Test
    @Transactional
    void createTenderCriterionDocumentWithExistingId() throws Exception {
        // Create the TenderCriterionDocument with an existing ID
        tenderCriterionDocument.setId(1L);
        TenderCriterionDocumentDTO tenderCriterionDocumentDTO = tenderCriterionDocumentMapper.toDto(tenderCriterionDocument);

        int databaseSizeBeforeCreate = tenderCriterionDocumentRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTenderCriterionDocumentMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCriterionDocumentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderCriterionDocument in the database
        List<TenderCriterionDocument> tenderCriterionDocumentList = tenderCriterionDocumentRepository.findAll();
        assertThat(tenderCriterionDocumentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNitIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderCriterionDocumentRepository.findAll().size();
        // set the field null
        tenderCriterionDocument.setNitId(null);

        // Create the TenderCriterionDocument, which fails.
        TenderCriterionDocumentDTO tenderCriterionDocumentDTO = tenderCriterionDocumentMapper.toDto(tenderCriterionDocument);

        restTenderCriterionDocumentMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCriterionDocumentDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderCriterionDocument> tenderCriterionDocumentList = tenderCriterionDocumentRepository.findAll();
        assertThat(tenderCriterionDocumentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTenderCriterionIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderCriterionDocumentRepository.findAll().size();
        // set the field null
        tenderCriterionDocument.setTenderCriterionId(null);

        // Create the TenderCriterionDocument, which fails.
        TenderCriterionDocumentDTO tenderCriterionDocumentDTO = tenderCriterionDocumentMapper.toDto(tenderCriterionDocument);

        restTenderCriterionDocumentMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCriterionDocumentDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderCriterionDocument> tenderCriterionDocumentList = tenderCriterionDocumentRepository.findAll();
        assertThat(tenderCriterionDocumentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCriterionIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderCriterionDocumentRepository.findAll().size();
        // set the field null
        tenderCriterionDocument.setCriterionId(null);

        // Create the TenderCriterionDocument, which fails.
        TenderCriterionDocumentDTO tenderCriterionDocumentDTO = tenderCriterionDocumentMapper.toDto(tenderCriterionDocument);

        restTenderCriterionDocumentMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCriterionDocumentDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderCriterionDocument> tenderCriterionDocumentList = tenderCriterionDocumentRepository.findAll();
        assertThat(tenderCriterionDocumentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDocumentNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderCriterionDocumentRepository.findAll().size();
        // set the field null
        tenderCriterionDocument.setDocumentName(null);

        // Create the TenderCriterionDocument, which fails.
        TenderCriterionDocumentDTO tenderCriterionDocumentDTO = tenderCriterionDocumentMapper.toDto(tenderCriterionDocument);

        restTenderCriterionDocumentMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCriterionDocumentDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderCriterionDocument> tenderCriterionDocumentList = tenderCriterionDocumentRepository.findAll();
        assertThat(tenderCriterionDocumentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkOptionalIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderCriterionDocumentRepository.findAll().size();
        // set the field null
        tenderCriterionDocument.setOptional(null);

        // Create the TenderCriterionDocument, which fails.
        TenderCriterionDocumentDTO tenderCriterionDocumentDTO = tenderCriterionDocumentMapper.toDto(tenderCriterionDocument);

        restTenderCriterionDocumentMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCriterionDocumentDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderCriterionDocument> tenderCriterionDocumentList = tenderCriterionDocumentRepository.findAll();
        assertThat(tenderCriterionDocumentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTenderCriterionDocuments() throws Exception {
        // Initialize the database
        tenderCriterionDocumentRepository.saveAndFlush(tenderCriterionDocument);

        // Get all the tenderCriterionDocumentList
        restTenderCriterionDocumentMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tenderCriterionDocument.getId().intValue())))
            .andExpect(jsonPath("$.[*].nitId").value(hasItem(DEFAULT_NIT_ID.intValue())))
            .andExpect(jsonPath("$.[*].tenderCriterionId").value(hasItem(DEFAULT_TENDER_CRITERION_ID.intValue())))
            .andExpect(jsonPath("$.[*].criterionId").value(hasItem(DEFAULT_CRITERION_ID.intValue())))
            .andExpect(jsonPath("$.[*].documentName").value(hasItem(DEFAULT_DOCUMENT_NAME)))
            .andExpect(jsonPath("$.[*].optional").value(hasItem(DEFAULT_OPTIONAL.booleanValue())));
    }

    @Test
    @Transactional
    void getTenderCriterionDocument() throws Exception {
        // Initialize the database
        tenderCriterionDocumentRepository.saveAndFlush(tenderCriterionDocument);

        // Get the tenderCriterionDocument
        restTenderCriterionDocumentMockMvc
            .perform(get(ENTITY_API_URL_ID, tenderCriterionDocument.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tenderCriterionDocument.getId().intValue()))
            .andExpect(jsonPath("$.nitId").value(DEFAULT_NIT_ID.intValue()))
            .andExpect(jsonPath("$.tenderCriterionId").value(DEFAULT_TENDER_CRITERION_ID.intValue()))
            .andExpect(jsonPath("$.criterionId").value(DEFAULT_CRITERION_ID.intValue()))
            .andExpect(jsonPath("$.documentName").value(DEFAULT_DOCUMENT_NAME))
            .andExpect(jsonPath("$.optional").value(DEFAULT_OPTIONAL.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingTenderCriterionDocument() throws Exception {
        // Get the tenderCriterionDocument
        restTenderCriterionDocumentMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTenderCriterionDocument() throws Exception {
        // Initialize the database
        tenderCriterionDocumentRepository.saveAndFlush(tenderCriterionDocument);

        int databaseSizeBeforeUpdate = tenderCriterionDocumentRepository.findAll().size();

        // Update the tenderCriterionDocument
        TenderCriterionDocument updatedTenderCriterionDocument = tenderCriterionDocumentRepository
            .findById(tenderCriterionDocument.getId())
            .get();
        // Disconnect from session so that the updates on updatedTenderCriterionDocument are not directly saved in db
        em.detach(updatedTenderCriterionDocument);
        updatedTenderCriterionDocument
            .nitId(UPDATED_NIT_ID)
            .tenderCriterionId(UPDATED_TENDER_CRITERION_ID)
            .criterionId(UPDATED_CRITERION_ID)
            .documentName(UPDATED_DOCUMENT_NAME)
            .optional(UPDATED_OPTIONAL);
        TenderCriterionDocumentDTO tenderCriterionDocumentDTO = tenderCriterionDocumentMapper.toDto(updatedTenderCriterionDocument);

        restTenderCriterionDocumentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tenderCriterionDocumentDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCriterionDocumentDTO))
            )
            .andExpect(status().isOk());

        // Validate the TenderCriterionDocument in the database
        List<TenderCriterionDocument> tenderCriterionDocumentList = tenderCriterionDocumentRepository.findAll();
        assertThat(tenderCriterionDocumentList).hasSize(databaseSizeBeforeUpdate);
        TenderCriterionDocument testTenderCriterionDocument = tenderCriterionDocumentList.get(tenderCriterionDocumentList.size() - 1);
        assertThat(testTenderCriterionDocument.getNitId()).isEqualTo(UPDATED_NIT_ID);
        assertThat(testTenderCriterionDocument.getTenderCriterionId()).isEqualTo(UPDATED_TENDER_CRITERION_ID);
        assertThat(testTenderCriterionDocument.getCriterionId()).isEqualTo(UPDATED_CRITERION_ID);
        assertThat(testTenderCriterionDocument.getDocumentName()).isEqualTo(UPDATED_DOCUMENT_NAME);
        assertThat(testTenderCriterionDocument.getOptional()).isEqualTo(UPDATED_OPTIONAL);
    }

    @Test
    @Transactional
    void putNonExistingTenderCriterionDocument() throws Exception {
        int databaseSizeBeforeUpdate = tenderCriterionDocumentRepository.findAll().size();
        tenderCriterionDocument.setId(count.incrementAndGet());

        // Create the TenderCriterionDocument
        TenderCriterionDocumentDTO tenderCriterionDocumentDTO = tenderCriterionDocumentMapper.toDto(tenderCriterionDocument);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTenderCriterionDocumentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tenderCriterionDocumentDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCriterionDocumentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderCriterionDocument in the database
        List<TenderCriterionDocument> tenderCriterionDocumentList = tenderCriterionDocumentRepository.findAll();
        assertThat(tenderCriterionDocumentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTenderCriterionDocument() throws Exception {
        int databaseSizeBeforeUpdate = tenderCriterionDocumentRepository.findAll().size();
        tenderCriterionDocument.setId(count.incrementAndGet());

        // Create the TenderCriterionDocument
        TenderCriterionDocumentDTO tenderCriterionDocumentDTO = tenderCriterionDocumentMapper.toDto(tenderCriterionDocument);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderCriterionDocumentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCriterionDocumentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderCriterionDocument in the database
        List<TenderCriterionDocument> tenderCriterionDocumentList = tenderCriterionDocumentRepository.findAll();
        assertThat(tenderCriterionDocumentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTenderCriterionDocument() throws Exception {
        int databaseSizeBeforeUpdate = tenderCriterionDocumentRepository.findAll().size();
        tenderCriterionDocument.setId(count.incrementAndGet());

        // Create the TenderCriterionDocument
        TenderCriterionDocumentDTO tenderCriterionDocumentDTO = tenderCriterionDocumentMapper.toDto(tenderCriterionDocument);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderCriterionDocumentMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCriterionDocumentDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TenderCriterionDocument in the database
        List<TenderCriterionDocument> tenderCriterionDocumentList = tenderCriterionDocumentRepository.findAll();
        assertThat(tenderCriterionDocumentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTenderCriterionDocumentWithPatch() throws Exception {
        // Initialize the database
        tenderCriterionDocumentRepository.saveAndFlush(tenderCriterionDocument);

        int databaseSizeBeforeUpdate = tenderCriterionDocumentRepository.findAll().size();

        // Update the tenderCriterionDocument using partial update
        TenderCriterionDocument partialUpdatedTenderCriterionDocument = new TenderCriterionDocument();
        partialUpdatedTenderCriterionDocument.setId(tenderCriterionDocument.getId());

        partialUpdatedTenderCriterionDocument.criterionId(UPDATED_CRITERION_ID).optional(UPDATED_OPTIONAL);

        restTenderCriterionDocumentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTenderCriterionDocument.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTenderCriterionDocument))
            )
            .andExpect(status().isOk());

        // Validate the TenderCriterionDocument in the database
        List<TenderCriterionDocument> tenderCriterionDocumentList = tenderCriterionDocumentRepository.findAll();
        assertThat(tenderCriterionDocumentList).hasSize(databaseSizeBeforeUpdate);
        TenderCriterionDocument testTenderCriterionDocument = tenderCriterionDocumentList.get(tenderCriterionDocumentList.size() - 1);
        assertThat(testTenderCriterionDocument.getNitId()).isEqualTo(DEFAULT_NIT_ID);
        assertThat(testTenderCriterionDocument.getTenderCriterionId()).isEqualTo(DEFAULT_TENDER_CRITERION_ID);
        assertThat(testTenderCriterionDocument.getCriterionId()).isEqualTo(UPDATED_CRITERION_ID);
        assertThat(testTenderCriterionDocument.getDocumentName()).isEqualTo(DEFAULT_DOCUMENT_NAME);
        assertThat(testTenderCriterionDocument.getOptional()).isEqualTo(UPDATED_OPTIONAL);
    }

    @Test
    @Transactional
    void fullUpdateTenderCriterionDocumentWithPatch() throws Exception {
        // Initialize the database
        tenderCriterionDocumentRepository.saveAndFlush(tenderCriterionDocument);

        int databaseSizeBeforeUpdate = tenderCriterionDocumentRepository.findAll().size();

        // Update the tenderCriterionDocument using partial update
        TenderCriterionDocument partialUpdatedTenderCriterionDocument = new TenderCriterionDocument();
        partialUpdatedTenderCriterionDocument.setId(tenderCriterionDocument.getId());

        partialUpdatedTenderCriterionDocument
            .nitId(UPDATED_NIT_ID)
            .tenderCriterionId(UPDATED_TENDER_CRITERION_ID)
            .criterionId(UPDATED_CRITERION_ID)
            .documentName(UPDATED_DOCUMENT_NAME)
            .optional(UPDATED_OPTIONAL);

        restTenderCriterionDocumentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTenderCriterionDocument.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTenderCriterionDocument))
            )
            .andExpect(status().isOk());

        // Validate the TenderCriterionDocument in the database
        List<TenderCriterionDocument> tenderCriterionDocumentList = tenderCriterionDocumentRepository.findAll();
        assertThat(tenderCriterionDocumentList).hasSize(databaseSizeBeforeUpdate);
        TenderCriterionDocument testTenderCriterionDocument = tenderCriterionDocumentList.get(tenderCriterionDocumentList.size() - 1);
        assertThat(testTenderCriterionDocument.getNitId()).isEqualTo(UPDATED_NIT_ID);
        assertThat(testTenderCriterionDocument.getTenderCriterionId()).isEqualTo(UPDATED_TENDER_CRITERION_ID);
        assertThat(testTenderCriterionDocument.getCriterionId()).isEqualTo(UPDATED_CRITERION_ID);
        assertThat(testTenderCriterionDocument.getDocumentName()).isEqualTo(UPDATED_DOCUMENT_NAME);
        assertThat(testTenderCriterionDocument.getOptional()).isEqualTo(UPDATED_OPTIONAL);
    }

    @Test
    @Transactional
    void patchNonExistingTenderCriterionDocument() throws Exception {
        int databaseSizeBeforeUpdate = tenderCriterionDocumentRepository.findAll().size();
        tenderCriterionDocument.setId(count.incrementAndGet());

        // Create the TenderCriterionDocument
        TenderCriterionDocumentDTO tenderCriterionDocumentDTO = tenderCriterionDocumentMapper.toDto(tenderCriterionDocument);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTenderCriterionDocumentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tenderCriterionDocumentDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderCriterionDocumentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderCriterionDocument in the database
        List<TenderCriterionDocument> tenderCriterionDocumentList = tenderCriterionDocumentRepository.findAll();
        assertThat(tenderCriterionDocumentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTenderCriterionDocument() throws Exception {
        int databaseSizeBeforeUpdate = tenderCriterionDocumentRepository.findAll().size();
        tenderCriterionDocument.setId(count.incrementAndGet());

        // Create the TenderCriterionDocument
        TenderCriterionDocumentDTO tenderCriterionDocumentDTO = tenderCriterionDocumentMapper.toDto(tenderCriterionDocument);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderCriterionDocumentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderCriterionDocumentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderCriterionDocument in the database
        List<TenderCriterionDocument> tenderCriterionDocumentList = tenderCriterionDocumentRepository.findAll();
        assertThat(tenderCriterionDocumentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTenderCriterionDocument() throws Exception {
        int databaseSizeBeforeUpdate = tenderCriterionDocumentRepository.findAll().size();
        tenderCriterionDocument.setId(count.incrementAndGet());

        // Create the TenderCriterionDocument
        TenderCriterionDocumentDTO tenderCriterionDocumentDTO = tenderCriterionDocumentMapper.toDto(tenderCriterionDocument);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderCriterionDocumentMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderCriterionDocumentDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TenderCriterionDocument in the database
        List<TenderCriterionDocument> tenderCriterionDocumentList = tenderCriterionDocumentRepository.findAll();
        assertThat(tenderCriterionDocumentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTenderCriterionDocument() throws Exception {
        // Initialize the database
        tenderCriterionDocumentRepository.saveAndFlush(tenderCriterionDocument);

        int databaseSizeBeforeDelete = tenderCriterionDocumentRepository.findAll().size();

        // Delete the tenderCriterionDocument
        restTenderCriterionDocumentMockMvc
            .perform(delete(ENTITY_API_URL_ID, tenderCriterionDocument.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TenderCriterionDocument> tenderCriterionDocumentList = tenderCriterionDocumentRepository.findAll();
        assertThat(tenderCriterionDocumentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
