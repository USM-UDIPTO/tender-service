package com.dxc.eproc.tender.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dxc.eproc.tender.IntegrationTest;
import com.dxc.eproc.tender.domain.TenderQueryResponse;
import com.dxc.eproc.tender.repository.TenderQueryResponseRepository;
import com.dxc.eproc.tender.service.dto.TenderQueryResponseDTO;
import com.dxc.eproc.tender.service.mapper.TenderQueryResponseMapper;
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
 * Integration tests for the {@link TenderQueryResponseResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TenderQueryResponseResourceIT {

    private static final Long DEFAULT_TENDER_QUERY_ID = 1L;
    private static final Long UPDATED_TENDER_QUERY_ID = 2L;

    private static final Long DEFAULT_STAFF_GENERAL_INFO_ID = 1L;
    private static final Long UPDATED_STAFF_GENERAL_INFO_ID = 2L;

    private static final String DEFAULT_TENDER_QUERY_RESPONSE_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_TENDER_QUERY_RESPONSE_TEXT = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/tender-query-responses";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TenderQueryResponseRepository tenderQueryResponseRepository;

    @Autowired
    private TenderQueryResponseMapper tenderQueryResponseMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTenderQueryResponseMockMvc;

    private TenderQueryResponse tenderQueryResponse;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TenderQueryResponse createEntity(EntityManager em) {
        TenderQueryResponse tenderQueryResponse = new TenderQueryResponse()
            .tenderQueryId(DEFAULT_TENDER_QUERY_ID)
            .staffGeneralInfoId(DEFAULT_STAFF_GENERAL_INFO_ID)
            .tenderQueryResponseText(DEFAULT_TENDER_QUERY_RESPONSE_TEXT);
        return tenderQueryResponse;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TenderQueryResponse createUpdatedEntity(EntityManager em) {
        TenderQueryResponse tenderQueryResponse = new TenderQueryResponse()
            .tenderQueryId(UPDATED_TENDER_QUERY_ID)
            .staffGeneralInfoId(UPDATED_STAFF_GENERAL_INFO_ID)
            .tenderQueryResponseText(UPDATED_TENDER_QUERY_RESPONSE_TEXT);
        return tenderQueryResponse;
    }

    @BeforeEach
    public void initTest() {
        tenderQueryResponse = createEntity(em);
    }

    @Test
    @Transactional
    void createTenderQueryResponse() throws Exception {
        int databaseSizeBeforeCreate = tenderQueryResponseRepository.findAll().size();
        // Create the TenderQueryResponse
        TenderQueryResponseDTO tenderQueryResponseDTO = tenderQueryResponseMapper.toDto(tenderQueryResponse);
        restTenderQueryResponseMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderQueryResponseDTO))
            )
            .andExpect(status().isCreated());

        // Validate the TenderQueryResponse in the database
        List<TenderQueryResponse> tenderQueryResponseList = tenderQueryResponseRepository.findAll();
        assertThat(tenderQueryResponseList).hasSize(databaseSizeBeforeCreate + 1);
        TenderQueryResponse testTenderQueryResponse = tenderQueryResponseList.get(tenderQueryResponseList.size() - 1);
        assertThat(testTenderQueryResponse.getTenderQueryId()).isEqualTo(DEFAULT_TENDER_QUERY_ID);
        assertThat(testTenderQueryResponse.getStaffGeneralInfoId()).isEqualTo(DEFAULT_STAFF_GENERAL_INFO_ID);
        assertThat(testTenderQueryResponse.getTenderQueryResponseText()).isEqualTo(DEFAULT_TENDER_QUERY_RESPONSE_TEXT);
    }

    @Test
    @Transactional
    void createTenderQueryResponseWithExistingId() throws Exception {
        // Create the TenderQueryResponse with an existing ID
        tenderQueryResponse.setId(1L);
        TenderQueryResponseDTO tenderQueryResponseDTO = tenderQueryResponseMapper.toDto(tenderQueryResponse);

        int databaseSizeBeforeCreate = tenderQueryResponseRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTenderQueryResponseMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderQueryResponseDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderQueryResponse in the database
        List<TenderQueryResponse> tenderQueryResponseList = tenderQueryResponseRepository.findAll();
        assertThat(tenderQueryResponseList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkTenderQueryIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderQueryResponseRepository.findAll().size();
        // set the field null
        tenderQueryResponse.setTenderQueryId(null);

        // Create the TenderQueryResponse, which fails.
        TenderQueryResponseDTO tenderQueryResponseDTO = tenderQueryResponseMapper.toDto(tenderQueryResponse);

        restTenderQueryResponseMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderQueryResponseDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderQueryResponse> tenderQueryResponseList = tenderQueryResponseRepository.findAll();
        assertThat(tenderQueryResponseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkStaffGeneralInfoIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderQueryResponseRepository.findAll().size();
        // set the field null
        tenderQueryResponse.setStaffGeneralInfoId(null);

        // Create the TenderQueryResponse, which fails.
        TenderQueryResponseDTO tenderQueryResponseDTO = tenderQueryResponseMapper.toDto(tenderQueryResponse);

        restTenderQueryResponseMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderQueryResponseDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderQueryResponse> tenderQueryResponseList = tenderQueryResponseRepository.findAll();
        assertThat(tenderQueryResponseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTenderQueryResponseTextIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderQueryResponseRepository.findAll().size();
        // set the field null
        tenderQueryResponse.setTenderQueryResponseText(null);

        // Create the TenderQueryResponse, which fails.
        TenderQueryResponseDTO tenderQueryResponseDTO = tenderQueryResponseMapper.toDto(tenderQueryResponse);

        restTenderQueryResponseMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderQueryResponseDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderQueryResponse> tenderQueryResponseList = tenderQueryResponseRepository.findAll();
        assertThat(tenderQueryResponseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTenderQueryResponses() throws Exception {
        // Initialize the database
        tenderQueryResponseRepository.saveAndFlush(tenderQueryResponse);

        // Get all the tenderQueryResponseList
        restTenderQueryResponseMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tenderQueryResponse.getId().intValue())))
            .andExpect(jsonPath("$.[*].tenderQueryId").value(hasItem(DEFAULT_TENDER_QUERY_ID.intValue())))
            .andExpect(jsonPath("$.[*].staffGeneralInfoId").value(hasItem(DEFAULT_STAFF_GENERAL_INFO_ID.intValue())))
            .andExpect(jsonPath("$.[*].tenderQueryResponseText").value(hasItem(DEFAULT_TENDER_QUERY_RESPONSE_TEXT)));
    }

    @Test
    @Transactional
    void getTenderQueryResponse() throws Exception {
        // Initialize the database
        tenderQueryResponseRepository.saveAndFlush(tenderQueryResponse);

        // Get the tenderQueryResponse
        restTenderQueryResponseMockMvc
            .perform(get(ENTITY_API_URL_ID, tenderQueryResponse.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tenderQueryResponse.getId().intValue()))
            .andExpect(jsonPath("$.tenderQueryId").value(DEFAULT_TENDER_QUERY_ID.intValue()))
            .andExpect(jsonPath("$.staffGeneralInfoId").value(DEFAULT_STAFF_GENERAL_INFO_ID.intValue()))
            .andExpect(jsonPath("$.tenderQueryResponseText").value(DEFAULT_TENDER_QUERY_RESPONSE_TEXT));
    }

    @Test
    @Transactional
    void getNonExistingTenderQueryResponse() throws Exception {
        // Get the tenderQueryResponse
        restTenderQueryResponseMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTenderQueryResponse() throws Exception {
        // Initialize the database
        tenderQueryResponseRepository.saveAndFlush(tenderQueryResponse);

        int databaseSizeBeforeUpdate = tenderQueryResponseRepository.findAll().size();

        // Update the tenderQueryResponse
        TenderQueryResponse updatedTenderQueryResponse = tenderQueryResponseRepository.findById(tenderQueryResponse.getId()).get();
        // Disconnect from session so that the updates on updatedTenderQueryResponse are not directly saved in db
        em.detach(updatedTenderQueryResponse);
        updatedTenderQueryResponse
            .tenderQueryId(UPDATED_TENDER_QUERY_ID)
            .staffGeneralInfoId(UPDATED_STAFF_GENERAL_INFO_ID)
            .tenderQueryResponseText(UPDATED_TENDER_QUERY_RESPONSE_TEXT);
        TenderQueryResponseDTO tenderQueryResponseDTO = tenderQueryResponseMapper.toDto(updatedTenderQueryResponse);

        restTenderQueryResponseMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tenderQueryResponseDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderQueryResponseDTO))
            )
            .andExpect(status().isOk());

        // Validate the TenderQueryResponse in the database
        List<TenderQueryResponse> tenderQueryResponseList = tenderQueryResponseRepository.findAll();
        assertThat(tenderQueryResponseList).hasSize(databaseSizeBeforeUpdate);
        TenderQueryResponse testTenderQueryResponse = tenderQueryResponseList.get(tenderQueryResponseList.size() - 1);
        assertThat(testTenderQueryResponse.getTenderQueryId()).isEqualTo(UPDATED_TENDER_QUERY_ID);
        assertThat(testTenderQueryResponse.getStaffGeneralInfoId()).isEqualTo(UPDATED_STAFF_GENERAL_INFO_ID);
        assertThat(testTenderQueryResponse.getTenderQueryResponseText()).isEqualTo(UPDATED_TENDER_QUERY_RESPONSE_TEXT);
    }

    @Test
    @Transactional
    void putNonExistingTenderQueryResponse() throws Exception {
        int databaseSizeBeforeUpdate = tenderQueryResponseRepository.findAll().size();
        tenderQueryResponse.setId(count.incrementAndGet());

        // Create the TenderQueryResponse
        TenderQueryResponseDTO tenderQueryResponseDTO = tenderQueryResponseMapper.toDto(tenderQueryResponse);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTenderQueryResponseMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tenderQueryResponseDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderQueryResponseDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderQueryResponse in the database
        List<TenderQueryResponse> tenderQueryResponseList = tenderQueryResponseRepository.findAll();
        assertThat(tenderQueryResponseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTenderQueryResponse() throws Exception {
        int databaseSizeBeforeUpdate = tenderQueryResponseRepository.findAll().size();
        tenderQueryResponse.setId(count.incrementAndGet());

        // Create the TenderQueryResponse
        TenderQueryResponseDTO tenderQueryResponseDTO = tenderQueryResponseMapper.toDto(tenderQueryResponse);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderQueryResponseMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderQueryResponseDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderQueryResponse in the database
        List<TenderQueryResponse> tenderQueryResponseList = tenderQueryResponseRepository.findAll();
        assertThat(tenderQueryResponseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTenderQueryResponse() throws Exception {
        int databaseSizeBeforeUpdate = tenderQueryResponseRepository.findAll().size();
        tenderQueryResponse.setId(count.incrementAndGet());

        // Create the TenderQueryResponse
        TenderQueryResponseDTO tenderQueryResponseDTO = tenderQueryResponseMapper.toDto(tenderQueryResponse);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderQueryResponseMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderQueryResponseDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TenderQueryResponse in the database
        List<TenderQueryResponse> tenderQueryResponseList = tenderQueryResponseRepository.findAll();
        assertThat(tenderQueryResponseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTenderQueryResponseWithPatch() throws Exception {
        // Initialize the database
        tenderQueryResponseRepository.saveAndFlush(tenderQueryResponse);

        int databaseSizeBeforeUpdate = tenderQueryResponseRepository.findAll().size();

        // Update the tenderQueryResponse using partial update
        TenderQueryResponse partialUpdatedTenderQueryResponse = new TenderQueryResponse();
        partialUpdatedTenderQueryResponse.setId(tenderQueryResponse.getId());

        partialUpdatedTenderQueryResponse.tenderQueryId(UPDATED_TENDER_QUERY_ID);

        restTenderQueryResponseMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTenderQueryResponse.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTenderQueryResponse))
            )
            .andExpect(status().isOk());

        // Validate the TenderQueryResponse in the database
        List<TenderQueryResponse> tenderQueryResponseList = tenderQueryResponseRepository.findAll();
        assertThat(tenderQueryResponseList).hasSize(databaseSizeBeforeUpdate);
        TenderQueryResponse testTenderQueryResponse = tenderQueryResponseList.get(tenderQueryResponseList.size() - 1);
        assertThat(testTenderQueryResponse.getTenderQueryId()).isEqualTo(UPDATED_TENDER_QUERY_ID);
        assertThat(testTenderQueryResponse.getStaffGeneralInfoId()).isEqualTo(DEFAULT_STAFF_GENERAL_INFO_ID);
        assertThat(testTenderQueryResponse.getTenderQueryResponseText()).isEqualTo(DEFAULT_TENDER_QUERY_RESPONSE_TEXT);
    }

    @Test
    @Transactional
    void fullUpdateTenderQueryResponseWithPatch() throws Exception {
        // Initialize the database
        tenderQueryResponseRepository.saveAndFlush(tenderQueryResponse);

        int databaseSizeBeforeUpdate = tenderQueryResponseRepository.findAll().size();

        // Update the tenderQueryResponse using partial update
        TenderQueryResponse partialUpdatedTenderQueryResponse = new TenderQueryResponse();
        partialUpdatedTenderQueryResponse.setId(tenderQueryResponse.getId());

        partialUpdatedTenderQueryResponse
            .tenderQueryId(UPDATED_TENDER_QUERY_ID)
            .staffGeneralInfoId(UPDATED_STAFF_GENERAL_INFO_ID)
            .tenderQueryResponseText(UPDATED_TENDER_QUERY_RESPONSE_TEXT);

        restTenderQueryResponseMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTenderQueryResponse.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTenderQueryResponse))
            )
            .andExpect(status().isOk());

        // Validate the TenderQueryResponse in the database
        List<TenderQueryResponse> tenderQueryResponseList = tenderQueryResponseRepository.findAll();
        assertThat(tenderQueryResponseList).hasSize(databaseSizeBeforeUpdate);
        TenderQueryResponse testTenderQueryResponse = tenderQueryResponseList.get(tenderQueryResponseList.size() - 1);
        assertThat(testTenderQueryResponse.getTenderQueryId()).isEqualTo(UPDATED_TENDER_QUERY_ID);
        assertThat(testTenderQueryResponse.getStaffGeneralInfoId()).isEqualTo(UPDATED_STAFF_GENERAL_INFO_ID);
        assertThat(testTenderQueryResponse.getTenderQueryResponseText()).isEqualTo(UPDATED_TENDER_QUERY_RESPONSE_TEXT);
    }

    @Test
    @Transactional
    void patchNonExistingTenderQueryResponse() throws Exception {
        int databaseSizeBeforeUpdate = tenderQueryResponseRepository.findAll().size();
        tenderQueryResponse.setId(count.incrementAndGet());

        // Create the TenderQueryResponse
        TenderQueryResponseDTO tenderQueryResponseDTO = tenderQueryResponseMapper.toDto(tenderQueryResponse);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTenderQueryResponseMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tenderQueryResponseDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderQueryResponseDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderQueryResponse in the database
        List<TenderQueryResponse> tenderQueryResponseList = tenderQueryResponseRepository.findAll();
        assertThat(tenderQueryResponseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTenderQueryResponse() throws Exception {
        int databaseSizeBeforeUpdate = tenderQueryResponseRepository.findAll().size();
        tenderQueryResponse.setId(count.incrementAndGet());

        // Create the TenderQueryResponse
        TenderQueryResponseDTO tenderQueryResponseDTO = tenderQueryResponseMapper.toDto(tenderQueryResponse);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderQueryResponseMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderQueryResponseDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderQueryResponse in the database
        List<TenderQueryResponse> tenderQueryResponseList = tenderQueryResponseRepository.findAll();
        assertThat(tenderQueryResponseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTenderQueryResponse() throws Exception {
        int databaseSizeBeforeUpdate = tenderQueryResponseRepository.findAll().size();
        tenderQueryResponse.setId(count.incrementAndGet());

        // Create the TenderQueryResponse
        TenderQueryResponseDTO tenderQueryResponseDTO = tenderQueryResponseMapper.toDto(tenderQueryResponse);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderQueryResponseMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderQueryResponseDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TenderQueryResponse in the database
        List<TenderQueryResponse> tenderQueryResponseList = tenderQueryResponseRepository.findAll();
        assertThat(tenderQueryResponseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTenderQueryResponse() throws Exception {
        // Initialize the database
        tenderQueryResponseRepository.saveAndFlush(tenderQueryResponse);

        int databaseSizeBeforeDelete = tenderQueryResponseRepository.findAll().size();

        // Delete the tenderQueryResponse
        restTenderQueryResponseMockMvc
            .perform(delete(ENTITY_API_URL_ID, tenderQueryResponse.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TenderQueryResponse> tenderQueryResponseList = tenderQueryResponseRepository.findAll();
        assertThat(tenderQueryResponseList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
