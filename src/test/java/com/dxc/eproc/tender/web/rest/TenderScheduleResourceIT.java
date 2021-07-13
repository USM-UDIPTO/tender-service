package com.dxc.eproc.tender.web.rest;

import static com.dxc.eproc.tender.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dxc.eproc.tender.IntegrationTest;
import com.dxc.eproc.tender.domain.TenderSchedule;
import com.dxc.eproc.tender.repository.TenderScheduleRepository;
import com.dxc.eproc.tender.service.dto.TenderScheduleDTO;
import com.dxc.eproc.tender.service.mapper.TenderScheduleMapper;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
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
 * Integration tests for the {@link TenderScheduleResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TenderScheduleResourceIT {

    private static final Long DEFAULT_NIT_ID = 1L;
    private static final Long UPDATED_NIT_ID = 2L;

    private static final String DEFAULT_TENDER_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_TENDER_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_CATEGORY = 1;
    private static final Integer UPDATED_CATEGORY = 2;

    private static final BigDecimal DEFAULT_ECV = new BigDecimal(1);
    private static final BigDecimal UPDATED_ECV = new BigDecimal(2);

    private static final Long DEFAULT_INDENT_ID = 1L;
    private static final Long UPDATED_INDENT_ID = 2L;

    private static final Long DEFAULT_DEPT_ID = 1L;
    private static final Long UPDATED_DEPT_ID = 2L;

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

    private static final Integer DEFAULT_PARENT_TENDER_REF = 1;
    private static final Integer UPDATED_PARENT_TENDER_REF = 2;

    private static final Integer DEFAULT_NO_OF_CALLS = 1;
    private static final Integer UPDATED_NO_OF_CALLS = 2;

    private static final Integer DEFAULT_PROCESS_ID = 1;
    private static final Integer UPDATED_PROCESS_ID = 2;

    private static final BigDecimal DEFAULT_CSR_VALUE = new BigDecimal(1);
    private static final BigDecimal UPDATED_CSR_VALUE = new BigDecimal(2);

    private static final Boolean DEFAULT_ECVTENDERY_YN = false;
    private static final Boolean UPDATED_ECVTENDERY_YN = true;

    private static final Integer DEFAULT_CERTIFICATE_ID = 1;
    private static final Integer UPDATED_CERTIFICATE_ID = 2;

    private static final Integer DEFAULT_PAYMENTS_VERIFIED = 1;
    private static final Integer UPDATED_PAYMENTS_VERIFIED = 2;

    private static final LocalDate DEFAULT_DTS_APPROVAL_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DTS_APPROVAL_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_MANDATORY_CLAUSE = "AAAAAAAAAA";
    private static final String UPDATED_MANDATORY_CLAUSE = "BBBBBBBBBB";

    private static final Integer DEFAULT_LOCATION = 1;
    private static final Integer UPDATED_LOCATION = 2;

    private static final Integer DEFAULT_DELEGATE_TO = 1;
    private static final Integer UPDATED_DELEGATE_TO = 2;

    private static final Integer DEFAULT_IS_APPROVED_BY_SELF = 1;
    private static final Integer UPDATED_IS_APPROVED_BY_SELF = 2;

    private static final String DEFAULT_CAT_WORK_CATEGORY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CAT_WORK_CATEGORY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_NEGOTIATION_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_NEGOTIATION_STATUS = "BBBBBBBBBB";

    private static final Boolean DEFAULT_MANUAL_TENDER_YN = false;
    private static final Boolean UPDATED_MANUAL_TENDER_YN = true;

    private static final Long DEFAULT_DISTRICT_ID = 1L;
    private static final Long UPDATED_DISTRICT_ID = 2L;

    private static final String DEFAULT_DRAFT_PUBLISH_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_DRAFT_PUBLISH_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_CSR_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_CSR_REMARKS = "BBBBBBBBBB";

    private static final Integer DEFAULT_PROC_ENTITY_TYPE = 1;
    private static final Integer UPDATED_PROC_ENTITY_TYPE = 2;

    private static final String DEFAULT_DTS_APPROVED_BY = "AAAAAAAAAA";
    private static final String UPDATED_DTS_APPROVED_BY = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/tender-schedules";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TenderScheduleRepository tenderScheduleRepository;

    @Autowired
    private TenderScheduleMapper tenderScheduleMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTenderScheduleMockMvc;

    private TenderSchedule tenderSchedule;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TenderSchedule createEntity(EntityManager em) {
        TenderSchedule tenderSchedule = new TenderSchedule()
            .nitId(DEFAULT_NIT_ID)
            .tenderNumber(DEFAULT_TENDER_NUMBER)
            .title(DEFAULT_TITLE)
            .description(DEFAULT_DESCRIPTION)
            .category(DEFAULT_CATEGORY)
            .ecv(DEFAULT_ECV)
            .indentId(DEFAULT_INDENT_ID)
            .deptId(DEFAULT_DEPT_ID)
            .status(DEFAULT_STATUS)
            .remarks(DEFAULT_REMARKS)
            .parentTenderRef(DEFAULT_PARENT_TENDER_REF)
            .noOfCalls(DEFAULT_NO_OF_CALLS)
            .processId(DEFAULT_PROCESS_ID)
            .csrValue(DEFAULT_CSR_VALUE)
            .ecvtenderyYn(DEFAULT_ECVTENDERY_YN)
            .certificateId(DEFAULT_CERTIFICATE_ID)
            .paymentsVerified(DEFAULT_PAYMENTS_VERIFIED)
            .dtsApprovalDate(DEFAULT_DTS_APPROVAL_DATE)
            .mandatoryClause(DEFAULT_MANDATORY_CLAUSE)
            .location(DEFAULT_LOCATION)
            .delegateTo(DEFAULT_DELEGATE_TO)
            .isApprovedBySelf(DEFAULT_IS_APPROVED_BY_SELF)
            .catWorkCategoryName(DEFAULT_CAT_WORK_CATEGORY_NAME)
            .negotiationStatus(DEFAULT_NEGOTIATION_STATUS)
            .manualTenderYn(DEFAULT_MANUAL_TENDER_YN)
            .districtId(DEFAULT_DISTRICT_ID)
            .draftPublishStatus(DEFAULT_DRAFT_PUBLISH_STATUS)
            .csrRemarks(DEFAULT_CSR_REMARKS)
            .procEntityType(DEFAULT_PROC_ENTITY_TYPE)
            .dtsApprovedBy(DEFAULT_DTS_APPROVED_BY);
        return tenderSchedule;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TenderSchedule createUpdatedEntity(EntityManager em) {
        TenderSchedule tenderSchedule = new TenderSchedule()
            .nitId(UPDATED_NIT_ID)
            .tenderNumber(UPDATED_TENDER_NUMBER)
            .title(UPDATED_TITLE)
            .description(UPDATED_DESCRIPTION)
            .category(UPDATED_CATEGORY)
            .ecv(UPDATED_ECV)
            .indentId(UPDATED_INDENT_ID)
            .deptId(UPDATED_DEPT_ID)
            .status(UPDATED_STATUS)
            .remarks(UPDATED_REMARKS)
            .parentTenderRef(UPDATED_PARENT_TENDER_REF)
            .noOfCalls(UPDATED_NO_OF_CALLS)
            .processId(UPDATED_PROCESS_ID)
            .csrValue(UPDATED_CSR_VALUE)
            .ecvtenderyYn(UPDATED_ECVTENDERY_YN)
            .certificateId(UPDATED_CERTIFICATE_ID)
            .paymentsVerified(UPDATED_PAYMENTS_VERIFIED)
            .dtsApprovalDate(UPDATED_DTS_APPROVAL_DATE)
            .mandatoryClause(UPDATED_MANDATORY_CLAUSE)
            .location(UPDATED_LOCATION)
            .delegateTo(UPDATED_DELEGATE_TO)
            .isApprovedBySelf(UPDATED_IS_APPROVED_BY_SELF)
            .catWorkCategoryName(UPDATED_CAT_WORK_CATEGORY_NAME)
            .negotiationStatus(UPDATED_NEGOTIATION_STATUS)
            .manualTenderYn(UPDATED_MANUAL_TENDER_YN)
            .districtId(UPDATED_DISTRICT_ID)
            .draftPublishStatus(UPDATED_DRAFT_PUBLISH_STATUS)
            .csrRemarks(UPDATED_CSR_REMARKS)
            .procEntityType(UPDATED_PROC_ENTITY_TYPE)
            .dtsApprovedBy(UPDATED_DTS_APPROVED_BY);
        return tenderSchedule;
    }

    @BeforeEach
    public void initTest() {
        tenderSchedule = createEntity(em);
    }

    @Test
    @Transactional
    void createTenderSchedule() throws Exception {
        int databaseSizeBeforeCreate = tenderScheduleRepository.findAll().size();
        // Create the TenderSchedule
        TenderScheduleDTO tenderScheduleDTO = tenderScheduleMapper.toDto(tenderSchedule);
        restTenderScheduleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tenderScheduleDTO))
            )
            .andExpect(status().isCreated());

        // Validate the TenderSchedule in the database
        List<TenderSchedule> tenderScheduleList = tenderScheduleRepository.findAll();
        assertThat(tenderScheduleList).hasSize(databaseSizeBeforeCreate + 1);
        TenderSchedule testTenderSchedule = tenderScheduleList.get(tenderScheduleList.size() - 1);
        assertThat(testTenderSchedule.getNitId()).isEqualTo(DEFAULT_NIT_ID);
        assertThat(testTenderSchedule.getTenderNumber()).isEqualTo(DEFAULT_TENDER_NUMBER);
        assertThat(testTenderSchedule.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testTenderSchedule.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testTenderSchedule.getCategory()).isEqualTo(DEFAULT_CATEGORY);
        assertThat(testTenderSchedule.getEcv()).isEqualByComparingTo(DEFAULT_ECV);
        assertThat(testTenderSchedule.getIndentId()).isEqualTo(DEFAULT_INDENT_ID);
        assertThat(testTenderSchedule.getDeptId()).isEqualTo(DEFAULT_DEPT_ID);
        assertThat(testTenderSchedule.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testTenderSchedule.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testTenderSchedule.getParentTenderRef()).isEqualTo(DEFAULT_PARENT_TENDER_REF);
        assertThat(testTenderSchedule.getNoOfCalls()).isEqualTo(DEFAULT_NO_OF_CALLS);
        assertThat(testTenderSchedule.getProcessId()).isEqualTo(DEFAULT_PROCESS_ID);
        assertThat(testTenderSchedule.getCsrValue()).isEqualByComparingTo(DEFAULT_CSR_VALUE);
        assertThat(testTenderSchedule.getEcvtenderyYn()).isEqualTo(DEFAULT_ECVTENDERY_YN);
        assertThat(testTenderSchedule.getCertificateId()).isEqualTo(DEFAULT_CERTIFICATE_ID);
        assertThat(testTenderSchedule.getPaymentsVerified()).isEqualTo(DEFAULT_PAYMENTS_VERIFIED);
        assertThat(testTenderSchedule.getDtsApprovalDate()).isEqualTo(DEFAULT_DTS_APPROVAL_DATE);
        assertThat(testTenderSchedule.getMandatoryClause()).isEqualTo(DEFAULT_MANDATORY_CLAUSE);
        assertThat(testTenderSchedule.getLocation()).isEqualTo(DEFAULT_LOCATION);
        assertThat(testTenderSchedule.getDelegateTo()).isEqualTo(DEFAULT_DELEGATE_TO);
        assertThat(testTenderSchedule.getIsApprovedBySelf()).isEqualTo(DEFAULT_IS_APPROVED_BY_SELF);
        assertThat(testTenderSchedule.getCatWorkCategoryName()).isEqualTo(DEFAULT_CAT_WORK_CATEGORY_NAME);
        assertThat(testTenderSchedule.getNegotiationStatus()).isEqualTo(DEFAULT_NEGOTIATION_STATUS);
        assertThat(testTenderSchedule.getManualTenderYn()).isEqualTo(DEFAULT_MANUAL_TENDER_YN);
        assertThat(testTenderSchedule.getDistrictId()).isEqualTo(DEFAULT_DISTRICT_ID);
        assertThat(testTenderSchedule.getDraftPublishStatus()).isEqualTo(DEFAULT_DRAFT_PUBLISH_STATUS);
        assertThat(testTenderSchedule.getCsrRemarks()).isEqualTo(DEFAULT_CSR_REMARKS);
        assertThat(testTenderSchedule.getProcEntityType()).isEqualTo(DEFAULT_PROC_ENTITY_TYPE);
        assertThat(testTenderSchedule.getDtsApprovedBy()).isEqualTo(DEFAULT_DTS_APPROVED_BY);
    }

    @Test
    @Transactional
    void createTenderScheduleWithExistingId() throws Exception {
        // Create the TenderSchedule with an existing ID
        tenderSchedule.setId(1L);
        TenderScheduleDTO tenderScheduleDTO = tenderScheduleMapper.toDto(tenderSchedule);

        int databaseSizeBeforeCreate = tenderScheduleRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTenderScheduleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tenderScheduleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderSchedule in the database
        List<TenderSchedule> tenderScheduleList = tenderScheduleRepository.findAll();
        assertThat(tenderScheduleList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNitIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderScheduleRepository.findAll().size();
        // set the field null
        tenderSchedule.setNitId(null);

        // Create the TenderSchedule, which fails.
        TenderScheduleDTO tenderScheduleDTO = tenderScheduleMapper.toDto(tenderSchedule);

        restTenderScheduleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tenderScheduleDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderSchedule> tenderScheduleList = tenderScheduleRepository.findAll();
        assertThat(tenderScheduleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTenderNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderScheduleRepository.findAll().size();
        // set the field null
        tenderSchedule.setTenderNumber(null);

        // Create the TenderSchedule, which fails.
        TenderScheduleDTO tenderScheduleDTO = tenderScheduleMapper.toDto(tenderSchedule);

        restTenderScheduleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tenderScheduleDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderSchedule> tenderScheduleList = tenderScheduleRepository.findAll();
        assertThat(tenderScheduleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderScheduleRepository.findAll().size();
        // set the field null
        tenderSchedule.setTitle(null);

        // Create the TenderSchedule, which fails.
        TenderScheduleDTO tenderScheduleDTO = tenderScheduleMapper.toDto(tenderSchedule);

        restTenderScheduleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tenderScheduleDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderSchedule> tenderScheduleList = tenderScheduleRepository.findAll();
        assertThat(tenderScheduleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderScheduleRepository.findAll().size();
        // set the field null
        tenderSchedule.setDescription(null);

        // Create the TenderSchedule, which fails.
        TenderScheduleDTO tenderScheduleDTO = tenderScheduleMapper.toDto(tenderSchedule);

        restTenderScheduleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tenderScheduleDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderSchedule> tenderScheduleList = tenderScheduleRepository.findAll();
        assertThat(tenderScheduleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCategoryIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderScheduleRepository.findAll().size();
        // set the field null
        tenderSchedule.setCategory(null);

        // Create the TenderSchedule, which fails.
        TenderScheduleDTO tenderScheduleDTO = tenderScheduleMapper.toDto(tenderSchedule);

        restTenderScheduleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tenderScheduleDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderSchedule> tenderScheduleList = tenderScheduleRepository.findAll();
        assertThat(tenderScheduleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEcvIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderScheduleRepository.findAll().size();
        // set the field null
        tenderSchedule.setEcv(null);

        // Create the TenderSchedule, which fails.
        TenderScheduleDTO tenderScheduleDTO = tenderScheduleMapper.toDto(tenderSchedule);

        restTenderScheduleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tenderScheduleDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderSchedule> tenderScheduleList = tenderScheduleRepository.findAll();
        assertThat(tenderScheduleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkIndentIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderScheduleRepository.findAll().size();
        // set the field null
        tenderSchedule.setIndentId(null);

        // Create the TenderSchedule, which fails.
        TenderScheduleDTO tenderScheduleDTO = tenderScheduleMapper.toDto(tenderSchedule);

        restTenderScheduleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tenderScheduleDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderSchedule> tenderScheduleList = tenderScheduleRepository.findAll();
        assertThat(tenderScheduleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDeptIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderScheduleRepository.findAll().size();
        // set the field null
        tenderSchedule.setDeptId(null);

        // Create the TenderSchedule, which fails.
        TenderScheduleDTO tenderScheduleDTO = tenderScheduleMapper.toDto(tenderSchedule);

        restTenderScheduleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tenderScheduleDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderSchedule> tenderScheduleList = tenderScheduleRepository.findAll();
        assertThat(tenderScheduleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderScheduleRepository.findAll().size();
        // set the field null
        tenderSchedule.setStatus(null);

        // Create the TenderSchedule, which fails.
        TenderScheduleDTO tenderScheduleDTO = tenderScheduleMapper.toDto(tenderSchedule);

        restTenderScheduleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tenderScheduleDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderSchedule> tenderScheduleList = tenderScheduleRepository.findAll();
        assertThat(tenderScheduleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkRemarksIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderScheduleRepository.findAll().size();
        // set the field null
        tenderSchedule.setRemarks(null);

        // Create the TenderSchedule, which fails.
        TenderScheduleDTO tenderScheduleDTO = tenderScheduleMapper.toDto(tenderSchedule);

        restTenderScheduleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tenderScheduleDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderSchedule> tenderScheduleList = tenderScheduleRepository.findAll();
        assertThat(tenderScheduleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkParentTenderRefIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderScheduleRepository.findAll().size();
        // set the field null
        tenderSchedule.setParentTenderRef(null);

        // Create the TenderSchedule, which fails.
        TenderScheduleDTO tenderScheduleDTO = tenderScheduleMapper.toDto(tenderSchedule);

        restTenderScheduleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tenderScheduleDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderSchedule> tenderScheduleList = tenderScheduleRepository.findAll();
        assertThat(tenderScheduleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNoOfCallsIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderScheduleRepository.findAll().size();
        // set the field null
        tenderSchedule.setNoOfCalls(null);

        // Create the TenderSchedule, which fails.
        TenderScheduleDTO tenderScheduleDTO = tenderScheduleMapper.toDto(tenderSchedule);

        restTenderScheduleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tenderScheduleDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderSchedule> tenderScheduleList = tenderScheduleRepository.findAll();
        assertThat(tenderScheduleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkProcessIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderScheduleRepository.findAll().size();
        // set the field null
        tenderSchedule.setProcessId(null);

        // Create the TenderSchedule, which fails.
        TenderScheduleDTO tenderScheduleDTO = tenderScheduleMapper.toDto(tenderSchedule);

        restTenderScheduleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tenderScheduleDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderSchedule> tenderScheduleList = tenderScheduleRepository.findAll();
        assertThat(tenderScheduleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCsrValueIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderScheduleRepository.findAll().size();
        // set the field null
        tenderSchedule.setCsrValue(null);

        // Create the TenderSchedule, which fails.
        TenderScheduleDTO tenderScheduleDTO = tenderScheduleMapper.toDto(tenderSchedule);

        restTenderScheduleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tenderScheduleDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderSchedule> tenderScheduleList = tenderScheduleRepository.findAll();
        assertThat(tenderScheduleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEcvtenderyYnIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderScheduleRepository.findAll().size();
        // set the field null
        tenderSchedule.setEcvtenderyYn(null);

        // Create the TenderSchedule, which fails.
        TenderScheduleDTO tenderScheduleDTO = tenderScheduleMapper.toDto(tenderSchedule);

        restTenderScheduleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tenderScheduleDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderSchedule> tenderScheduleList = tenderScheduleRepository.findAll();
        assertThat(tenderScheduleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCertificateIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderScheduleRepository.findAll().size();
        // set the field null
        tenderSchedule.setCertificateId(null);

        // Create the TenderSchedule, which fails.
        TenderScheduleDTO tenderScheduleDTO = tenderScheduleMapper.toDto(tenderSchedule);

        restTenderScheduleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tenderScheduleDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderSchedule> tenderScheduleList = tenderScheduleRepository.findAll();
        assertThat(tenderScheduleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPaymentsVerifiedIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderScheduleRepository.findAll().size();
        // set the field null
        tenderSchedule.setPaymentsVerified(null);

        // Create the TenderSchedule, which fails.
        TenderScheduleDTO tenderScheduleDTO = tenderScheduleMapper.toDto(tenderSchedule);

        restTenderScheduleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tenderScheduleDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderSchedule> tenderScheduleList = tenderScheduleRepository.findAll();
        assertThat(tenderScheduleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDtsApprovalDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderScheduleRepository.findAll().size();
        // set the field null
        tenderSchedule.setDtsApprovalDate(null);

        // Create the TenderSchedule, which fails.
        TenderScheduleDTO tenderScheduleDTO = tenderScheduleMapper.toDto(tenderSchedule);

        restTenderScheduleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tenderScheduleDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderSchedule> tenderScheduleList = tenderScheduleRepository.findAll();
        assertThat(tenderScheduleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkMandatoryClauseIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderScheduleRepository.findAll().size();
        // set the field null
        tenderSchedule.setMandatoryClause(null);

        // Create the TenderSchedule, which fails.
        TenderScheduleDTO tenderScheduleDTO = tenderScheduleMapper.toDto(tenderSchedule);

        restTenderScheduleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tenderScheduleDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderSchedule> tenderScheduleList = tenderScheduleRepository.findAll();
        assertThat(tenderScheduleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkLocationIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderScheduleRepository.findAll().size();
        // set the field null
        tenderSchedule.setLocation(null);

        // Create the TenderSchedule, which fails.
        TenderScheduleDTO tenderScheduleDTO = tenderScheduleMapper.toDto(tenderSchedule);

        restTenderScheduleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tenderScheduleDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderSchedule> tenderScheduleList = tenderScheduleRepository.findAll();
        assertThat(tenderScheduleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDelegateToIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderScheduleRepository.findAll().size();
        // set the field null
        tenderSchedule.setDelegateTo(null);

        // Create the TenderSchedule, which fails.
        TenderScheduleDTO tenderScheduleDTO = tenderScheduleMapper.toDto(tenderSchedule);

        restTenderScheduleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tenderScheduleDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderSchedule> tenderScheduleList = tenderScheduleRepository.findAll();
        assertThat(tenderScheduleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkIsApprovedBySelfIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderScheduleRepository.findAll().size();
        // set the field null
        tenderSchedule.setIsApprovedBySelf(null);

        // Create the TenderSchedule, which fails.
        TenderScheduleDTO tenderScheduleDTO = tenderScheduleMapper.toDto(tenderSchedule);

        restTenderScheduleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tenderScheduleDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderSchedule> tenderScheduleList = tenderScheduleRepository.findAll();
        assertThat(tenderScheduleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCatWorkCategoryNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderScheduleRepository.findAll().size();
        // set the field null
        tenderSchedule.setCatWorkCategoryName(null);

        // Create the TenderSchedule, which fails.
        TenderScheduleDTO tenderScheduleDTO = tenderScheduleMapper.toDto(tenderSchedule);

        restTenderScheduleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tenderScheduleDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderSchedule> tenderScheduleList = tenderScheduleRepository.findAll();
        assertThat(tenderScheduleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNegotiationStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderScheduleRepository.findAll().size();
        // set the field null
        tenderSchedule.setNegotiationStatus(null);

        // Create the TenderSchedule, which fails.
        TenderScheduleDTO tenderScheduleDTO = tenderScheduleMapper.toDto(tenderSchedule);

        restTenderScheduleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tenderScheduleDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderSchedule> tenderScheduleList = tenderScheduleRepository.findAll();
        assertThat(tenderScheduleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkManualTenderYnIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderScheduleRepository.findAll().size();
        // set the field null
        tenderSchedule.setManualTenderYn(null);

        // Create the TenderSchedule, which fails.
        TenderScheduleDTO tenderScheduleDTO = tenderScheduleMapper.toDto(tenderSchedule);

        restTenderScheduleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tenderScheduleDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderSchedule> tenderScheduleList = tenderScheduleRepository.findAll();
        assertThat(tenderScheduleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDistrictIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderScheduleRepository.findAll().size();
        // set the field null
        tenderSchedule.setDistrictId(null);

        // Create the TenderSchedule, which fails.
        TenderScheduleDTO tenderScheduleDTO = tenderScheduleMapper.toDto(tenderSchedule);

        restTenderScheduleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tenderScheduleDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderSchedule> tenderScheduleList = tenderScheduleRepository.findAll();
        assertThat(tenderScheduleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDraftPublishStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderScheduleRepository.findAll().size();
        // set the field null
        tenderSchedule.setDraftPublishStatus(null);

        // Create the TenderSchedule, which fails.
        TenderScheduleDTO tenderScheduleDTO = tenderScheduleMapper.toDto(tenderSchedule);

        restTenderScheduleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tenderScheduleDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderSchedule> tenderScheduleList = tenderScheduleRepository.findAll();
        assertThat(tenderScheduleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCsrRemarksIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderScheduleRepository.findAll().size();
        // set the field null
        tenderSchedule.setCsrRemarks(null);

        // Create the TenderSchedule, which fails.
        TenderScheduleDTO tenderScheduleDTO = tenderScheduleMapper.toDto(tenderSchedule);

        restTenderScheduleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tenderScheduleDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderSchedule> tenderScheduleList = tenderScheduleRepository.findAll();
        assertThat(tenderScheduleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkProcEntityTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderScheduleRepository.findAll().size();
        // set the field null
        tenderSchedule.setProcEntityType(null);

        // Create the TenderSchedule, which fails.
        TenderScheduleDTO tenderScheduleDTO = tenderScheduleMapper.toDto(tenderSchedule);

        restTenderScheduleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tenderScheduleDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderSchedule> tenderScheduleList = tenderScheduleRepository.findAll();
        assertThat(tenderScheduleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDtsApprovedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderScheduleRepository.findAll().size();
        // set the field null
        tenderSchedule.setDtsApprovedBy(null);

        // Create the TenderSchedule, which fails.
        TenderScheduleDTO tenderScheduleDTO = tenderScheduleMapper.toDto(tenderSchedule);

        restTenderScheduleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tenderScheduleDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderSchedule> tenderScheduleList = tenderScheduleRepository.findAll();
        assertThat(tenderScheduleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTenderSchedules() throws Exception {
        // Initialize the database
        tenderScheduleRepository.saveAndFlush(tenderSchedule);

        // Get all the tenderScheduleList
        restTenderScheduleMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tenderSchedule.getId().intValue())))
            .andExpect(jsonPath("$.[*].nitId").value(hasItem(DEFAULT_NIT_ID.intValue())))
            .andExpect(jsonPath("$.[*].tenderNumber").value(hasItem(DEFAULT_TENDER_NUMBER)))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].category").value(hasItem(DEFAULT_CATEGORY)))
            .andExpect(jsonPath("$.[*].ecv").value(hasItem(sameNumber(DEFAULT_ECV))))
            .andExpect(jsonPath("$.[*].indentId").value(hasItem(DEFAULT_INDENT_ID.intValue())))
            .andExpect(jsonPath("$.[*].deptId").value(hasItem(DEFAULT_DEPT_ID.intValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS)))
            .andExpect(jsonPath("$.[*].parentTenderRef").value(hasItem(DEFAULT_PARENT_TENDER_REF)))
            .andExpect(jsonPath("$.[*].noOfCalls").value(hasItem(DEFAULT_NO_OF_CALLS)))
            .andExpect(jsonPath("$.[*].processId").value(hasItem(DEFAULT_PROCESS_ID)))
            .andExpect(jsonPath("$.[*].csrValue").value(hasItem(sameNumber(DEFAULT_CSR_VALUE))))
            .andExpect(jsonPath("$.[*].ecvtenderyYn").value(hasItem(DEFAULT_ECVTENDERY_YN.booleanValue())))
            .andExpect(jsonPath("$.[*].certificateId").value(hasItem(DEFAULT_CERTIFICATE_ID)))
            .andExpect(jsonPath("$.[*].paymentsVerified").value(hasItem(DEFAULT_PAYMENTS_VERIFIED)))
            .andExpect(jsonPath("$.[*].dtsApprovalDate").value(hasItem(DEFAULT_DTS_APPROVAL_DATE.toString())))
            .andExpect(jsonPath("$.[*].mandatoryClause").value(hasItem(DEFAULT_MANDATORY_CLAUSE)))
            .andExpect(jsonPath("$.[*].location").value(hasItem(DEFAULT_LOCATION)))
            .andExpect(jsonPath("$.[*].delegateTo").value(hasItem(DEFAULT_DELEGATE_TO)))
            .andExpect(jsonPath("$.[*].isApprovedBySelf").value(hasItem(DEFAULT_IS_APPROVED_BY_SELF)))
            .andExpect(jsonPath("$.[*].catWorkCategoryName").value(hasItem(DEFAULT_CAT_WORK_CATEGORY_NAME)))
            .andExpect(jsonPath("$.[*].negotiationStatus").value(hasItem(DEFAULT_NEGOTIATION_STATUS)))
            .andExpect(jsonPath("$.[*].manualTenderYn").value(hasItem(DEFAULT_MANUAL_TENDER_YN.booleanValue())))
            .andExpect(jsonPath("$.[*].districtId").value(hasItem(DEFAULT_DISTRICT_ID.intValue())))
            .andExpect(jsonPath("$.[*].draftPublishStatus").value(hasItem(DEFAULT_DRAFT_PUBLISH_STATUS)))
            .andExpect(jsonPath("$.[*].csrRemarks").value(hasItem(DEFAULT_CSR_REMARKS)))
            .andExpect(jsonPath("$.[*].procEntityType").value(hasItem(DEFAULT_PROC_ENTITY_TYPE)))
            .andExpect(jsonPath("$.[*].dtsApprovedBy").value(hasItem(DEFAULT_DTS_APPROVED_BY)));
    }

    @Test
    @Transactional
    void getTenderSchedule() throws Exception {
        // Initialize the database
        tenderScheduleRepository.saveAndFlush(tenderSchedule);

        // Get the tenderSchedule
        restTenderScheduleMockMvc
            .perform(get(ENTITY_API_URL_ID, tenderSchedule.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tenderSchedule.getId().intValue()))
            .andExpect(jsonPath("$.nitId").value(DEFAULT_NIT_ID.intValue()))
            .andExpect(jsonPath("$.tenderNumber").value(DEFAULT_TENDER_NUMBER))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.category").value(DEFAULT_CATEGORY))
            .andExpect(jsonPath("$.ecv").value(sameNumber(DEFAULT_ECV)))
            .andExpect(jsonPath("$.indentId").value(DEFAULT_INDENT_ID.intValue()))
            .andExpect(jsonPath("$.deptId").value(DEFAULT_DEPT_ID.intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS))
            .andExpect(jsonPath("$.parentTenderRef").value(DEFAULT_PARENT_TENDER_REF))
            .andExpect(jsonPath("$.noOfCalls").value(DEFAULT_NO_OF_CALLS))
            .andExpect(jsonPath("$.processId").value(DEFAULT_PROCESS_ID))
            .andExpect(jsonPath("$.csrValue").value(sameNumber(DEFAULT_CSR_VALUE)))
            .andExpect(jsonPath("$.ecvtenderyYn").value(DEFAULT_ECVTENDERY_YN.booleanValue()))
            .andExpect(jsonPath("$.certificateId").value(DEFAULT_CERTIFICATE_ID))
            .andExpect(jsonPath("$.paymentsVerified").value(DEFAULT_PAYMENTS_VERIFIED))
            .andExpect(jsonPath("$.dtsApprovalDate").value(DEFAULT_DTS_APPROVAL_DATE.toString()))
            .andExpect(jsonPath("$.mandatoryClause").value(DEFAULT_MANDATORY_CLAUSE))
            .andExpect(jsonPath("$.location").value(DEFAULT_LOCATION))
            .andExpect(jsonPath("$.delegateTo").value(DEFAULT_DELEGATE_TO))
            .andExpect(jsonPath("$.isApprovedBySelf").value(DEFAULT_IS_APPROVED_BY_SELF))
            .andExpect(jsonPath("$.catWorkCategoryName").value(DEFAULT_CAT_WORK_CATEGORY_NAME))
            .andExpect(jsonPath("$.negotiationStatus").value(DEFAULT_NEGOTIATION_STATUS))
            .andExpect(jsonPath("$.manualTenderYn").value(DEFAULT_MANUAL_TENDER_YN.booleanValue()))
            .andExpect(jsonPath("$.districtId").value(DEFAULT_DISTRICT_ID.intValue()))
            .andExpect(jsonPath("$.draftPublishStatus").value(DEFAULT_DRAFT_PUBLISH_STATUS))
            .andExpect(jsonPath("$.csrRemarks").value(DEFAULT_CSR_REMARKS))
            .andExpect(jsonPath("$.procEntityType").value(DEFAULT_PROC_ENTITY_TYPE))
            .andExpect(jsonPath("$.dtsApprovedBy").value(DEFAULT_DTS_APPROVED_BY));
    }

    @Test
    @Transactional
    void getNonExistingTenderSchedule() throws Exception {
        // Get the tenderSchedule
        restTenderScheduleMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTenderSchedule() throws Exception {
        // Initialize the database
        tenderScheduleRepository.saveAndFlush(tenderSchedule);

        int databaseSizeBeforeUpdate = tenderScheduleRepository.findAll().size();

        // Update the tenderSchedule
        TenderSchedule updatedTenderSchedule = tenderScheduleRepository.findById(tenderSchedule.getId()).get();
        // Disconnect from session so that the updates on updatedTenderSchedule are not directly saved in db
        em.detach(updatedTenderSchedule);
        updatedTenderSchedule
            .nitId(UPDATED_NIT_ID)
            .tenderNumber(UPDATED_TENDER_NUMBER)
            .title(UPDATED_TITLE)
            .description(UPDATED_DESCRIPTION)
            .category(UPDATED_CATEGORY)
            .ecv(UPDATED_ECV)
            .indentId(UPDATED_INDENT_ID)
            .deptId(UPDATED_DEPT_ID)
            .status(UPDATED_STATUS)
            .remarks(UPDATED_REMARKS)
            .parentTenderRef(UPDATED_PARENT_TENDER_REF)
            .noOfCalls(UPDATED_NO_OF_CALLS)
            .processId(UPDATED_PROCESS_ID)
            .csrValue(UPDATED_CSR_VALUE)
            .ecvtenderyYn(UPDATED_ECVTENDERY_YN)
            .certificateId(UPDATED_CERTIFICATE_ID)
            .paymentsVerified(UPDATED_PAYMENTS_VERIFIED)
            .dtsApprovalDate(UPDATED_DTS_APPROVAL_DATE)
            .mandatoryClause(UPDATED_MANDATORY_CLAUSE)
            .location(UPDATED_LOCATION)
            .delegateTo(UPDATED_DELEGATE_TO)
            .isApprovedBySelf(UPDATED_IS_APPROVED_BY_SELF)
            .catWorkCategoryName(UPDATED_CAT_WORK_CATEGORY_NAME)
            .negotiationStatus(UPDATED_NEGOTIATION_STATUS)
            .manualTenderYn(UPDATED_MANUAL_TENDER_YN)
            .districtId(UPDATED_DISTRICT_ID)
            .draftPublishStatus(UPDATED_DRAFT_PUBLISH_STATUS)
            .csrRemarks(UPDATED_CSR_REMARKS)
            .procEntityType(UPDATED_PROC_ENTITY_TYPE)
            .dtsApprovedBy(UPDATED_DTS_APPROVED_BY);
        TenderScheduleDTO tenderScheduleDTO = tenderScheduleMapper.toDto(updatedTenderSchedule);

        restTenderScheduleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tenderScheduleDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleDTO))
            )
            .andExpect(status().isOk());

        // Validate the TenderSchedule in the database
        List<TenderSchedule> tenderScheduleList = tenderScheduleRepository.findAll();
        assertThat(tenderScheduleList).hasSize(databaseSizeBeforeUpdate);
        TenderSchedule testTenderSchedule = tenderScheduleList.get(tenderScheduleList.size() - 1);
        assertThat(testTenderSchedule.getNitId()).isEqualTo(UPDATED_NIT_ID);
        assertThat(testTenderSchedule.getTenderNumber()).isEqualTo(UPDATED_TENDER_NUMBER);
        assertThat(testTenderSchedule.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testTenderSchedule.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testTenderSchedule.getCategory()).isEqualTo(UPDATED_CATEGORY);
        assertThat(testTenderSchedule.getEcv()).isEqualTo(UPDATED_ECV);
        assertThat(testTenderSchedule.getIndentId()).isEqualTo(UPDATED_INDENT_ID);
        assertThat(testTenderSchedule.getDeptId()).isEqualTo(UPDATED_DEPT_ID);
        assertThat(testTenderSchedule.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testTenderSchedule.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testTenderSchedule.getParentTenderRef()).isEqualTo(UPDATED_PARENT_TENDER_REF);
        assertThat(testTenderSchedule.getNoOfCalls()).isEqualTo(UPDATED_NO_OF_CALLS);
        assertThat(testTenderSchedule.getProcessId()).isEqualTo(UPDATED_PROCESS_ID);
        assertThat(testTenderSchedule.getCsrValue()).isEqualTo(UPDATED_CSR_VALUE);
        assertThat(testTenderSchedule.getEcvtenderyYn()).isEqualTo(UPDATED_ECVTENDERY_YN);
        assertThat(testTenderSchedule.getCertificateId()).isEqualTo(UPDATED_CERTIFICATE_ID);
        assertThat(testTenderSchedule.getPaymentsVerified()).isEqualTo(UPDATED_PAYMENTS_VERIFIED);
        assertThat(testTenderSchedule.getDtsApprovalDate()).isEqualTo(UPDATED_DTS_APPROVAL_DATE);
        assertThat(testTenderSchedule.getMandatoryClause()).isEqualTo(UPDATED_MANDATORY_CLAUSE);
        assertThat(testTenderSchedule.getLocation()).isEqualTo(UPDATED_LOCATION);
        assertThat(testTenderSchedule.getDelegateTo()).isEqualTo(UPDATED_DELEGATE_TO);
        assertThat(testTenderSchedule.getIsApprovedBySelf()).isEqualTo(UPDATED_IS_APPROVED_BY_SELF);
        assertThat(testTenderSchedule.getCatWorkCategoryName()).isEqualTo(UPDATED_CAT_WORK_CATEGORY_NAME);
        assertThat(testTenderSchedule.getNegotiationStatus()).isEqualTo(UPDATED_NEGOTIATION_STATUS);
        assertThat(testTenderSchedule.getManualTenderYn()).isEqualTo(UPDATED_MANUAL_TENDER_YN);
        assertThat(testTenderSchedule.getDistrictId()).isEqualTo(UPDATED_DISTRICT_ID);
        assertThat(testTenderSchedule.getDraftPublishStatus()).isEqualTo(UPDATED_DRAFT_PUBLISH_STATUS);
        assertThat(testTenderSchedule.getCsrRemarks()).isEqualTo(UPDATED_CSR_REMARKS);
        assertThat(testTenderSchedule.getProcEntityType()).isEqualTo(UPDATED_PROC_ENTITY_TYPE);
        assertThat(testTenderSchedule.getDtsApprovedBy()).isEqualTo(UPDATED_DTS_APPROVED_BY);
    }

    @Test
    @Transactional
    void putNonExistingTenderSchedule() throws Exception {
        int databaseSizeBeforeUpdate = tenderScheduleRepository.findAll().size();
        tenderSchedule.setId(count.incrementAndGet());

        // Create the TenderSchedule
        TenderScheduleDTO tenderScheduleDTO = tenderScheduleMapper.toDto(tenderSchedule);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTenderScheduleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tenderScheduleDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderSchedule in the database
        List<TenderSchedule> tenderScheduleList = tenderScheduleRepository.findAll();
        assertThat(tenderScheduleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTenderSchedule() throws Exception {
        int databaseSizeBeforeUpdate = tenderScheduleRepository.findAll().size();
        tenderSchedule.setId(count.incrementAndGet());

        // Create the TenderSchedule
        TenderScheduleDTO tenderScheduleDTO = tenderScheduleMapper.toDto(tenderSchedule);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderScheduleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderSchedule in the database
        List<TenderSchedule> tenderScheduleList = tenderScheduleRepository.findAll();
        assertThat(tenderScheduleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTenderSchedule() throws Exception {
        int databaseSizeBeforeUpdate = tenderScheduleRepository.findAll().size();
        tenderSchedule.setId(count.incrementAndGet());

        // Create the TenderSchedule
        TenderScheduleDTO tenderScheduleDTO = tenderScheduleMapper.toDto(tenderSchedule);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderScheduleMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tenderScheduleDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TenderSchedule in the database
        List<TenderSchedule> tenderScheduleList = tenderScheduleRepository.findAll();
        assertThat(tenderScheduleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTenderScheduleWithPatch() throws Exception {
        // Initialize the database
        tenderScheduleRepository.saveAndFlush(tenderSchedule);

        int databaseSizeBeforeUpdate = tenderScheduleRepository.findAll().size();

        // Update the tenderSchedule using partial update
        TenderSchedule partialUpdatedTenderSchedule = new TenderSchedule();
        partialUpdatedTenderSchedule.setId(tenderSchedule.getId());

        partialUpdatedTenderSchedule
            .nitId(UPDATED_NIT_ID)
            .title(UPDATED_TITLE)
            .description(UPDATED_DESCRIPTION)
            .indentId(UPDATED_INDENT_ID)
            .deptId(UPDATED_DEPT_ID)
            .status(UPDATED_STATUS)
            .parentTenderRef(UPDATED_PARENT_TENDER_REF)
            .noOfCalls(UPDATED_NO_OF_CALLS)
            .csrValue(UPDATED_CSR_VALUE)
            .paymentsVerified(UPDATED_PAYMENTS_VERIFIED)
            .dtsApprovalDate(UPDATED_DTS_APPROVAL_DATE)
            .mandatoryClause(UPDATED_MANDATORY_CLAUSE)
            .isApprovedBySelf(UPDATED_IS_APPROVED_BY_SELF)
            .districtId(UPDATED_DISTRICT_ID)
            .procEntityType(UPDATED_PROC_ENTITY_TYPE);

        restTenderScheduleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTenderSchedule.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTenderSchedule))
            )
            .andExpect(status().isOk());

        // Validate the TenderSchedule in the database
        List<TenderSchedule> tenderScheduleList = tenderScheduleRepository.findAll();
        assertThat(tenderScheduleList).hasSize(databaseSizeBeforeUpdate);
        TenderSchedule testTenderSchedule = tenderScheduleList.get(tenderScheduleList.size() - 1);
        assertThat(testTenderSchedule.getNitId()).isEqualTo(UPDATED_NIT_ID);
        assertThat(testTenderSchedule.getTenderNumber()).isEqualTo(DEFAULT_TENDER_NUMBER);
        assertThat(testTenderSchedule.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testTenderSchedule.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testTenderSchedule.getCategory()).isEqualTo(DEFAULT_CATEGORY);
        assertThat(testTenderSchedule.getEcv()).isEqualByComparingTo(DEFAULT_ECV);
        assertThat(testTenderSchedule.getIndentId()).isEqualTo(UPDATED_INDENT_ID);
        assertThat(testTenderSchedule.getDeptId()).isEqualTo(UPDATED_DEPT_ID);
        assertThat(testTenderSchedule.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testTenderSchedule.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testTenderSchedule.getParentTenderRef()).isEqualTo(UPDATED_PARENT_TENDER_REF);
        assertThat(testTenderSchedule.getNoOfCalls()).isEqualTo(UPDATED_NO_OF_CALLS);
        assertThat(testTenderSchedule.getProcessId()).isEqualTo(DEFAULT_PROCESS_ID);
        assertThat(testTenderSchedule.getCsrValue()).isEqualByComparingTo(UPDATED_CSR_VALUE);
        assertThat(testTenderSchedule.getEcvtenderyYn()).isEqualTo(DEFAULT_ECVTENDERY_YN);
        assertThat(testTenderSchedule.getCertificateId()).isEqualTo(DEFAULT_CERTIFICATE_ID);
        assertThat(testTenderSchedule.getPaymentsVerified()).isEqualTo(UPDATED_PAYMENTS_VERIFIED);
        assertThat(testTenderSchedule.getDtsApprovalDate()).isEqualTo(UPDATED_DTS_APPROVAL_DATE);
        assertThat(testTenderSchedule.getMandatoryClause()).isEqualTo(UPDATED_MANDATORY_CLAUSE);
        assertThat(testTenderSchedule.getLocation()).isEqualTo(DEFAULT_LOCATION);
        assertThat(testTenderSchedule.getDelegateTo()).isEqualTo(DEFAULT_DELEGATE_TO);
        assertThat(testTenderSchedule.getIsApprovedBySelf()).isEqualTo(UPDATED_IS_APPROVED_BY_SELF);
        assertThat(testTenderSchedule.getCatWorkCategoryName()).isEqualTo(DEFAULT_CAT_WORK_CATEGORY_NAME);
        assertThat(testTenderSchedule.getNegotiationStatus()).isEqualTo(DEFAULT_NEGOTIATION_STATUS);
        assertThat(testTenderSchedule.getManualTenderYn()).isEqualTo(DEFAULT_MANUAL_TENDER_YN);
        assertThat(testTenderSchedule.getDistrictId()).isEqualTo(UPDATED_DISTRICT_ID);
        assertThat(testTenderSchedule.getDraftPublishStatus()).isEqualTo(DEFAULT_DRAFT_PUBLISH_STATUS);
        assertThat(testTenderSchedule.getCsrRemarks()).isEqualTo(DEFAULT_CSR_REMARKS);
        assertThat(testTenderSchedule.getProcEntityType()).isEqualTo(UPDATED_PROC_ENTITY_TYPE);
        assertThat(testTenderSchedule.getDtsApprovedBy()).isEqualTo(DEFAULT_DTS_APPROVED_BY);
    }

    @Test
    @Transactional
    void fullUpdateTenderScheduleWithPatch() throws Exception {
        // Initialize the database
        tenderScheduleRepository.saveAndFlush(tenderSchedule);

        int databaseSizeBeforeUpdate = tenderScheduleRepository.findAll().size();

        // Update the tenderSchedule using partial update
        TenderSchedule partialUpdatedTenderSchedule = new TenderSchedule();
        partialUpdatedTenderSchedule.setId(tenderSchedule.getId());

        partialUpdatedTenderSchedule
            .nitId(UPDATED_NIT_ID)
            .tenderNumber(UPDATED_TENDER_NUMBER)
            .title(UPDATED_TITLE)
            .description(UPDATED_DESCRIPTION)
            .category(UPDATED_CATEGORY)
            .ecv(UPDATED_ECV)
            .indentId(UPDATED_INDENT_ID)
            .deptId(UPDATED_DEPT_ID)
            .status(UPDATED_STATUS)
            .remarks(UPDATED_REMARKS)
            .parentTenderRef(UPDATED_PARENT_TENDER_REF)
            .noOfCalls(UPDATED_NO_OF_CALLS)
            .processId(UPDATED_PROCESS_ID)
            .csrValue(UPDATED_CSR_VALUE)
            .ecvtenderyYn(UPDATED_ECVTENDERY_YN)
            .certificateId(UPDATED_CERTIFICATE_ID)
            .paymentsVerified(UPDATED_PAYMENTS_VERIFIED)
            .dtsApprovalDate(UPDATED_DTS_APPROVAL_DATE)
            .mandatoryClause(UPDATED_MANDATORY_CLAUSE)
            .location(UPDATED_LOCATION)
            .delegateTo(UPDATED_DELEGATE_TO)
            .isApprovedBySelf(UPDATED_IS_APPROVED_BY_SELF)
            .catWorkCategoryName(UPDATED_CAT_WORK_CATEGORY_NAME)
            .negotiationStatus(UPDATED_NEGOTIATION_STATUS)
            .manualTenderYn(UPDATED_MANUAL_TENDER_YN)
            .districtId(UPDATED_DISTRICT_ID)
            .draftPublishStatus(UPDATED_DRAFT_PUBLISH_STATUS)
            .csrRemarks(UPDATED_CSR_REMARKS)
            .procEntityType(UPDATED_PROC_ENTITY_TYPE)
            .dtsApprovedBy(UPDATED_DTS_APPROVED_BY);

        restTenderScheduleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTenderSchedule.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTenderSchedule))
            )
            .andExpect(status().isOk());

        // Validate the TenderSchedule in the database
        List<TenderSchedule> tenderScheduleList = tenderScheduleRepository.findAll();
        assertThat(tenderScheduleList).hasSize(databaseSizeBeforeUpdate);
        TenderSchedule testTenderSchedule = tenderScheduleList.get(tenderScheduleList.size() - 1);
        assertThat(testTenderSchedule.getNitId()).isEqualTo(UPDATED_NIT_ID);
        assertThat(testTenderSchedule.getTenderNumber()).isEqualTo(UPDATED_TENDER_NUMBER);
        assertThat(testTenderSchedule.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testTenderSchedule.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testTenderSchedule.getCategory()).isEqualTo(UPDATED_CATEGORY);
        assertThat(testTenderSchedule.getEcv()).isEqualByComparingTo(UPDATED_ECV);
        assertThat(testTenderSchedule.getIndentId()).isEqualTo(UPDATED_INDENT_ID);
        assertThat(testTenderSchedule.getDeptId()).isEqualTo(UPDATED_DEPT_ID);
        assertThat(testTenderSchedule.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testTenderSchedule.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testTenderSchedule.getParentTenderRef()).isEqualTo(UPDATED_PARENT_TENDER_REF);
        assertThat(testTenderSchedule.getNoOfCalls()).isEqualTo(UPDATED_NO_OF_CALLS);
        assertThat(testTenderSchedule.getProcessId()).isEqualTo(UPDATED_PROCESS_ID);
        assertThat(testTenderSchedule.getCsrValue()).isEqualByComparingTo(UPDATED_CSR_VALUE);
        assertThat(testTenderSchedule.getEcvtenderyYn()).isEqualTo(UPDATED_ECVTENDERY_YN);
        assertThat(testTenderSchedule.getCertificateId()).isEqualTo(UPDATED_CERTIFICATE_ID);
        assertThat(testTenderSchedule.getPaymentsVerified()).isEqualTo(UPDATED_PAYMENTS_VERIFIED);
        assertThat(testTenderSchedule.getDtsApprovalDate()).isEqualTo(UPDATED_DTS_APPROVAL_DATE);
        assertThat(testTenderSchedule.getMandatoryClause()).isEqualTo(UPDATED_MANDATORY_CLAUSE);
        assertThat(testTenderSchedule.getLocation()).isEqualTo(UPDATED_LOCATION);
        assertThat(testTenderSchedule.getDelegateTo()).isEqualTo(UPDATED_DELEGATE_TO);
        assertThat(testTenderSchedule.getIsApprovedBySelf()).isEqualTo(UPDATED_IS_APPROVED_BY_SELF);
        assertThat(testTenderSchedule.getCatWorkCategoryName()).isEqualTo(UPDATED_CAT_WORK_CATEGORY_NAME);
        assertThat(testTenderSchedule.getNegotiationStatus()).isEqualTo(UPDATED_NEGOTIATION_STATUS);
        assertThat(testTenderSchedule.getManualTenderYn()).isEqualTo(UPDATED_MANUAL_TENDER_YN);
        assertThat(testTenderSchedule.getDistrictId()).isEqualTo(UPDATED_DISTRICT_ID);
        assertThat(testTenderSchedule.getDraftPublishStatus()).isEqualTo(UPDATED_DRAFT_PUBLISH_STATUS);
        assertThat(testTenderSchedule.getCsrRemarks()).isEqualTo(UPDATED_CSR_REMARKS);
        assertThat(testTenderSchedule.getProcEntityType()).isEqualTo(UPDATED_PROC_ENTITY_TYPE);
        assertThat(testTenderSchedule.getDtsApprovedBy()).isEqualTo(UPDATED_DTS_APPROVED_BY);
    }

    @Test
    @Transactional
    void patchNonExistingTenderSchedule() throws Exception {
        int databaseSizeBeforeUpdate = tenderScheduleRepository.findAll().size();
        tenderSchedule.setId(count.incrementAndGet());

        // Create the TenderSchedule
        TenderScheduleDTO tenderScheduleDTO = tenderScheduleMapper.toDto(tenderSchedule);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTenderScheduleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tenderScheduleDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderSchedule in the database
        List<TenderSchedule> tenderScheduleList = tenderScheduleRepository.findAll();
        assertThat(tenderScheduleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTenderSchedule() throws Exception {
        int databaseSizeBeforeUpdate = tenderScheduleRepository.findAll().size();
        tenderSchedule.setId(count.incrementAndGet());

        // Create the TenderSchedule
        TenderScheduleDTO tenderScheduleDTO = tenderScheduleMapper.toDto(tenderSchedule);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderScheduleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderSchedule in the database
        List<TenderSchedule> tenderScheduleList = tenderScheduleRepository.findAll();
        assertThat(tenderScheduleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTenderSchedule() throws Exception {
        int databaseSizeBeforeUpdate = tenderScheduleRepository.findAll().size();
        tenderSchedule.setId(count.incrementAndGet());

        // Create the TenderSchedule
        TenderScheduleDTO tenderScheduleDTO = tenderScheduleMapper.toDto(tenderSchedule);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderScheduleMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderScheduleDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TenderSchedule in the database
        List<TenderSchedule> tenderScheduleList = tenderScheduleRepository.findAll();
        assertThat(tenderScheduleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTenderSchedule() throws Exception {
        // Initialize the database
        tenderScheduleRepository.saveAndFlush(tenderSchedule);

        int databaseSizeBeforeDelete = tenderScheduleRepository.findAll().size();

        // Delete the tenderSchedule
        restTenderScheduleMockMvc
            .perform(delete(ENTITY_API_URL_ID, tenderSchedule.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TenderSchedule> tenderScheduleList = tenderScheduleRepository.findAll();
        assertThat(tenderScheduleList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
