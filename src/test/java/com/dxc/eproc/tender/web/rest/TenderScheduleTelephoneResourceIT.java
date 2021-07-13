package com.dxc.eproc.tender.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dxc.eproc.tender.IntegrationTest;
import com.dxc.eproc.tender.domain.TenderScheduleTelephone;
import com.dxc.eproc.tender.repository.TenderScheduleTelephoneRepository;
import com.dxc.eproc.tender.service.dto.TenderScheduleTelephoneDTO;
import com.dxc.eproc.tender.service.mapper.TenderScheduleTelephoneMapper;
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
 * Integration tests for the {@link TenderScheduleTelephoneResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TenderScheduleTelephoneResourceIT {

    private static final Long DEFAULT_NIT_ID = 1L;
    private static final Long UPDATED_NIT_ID = 2L;

    private static final Long DEFAULT_STAFF_GENERAL_INFO_ID = 1L;
    private static final Long UPDATED_STAFF_GENERAL_INFO_ID = 2L;

    private static final Long DEFAULT_TENDER_QUERY_ID = 1L;
    private static final Long UPDATED_TENDER_QUERY_ID = 2L;

    private static final String DEFAULT_TENDER_QUERY_RESPONSE_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_TENDER_QUERY_RESPONSE_TEXT = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/tender-schedule-telephones";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TenderScheduleTelephoneRepository tenderScheduleTelephoneRepository;

    @Autowired
    private TenderScheduleTelephoneMapper tenderScheduleTelephoneMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTenderScheduleTelephoneMockMvc;

    private TenderScheduleTelephone tenderScheduleTelephone;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TenderScheduleTelephone createEntity(EntityManager em) {
        TenderScheduleTelephone tenderScheduleTelephone = new TenderScheduleTelephone()
            .nitId(DEFAULT_NIT_ID)
            .staffGeneralInfoId(DEFAULT_STAFF_GENERAL_INFO_ID)
            .tenderQueryId(DEFAULT_TENDER_QUERY_ID)
            .tenderQueryResponseText(DEFAULT_TENDER_QUERY_RESPONSE_TEXT);
        return tenderScheduleTelephone;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TenderScheduleTelephone createUpdatedEntity(EntityManager em) {
        TenderScheduleTelephone tenderScheduleTelephone = new TenderScheduleTelephone()
            .nitId(UPDATED_NIT_ID)
            .staffGeneralInfoId(UPDATED_STAFF_GENERAL_INFO_ID)
            .tenderQueryId(UPDATED_TENDER_QUERY_ID)
            .tenderQueryResponseText(UPDATED_TENDER_QUERY_RESPONSE_TEXT);
        return tenderScheduleTelephone;
    }

    @BeforeEach
    public void initTest() {
        tenderScheduleTelephone = createEntity(em);
    }

    @Test
    @Transactional
    void createTenderScheduleTelephone() throws Exception {
        int databaseSizeBeforeCreate = tenderScheduleTelephoneRepository.findAll().size();
        // Create the TenderScheduleTelephone
        TenderScheduleTelephoneDTO tenderScheduleTelephoneDTO = tenderScheduleTelephoneMapper.toDto(tenderScheduleTelephone);
        restTenderScheduleTelephoneMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleTelephoneDTO))
            )
            .andExpect(status().isCreated());

        // Validate the TenderScheduleTelephone in the database
        List<TenderScheduleTelephone> tenderScheduleTelephoneList = tenderScheduleTelephoneRepository.findAll();
        assertThat(tenderScheduleTelephoneList).hasSize(databaseSizeBeforeCreate + 1);
        TenderScheduleTelephone testTenderScheduleTelephone = tenderScheduleTelephoneList.get(tenderScheduleTelephoneList.size() - 1);
        assertThat(testTenderScheduleTelephone.getNitId()).isEqualTo(DEFAULT_NIT_ID);
        assertThat(testTenderScheduleTelephone.getStaffGeneralInfoId()).isEqualTo(DEFAULT_STAFF_GENERAL_INFO_ID);
        assertThat(testTenderScheduleTelephone.getTenderQueryId()).isEqualTo(DEFAULT_TENDER_QUERY_ID);
        assertThat(testTenderScheduleTelephone.getTenderQueryResponseText()).isEqualTo(DEFAULT_TENDER_QUERY_RESPONSE_TEXT);
    }

    @Test
    @Transactional
    void createTenderScheduleTelephoneWithExistingId() throws Exception {
        // Create the TenderScheduleTelephone with an existing ID
        tenderScheduleTelephone.setId(1L);
        TenderScheduleTelephoneDTO tenderScheduleTelephoneDTO = tenderScheduleTelephoneMapper.toDto(tenderScheduleTelephone);

        int databaseSizeBeforeCreate = tenderScheduleTelephoneRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTenderScheduleTelephoneMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleTelephoneDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderScheduleTelephone in the database
        List<TenderScheduleTelephone> tenderScheduleTelephoneList = tenderScheduleTelephoneRepository.findAll();
        assertThat(tenderScheduleTelephoneList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNitIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderScheduleTelephoneRepository.findAll().size();
        // set the field null
        tenderScheduleTelephone.setNitId(null);

        // Create the TenderScheduleTelephone, which fails.
        TenderScheduleTelephoneDTO tenderScheduleTelephoneDTO = tenderScheduleTelephoneMapper.toDto(tenderScheduleTelephone);

        restTenderScheduleTelephoneMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleTelephoneDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderScheduleTelephone> tenderScheduleTelephoneList = tenderScheduleTelephoneRepository.findAll();
        assertThat(tenderScheduleTelephoneList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkStaffGeneralInfoIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderScheduleTelephoneRepository.findAll().size();
        // set the field null
        tenderScheduleTelephone.setStaffGeneralInfoId(null);

        // Create the TenderScheduleTelephone, which fails.
        TenderScheduleTelephoneDTO tenderScheduleTelephoneDTO = tenderScheduleTelephoneMapper.toDto(tenderScheduleTelephone);

        restTenderScheduleTelephoneMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleTelephoneDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderScheduleTelephone> tenderScheduleTelephoneList = tenderScheduleTelephoneRepository.findAll();
        assertThat(tenderScheduleTelephoneList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTenderQueryIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderScheduleTelephoneRepository.findAll().size();
        // set the field null
        tenderScheduleTelephone.setTenderQueryId(null);

        // Create the TenderScheduleTelephone, which fails.
        TenderScheduleTelephoneDTO tenderScheduleTelephoneDTO = tenderScheduleTelephoneMapper.toDto(tenderScheduleTelephone);

        restTenderScheduleTelephoneMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleTelephoneDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderScheduleTelephone> tenderScheduleTelephoneList = tenderScheduleTelephoneRepository.findAll();
        assertThat(tenderScheduleTelephoneList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTenderQueryResponseTextIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderScheduleTelephoneRepository.findAll().size();
        // set the field null
        tenderScheduleTelephone.setTenderQueryResponseText(null);

        // Create the TenderScheduleTelephone, which fails.
        TenderScheduleTelephoneDTO tenderScheduleTelephoneDTO = tenderScheduleTelephoneMapper.toDto(tenderScheduleTelephone);

        restTenderScheduleTelephoneMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleTelephoneDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderScheduleTelephone> tenderScheduleTelephoneList = tenderScheduleTelephoneRepository.findAll();
        assertThat(tenderScheduleTelephoneList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTenderScheduleTelephones() throws Exception {
        // Initialize the database
        tenderScheduleTelephoneRepository.saveAndFlush(tenderScheduleTelephone);

        // Get all the tenderScheduleTelephoneList
        restTenderScheduleTelephoneMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tenderScheduleTelephone.getId().intValue())))
            .andExpect(jsonPath("$.[*].nitId").value(hasItem(DEFAULT_NIT_ID.intValue())))
            .andExpect(jsonPath("$.[*].staffGeneralInfoId").value(hasItem(DEFAULT_STAFF_GENERAL_INFO_ID.intValue())))
            .andExpect(jsonPath("$.[*].tenderQueryId").value(hasItem(DEFAULT_TENDER_QUERY_ID.intValue())))
            .andExpect(jsonPath("$.[*].tenderQueryResponseText").value(hasItem(DEFAULT_TENDER_QUERY_RESPONSE_TEXT)));
    }

    @Test
    @Transactional
    void getTenderScheduleTelephone() throws Exception {
        // Initialize the database
        tenderScheduleTelephoneRepository.saveAndFlush(tenderScheduleTelephone);

        // Get the tenderScheduleTelephone
        restTenderScheduleTelephoneMockMvc
            .perform(get(ENTITY_API_URL_ID, tenderScheduleTelephone.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tenderScheduleTelephone.getId().intValue()))
            .andExpect(jsonPath("$.nitId").value(DEFAULT_NIT_ID.intValue()))
            .andExpect(jsonPath("$.staffGeneralInfoId").value(DEFAULT_STAFF_GENERAL_INFO_ID.intValue()))
            .andExpect(jsonPath("$.tenderQueryId").value(DEFAULT_TENDER_QUERY_ID.intValue()))
            .andExpect(jsonPath("$.tenderQueryResponseText").value(DEFAULT_TENDER_QUERY_RESPONSE_TEXT));
    }

    @Test
    @Transactional
    void getNonExistingTenderScheduleTelephone() throws Exception {
        // Get the tenderScheduleTelephone
        restTenderScheduleTelephoneMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTenderScheduleTelephone() throws Exception {
        // Initialize the database
        tenderScheduleTelephoneRepository.saveAndFlush(tenderScheduleTelephone);

        int databaseSizeBeforeUpdate = tenderScheduleTelephoneRepository.findAll().size();

        // Update the tenderScheduleTelephone
        TenderScheduleTelephone updatedTenderScheduleTelephone = tenderScheduleTelephoneRepository
            .findById(tenderScheduleTelephone.getId())
            .get();
        // Disconnect from session so that the updates on updatedTenderScheduleTelephone are not directly saved in db
        em.detach(updatedTenderScheduleTelephone);
        updatedTenderScheduleTelephone
            .nitId(UPDATED_NIT_ID)
            .staffGeneralInfoId(UPDATED_STAFF_GENERAL_INFO_ID)
            .tenderQueryId(UPDATED_TENDER_QUERY_ID)
            .tenderQueryResponseText(UPDATED_TENDER_QUERY_RESPONSE_TEXT);
        TenderScheduleTelephoneDTO tenderScheduleTelephoneDTO = tenderScheduleTelephoneMapper.toDto(updatedTenderScheduleTelephone);

        restTenderScheduleTelephoneMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tenderScheduleTelephoneDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleTelephoneDTO))
            )
            .andExpect(status().isOk());

        // Validate the TenderScheduleTelephone in the database
        List<TenderScheduleTelephone> tenderScheduleTelephoneList = tenderScheduleTelephoneRepository.findAll();
        assertThat(tenderScheduleTelephoneList).hasSize(databaseSizeBeforeUpdate);
        TenderScheduleTelephone testTenderScheduleTelephone = tenderScheduleTelephoneList.get(tenderScheduleTelephoneList.size() - 1);
        assertThat(testTenderScheduleTelephone.getNitId()).isEqualTo(UPDATED_NIT_ID);
        assertThat(testTenderScheduleTelephone.getStaffGeneralInfoId()).isEqualTo(UPDATED_STAFF_GENERAL_INFO_ID);
        assertThat(testTenderScheduleTelephone.getTenderQueryId()).isEqualTo(UPDATED_TENDER_QUERY_ID);
        assertThat(testTenderScheduleTelephone.getTenderQueryResponseText()).isEqualTo(UPDATED_TENDER_QUERY_RESPONSE_TEXT);
    }

    @Test
    @Transactional
    void putNonExistingTenderScheduleTelephone() throws Exception {
        int databaseSizeBeforeUpdate = tenderScheduleTelephoneRepository.findAll().size();
        tenderScheduleTelephone.setId(count.incrementAndGet());

        // Create the TenderScheduleTelephone
        TenderScheduleTelephoneDTO tenderScheduleTelephoneDTO = tenderScheduleTelephoneMapper.toDto(tenderScheduleTelephone);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTenderScheduleTelephoneMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tenderScheduleTelephoneDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleTelephoneDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderScheduleTelephone in the database
        List<TenderScheduleTelephone> tenderScheduleTelephoneList = tenderScheduleTelephoneRepository.findAll();
        assertThat(tenderScheduleTelephoneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTenderScheduleTelephone() throws Exception {
        int databaseSizeBeforeUpdate = tenderScheduleTelephoneRepository.findAll().size();
        tenderScheduleTelephone.setId(count.incrementAndGet());

        // Create the TenderScheduleTelephone
        TenderScheduleTelephoneDTO tenderScheduleTelephoneDTO = tenderScheduleTelephoneMapper.toDto(tenderScheduleTelephone);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderScheduleTelephoneMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleTelephoneDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderScheduleTelephone in the database
        List<TenderScheduleTelephone> tenderScheduleTelephoneList = tenderScheduleTelephoneRepository.findAll();
        assertThat(tenderScheduleTelephoneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTenderScheduleTelephone() throws Exception {
        int databaseSizeBeforeUpdate = tenderScheduleTelephoneRepository.findAll().size();
        tenderScheduleTelephone.setId(count.incrementAndGet());

        // Create the TenderScheduleTelephone
        TenderScheduleTelephoneDTO tenderScheduleTelephoneDTO = tenderScheduleTelephoneMapper.toDto(tenderScheduleTelephone);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderScheduleTelephoneMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleTelephoneDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TenderScheduleTelephone in the database
        List<TenderScheduleTelephone> tenderScheduleTelephoneList = tenderScheduleTelephoneRepository.findAll();
        assertThat(tenderScheduleTelephoneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTenderScheduleTelephoneWithPatch() throws Exception {
        // Initialize the database
        tenderScheduleTelephoneRepository.saveAndFlush(tenderScheduleTelephone);

        int databaseSizeBeforeUpdate = tenderScheduleTelephoneRepository.findAll().size();

        // Update the tenderScheduleTelephone using partial update
        TenderScheduleTelephone partialUpdatedTenderScheduleTelephone = new TenderScheduleTelephone();
        partialUpdatedTenderScheduleTelephone.setId(tenderScheduleTelephone.getId());

        partialUpdatedTenderScheduleTelephone.tenderQueryResponseText(UPDATED_TENDER_QUERY_RESPONSE_TEXT);

        restTenderScheduleTelephoneMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTenderScheduleTelephone.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTenderScheduleTelephone))
            )
            .andExpect(status().isOk());

        // Validate the TenderScheduleTelephone in the database
        List<TenderScheduleTelephone> tenderScheduleTelephoneList = tenderScheduleTelephoneRepository.findAll();
        assertThat(tenderScheduleTelephoneList).hasSize(databaseSizeBeforeUpdate);
        TenderScheduleTelephone testTenderScheduleTelephone = tenderScheduleTelephoneList.get(tenderScheduleTelephoneList.size() - 1);
        assertThat(testTenderScheduleTelephone.getNitId()).isEqualTo(DEFAULT_NIT_ID);
        assertThat(testTenderScheduleTelephone.getStaffGeneralInfoId()).isEqualTo(DEFAULT_STAFF_GENERAL_INFO_ID);
        assertThat(testTenderScheduleTelephone.getTenderQueryId()).isEqualTo(DEFAULT_TENDER_QUERY_ID);
        assertThat(testTenderScheduleTelephone.getTenderQueryResponseText()).isEqualTo(UPDATED_TENDER_QUERY_RESPONSE_TEXT);
    }

    @Test
    @Transactional
    void fullUpdateTenderScheduleTelephoneWithPatch() throws Exception {
        // Initialize the database
        tenderScheduleTelephoneRepository.saveAndFlush(tenderScheduleTelephone);

        int databaseSizeBeforeUpdate = tenderScheduleTelephoneRepository.findAll().size();

        // Update the tenderScheduleTelephone using partial update
        TenderScheduleTelephone partialUpdatedTenderScheduleTelephone = new TenderScheduleTelephone();
        partialUpdatedTenderScheduleTelephone.setId(tenderScheduleTelephone.getId());

        partialUpdatedTenderScheduleTelephone
            .nitId(UPDATED_NIT_ID)
            .staffGeneralInfoId(UPDATED_STAFF_GENERAL_INFO_ID)
            .tenderQueryId(UPDATED_TENDER_QUERY_ID)
            .tenderQueryResponseText(UPDATED_TENDER_QUERY_RESPONSE_TEXT);

        restTenderScheduleTelephoneMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTenderScheduleTelephone.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTenderScheduleTelephone))
            )
            .andExpect(status().isOk());

        // Validate the TenderScheduleTelephone in the database
        List<TenderScheduleTelephone> tenderScheduleTelephoneList = tenderScheduleTelephoneRepository.findAll();
        assertThat(tenderScheduleTelephoneList).hasSize(databaseSizeBeforeUpdate);
        TenderScheduleTelephone testTenderScheduleTelephone = tenderScheduleTelephoneList.get(tenderScheduleTelephoneList.size() - 1);
        assertThat(testTenderScheduleTelephone.getNitId()).isEqualTo(UPDATED_NIT_ID);
        assertThat(testTenderScheduleTelephone.getStaffGeneralInfoId()).isEqualTo(UPDATED_STAFF_GENERAL_INFO_ID);
        assertThat(testTenderScheduleTelephone.getTenderQueryId()).isEqualTo(UPDATED_TENDER_QUERY_ID);
        assertThat(testTenderScheduleTelephone.getTenderQueryResponseText()).isEqualTo(UPDATED_TENDER_QUERY_RESPONSE_TEXT);
    }

    @Test
    @Transactional
    void patchNonExistingTenderScheduleTelephone() throws Exception {
        int databaseSizeBeforeUpdate = tenderScheduleTelephoneRepository.findAll().size();
        tenderScheduleTelephone.setId(count.incrementAndGet());

        // Create the TenderScheduleTelephone
        TenderScheduleTelephoneDTO tenderScheduleTelephoneDTO = tenderScheduleTelephoneMapper.toDto(tenderScheduleTelephone);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTenderScheduleTelephoneMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tenderScheduleTelephoneDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleTelephoneDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderScheduleTelephone in the database
        List<TenderScheduleTelephone> tenderScheduleTelephoneList = tenderScheduleTelephoneRepository.findAll();
        assertThat(tenderScheduleTelephoneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTenderScheduleTelephone() throws Exception {
        int databaseSizeBeforeUpdate = tenderScheduleTelephoneRepository.findAll().size();
        tenderScheduleTelephone.setId(count.incrementAndGet());

        // Create the TenderScheduleTelephone
        TenderScheduleTelephoneDTO tenderScheduleTelephoneDTO = tenderScheduleTelephoneMapper.toDto(tenderScheduleTelephone);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderScheduleTelephoneMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleTelephoneDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderScheduleTelephone in the database
        List<TenderScheduleTelephone> tenderScheduleTelephoneList = tenderScheduleTelephoneRepository.findAll();
        assertThat(tenderScheduleTelephoneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTenderScheduleTelephone() throws Exception {
        int databaseSizeBeforeUpdate = tenderScheduleTelephoneRepository.findAll().size();
        tenderScheduleTelephone.setId(count.incrementAndGet());

        // Create the TenderScheduleTelephone
        TenderScheduleTelephoneDTO tenderScheduleTelephoneDTO = tenderScheduleTelephoneMapper.toDto(tenderScheduleTelephone);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderScheduleTelephoneMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleTelephoneDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TenderScheduleTelephone in the database
        List<TenderScheduleTelephone> tenderScheduleTelephoneList = tenderScheduleTelephoneRepository.findAll();
        assertThat(tenderScheduleTelephoneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTenderScheduleTelephone() throws Exception {
        // Initialize the database
        tenderScheduleTelephoneRepository.saveAndFlush(tenderScheduleTelephone);

        int databaseSizeBeforeDelete = tenderScheduleTelephoneRepository.findAll().size();

        // Delete the tenderScheduleTelephone
        restTenderScheduleTelephoneMockMvc
            .perform(delete(ENTITY_API_URL_ID, tenderScheduleTelephone.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TenderScheduleTelephone> tenderScheduleTelephoneList = tenderScheduleTelephoneRepository.findAll();
        assertThat(tenderScheduleTelephoneList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
