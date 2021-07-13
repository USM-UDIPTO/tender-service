package com.dxc.eproc.tender.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dxc.eproc.tender.IntegrationTest;
import com.dxc.eproc.tender.domain.TenderScheduleSampleAddress;
import com.dxc.eproc.tender.repository.TenderScheduleSampleAddressRepository;
import com.dxc.eproc.tender.service.dto.TenderScheduleSampleAddressDTO;
import com.dxc.eproc.tender.service.mapper.TenderScheduleSampleAddressMapper;
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
 * Integration tests for the {@link TenderScheduleSampleAddressResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TenderScheduleSampleAddressResourceIT {

    private static final String DEFAULT_BLOCK_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_BLOCK_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_STREET = "AAAAAAAAAA";
    private static final String UPDATED_STREET = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_PIN = "AAAAAAAAAA";
    private static final String UPDATED_PIN = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/tender-schedule-sample-addresses";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TenderScheduleSampleAddressRepository tenderScheduleSampleAddressRepository;

    @Autowired
    private TenderScheduleSampleAddressMapper tenderScheduleSampleAddressMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTenderScheduleSampleAddressMockMvc;

    private TenderScheduleSampleAddress tenderScheduleSampleAddress;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TenderScheduleSampleAddress createEntity(EntityManager em) {
        TenderScheduleSampleAddress tenderScheduleSampleAddress = new TenderScheduleSampleAddress()
            .blockNumber(DEFAULT_BLOCK_NUMBER)
            .street(DEFAULT_STREET)
            .city(DEFAULT_CITY)
            .pin(DEFAULT_PIN);
        return tenderScheduleSampleAddress;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TenderScheduleSampleAddress createUpdatedEntity(EntityManager em) {
        TenderScheduleSampleAddress tenderScheduleSampleAddress = new TenderScheduleSampleAddress()
            .blockNumber(UPDATED_BLOCK_NUMBER)
            .street(UPDATED_STREET)
            .city(UPDATED_CITY)
            .pin(UPDATED_PIN);
        return tenderScheduleSampleAddress;
    }

    @BeforeEach
    public void initTest() {
        tenderScheduleSampleAddress = createEntity(em);
    }

    @Test
    @Transactional
    void createTenderScheduleSampleAddress() throws Exception {
        int databaseSizeBeforeCreate = tenderScheduleSampleAddressRepository.findAll().size();
        // Create the TenderScheduleSampleAddress
        TenderScheduleSampleAddressDTO tenderScheduleSampleAddressDTO = tenderScheduleSampleAddressMapper.toDto(
            tenderScheduleSampleAddress
        );
        restTenderScheduleSampleAddressMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleSampleAddressDTO))
            )
            .andExpect(status().isCreated());

        // Validate the TenderScheduleSampleAddress in the database
        List<TenderScheduleSampleAddress> tenderScheduleSampleAddressList = tenderScheduleSampleAddressRepository.findAll();
        assertThat(tenderScheduleSampleAddressList).hasSize(databaseSizeBeforeCreate + 1);
        TenderScheduleSampleAddress testTenderScheduleSampleAddress = tenderScheduleSampleAddressList.get(
            tenderScheduleSampleAddressList.size() - 1
        );
        assertThat(testTenderScheduleSampleAddress.getBlockNumber()).isEqualTo(DEFAULT_BLOCK_NUMBER);
        assertThat(testTenderScheduleSampleAddress.getStreet()).isEqualTo(DEFAULT_STREET);
        assertThat(testTenderScheduleSampleAddress.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testTenderScheduleSampleAddress.getPin()).isEqualTo(DEFAULT_PIN);
    }

    @Test
    @Transactional
    void createTenderScheduleSampleAddressWithExistingId() throws Exception {
        // Create the TenderScheduleSampleAddress with an existing ID
        tenderScheduleSampleAddress.setId(1L);
        TenderScheduleSampleAddressDTO tenderScheduleSampleAddressDTO = tenderScheduleSampleAddressMapper.toDto(
            tenderScheduleSampleAddress
        );

        int databaseSizeBeforeCreate = tenderScheduleSampleAddressRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTenderScheduleSampleAddressMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleSampleAddressDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderScheduleSampleAddress in the database
        List<TenderScheduleSampleAddress> tenderScheduleSampleAddressList = tenderScheduleSampleAddressRepository.findAll();
        assertThat(tenderScheduleSampleAddressList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkBlockNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderScheduleSampleAddressRepository.findAll().size();
        // set the field null
        tenderScheduleSampleAddress.setBlockNumber(null);

        // Create the TenderScheduleSampleAddress, which fails.
        TenderScheduleSampleAddressDTO tenderScheduleSampleAddressDTO = tenderScheduleSampleAddressMapper.toDto(
            tenderScheduleSampleAddress
        );

        restTenderScheduleSampleAddressMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleSampleAddressDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderScheduleSampleAddress> tenderScheduleSampleAddressList = tenderScheduleSampleAddressRepository.findAll();
        assertThat(tenderScheduleSampleAddressList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkStreetIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderScheduleSampleAddressRepository.findAll().size();
        // set the field null
        tenderScheduleSampleAddress.setStreet(null);

        // Create the TenderScheduleSampleAddress, which fails.
        TenderScheduleSampleAddressDTO tenderScheduleSampleAddressDTO = tenderScheduleSampleAddressMapper.toDto(
            tenderScheduleSampleAddress
        );

        restTenderScheduleSampleAddressMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleSampleAddressDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderScheduleSampleAddress> tenderScheduleSampleAddressList = tenderScheduleSampleAddressRepository.findAll();
        assertThat(tenderScheduleSampleAddressList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCityIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderScheduleSampleAddressRepository.findAll().size();
        // set the field null
        tenderScheduleSampleAddress.setCity(null);

        // Create the TenderScheduleSampleAddress, which fails.
        TenderScheduleSampleAddressDTO tenderScheduleSampleAddressDTO = tenderScheduleSampleAddressMapper.toDto(
            tenderScheduleSampleAddress
        );

        restTenderScheduleSampleAddressMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleSampleAddressDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderScheduleSampleAddress> tenderScheduleSampleAddressList = tenderScheduleSampleAddressRepository.findAll();
        assertThat(tenderScheduleSampleAddressList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPinIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderScheduleSampleAddressRepository.findAll().size();
        // set the field null
        tenderScheduleSampleAddress.setPin(null);

        // Create the TenderScheduleSampleAddress, which fails.
        TenderScheduleSampleAddressDTO tenderScheduleSampleAddressDTO = tenderScheduleSampleAddressMapper.toDto(
            tenderScheduleSampleAddress
        );

        restTenderScheduleSampleAddressMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleSampleAddressDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderScheduleSampleAddress> tenderScheduleSampleAddressList = tenderScheduleSampleAddressRepository.findAll();
        assertThat(tenderScheduleSampleAddressList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTenderScheduleSampleAddresses() throws Exception {
        // Initialize the database
        tenderScheduleSampleAddressRepository.saveAndFlush(tenderScheduleSampleAddress);

        // Get all the tenderScheduleSampleAddressList
        restTenderScheduleSampleAddressMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tenderScheduleSampleAddress.getId().intValue())))
            .andExpect(jsonPath("$.[*].blockNumber").value(hasItem(DEFAULT_BLOCK_NUMBER)))
            .andExpect(jsonPath("$.[*].street").value(hasItem(DEFAULT_STREET)))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY)))
            .andExpect(jsonPath("$.[*].pin").value(hasItem(DEFAULT_PIN)));
    }

    @Test
    @Transactional
    void getTenderScheduleSampleAddress() throws Exception {
        // Initialize the database
        tenderScheduleSampleAddressRepository.saveAndFlush(tenderScheduleSampleAddress);

        // Get the tenderScheduleSampleAddress
        restTenderScheduleSampleAddressMockMvc
            .perform(get(ENTITY_API_URL_ID, tenderScheduleSampleAddress.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tenderScheduleSampleAddress.getId().intValue()))
            .andExpect(jsonPath("$.blockNumber").value(DEFAULT_BLOCK_NUMBER))
            .andExpect(jsonPath("$.street").value(DEFAULT_STREET))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY))
            .andExpect(jsonPath("$.pin").value(DEFAULT_PIN));
    }

    @Test
    @Transactional
    void getNonExistingTenderScheduleSampleAddress() throws Exception {
        // Get the tenderScheduleSampleAddress
        restTenderScheduleSampleAddressMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTenderScheduleSampleAddress() throws Exception {
        // Initialize the database
        tenderScheduleSampleAddressRepository.saveAndFlush(tenderScheduleSampleAddress);

        int databaseSizeBeforeUpdate = tenderScheduleSampleAddressRepository.findAll().size();

        // Update the tenderScheduleSampleAddress
        TenderScheduleSampleAddress updatedTenderScheduleSampleAddress = tenderScheduleSampleAddressRepository
            .findById(tenderScheduleSampleAddress.getId())
            .get();
        // Disconnect from session so that the updates on updatedTenderScheduleSampleAddress are not directly saved in db
        em.detach(updatedTenderScheduleSampleAddress);
        updatedTenderScheduleSampleAddress.blockNumber(UPDATED_BLOCK_NUMBER).street(UPDATED_STREET).city(UPDATED_CITY).pin(UPDATED_PIN);
        TenderScheduleSampleAddressDTO tenderScheduleSampleAddressDTO = tenderScheduleSampleAddressMapper.toDto(
            updatedTenderScheduleSampleAddress
        );

        restTenderScheduleSampleAddressMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tenderScheduleSampleAddressDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleSampleAddressDTO))
            )
            .andExpect(status().isOk());

        // Validate the TenderScheduleSampleAddress in the database
        List<TenderScheduleSampleAddress> tenderScheduleSampleAddressList = tenderScheduleSampleAddressRepository.findAll();
        assertThat(tenderScheduleSampleAddressList).hasSize(databaseSizeBeforeUpdate);
        TenderScheduleSampleAddress testTenderScheduleSampleAddress = tenderScheduleSampleAddressList.get(
            tenderScheduleSampleAddressList.size() - 1
        );
        assertThat(testTenderScheduleSampleAddress.getBlockNumber()).isEqualTo(UPDATED_BLOCK_NUMBER);
        assertThat(testTenderScheduleSampleAddress.getStreet()).isEqualTo(UPDATED_STREET);
        assertThat(testTenderScheduleSampleAddress.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testTenderScheduleSampleAddress.getPin()).isEqualTo(UPDATED_PIN);
    }

    @Test
    @Transactional
    void putNonExistingTenderScheduleSampleAddress() throws Exception {
        int databaseSizeBeforeUpdate = tenderScheduleSampleAddressRepository.findAll().size();
        tenderScheduleSampleAddress.setId(count.incrementAndGet());

        // Create the TenderScheduleSampleAddress
        TenderScheduleSampleAddressDTO tenderScheduleSampleAddressDTO = tenderScheduleSampleAddressMapper.toDto(
            tenderScheduleSampleAddress
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTenderScheduleSampleAddressMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tenderScheduleSampleAddressDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleSampleAddressDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderScheduleSampleAddress in the database
        List<TenderScheduleSampleAddress> tenderScheduleSampleAddressList = tenderScheduleSampleAddressRepository.findAll();
        assertThat(tenderScheduleSampleAddressList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTenderScheduleSampleAddress() throws Exception {
        int databaseSizeBeforeUpdate = tenderScheduleSampleAddressRepository.findAll().size();
        tenderScheduleSampleAddress.setId(count.incrementAndGet());

        // Create the TenderScheduleSampleAddress
        TenderScheduleSampleAddressDTO tenderScheduleSampleAddressDTO = tenderScheduleSampleAddressMapper.toDto(
            tenderScheduleSampleAddress
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderScheduleSampleAddressMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleSampleAddressDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderScheduleSampleAddress in the database
        List<TenderScheduleSampleAddress> tenderScheduleSampleAddressList = tenderScheduleSampleAddressRepository.findAll();
        assertThat(tenderScheduleSampleAddressList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTenderScheduleSampleAddress() throws Exception {
        int databaseSizeBeforeUpdate = tenderScheduleSampleAddressRepository.findAll().size();
        tenderScheduleSampleAddress.setId(count.incrementAndGet());

        // Create the TenderScheduleSampleAddress
        TenderScheduleSampleAddressDTO tenderScheduleSampleAddressDTO = tenderScheduleSampleAddressMapper.toDto(
            tenderScheduleSampleAddress
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderScheduleSampleAddressMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleSampleAddressDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TenderScheduleSampleAddress in the database
        List<TenderScheduleSampleAddress> tenderScheduleSampleAddressList = tenderScheduleSampleAddressRepository.findAll();
        assertThat(tenderScheduleSampleAddressList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTenderScheduleSampleAddressWithPatch() throws Exception {
        // Initialize the database
        tenderScheduleSampleAddressRepository.saveAndFlush(tenderScheduleSampleAddress);

        int databaseSizeBeforeUpdate = tenderScheduleSampleAddressRepository.findAll().size();

        // Update the tenderScheduleSampleAddress using partial update
        TenderScheduleSampleAddress partialUpdatedTenderScheduleSampleAddress = new TenderScheduleSampleAddress();
        partialUpdatedTenderScheduleSampleAddress.setId(tenderScheduleSampleAddress.getId());

        partialUpdatedTenderScheduleSampleAddress.blockNumber(UPDATED_BLOCK_NUMBER).city(UPDATED_CITY);

        restTenderScheduleSampleAddressMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTenderScheduleSampleAddress.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTenderScheduleSampleAddress))
            )
            .andExpect(status().isOk());

        // Validate the TenderScheduleSampleAddress in the database
        List<TenderScheduleSampleAddress> tenderScheduleSampleAddressList = tenderScheduleSampleAddressRepository.findAll();
        assertThat(tenderScheduleSampleAddressList).hasSize(databaseSizeBeforeUpdate);
        TenderScheduleSampleAddress testTenderScheduleSampleAddress = tenderScheduleSampleAddressList.get(
            tenderScheduleSampleAddressList.size() - 1
        );
        assertThat(testTenderScheduleSampleAddress.getBlockNumber()).isEqualTo(UPDATED_BLOCK_NUMBER);
        assertThat(testTenderScheduleSampleAddress.getStreet()).isEqualTo(DEFAULT_STREET);
        assertThat(testTenderScheduleSampleAddress.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testTenderScheduleSampleAddress.getPin()).isEqualTo(DEFAULT_PIN);
    }

    @Test
    @Transactional
    void fullUpdateTenderScheduleSampleAddressWithPatch() throws Exception {
        // Initialize the database
        tenderScheduleSampleAddressRepository.saveAndFlush(tenderScheduleSampleAddress);

        int databaseSizeBeforeUpdate = tenderScheduleSampleAddressRepository.findAll().size();

        // Update the tenderScheduleSampleAddress using partial update
        TenderScheduleSampleAddress partialUpdatedTenderScheduleSampleAddress = new TenderScheduleSampleAddress();
        partialUpdatedTenderScheduleSampleAddress.setId(tenderScheduleSampleAddress.getId());

        partialUpdatedTenderScheduleSampleAddress
            .blockNumber(UPDATED_BLOCK_NUMBER)
            .street(UPDATED_STREET)
            .city(UPDATED_CITY)
            .pin(UPDATED_PIN);

        restTenderScheduleSampleAddressMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTenderScheduleSampleAddress.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTenderScheduleSampleAddress))
            )
            .andExpect(status().isOk());

        // Validate the TenderScheduleSampleAddress in the database
        List<TenderScheduleSampleAddress> tenderScheduleSampleAddressList = tenderScheduleSampleAddressRepository.findAll();
        assertThat(tenderScheduleSampleAddressList).hasSize(databaseSizeBeforeUpdate);
        TenderScheduleSampleAddress testTenderScheduleSampleAddress = tenderScheduleSampleAddressList.get(
            tenderScheduleSampleAddressList.size() - 1
        );
        assertThat(testTenderScheduleSampleAddress.getBlockNumber()).isEqualTo(UPDATED_BLOCK_NUMBER);
        assertThat(testTenderScheduleSampleAddress.getStreet()).isEqualTo(UPDATED_STREET);
        assertThat(testTenderScheduleSampleAddress.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testTenderScheduleSampleAddress.getPin()).isEqualTo(UPDATED_PIN);
    }

    @Test
    @Transactional
    void patchNonExistingTenderScheduleSampleAddress() throws Exception {
        int databaseSizeBeforeUpdate = tenderScheduleSampleAddressRepository.findAll().size();
        tenderScheduleSampleAddress.setId(count.incrementAndGet());

        // Create the TenderScheduleSampleAddress
        TenderScheduleSampleAddressDTO tenderScheduleSampleAddressDTO = tenderScheduleSampleAddressMapper.toDto(
            tenderScheduleSampleAddress
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTenderScheduleSampleAddressMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tenderScheduleSampleAddressDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleSampleAddressDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderScheduleSampleAddress in the database
        List<TenderScheduleSampleAddress> tenderScheduleSampleAddressList = tenderScheduleSampleAddressRepository.findAll();
        assertThat(tenderScheduleSampleAddressList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTenderScheduleSampleAddress() throws Exception {
        int databaseSizeBeforeUpdate = tenderScheduleSampleAddressRepository.findAll().size();
        tenderScheduleSampleAddress.setId(count.incrementAndGet());

        // Create the TenderScheduleSampleAddress
        TenderScheduleSampleAddressDTO tenderScheduleSampleAddressDTO = tenderScheduleSampleAddressMapper.toDto(
            tenderScheduleSampleAddress
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderScheduleSampleAddressMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleSampleAddressDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderScheduleSampleAddress in the database
        List<TenderScheduleSampleAddress> tenderScheduleSampleAddressList = tenderScheduleSampleAddressRepository.findAll();
        assertThat(tenderScheduleSampleAddressList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTenderScheduleSampleAddress() throws Exception {
        int databaseSizeBeforeUpdate = tenderScheduleSampleAddressRepository.findAll().size();
        tenderScheduleSampleAddress.setId(count.incrementAndGet());

        // Create the TenderScheduleSampleAddress
        TenderScheduleSampleAddressDTO tenderScheduleSampleAddressDTO = tenderScheduleSampleAddressMapper.toDto(
            tenderScheduleSampleAddress
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderScheduleSampleAddressMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleSampleAddressDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TenderScheduleSampleAddress in the database
        List<TenderScheduleSampleAddress> tenderScheduleSampleAddressList = tenderScheduleSampleAddressRepository.findAll();
        assertThat(tenderScheduleSampleAddressList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTenderScheduleSampleAddress() throws Exception {
        // Initialize the database
        tenderScheduleSampleAddressRepository.saveAndFlush(tenderScheduleSampleAddress);

        int databaseSizeBeforeDelete = tenderScheduleSampleAddressRepository.findAll().size();

        // Delete the tenderScheduleSampleAddress
        restTenderScheduleSampleAddressMockMvc
            .perform(delete(ENTITY_API_URL_ID, tenderScheduleSampleAddress.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TenderScheduleSampleAddress> tenderScheduleSampleAddressList = tenderScheduleSampleAddressRepository.findAll();
        assertThat(tenderScheduleSampleAddressList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
