package com.dxc.eproc.tender.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dxc.eproc.tender.IntegrationTest;
import com.dxc.eproc.tender.domain.TenderAddendum;
import com.dxc.eproc.tender.repository.TenderAddendumRepository;
import com.dxc.eproc.tender.service.dto.TenderAddendumDTO;
import com.dxc.eproc.tender.service.mapper.TenderAddendumMapper;
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
 * Integration tests for the {@link TenderAddendumResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TenderAddendumResourceIT {

    private static final Long DEFAULT_NIT_ID = 1L;
    private static final Long UPDATED_NIT_ID = 2L;

    private static final String DEFAULT_REASON = "AAAAAAAAAA";
    private static final String UPDATED_REASON = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/tender-addenda";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TenderAddendumRepository tenderAddendumRepository;

    @Autowired
    private TenderAddendumMapper tenderAddendumMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTenderAddendumMockMvc;

    private TenderAddendum tenderAddendum;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TenderAddendum createEntity(EntityManager em) {
        TenderAddendum tenderAddendum = new TenderAddendum()
            .nitId(DEFAULT_NIT_ID)
            .reason(DEFAULT_REASON)
            .description(DEFAULT_DESCRIPTION)
            .status(DEFAULT_STATUS);
        return tenderAddendum;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TenderAddendum createUpdatedEntity(EntityManager em) {
        TenderAddendum tenderAddendum = new TenderAddendum()
            .nitId(UPDATED_NIT_ID)
            .reason(UPDATED_REASON)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS);
        return tenderAddendum;
    }

    @BeforeEach
    public void initTest() {
        tenderAddendum = createEntity(em);
    }

    @Test
    @Transactional
    void createTenderAddendum() throws Exception {
        int databaseSizeBeforeCreate = tenderAddendumRepository.findAll().size();
        // Create the TenderAddendum
        TenderAddendumDTO tenderAddendumDTO = tenderAddendumMapper.toDto(tenderAddendum);
        restTenderAddendumMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tenderAddendumDTO))
            )
            .andExpect(status().isCreated());

        // Validate the TenderAddendum in the database
        List<TenderAddendum> tenderAddendumList = tenderAddendumRepository.findAll();
        assertThat(tenderAddendumList).hasSize(databaseSizeBeforeCreate + 1);
        TenderAddendum testTenderAddendum = tenderAddendumList.get(tenderAddendumList.size() - 1);
        assertThat(testTenderAddendum.getNitId()).isEqualTo(DEFAULT_NIT_ID);
        assertThat(testTenderAddendum.getReason()).isEqualTo(DEFAULT_REASON);
        assertThat(testTenderAddendum.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testTenderAddendum.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    void createTenderAddendumWithExistingId() throws Exception {
        // Create the TenderAddendum with an existing ID
        tenderAddendum.setId(1L);
        TenderAddendumDTO tenderAddendumDTO = tenderAddendumMapper.toDto(tenderAddendum);

        int databaseSizeBeforeCreate = tenderAddendumRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTenderAddendumMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tenderAddendumDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderAddendum in the database
        List<TenderAddendum> tenderAddendumList = tenderAddendumRepository.findAll();
        assertThat(tenderAddendumList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNitIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderAddendumRepository.findAll().size();
        // set the field null
        tenderAddendum.setNitId(null);

        // Create the TenderAddendum, which fails.
        TenderAddendumDTO tenderAddendumDTO = tenderAddendumMapper.toDto(tenderAddendum);

        restTenderAddendumMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tenderAddendumDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderAddendum> tenderAddendumList = tenderAddendumRepository.findAll();
        assertThat(tenderAddendumList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkReasonIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderAddendumRepository.findAll().size();
        // set the field null
        tenderAddendum.setReason(null);

        // Create the TenderAddendum, which fails.
        TenderAddendumDTO tenderAddendumDTO = tenderAddendumMapper.toDto(tenderAddendum);

        restTenderAddendumMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tenderAddendumDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderAddendum> tenderAddendumList = tenderAddendumRepository.findAll();
        assertThat(tenderAddendumList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderAddendumRepository.findAll().size();
        // set the field null
        tenderAddendum.setDescription(null);

        // Create the TenderAddendum, which fails.
        TenderAddendumDTO tenderAddendumDTO = tenderAddendumMapper.toDto(tenderAddendum);

        restTenderAddendumMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tenderAddendumDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderAddendum> tenderAddendumList = tenderAddendumRepository.findAll();
        assertThat(tenderAddendumList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderAddendumRepository.findAll().size();
        // set the field null
        tenderAddendum.setStatus(null);

        // Create the TenderAddendum, which fails.
        TenderAddendumDTO tenderAddendumDTO = tenderAddendumMapper.toDto(tenderAddendum);

        restTenderAddendumMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tenderAddendumDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderAddendum> tenderAddendumList = tenderAddendumRepository.findAll();
        assertThat(tenderAddendumList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTenderAddenda() throws Exception {
        // Initialize the database
        tenderAddendumRepository.saveAndFlush(tenderAddendum);

        // Get all the tenderAddendumList
        restTenderAddendumMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tenderAddendum.getId().intValue())))
            .andExpect(jsonPath("$.[*].nitId").value(hasItem(DEFAULT_NIT_ID.intValue())))
            .andExpect(jsonPath("$.[*].reason").value(hasItem(DEFAULT_REASON)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }

    @Test
    @Transactional
    void getTenderAddendum() throws Exception {
        // Initialize the database
        tenderAddendumRepository.saveAndFlush(tenderAddendum);

        // Get the tenderAddendum
        restTenderAddendumMockMvc
            .perform(get(ENTITY_API_URL_ID, tenderAddendum.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tenderAddendum.getId().intValue()))
            .andExpect(jsonPath("$.nitId").value(DEFAULT_NIT_ID.intValue()))
            .andExpect(jsonPath("$.reason").value(DEFAULT_REASON))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    @Transactional
    void getNonExistingTenderAddendum() throws Exception {
        // Get the tenderAddendum
        restTenderAddendumMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTenderAddendum() throws Exception {
        // Initialize the database
        tenderAddendumRepository.saveAndFlush(tenderAddendum);

        int databaseSizeBeforeUpdate = tenderAddendumRepository.findAll().size();

        // Update the tenderAddendum
        TenderAddendum updatedTenderAddendum = tenderAddendumRepository.findById(tenderAddendum.getId()).get();
        // Disconnect from session so that the updates on updatedTenderAddendum are not directly saved in db
        em.detach(updatedTenderAddendum);
        updatedTenderAddendum.nitId(UPDATED_NIT_ID).reason(UPDATED_REASON).description(UPDATED_DESCRIPTION).status(UPDATED_STATUS);
        TenderAddendumDTO tenderAddendumDTO = tenderAddendumMapper.toDto(updatedTenderAddendum);

        restTenderAddendumMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tenderAddendumDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderAddendumDTO))
            )
            .andExpect(status().isOk());

        // Validate the TenderAddendum in the database
        List<TenderAddendum> tenderAddendumList = tenderAddendumRepository.findAll();
        assertThat(tenderAddendumList).hasSize(databaseSizeBeforeUpdate);
        TenderAddendum testTenderAddendum = tenderAddendumList.get(tenderAddendumList.size() - 1);
        assertThat(testTenderAddendum.getNitId()).isEqualTo(UPDATED_NIT_ID);
        assertThat(testTenderAddendum.getReason()).isEqualTo(UPDATED_REASON);
        assertThat(testTenderAddendum.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testTenderAddendum.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    void putNonExistingTenderAddendum() throws Exception {
        int databaseSizeBeforeUpdate = tenderAddendumRepository.findAll().size();
        tenderAddendum.setId(count.incrementAndGet());

        // Create the TenderAddendum
        TenderAddendumDTO tenderAddendumDTO = tenderAddendumMapper.toDto(tenderAddendum);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTenderAddendumMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tenderAddendumDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderAddendumDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderAddendum in the database
        List<TenderAddendum> tenderAddendumList = tenderAddendumRepository.findAll();
        assertThat(tenderAddendumList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTenderAddendum() throws Exception {
        int databaseSizeBeforeUpdate = tenderAddendumRepository.findAll().size();
        tenderAddendum.setId(count.incrementAndGet());

        // Create the TenderAddendum
        TenderAddendumDTO tenderAddendumDTO = tenderAddendumMapper.toDto(tenderAddendum);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderAddendumMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderAddendumDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderAddendum in the database
        List<TenderAddendum> tenderAddendumList = tenderAddendumRepository.findAll();
        assertThat(tenderAddendumList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTenderAddendum() throws Exception {
        int databaseSizeBeforeUpdate = tenderAddendumRepository.findAll().size();
        tenderAddendum.setId(count.incrementAndGet());

        // Create the TenderAddendum
        TenderAddendumDTO tenderAddendumDTO = tenderAddendumMapper.toDto(tenderAddendum);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderAddendumMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tenderAddendumDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TenderAddendum in the database
        List<TenderAddendum> tenderAddendumList = tenderAddendumRepository.findAll();
        assertThat(tenderAddendumList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTenderAddendumWithPatch() throws Exception {
        // Initialize the database
        tenderAddendumRepository.saveAndFlush(tenderAddendum);

        int databaseSizeBeforeUpdate = tenderAddendumRepository.findAll().size();

        // Update the tenderAddendum using partial update
        TenderAddendum partialUpdatedTenderAddendum = new TenderAddendum();
        partialUpdatedTenderAddendum.setId(tenderAddendum.getId());

        restTenderAddendumMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTenderAddendum.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTenderAddendum))
            )
            .andExpect(status().isOk());

        // Validate the TenderAddendum in the database
        List<TenderAddendum> tenderAddendumList = tenderAddendumRepository.findAll();
        assertThat(tenderAddendumList).hasSize(databaseSizeBeforeUpdate);
        TenderAddendum testTenderAddendum = tenderAddendumList.get(tenderAddendumList.size() - 1);
        assertThat(testTenderAddendum.getNitId()).isEqualTo(DEFAULT_NIT_ID);
        assertThat(testTenderAddendum.getReason()).isEqualTo(DEFAULT_REASON);
        assertThat(testTenderAddendum.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testTenderAddendum.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    void fullUpdateTenderAddendumWithPatch() throws Exception {
        // Initialize the database
        tenderAddendumRepository.saveAndFlush(tenderAddendum);

        int databaseSizeBeforeUpdate = tenderAddendumRepository.findAll().size();

        // Update the tenderAddendum using partial update
        TenderAddendum partialUpdatedTenderAddendum = new TenderAddendum();
        partialUpdatedTenderAddendum.setId(tenderAddendum.getId());

        partialUpdatedTenderAddendum.nitId(UPDATED_NIT_ID).reason(UPDATED_REASON).description(UPDATED_DESCRIPTION).status(UPDATED_STATUS);

        restTenderAddendumMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTenderAddendum.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTenderAddendum))
            )
            .andExpect(status().isOk());

        // Validate the TenderAddendum in the database
        List<TenderAddendum> tenderAddendumList = tenderAddendumRepository.findAll();
        assertThat(tenderAddendumList).hasSize(databaseSizeBeforeUpdate);
        TenderAddendum testTenderAddendum = tenderAddendumList.get(tenderAddendumList.size() - 1);
        assertThat(testTenderAddendum.getNitId()).isEqualTo(UPDATED_NIT_ID);
        assertThat(testTenderAddendum.getReason()).isEqualTo(UPDATED_REASON);
        assertThat(testTenderAddendum.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testTenderAddendum.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    void patchNonExistingTenderAddendum() throws Exception {
        int databaseSizeBeforeUpdate = tenderAddendumRepository.findAll().size();
        tenderAddendum.setId(count.incrementAndGet());

        // Create the TenderAddendum
        TenderAddendumDTO tenderAddendumDTO = tenderAddendumMapper.toDto(tenderAddendum);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTenderAddendumMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tenderAddendumDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderAddendumDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderAddendum in the database
        List<TenderAddendum> tenderAddendumList = tenderAddendumRepository.findAll();
        assertThat(tenderAddendumList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTenderAddendum() throws Exception {
        int databaseSizeBeforeUpdate = tenderAddendumRepository.findAll().size();
        tenderAddendum.setId(count.incrementAndGet());

        // Create the TenderAddendum
        TenderAddendumDTO tenderAddendumDTO = tenderAddendumMapper.toDto(tenderAddendum);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderAddendumMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderAddendumDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderAddendum in the database
        List<TenderAddendum> tenderAddendumList = tenderAddendumRepository.findAll();
        assertThat(tenderAddendumList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTenderAddendum() throws Exception {
        int databaseSizeBeforeUpdate = tenderAddendumRepository.findAll().size();
        tenderAddendum.setId(count.incrementAndGet());

        // Create the TenderAddendum
        TenderAddendumDTO tenderAddendumDTO = tenderAddendumMapper.toDto(tenderAddendum);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderAddendumMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderAddendumDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TenderAddendum in the database
        List<TenderAddendum> tenderAddendumList = tenderAddendumRepository.findAll();
        assertThat(tenderAddendumList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTenderAddendum() throws Exception {
        // Initialize the database
        tenderAddendumRepository.saveAndFlush(tenderAddendum);

        int databaseSizeBeforeDelete = tenderAddendumRepository.findAll().size();

        // Delete the tenderAddendum
        restTenderAddendumMockMvc
            .perform(delete(ENTITY_API_URL_ID, tenderAddendum.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TenderAddendum> tenderAddendumList = tenderAddendumRepository.findAll();
        assertThat(tenderAddendumList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
