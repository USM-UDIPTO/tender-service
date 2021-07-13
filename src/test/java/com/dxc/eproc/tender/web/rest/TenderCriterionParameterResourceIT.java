package com.dxc.eproc.tender.web.rest;

import static com.dxc.eproc.tender.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dxc.eproc.tender.IntegrationTest;
import com.dxc.eproc.tender.domain.TenderCriterionParameter;
import com.dxc.eproc.tender.repository.TenderCriterionParameterRepository;
import com.dxc.eproc.tender.service.dto.TenderCriterionParameterDTO;
import com.dxc.eproc.tender.service.mapper.TenderCriterionParameterMapper;
import java.math.BigDecimal;
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
 * Integration tests for the {@link TenderCriterionParameterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TenderCriterionParameterResourceIT {

    private static final Long DEFAULT_NIT_ID = 1L;
    private static final Long UPDATED_NIT_ID = 2L;

    private static final Long DEFAULT_INDENT_ITEM_ID = 1L;
    private static final Long UPDATED_INDENT_ITEM_ID = 2L;

    private static final Long DEFAULT_TENDER_CRITERION_ID = 1L;
    private static final Long UPDATED_TENDER_CRITERION_ID = 2L;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_MIN_VALUE = new BigDecimal(1);
    private static final BigDecimal UPDATED_MIN_VALUE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MAX_VALUE = new BigDecimal(1);
    private static final BigDecimal UPDATED_MAX_VALUE = new BigDecimal(2);

    private static final String DEFAULT_OPERATOR = "AAAAAAAAAA";
    private static final String UPDATED_OPERATOR = "BBBBBBBBBB";

    private static final String DEFAULT_DATA_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_DATA_TYPE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_OPTIONAL_YN = false;
    private static final Boolean UPDATED_OPTIONAL_YN = true;

    private static final String ENTITY_API_URL = "/api/tender-criterion-parameters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TenderCriterionParameterRepository tenderCriterionParameterRepository;

    @Autowired
    private TenderCriterionParameterMapper tenderCriterionParameterMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTenderCriterionParameterMockMvc;

    private TenderCriterionParameter tenderCriterionParameter;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TenderCriterionParameter createEntity(EntityManager em) {
        TenderCriterionParameter tenderCriterionParameter = new TenderCriterionParameter()
            .nitId(DEFAULT_NIT_ID)
            .indentItemId(DEFAULT_INDENT_ITEM_ID)
            .tenderCriterionId(DEFAULT_TENDER_CRITERION_ID)
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .minValue(DEFAULT_MIN_VALUE)
            .maxValue(DEFAULT_MAX_VALUE)
            .operator(DEFAULT_OPERATOR)
            .dataType(DEFAULT_DATA_TYPE)
            .optionalYn(DEFAULT_OPTIONAL_YN);
        return tenderCriterionParameter;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TenderCriterionParameter createUpdatedEntity(EntityManager em) {
        TenderCriterionParameter tenderCriterionParameter = new TenderCriterionParameter()
            .nitId(UPDATED_NIT_ID)
            .indentItemId(UPDATED_INDENT_ITEM_ID)
            .tenderCriterionId(UPDATED_TENDER_CRITERION_ID)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .minValue(UPDATED_MIN_VALUE)
            .maxValue(UPDATED_MAX_VALUE)
            .operator(UPDATED_OPERATOR)
            .dataType(UPDATED_DATA_TYPE)
            .optionalYn(UPDATED_OPTIONAL_YN);
        return tenderCriterionParameter;
    }

    @BeforeEach
    public void initTest() {
        tenderCriterionParameter = createEntity(em);
    }

    @Test
    @Transactional
    void createTenderCriterionParameter() throws Exception {
        int databaseSizeBeforeCreate = tenderCriterionParameterRepository.findAll().size();
        // Create the TenderCriterionParameter
        TenderCriterionParameterDTO tenderCriterionParameterDTO = tenderCriterionParameterMapper.toDto(tenderCriterionParameter);
        restTenderCriterionParameterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCriterionParameterDTO))
            )
            .andExpect(status().isCreated());

        // Validate the TenderCriterionParameter in the database
        List<TenderCriterionParameter> tenderCriterionParameterList = tenderCriterionParameterRepository.findAll();
        assertThat(tenderCriterionParameterList).hasSize(databaseSizeBeforeCreate + 1);
        TenderCriterionParameter testTenderCriterionParameter = tenderCriterionParameterList.get(tenderCriterionParameterList.size() - 1);
        assertThat(testTenderCriterionParameter.getNitId()).isEqualTo(DEFAULT_NIT_ID);
        assertThat(testTenderCriterionParameter.getIndentItemId()).isEqualTo(DEFAULT_INDENT_ITEM_ID);
        assertThat(testTenderCriterionParameter.getTenderCriterionId()).isEqualTo(DEFAULT_TENDER_CRITERION_ID);
        assertThat(testTenderCriterionParameter.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testTenderCriterionParameter.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testTenderCriterionParameter.getMinValue()).isEqualByComparingTo(DEFAULT_MIN_VALUE);
        assertThat(testTenderCriterionParameter.getMaxValue()).isEqualByComparingTo(DEFAULT_MAX_VALUE);
        assertThat(testTenderCriterionParameter.getOperator()).isEqualTo(DEFAULT_OPERATOR);
        assertThat(testTenderCriterionParameter.getDataType()).isEqualTo(DEFAULT_DATA_TYPE);
        assertThat(testTenderCriterionParameter.getOptionalYn()).isEqualTo(DEFAULT_OPTIONAL_YN);
    }

    @Test
    @Transactional
    void createTenderCriterionParameterWithExistingId() throws Exception {
        // Create the TenderCriterionParameter with an existing ID
        tenderCriterionParameter.setId(1L);
        TenderCriterionParameterDTO tenderCriterionParameterDTO = tenderCriterionParameterMapper.toDto(tenderCriterionParameter);

        int databaseSizeBeforeCreate = tenderCriterionParameterRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTenderCriterionParameterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCriterionParameterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderCriterionParameter in the database
        List<TenderCriterionParameter> tenderCriterionParameterList = tenderCriterionParameterRepository.findAll();
        assertThat(tenderCriterionParameterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNitIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderCriterionParameterRepository.findAll().size();
        // set the field null
        tenderCriterionParameter.setNitId(null);

        // Create the TenderCriterionParameter, which fails.
        TenderCriterionParameterDTO tenderCriterionParameterDTO = tenderCriterionParameterMapper.toDto(tenderCriterionParameter);

        restTenderCriterionParameterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCriterionParameterDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderCriterionParameter> tenderCriterionParameterList = tenderCriterionParameterRepository.findAll();
        assertThat(tenderCriterionParameterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkIndentItemIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderCriterionParameterRepository.findAll().size();
        // set the field null
        tenderCriterionParameter.setIndentItemId(null);

        // Create the TenderCriterionParameter, which fails.
        TenderCriterionParameterDTO tenderCriterionParameterDTO = tenderCriterionParameterMapper.toDto(tenderCriterionParameter);

        restTenderCriterionParameterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCriterionParameterDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderCriterionParameter> tenderCriterionParameterList = tenderCriterionParameterRepository.findAll();
        assertThat(tenderCriterionParameterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTenderCriterionIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderCriterionParameterRepository.findAll().size();
        // set the field null
        tenderCriterionParameter.setTenderCriterionId(null);

        // Create the TenderCriterionParameter, which fails.
        TenderCriterionParameterDTO tenderCriterionParameterDTO = tenderCriterionParameterMapper.toDto(tenderCriterionParameter);

        restTenderCriterionParameterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCriterionParameterDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderCriterionParameter> tenderCriterionParameterList = tenderCriterionParameterRepository.findAll();
        assertThat(tenderCriterionParameterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderCriterionParameterRepository.findAll().size();
        // set the field null
        tenderCriterionParameter.setName(null);

        // Create the TenderCriterionParameter, which fails.
        TenderCriterionParameterDTO tenderCriterionParameterDTO = tenderCriterionParameterMapper.toDto(tenderCriterionParameter);

        restTenderCriterionParameterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCriterionParameterDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderCriterionParameter> tenderCriterionParameterList = tenderCriterionParameterRepository.findAll();
        assertThat(tenderCriterionParameterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderCriterionParameterRepository.findAll().size();
        // set the field null
        tenderCriterionParameter.setDescription(null);

        // Create the TenderCriterionParameter, which fails.
        TenderCriterionParameterDTO tenderCriterionParameterDTO = tenderCriterionParameterMapper.toDto(tenderCriterionParameter);

        restTenderCriterionParameterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCriterionParameterDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderCriterionParameter> tenderCriterionParameterList = tenderCriterionParameterRepository.findAll();
        assertThat(tenderCriterionParameterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkMinValueIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderCriterionParameterRepository.findAll().size();
        // set the field null
        tenderCriterionParameter.setMinValue(null);

        // Create the TenderCriterionParameter, which fails.
        TenderCriterionParameterDTO tenderCriterionParameterDTO = tenderCriterionParameterMapper.toDto(tenderCriterionParameter);

        restTenderCriterionParameterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCriterionParameterDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderCriterionParameter> tenderCriterionParameterList = tenderCriterionParameterRepository.findAll();
        assertThat(tenderCriterionParameterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkMaxValueIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderCriterionParameterRepository.findAll().size();
        // set the field null
        tenderCriterionParameter.setMaxValue(null);

        // Create the TenderCriterionParameter, which fails.
        TenderCriterionParameterDTO tenderCriterionParameterDTO = tenderCriterionParameterMapper.toDto(tenderCriterionParameter);

        restTenderCriterionParameterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCriterionParameterDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderCriterionParameter> tenderCriterionParameterList = tenderCriterionParameterRepository.findAll();
        assertThat(tenderCriterionParameterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkOperatorIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderCriterionParameterRepository.findAll().size();
        // set the field null
        tenderCriterionParameter.setOperator(null);

        // Create the TenderCriterionParameter, which fails.
        TenderCriterionParameterDTO tenderCriterionParameterDTO = tenderCriterionParameterMapper.toDto(tenderCriterionParameter);

        restTenderCriterionParameterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCriterionParameterDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderCriterionParameter> tenderCriterionParameterList = tenderCriterionParameterRepository.findAll();
        assertThat(tenderCriterionParameterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDataTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderCriterionParameterRepository.findAll().size();
        // set the field null
        tenderCriterionParameter.setDataType(null);

        // Create the TenderCriterionParameter, which fails.
        TenderCriterionParameterDTO tenderCriterionParameterDTO = tenderCriterionParameterMapper.toDto(tenderCriterionParameter);

        restTenderCriterionParameterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCriterionParameterDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderCriterionParameter> tenderCriterionParameterList = tenderCriterionParameterRepository.findAll();
        assertThat(tenderCriterionParameterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkOptionalYnIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderCriterionParameterRepository.findAll().size();
        // set the field null
        tenderCriterionParameter.setOptionalYn(null);

        // Create the TenderCriterionParameter, which fails.
        TenderCriterionParameterDTO tenderCriterionParameterDTO = tenderCriterionParameterMapper.toDto(tenderCriterionParameter);

        restTenderCriterionParameterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCriterionParameterDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderCriterionParameter> tenderCriterionParameterList = tenderCriterionParameterRepository.findAll();
        assertThat(tenderCriterionParameterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTenderCriterionParameters() throws Exception {
        // Initialize the database
        tenderCriterionParameterRepository.saveAndFlush(tenderCriterionParameter);

        // Get all the tenderCriterionParameterList
        restTenderCriterionParameterMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tenderCriterionParameter.getId().intValue())))
            .andExpect(jsonPath("$.[*].nitId").value(hasItem(DEFAULT_NIT_ID.intValue())))
            .andExpect(jsonPath("$.[*].indentItemId").value(hasItem(DEFAULT_INDENT_ITEM_ID.intValue())))
            .andExpect(jsonPath("$.[*].tenderCriterionId").value(hasItem(DEFAULT_TENDER_CRITERION_ID.intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].minValue").value(hasItem(sameNumber(DEFAULT_MIN_VALUE))))
            .andExpect(jsonPath("$.[*].maxValue").value(hasItem(sameNumber(DEFAULT_MAX_VALUE))))
            .andExpect(jsonPath("$.[*].operator").value(hasItem(DEFAULT_OPERATOR)))
            .andExpect(jsonPath("$.[*].dataType").value(hasItem(DEFAULT_DATA_TYPE)))
            .andExpect(jsonPath("$.[*].optionalYn").value(hasItem(DEFAULT_OPTIONAL_YN.booleanValue())));
    }

    @Test
    @Transactional
    void getTenderCriterionParameter() throws Exception {
        // Initialize the database
        tenderCriterionParameterRepository.saveAndFlush(tenderCriterionParameter);

        // Get the tenderCriterionParameter
        restTenderCriterionParameterMockMvc
            .perform(get(ENTITY_API_URL_ID, tenderCriterionParameter.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tenderCriterionParameter.getId().intValue()))
            .andExpect(jsonPath("$.nitId").value(DEFAULT_NIT_ID.intValue()))
            .andExpect(jsonPath("$.indentItemId").value(DEFAULT_INDENT_ITEM_ID.intValue()))
            .andExpect(jsonPath("$.tenderCriterionId").value(DEFAULT_TENDER_CRITERION_ID.intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.minValue").value(sameNumber(DEFAULT_MIN_VALUE)))
            .andExpect(jsonPath("$.maxValue").value(sameNumber(DEFAULT_MAX_VALUE)))
            .andExpect(jsonPath("$.operator").value(DEFAULT_OPERATOR))
            .andExpect(jsonPath("$.dataType").value(DEFAULT_DATA_TYPE))
            .andExpect(jsonPath("$.optionalYn").value(DEFAULT_OPTIONAL_YN.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingTenderCriterionParameter() throws Exception {
        // Get the tenderCriterionParameter
        restTenderCriterionParameterMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTenderCriterionParameter() throws Exception {
        // Initialize the database
        tenderCriterionParameterRepository.saveAndFlush(tenderCriterionParameter);

        int databaseSizeBeforeUpdate = tenderCriterionParameterRepository.findAll().size();

        // Update the tenderCriterionParameter
        TenderCriterionParameter updatedTenderCriterionParameter = tenderCriterionParameterRepository
            .findById(tenderCriterionParameter.getId())
            .get();
        // Disconnect from session so that the updates on updatedTenderCriterionParameter are not directly saved in db
        em.detach(updatedTenderCriterionParameter);
        updatedTenderCriterionParameter
            .nitId(UPDATED_NIT_ID)
            .indentItemId(UPDATED_INDENT_ITEM_ID)
            .tenderCriterionId(UPDATED_TENDER_CRITERION_ID)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .minValue(UPDATED_MIN_VALUE)
            .maxValue(UPDATED_MAX_VALUE)
            .operator(UPDATED_OPERATOR)
            .dataType(UPDATED_DATA_TYPE)
            .optionalYn(UPDATED_OPTIONAL_YN);
        TenderCriterionParameterDTO tenderCriterionParameterDTO = tenderCriterionParameterMapper.toDto(updatedTenderCriterionParameter);

        restTenderCriterionParameterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tenderCriterionParameterDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCriterionParameterDTO))
            )
            .andExpect(status().isOk());

        // Validate the TenderCriterionParameter in the database
        List<TenderCriterionParameter> tenderCriterionParameterList = tenderCriterionParameterRepository.findAll();
        assertThat(tenderCriterionParameterList).hasSize(databaseSizeBeforeUpdate);
        TenderCriterionParameter testTenderCriterionParameter = tenderCriterionParameterList.get(tenderCriterionParameterList.size() - 1);
        assertThat(testTenderCriterionParameter.getNitId()).isEqualTo(UPDATED_NIT_ID);
        assertThat(testTenderCriterionParameter.getIndentItemId()).isEqualTo(UPDATED_INDENT_ITEM_ID);
        assertThat(testTenderCriterionParameter.getTenderCriterionId()).isEqualTo(UPDATED_TENDER_CRITERION_ID);
        assertThat(testTenderCriterionParameter.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testTenderCriterionParameter.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testTenderCriterionParameter.getMinValue()).isEqualTo(UPDATED_MIN_VALUE);
        assertThat(testTenderCriterionParameter.getMaxValue()).isEqualTo(UPDATED_MAX_VALUE);
        assertThat(testTenderCriterionParameter.getOperator()).isEqualTo(UPDATED_OPERATOR);
        assertThat(testTenderCriterionParameter.getDataType()).isEqualTo(UPDATED_DATA_TYPE);
        assertThat(testTenderCriterionParameter.getOptionalYn()).isEqualTo(UPDATED_OPTIONAL_YN);
    }

    @Test
    @Transactional
    void putNonExistingTenderCriterionParameter() throws Exception {
        int databaseSizeBeforeUpdate = tenderCriterionParameterRepository.findAll().size();
        tenderCriterionParameter.setId(count.incrementAndGet());

        // Create the TenderCriterionParameter
        TenderCriterionParameterDTO tenderCriterionParameterDTO = tenderCriterionParameterMapper.toDto(tenderCriterionParameter);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTenderCriterionParameterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tenderCriterionParameterDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCriterionParameterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderCriterionParameter in the database
        List<TenderCriterionParameter> tenderCriterionParameterList = tenderCriterionParameterRepository.findAll();
        assertThat(tenderCriterionParameterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTenderCriterionParameter() throws Exception {
        int databaseSizeBeforeUpdate = tenderCriterionParameterRepository.findAll().size();
        tenderCriterionParameter.setId(count.incrementAndGet());

        // Create the TenderCriterionParameter
        TenderCriterionParameterDTO tenderCriterionParameterDTO = tenderCriterionParameterMapper.toDto(tenderCriterionParameter);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderCriterionParameterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCriterionParameterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderCriterionParameter in the database
        List<TenderCriterionParameter> tenderCriterionParameterList = tenderCriterionParameterRepository.findAll();
        assertThat(tenderCriterionParameterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTenderCriterionParameter() throws Exception {
        int databaseSizeBeforeUpdate = tenderCriterionParameterRepository.findAll().size();
        tenderCriterionParameter.setId(count.incrementAndGet());

        // Create the TenderCriterionParameter
        TenderCriterionParameterDTO tenderCriterionParameterDTO = tenderCriterionParameterMapper.toDto(tenderCriterionParameter);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderCriterionParameterMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCriterionParameterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TenderCriterionParameter in the database
        List<TenderCriterionParameter> tenderCriterionParameterList = tenderCriterionParameterRepository.findAll();
        assertThat(tenderCriterionParameterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTenderCriterionParameterWithPatch() throws Exception {
        // Initialize the database
        tenderCriterionParameterRepository.saveAndFlush(tenderCriterionParameter);

        int databaseSizeBeforeUpdate = tenderCriterionParameterRepository.findAll().size();

        // Update the tenderCriterionParameter using partial update
        TenderCriterionParameter partialUpdatedTenderCriterionParameter = new TenderCriterionParameter();
        partialUpdatedTenderCriterionParameter.setId(tenderCriterionParameter.getId());

        partialUpdatedTenderCriterionParameter
            .nitId(UPDATED_NIT_ID)
            .indentItemId(UPDATED_INDENT_ITEM_ID)
            .tenderCriterionId(UPDATED_TENDER_CRITERION_ID)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .maxValue(UPDATED_MAX_VALUE);

        restTenderCriterionParameterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTenderCriterionParameter.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTenderCriterionParameter))
            )
            .andExpect(status().isOk());

        // Validate the TenderCriterionParameter in the database
        List<TenderCriterionParameter> tenderCriterionParameterList = tenderCriterionParameterRepository.findAll();
        assertThat(tenderCriterionParameterList).hasSize(databaseSizeBeforeUpdate);
        TenderCriterionParameter testTenderCriterionParameter = tenderCriterionParameterList.get(tenderCriterionParameterList.size() - 1);
        assertThat(testTenderCriterionParameter.getNitId()).isEqualTo(UPDATED_NIT_ID);
        assertThat(testTenderCriterionParameter.getIndentItemId()).isEqualTo(UPDATED_INDENT_ITEM_ID);
        assertThat(testTenderCriterionParameter.getTenderCriterionId()).isEqualTo(UPDATED_TENDER_CRITERION_ID);
        assertThat(testTenderCriterionParameter.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testTenderCriterionParameter.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testTenderCriterionParameter.getMinValue()).isEqualByComparingTo(DEFAULT_MIN_VALUE);
        assertThat(testTenderCriterionParameter.getMaxValue()).isEqualByComparingTo(UPDATED_MAX_VALUE);
        assertThat(testTenderCriterionParameter.getOperator()).isEqualTo(DEFAULT_OPERATOR);
        assertThat(testTenderCriterionParameter.getDataType()).isEqualTo(DEFAULT_DATA_TYPE);
        assertThat(testTenderCriterionParameter.getOptionalYn()).isEqualTo(DEFAULT_OPTIONAL_YN);
    }

    @Test
    @Transactional
    void fullUpdateTenderCriterionParameterWithPatch() throws Exception {
        // Initialize the database
        tenderCriterionParameterRepository.saveAndFlush(tenderCriterionParameter);

        int databaseSizeBeforeUpdate = tenderCriterionParameterRepository.findAll().size();

        // Update the tenderCriterionParameter using partial update
        TenderCriterionParameter partialUpdatedTenderCriterionParameter = new TenderCriterionParameter();
        partialUpdatedTenderCriterionParameter.setId(tenderCriterionParameter.getId());

        partialUpdatedTenderCriterionParameter
            .nitId(UPDATED_NIT_ID)
            .indentItemId(UPDATED_INDENT_ITEM_ID)
            .tenderCriterionId(UPDATED_TENDER_CRITERION_ID)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .minValue(UPDATED_MIN_VALUE)
            .maxValue(UPDATED_MAX_VALUE)
            .operator(UPDATED_OPERATOR)
            .dataType(UPDATED_DATA_TYPE)
            .optionalYn(UPDATED_OPTIONAL_YN);

        restTenderCriterionParameterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTenderCriterionParameter.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTenderCriterionParameter))
            )
            .andExpect(status().isOk());

        // Validate the TenderCriterionParameter in the database
        List<TenderCriterionParameter> tenderCriterionParameterList = tenderCriterionParameterRepository.findAll();
        assertThat(tenderCriterionParameterList).hasSize(databaseSizeBeforeUpdate);
        TenderCriterionParameter testTenderCriterionParameter = tenderCriterionParameterList.get(tenderCriterionParameterList.size() - 1);
        assertThat(testTenderCriterionParameter.getNitId()).isEqualTo(UPDATED_NIT_ID);
        assertThat(testTenderCriterionParameter.getIndentItemId()).isEqualTo(UPDATED_INDENT_ITEM_ID);
        assertThat(testTenderCriterionParameter.getTenderCriterionId()).isEqualTo(UPDATED_TENDER_CRITERION_ID);
        assertThat(testTenderCriterionParameter.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testTenderCriterionParameter.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testTenderCriterionParameter.getMinValue()).isEqualByComparingTo(UPDATED_MIN_VALUE);
        assertThat(testTenderCriterionParameter.getMaxValue()).isEqualByComparingTo(UPDATED_MAX_VALUE);
        assertThat(testTenderCriterionParameter.getOperator()).isEqualTo(UPDATED_OPERATOR);
        assertThat(testTenderCriterionParameter.getDataType()).isEqualTo(UPDATED_DATA_TYPE);
        assertThat(testTenderCriterionParameter.getOptionalYn()).isEqualTo(UPDATED_OPTIONAL_YN);
    }

    @Test
    @Transactional
    void patchNonExistingTenderCriterionParameter() throws Exception {
        int databaseSizeBeforeUpdate = tenderCriterionParameterRepository.findAll().size();
        tenderCriterionParameter.setId(count.incrementAndGet());

        // Create the TenderCriterionParameter
        TenderCriterionParameterDTO tenderCriterionParameterDTO = tenderCriterionParameterMapper.toDto(tenderCriterionParameter);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTenderCriterionParameterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tenderCriterionParameterDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderCriterionParameterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderCriterionParameter in the database
        List<TenderCriterionParameter> tenderCriterionParameterList = tenderCriterionParameterRepository.findAll();
        assertThat(tenderCriterionParameterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTenderCriterionParameter() throws Exception {
        int databaseSizeBeforeUpdate = tenderCriterionParameterRepository.findAll().size();
        tenderCriterionParameter.setId(count.incrementAndGet());

        // Create the TenderCriterionParameter
        TenderCriterionParameterDTO tenderCriterionParameterDTO = tenderCriterionParameterMapper.toDto(tenderCriterionParameter);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderCriterionParameterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderCriterionParameterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderCriterionParameter in the database
        List<TenderCriterionParameter> tenderCriterionParameterList = tenderCriterionParameterRepository.findAll();
        assertThat(tenderCriterionParameterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTenderCriterionParameter() throws Exception {
        int databaseSizeBeforeUpdate = tenderCriterionParameterRepository.findAll().size();
        tenderCriterionParameter.setId(count.incrementAndGet());

        // Create the TenderCriterionParameter
        TenderCriterionParameterDTO tenderCriterionParameterDTO = tenderCriterionParameterMapper.toDto(tenderCriterionParameter);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderCriterionParameterMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderCriterionParameterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TenderCriterionParameter in the database
        List<TenderCriterionParameter> tenderCriterionParameterList = tenderCriterionParameterRepository.findAll();
        assertThat(tenderCriterionParameterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTenderCriterionParameter() throws Exception {
        // Initialize the database
        tenderCriterionParameterRepository.saveAndFlush(tenderCriterionParameter);

        int databaseSizeBeforeDelete = tenderCriterionParameterRepository.findAll().size();

        // Delete the tenderCriterionParameter
        restTenderCriterionParameterMockMvc
            .perform(delete(ENTITY_API_URL_ID, tenderCriterionParameter.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TenderCriterionParameter> tenderCriterionParameterList = tenderCriterionParameterRepository.findAll();
        assertThat(tenderCriterionParameterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
