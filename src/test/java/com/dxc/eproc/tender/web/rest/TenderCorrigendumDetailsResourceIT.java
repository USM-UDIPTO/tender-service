package com.dxc.eproc.tender.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dxc.eproc.tender.IntegrationTest;
import com.dxc.eproc.tender.domain.TenderCorrigendumDetails;
import com.dxc.eproc.tender.repository.TenderCorrigendumDetailsRepository;
import com.dxc.eproc.tender.service.dto.TenderCorrigendumDetailsDTO;
import com.dxc.eproc.tender.service.mapper.TenderCorrigendumDetailsMapper;
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
 * Integration tests for the {@link TenderCorrigendumDetailsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TenderCorrigendumDetailsResourceIT {

    private static final Long DEFAULT_TENDER_CORRIGENDUM_ID = 1L;
    private static final Long UPDATED_TENDER_CORRIGENDUM_ID = 2L;

    private static final String DEFAULT_REFERENCE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_READ_AS = "AAAAAAAAAA";
    private static final String UPDATED_READ_AS = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/tender-corrigendum-details";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TenderCorrigendumDetailsRepository tenderCorrigendumDetailsRepository;

    @Autowired
    private TenderCorrigendumDetailsMapper tenderCorrigendumDetailsMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTenderCorrigendumDetailsMockMvc;

    private TenderCorrigendumDetails tenderCorrigendumDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TenderCorrigendumDetails createEntity(EntityManager em) {
        TenderCorrigendumDetails tenderCorrigendumDetails = new TenderCorrigendumDetails()
            .tenderCorrigendumId(DEFAULT_TENDER_CORRIGENDUM_ID)
            .referenceNumber(DEFAULT_REFERENCE_NUMBER)
            .readAs(DEFAULT_READ_AS);
        return tenderCorrigendumDetails;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TenderCorrigendumDetails createUpdatedEntity(EntityManager em) {
        TenderCorrigendumDetails tenderCorrigendumDetails = new TenderCorrigendumDetails()
            .tenderCorrigendumId(UPDATED_TENDER_CORRIGENDUM_ID)
            .referenceNumber(UPDATED_REFERENCE_NUMBER)
            .readAs(UPDATED_READ_AS);
        return tenderCorrigendumDetails;
    }

    @BeforeEach
    public void initTest() {
        tenderCorrigendumDetails = createEntity(em);
    }

    @Test
    @Transactional
    void createTenderCorrigendumDetails() throws Exception {
        int databaseSizeBeforeCreate = tenderCorrigendumDetailsRepository.findAll().size();
        // Create the TenderCorrigendumDetails
        TenderCorrigendumDetailsDTO tenderCorrigendumDetailsDTO = tenderCorrigendumDetailsMapper.toDto(tenderCorrigendumDetails);
        restTenderCorrigendumDetailsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCorrigendumDetailsDTO))
            )
            .andExpect(status().isCreated());

        // Validate the TenderCorrigendumDetails in the database
        List<TenderCorrigendumDetails> tenderCorrigendumDetailsList = tenderCorrigendumDetailsRepository.findAll();
        assertThat(tenderCorrigendumDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        TenderCorrigendumDetails testTenderCorrigendumDetails = tenderCorrigendumDetailsList.get(tenderCorrigendumDetailsList.size() - 1);
        assertThat(testTenderCorrigendumDetails.getTenderCorrigendumId()).isEqualTo(DEFAULT_TENDER_CORRIGENDUM_ID);
        assertThat(testTenderCorrigendumDetails.getReferenceNumber()).isEqualTo(DEFAULT_REFERENCE_NUMBER);
        assertThat(testTenderCorrigendumDetails.getReadAs()).isEqualTo(DEFAULT_READ_AS);
    }

    @Test
    @Transactional
    void createTenderCorrigendumDetailsWithExistingId() throws Exception {
        // Create the TenderCorrigendumDetails with an existing ID
        tenderCorrigendumDetails.setId(1L);
        TenderCorrigendumDetailsDTO tenderCorrigendumDetailsDTO = tenderCorrigendumDetailsMapper.toDto(tenderCorrigendumDetails);

        int databaseSizeBeforeCreate = tenderCorrigendumDetailsRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTenderCorrigendumDetailsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCorrigendumDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderCorrigendumDetails in the database
        List<TenderCorrigendumDetails> tenderCorrigendumDetailsList = tenderCorrigendumDetailsRepository.findAll();
        assertThat(tenderCorrigendumDetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkTenderCorrigendumIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderCorrigendumDetailsRepository.findAll().size();
        // set the field null
        tenderCorrigendumDetails.setTenderCorrigendumId(null);

        // Create the TenderCorrigendumDetails, which fails.
        TenderCorrigendumDetailsDTO tenderCorrigendumDetailsDTO = tenderCorrigendumDetailsMapper.toDto(tenderCorrigendumDetails);

        restTenderCorrigendumDetailsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCorrigendumDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderCorrigendumDetails> tenderCorrigendumDetailsList = tenderCorrigendumDetailsRepository.findAll();
        assertThat(tenderCorrigendumDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkReferenceNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderCorrigendumDetailsRepository.findAll().size();
        // set the field null
        tenderCorrigendumDetails.setReferenceNumber(null);

        // Create the TenderCorrigendumDetails, which fails.
        TenderCorrigendumDetailsDTO tenderCorrigendumDetailsDTO = tenderCorrigendumDetailsMapper.toDto(tenderCorrigendumDetails);

        restTenderCorrigendumDetailsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCorrigendumDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderCorrigendumDetails> tenderCorrigendumDetailsList = tenderCorrigendumDetailsRepository.findAll();
        assertThat(tenderCorrigendumDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkReadAsIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderCorrigendumDetailsRepository.findAll().size();
        // set the field null
        tenderCorrigendumDetails.setReadAs(null);

        // Create the TenderCorrigendumDetails, which fails.
        TenderCorrigendumDetailsDTO tenderCorrigendumDetailsDTO = tenderCorrigendumDetailsMapper.toDto(tenderCorrigendumDetails);

        restTenderCorrigendumDetailsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCorrigendumDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderCorrigendumDetails> tenderCorrigendumDetailsList = tenderCorrigendumDetailsRepository.findAll();
        assertThat(tenderCorrigendumDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTenderCorrigendumDetails() throws Exception {
        // Initialize the database
        tenderCorrigendumDetailsRepository.saveAndFlush(tenderCorrigendumDetails);

        // Get all the tenderCorrigendumDetailsList
        restTenderCorrigendumDetailsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tenderCorrigendumDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].tenderCorrigendumId").value(hasItem(DEFAULT_TENDER_CORRIGENDUM_ID.intValue())))
            .andExpect(jsonPath("$.[*].referenceNumber").value(hasItem(DEFAULT_REFERENCE_NUMBER)))
            .andExpect(jsonPath("$.[*].readAs").value(hasItem(DEFAULT_READ_AS)));
    }

    @Test
    @Transactional
    void getTenderCorrigendumDetails() throws Exception {
        // Initialize the database
        tenderCorrigendumDetailsRepository.saveAndFlush(tenderCorrigendumDetails);

        // Get the tenderCorrigendumDetails
        restTenderCorrigendumDetailsMockMvc
            .perform(get(ENTITY_API_URL_ID, tenderCorrigendumDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tenderCorrigendumDetails.getId().intValue()))
            .andExpect(jsonPath("$.tenderCorrigendumId").value(DEFAULT_TENDER_CORRIGENDUM_ID.intValue()))
            .andExpect(jsonPath("$.referenceNumber").value(DEFAULT_REFERENCE_NUMBER))
            .andExpect(jsonPath("$.readAs").value(DEFAULT_READ_AS));
    }

    @Test
    @Transactional
    void getNonExistingTenderCorrigendumDetails() throws Exception {
        // Get the tenderCorrigendumDetails
        restTenderCorrigendumDetailsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTenderCorrigendumDetails() throws Exception {
        // Initialize the database
        tenderCorrigendumDetailsRepository.saveAndFlush(tenderCorrigendumDetails);

        int databaseSizeBeforeUpdate = tenderCorrigendumDetailsRepository.findAll().size();

        // Update the tenderCorrigendumDetails
        TenderCorrigendumDetails updatedTenderCorrigendumDetails = tenderCorrigendumDetailsRepository
            .findById(tenderCorrigendumDetails.getId())
            .get();
        // Disconnect from session so that the updates on updatedTenderCorrigendumDetails are not directly saved in db
        em.detach(updatedTenderCorrigendumDetails);
        updatedTenderCorrigendumDetails
            .tenderCorrigendumId(UPDATED_TENDER_CORRIGENDUM_ID)
            .referenceNumber(UPDATED_REFERENCE_NUMBER)
            .readAs(UPDATED_READ_AS);
        TenderCorrigendumDetailsDTO tenderCorrigendumDetailsDTO = tenderCorrigendumDetailsMapper.toDto(updatedTenderCorrigendumDetails);

        restTenderCorrigendumDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tenderCorrigendumDetailsDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCorrigendumDetailsDTO))
            )
            .andExpect(status().isOk());

        // Validate the TenderCorrigendumDetails in the database
        List<TenderCorrigendumDetails> tenderCorrigendumDetailsList = tenderCorrigendumDetailsRepository.findAll();
        assertThat(tenderCorrigendumDetailsList).hasSize(databaseSizeBeforeUpdate);
        TenderCorrigendumDetails testTenderCorrigendumDetails = tenderCorrigendumDetailsList.get(tenderCorrigendumDetailsList.size() - 1);
        assertThat(testTenderCorrigendumDetails.getTenderCorrigendumId()).isEqualTo(UPDATED_TENDER_CORRIGENDUM_ID);
        assertThat(testTenderCorrigendumDetails.getReferenceNumber()).isEqualTo(UPDATED_REFERENCE_NUMBER);
        assertThat(testTenderCorrigendumDetails.getReadAs()).isEqualTo(UPDATED_READ_AS);
    }

    @Test
    @Transactional
    void putNonExistingTenderCorrigendumDetails() throws Exception {
        int databaseSizeBeforeUpdate = tenderCorrigendumDetailsRepository.findAll().size();
        tenderCorrigendumDetails.setId(count.incrementAndGet());

        // Create the TenderCorrigendumDetails
        TenderCorrigendumDetailsDTO tenderCorrigendumDetailsDTO = tenderCorrigendumDetailsMapper.toDto(tenderCorrigendumDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTenderCorrigendumDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tenderCorrigendumDetailsDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCorrigendumDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderCorrigendumDetails in the database
        List<TenderCorrigendumDetails> tenderCorrigendumDetailsList = tenderCorrigendumDetailsRepository.findAll();
        assertThat(tenderCorrigendumDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTenderCorrigendumDetails() throws Exception {
        int databaseSizeBeforeUpdate = tenderCorrigendumDetailsRepository.findAll().size();
        tenderCorrigendumDetails.setId(count.incrementAndGet());

        // Create the TenderCorrigendumDetails
        TenderCorrigendumDetailsDTO tenderCorrigendumDetailsDTO = tenderCorrigendumDetailsMapper.toDto(tenderCorrigendumDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderCorrigendumDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCorrigendumDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderCorrigendumDetails in the database
        List<TenderCorrigendumDetails> tenderCorrigendumDetailsList = tenderCorrigendumDetailsRepository.findAll();
        assertThat(tenderCorrigendumDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTenderCorrigendumDetails() throws Exception {
        int databaseSizeBeforeUpdate = tenderCorrigendumDetailsRepository.findAll().size();
        tenderCorrigendumDetails.setId(count.incrementAndGet());

        // Create the TenderCorrigendumDetails
        TenderCorrigendumDetailsDTO tenderCorrigendumDetailsDTO = tenderCorrigendumDetailsMapper.toDto(tenderCorrigendumDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderCorrigendumDetailsMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCorrigendumDetailsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TenderCorrigendumDetails in the database
        List<TenderCorrigendumDetails> tenderCorrigendumDetailsList = tenderCorrigendumDetailsRepository.findAll();
        assertThat(tenderCorrigendumDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTenderCorrigendumDetailsWithPatch() throws Exception {
        // Initialize the database
        tenderCorrigendumDetailsRepository.saveAndFlush(tenderCorrigendumDetails);

        int databaseSizeBeforeUpdate = tenderCorrigendumDetailsRepository.findAll().size();

        // Update the tenderCorrigendumDetails using partial update
        TenderCorrigendumDetails partialUpdatedTenderCorrigendumDetails = new TenderCorrigendumDetails();
        partialUpdatedTenderCorrigendumDetails.setId(tenderCorrigendumDetails.getId());

        partialUpdatedTenderCorrigendumDetails.referenceNumber(UPDATED_REFERENCE_NUMBER).readAs(UPDATED_READ_AS);

        restTenderCorrigendumDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTenderCorrigendumDetails.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTenderCorrigendumDetails))
            )
            .andExpect(status().isOk());

        // Validate the TenderCorrigendumDetails in the database
        List<TenderCorrigendumDetails> tenderCorrigendumDetailsList = tenderCorrigendumDetailsRepository.findAll();
        assertThat(tenderCorrigendumDetailsList).hasSize(databaseSizeBeforeUpdate);
        TenderCorrigendumDetails testTenderCorrigendumDetails = tenderCorrigendumDetailsList.get(tenderCorrigendumDetailsList.size() - 1);
        assertThat(testTenderCorrigendumDetails.getTenderCorrigendumId()).isEqualTo(DEFAULT_TENDER_CORRIGENDUM_ID);
        assertThat(testTenderCorrigendumDetails.getReferenceNumber()).isEqualTo(UPDATED_REFERENCE_NUMBER);
        assertThat(testTenderCorrigendumDetails.getReadAs()).isEqualTo(UPDATED_READ_AS);
    }

    @Test
    @Transactional
    void fullUpdateTenderCorrigendumDetailsWithPatch() throws Exception {
        // Initialize the database
        tenderCorrigendumDetailsRepository.saveAndFlush(tenderCorrigendumDetails);

        int databaseSizeBeforeUpdate = tenderCorrigendumDetailsRepository.findAll().size();

        // Update the tenderCorrigendumDetails using partial update
        TenderCorrigendumDetails partialUpdatedTenderCorrigendumDetails = new TenderCorrigendumDetails();
        partialUpdatedTenderCorrigendumDetails.setId(tenderCorrigendumDetails.getId());

        partialUpdatedTenderCorrigendumDetails
            .tenderCorrigendumId(UPDATED_TENDER_CORRIGENDUM_ID)
            .referenceNumber(UPDATED_REFERENCE_NUMBER)
            .readAs(UPDATED_READ_AS);

        restTenderCorrigendumDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTenderCorrigendumDetails.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTenderCorrigendumDetails))
            )
            .andExpect(status().isOk());

        // Validate the TenderCorrigendumDetails in the database
        List<TenderCorrigendumDetails> tenderCorrigendumDetailsList = tenderCorrigendumDetailsRepository.findAll();
        assertThat(tenderCorrigendumDetailsList).hasSize(databaseSizeBeforeUpdate);
        TenderCorrigendumDetails testTenderCorrigendumDetails = tenderCorrigendumDetailsList.get(tenderCorrigendumDetailsList.size() - 1);
        assertThat(testTenderCorrigendumDetails.getTenderCorrigendumId()).isEqualTo(UPDATED_TENDER_CORRIGENDUM_ID);
        assertThat(testTenderCorrigendumDetails.getReferenceNumber()).isEqualTo(UPDATED_REFERENCE_NUMBER);
        assertThat(testTenderCorrigendumDetails.getReadAs()).isEqualTo(UPDATED_READ_AS);
    }

    @Test
    @Transactional
    void patchNonExistingTenderCorrigendumDetails() throws Exception {
        int databaseSizeBeforeUpdate = tenderCorrigendumDetailsRepository.findAll().size();
        tenderCorrigendumDetails.setId(count.incrementAndGet());

        // Create the TenderCorrigendumDetails
        TenderCorrigendumDetailsDTO tenderCorrigendumDetailsDTO = tenderCorrigendumDetailsMapper.toDto(tenderCorrigendumDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTenderCorrigendumDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tenderCorrigendumDetailsDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderCorrigendumDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderCorrigendumDetails in the database
        List<TenderCorrigendumDetails> tenderCorrigendumDetailsList = tenderCorrigendumDetailsRepository.findAll();
        assertThat(tenderCorrigendumDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTenderCorrigendumDetails() throws Exception {
        int databaseSizeBeforeUpdate = tenderCorrigendumDetailsRepository.findAll().size();
        tenderCorrigendumDetails.setId(count.incrementAndGet());

        // Create the TenderCorrigendumDetails
        TenderCorrigendumDetailsDTO tenderCorrigendumDetailsDTO = tenderCorrigendumDetailsMapper.toDto(tenderCorrigendumDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderCorrigendumDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderCorrigendumDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderCorrigendumDetails in the database
        List<TenderCorrigendumDetails> tenderCorrigendumDetailsList = tenderCorrigendumDetailsRepository.findAll();
        assertThat(tenderCorrigendumDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTenderCorrigendumDetails() throws Exception {
        int databaseSizeBeforeUpdate = tenderCorrigendumDetailsRepository.findAll().size();
        tenderCorrigendumDetails.setId(count.incrementAndGet());

        // Create the TenderCorrigendumDetails
        TenderCorrigendumDetailsDTO tenderCorrigendumDetailsDTO = tenderCorrigendumDetailsMapper.toDto(tenderCorrigendumDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderCorrigendumDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderCorrigendumDetailsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TenderCorrigendumDetails in the database
        List<TenderCorrigendumDetails> tenderCorrigendumDetailsList = tenderCorrigendumDetailsRepository.findAll();
        assertThat(tenderCorrigendumDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTenderCorrigendumDetails() throws Exception {
        // Initialize the database
        tenderCorrigendumDetailsRepository.saveAndFlush(tenderCorrigendumDetails);

        int databaseSizeBeforeDelete = tenderCorrigendumDetailsRepository.findAll().size();

        // Delete the tenderCorrigendumDetails
        restTenderCorrigendumDetailsMockMvc
            .perform(delete(ENTITY_API_URL_ID, tenderCorrigendumDetails.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TenderCorrigendumDetails> tenderCorrigendumDetailsList = tenderCorrigendumDetailsRepository.findAll();
        assertThat(tenderCorrigendumDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
