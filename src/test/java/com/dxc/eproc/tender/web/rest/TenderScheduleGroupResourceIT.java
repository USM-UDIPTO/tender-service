package com.dxc.eproc.tender.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dxc.eproc.tender.IntegrationTest;
import com.dxc.eproc.tender.domain.TenderScheduleGroup;
import com.dxc.eproc.tender.repository.TenderScheduleGroupRepository;
import com.dxc.eproc.tender.service.dto.TenderScheduleGroupDTO;
import com.dxc.eproc.tender.service.mapper.TenderScheduleGroupMapper;
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
 * Integration tests for the {@link TenderScheduleGroupResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TenderScheduleGroupResourceIT {

    private static final Long DEFAULT_NIT_ID = 1L;
    private static final Long UPDATED_NIT_ID = 2L;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_MANDATE_BID_YN = false;
    private static final Boolean UPDATED_MANDATE_BID_YN = true;

    private static final Boolean DEFAULT_MANDATE_GROUP_YN = false;
    private static final Boolean UPDATED_MANDATE_GROUP_YN = true;

    private static final String ENTITY_API_URL = "/api/tender-schedule-groups";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TenderScheduleGroupRepository tenderScheduleGroupRepository;

    @Autowired
    private TenderScheduleGroupMapper tenderScheduleGroupMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTenderScheduleGroupMockMvc;

    private TenderScheduleGroup tenderScheduleGroup;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TenderScheduleGroup createEntity(EntityManager em) {
        TenderScheduleGroup tenderScheduleGroup = new TenderScheduleGroup()
            .nitId(DEFAULT_NIT_ID)
            .name(DEFAULT_NAME)
            .mandateBidYn(DEFAULT_MANDATE_BID_YN)
            .mandateGroupYn(DEFAULT_MANDATE_GROUP_YN);
        return tenderScheduleGroup;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TenderScheduleGroup createUpdatedEntity(EntityManager em) {
        TenderScheduleGroup tenderScheduleGroup = new TenderScheduleGroup()
            .nitId(UPDATED_NIT_ID)
            .name(UPDATED_NAME)
            .mandateBidYn(UPDATED_MANDATE_BID_YN)
            .mandateGroupYn(UPDATED_MANDATE_GROUP_YN);
        return tenderScheduleGroup;
    }

    @BeforeEach
    public void initTest() {
        tenderScheduleGroup = createEntity(em);
    }

    @Test
    @Transactional
    void createTenderScheduleGroup() throws Exception {
        int databaseSizeBeforeCreate = tenderScheduleGroupRepository.findAll().size();
        // Create the TenderScheduleGroup
        TenderScheduleGroupDTO tenderScheduleGroupDTO = tenderScheduleGroupMapper.toDto(tenderScheduleGroup);
        restTenderScheduleGroupMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleGroupDTO))
            )
            .andExpect(status().isCreated());

        // Validate the TenderScheduleGroup in the database
        List<TenderScheduleGroup> tenderScheduleGroupList = tenderScheduleGroupRepository.findAll();
        assertThat(tenderScheduleGroupList).hasSize(databaseSizeBeforeCreate + 1);
        TenderScheduleGroup testTenderScheduleGroup = tenderScheduleGroupList.get(tenderScheduleGroupList.size() - 1);
        assertThat(testTenderScheduleGroup.getNitId()).isEqualTo(DEFAULT_NIT_ID);
        assertThat(testTenderScheduleGroup.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testTenderScheduleGroup.getMandateBidYn()).isEqualTo(DEFAULT_MANDATE_BID_YN);
        assertThat(testTenderScheduleGroup.getMandateGroupYn()).isEqualTo(DEFAULT_MANDATE_GROUP_YN);
    }

    @Test
    @Transactional
    void createTenderScheduleGroupWithExistingId() throws Exception {
        // Create the TenderScheduleGroup with an existing ID
        tenderScheduleGroup.setId(1L);
        TenderScheduleGroupDTO tenderScheduleGroupDTO = tenderScheduleGroupMapper.toDto(tenderScheduleGroup);

        int databaseSizeBeforeCreate = tenderScheduleGroupRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTenderScheduleGroupMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleGroupDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderScheduleGroup in the database
        List<TenderScheduleGroup> tenderScheduleGroupList = tenderScheduleGroupRepository.findAll();
        assertThat(tenderScheduleGroupList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNitIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderScheduleGroupRepository.findAll().size();
        // set the field null
        tenderScheduleGroup.setNitId(null);

        // Create the TenderScheduleGroup, which fails.
        TenderScheduleGroupDTO tenderScheduleGroupDTO = tenderScheduleGroupMapper.toDto(tenderScheduleGroup);

        restTenderScheduleGroupMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleGroupDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderScheduleGroup> tenderScheduleGroupList = tenderScheduleGroupRepository.findAll();
        assertThat(tenderScheduleGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderScheduleGroupRepository.findAll().size();
        // set the field null
        tenderScheduleGroup.setName(null);

        // Create the TenderScheduleGroup, which fails.
        TenderScheduleGroupDTO tenderScheduleGroupDTO = tenderScheduleGroupMapper.toDto(tenderScheduleGroup);

        restTenderScheduleGroupMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleGroupDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderScheduleGroup> tenderScheduleGroupList = tenderScheduleGroupRepository.findAll();
        assertThat(tenderScheduleGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkMandateBidYnIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderScheduleGroupRepository.findAll().size();
        // set the field null
        tenderScheduleGroup.setMandateBidYn(null);

        // Create the TenderScheduleGroup, which fails.
        TenderScheduleGroupDTO tenderScheduleGroupDTO = tenderScheduleGroupMapper.toDto(tenderScheduleGroup);

        restTenderScheduleGroupMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleGroupDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderScheduleGroup> tenderScheduleGroupList = tenderScheduleGroupRepository.findAll();
        assertThat(tenderScheduleGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkMandateGroupYnIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderScheduleGroupRepository.findAll().size();
        // set the field null
        tenderScheduleGroup.setMandateGroupYn(null);

        // Create the TenderScheduleGroup, which fails.
        TenderScheduleGroupDTO tenderScheduleGroupDTO = tenderScheduleGroupMapper.toDto(tenderScheduleGroup);

        restTenderScheduleGroupMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleGroupDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderScheduleGroup> tenderScheduleGroupList = tenderScheduleGroupRepository.findAll();
        assertThat(tenderScheduleGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTenderScheduleGroups() throws Exception {
        // Initialize the database
        tenderScheduleGroupRepository.saveAndFlush(tenderScheduleGroup);

        // Get all the tenderScheduleGroupList
        restTenderScheduleGroupMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tenderScheduleGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].nitId").value(hasItem(DEFAULT_NIT_ID.intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].mandateBidYn").value(hasItem(DEFAULT_MANDATE_BID_YN.booleanValue())))
            .andExpect(jsonPath("$.[*].mandateGroupYn").value(hasItem(DEFAULT_MANDATE_GROUP_YN.booleanValue())));
    }

    @Test
    @Transactional
    void getTenderScheduleGroup() throws Exception {
        // Initialize the database
        tenderScheduleGroupRepository.saveAndFlush(tenderScheduleGroup);

        // Get the tenderScheduleGroup
        restTenderScheduleGroupMockMvc
            .perform(get(ENTITY_API_URL_ID, tenderScheduleGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tenderScheduleGroup.getId().intValue()))
            .andExpect(jsonPath("$.nitId").value(DEFAULT_NIT_ID.intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.mandateBidYn").value(DEFAULT_MANDATE_BID_YN.booleanValue()))
            .andExpect(jsonPath("$.mandateGroupYn").value(DEFAULT_MANDATE_GROUP_YN.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingTenderScheduleGroup() throws Exception {
        // Get the tenderScheduleGroup
        restTenderScheduleGroupMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTenderScheduleGroup() throws Exception {
        // Initialize the database
        tenderScheduleGroupRepository.saveAndFlush(tenderScheduleGroup);

        int databaseSizeBeforeUpdate = tenderScheduleGroupRepository.findAll().size();

        // Update the tenderScheduleGroup
        TenderScheduleGroup updatedTenderScheduleGroup = tenderScheduleGroupRepository.findById(tenderScheduleGroup.getId()).get();
        // Disconnect from session so that the updates on updatedTenderScheduleGroup are not directly saved in db
        em.detach(updatedTenderScheduleGroup);
        updatedTenderScheduleGroup
            .nitId(UPDATED_NIT_ID)
            .name(UPDATED_NAME)
            .mandateBidYn(UPDATED_MANDATE_BID_YN)
            .mandateGroupYn(UPDATED_MANDATE_GROUP_YN);
        TenderScheduleGroupDTO tenderScheduleGroupDTO = tenderScheduleGroupMapper.toDto(updatedTenderScheduleGroup);

        restTenderScheduleGroupMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tenderScheduleGroupDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleGroupDTO))
            )
            .andExpect(status().isOk());

        // Validate the TenderScheduleGroup in the database
        List<TenderScheduleGroup> tenderScheduleGroupList = tenderScheduleGroupRepository.findAll();
        assertThat(tenderScheduleGroupList).hasSize(databaseSizeBeforeUpdate);
        TenderScheduleGroup testTenderScheduleGroup = tenderScheduleGroupList.get(tenderScheduleGroupList.size() - 1);
        assertThat(testTenderScheduleGroup.getNitId()).isEqualTo(UPDATED_NIT_ID);
        assertThat(testTenderScheduleGroup.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testTenderScheduleGroup.getMandateBidYn()).isEqualTo(UPDATED_MANDATE_BID_YN);
        assertThat(testTenderScheduleGroup.getMandateGroupYn()).isEqualTo(UPDATED_MANDATE_GROUP_YN);
    }

    @Test
    @Transactional
    void putNonExistingTenderScheduleGroup() throws Exception {
        int databaseSizeBeforeUpdate = tenderScheduleGroupRepository.findAll().size();
        tenderScheduleGroup.setId(count.incrementAndGet());

        // Create the TenderScheduleGroup
        TenderScheduleGroupDTO tenderScheduleGroupDTO = tenderScheduleGroupMapper.toDto(tenderScheduleGroup);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTenderScheduleGroupMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tenderScheduleGroupDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleGroupDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderScheduleGroup in the database
        List<TenderScheduleGroup> tenderScheduleGroupList = tenderScheduleGroupRepository.findAll();
        assertThat(tenderScheduleGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTenderScheduleGroup() throws Exception {
        int databaseSizeBeforeUpdate = tenderScheduleGroupRepository.findAll().size();
        tenderScheduleGroup.setId(count.incrementAndGet());

        // Create the TenderScheduleGroup
        TenderScheduleGroupDTO tenderScheduleGroupDTO = tenderScheduleGroupMapper.toDto(tenderScheduleGroup);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderScheduleGroupMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleGroupDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderScheduleGroup in the database
        List<TenderScheduleGroup> tenderScheduleGroupList = tenderScheduleGroupRepository.findAll();
        assertThat(tenderScheduleGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTenderScheduleGroup() throws Exception {
        int databaseSizeBeforeUpdate = tenderScheduleGroupRepository.findAll().size();
        tenderScheduleGroup.setId(count.incrementAndGet());

        // Create the TenderScheduleGroup
        TenderScheduleGroupDTO tenderScheduleGroupDTO = tenderScheduleGroupMapper.toDto(tenderScheduleGroup);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderScheduleGroupMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleGroupDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TenderScheduleGroup in the database
        List<TenderScheduleGroup> tenderScheduleGroupList = tenderScheduleGroupRepository.findAll();
        assertThat(tenderScheduleGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTenderScheduleGroupWithPatch() throws Exception {
        // Initialize the database
        tenderScheduleGroupRepository.saveAndFlush(tenderScheduleGroup);

        int databaseSizeBeforeUpdate = tenderScheduleGroupRepository.findAll().size();

        // Update the tenderScheduleGroup using partial update
        TenderScheduleGroup partialUpdatedTenderScheduleGroup = new TenderScheduleGroup();
        partialUpdatedTenderScheduleGroup.setId(tenderScheduleGroup.getId());

        partialUpdatedTenderScheduleGroup
            .nitId(UPDATED_NIT_ID)
            .name(UPDATED_NAME)
            .mandateBidYn(UPDATED_MANDATE_BID_YN)
            .mandateGroupYn(UPDATED_MANDATE_GROUP_YN);

        restTenderScheduleGroupMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTenderScheduleGroup.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTenderScheduleGroup))
            )
            .andExpect(status().isOk());

        // Validate the TenderScheduleGroup in the database
        List<TenderScheduleGroup> tenderScheduleGroupList = tenderScheduleGroupRepository.findAll();
        assertThat(tenderScheduleGroupList).hasSize(databaseSizeBeforeUpdate);
        TenderScheduleGroup testTenderScheduleGroup = tenderScheduleGroupList.get(tenderScheduleGroupList.size() - 1);
        assertThat(testTenderScheduleGroup.getNitId()).isEqualTo(UPDATED_NIT_ID);
        assertThat(testTenderScheduleGroup.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testTenderScheduleGroup.getMandateBidYn()).isEqualTo(UPDATED_MANDATE_BID_YN);
        assertThat(testTenderScheduleGroup.getMandateGroupYn()).isEqualTo(UPDATED_MANDATE_GROUP_YN);
    }

    @Test
    @Transactional
    void fullUpdateTenderScheduleGroupWithPatch() throws Exception {
        // Initialize the database
        tenderScheduleGroupRepository.saveAndFlush(tenderScheduleGroup);

        int databaseSizeBeforeUpdate = tenderScheduleGroupRepository.findAll().size();

        // Update the tenderScheduleGroup using partial update
        TenderScheduleGroup partialUpdatedTenderScheduleGroup = new TenderScheduleGroup();
        partialUpdatedTenderScheduleGroup.setId(tenderScheduleGroup.getId());

        partialUpdatedTenderScheduleGroup
            .nitId(UPDATED_NIT_ID)
            .name(UPDATED_NAME)
            .mandateBidYn(UPDATED_MANDATE_BID_YN)
            .mandateGroupYn(UPDATED_MANDATE_GROUP_YN);

        restTenderScheduleGroupMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTenderScheduleGroup.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTenderScheduleGroup))
            )
            .andExpect(status().isOk());

        // Validate the TenderScheduleGroup in the database
        List<TenderScheduleGroup> tenderScheduleGroupList = tenderScheduleGroupRepository.findAll();
        assertThat(tenderScheduleGroupList).hasSize(databaseSizeBeforeUpdate);
        TenderScheduleGroup testTenderScheduleGroup = tenderScheduleGroupList.get(tenderScheduleGroupList.size() - 1);
        assertThat(testTenderScheduleGroup.getNitId()).isEqualTo(UPDATED_NIT_ID);
        assertThat(testTenderScheduleGroup.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testTenderScheduleGroup.getMandateBidYn()).isEqualTo(UPDATED_MANDATE_BID_YN);
        assertThat(testTenderScheduleGroup.getMandateGroupYn()).isEqualTo(UPDATED_MANDATE_GROUP_YN);
    }

    @Test
    @Transactional
    void patchNonExistingTenderScheduleGroup() throws Exception {
        int databaseSizeBeforeUpdate = tenderScheduleGroupRepository.findAll().size();
        tenderScheduleGroup.setId(count.incrementAndGet());

        // Create the TenderScheduleGroup
        TenderScheduleGroupDTO tenderScheduleGroupDTO = tenderScheduleGroupMapper.toDto(tenderScheduleGroup);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTenderScheduleGroupMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tenderScheduleGroupDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleGroupDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderScheduleGroup in the database
        List<TenderScheduleGroup> tenderScheduleGroupList = tenderScheduleGroupRepository.findAll();
        assertThat(tenderScheduleGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTenderScheduleGroup() throws Exception {
        int databaseSizeBeforeUpdate = tenderScheduleGroupRepository.findAll().size();
        tenderScheduleGroup.setId(count.incrementAndGet());

        // Create the TenderScheduleGroup
        TenderScheduleGroupDTO tenderScheduleGroupDTO = tenderScheduleGroupMapper.toDto(tenderScheduleGroup);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderScheduleGroupMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleGroupDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderScheduleGroup in the database
        List<TenderScheduleGroup> tenderScheduleGroupList = tenderScheduleGroupRepository.findAll();
        assertThat(tenderScheduleGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTenderScheduleGroup() throws Exception {
        int databaseSizeBeforeUpdate = tenderScheduleGroupRepository.findAll().size();
        tenderScheduleGroup.setId(count.incrementAndGet());

        // Create the TenderScheduleGroup
        TenderScheduleGroupDTO tenderScheduleGroupDTO = tenderScheduleGroupMapper.toDto(tenderScheduleGroup);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderScheduleGroupMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleGroupDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TenderScheduleGroup in the database
        List<TenderScheduleGroup> tenderScheduleGroupList = tenderScheduleGroupRepository.findAll();
        assertThat(tenderScheduleGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTenderScheduleGroup() throws Exception {
        // Initialize the database
        tenderScheduleGroupRepository.saveAndFlush(tenderScheduleGroup);

        int databaseSizeBeforeDelete = tenderScheduleGroupRepository.findAll().size();

        // Delete the tenderScheduleGroup
        restTenderScheduleGroupMockMvc
            .perform(delete(ENTITY_API_URL_ID, tenderScheduleGroup.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TenderScheduleGroup> tenderScheduleGroupList = tenderScheduleGroupRepository.findAll();
        assertThat(tenderScheduleGroupList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
