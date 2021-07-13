package com.dxc.eproc.tender.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dxc.eproc.tender.IntegrationTest;
import com.dxc.eproc.tender.domain.SeriesTable;
import com.dxc.eproc.tender.repository.SeriesTableRepository;
import com.dxc.eproc.tender.service.dto.SeriesTableDTO;
import com.dxc.eproc.tender.service.mapper.SeriesTableMapper;
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
 * Integration tests for the {@link SeriesTableResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SeriesTableResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PREFIX = "AAAAAAAAAA";
    private static final String UPDATED_PREFIX = "BBBBBBBBBB";

    private static final String DEFAULT_NEXT_SERIES = "AAAAAAAAAA";
    private static final String UPDATED_NEXT_SERIES = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/series-tables";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SeriesTableRepository seriesTableRepository;

    @Autowired
    private SeriesTableMapper seriesTableMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSeriesTableMockMvc;

    private SeriesTable seriesTable;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SeriesTable createEntity(EntityManager em) {
        SeriesTable seriesTable = new SeriesTable().name(DEFAULT_NAME).prefix(DEFAULT_PREFIX).nextSeries(DEFAULT_NEXT_SERIES);
        return seriesTable;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SeriesTable createUpdatedEntity(EntityManager em) {
        SeriesTable seriesTable = new SeriesTable().name(UPDATED_NAME).prefix(UPDATED_PREFIX).nextSeries(UPDATED_NEXT_SERIES);
        return seriesTable;
    }

    @BeforeEach
    public void initTest() {
        seriesTable = createEntity(em);
    }

    @Test
    @Transactional
    void createSeriesTable() throws Exception {
        int databaseSizeBeforeCreate = seriesTableRepository.findAll().size();
        // Create the SeriesTable
        SeriesTableDTO seriesTableDTO = seriesTableMapper.toDto(seriesTable);
        restSeriesTableMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(seriesTableDTO))
            )
            .andExpect(status().isCreated());

        // Validate the SeriesTable in the database
        List<SeriesTable> seriesTableList = seriesTableRepository.findAll();
        assertThat(seriesTableList).hasSize(databaseSizeBeforeCreate + 1);
        SeriesTable testSeriesTable = seriesTableList.get(seriesTableList.size() - 1);
        assertThat(testSeriesTable.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSeriesTable.getPrefix()).isEqualTo(DEFAULT_PREFIX);
        assertThat(testSeriesTable.getNextSeries()).isEqualTo(DEFAULT_NEXT_SERIES);
    }

    @Test
    @Transactional
    void createSeriesTableWithExistingId() throws Exception {
        // Create the SeriesTable with an existing ID
        seriesTable.setId(1L);
        SeriesTableDTO seriesTableDTO = seriesTableMapper.toDto(seriesTable);

        int databaseSizeBeforeCreate = seriesTableRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSeriesTableMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(seriesTableDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SeriesTable in the database
        List<SeriesTable> seriesTableList = seriesTableRepository.findAll();
        assertThat(seriesTableList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = seriesTableRepository.findAll().size();
        // set the field null
        seriesTable.setName(null);

        // Create the SeriesTable, which fails.
        SeriesTableDTO seriesTableDTO = seriesTableMapper.toDto(seriesTable);

        restSeriesTableMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(seriesTableDTO))
            )
            .andExpect(status().isBadRequest());

        List<SeriesTable> seriesTableList = seriesTableRepository.findAll();
        assertThat(seriesTableList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPrefixIsRequired() throws Exception {
        int databaseSizeBeforeTest = seriesTableRepository.findAll().size();
        // set the field null
        seriesTable.setPrefix(null);

        // Create the SeriesTable, which fails.
        SeriesTableDTO seriesTableDTO = seriesTableMapper.toDto(seriesTable);

        restSeriesTableMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(seriesTableDTO))
            )
            .andExpect(status().isBadRequest());

        List<SeriesTable> seriesTableList = seriesTableRepository.findAll();
        assertThat(seriesTableList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNextSeriesIsRequired() throws Exception {
        int databaseSizeBeforeTest = seriesTableRepository.findAll().size();
        // set the field null
        seriesTable.setNextSeries(null);

        // Create the SeriesTable, which fails.
        SeriesTableDTO seriesTableDTO = seriesTableMapper.toDto(seriesTable);

        restSeriesTableMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(seriesTableDTO))
            )
            .andExpect(status().isBadRequest());

        List<SeriesTable> seriesTableList = seriesTableRepository.findAll();
        assertThat(seriesTableList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllSeriesTables() throws Exception {
        // Initialize the database
        seriesTableRepository.saveAndFlush(seriesTable);

        // Get all the seriesTableList
        restSeriesTableMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(seriesTable.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].prefix").value(hasItem(DEFAULT_PREFIX)))
            .andExpect(jsonPath("$.[*].nextSeries").value(hasItem(DEFAULT_NEXT_SERIES)));
    }

    @Test
    @Transactional
    void getSeriesTable() throws Exception {
        // Initialize the database
        seriesTableRepository.saveAndFlush(seriesTable);

        // Get the seriesTable
        restSeriesTableMockMvc
            .perform(get(ENTITY_API_URL_ID, seriesTable.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(seriesTable.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.prefix").value(DEFAULT_PREFIX))
            .andExpect(jsonPath("$.nextSeries").value(DEFAULT_NEXT_SERIES));
    }

    @Test
    @Transactional
    void getNonExistingSeriesTable() throws Exception {
        // Get the seriesTable
        restSeriesTableMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewSeriesTable() throws Exception {
        // Initialize the database
        seriesTableRepository.saveAndFlush(seriesTable);

        int databaseSizeBeforeUpdate = seriesTableRepository.findAll().size();

        // Update the seriesTable
        SeriesTable updatedSeriesTable = seriesTableRepository.findById(seriesTable.getId()).get();
        // Disconnect from session so that the updates on updatedSeriesTable are not directly saved in db
        em.detach(updatedSeriesTable);
        updatedSeriesTable.name(UPDATED_NAME).prefix(UPDATED_PREFIX).nextSeries(UPDATED_NEXT_SERIES);
        SeriesTableDTO seriesTableDTO = seriesTableMapper.toDto(updatedSeriesTable);

        restSeriesTableMockMvc
            .perform(
                put(ENTITY_API_URL_ID, seriesTableDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(seriesTableDTO))
            )
            .andExpect(status().isOk());

        // Validate the SeriesTable in the database
        List<SeriesTable> seriesTableList = seriesTableRepository.findAll();
        assertThat(seriesTableList).hasSize(databaseSizeBeforeUpdate);
        SeriesTable testSeriesTable = seriesTableList.get(seriesTableList.size() - 1);
        assertThat(testSeriesTable.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSeriesTable.getPrefix()).isEqualTo(UPDATED_PREFIX);
        assertThat(testSeriesTable.getNextSeries()).isEqualTo(UPDATED_NEXT_SERIES);
    }

    @Test
    @Transactional
    void putNonExistingSeriesTable() throws Exception {
        int databaseSizeBeforeUpdate = seriesTableRepository.findAll().size();
        seriesTable.setId(count.incrementAndGet());

        // Create the SeriesTable
        SeriesTableDTO seriesTableDTO = seriesTableMapper.toDto(seriesTable);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSeriesTableMockMvc
            .perform(
                put(ENTITY_API_URL_ID, seriesTableDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(seriesTableDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SeriesTable in the database
        List<SeriesTable> seriesTableList = seriesTableRepository.findAll();
        assertThat(seriesTableList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSeriesTable() throws Exception {
        int databaseSizeBeforeUpdate = seriesTableRepository.findAll().size();
        seriesTable.setId(count.incrementAndGet());

        // Create the SeriesTable
        SeriesTableDTO seriesTableDTO = seriesTableMapper.toDto(seriesTable);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSeriesTableMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(seriesTableDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SeriesTable in the database
        List<SeriesTable> seriesTableList = seriesTableRepository.findAll();
        assertThat(seriesTableList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSeriesTable() throws Exception {
        int databaseSizeBeforeUpdate = seriesTableRepository.findAll().size();
        seriesTable.setId(count.incrementAndGet());

        // Create the SeriesTable
        SeriesTableDTO seriesTableDTO = seriesTableMapper.toDto(seriesTable);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSeriesTableMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(seriesTableDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the SeriesTable in the database
        List<SeriesTable> seriesTableList = seriesTableRepository.findAll();
        assertThat(seriesTableList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSeriesTableWithPatch() throws Exception {
        // Initialize the database
        seriesTableRepository.saveAndFlush(seriesTable);

        int databaseSizeBeforeUpdate = seriesTableRepository.findAll().size();

        // Update the seriesTable using partial update
        SeriesTable partialUpdatedSeriesTable = new SeriesTable();
        partialUpdatedSeriesTable.setId(seriesTable.getId());

        restSeriesTableMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSeriesTable.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSeriesTable))
            )
            .andExpect(status().isOk());

        // Validate the SeriesTable in the database
        List<SeriesTable> seriesTableList = seriesTableRepository.findAll();
        assertThat(seriesTableList).hasSize(databaseSizeBeforeUpdate);
        SeriesTable testSeriesTable = seriesTableList.get(seriesTableList.size() - 1);
        assertThat(testSeriesTable.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSeriesTable.getPrefix()).isEqualTo(DEFAULT_PREFIX);
        assertThat(testSeriesTable.getNextSeries()).isEqualTo(DEFAULT_NEXT_SERIES);
    }

    @Test
    @Transactional
    void fullUpdateSeriesTableWithPatch() throws Exception {
        // Initialize the database
        seriesTableRepository.saveAndFlush(seriesTable);

        int databaseSizeBeforeUpdate = seriesTableRepository.findAll().size();

        // Update the seriesTable using partial update
        SeriesTable partialUpdatedSeriesTable = new SeriesTable();
        partialUpdatedSeriesTable.setId(seriesTable.getId());

        partialUpdatedSeriesTable.name(UPDATED_NAME).prefix(UPDATED_PREFIX).nextSeries(UPDATED_NEXT_SERIES);

        restSeriesTableMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSeriesTable.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSeriesTable))
            )
            .andExpect(status().isOk());

        // Validate the SeriesTable in the database
        List<SeriesTable> seriesTableList = seriesTableRepository.findAll();
        assertThat(seriesTableList).hasSize(databaseSizeBeforeUpdate);
        SeriesTable testSeriesTable = seriesTableList.get(seriesTableList.size() - 1);
        assertThat(testSeriesTable.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSeriesTable.getPrefix()).isEqualTo(UPDATED_PREFIX);
        assertThat(testSeriesTable.getNextSeries()).isEqualTo(UPDATED_NEXT_SERIES);
    }

    @Test
    @Transactional
    void patchNonExistingSeriesTable() throws Exception {
        int databaseSizeBeforeUpdate = seriesTableRepository.findAll().size();
        seriesTable.setId(count.incrementAndGet());

        // Create the SeriesTable
        SeriesTableDTO seriesTableDTO = seriesTableMapper.toDto(seriesTable);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSeriesTableMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, seriesTableDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(seriesTableDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SeriesTable in the database
        List<SeriesTable> seriesTableList = seriesTableRepository.findAll();
        assertThat(seriesTableList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSeriesTable() throws Exception {
        int databaseSizeBeforeUpdate = seriesTableRepository.findAll().size();
        seriesTable.setId(count.incrementAndGet());

        // Create the SeriesTable
        SeriesTableDTO seriesTableDTO = seriesTableMapper.toDto(seriesTable);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSeriesTableMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(seriesTableDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SeriesTable in the database
        List<SeriesTable> seriesTableList = seriesTableRepository.findAll();
        assertThat(seriesTableList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSeriesTable() throws Exception {
        int databaseSizeBeforeUpdate = seriesTableRepository.findAll().size();
        seriesTable.setId(count.incrementAndGet());

        // Create the SeriesTable
        SeriesTableDTO seriesTableDTO = seriesTableMapper.toDto(seriesTable);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSeriesTableMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(seriesTableDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SeriesTable in the database
        List<SeriesTable> seriesTableList = seriesTableRepository.findAll();
        assertThat(seriesTableList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSeriesTable() throws Exception {
        // Initialize the database
        seriesTableRepository.saveAndFlush(seriesTable);

        int databaseSizeBeforeDelete = seriesTableRepository.findAll().size();

        // Delete the seriesTable
        restSeriesTableMockMvc
            .perform(delete(ENTITY_API_URL_ID, seriesTable.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SeriesTable> seriesTableList = seriesTableRepository.findAll();
        assertThat(seriesTableList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
