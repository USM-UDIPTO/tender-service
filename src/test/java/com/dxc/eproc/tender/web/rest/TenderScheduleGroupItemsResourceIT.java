package com.dxc.eproc.tender.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dxc.eproc.tender.IntegrationTest;
import com.dxc.eproc.tender.domain.TenderScheduleGroupItems;
import com.dxc.eproc.tender.repository.TenderScheduleGroupItemsRepository;
import com.dxc.eproc.tender.service.dto.TenderScheduleGroupItemsDTO;
import com.dxc.eproc.tender.service.mapper.TenderScheduleGroupItemsMapper;
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
 * Integration tests for the {@link TenderScheduleGroupItemsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TenderScheduleGroupItemsResourceIT {

    private static final Long DEFAULT_TENDER_GOODS_INDENT_ITEM_ID = 1L;
    private static final Long UPDATED_TENDER_GOODS_INDENT_ITEM_ID = 2L;

    private static final Long DEFAULT_TENDER_SCHEDULE_GROUP_ID = 1L;
    private static final Long UPDATED_TENDER_SCHEDULE_GROUP_ID = 2L;

    private static final String ENTITY_API_URL = "/api/tender-schedule-group-items";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TenderScheduleGroupItemsRepository tenderScheduleGroupItemsRepository;

    @Autowired
    private TenderScheduleGroupItemsMapper tenderScheduleGroupItemsMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTenderScheduleGroupItemsMockMvc;

    private TenderScheduleGroupItems tenderScheduleGroupItems;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TenderScheduleGroupItems createEntity(EntityManager em) {
        TenderScheduleGroupItems tenderScheduleGroupItems = new TenderScheduleGroupItems()
            .tenderGoodsIndentItemId(DEFAULT_TENDER_GOODS_INDENT_ITEM_ID)
            .tenderScheduleGroupId(DEFAULT_TENDER_SCHEDULE_GROUP_ID);
        return tenderScheduleGroupItems;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TenderScheduleGroupItems createUpdatedEntity(EntityManager em) {
        TenderScheduleGroupItems tenderScheduleGroupItems = new TenderScheduleGroupItems()
            .tenderGoodsIndentItemId(UPDATED_TENDER_GOODS_INDENT_ITEM_ID)
            .tenderScheduleGroupId(UPDATED_TENDER_SCHEDULE_GROUP_ID);
        return tenderScheduleGroupItems;
    }

    @BeforeEach
    public void initTest() {
        tenderScheduleGroupItems = createEntity(em);
    }

    @Test
    @Transactional
    void createTenderScheduleGroupItems() throws Exception {
        int databaseSizeBeforeCreate = tenderScheduleGroupItemsRepository.findAll().size();
        // Create the TenderScheduleGroupItems
        TenderScheduleGroupItemsDTO tenderScheduleGroupItemsDTO = tenderScheduleGroupItemsMapper.toDto(tenderScheduleGroupItems);
        restTenderScheduleGroupItemsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleGroupItemsDTO))
            )
            .andExpect(status().isCreated());

        // Validate the TenderScheduleGroupItems in the database
        List<TenderScheduleGroupItems> tenderScheduleGroupItemsList = tenderScheduleGroupItemsRepository.findAll();
        assertThat(tenderScheduleGroupItemsList).hasSize(databaseSizeBeforeCreate + 1);
        TenderScheduleGroupItems testTenderScheduleGroupItems = tenderScheduleGroupItemsList.get(tenderScheduleGroupItemsList.size() - 1);
        assertThat(testTenderScheduleGroupItems.getTenderGoodsIndentItemId()).isEqualTo(DEFAULT_TENDER_GOODS_INDENT_ITEM_ID);
        assertThat(testTenderScheduleGroupItems.getTenderScheduleGroupId()).isEqualTo(DEFAULT_TENDER_SCHEDULE_GROUP_ID);
    }

    @Test
    @Transactional
    void createTenderScheduleGroupItemsWithExistingId() throws Exception {
        // Create the TenderScheduleGroupItems with an existing ID
        tenderScheduleGroupItems.setId(1L);
        TenderScheduleGroupItemsDTO tenderScheduleGroupItemsDTO = tenderScheduleGroupItemsMapper.toDto(tenderScheduleGroupItems);

        int databaseSizeBeforeCreate = tenderScheduleGroupItemsRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTenderScheduleGroupItemsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleGroupItemsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderScheduleGroupItems in the database
        List<TenderScheduleGroupItems> tenderScheduleGroupItemsList = tenderScheduleGroupItemsRepository.findAll();
        assertThat(tenderScheduleGroupItemsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkTenderGoodsIndentItemIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderScheduleGroupItemsRepository.findAll().size();
        // set the field null
        tenderScheduleGroupItems.setTenderGoodsIndentItemId(null);

        // Create the TenderScheduleGroupItems, which fails.
        TenderScheduleGroupItemsDTO tenderScheduleGroupItemsDTO = tenderScheduleGroupItemsMapper.toDto(tenderScheduleGroupItems);

        restTenderScheduleGroupItemsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleGroupItemsDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderScheduleGroupItems> tenderScheduleGroupItemsList = tenderScheduleGroupItemsRepository.findAll();
        assertThat(tenderScheduleGroupItemsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTenderScheduleGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderScheduleGroupItemsRepository.findAll().size();
        // set the field null
        tenderScheduleGroupItems.setTenderScheduleGroupId(null);

        // Create the TenderScheduleGroupItems, which fails.
        TenderScheduleGroupItemsDTO tenderScheduleGroupItemsDTO = tenderScheduleGroupItemsMapper.toDto(tenderScheduleGroupItems);

        restTenderScheduleGroupItemsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleGroupItemsDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderScheduleGroupItems> tenderScheduleGroupItemsList = tenderScheduleGroupItemsRepository.findAll();
        assertThat(tenderScheduleGroupItemsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTenderScheduleGroupItems() throws Exception {
        // Initialize the database
        tenderScheduleGroupItemsRepository.saveAndFlush(tenderScheduleGroupItems);

        // Get all the tenderScheduleGroupItemsList
        restTenderScheduleGroupItemsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tenderScheduleGroupItems.getId().intValue())))
            .andExpect(jsonPath("$.[*].tenderGoodsIndentItemId").value(hasItem(DEFAULT_TENDER_GOODS_INDENT_ITEM_ID.intValue())))
            .andExpect(jsonPath("$.[*].tenderScheduleGroupId").value(hasItem(DEFAULT_TENDER_SCHEDULE_GROUP_ID.intValue())));
    }

    @Test
    @Transactional
    void getTenderScheduleGroupItems() throws Exception {
        // Initialize the database
        tenderScheduleGroupItemsRepository.saveAndFlush(tenderScheduleGroupItems);

        // Get the tenderScheduleGroupItems
        restTenderScheduleGroupItemsMockMvc
            .perform(get(ENTITY_API_URL_ID, tenderScheduleGroupItems.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tenderScheduleGroupItems.getId().intValue()))
            .andExpect(jsonPath("$.tenderGoodsIndentItemId").value(DEFAULT_TENDER_GOODS_INDENT_ITEM_ID.intValue()))
            .andExpect(jsonPath("$.tenderScheduleGroupId").value(DEFAULT_TENDER_SCHEDULE_GROUP_ID.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingTenderScheduleGroupItems() throws Exception {
        // Get the tenderScheduleGroupItems
        restTenderScheduleGroupItemsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTenderScheduleGroupItems() throws Exception {
        // Initialize the database
        tenderScheduleGroupItemsRepository.saveAndFlush(tenderScheduleGroupItems);

        int databaseSizeBeforeUpdate = tenderScheduleGroupItemsRepository.findAll().size();

        // Update the tenderScheduleGroupItems
        TenderScheduleGroupItems updatedTenderScheduleGroupItems = tenderScheduleGroupItemsRepository
            .findById(tenderScheduleGroupItems.getId())
            .get();
        // Disconnect from session so that the updates on updatedTenderScheduleGroupItems are not directly saved in db
        em.detach(updatedTenderScheduleGroupItems);
        updatedTenderScheduleGroupItems
            .tenderGoodsIndentItemId(UPDATED_TENDER_GOODS_INDENT_ITEM_ID)
            .tenderScheduleGroupId(UPDATED_TENDER_SCHEDULE_GROUP_ID);
        TenderScheduleGroupItemsDTO tenderScheduleGroupItemsDTO = tenderScheduleGroupItemsMapper.toDto(updatedTenderScheduleGroupItems);

        restTenderScheduleGroupItemsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tenderScheduleGroupItemsDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleGroupItemsDTO))
            )
            .andExpect(status().isOk());

        // Validate the TenderScheduleGroupItems in the database
        List<TenderScheduleGroupItems> tenderScheduleGroupItemsList = tenderScheduleGroupItemsRepository.findAll();
        assertThat(tenderScheduleGroupItemsList).hasSize(databaseSizeBeforeUpdate);
        TenderScheduleGroupItems testTenderScheduleGroupItems = tenderScheduleGroupItemsList.get(tenderScheduleGroupItemsList.size() - 1);
        assertThat(testTenderScheduleGroupItems.getTenderGoodsIndentItemId()).isEqualTo(UPDATED_TENDER_GOODS_INDENT_ITEM_ID);
        assertThat(testTenderScheduleGroupItems.getTenderScheduleGroupId()).isEqualTo(UPDATED_TENDER_SCHEDULE_GROUP_ID);
    }

    @Test
    @Transactional
    void putNonExistingTenderScheduleGroupItems() throws Exception {
        int databaseSizeBeforeUpdate = tenderScheduleGroupItemsRepository.findAll().size();
        tenderScheduleGroupItems.setId(count.incrementAndGet());

        // Create the TenderScheduleGroupItems
        TenderScheduleGroupItemsDTO tenderScheduleGroupItemsDTO = tenderScheduleGroupItemsMapper.toDto(tenderScheduleGroupItems);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTenderScheduleGroupItemsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tenderScheduleGroupItemsDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleGroupItemsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderScheduleGroupItems in the database
        List<TenderScheduleGroupItems> tenderScheduleGroupItemsList = tenderScheduleGroupItemsRepository.findAll();
        assertThat(tenderScheduleGroupItemsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTenderScheduleGroupItems() throws Exception {
        int databaseSizeBeforeUpdate = tenderScheduleGroupItemsRepository.findAll().size();
        tenderScheduleGroupItems.setId(count.incrementAndGet());

        // Create the TenderScheduleGroupItems
        TenderScheduleGroupItemsDTO tenderScheduleGroupItemsDTO = tenderScheduleGroupItemsMapper.toDto(tenderScheduleGroupItems);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderScheduleGroupItemsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleGroupItemsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderScheduleGroupItems in the database
        List<TenderScheduleGroupItems> tenderScheduleGroupItemsList = tenderScheduleGroupItemsRepository.findAll();
        assertThat(tenderScheduleGroupItemsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTenderScheduleGroupItems() throws Exception {
        int databaseSizeBeforeUpdate = tenderScheduleGroupItemsRepository.findAll().size();
        tenderScheduleGroupItems.setId(count.incrementAndGet());

        // Create the TenderScheduleGroupItems
        TenderScheduleGroupItemsDTO tenderScheduleGroupItemsDTO = tenderScheduleGroupItemsMapper.toDto(tenderScheduleGroupItems);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderScheduleGroupItemsMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleGroupItemsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TenderScheduleGroupItems in the database
        List<TenderScheduleGroupItems> tenderScheduleGroupItemsList = tenderScheduleGroupItemsRepository.findAll();
        assertThat(tenderScheduleGroupItemsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTenderScheduleGroupItemsWithPatch() throws Exception {
        // Initialize the database
        tenderScheduleGroupItemsRepository.saveAndFlush(tenderScheduleGroupItems);

        int databaseSizeBeforeUpdate = tenderScheduleGroupItemsRepository.findAll().size();

        // Update the tenderScheduleGroupItems using partial update
        TenderScheduleGroupItems partialUpdatedTenderScheduleGroupItems = new TenderScheduleGroupItems();
        partialUpdatedTenderScheduleGroupItems.setId(tenderScheduleGroupItems.getId());

        partialUpdatedTenderScheduleGroupItems.tenderGoodsIndentItemId(UPDATED_TENDER_GOODS_INDENT_ITEM_ID);

        restTenderScheduleGroupItemsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTenderScheduleGroupItems.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTenderScheduleGroupItems))
            )
            .andExpect(status().isOk());

        // Validate the TenderScheduleGroupItems in the database
        List<TenderScheduleGroupItems> tenderScheduleGroupItemsList = tenderScheduleGroupItemsRepository.findAll();
        assertThat(tenderScheduleGroupItemsList).hasSize(databaseSizeBeforeUpdate);
        TenderScheduleGroupItems testTenderScheduleGroupItems = tenderScheduleGroupItemsList.get(tenderScheduleGroupItemsList.size() - 1);
        assertThat(testTenderScheduleGroupItems.getTenderGoodsIndentItemId()).isEqualTo(UPDATED_TENDER_GOODS_INDENT_ITEM_ID);
        assertThat(testTenderScheduleGroupItems.getTenderScheduleGroupId()).isEqualTo(DEFAULT_TENDER_SCHEDULE_GROUP_ID);
    }

    @Test
    @Transactional
    void fullUpdateTenderScheduleGroupItemsWithPatch() throws Exception {
        // Initialize the database
        tenderScheduleGroupItemsRepository.saveAndFlush(tenderScheduleGroupItems);

        int databaseSizeBeforeUpdate = tenderScheduleGroupItemsRepository.findAll().size();

        // Update the tenderScheduleGroupItems using partial update
        TenderScheduleGroupItems partialUpdatedTenderScheduleGroupItems = new TenderScheduleGroupItems();
        partialUpdatedTenderScheduleGroupItems.setId(tenderScheduleGroupItems.getId());

        partialUpdatedTenderScheduleGroupItems
            .tenderGoodsIndentItemId(UPDATED_TENDER_GOODS_INDENT_ITEM_ID)
            .tenderScheduleGroupId(UPDATED_TENDER_SCHEDULE_GROUP_ID);

        restTenderScheduleGroupItemsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTenderScheduleGroupItems.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTenderScheduleGroupItems))
            )
            .andExpect(status().isOk());

        // Validate the TenderScheduleGroupItems in the database
        List<TenderScheduleGroupItems> tenderScheduleGroupItemsList = tenderScheduleGroupItemsRepository.findAll();
        assertThat(tenderScheduleGroupItemsList).hasSize(databaseSizeBeforeUpdate);
        TenderScheduleGroupItems testTenderScheduleGroupItems = tenderScheduleGroupItemsList.get(tenderScheduleGroupItemsList.size() - 1);
        assertThat(testTenderScheduleGroupItems.getTenderGoodsIndentItemId()).isEqualTo(UPDATED_TENDER_GOODS_INDENT_ITEM_ID);
        assertThat(testTenderScheduleGroupItems.getTenderScheduleGroupId()).isEqualTo(UPDATED_TENDER_SCHEDULE_GROUP_ID);
    }

    @Test
    @Transactional
    void patchNonExistingTenderScheduleGroupItems() throws Exception {
        int databaseSizeBeforeUpdate = tenderScheduleGroupItemsRepository.findAll().size();
        tenderScheduleGroupItems.setId(count.incrementAndGet());

        // Create the TenderScheduleGroupItems
        TenderScheduleGroupItemsDTO tenderScheduleGroupItemsDTO = tenderScheduleGroupItemsMapper.toDto(tenderScheduleGroupItems);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTenderScheduleGroupItemsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tenderScheduleGroupItemsDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleGroupItemsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderScheduleGroupItems in the database
        List<TenderScheduleGroupItems> tenderScheduleGroupItemsList = tenderScheduleGroupItemsRepository.findAll();
        assertThat(tenderScheduleGroupItemsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTenderScheduleGroupItems() throws Exception {
        int databaseSizeBeforeUpdate = tenderScheduleGroupItemsRepository.findAll().size();
        tenderScheduleGroupItems.setId(count.incrementAndGet());

        // Create the TenderScheduleGroupItems
        TenderScheduleGroupItemsDTO tenderScheduleGroupItemsDTO = tenderScheduleGroupItemsMapper.toDto(tenderScheduleGroupItems);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderScheduleGroupItemsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleGroupItemsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderScheduleGroupItems in the database
        List<TenderScheduleGroupItems> tenderScheduleGroupItemsList = tenderScheduleGroupItemsRepository.findAll();
        assertThat(tenderScheduleGroupItemsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTenderScheduleGroupItems() throws Exception {
        int databaseSizeBeforeUpdate = tenderScheduleGroupItemsRepository.findAll().size();
        tenderScheduleGroupItems.setId(count.incrementAndGet());

        // Create the TenderScheduleGroupItems
        TenderScheduleGroupItemsDTO tenderScheduleGroupItemsDTO = tenderScheduleGroupItemsMapper.toDto(tenderScheduleGroupItems);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderScheduleGroupItemsMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleGroupItemsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TenderScheduleGroupItems in the database
        List<TenderScheduleGroupItems> tenderScheduleGroupItemsList = tenderScheduleGroupItemsRepository.findAll();
        assertThat(tenderScheduleGroupItemsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTenderScheduleGroupItems() throws Exception {
        // Initialize the database
        tenderScheduleGroupItemsRepository.saveAndFlush(tenderScheduleGroupItems);

        int databaseSizeBeforeDelete = tenderScheduleGroupItemsRepository.findAll().size();

        // Delete the tenderScheduleGroupItems
        restTenderScheduleGroupItemsMockMvc
            .perform(delete(ENTITY_API_URL_ID, tenderScheduleGroupItems.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TenderScheduleGroupItems> tenderScheduleGroupItemsList = tenderScheduleGroupItemsRepository.findAll();
        assertThat(tenderScheduleGroupItemsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
