package com.dxc.eproc.tender.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dxc.eproc.tender.IntegrationTest;
import com.dxc.eproc.tender.domain.CriterionDocument;
import com.dxc.eproc.tender.repository.CriterionDocumentRepository;
import com.dxc.eproc.tender.service.dto.CriterionDocumentDTO;
import com.dxc.eproc.tender.service.mapper.CriterionDocumentMapper;
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
 * Integration tests for the {@link CriterionDocumentResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CriterionDocumentResourceIT {

    private static final String DEFAULT_DOCUMENT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DOCUMENT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TENDER_CATEGORY = "AAAAAAAAAA";
    private static final String UPDATED_TENDER_CATEGORY = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ACTIVE_YN = false;
    private static final Boolean UPDATED_ACTIVE_YN = true;

    private static final String ENTITY_API_URL = "/api/criterion-documents";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CriterionDocumentRepository criterionDocumentRepository;

    @Autowired
    private CriterionDocumentMapper criterionDocumentMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCriterionDocumentMockMvc;

    private CriterionDocument criterionDocument;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CriterionDocument createEntity(EntityManager em) {
        CriterionDocument criterionDocument = new CriterionDocument()
            .documentName(DEFAULT_DOCUMENT_NAME)
            .tenderCategory(DEFAULT_TENDER_CATEGORY)
            .activeYn(DEFAULT_ACTIVE_YN);
        return criterionDocument;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CriterionDocument createUpdatedEntity(EntityManager em) {
        CriterionDocument criterionDocument = new CriterionDocument()
            .documentName(UPDATED_DOCUMENT_NAME)
            .tenderCategory(UPDATED_TENDER_CATEGORY)
            .activeYn(UPDATED_ACTIVE_YN);
        return criterionDocument;
    }

    @BeforeEach
    public void initTest() {
        criterionDocument = createEntity(em);
    }

    @Test
    @Transactional
    void createCriterionDocument() throws Exception {
        int databaseSizeBeforeCreate = criterionDocumentRepository.findAll().size();
        // Create the CriterionDocument
        CriterionDocumentDTO criterionDocumentDTO = criterionDocumentMapper.toDto(criterionDocument);
        restCriterionDocumentMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(criterionDocumentDTO))
            )
            .andExpect(status().isCreated());

        // Validate the CriterionDocument in the database
        List<CriterionDocument> criterionDocumentList = criterionDocumentRepository.findAll();
        assertThat(criterionDocumentList).hasSize(databaseSizeBeforeCreate + 1);
        CriterionDocument testCriterionDocument = criterionDocumentList.get(criterionDocumentList.size() - 1);
        assertThat(testCriterionDocument.getDocumentName()).isEqualTo(DEFAULT_DOCUMENT_NAME);
        assertThat(testCriterionDocument.getTenderCategory()).isEqualTo(DEFAULT_TENDER_CATEGORY);
        assertThat(testCriterionDocument.getActiveYn()).isEqualTo(DEFAULT_ACTIVE_YN);
    }

    @Test
    @Transactional
    void createCriterionDocumentWithExistingId() throws Exception {
        // Create the CriterionDocument with an existing ID
        criterionDocument.setId(1L);
        CriterionDocumentDTO criterionDocumentDTO = criterionDocumentMapper.toDto(criterionDocument);

        int databaseSizeBeforeCreate = criterionDocumentRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCriterionDocumentMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(criterionDocumentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CriterionDocument in the database
        List<CriterionDocument> criterionDocumentList = criterionDocumentRepository.findAll();
        assertThat(criterionDocumentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkDocumentNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = criterionDocumentRepository.findAll().size();
        // set the field null
        criterionDocument.setDocumentName(null);

        // Create the CriterionDocument, which fails.
        CriterionDocumentDTO criterionDocumentDTO = criterionDocumentMapper.toDto(criterionDocument);

        restCriterionDocumentMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(criterionDocumentDTO))
            )
            .andExpect(status().isBadRequest());

        List<CriterionDocument> criterionDocumentList = criterionDocumentRepository.findAll();
        assertThat(criterionDocumentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTenderCategoryIsRequired() throws Exception {
        int databaseSizeBeforeTest = criterionDocumentRepository.findAll().size();
        // set the field null
        criterionDocument.setTenderCategory(null);

        // Create the CriterionDocument, which fails.
        CriterionDocumentDTO criterionDocumentDTO = criterionDocumentMapper.toDto(criterionDocument);

        restCriterionDocumentMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(criterionDocumentDTO))
            )
            .andExpect(status().isBadRequest());

        List<CriterionDocument> criterionDocumentList = criterionDocumentRepository.findAll();
        assertThat(criterionDocumentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkActiveYnIsRequired() throws Exception {
        int databaseSizeBeforeTest = criterionDocumentRepository.findAll().size();
        // set the field null
        criterionDocument.setActiveYn(null);

        // Create the CriterionDocument, which fails.
        CriterionDocumentDTO criterionDocumentDTO = criterionDocumentMapper.toDto(criterionDocument);

        restCriterionDocumentMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(criterionDocumentDTO))
            )
            .andExpect(status().isBadRequest());

        List<CriterionDocument> criterionDocumentList = criterionDocumentRepository.findAll();
        assertThat(criterionDocumentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllCriterionDocuments() throws Exception {
        // Initialize the database
        criterionDocumentRepository.saveAndFlush(criterionDocument);

        // Get all the criterionDocumentList
        restCriterionDocumentMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(criterionDocument.getId().intValue())))
            .andExpect(jsonPath("$.[*].documentName").value(hasItem(DEFAULT_DOCUMENT_NAME)))
            .andExpect(jsonPath("$.[*].tenderCategory").value(hasItem(DEFAULT_TENDER_CATEGORY)))
            .andExpect(jsonPath("$.[*].activeYn").value(hasItem(DEFAULT_ACTIVE_YN.booleanValue())));
    }

    @Test
    @Transactional
    void getCriterionDocument() throws Exception {
        // Initialize the database
        criterionDocumentRepository.saveAndFlush(criterionDocument);

        // Get the criterionDocument
        restCriterionDocumentMockMvc
            .perform(get(ENTITY_API_URL_ID, criterionDocument.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(criterionDocument.getId().intValue()))
            .andExpect(jsonPath("$.documentName").value(DEFAULT_DOCUMENT_NAME))
            .andExpect(jsonPath("$.tenderCategory").value(DEFAULT_TENDER_CATEGORY))
            .andExpect(jsonPath("$.activeYn").value(DEFAULT_ACTIVE_YN.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingCriterionDocument() throws Exception {
        // Get the criterionDocument
        restCriterionDocumentMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewCriterionDocument() throws Exception {
        // Initialize the database
        criterionDocumentRepository.saveAndFlush(criterionDocument);

        int databaseSizeBeforeUpdate = criterionDocumentRepository.findAll().size();

        // Update the criterionDocument
        CriterionDocument updatedCriterionDocument = criterionDocumentRepository.findById(criterionDocument.getId()).get();
        // Disconnect from session so that the updates on updatedCriterionDocument are not directly saved in db
        em.detach(updatedCriterionDocument);
        updatedCriterionDocument.documentName(UPDATED_DOCUMENT_NAME).tenderCategory(UPDATED_TENDER_CATEGORY).activeYn(UPDATED_ACTIVE_YN);
        CriterionDocumentDTO criterionDocumentDTO = criterionDocumentMapper.toDto(updatedCriterionDocument);

        restCriterionDocumentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, criterionDocumentDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(criterionDocumentDTO))
            )
            .andExpect(status().isOk());

        // Validate the CriterionDocument in the database
        List<CriterionDocument> criterionDocumentList = criterionDocumentRepository.findAll();
        assertThat(criterionDocumentList).hasSize(databaseSizeBeforeUpdate);
        CriterionDocument testCriterionDocument = criterionDocumentList.get(criterionDocumentList.size() - 1);
        assertThat(testCriterionDocument.getDocumentName()).isEqualTo(UPDATED_DOCUMENT_NAME);
        assertThat(testCriterionDocument.getTenderCategory()).isEqualTo(UPDATED_TENDER_CATEGORY);
        assertThat(testCriterionDocument.getActiveYn()).isEqualTo(UPDATED_ACTIVE_YN);
    }

    @Test
    @Transactional
    void putNonExistingCriterionDocument() throws Exception {
        int databaseSizeBeforeUpdate = criterionDocumentRepository.findAll().size();
        criterionDocument.setId(count.incrementAndGet());

        // Create the CriterionDocument
        CriterionDocumentDTO criterionDocumentDTO = criterionDocumentMapper.toDto(criterionDocument);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCriterionDocumentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, criterionDocumentDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(criterionDocumentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CriterionDocument in the database
        List<CriterionDocument> criterionDocumentList = criterionDocumentRepository.findAll();
        assertThat(criterionDocumentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCriterionDocument() throws Exception {
        int databaseSizeBeforeUpdate = criterionDocumentRepository.findAll().size();
        criterionDocument.setId(count.incrementAndGet());

        // Create the CriterionDocument
        CriterionDocumentDTO criterionDocumentDTO = criterionDocumentMapper.toDto(criterionDocument);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCriterionDocumentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(criterionDocumentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CriterionDocument in the database
        List<CriterionDocument> criterionDocumentList = criterionDocumentRepository.findAll();
        assertThat(criterionDocumentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCriterionDocument() throws Exception {
        int databaseSizeBeforeUpdate = criterionDocumentRepository.findAll().size();
        criterionDocument.setId(count.incrementAndGet());

        // Create the CriterionDocument
        CriterionDocumentDTO criterionDocumentDTO = criterionDocumentMapper.toDto(criterionDocument);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCriterionDocumentMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(criterionDocumentDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CriterionDocument in the database
        List<CriterionDocument> criterionDocumentList = criterionDocumentRepository.findAll();
        assertThat(criterionDocumentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCriterionDocumentWithPatch() throws Exception {
        // Initialize the database
        criterionDocumentRepository.saveAndFlush(criterionDocument);

        int databaseSizeBeforeUpdate = criterionDocumentRepository.findAll().size();

        // Update the criterionDocument using partial update
        CriterionDocument partialUpdatedCriterionDocument = new CriterionDocument();
        partialUpdatedCriterionDocument.setId(criterionDocument.getId());

        partialUpdatedCriterionDocument.documentName(UPDATED_DOCUMENT_NAME).tenderCategory(UPDATED_TENDER_CATEGORY);

        restCriterionDocumentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCriterionDocument.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCriterionDocument))
            )
            .andExpect(status().isOk());

        // Validate the CriterionDocument in the database
        List<CriterionDocument> criterionDocumentList = criterionDocumentRepository.findAll();
        assertThat(criterionDocumentList).hasSize(databaseSizeBeforeUpdate);
        CriterionDocument testCriterionDocument = criterionDocumentList.get(criterionDocumentList.size() - 1);
        assertThat(testCriterionDocument.getDocumentName()).isEqualTo(UPDATED_DOCUMENT_NAME);
        assertThat(testCriterionDocument.getTenderCategory()).isEqualTo(UPDATED_TENDER_CATEGORY);
        assertThat(testCriterionDocument.getActiveYn()).isEqualTo(DEFAULT_ACTIVE_YN);
    }

    @Test
    @Transactional
    void fullUpdateCriterionDocumentWithPatch() throws Exception {
        // Initialize the database
        criterionDocumentRepository.saveAndFlush(criterionDocument);

        int databaseSizeBeforeUpdate = criterionDocumentRepository.findAll().size();

        // Update the criterionDocument using partial update
        CriterionDocument partialUpdatedCriterionDocument = new CriterionDocument();
        partialUpdatedCriterionDocument.setId(criterionDocument.getId());

        partialUpdatedCriterionDocument
            .documentName(UPDATED_DOCUMENT_NAME)
            .tenderCategory(UPDATED_TENDER_CATEGORY)
            .activeYn(UPDATED_ACTIVE_YN);

        restCriterionDocumentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCriterionDocument.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCriterionDocument))
            )
            .andExpect(status().isOk());

        // Validate the CriterionDocument in the database
        List<CriterionDocument> criterionDocumentList = criterionDocumentRepository.findAll();
        assertThat(criterionDocumentList).hasSize(databaseSizeBeforeUpdate);
        CriterionDocument testCriterionDocument = criterionDocumentList.get(criterionDocumentList.size() - 1);
        assertThat(testCriterionDocument.getDocumentName()).isEqualTo(UPDATED_DOCUMENT_NAME);
        assertThat(testCriterionDocument.getTenderCategory()).isEqualTo(UPDATED_TENDER_CATEGORY);
        assertThat(testCriterionDocument.getActiveYn()).isEqualTo(UPDATED_ACTIVE_YN);
    }

    @Test
    @Transactional
    void patchNonExistingCriterionDocument() throws Exception {
        int databaseSizeBeforeUpdate = criterionDocumentRepository.findAll().size();
        criterionDocument.setId(count.incrementAndGet());

        // Create the CriterionDocument
        CriterionDocumentDTO criterionDocumentDTO = criterionDocumentMapper.toDto(criterionDocument);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCriterionDocumentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, criterionDocumentDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(criterionDocumentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CriterionDocument in the database
        List<CriterionDocument> criterionDocumentList = criterionDocumentRepository.findAll();
        assertThat(criterionDocumentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCriterionDocument() throws Exception {
        int databaseSizeBeforeUpdate = criterionDocumentRepository.findAll().size();
        criterionDocument.setId(count.incrementAndGet());

        // Create the CriterionDocument
        CriterionDocumentDTO criterionDocumentDTO = criterionDocumentMapper.toDto(criterionDocument);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCriterionDocumentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(criterionDocumentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CriterionDocument in the database
        List<CriterionDocument> criterionDocumentList = criterionDocumentRepository.findAll();
        assertThat(criterionDocumentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCriterionDocument() throws Exception {
        int databaseSizeBeforeUpdate = criterionDocumentRepository.findAll().size();
        criterionDocument.setId(count.incrementAndGet());

        // Create the CriterionDocument
        CriterionDocumentDTO criterionDocumentDTO = criterionDocumentMapper.toDto(criterionDocument);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCriterionDocumentMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(criterionDocumentDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CriterionDocument in the database
        List<CriterionDocument> criterionDocumentList = criterionDocumentRepository.findAll();
        assertThat(criterionDocumentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCriterionDocument() throws Exception {
        // Initialize the database
        criterionDocumentRepository.saveAndFlush(criterionDocument);

        int databaseSizeBeforeDelete = criterionDocumentRepository.findAll().size();

        // Delete the criterionDocument
        restCriterionDocumentMockMvc
            .perform(delete(ENTITY_API_URL_ID, criterionDocument.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CriterionDocument> criterionDocumentList = criterionDocumentRepository.findAll();
        assertThat(criterionDocumentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
