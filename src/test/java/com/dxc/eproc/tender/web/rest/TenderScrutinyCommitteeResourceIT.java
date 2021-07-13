package com.dxc.eproc.tender.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dxc.eproc.tender.IntegrationTest;
import com.dxc.eproc.tender.domain.TenderScrutinyCommittee;
import com.dxc.eproc.tender.repository.TenderScrutinyCommitteeRepository;
import com.dxc.eproc.tender.service.dto.TenderScrutinyCommitteeDTO;
import com.dxc.eproc.tender.service.mapper.TenderScrutinyCommitteeMapper;
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
 * Integration tests for the {@link TenderScrutinyCommitteeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TenderScrutinyCommitteeResourceIT {

    private static final Long DEFAULT_NIT_ID = 1L;
    private static final Long UPDATED_NIT_ID = 2L;

    private static final Long DEFAULT_STAFF_ID = 1L;
    private static final Long UPDATED_STAFF_ID = 2L;

    private static final Long DEFAULT_TENDER_SCRUTINY_ACTIVITY_ID = 1L;
    private static final Long UPDATED_TENDER_SCRUTINY_ACTIVITY_ID = 2L;

    private static final String ENTITY_API_URL = "/api/tender-scrutiny-committees";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TenderScrutinyCommitteeRepository tenderScrutinyCommitteeRepository;

    @Autowired
    private TenderScrutinyCommitteeMapper tenderScrutinyCommitteeMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTenderScrutinyCommitteeMockMvc;

    private TenderScrutinyCommittee tenderScrutinyCommittee;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TenderScrutinyCommittee createEntity(EntityManager em) {
        TenderScrutinyCommittee tenderScrutinyCommittee = new TenderScrutinyCommittee()
            .nitId(DEFAULT_NIT_ID)
            .staffId(DEFAULT_STAFF_ID)
            .tenderScrutinyActivityId(DEFAULT_TENDER_SCRUTINY_ACTIVITY_ID);
        return tenderScrutinyCommittee;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TenderScrutinyCommittee createUpdatedEntity(EntityManager em) {
        TenderScrutinyCommittee tenderScrutinyCommittee = new TenderScrutinyCommittee()
            .nitId(UPDATED_NIT_ID)
            .staffId(UPDATED_STAFF_ID)
            .tenderScrutinyActivityId(UPDATED_TENDER_SCRUTINY_ACTIVITY_ID);
        return tenderScrutinyCommittee;
    }

    @BeforeEach
    public void initTest() {
        tenderScrutinyCommittee = createEntity(em);
    }

    @Test
    @Transactional
    void createTenderScrutinyCommittee() throws Exception {
        int databaseSizeBeforeCreate = tenderScrutinyCommitteeRepository.findAll().size();
        // Create the TenderScrutinyCommittee
        TenderScrutinyCommitteeDTO tenderScrutinyCommitteeDTO = tenderScrutinyCommitteeMapper.toDto(tenderScrutinyCommittee);
        restTenderScrutinyCommitteeMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScrutinyCommitteeDTO))
            )
            .andExpect(status().isCreated());

        // Validate the TenderScrutinyCommittee in the database
        List<TenderScrutinyCommittee> tenderScrutinyCommitteeList = tenderScrutinyCommitteeRepository.findAll();
        assertThat(tenderScrutinyCommitteeList).hasSize(databaseSizeBeforeCreate + 1);
        TenderScrutinyCommittee testTenderScrutinyCommittee = tenderScrutinyCommitteeList.get(tenderScrutinyCommitteeList.size() - 1);
        assertThat(testTenderScrutinyCommittee.getNitId()).isEqualTo(DEFAULT_NIT_ID);
        assertThat(testTenderScrutinyCommittee.getStaffId()).isEqualTo(DEFAULT_STAFF_ID);
        assertThat(testTenderScrutinyCommittee.getTenderScrutinyActivityId()).isEqualTo(DEFAULT_TENDER_SCRUTINY_ACTIVITY_ID);
    }

    @Test
    @Transactional
    void createTenderScrutinyCommitteeWithExistingId() throws Exception {
        // Create the TenderScrutinyCommittee with an existing ID
        tenderScrutinyCommittee.setId(1L);
        TenderScrutinyCommitteeDTO tenderScrutinyCommitteeDTO = tenderScrutinyCommitteeMapper.toDto(tenderScrutinyCommittee);

        int databaseSizeBeforeCreate = tenderScrutinyCommitteeRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTenderScrutinyCommitteeMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScrutinyCommitteeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderScrutinyCommittee in the database
        List<TenderScrutinyCommittee> tenderScrutinyCommitteeList = tenderScrutinyCommitteeRepository.findAll();
        assertThat(tenderScrutinyCommitteeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNitIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderScrutinyCommitteeRepository.findAll().size();
        // set the field null
        tenderScrutinyCommittee.setNitId(null);

        // Create the TenderScrutinyCommittee, which fails.
        TenderScrutinyCommitteeDTO tenderScrutinyCommitteeDTO = tenderScrutinyCommitteeMapper.toDto(tenderScrutinyCommittee);

        restTenderScrutinyCommitteeMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScrutinyCommitteeDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderScrutinyCommittee> tenderScrutinyCommitteeList = tenderScrutinyCommitteeRepository.findAll();
        assertThat(tenderScrutinyCommitteeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkStaffIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderScrutinyCommitteeRepository.findAll().size();
        // set the field null
        tenderScrutinyCommittee.setStaffId(null);

        // Create the TenderScrutinyCommittee, which fails.
        TenderScrutinyCommitteeDTO tenderScrutinyCommitteeDTO = tenderScrutinyCommitteeMapper.toDto(tenderScrutinyCommittee);

        restTenderScrutinyCommitteeMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScrutinyCommitteeDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderScrutinyCommittee> tenderScrutinyCommitteeList = tenderScrutinyCommitteeRepository.findAll();
        assertThat(tenderScrutinyCommitteeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTenderScrutinyActivityIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderScrutinyCommitteeRepository.findAll().size();
        // set the field null
        tenderScrutinyCommittee.setTenderScrutinyActivityId(null);

        // Create the TenderScrutinyCommittee, which fails.
        TenderScrutinyCommitteeDTO tenderScrutinyCommitteeDTO = tenderScrutinyCommitteeMapper.toDto(tenderScrutinyCommittee);

        restTenderScrutinyCommitteeMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScrutinyCommitteeDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderScrutinyCommittee> tenderScrutinyCommitteeList = tenderScrutinyCommitteeRepository.findAll();
        assertThat(tenderScrutinyCommitteeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTenderScrutinyCommittees() throws Exception {
        // Initialize the database
        tenderScrutinyCommitteeRepository.saveAndFlush(tenderScrutinyCommittee);

        // Get all the tenderScrutinyCommitteeList
        restTenderScrutinyCommitteeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tenderScrutinyCommittee.getId().intValue())))
            .andExpect(jsonPath("$.[*].nitId").value(hasItem(DEFAULT_NIT_ID.intValue())))
            .andExpect(jsonPath("$.[*].staffId").value(hasItem(DEFAULT_STAFF_ID.intValue())))
            .andExpect(jsonPath("$.[*].tenderScrutinyActivityId").value(hasItem(DEFAULT_TENDER_SCRUTINY_ACTIVITY_ID.intValue())));
    }

    @Test
    @Transactional
    void getTenderScrutinyCommittee() throws Exception {
        // Initialize the database
        tenderScrutinyCommitteeRepository.saveAndFlush(tenderScrutinyCommittee);

        // Get the tenderScrutinyCommittee
        restTenderScrutinyCommitteeMockMvc
            .perform(get(ENTITY_API_URL_ID, tenderScrutinyCommittee.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tenderScrutinyCommittee.getId().intValue()))
            .andExpect(jsonPath("$.nitId").value(DEFAULT_NIT_ID.intValue()))
            .andExpect(jsonPath("$.staffId").value(DEFAULT_STAFF_ID.intValue()))
            .andExpect(jsonPath("$.tenderScrutinyActivityId").value(DEFAULT_TENDER_SCRUTINY_ACTIVITY_ID.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingTenderScrutinyCommittee() throws Exception {
        // Get the tenderScrutinyCommittee
        restTenderScrutinyCommitteeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTenderScrutinyCommittee() throws Exception {
        // Initialize the database
        tenderScrutinyCommitteeRepository.saveAndFlush(tenderScrutinyCommittee);

        int databaseSizeBeforeUpdate = tenderScrutinyCommitteeRepository.findAll().size();

        // Update the tenderScrutinyCommittee
        TenderScrutinyCommittee updatedTenderScrutinyCommittee = tenderScrutinyCommitteeRepository
            .findById(tenderScrutinyCommittee.getId())
            .get();
        // Disconnect from session so that the updates on updatedTenderScrutinyCommittee are not directly saved in db
        em.detach(updatedTenderScrutinyCommittee);
        updatedTenderScrutinyCommittee
            .nitId(UPDATED_NIT_ID)
            .staffId(UPDATED_STAFF_ID)
            .tenderScrutinyActivityId(UPDATED_TENDER_SCRUTINY_ACTIVITY_ID);
        TenderScrutinyCommitteeDTO tenderScrutinyCommitteeDTO = tenderScrutinyCommitteeMapper.toDto(updatedTenderScrutinyCommittee);

        restTenderScrutinyCommitteeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tenderScrutinyCommitteeDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScrutinyCommitteeDTO))
            )
            .andExpect(status().isOk());

        // Validate the TenderScrutinyCommittee in the database
        List<TenderScrutinyCommittee> tenderScrutinyCommitteeList = tenderScrutinyCommitteeRepository.findAll();
        assertThat(tenderScrutinyCommitteeList).hasSize(databaseSizeBeforeUpdate);
        TenderScrutinyCommittee testTenderScrutinyCommittee = tenderScrutinyCommitteeList.get(tenderScrutinyCommitteeList.size() - 1);
        assertThat(testTenderScrutinyCommittee.getNitId()).isEqualTo(UPDATED_NIT_ID);
        assertThat(testTenderScrutinyCommittee.getStaffId()).isEqualTo(UPDATED_STAFF_ID);
        assertThat(testTenderScrutinyCommittee.getTenderScrutinyActivityId()).isEqualTo(UPDATED_TENDER_SCRUTINY_ACTIVITY_ID);
    }

    @Test
    @Transactional
    void putNonExistingTenderScrutinyCommittee() throws Exception {
        int databaseSizeBeforeUpdate = tenderScrutinyCommitteeRepository.findAll().size();
        tenderScrutinyCommittee.setId(count.incrementAndGet());

        // Create the TenderScrutinyCommittee
        TenderScrutinyCommitteeDTO tenderScrutinyCommitteeDTO = tenderScrutinyCommitteeMapper.toDto(tenderScrutinyCommittee);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTenderScrutinyCommitteeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tenderScrutinyCommitteeDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScrutinyCommitteeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderScrutinyCommittee in the database
        List<TenderScrutinyCommittee> tenderScrutinyCommitteeList = tenderScrutinyCommitteeRepository.findAll();
        assertThat(tenderScrutinyCommitteeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTenderScrutinyCommittee() throws Exception {
        int databaseSizeBeforeUpdate = tenderScrutinyCommitteeRepository.findAll().size();
        tenderScrutinyCommittee.setId(count.incrementAndGet());

        // Create the TenderScrutinyCommittee
        TenderScrutinyCommitteeDTO tenderScrutinyCommitteeDTO = tenderScrutinyCommitteeMapper.toDto(tenderScrutinyCommittee);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderScrutinyCommitteeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScrutinyCommitteeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderScrutinyCommittee in the database
        List<TenderScrutinyCommittee> tenderScrutinyCommitteeList = tenderScrutinyCommitteeRepository.findAll();
        assertThat(tenderScrutinyCommitteeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTenderScrutinyCommittee() throws Exception {
        int databaseSizeBeforeUpdate = tenderScrutinyCommitteeRepository.findAll().size();
        tenderScrutinyCommittee.setId(count.incrementAndGet());

        // Create the TenderScrutinyCommittee
        TenderScrutinyCommitteeDTO tenderScrutinyCommitteeDTO = tenderScrutinyCommitteeMapper.toDto(tenderScrutinyCommittee);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderScrutinyCommitteeMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScrutinyCommitteeDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TenderScrutinyCommittee in the database
        List<TenderScrutinyCommittee> tenderScrutinyCommitteeList = tenderScrutinyCommitteeRepository.findAll();
        assertThat(tenderScrutinyCommitteeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTenderScrutinyCommitteeWithPatch() throws Exception {
        // Initialize the database
        tenderScrutinyCommitteeRepository.saveAndFlush(tenderScrutinyCommittee);

        int databaseSizeBeforeUpdate = tenderScrutinyCommitteeRepository.findAll().size();

        // Update the tenderScrutinyCommittee using partial update
        TenderScrutinyCommittee partialUpdatedTenderScrutinyCommittee = new TenderScrutinyCommittee();
        partialUpdatedTenderScrutinyCommittee.setId(tenderScrutinyCommittee.getId());

        partialUpdatedTenderScrutinyCommittee.tenderScrutinyActivityId(UPDATED_TENDER_SCRUTINY_ACTIVITY_ID);

        restTenderScrutinyCommitteeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTenderScrutinyCommittee.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTenderScrutinyCommittee))
            )
            .andExpect(status().isOk());

        // Validate the TenderScrutinyCommittee in the database
        List<TenderScrutinyCommittee> tenderScrutinyCommitteeList = tenderScrutinyCommitteeRepository.findAll();
        assertThat(tenderScrutinyCommitteeList).hasSize(databaseSizeBeforeUpdate);
        TenderScrutinyCommittee testTenderScrutinyCommittee = tenderScrutinyCommitteeList.get(tenderScrutinyCommitteeList.size() - 1);
        assertThat(testTenderScrutinyCommittee.getNitId()).isEqualTo(DEFAULT_NIT_ID);
        assertThat(testTenderScrutinyCommittee.getStaffId()).isEqualTo(DEFAULT_STAFF_ID);
        assertThat(testTenderScrutinyCommittee.getTenderScrutinyActivityId()).isEqualTo(UPDATED_TENDER_SCRUTINY_ACTIVITY_ID);
    }

    @Test
    @Transactional
    void fullUpdateTenderScrutinyCommitteeWithPatch() throws Exception {
        // Initialize the database
        tenderScrutinyCommitteeRepository.saveAndFlush(tenderScrutinyCommittee);

        int databaseSizeBeforeUpdate = tenderScrutinyCommitteeRepository.findAll().size();

        // Update the tenderScrutinyCommittee using partial update
        TenderScrutinyCommittee partialUpdatedTenderScrutinyCommittee = new TenderScrutinyCommittee();
        partialUpdatedTenderScrutinyCommittee.setId(tenderScrutinyCommittee.getId());

        partialUpdatedTenderScrutinyCommittee
            .nitId(UPDATED_NIT_ID)
            .staffId(UPDATED_STAFF_ID)
            .tenderScrutinyActivityId(UPDATED_TENDER_SCRUTINY_ACTIVITY_ID);

        restTenderScrutinyCommitteeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTenderScrutinyCommittee.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTenderScrutinyCommittee))
            )
            .andExpect(status().isOk());

        // Validate the TenderScrutinyCommittee in the database
        List<TenderScrutinyCommittee> tenderScrutinyCommitteeList = tenderScrutinyCommitteeRepository.findAll();
        assertThat(tenderScrutinyCommitteeList).hasSize(databaseSizeBeforeUpdate);
        TenderScrutinyCommittee testTenderScrutinyCommittee = tenderScrutinyCommitteeList.get(tenderScrutinyCommitteeList.size() - 1);
        assertThat(testTenderScrutinyCommittee.getNitId()).isEqualTo(UPDATED_NIT_ID);
        assertThat(testTenderScrutinyCommittee.getStaffId()).isEqualTo(UPDATED_STAFF_ID);
        assertThat(testTenderScrutinyCommittee.getTenderScrutinyActivityId()).isEqualTo(UPDATED_TENDER_SCRUTINY_ACTIVITY_ID);
    }

    @Test
    @Transactional
    void patchNonExistingTenderScrutinyCommittee() throws Exception {
        int databaseSizeBeforeUpdate = tenderScrutinyCommitteeRepository.findAll().size();
        tenderScrutinyCommittee.setId(count.incrementAndGet());

        // Create the TenderScrutinyCommittee
        TenderScrutinyCommitteeDTO tenderScrutinyCommitteeDTO = tenderScrutinyCommitteeMapper.toDto(tenderScrutinyCommittee);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTenderScrutinyCommitteeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tenderScrutinyCommitteeDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderScrutinyCommitteeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderScrutinyCommittee in the database
        List<TenderScrutinyCommittee> tenderScrutinyCommitteeList = tenderScrutinyCommitteeRepository.findAll();
        assertThat(tenderScrutinyCommitteeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTenderScrutinyCommittee() throws Exception {
        int databaseSizeBeforeUpdate = tenderScrutinyCommitteeRepository.findAll().size();
        tenderScrutinyCommittee.setId(count.incrementAndGet());

        // Create the TenderScrutinyCommittee
        TenderScrutinyCommitteeDTO tenderScrutinyCommitteeDTO = tenderScrutinyCommitteeMapper.toDto(tenderScrutinyCommittee);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderScrutinyCommitteeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderScrutinyCommitteeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderScrutinyCommittee in the database
        List<TenderScrutinyCommittee> tenderScrutinyCommitteeList = tenderScrutinyCommitteeRepository.findAll();
        assertThat(tenderScrutinyCommitteeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTenderScrutinyCommittee() throws Exception {
        int databaseSizeBeforeUpdate = tenderScrutinyCommitteeRepository.findAll().size();
        tenderScrutinyCommittee.setId(count.incrementAndGet());

        // Create the TenderScrutinyCommittee
        TenderScrutinyCommitteeDTO tenderScrutinyCommitteeDTO = tenderScrutinyCommitteeMapper.toDto(tenderScrutinyCommittee);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderScrutinyCommitteeMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderScrutinyCommitteeDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TenderScrutinyCommittee in the database
        List<TenderScrutinyCommittee> tenderScrutinyCommitteeList = tenderScrutinyCommitteeRepository.findAll();
        assertThat(tenderScrutinyCommitteeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTenderScrutinyCommittee() throws Exception {
        // Initialize the database
        tenderScrutinyCommitteeRepository.saveAndFlush(tenderScrutinyCommittee);

        int databaseSizeBeforeDelete = tenderScrutinyCommitteeRepository.findAll().size();

        // Delete the tenderScrutinyCommittee
        restTenderScrutinyCommitteeMockMvc
            .perform(delete(ENTITY_API_URL_ID, tenderScrutinyCommittee.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TenderScrutinyCommittee> tenderScrutinyCommitteeList = tenderScrutinyCommitteeRepository.findAll();
        assertThat(tenderScrutinyCommitteeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
