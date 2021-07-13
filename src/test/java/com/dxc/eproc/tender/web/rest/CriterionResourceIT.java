package com.dxc.eproc.tender.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dxc.eproc.tender.IntegrationTest;
import com.dxc.eproc.tender.domain.Criterion;
import com.dxc.eproc.tender.repository.CriterionRepository;
import com.dxc.eproc.tender.service.dto.CriterionDTO;
import com.dxc.eproc.tender.service.mapper.CriterionMapper;
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
 * Integration tests for the {@link CriterionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CriterionResourceIT {

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_CRITERION_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_CRITERION_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_TENDER_CATEGORY = "AAAAAAAAAA";
    private static final String UPDATED_TENDER_CATEGORY = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ACTIVE_YN = false;
    private static final Boolean UPDATED_ACTIVE_YN = true;

    private static final String ENTITY_API_URL = "/api/criteria";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CriterionRepository criterionRepository;

    @Autowired
    private CriterionMapper criterionMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCriterionMockMvc;

    private Criterion criterion;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Criterion createEntity(EntityManager em) {
        Criterion criterion = new Criterion()
            .type(DEFAULT_TYPE)
            .criterionType(DEFAULT_CRITERION_TYPE)
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .tenderCategory(DEFAULT_TENDER_CATEGORY)
            .activeYn(DEFAULT_ACTIVE_YN);
        return criterion;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Criterion createUpdatedEntity(EntityManager em) {
        Criterion criterion = new Criterion()
            .type(UPDATED_TYPE)
            .criterionType(UPDATED_CRITERION_TYPE)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .tenderCategory(UPDATED_TENDER_CATEGORY)
            .activeYn(UPDATED_ACTIVE_YN);
        return criterion;
    }

    @BeforeEach
    public void initTest() {
        criterion = createEntity(em);
    }

    @Test
    @Transactional
    void createCriterion() throws Exception {
        int databaseSizeBeforeCreate = criterionRepository.findAll().size();
        // Create the Criterion
        CriterionDTO criterionDTO = criterionMapper.toDto(criterion);
        restCriterionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(criterionDTO)))
            .andExpect(status().isCreated());

        // Validate the Criterion in the database
        List<Criterion> criterionList = criterionRepository.findAll();
        assertThat(criterionList).hasSize(databaseSizeBeforeCreate + 1);
        Criterion testCriterion = criterionList.get(criterionList.size() - 1);
        assertThat(testCriterion.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testCriterion.getCriterionType()).isEqualTo(DEFAULT_CRITERION_TYPE);
        assertThat(testCriterion.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCriterion.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testCriterion.getTenderCategory()).isEqualTo(DEFAULT_TENDER_CATEGORY);
        assertThat(testCriterion.getActiveYn()).isEqualTo(DEFAULT_ACTIVE_YN);
    }

    @Test
    @Transactional
    void createCriterionWithExistingId() throws Exception {
        // Create the Criterion with an existing ID
        criterion.setId(1L);
        CriterionDTO criterionDTO = criterionMapper.toDto(criterion);

        int databaseSizeBeforeCreate = criterionRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCriterionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(criterionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Criterion in the database
        List<Criterion> criterionList = criterionRepository.findAll();
        assertThat(criterionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = criterionRepository.findAll().size();
        // set the field null
        criterion.setType(null);

        // Create the Criterion, which fails.
        CriterionDTO criterionDTO = criterionMapper.toDto(criterion);

        restCriterionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(criterionDTO)))
            .andExpect(status().isBadRequest());

        List<Criterion> criterionList = criterionRepository.findAll();
        assertThat(criterionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCriterionTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = criterionRepository.findAll().size();
        // set the field null
        criterion.setCriterionType(null);

        // Create the Criterion, which fails.
        CriterionDTO criterionDTO = criterionMapper.toDto(criterion);

        restCriterionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(criterionDTO)))
            .andExpect(status().isBadRequest());

        List<Criterion> criterionList = criterionRepository.findAll();
        assertThat(criterionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = criterionRepository.findAll().size();
        // set the field null
        criterion.setName(null);

        // Create the Criterion, which fails.
        CriterionDTO criterionDTO = criterionMapper.toDto(criterion);

        restCriterionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(criterionDTO)))
            .andExpect(status().isBadRequest());

        List<Criterion> criterionList = criterionRepository.findAll();
        assertThat(criterionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = criterionRepository.findAll().size();
        // set the field null
        criterion.setDescription(null);

        // Create the Criterion, which fails.
        CriterionDTO criterionDTO = criterionMapper.toDto(criterion);

        restCriterionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(criterionDTO)))
            .andExpect(status().isBadRequest());

        List<Criterion> criterionList = criterionRepository.findAll();
        assertThat(criterionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTenderCategoryIsRequired() throws Exception {
        int databaseSizeBeforeTest = criterionRepository.findAll().size();
        // set the field null
        criterion.setTenderCategory(null);

        // Create the Criterion, which fails.
        CriterionDTO criterionDTO = criterionMapper.toDto(criterion);

        restCriterionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(criterionDTO)))
            .andExpect(status().isBadRequest());

        List<Criterion> criterionList = criterionRepository.findAll();
        assertThat(criterionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkActiveYnIsRequired() throws Exception {
        int databaseSizeBeforeTest = criterionRepository.findAll().size();
        // set the field null
        criterion.setActiveYn(null);

        // Create the Criterion, which fails.
        CriterionDTO criterionDTO = criterionMapper.toDto(criterion);

        restCriterionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(criterionDTO)))
            .andExpect(status().isBadRequest());

        List<Criterion> criterionList = criterionRepository.findAll();
        assertThat(criterionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllCriteria() throws Exception {
        // Initialize the database
        criterionRepository.saveAndFlush(criterion);

        // Get all the criterionList
        restCriterionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(criterion.getId().intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].criterionType").value(hasItem(DEFAULT_CRITERION_TYPE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].tenderCategory").value(hasItem(DEFAULT_TENDER_CATEGORY)))
            .andExpect(jsonPath("$.[*].activeYn").value(hasItem(DEFAULT_ACTIVE_YN.booleanValue())));
    }

    @Test
    @Transactional
    void getCriterion() throws Exception {
        // Initialize the database
        criterionRepository.saveAndFlush(criterion);

        // Get the criterion
        restCriterionMockMvc
            .perform(get(ENTITY_API_URL_ID, criterion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(criterion.getId().intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.criterionType").value(DEFAULT_CRITERION_TYPE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.tenderCategory").value(DEFAULT_TENDER_CATEGORY))
            .andExpect(jsonPath("$.activeYn").value(DEFAULT_ACTIVE_YN.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingCriterion() throws Exception {
        // Get the criterion
        restCriterionMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewCriterion() throws Exception {
        // Initialize the database
        criterionRepository.saveAndFlush(criterion);

        int databaseSizeBeforeUpdate = criterionRepository.findAll().size();

        // Update the criterion
        Criterion updatedCriterion = criterionRepository.findById(criterion.getId()).get();
        // Disconnect from session so that the updates on updatedCriterion are not directly saved in db
        em.detach(updatedCriterion);
        updatedCriterion
            .type(UPDATED_TYPE)
            .criterionType(UPDATED_CRITERION_TYPE)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .tenderCategory(UPDATED_TENDER_CATEGORY)
            .activeYn(UPDATED_ACTIVE_YN);
        CriterionDTO criterionDTO = criterionMapper.toDto(updatedCriterion);

        restCriterionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, criterionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(criterionDTO))
            )
            .andExpect(status().isOk());

        // Validate the Criterion in the database
        List<Criterion> criterionList = criterionRepository.findAll();
        assertThat(criterionList).hasSize(databaseSizeBeforeUpdate);
        Criterion testCriterion = criterionList.get(criterionList.size() - 1);
        assertThat(testCriterion.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testCriterion.getCriterionType()).isEqualTo(UPDATED_CRITERION_TYPE);
        assertThat(testCriterion.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCriterion.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testCriterion.getTenderCategory()).isEqualTo(UPDATED_TENDER_CATEGORY);
        assertThat(testCriterion.getActiveYn()).isEqualTo(UPDATED_ACTIVE_YN);
    }

    @Test
    @Transactional
    void putNonExistingCriterion() throws Exception {
        int databaseSizeBeforeUpdate = criterionRepository.findAll().size();
        criterion.setId(count.incrementAndGet());

        // Create the Criterion
        CriterionDTO criterionDTO = criterionMapper.toDto(criterion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCriterionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, criterionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(criterionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Criterion in the database
        List<Criterion> criterionList = criterionRepository.findAll();
        assertThat(criterionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCriterion() throws Exception {
        int databaseSizeBeforeUpdate = criterionRepository.findAll().size();
        criterion.setId(count.incrementAndGet());

        // Create the Criterion
        CriterionDTO criterionDTO = criterionMapper.toDto(criterion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCriterionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(criterionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Criterion in the database
        List<Criterion> criterionList = criterionRepository.findAll();
        assertThat(criterionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCriterion() throws Exception {
        int databaseSizeBeforeUpdate = criterionRepository.findAll().size();
        criterion.setId(count.incrementAndGet());

        // Create the Criterion
        CriterionDTO criterionDTO = criterionMapper.toDto(criterion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCriterionMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(criterionDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Criterion in the database
        List<Criterion> criterionList = criterionRepository.findAll();
        assertThat(criterionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCriterionWithPatch() throws Exception {
        // Initialize the database
        criterionRepository.saveAndFlush(criterion);

        int databaseSizeBeforeUpdate = criterionRepository.findAll().size();

        // Update the criterion using partial update
        Criterion partialUpdatedCriterion = new Criterion();
        partialUpdatedCriterion.setId(criterion.getId());

        partialUpdatedCriterion.criterionType(UPDATED_CRITERION_TYPE).name(UPDATED_NAME).activeYn(UPDATED_ACTIVE_YN);

        restCriterionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCriterion.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCriterion))
            )
            .andExpect(status().isOk());

        // Validate the Criterion in the database
        List<Criterion> criterionList = criterionRepository.findAll();
        assertThat(criterionList).hasSize(databaseSizeBeforeUpdate);
        Criterion testCriterion = criterionList.get(criterionList.size() - 1);
        assertThat(testCriterion.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testCriterion.getCriterionType()).isEqualTo(UPDATED_CRITERION_TYPE);
        assertThat(testCriterion.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCriterion.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testCriterion.getTenderCategory()).isEqualTo(DEFAULT_TENDER_CATEGORY);
        assertThat(testCriterion.getActiveYn()).isEqualTo(UPDATED_ACTIVE_YN);
    }

    @Test
    @Transactional
    void fullUpdateCriterionWithPatch() throws Exception {
        // Initialize the database
        criterionRepository.saveAndFlush(criterion);

        int databaseSizeBeforeUpdate = criterionRepository.findAll().size();

        // Update the criterion using partial update
        Criterion partialUpdatedCriterion = new Criterion();
        partialUpdatedCriterion.setId(criterion.getId());

        partialUpdatedCriterion
            .type(UPDATED_TYPE)
            .criterionType(UPDATED_CRITERION_TYPE)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .tenderCategory(UPDATED_TENDER_CATEGORY)
            .activeYn(UPDATED_ACTIVE_YN);

        restCriterionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCriterion.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCriterion))
            )
            .andExpect(status().isOk());

        // Validate the Criterion in the database
        List<Criterion> criterionList = criterionRepository.findAll();
        assertThat(criterionList).hasSize(databaseSizeBeforeUpdate);
        Criterion testCriterion = criterionList.get(criterionList.size() - 1);
        assertThat(testCriterion.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testCriterion.getCriterionType()).isEqualTo(UPDATED_CRITERION_TYPE);
        assertThat(testCriterion.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCriterion.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testCriterion.getTenderCategory()).isEqualTo(UPDATED_TENDER_CATEGORY);
        assertThat(testCriterion.getActiveYn()).isEqualTo(UPDATED_ACTIVE_YN);
    }

    @Test
    @Transactional
    void patchNonExistingCriterion() throws Exception {
        int databaseSizeBeforeUpdate = criterionRepository.findAll().size();
        criterion.setId(count.incrementAndGet());

        // Create the Criterion
        CriterionDTO criterionDTO = criterionMapper.toDto(criterion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCriterionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, criterionDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(criterionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Criterion in the database
        List<Criterion> criterionList = criterionRepository.findAll();
        assertThat(criterionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCriterion() throws Exception {
        int databaseSizeBeforeUpdate = criterionRepository.findAll().size();
        criterion.setId(count.incrementAndGet());

        // Create the Criterion
        CriterionDTO criterionDTO = criterionMapper.toDto(criterion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCriterionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(criterionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Criterion in the database
        List<Criterion> criterionList = criterionRepository.findAll();
        assertThat(criterionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCriterion() throws Exception {
        int databaseSizeBeforeUpdate = criterionRepository.findAll().size();
        criterion.setId(count.incrementAndGet());

        // Create the Criterion
        CriterionDTO criterionDTO = criterionMapper.toDto(criterion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCriterionMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(criterionDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Criterion in the database
        List<Criterion> criterionList = criterionRepository.findAll();
        assertThat(criterionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCriterion() throws Exception {
        // Initialize the database
        criterionRepository.saveAndFlush(criterion);

        int databaseSizeBeforeDelete = criterionRepository.findAll().size();

        // Delete the criterion
        restCriterionMockMvc
            .perform(delete(ENTITY_API_URL_ID, criterion.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Criterion> criterionList = criterionRepository.findAll();
        assertThat(criterionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
