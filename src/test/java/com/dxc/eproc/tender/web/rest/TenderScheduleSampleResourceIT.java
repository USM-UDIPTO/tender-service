package com.dxc.eproc.tender.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dxc.eproc.tender.IntegrationTest;
import com.dxc.eproc.tender.domain.TenderScheduleSample;
import com.dxc.eproc.tender.repository.TenderScheduleSampleRepository;
import com.dxc.eproc.tender.service.dto.TenderScheduleSampleDTO;
import com.dxc.eproc.tender.service.mapper.TenderScheduleSampleMapper;
import java.time.LocalDate;
import java.time.ZoneId;
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
 * Integration tests for the {@link TenderScheduleSampleResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TenderScheduleSampleResourceIT {

    private static final Long DEFAULT_NIT_ID = 1L;
    private static final Long UPDATED_NIT_ID = 2L;

    private static final Long DEFAULT_ADDRESS_ID = 1L;
    private static final Long UPDATED_ADDRESS_ID = 2L;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESIGNATION = "AAAAAAAAAA";
    private static final String UPDATED_DESIGNATION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/tender-schedule-samples";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TenderScheduleSampleRepository tenderScheduleSampleRepository;

    @Autowired
    private TenderScheduleSampleMapper tenderScheduleSampleMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTenderScheduleSampleMockMvc;

    private TenderScheduleSample tenderScheduleSample;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TenderScheduleSample createEntity(EntityManager em) {
        TenderScheduleSample tenderScheduleSample = new TenderScheduleSample()
            .nitId(DEFAULT_NIT_ID)
            .addressId(DEFAULT_ADDRESS_ID)
            .name(DEFAULT_NAME)
            .designation(DEFAULT_DESIGNATION)
            .date(DEFAULT_DATE);
        return tenderScheduleSample;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TenderScheduleSample createUpdatedEntity(EntityManager em) {
        TenderScheduleSample tenderScheduleSample = new TenderScheduleSample()
            .nitId(UPDATED_NIT_ID)
            .addressId(UPDATED_ADDRESS_ID)
            .name(UPDATED_NAME)
            .designation(UPDATED_DESIGNATION)
            .date(UPDATED_DATE);
        return tenderScheduleSample;
    }

    @BeforeEach
    public void initTest() {
        tenderScheduleSample = createEntity(em);
    }

    @Test
    @Transactional
    void createTenderScheduleSample() throws Exception {
        int databaseSizeBeforeCreate = tenderScheduleSampleRepository.findAll().size();
        // Create the TenderScheduleSample
        TenderScheduleSampleDTO tenderScheduleSampleDTO = tenderScheduleSampleMapper.toDto(tenderScheduleSample);
        restTenderScheduleSampleMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleSampleDTO))
            )
            .andExpect(status().isCreated());

        // Validate the TenderScheduleSample in the database
        List<TenderScheduleSample> tenderScheduleSampleList = tenderScheduleSampleRepository.findAll();
        assertThat(tenderScheduleSampleList).hasSize(databaseSizeBeforeCreate + 1);
        TenderScheduleSample testTenderScheduleSample = tenderScheduleSampleList.get(tenderScheduleSampleList.size() - 1);
        assertThat(testTenderScheduleSample.getNitId()).isEqualTo(DEFAULT_NIT_ID);
        assertThat(testTenderScheduleSample.getAddressId()).isEqualTo(DEFAULT_ADDRESS_ID);
        assertThat(testTenderScheduleSample.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testTenderScheduleSample.getDesignation()).isEqualTo(DEFAULT_DESIGNATION);
        assertThat(testTenderScheduleSample.getDate()).isEqualTo(DEFAULT_DATE);
    }

    @Test
    @Transactional
    void createTenderScheduleSampleWithExistingId() throws Exception {
        // Create the TenderScheduleSample with an existing ID
        tenderScheduleSample.setId(1L);
        TenderScheduleSampleDTO tenderScheduleSampleDTO = tenderScheduleSampleMapper.toDto(tenderScheduleSample);

        int databaseSizeBeforeCreate = tenderScheduleSampleRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTenderScheduleSampleMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleSampleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderScheduleSample in the database
        List<TenderScheduleSample> tenderScheduleSampleList = tenderScheduleSampleRepository.findAll();
        assertThat(tenderScheduleSampleList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNitIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderScheduleSampleRepository.findAll().size();
        // set the field null
        tenderScheduleSample.setNitId(null);

        // Create the TenderScheduleSample, which fails.
        TenderScheduleSampleDTO tenderScheduleSampleDTO = tenderScheduleSampleMapper.toDto(tenderScheduleSample);

        restTenderScheduleSampleMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleSampleDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderScheduleSample> tenderScheduleSampleList = tenderScheduleSampleRepository.findAll();
        assertThat(tenderScheduleSampleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkAddressIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderScheduleSampleRepository.findAll().size();
        // set the field null
        tenderScheduleSample.setAddressId(null);

        // Create the TenderScheduleSample, which fails.
        TenderScheduleSampleDTO tenderScheduleSampleDTO = tenderScheduleSampleMapper.toDto(tenderScheduleSample);

        restTenderScheduleSampleMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleSampleDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderScheduleSample> tenderScheduleSampleList = tenderScheduleSampleRepository.findAll();
        assertThat(tenderScheduleSampleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderScheduleSampleRepository.findAll().size();
        // set the field null
        tenderScheduleSample.setName(null);

        // Create the TenderScheduleSample, which fails.
        TenderScheduleSampleDTO tenderScheduleSampleDTO = tenderScheduleSampleMapper.toDto(tenderScheduleSample);

        restTenderScheduleSampleMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleSampleDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderScheduleSample> tenderScheduleSampleList = tenderScheduleSampleRepository.findAll();
        assertThat(tenderScheduleSampleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDesignationIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderScheduleSampleRepository.findAll().size();
        // set the field null
        tenderScheduleSample.setDesignation(null);

        // Create the TenderScheduleSample, which fails.
        TenderScheduleSampleDTO tenderScheduleSampleDTO = tenderScheduleSampleMapper.toDto(tenderScheduleSample);

        restTenderScheduleSampleMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleSampleDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderScheduleSample> tenderScheduleSampleList = tenderScheduleSampleRepository.findAll();
        assertThat(tenderScheduleSampleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderScheduleSampleRepository.findAll().size();
        // set the field null
        tenderScheduleSample.setDate(null);

        // Create the TenderScheduleSample, which fails.
        TenderScheduleSampleDTO tenderScheduleSampleDTO = tenderScheduleSampleMapper.toDto(tenderScheduleSample);

        restTenderScheduleSampleMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleSampleDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderScheduleSample> tenderScheduleSampleList = tenderScheduleSampleRepository.findAll();
        assertThat(tenderScheduleSampleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTenderScheduleSamples() throws Exception {
        // Initialize the database
        tenderScheduleSampleRepository.saveAndFlush(tenderScheduleSample);

        // Get all the tenderScheduleSampleList
        restTenderScheduleSampleMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tenderScheduleSample.getId().intValue())))
            .andExpect(jsonPath("$.[*].nitId").value(hasItem(DEFAULT_NIT_ID.intValue())))
            .andExpect(jsonPath("$.[*].addressId").value(hasItem(DEFAULT_ADDRESS_ID.intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].designation").value(hasItem(DEFAULT_DESIGNATION)))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())));
    }

    @Test
    @Transactional
    void getTenderScheduleSample() throws Exception {
        // Initialize the database
        tenderScheduleSampleRepository.saveAndFlush(tenderScheduleSample);

        // Get the tenderScheduleSample
        restTenderScheduleSampleMockMvc
            .perform(get(ENTITY_API_URL_ID, tenderScheduleSample.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tenderScheduleSample.getId().intValue()))
            .andExpect(jsonPath("$.nitId").value(DEFAULT_NIT_ID.intValue()))
            .andExpect(jsonPath("$.addressId").value(DEFAULT_ADDRESS_ID.intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.designation").value(DEFAULT_DESIGNATION))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()));
    }

    @Test
    @Transactional
    void getNonExistingTenderScheduleSample() throws Exception {
        // Get the tenderScheduleSample
        restTenderScheduleSampleMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTenderScheduleSample() throws Exception {
        // Initialize the database
        tenderScheduleSampleRepository.saveAndFlush(tenderScheduleSample);

        int databaseSizeBeforeUpdate = tenderScheduleSampleRepository.findAll().size();

        // Update the tenderScheduleSample
        TenderScheduleSample updatedTenderScheduleSample = tenderScheduleSampleRepository.findById(tenderScheduleSample.getId()).get();
        // Disconnect from session so that the updates on updatedTenderScheduleSample are not directly saved in db
        em.detach(updatedTenderScheduleSample);
        updatedTenderScheduleSample
            .nitId(UPDATED_NIT_ID)
            .addressId(UPDATED_ADDRESS_ID)
            .name(UPDATED_NAME)
            .designation(UPDATED_DESIGNATION)
            .date(UPDATED_DATE);
        TenderScheduleSampleDTO tenderScheduleSampleDTO = tenderScheduleSampleMapper.toDto(updatedTenderScheduleSample);

        restTenderScheduleSampleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tenderScheduleSampleDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleSampleDTO))
            )
            .andExpect(status().isOk());

        // Validate the TenderScheduleSample in the database
        List<TenderScheduleSample> tenderScheduleSampleList = tenderScheduleSampleRepository.findAll();
        assertThat(tenderScheduleSampleList).hasSize(databaseSizeBeforeUpdate);
        TenderScheduleSample testTenderScheduleSample = tenderScheduleSampleList.get(tenderScheduleSampleList.size() - 1);
        assertThat(testTenderScheduleSample.getNitId()).isEqualTo(UPDATED_NIT_ID);
        assertThat(testTenderScheduleSample.getAddressId()).isEqualTo(UPDATED_ADDRESS_ID);
        assertThat(testTenderScheduleSample.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testTenderScheduleSample.getDesignation()).isEqualTo(UPDATED_DESIGNATION);
        assertThat(testTenderScheduleSample.getDate()).isEqualTo(UPDATED_DATE);
    }

    @Test
    @Transactional
    void putNonExistingTenderScheduleSample() throws Exception {
        int databaseSizeBeforeUpdate = tenderScheduleSampleRepository.findAll().size();
        tenderScheduleSample.setId(count.incrementAndGet());

        // Create the TenderScheduleSample
        TenderScheduleSampleDTO tenderScheduleSampleDTO = tenderScheduleSampleMapper.toDto(tenderScheduleSample);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTenderScheduleSampleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tenderScheduleSampleDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleSampleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderScheduleSample in the database
        List<TenderScheduleSample> tenderScheduleSampleList = tenderScheduleSampleRepository.findAll();
        assertThat(tenderScheduleSampleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTenderScheduleSample() throws Exception {
        int databaseSizeBeforeUpdate = tenderScheduleSampleRepository.findAll().size();
        tenderScheduleSample.setId(count.incrementAndGet());

        // Create the TenderScheduleSample
        TenderScheduleSampleDTO tenderScheduleSampleDTO = tenderScheduleSampleMapper.toDto(tenderScheduleSample);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderScheduleSampleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleSampleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderScheduleSample in the database
        List<TenderScheduleSample> tenderScheduleSampleList = tenderScheduleSampleRepository.findAll();
        assertThat(tenderScheduleSampleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTenderScheduleSample() throws Exception {
        int databaseSizeBeforeUpdate = tenderScheduleSampleRepository.findAll().size();
        tenderScheduleSample.setId(count.incrementAndGet());

        // Create the TenderScheduleSample
        TenderScheduleSampleDTO tenderScheduleSampleDTO = tenderScheduleSampleMapper.toDto(tenderScheduleSample);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderScheduleSampleMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleSampleDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TenderScheduleSample in the database
        List<TenderScheduleSample> tenderScheduleSampleList = tenderScheduleSampleRepository.findAll();
        assertThat(tenderScheduleSampleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTenderScheduleSampleWithPatch() throws Exception {
        // Initialize the database
        tenderScheduleSampleRepository.saveAndFlush(tenderScheduleSample);

        int databaseSizeBeforeUpdate = tenderScheduleSampleRepository.findAll().size();

        // Update the tenderScheduleSample using partial update
        TenderScheduleSample partialUpdatedTenderScheduleSample = new TenderScheduleSample();
        partialUpdatedTenderScheduleSample.setId(tenderScheduleSample.getId());

        partialUpdatedTenderScheduleSample.nitId(UPDATED_NIT_ID).name(UPDATED_NAME).designation(UPDATED_DESIGNATION);

        restTenderScheduleSampleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTenderScheduleSample.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTenderScheduleSample))
            )
            .andExpect(status().isOk());

        // Validate the TenderScheduleSample in the database
        List<TenderScheduleSample> tenderScheduleSampleList = tenderScheduleSampleRepository.findAll();
        assertThat(tenderScheduleSampleList).hasSize(databaseSizeBeforeUpdate);
        TenderScheduleSample testTenderScheduleSample = tenderScheduleSampleList.get(tenderScheduleSampleList.size() - 1);
        assertThat(testTenderScheduleSample.getNitId()).isEqualTo(UPDATED_NIT_ID);
        assertThat(testTenderScheduleSample.getAddressId()).isEqualTo(DEFAULT_ADDRESS_ID);
        assertThat(testTenderScheduleSample.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testTenderScheduleSample.getDesignation()).isEqualTo(UPDATED_DESIGNATION);
        assertThat(testTenderScheduleSample.getDate()).isEqualTo(DEFAULT_DATE);
    }

    @Test
    @Transactional
    void fullUpdateTenderScheduleSampleWithPatch() throws Exception {
        // Initialize the database
        tenderScheduleSampleRepository.saveAndFlush(tenderScheduleSample);

        int databaseSizeBeforeUpdate = tenderScheduleSampleRepository.findAll().size();

        // Update the tenderScheduleSample using partial update
        TenderScheduleSample partialUpdatedTenderScheduleSample = new TenderScheduleSample();
        partialUpdatedTenderScheduleSample.setId(tenderScheduleSample.getId());

        partialUpdatedTenderScheduleSample
            .nitId(UPDATED_NIT_ID)
            .addressId(UPDATED_ADDRESS_ID)
            .name(UPDATED_NAME)
            .designation(UPDATED_DESIGNATION)
            .date(UPDATED_DATE);

        restTenderScheduleSampleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTenderScheduleSample.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTenderScheduleSample))
            )
            .andExpect(status().isOk());

        // Validate the TenderScheduleSample in the database
        List<TenderScheduleSample> tenderScheduleSampleList = tenderScheduleSampleRepository.findAll();
        assertThat(tenderScheduleSampleList).hasSize(databaseSizeBeforeUpdate);
        TenderScheduleSample testTenderScheduleSample = tenderScheduleSampleList.get(tenderScheduleSampleList.size() - 1);
        assertThat(testTenderScheduleSample.getNitId()).isEqualTo(UPDATED_NIT_ID);
        assertThat(testTenderScheduleSample.getAddressId()).isEqualTo(UPDATED_ADDRESS_ID);
        assertThat(testTenderScheduleSample.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testTenderScheduleSample.getDesignation()).isEqualTo(UPDATED_DESIGNATION);
        assertThat(testTenderScheduleSample.getDate()).isEqualTo(UPDATED_DATE);
    }

    @Test
    @Transactional
    void patchNonExistingTenderScheduleSample() throws Exception {
        int databaseSizeBeforeUpdate = tenderScheduleSampleRepository.findAll().size();
        tenderScheduleSample.setId(count.incrementAndGet());

        // Create the TenderScheduleSample
        TenderScheduleSampleDTO tenderScheduleSampleDTO = tenderScheduleSampleMapper.toDto(tenderScheduleSample);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTenderScheduleSampleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tenderScheduleSampleDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleSampleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderScheduleSample in the database
        List<TenderScheduleSample> tenderScheduleSampleList = tenderScheduleSampleRepository.findAll();
        assertThat(tenderScheduleSampleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTenderScheduleSample() throws Exception {
        int databaseSizeBeforeUpdate = tenderScheduleSampleRepository.findAll().size();
        tenderScheduleSample.setId(count.incrementAndGet());

        // Create the TenderScheduleSample
        TenderScheduleSampleDTO tenderScheduleSampleDTO = tenderScheduleSampleMapper.toDto(tenderScheduleSample);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderScheduleSampleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleSampleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderScheduleSample in the database
        List<TenderScheduleSample> tenderScheduleSampleList = tenderScheduleSampleRepository.findAll();
        assertThat(tenderScheduleSampleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTenderScheduleSample() throws Exception {
        int databaseSizeBeforeUpdate = tenderScheduleSampleRepository.findAll().size();
        tenderScheduleSample.setId(count.incrementAndGet());

        // Create the TenderScheduleSample
        TenderScheduleSampleDTO tenderScheduleSampleDTO = tenderScheduleSampleMapper.toDto(tenderScheduleSample);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderScheduleSampleMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleSampleDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TenderScheduleSample in the database
        List<TenderScheduleSample> tenderScheduleSampleList = tenderScheduleSampleRepository.findAll();
        assertThat(tenderScheduleSampleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTenderScheduleSample() throws Exception {
        // Initialize the database
        tenderScheduleSampleRepository.saveAndFlush(tenderScheduleSample);

        int databaseSizeBeforeDelete = tenderScheduleSampleRepository.findAll().size();

        // Delete the tenderScheduleSample
        restTenderScheduleSampleMockMvc
            .perform(delete(ENTITY_API_URL_ID, tenderScheduleSample.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TenderScheduleSample> tenderScheduleSampleList = tenderScheduleSampleRepository.findAll();
        assertThat(tenderScheduleSampleList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
