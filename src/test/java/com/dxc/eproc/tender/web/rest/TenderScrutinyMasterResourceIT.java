package com.dxc.eproc.tender.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dxc.eproc.tender.IntegrationTest;
import com.dxc.eproc.tender.domain.TenderScrutinyMaster;
import com.dxc.eproc.tender.repository.TenderScrutinyMasterRepository;
import com.dxc.eproc.tender.service.dto.TenderScrutinyMasterDTO;
import com.dxc.eproc.tender.service.mapper.TenderScrutinyMasterMapper;
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
 * Integration tests for the {@link TenderScrutinyMasterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TenderScrutinyMasterResourceIT {

    private static final String DEFAULT_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_VALUE = "BBBBBBBBBB";

    private static final Integer DEFAULT_MAX_USERS = 1;
    private static final Integer UPDATED_MAX_USERS = 2;

    private static final Integer DEFAULT_MIN_USERS = 1;
    private static final Integer UPDATED_MIN_USERS = 2;

    private static final Boolean DEFAULT_ACTIVE_YN = false;
    private static final Boolean UPDATED_ACTIVE_YN = true;

    private static final String ENTITY_API_URL = "/api/tender-scrutiny-masters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TenderScrutinyMasterRepository tenderScrutinyMasterRepository;

    @Autowired
    private TenderScrutinyMasterMapper tenderScrutinyMasterMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTenderScrutinyMasterMockMvc;

    private TenderScrutinyMaster tenderScrutinyMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TenderScrutinyMaster createEntity(EntityManager em) {
        TenderScrutinyMaster tenderScrutinyMaster = new TenderScrutinyMaster()
            .value(DEFAULT_VALUE)
            .maxUsers(DEFAULT_MAX_USERS)
            .minUsers(DEFAULT_MIN_USERS)
            .activeYn(DEFAULT_ACTIVE_YN);
        return tenderScrutinyMaster;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TenderScrutinyMaster createUpdatedEntity(EntityManager em) {
        TenderScrutinyMaster tenderScrutinyMaster = new TenderScrutinyMaster()
            .value(UPDATED_VALUE)
            .maxUsers(UPDATED_MAX_USERS)
            .minUsers(UPDATED_MIN_USERS)
            .activeYn(UPDATED_ACTIVE_YN);
        return tenderScrutinyMaster;
    }

    @BeforeEach
    public void initTest() {
        tenderScrutinyMaster = createEntity(em);
    }

    @Test
    @Transactional
    void createTenderScrutinyMaster() throws Exception {
        int databaseSizeBeforeCreate = tenderScrutinyMasterRepository.findAll().size();
        // Create the TenderScrutinyMaster
        TenderScrutinyMasterDTO tenderScrutinyMasterDTO = tenderScrutinyMasterMapper.toDto(tenderScrutinyMaster);
        restTenderScrutinyMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScrutinyMasterDTO))
            )
            .andExpect(status().isCreated());

        // Validate the TenderScrutinyMaster in the database
        List<TenderScrutinyMaster> tenderScrutinyMasterList = tenderScrutinyMasterRepository.findAll();
        assertThat(tenderScrutinyMasterList).hasSize(databaseSizeBeforeCreate + 1);
        TenderScrutinyMaster testTenderScrutinyMaster = tenderScrutinyMasterList.get(tenderScrutinyMasterList.size() - 1);
        assertThat(testTenderScrutinyMaster.getValue()).isEqualTo(DEFAULT_VALUE);
        assertThat(testTenderScrutinyMaster.getMaxUsers()).isEqualTo(DEFAULT_MAX_USERS);
        assertThat(testTenderScrutinyMaster.getMinUsers()).isEqualTo(DEFAULT_MIN_USERS);
        assertThat(testTenderScrutinyMaster.getActiveYn()).isEqualTo(DEFAULT_ACTIVE_YN);
    }

    @Test
    @Transactional
    void createTenderScrutinyMasterWithExistingId() throws Exception {
        // Create the TenderScrutinyMaster with an existing ID
        tenderScrutinyMaster.setId(1L);
        TenderScrutinyMasterDTO tenderScrutinyMasterDTO = tenderScrutinyMasterMapper.toDto(tenderScrutinyMaster);

        int databaseSizeBeforeCreate = tenderScrutinyMasterRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTenderScrutinyMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScrutinyMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderScrutinyMaster in the database
        List<TenderScrutinyMaster> tenderScrutinyMasterList = tenderScrutinyMasterRepository.findAll();
        assertThat(tenderScrutinyMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkValueIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderScrutinyMasterRepository.findAll().size();
        // set the field null
        tenderScrutinyMaster.setValue(null);

        // Create the TenderScrutinyMaster, which fails.
        TenderScrutinyMasterDTO tenderScrutinyMasterDTO = tenderScrutinyMasterMapper.toDto(tenderScrutinyMaster);

        restTenderScrutinyMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScrutinyMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderScrutinyMaster> tenderScrutinyMasterList = tenderScrutinyMasterRepository.findAll();
        assertThat(tenderScrutinyMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkMaxUsersIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderScrutinyMasterRepository.findAll().size();
        // set the field null
        tenderScrutinyMaster.setMaxUsers(null);

        // Create the TenderScrutinyMaster, which fails.
        TenderScrutinyMasterDTO tenderScrutinyMasterDTO = tenderScrutinyMasterMapper.toDto(tenderScrutinyMaster);

        restTenderScrutinyMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScrutinyMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderScrutinyMaster> tenderScrutinyMasterList = tenderScrutinyMasterRepository.findAll();
        assertThat(tenderScrutinyMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkMinUsersIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderScrutinyMasterRepository.findAll().size();
        // set the field null
        tenderScrutinyMaster.setMinUsers(null);

        // Create the TenderScrutinyMaster, which fails.
        TenderScrutinyMasterDTO tenderScrutinyMasterDTO = tenderScrutinyMasterMapper.toDto(tenderScrutinyMaster);

        restTenderScrutinyMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScrutinyMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderScrutinyMaster> tenderScrutinyMasterList = tenderScrutinyMasterRepository.findAll();
        assertThat(tenderScrutinyMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkActiveYnIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderScrutinyMasterRepository.findAll().size();
        // set the field null
        tenderScrutinyMaster.setActiveYn(null);

        // Create the TenderScrutinyMaster, which fails.
        TenderScrutinyMasterDTO tenderScrutinyMasterDTO = tenderScrutinyMasterMapper.toDto(tenderScrutinyMaster);

        restTenderScrutinyMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScrutinyMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderScrutinyMaster> tenderScrutinyMasterList = tenderScrutinyMasterRepository.findAll();
        assertThat(tenderScrutinyMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTenderScrutinyMasters() throws Exception {
        // Initialize the database
        tenderScrutinyMasterRepository.saveAndFlush(tenderScrutinyMaster);

        // Get all the tenderScrutinyMasterList
        restTenderScrutinyMasterMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tenderScrutinyMaster.getId().intValue())))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE)))
            .andExpect(jsonPath("$.[*].maxUsers").value(hasItem(DEFAULT_MAX_USERS)))
            .andExpect(jsonPath("$.[*].minUsers").value(hasItem(DEFAULT_MIN_USERS)))
            .andExpect(jsonPath("$.[*].activeYn").value(hasItem(DEFAULT_ACTIVE_YN.booleanValue())));
    }

    @Test
    @Transactional
    void getTenderScrutinyMaster() throws Exception {
        // Initialize the database
        tenderScrutinyMasterRepository.saveAndFlush(tenderScrutinyMaster);

        // Get the tenderScrutinyMaster
        restTenderScrutinyMasterMockMvc
            .perform(get(ENTITY_API_URL_ID, tenderScrutinyMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tenderScrutinyMaster.getId().intValue()))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE))
            .andExpect(jsonPath("$.maxUsers").value(DEFAULT_MAX_USERS))
            .andExpect(jsonPath("$.minUsers").value(DEFAULT_MIN_USERS))
            .andExpect(jsonPath("$.activeYn").value(DEFAULT_ACTIVE_YN.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingTenderScrutinyMaster() throws Exception {
        // Get the tenderScrutinyMaster
        restTenderScrutinyMasterMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTenderScrutinyMaster() throws Exception {
        // Initialize the database
        tenderScrutinyMasterRepository.saveAndFlush(tenderScrutinyMaster);

        int databaseSizeBeforeUpdate = tenderScrutinyMasterRepository.findAll().size();

        // Update the tenderScrutinyMaster
        TenderScrutinyMaster updatedTenderScrutinyMaster = tenderScrutinyMasterRepository.findById(tenderScrutinyMaster.getId()).get();
        // Disconnect from session so that the updates on updatedTenderScrutinyMaster are not directly saved in db
        em.detach(updatedTenderScrutinyMaster);
        updatedTenderScrutinyMaster
            .value(UPDATED_VALUE)
            .maxUsers(UPDATED_MAX_USERS)
            .minUsers(UPDATED_MIN_USERS)
            .activeYn(UPDATED_ACTIVE_YN);
        TenderScrutinyMasterDTO tenderScrutinyMasterDTO = tenderScrutinyMasterMapper.toDto(updatedTenderScrutinyMaster);

        restTenderScrutinyMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tenderScrutinyMasterDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScrutinyMasterDTO))
            )
            .andExpect(status().isOk());

        // Validate the TenderScrutinyMaster in the database
        List<TenderScrutinyMaster> tenderScrutinyMasterList = tenderScrutinyMasterRepository.findAll();
        assertThat(tenderScrutinyMasterList).hasSize(databaseSizeBeforeUpdate);
        TenderScrutinyMaster testTenderScrutinyMaster = tenderScrutinyMasterList.get(tenderScrutinyMasterList.size() - 1);
        assertThat(testTenderScrutinyMaster.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testTenderScrutinyMaster.getMaxUsers()).isEqualTo(UPDATED_MAX_USERS);
        assertThat(testTenderScrutinyMaster.getMinUsers()).isEqualTo(UPDATED_MIN_USERS);
        assertThat(testTenderScrutinyMaster.getActiveYn()).isEqualTo(UPDATED_ACTIVE_YN);
    }

    @Test
    @Transactional
    void putNonExistingTenderScrutinyMaster() throws Exception {
        int databaseSizeBeforeUpdate = tenderScrutinyMasterRepository.findAll().size();
        tenderScrutinyMaster.setId(count.incrementAndGet());

        // Create the TenderScrutinyMaster
        TenderScrutinyMasterDTO tenderScrutinyMasterDTO = tenderScrutinyMasterMapper.toDto(tenderScrutinyMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTenderScrutinyMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tenderScrutinyMasterDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScrutinyMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderScrutinyMaster in the database
        List<TenderScrutinyMaster> tenderScrutinyMasterList = tenderScrutinyMasterRepository.findAll();
        assertThat(tenderScrutinyMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTenderScrutinyMaster() throws Exception {
        int databaseSizeBeforeUpdate = tenderScrutinyMasterRepository.findAll().size();
        tenderScrutinyMaster.setId(count.incrementAndGet());

        // Create the TenderScrutinyMaster
        TenderScrutinyMasterDTO tenderScrutinyMasterDTO = tenderScrutinyMasterMapper.toDto(tenderScrutinyMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderScrutinyMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScrutinyMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderScrutinyMaster in the database
        List<TenderScrutinyMaster> tenderScrutinyMasterList = tenderScrutinyMasterRepository.findAll();
        assertThat(tenderScrutinyMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTenderScrutinyMaster() throws Exception {
        int databaseSizeBeforeUpdate = tenderScrutinyMasterRepository.findAll().size();
        tenderScrutinyMaster.setId(count.incrementAndGet());

        // Create the TenderScrutinyMaster
        TenderScrutinyMasterDTO tenderScrutinyMasterDTO = tenderScrutinyMasterMapper.toDto(tenderScrutinyMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderScrutinyMasterMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScrutinyMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TenderScrutinyMaster in the database
        List<TenderScrutinyMaster> tenderScrutinyMasterList = tenderScrutinyMasterRepository.findAll();
        assertThat(tenderScrutinyMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTenderScrutinyMasterWithPatch() throws Exception {
        // Initialize the database
        tenderScrutinyMasterRepository.saveAndFlush(tenderScrutinyMaster);

        int databaseSizeBeforeUpdate = tenderScrutinyMasterRepository.findAll().size();

        // Update the tenderScrutinyMaster using partial update
        TenderScrutinyMaster partialUpdatedTenderScrutinyMaster = new TenderScrutinyMaster();
        partialUpdatedTenderScrutinyMaster.setId(tenderScrutinyMaster.getId());

        partialUpdatedTenderScrutinyMaster.maxUsers(UPDATED_MAX_USERS).minUsers(UPDATED_MIN_USERS);

        restTenderScrutinyMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTenderScrutinyMaster.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTenderScrutinyMaster))
            )
            .andExpect(status().isOk());

        // Validate the TenderScrutinyMaster in the database
        List<TenderScrutinyMaster> tenderScrutinyMasterList = tenderScrutinyMasterRepository.findAll();
        assertThat(tenderScrutinyMasterList).hasSize(databaseSizeBeforeUpdate);
        TenderScrutinyMaster testTenderScrutinyMaster = tenderScrutinyMasterList.get(tenderScrutinyMasterList.size() - 1);
        assertThat(testTenderScrutinyMaster.getValue()).isEqualTo(DEFAULT_VALUE);
        assertThat(testTenderScrutinyMaster.getMaxUsers()).isEqualTo(UPDATED_MAX_USERS);
        assertThat(testTenderScrutinyMaster.getMinUsers()).isEqualTo(UPDATED_MIN_USERS);
        assertThat(testTenderScrutinyMaster.getActiveYn()).isEqualTo(DEFAULT_ACTIVE_YN);
    }

    @Test
    @Transactional
    void fullUpdateTenderScrutinyMasterWithPatch() throws Exception {
        // Initialize the database
        tenderScrutinyMasterRepository.saveAndFlush(tenderScrutinyMaster);

        int databaseSizeBeforeUpdate = tenderScrutinyMasterRepository.findAll().size();

        // Update the tenderScrutinyMaster using partial update
        TenderScrutinyMaster partialUpdatedTenderScrutinyMaster = new TenderScrutinyMaster();
        partialUpdatedTenderScrutinyMaster.setId(tenderScrutinyMaster.getId());

        partialUpdatedTenderScrutinyMaster
            .value(UPDATED_VALUE)
            .maxUsers(UPDATED_MAX_USERS)
            .minUsers(UPDATED_MIN_USERS)
            .activeYn(UPDATED_ACTIVE_YN);

        restTenderScrutinyMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTenderScrutinyMaster.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTenderScrutinyMaster))
            )
            .andExpect(status().isOk());

        // Validate the TenderScrutinyMaster in the database
        List<TenderScrutinyMaster> tenderScrutinyMasterList = tenderScrutinyMasterRepository.findAll();
        assertThat(tenderScrutinyMasterList).hasSize(databaseSizeBeforeUpdate);
        TenderScrutinyMaster testTenderScrutinyMaster = tenderScrutinyMasterList.get(tenderScrutinyMasterList.size() - 1);
        assertThat(testTenderScrutinyMaster.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testTenderScrutinyMaster.getMaxUsers()).isEqualTo(UPDATED_MAX_USERS);
        assertThat(testTenderScrutinyMaster.getMinUsers()).isEqualTo(UPDATED_MIN_USERS);
        assertThat(testTenderScrutinyMaster.getActiveYn()).isEqualTo(UPDATED_ACTIVE_YN);
    }

    @Test
    @Transactional
    void patchNonExistingTenderScrutinyMaster() throws Exception {
        int databaseSizeBeforeUpdate = tenderScrutinyMasterRepository.findAll().size();
        tenderScrutinyMaster.setId(count.incrementAndGet());

        // Create the TenderScrutinyMaster
        TenderScrutinyMasterDTO tenderScrutinyMasterDTO = tenderScrutinyMasterMapper.toDto(tenderScrutinyMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTenderScrutinyMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tenderScrutinyMasterDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderScrutinyMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderScrutinyMaster in the database
        List<TenderScrutinyMaster> tenderScrutinyMasterList = tenderScrutinyMasterRepository.findAll();
        assertThat(tenderScrutinyMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTenderScrutinyMaster() throws Exception {
        int databaseSizeBeforeUpdate = tenderScrutinyMasterRepository.findAll().size();
        tenderScrutinyMaster.setId(count.incrementAndGet());

        // Create the TenderScrutinyMaster
        TenderScrutinyMasterDTO tenderScrutinyMasterDTO = tenderScrutinyMasterMapper.toDto(tenderScrutinyMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderScrutinyMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderScrutinyMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderScrutinyMaster in the database
        List<TenderScrutinyMaster> tenderScrutinyMasterList = tenderScrutinyMasterRepository.findAll();
        assertThat(tenderScrutinyMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTenderScrutinyMaster() throws Exception {
        int databaseSizeBeforeUpdate = tenderScrutinyMasterRepository.findAll().size();
        tenderScrutinyMaster.setId(count.incrementAndGet());

        // Create the TenderScrutinyMaster
        TenderScrutinyMasterDTO tenderScrutinyMasterDTO = tenderScrutinyMasterMapper.toDto(tenderScrutinyMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderScrutinyMasterMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderScrutinyMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TenderScrutinyMaster in the database
        List<TenderScrutinyMaster> tenderScrutinyMasterList = tenderScrutinyMasterRepository.findAll();
        assertThat(tenderScrutinyMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTenderScrutinyMaster() throws Exception {
        // Initialize the database
        tenderScrutinyMasterRepository.saveAndFlush(tenderScrutinyMaster);

        int databaseSizeBeforeDelete = tenderScrutinyMasterRepository.findAll().size();

        // Delete the tenderScrutinyMaster
        restTenderScrutinyMasterMockMvc
            .perform(delete(ENTITY_API_URL_ID, tenderScrutinyMaster.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TenderScrutinyMaster> tenderScrutinyMasterList = tenderScrutinyMasterRepository.findAll();
        assertThat(tenderScrutinyMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
