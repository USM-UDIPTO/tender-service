package com.dxc.eproc.tender.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dxc.eproc.tender.IntegrationTest;
import com.dxc.eproc.tender.domain.ObjectStore;
import com.dxc.eproc.tender.repository.ObjectStoreRepository;
import com.dxc.eproc.tender.service.dto.ObjectStoreDTO;
import com.dxc.eproc.tender.service.mapper.ObjectStoreMapper;
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
 * Integration tests for the {@link ObjectStoreResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ObjectStoreResourceIT {

    private static final Long DEFAULT_NIT_ID = 1L;
    private static final Long UPDATED_NIT_ID = 2L;

    private static final String DEFAULT_UUID = "AAAAAAAAAA";
    private static final String UPDATED_UUID = "BBBBBBBBBB";

    private static final Long DEFAULT_REFERENCE_ID = 1L;
    private static final Long UPDATED_REFERENCE_ID = 2L;

    private static final Long DEFAULT_REFERENCE_TYPE = 1L;
    private static final Long UPDATED_REFERENCE_TYPE = 2L;

    private static final Boolean DEFAULT_ACTIVE_YN = false;
    private static final Boolean UPDATED_ACTIVE_YN = true;

    private static final String ENTITY_API_URL = "/api/object-stores";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectStoreRepository objectStoreRepository;

    @Autowired
    private ObjectStoreMapper objectStoreMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restObjectStoreMockMvc;

    private ObjectStore objectStore;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ObjectStore createEntity(EntityManager em) {
        ObjectStore objectStore = new ObjectStore()
            .nitId(DEFAULT_NIT_ID)
            .uuid(DEFAULT_UUID)
            .referenceId(DEFAULT_REFERENCE_ID)
            .referenceType(DEFAULT_REFERENCE_TYPE)
            .activeYn(DEFAULT_ACTIVE_YN);
        return objectStore;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ObjectStore createUpdatedEntity(EntityManager em) {
        ObjectStore objectStore = new ObjectStore()
            .nitId(UPDATED_NIT_ID)
            .uuid(UPDATED_UUID)
            .referenceId(UPDATED_REFERENCE_ID)
            .referenceType(UPDATED_REFERENCE_TYPE)
            .activeYn(UPDATED_ACTIVE_YN);
        return objectStore;
    }

    @BeforeEach
    public void initTest() {
        objectStore = createEntity(em);
    }

    @Test
    @Transactional
    void createObjectStore() throws Exception {
        int databaseSizeBeforeCreate = objectStoreRepository.findAll().size();
        // Create the ObjectStore
        ObjectStoreDTO objectStoreDTO = objectStoreMapper.toDto(objectStore);
        restObjectStoreMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(objectStoreDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ObjectStore in the database
        List<ObjectStore> objectStoreList = objectStoreRepository.findAll();
        assertThat(objectStoreList).hasSize(databaseSizeBeforeCreate + 1);
        ObjectStore testObjectStore = objectStoreList.get(objectStoreList.size() - 1);
        assertThat(testObjectStore.getNitId()).isEqualTo(DEFAULT_NIT_ID);
        assertThat(testObjectStore.getUuid()).isEqualTo(DEFAULT_UUID);
        assertThat(testObjectStore.getReferenceId()).isEqualTo(DEFAULT_REFERENCE_ID);
        assertThat(testObjectStore.getReferenceType()).isEqualTo(DEFAULT_REFERENCE_TYPE);
        assertThat(testObjectStore.getActiveYn()).isEqualTo(DEFAULT_ACTIVE_YN);
    }

    @Test
    @Transactional
    void createObjectStoreWithExistingId() throws Exception {
        // Create the ObjectStore with an existing ID
        objectStore.setId(1L);
        ObjectStoreDTO objectStoreDTO = objectStoreMapper.toDto(objectStore);

        int databaseSizeBeforeCreate = objectStoreRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restObjectStoreMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(objectStoreDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ObjectStore in the database
        List<ObjectStore> objectStoreList = objectStoreRepository.findAll();
        assertThat(objectStoreList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNitIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = objectStoreRepository.findAll().size();
        // set the field null
        objectStore.setNitId(null);

        // Create the ObjectStore, which fails.
        ObjectStoreDTO objectStoreDTO = objectStoreMapper.toDto(objectStore);

        restObjectStoreMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(objectStoreDTO))
            )
            .andExpect(status().isBadRequest());

        List<ObjectStore> objectStoreList = objectStoreRepository.findAll();
        assertThat(objectStoreList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUuidIsRequired() throws Exception {
        int databaseSizeBeforeTest = objectStoreRepository.findAll().size();
        // set the field null
        objectStore.setUuid(null);

        // Create the ObjectStore, which fails.
        ObjectStoreDTO objectStoreDTO = objectStoreMapper.toDto(objectStore);

        restObjectStoreMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(objectStoreDTO))
            )
            .andExpect(status().isBadRequest());

        List<ObjectStore> objectStoreList = objectStoreRepository.findAll();
        assertThat(objectStoreList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkReferenceIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = objectStoreRepository.findAll().size();
        // set the field null
        objectStore.setReferenceId(null);

        // Create the ObjectStore, which fails.
        ObjectStoreDTO objectStoreDTO = objectStoreMapper.toDto(objectStore);

        restObjectStoreMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(objectStoreDTO))
            )
            .andExpect(status().isBadRequest());

        List<ObjectStore> objectStoreList = objectStoreRepository.findAll();
        assertThat(objectStoreList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkReferenceTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = objectStoreRepository.findAll().size();
        // set the field null
        objectStore.setReferenceType(null);

        // Create the ObjectStore, which fails.
        ObjectStoreDTO objectStoreDTO = objectStoreMapper.toDto(objectStore);

        restObjectStoreMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(objectStoreDTO))
            )
            .andExpect(status().isBadRequest());

        List<ObjectStore> objectStoreList = objectStoreRepository.findAll();
        assertThat(objectStoreList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkActiveYnIsRequired() throws Exception {
        int databaseSizeBeforeTest = objectStoreRepository.findAll().size();
        // set the field null
        objectStore.setActiveYn(null);

        // Create the ObjectStore, which fails.
        ObjectStoreDTO objectStoreDTO = objectStoreMapper.toDto(objectStore);

        restObjectStoreMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(objectStoreDTO))
            )
            .andExpect(status().isBadRequest());

        List<ObjectStore> objectStoreList = objectStoreRepository.findAll();
        assertThat(objectStoreList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllObjectStores() throws Exception {
        // Initialize the database
        objectStoreRepository.saveAndFlush(objectStore);

        // Get all the objectStoreList
        restObjectStoreMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(objectStore.getId().intValue())))
            .andExpect(jsonPath("$.[*].nitId").value(hasItem(DEFAULT_NIT_ID.intValue())))
            .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID)))
            .andExpect(jsonPath("$.[*].referenceId").value(hasItem(DEFAULT_REFERENCE_ID.intValue())))
            .andExpect(jsonPath("$.[*].referenceType").value(hasItem(DEFAULT_REFERENCE_TYPE.intValue())))
            .andExpect(jsonPath("$.[*].activeYn").value(hasItem(DEFAULT_ACTIVE_YN.booleanValue())));
    }

    @Test
    @Transactional
    void getObjectStore() throws Exception {
        // Initialize the database
        objectStoreRepository.saveAndFlush(objectStore);

        // Get the objectStore
        restObjectStoreMockMvc
            .perform(get(ENTITY_API_URL_ID, objectStore.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(objectStore.getId().intValue()))
            .andExpect(jsonPath("$.nitId").value(DEFAULT_NIT_ID.intValue()))
            .andExpect(jsonPath("$.uuid").value(DEFAULT_UUID))
            .andExpect(jsonPath("$.referenceId").value(DEFAULT_REFERENCE_ID.intValue()))
            .andExpect(jsonPath("$.referenceType").value(DEFAULT_REFERENCE_TYPE.intValue()))
            .andExpect(jsonPath("$.activeYn").value(DEFAULT_ACTIVE_YN.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingObjectStore() throws Exception {
        // Get the objectStore
        restObjectStoreMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewObjectStore() throws Exception {
        // Initialize the database
        objectStoreRepository.saveAndFlush(objectStore);

        int databaseSizeBeforeUpdate = objectStoreRepository.findAll().size();

        // Update the objectStore
        ObjectStore updatedObjectStore = objectStoreRepository.findById(objectStore.getId()).get();
        // Disconnect from session so that the updates on updatedObjectStore are not directly saved in db
        em.detach(updatedObjectStore);
        updatedObjectStore
            .nitId(UPDATED_NIT_ID)
            .uuid(UPDATED_UUID)
            .referenceId(UPDATED_REFERENCE_ID)
            .referenceType(UPDATED_REFERENCE_TYPE)
            .activeYn(UPDATED_ACTIVE_YN);
        ObjectStoreDTO objectStoreDTO = objectStoreMapper.toDto(updatedObjectStore);

        restObjectStoreMockMvc
            .perform(
                put(ENTITY_API_URL_ID, objectStoreDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(objectStoreDTO))
            )
            .andExpect(status().isOk());

        // Validate the ObjectStore in the database
        List<ObjectStore> objectStoreList = objectStoreRepository.findAll();
        assertThat(objectStoreList).hasSize(databaseSizeBeforeUpdate);
        ObjectStore testObjectStore = objectStoreList.get(objectStoreList.size() - 1);
        assertThat(testObjectStore.getNitId()).isEqualTo(UPDATED_NIT_ID);
        assertThat(testObjectStore.getUuid()).isEqualTo(UPDATED_UUID);
        assertThat(testObjectStore.getReferenceId()).isEqualTo(UPDATED_REFERENCE_ID);
        assertThat(testObjectStore.getReferenceType()).isEqualTo(UPDATED_REFERENCE_TYPE);
        assertThat(testObjectStore.getActiveYn()).isEqualTo(UPDATED_ACTIVE_YN);
    }

    @Test
    @Transactional
    void putNonExistingObjectStore() throws Exception {
        int databaseSizeBeforeUpdate = objectStoreRepository.findAll().size();
        objectStore.setId(count.incrementAndGet());

        // Create the ObjectStore
        ObjectStoreDTO objectStoreDTO = objectStoreMapper.toDto(objectStore);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restObjectStoreMockMvc
            .perform(
                put(ENTITY_API_URL_ID, objectStoreDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(objectStoreDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ObjectStore in the database
        List<ObjectStore> objectStoreList = objectStoreRepository.findAll();
        assertThat(objectStoreList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchObjectStore() throws Exception {
        int databaseSizeBeforeUpdate = objectStoreRepository.findAll().size();
        objectStore.setId(count.incrementAndGet());

        // Create the ObjectStore
        ObjectStoreDTO objectStoreDTO = objectStoreMapper.toDto(objectStore);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restObjectStoreMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(objectStoreDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ObjectStore in the database
        List<ObjectStore> objectStoreList = objectStoreRepository.findAll();
        assertThat(objectStoreList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamObjectStore() throws Exception {
        int databaseSizeBeforeUpdate = objectStoreRepository.findAll().size();
        objectStore.setId(count.incrementAndGet());

        // Create the ObjectStore
        ObjectStoreDTO objectStoreDTO = objectStoreMapper.toDto(objectStore);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restObjectStoreMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(objectStoreDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ObjectStore in the database
        List<ObjectStore> objectStoreList = objectStoreRepository.findAll();
        assertThat(objectStoreList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateObjectStoreWithPatch() throws Exception {
        // Initialize the database
        objectStoreRepository.saveAndFlush(objectStore);

        int databaseSizeBeforeUpdate = objectStoreRepository.findAll().size();

        // Update the objectStore using partial update
        ObjectStore partialUpdatedObjectStore = new ObjectStore();
        partialUpdatedObjectStore.setId(objectStore.getId());

        restObjectStoreMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedObjectStore.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedObjectStore))
            )
            .andExpect(status().isOk());

        // Validate the ObjectStore in the database
        List<ObjectStore> objectStoreList = objectStoreRepository.findAll();
        assertThat(objectStoreList).hasSize(databaseSizeBeforeUpdate);
        ObjectStore testObjectStore = objectStoreList.get(objectStoreList.size() - 1);
        assertThat(testObjectStore.getNitId()).isEqualTo(DEFAULT_NIT_ID);
        assertThat(testObjectStore.getUuid()).isEqualTo(DEFAULT_UUID);
        assertThat(testObjectStore.getReferenceId()).isEqualTo(DEFAULT_REFERENCE_ID);
        assertThat(testObjectStore.getReferenceType()).isEqualTo(DEFAULT_REFERENCE_TYPE);
        assertThat(testObjectStore.getActiveYn()).isEqualTo(DEFAULT_ACTIVE_YN);
    }

    @Test
    @Transactional
    void fullUpdateObjectStoreWithPatch() throws Exception {
        // Initialize the database
        objectStoreRepository.saveAndFlush(objectStore);

        int databaseSizeBeforeUpdate = objectStoreRepository.findAll().size();

        // Update the objectStore using partial update
        ObjectStore partialUpdatedObjectStore = new ObjectStore();
        partialUpdatedObjectStore.setId(objectStore.getId());

        partialUpdatedObjectStore
            .nitId(UPDATED_NIT_ID)
            .uuid(UPDATED_UUID)
            .referenceId(UPDATED_REFERENCE_ID)
            .referenceType(UPDATED_REFERENCE_TYPE)
            .activeYn(UPDATED_ACTIVE_YN);

        restObjectStoreMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedObjectStore.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedObjectStore))
            )
            .andExpect(status().isOk());

        // Validate the ObjectStore in the database
        List<ObjectStore> objectStoreList = objectStoreRepository.findAll();
        assertThat(objectStoreList).hasSize(databaseSizeBeforeUpdate);
        ObjectStore testObjectStore = objectStoreList.get(objectStoreList.size() - 1);
        assertThat(testObjectStore.getNitId()).isEqualTo(UPDATED_NIT_ID);
        assertThat(testObjectStore.getUuid()).isEqualTo(UPDATED_UUID);
        assertThat(testObjectStore.getReferenceId()).isEqualTo(UPDATED_REFERENCE_ID);
        assertThat(testObjectStore.getReferenceType()).isEqualTo(UPDATED_REFERENCE_TYPE);
        assertThat(testObjectStore.getActiveYn()).isEqualTo(UPDATED_ACTIVE_YN);
    }

    @Test
    @Transactional
    void patchNonExistingObjectStore() throws Exception {
        int databaseSizeBeforeUpdate = objectStoreRepository.findAll().size();
        objectStore.setId(count.incrementAndGet());

        // Create the ObjectStore
        ObjectStoreDTO objectStoreDTO = objectStoreMapper.toDto(objectStore);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restObjectStoreMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, objectStoreDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(objectStoreDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ObjectStore in the database
        List<ObjectStore> objectStoreList = objectStoreRepository.findAll();
        assertThat(objectStoreList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchObjectStore() throws Exception {
        int databaseSizeBeforeUpdate = objectStoreRepository.findAll().size();
        objectStore.setId(count.incrementAndGet());

        // Create the ObjectStore
        ObjectStoreDTO objectStoreDTO = objectStoreMapper.toDto(objectStore);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restObjectStoreMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(objectStoreDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ObjectStore in the database
        List<ObjectStore> objectStoreList = objectStoreRepository.findAll();
        assertThat(objectStoreList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamObjectStore() throws Exception {
        int databaseSizeBeforeUpdate = objectStoreRepository.findAll().size();
        objectStore.setId(count.incrementAndGet());

        // Create the ObjectStore
        ObjectStoreDTO objectStoreDTO = objectStoreMapper.toDto(objectStore);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restObjectStoreMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(objectStoreDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ObjectStore in the database
        List<ObjectStore> objectStoreList = objectStoreRepository.findAll();
        assertThat(objectStoreList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteObjectStore() throws Exception {
        // Initialize the database
        objectStoreRepository.saveAndFlush(objectStore);

        int databaseSizeBeforeDelete = objectStoreRepository.findAll().size();

        // Delete the objectStore
        restObjectStoreMockMvc
            .perform(delete(ENTITY_API_URL_ID, objectStore.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ObjectStore> objectStoreList = objectStoreRepository.findAll();
        assertThat(objectStoreList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
