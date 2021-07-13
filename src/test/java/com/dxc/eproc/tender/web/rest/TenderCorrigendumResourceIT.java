package com.dxc.eproc.tender.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dxc.eproc.tender.IntegrationTest;
import com.dxc.eproc.tender.domain.TenderCorrigendum;
import com.dxc.eproc.tender.repository.TenderCorrigendumRepository;
import com.dxc.eproc.tender.service.dto.TenderCorrigendumDTO;
import com.dxc.eproc.tender.service.mapper.TenderCorrigendumMapper;
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
 * Integration tests for the {@link TenderCorrigendumResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TenderCorrigendumResourceIT {

    private static final Long DEFAULT_NIT_ID = 1L;
    private static final Long UPDATED_NIT_ID = 2L;

    private static final String DEFAULT_REASON = "AAAAAAAAAA";
    private static final String UPDATED_REASON = "BBBBBBBBBB";

    private static final Integer DEFAULT_HISTORY_ORDER = 1;
    private static final Integer UPDATED_HISTORY_ORDER = 2;

    private static final LocalDate DEFAULT_TENDER_DOC_CLOSE_DATE_ORIGINAL = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TENDER_DOC_CLOSE_DATE_ORIGINAL = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_TENDER_DOC_CLOSE_DATE_REVISED = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TENDER_DOC_CLOSE_DATE_REVISED = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_TENDER_RECEIPT_CLOSE_DATE_ORIGINAL = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TENDER_RECEIPT_CLOSE_DATE_ORIGINAL = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_TENDER_RECEIPT_CLOSE_DATE_REVISED = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TENDER_RECEIPT_CLOSE_DATE_REVISED = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_TENDER_QUERY_CLOSE_DATE_ORIGINAL = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TENDER_QUERY_CLOSE_DATE_ORIGINAL = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_TENDER_QUERY_CLOSE_DATE_REVISED = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TENDER_QUERY_CLOSE_DATE_REVISED = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_TECHNICAL_BID_OPEN_DATE_ORIGINAL = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TECHNICAL_BID_OPEN_DATE_ORIGINAL = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_TECHNICAL_BID_OPEN_DATE_REVISED = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TECHNICAL_BID_OPEN_DATE_REVISED = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_FINANCIAL_BID_OPEN_DATE_ORIGINAL = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FINANCIAL_BID_OPEN_DATE_ORIGINAL = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_FINANCIAL_BID_OPEN_DATE_REVISED = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FINANCIAL_BID_OPEN_DATE_REVISED = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_PREQUAL_BID_OPEN_DATE_ORIGINAL = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PREQUAL_BID_OPEN_DATE_ORIGINAL = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_PREQUAL_BID_OPEN_DATE_REVISED = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PREQUAL_BID_OPEN_DATE_REVISED = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_PREQUAL_TENDE_BID_OPEN_ORIGINAL = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PREQUAL_TENDE_BID_OPEN_ORIGINAL = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_PREQUAL_TENDER_BID_OPEN_REVISED = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PREQUAL_TENDER_BID_OPEN_REVISED = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_PRE_BID_MEETING_DATE_ORIGINAL = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PRE_BID_MEETING_DATE_ORIGINAL = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_PRE_BID_MEETING_DATE_REVISED = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PRE_BID_MEETING_DATE_REVISED = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_PREBID_MEETING_ADDRESS_ORIGINAL = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PREBID_MEETING_ADDRESS_ORIGINAL = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_PREBID_MEETING_ADDRESS_REVISED = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PREBID_MEETING_ADDRESS_REVISED = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_PREQUAL_VALIDITY_PERIOD_ORIGINAL = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PREQUAL_VALIDITY_PERIOD_ORIGINAL = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_PREQUAL_VALIDITY_PERIOD_REVISED = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PREQUAL_VALIDITY_PERIOD_REVISED = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/tender-corrigendums";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TenderCorrigendumRepository tenderCorrigendumRepository;

    @Autowired
    private TenderCorrigendumMapper tenderCorrigendumMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTenderCorrigendumMockMvc;

    private TenderCorrigendum tenderCorrigendum;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TenderCorrigendum createEntity(EntityManager em) {
        TenderCorrigendum tenderCorrigendum = new TenderCorrigendum()
            .nitId(DEFAULT_NIT_ID)
            .reason(DEFAULT_REASON)
            .historyOrder(DEFAULT_HISTORY_ORDER)
            .tenderDocCloseDateOriginal(DEFAULT_TENDER_DOC_CLOSE_DATE_ORIGINAL)
            .tenderDocCloseDateRevised(DEFAULT_TENDER_DOC_CLOSE_DATE_REVISED)
            .tenderReceiptCloseDateOriginal(DEFAULT_TENDER_RECEIPT_CLOSE_DATE_ORIGINAL)
            .tenderReceiptCloseDateRevised(DEFAULT_TENDER_RECEIPT_CLOSE_DATE_REVISED)
            .tenderQueryCloseDateOriginal(DEFAULT_TENDER_QUERY_CLOSE_DATE_ORIGINAL)
            .tenderQueryCloseDateRevised(DEFAULT_TENDER_QUERY_CLOSE_DATE_REVISED)
            .technicalBidOpenDateOriginal(DEFAULT_TECHNICAL_BID_OPEN_DATE_ORIGINAL)
            .technicalBidOpenDateRevised(DEFAULT_TECHNICAL_BID_OPEN_DATE_REVISED)
            .financialBidOpenDateOriginal(DEFAULT_FINANCIAL_BID_OPEN_DATE_ORIGINAL)
            .financialBidOpenDateRevised(DEFAULT_FINANCIAL_BID_OPEN_DATE_REVISED)
            .prequalBidOpenDateOriginal(DEFAULT_PREQUAL_BID_OPEN_DATE_ORIGINAL)
            .prequalBidOpenDateRevised(DEFAULT_PREQUAL_BID_OPEN_DATE_REVISED)
            .status(DEFAULT_STATUS)
            .prequalTendeBidOpenOriginal(DEFAULT_PREQUAL_TENDE_BID_OPEN_ORIGINAL)
            .prequalTenderBidOpenRevised(DEFAULT_PREQUAL_TENDER_BID_OPEN_REVISED)
            .preBidMeetingDateOriginal(DEFAULT_PRE_BID_MEETING_DATE_ORIGINAL)
            .preBidMeetingDateRevised(DEFAULT_PRE_BID_MEETING_DATE_REVISED)
            .prebidMeetingAddressOriginal(DEFAULT_PREBID_MEETING_ADDRESS_ORIGINAL)
            .prebidMeetingAddressRevised(DEFAULT_PREBID_MEETING_ADDRESS_REVISED)
            .prequalValidityPeriodOriginal(DEFAULT_PREQUAL_VALIDITY_PERIOD_ORIGINAL)
            .prequalValidityPeriodRevised(DEFAULT_PREQUAL_VALIDITY_PERIOD_REVISED);
        return tenderCorrigendum;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TenderCorrigendum createUpdatedEntity(EntityManager em) {
        TenderCorrigendum tenderCorrigendum = new TenderCorrigendum()
            .nitId(UPDATED_NIT_ID)
            .reason(UPDATED_REASON)
            .historyOrder(UPDATED_HISTORY_ORDER)
            .tenderDocCloseDateOriginal(UPDATED_TENDER_DOC_CLOSE_DATE_ORIGINAL)
            .tenderDocCloseDateRevised(UPDATED_TENDER_DOC_CLOSE_DATE_REVISED)
            .tenderReceiptCloseDateOriginal(UPDATED_TENDER_RECEIPT_CLOSE_DATE_ORIGINAL)
            .tenderReceiptCloseDateRevised(UPDATED_TENDER_RECEIPT_CLOSE_DATE_REVISED)
            .tenderQueryCloseDateOriginal(UPDATED_TENDER_QUERY_CLOSE_DATE_ORIGINAL)
            .tenderQueryCloseDateRevised(UPDATED_TENDER_QUERY_CLOSE_DATE_REVISED)
            .technicalBidOpenDateOriginal(UPDATED_TECHNICAL_BID_OPEN_DATE_ORIGINAL)
            .technicalBidOpenDateRevised(UPDATED_TECHNICAL_BID_OPEN_DATE_REVISED)
            .financialBidOpenDateOriginal(UPDATED_FINANCIAL_BID_OPEN_DATE_ORIGINAL)
            .financialBidOpenDateRevised(UPDATED_FINANCIAL_BID_OPEN_DATE_REVISED)
            .prequalBidOpenDateOriginal(UPDATED_PREQUAL_BID_OPEN_DATE_ORIGINAL)
            .prequalBidOpenDateRevised(UPDATED_PREQUAL_BID_OPEN_DATE_REVISED)
            .status(UPDATED_STATUS)
            .prequalTendeBidOpenOriginal(UPDATED_PREQUAL_TENDE_BID_OPEN_ORIGINAL)
            .prequalTenderBidOpenRevised(UPDATED_PREQUAL_TENDER_BID_OPEN_REVISED)
            .preBidMeetingDateOriginal(UPDATED_PRE_BID_MEETING_DATE_ORIGINAL)
            .preBidMeetingDateRevised(UPDATED_PRE_BID_MEETING_DATE_REVISED)
            .prebidMeetingAddressOriginal(UPDATED_PREBID_MEETING_ADDRESS_ORIGINAL)
            .prebidMeetingAddressRevised(UPDATED_PREBID_MEETING_ADDRESS_REVISED)
            .prequalValidityPeriodOriginal(UPDATED_PREQUAL_VALIDITY_PERIOD_ORIGINAL)
            .prequalValidityPeriodRevised(UPDATED_PREQUAL_VALIDITY_PERIOD_REVISED);
        return tenderCorrigendum;
    }

    @BeforeEach
    public void initTest() {
        tenderCorrigendum = createEntity(em);
    }

    @Test
    @Transactional
    void createTenderCorrigendum() throws Exception {
        int databaseSizeBeforeCreate = tenderCorrigendumRepository.findAll().size();
        // Create the TenderCorrigendum
        TenderCorrigendumDTO tenderCorrigendumDTO = tenderCorrigendumMapper.toDto(tenderCorrigendum);
        restTenderCorrigendumMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCorrigendumDTO))
            )
            .andExpect(status().isCreated());

        // Validate the TenderCorrigendum in the database
        List<TenderCorrigendum> tenderCorrigendumList = tenderCorrigendumRepository.findAll();
        assertThat(tenderCorrigendumList).hasSize(databaseSizeBeforeCreate + 1);
        TenderCorrigendum testTenderCorrigendum = tenderCorrigendumList.get(tenderCorrigendumList.size() - 1);
        assertThat(testTenderCorrigendum.getNitId()).isEqualTo(DEFAULT_NIT_ID);
        assertThat(testTenderCorrigendum.getReason()).isEqualTo(DEFAULT_REASON);
        assertThat(testTenderCorrigendum.getHistoryOrder()).isEqualTo(DEFAULT_HISTORY_ORDER);
        assertThat(testTenderCorrigendum.getTenderDocCloseDateOriginal()).isEqualTo(DEFAULT_TENDER_DOC_CLOSE_DATE_ORIGINAL);
        assertThat(testTenderCorrigendum.getTenderDocCloseDateRevised()).isEqualTo(DEFAULT_TENDER_DOC_CLOSE_DATE_REVISED);
        assertThat(testTenderCorrigendum.getTenderReceiptCloseDateOriginal()).isEqualTo(DEFAULT_TENDER_RECEIPT_CLOSE_DATE_ORIGINAL);
        assertThat(testTenderCorrigendum.getTenderReceiptCloseDateRevised()).isEqualTo(DEFAULT_TENDER_RECEIPT_CLOSE_DATE_REVISED);
        assertThat(testTenderCorrigendum.getTenderQueryCloseDateOriginal()).isEqualTo(DEFAULT_TENDER_QUERY_CLOSE_DATE_ORIGINAL);
        assertThat(testTenderCorrigendum.getTenderQueryCloseDateRevised()).isEqualTo(DEFAULT_TENDER_QUERY_CLOSE_DATE_REVISED);
        assertThat(testTenderCorrigendum.getTechnicalBidOpenDateOriginal()).isEqualTo(DEFAULT_TECHNICAL_BID_OPEN_DATE_ORIGINAL);
        assertThat(testTenderCorrigendum.getTechnicalBidOpenDateRevised()).isEqualTo(DEFAULT_TECHNICAL_BID_OPEN_DATE_REVISED);
        assertThat(testTenderCorrigendum.getFinancialBidOpenDateOriginal()).isEqualTo(DEFAULT_FINANCIAL_BID_OPEN_DATE_ORIGINAL);
        assertThat(testTenderCorrigendum.getFinancialBidOpenDateRevised()).isEqualTo(DEFAULT_FINANCIAL_BID_OPEN_DATE_REVISED);
        assertThat(testTenderCorrigendum.getPrequalBidOpenDateOriginal()).isEqualTo(DEFAULT_PREQUAL_BID_OPEN_DATE_ORIGINAL);
        assertThat(testTenderCorrigendum.getPrequalBidOpenDateRevised()).isEqualTo(DEFAULT_PREQUAL_BID_OPEN_DATE_REVISED);
        assertThat(testTenderCorrigendum.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testTenderCorrigendum.getPrequalTendeBidOpenOriginal()).isEqualTo(DEFAULT_PREQUAL_TENDE_BID_OPEN_ORIGINAL);
        assertThat(testTenderCorrigendum.getPrequalTenderBidOpenRevised()).isEqualTo(DEFAULT_PREQUAL_TENDER_BID_OPEN_REVISED);
        assertThat(testTenderCorrigendum.getPreBidMeetingDateOriginal()).isEqualTo(DEFAULT_PRE_BID_MEETING_DATE_ORIGINAL);
        assertThat(testTenderCorrigendum.getPreBidMeetingDateRevised()).isEqualTo(DEFAULT_PRE_BID_MEETING_DATE_REVISED);
        assertThat(testTenderCorrigendum.getPrebidMeetingAddressOriginal()).isEqualTo(DEFAULT_PREBID_MEETING_ADDRESS_ORIGINAL);
        assertThat(testTenderCorrigendum.getPrebidMeetingAddressRevised()).isEqualTo(DEFAULT_PREBID_MEETING_ADDRESS_REVISED);
        assertThat(testTenderCorrigendum.getPrequalValidityPeriodOriginal()).isEqualTo(DEFAULT_PREQUAL_VALIDITY_PERIOD_ORIGINAL);
        assertThat(testTenderCorrigendum.getPrequalValidityPeriodRevised()).isEqualTo(DEFAULT_PREQUAL_VALIDITY_PERIOD_REVISED);
    }

    @Test
    @Transactional
    void createTenderCorrigendumWithExistingId() throws Exception {
        // Create the TenderCorrigendum with an existing ID
        tenderCorrigendum.setId(1L);
        TenderCorrigendumDTO tenderCorrigendumDTO = tenderCorrigendumMapper.toDto(tenderCorrigendum);

        int databaseSizeBeforeCreate = tenderCorrigendumRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTenderCorrigendumMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCorrigendumDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderCorrigendum in the database
        List<TenderCorrigendum> tenderCorrigendumList = tenderCorrigendumRepository.findAll();
        assertThat(tenderCorrigendumList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNitIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderCorrigendumRepository.findAll().size();
        // set the field null
        tenderCorrigendum.setNitId(null);

        // Create the TenderCorrigendum, which fails.
        TenderCorrigendumDTO tenderCorrigendumDTO = tenderCorrigendumMapper.toDto(tenderCorrigendum);

        restTenderCorrigendumMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCorrigendumDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderCorrigendum> tenderCorrigendumList = tenderCorrigendumRepository.findAll();
        assertThat(tenderCorrigendumList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkReasonIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderCorrigendumRepository.findAll().size();
        // set the field null
        tenderCorrigendum.setReason(null);

        // Create the TenderCorrigendum, which fails.
        TenderCorrigendumDTO tenderCorrigendumDTO = tenderCorrigendumMapper.toDto(tenderCorrigendum);

        restTenderCorrigendumMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCorrigendumDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderCorrigendum> tenderCorrigendumList = tenderCorrigendumRepository.findAll();
        assertThat(tenderCorrigendumList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkHistoryOrderIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderCorrigendumRepository.findAll().size();
        // set the field null
        tenderCorrigendum.setHistoryOrder(null);

        // Create the TenderCorrigendum, which fails.
        TenderCorrigendumDTO tenderCorrigendumDTO = tenderCorrigendumMapper.toDto(tenderCorrigendum);

        restTenderCorrigendumMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCorrigendumDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderCorrigendum> tenderCorrigendumList = tenderCorrigendumRepository.findAll();
        assertThat(tenderCorrigendumList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTenderDocCloseDateOriginalIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderCorrigendumRepository.findAll().size();
        // set the field null
        tenderCorrigendum.setTenderDocCloseDateOriginal(null);

        // Create the TenderCorrigendum, which fails.
        TenderCorrigendumDTO tenderCorrigendumDTO = tenderCorrigendumMapper.toDto(tenderCorrigendum);

        restTenderCorrigendumMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCorrigendumDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderCorrigendum> tenderCorrigendumList = tenderCorrigendumRepository.findAll();
        assertThat(tenderCorrigendumList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTenderDocCloseDateRevisedIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderCorrigendumRepository.findAll().size();
        // set the field null
        tenderCorrigendum.setTenderDocCloseDateRevised(null);

        // Create the TenderCorrigendum, which fails.
        TenderCorrigendumDTO tenderCorrigendumDTO = tenderCorrigendumMapper.toDto(tenderCorrigendum);

        restTenderCorrigendumMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCorrigendumDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderCorrigendum> tenderCorrigendumList = tenderCorrigendumRepository.findAll();
        assertThat(tenderCorrigendumList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTenderReceiptCloseDateOriginalIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderCorrigendumRepository.findAll().size();
        // set the field null
        tenderCorrigendum.setTenderReceiptCloseDateOriginal(null);

        // Create the TenderCorrigendum, which fails.
        TenderCorrigendumDTO tenderCorrigendumDTO = tenderCorrigendumMapper.toDto(tenderCorrigendum);

        restTenderCorrigendumMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCorrigendumDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderCorrigendum> tenderCorrigendumList = tenderCorrigendumRepository.findAll();
        assertThat(tenderCorrigendumList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTenderReceiptCloseDateRevisedIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderCorrigendumRepository.findAll().size();
        // set the field null
        tenderCorrigendum.setTenderReceiptCloseDateRevised(null);

        // Create the TenderCorrigendum, which fails.
        TenderCorrigendumDTO tenderCorrigendumDTO = tenderCorrigendumMapper.toDto(tenderCorrigendum);

        restTenderCorrigendumMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCorrigendumDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderCorrigendum> tenderCorrigendumList = tenderCorrigendumRepository.findAll();
        assertThat(tenderCorrigendumList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTenderQueryCloseDateOriginalIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderCorrigendumRepository.findAll().size();
        // set the field null
        tenderCorrigendum.setTenderQueryCloseDateOriginal(null);

        // Create the TenderCorrigendum, which fails.
        TenderCorrigendumDTO tenderCorrigendumDTO = tenderCorrigendumMapper.toDto(tenderCorrigendum);

        restTenderCorrigendumMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCorrigendumDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderCorrigendum> tenderCorrigendumList = tenderCorrigendumRepository.findAll();
        assertThat(tenderCorrigendumList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTenderQueryCloseDateRevisedIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderCorrigendumRepository.findAll().size();
        // set the field null
        tenderCorrigendum.setTenderQueryCloseDateRevised(null);

        // Create the TenderCorrigendum, which fails.
        TenderCorrigendumDTO tenderCorrigendumDTO = tenderCorrigendumMapper.toDto(tenderCorrigendum);

        restTenderCorrigendumMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCorrigendumDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderCorrigendum> tenderCorrigendumList = tenderCorrigendumRepository.findAll();
        assertThat(tenderCorrigendumList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTechnicalBidOpenDateOriginalIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderCorrigendumRepository.findAll().size();
        // set the field null
        tenderCorrigendum.setTechnicalBidOpenDateOriginal(null);

        // Create the TenderCorrigendum, which fails.
        TenderCorrigendumDTO tenderCorrigendumDTO = tenderCorrigendumMapper.toDto(tenderCorrigendum);

        restTenderCorrigendumMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCorrigendumDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderCorrigendum> tenderCorrigendumList = tenderCorrigendumRepository.findAll();
        assertThat(tenderCorrigendumList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTechnicalBidOpenDateRevisedIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderCorrigendumRepository.findAll().size();
        // set the field null
        tenderCorrigendum.setTechnicalBidOpenDateRevised(null);

        // Create the TenderCorrigendum, which fails.
        TenderCorrigendumDTO tenderCorrigendumDTO = tenderCorrigendumMapper.toDto(tenderCorrigendum);

        restTenderCorrigendumMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCorrigendumDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderCorrigendum> tenderCorrigendumList = tenderCorrigendumRepository.findAll();
        assertThat(tenderCorrigendumList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkFinancialBidOpenDateOriginalIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderCorrigendumRepository.findAll().size();
        // set the field null
        tenderCorrigendum.setFinancialBidOpenDateOriginal(null);

        // Create the TenderCorrigendum, which fails.
        TenderCorrigendumDTO tenderCorrigendumDTO = tenderCorrigendumMapper.toDto(tenderCorrigendum);

        restTenderCorrigendumMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCorrigendumDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderCorrigendum> tenderCorrigendumList = tenderCorrigendumRepository.findAll();
        assertThat(tenderCorrigendumList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkFinancialBidOpenDateRevisedIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderCorrigendumRepository.findAll().size();
        // set the field null
        tenderCorrigendum.setFinancialBidOpenDateRevised(null);

        // Create the TenderCorrigendum, which fails.
        TenderCorrigendumDTO tenderCorrigendumDTO = tenderCorrigendumMapper.toDto(tenderCorrigendum);

        restTenderCorrigendumMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCorrigendumDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderCorrigendum> tenderCorrigendumList = tenderCorrigendumRepository.findAll();
        assertThat(tenderCorrigendumList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPrequalBidOpenDateOriginalIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderCorrigendumRepository.findAll().size();
        // set the field null
        tenderCorrigendum.setPrequalBidOpenDateOriginal(null);

        // Create the TenderCorrigendum, which fails.
        TenderCorrigendumDTO tenderCorrigendumDTO = tenderCorrigendumMapper.toDto(tenderCorrigendum);

        restTenderCorrigendumMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCorrigendumDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderCorrigendum> tenderCorrigendumList = tenderCorrigendumRepository.findAll();
        assertThat(tenderCorrigendumList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPrequalBidOpenDateRevisedIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderCorrigendumRepository.findAll().size();
        // set the field null
        tenderCorrigendum.setPrequalBidOpenDateRevised(null);

        // Create the TenderCorrigendum, which fails.
        TenderCorrigendumDTO tenderCorrigendumDTO = tenderCorrigendumMapper.toDto(tenderCorrigendum);

        restTenderCorrigendumMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCorrigendumDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderCorrigendum> tenderCorrigendumList = tenderCorrigendumRepository.findAll();
        assertThat(tenderCorrigendumList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderCorrigendumRepository.findAll().size();
        // set the field null
        tenderCorrigendum.setStatus(null);

        // Create the TenderCorrigendum, which fails.
        TenderCorrigendumDTO tenderCorrigendumDTO = tenderCorrigendumMapper.toDto(tenderCorrigendum);

        restTenderCorrigendumMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCorrigendumDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderCorrigendum> tenderCorrigendumList = tenderCorrigendumRepository.findAll();
        assertThat(tenderCorrigendumList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPrequalTendeBidOpenOriginalIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderCorrigendumRepository.findAll().size();
        // set the field null
        tenderCorrigendum.setPrequalTendeBidOpenOriginal(null);

        // Create the TenderCorrigendum, which fails.
        TenderCorrigendumDTO tenderCorrigendumDTO = tenderCorrigendumMapper.toDto(tenderCorrigendum);

        restTenderCorrigendumMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCorrigendumDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderCorrigendum> tenderCorrigendumList = tenderCorrigendumRepository.findAll();
        assertThat(tenderCorrigendumList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPrequalTenderBidOpenRevisedIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderCorrigendumRepository.findAll().size();
        // set the field null
        tenderCorrigendum.setPrequalTenderBidOpenRevised(null);

        // Create the TenderCorrigendum, which fails.
        TenderCorrigendumDTO tenderCorrigendumDTO = tenderCorrigendumMapper.toDto(tenderCorrigendum);

        restTenderCorrigendumMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCorrigendumDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderCorrigendum> tenderCorrigendumList = tenderCorrigendumRepository.findAll();
        assertThat(tenderCorrigendumList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPreBidMeetingDateOriginalIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderCorrigendumRepository.findAll().size();
        // set the field null
        tenderCorrigendum.setPreBidMeetingDateOriginal(null);

        // Create the TenderCorrigendum, which fails.
        TenderCorrigendumDTO tenderCorrigendumDTO = tenderCorrigendumMapper.toDto(tenderCorrigendum);

        restTenderCorrigendumMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCorrigendumDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderCorrigendum> tenderCorrigendumList = tenderCorrigendumRepository.findAll();
        assertThat(tenderCorrigendumList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPreBidMeetingDateRevisedIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderCorrigendumRepository.findAll().size();
        // set the field null
        tenderCorrigendum.setPreBidMeetingDateRevised(null);

        // Create the TenderCorrigendum, which fails.
        TenderCorrigendumDTO tenderCorrigendumDTO = tenderCorrigendumMapper.toDto(tenderCorrigendum);

        restTenderCorrigendumMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCorrigendumDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderCorrigendum> tenderCorrigendumList = tenderCorrigendumRepository.findAll();
        assertThat(tenderCorrigendumList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPrebidMeetingAddressOriginalIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderCorrigendumRepository.findAll().size();
        // set the field null
        tenderCorrigendum.setPrebidMeetingAddressOriginal(null);

        // Create the TenderCorrigendum, which fails.
        TenderCorrigendumDTO tenderCorrigendumDTO = tenderCorrigendumMapper.toDto(tenderCorrigendum);

        restTenderCorrigendumMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCorrigendumDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderCorrigendum> tenderCorrigendumList = tenderCorrigendumRepository.findAll();
        assertThat(tenderCorrigendumList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPrebidMeetingAddressRevisedIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderCorrigendumRepository.findAll().size();
        // set the field null
        tenderCorrigendum.setPrebidMeetingAddressRevised(null);

        // Create the TenderCorrigendum, which fails.
        TenderCorrigendumDTO tenderCorrigendumDTO = tenderCorrigendumMapper.toDto(tenderCorrigendum);

        restTenderCorrigendumMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCorrigendumDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderCorrigendum> tenderCorrigendumList = tenderCorrigendumRepository.findAll();
        assertThat(tenderCorrigendumList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPrequalValidityPeriodOriginalIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderCorrigendumRepository.findAll().size();
        // set the field null
        tenderCorrigendum.setPrequalValidityPeriodOriginal(null);

        // Create the TenderCorrigendum, which fails.
        TenderCorrigendumDTO tenderCorrigendumDTO = tenderCorrigendumMapper.toDto(tenderCorrigendum);

        restTenderCorrigendumMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCorrigendumDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderCorrigendum> tenderCorrigendumList = tenderCorrigendumRepository.findAll();
        assertThat(tenderCorrigendumList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPrequalValidityPeriodRevisedIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderCorrigendumRepository.findAll().size();
        // set the field null
        tenderCorrigendum.setPrequalValidityPeriodRevised(null);

        // Create the TenderCorrigendum, which fails.
        TenderCorrigendumDTO tenderCorrigendumDTO = tenderCorrigendumMapper.toDto(tenderCorrigendum);

        restTenderCorrigendumMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCorrigendumDTO))
            )
            .andExpect(status().isBadRequest());

        List<TenderCorrigendum> tenderCorrigendumList = tenderCorrigendumRepository.findAll();
        assertThat(tenderCorrigendumList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTenderCorrigendums() throws Exception {
        // Initialize the database
        tenderCorrigendumRepository.saveAndFlush(tenderCorrigendum);

        // Get all the tenderCorrigendumList
        restTenderCorrigendumMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tenderCorrigendum.getId().intValue())))
            .andExpect(jsonPath("$.[*].nitId").value(hasItem(DEFAULT_NIT_ID.intValue())))
            .andExpect(jsonPath("$.[*].reason").value(hasItem(DEFAULT_REASON)))
            .andExpect(jsonPath("$.[*].historyOrder").value(hasItem(DEFAULT_HISTORY_ORDER)))
            .andExpect(jsonPath("$.[*].tenderDocCloseDateOriginal").value(hasItem(DEFAULT_TENDER_DOC_CLOSE_DATE_ORIGINAL.toString())))
            .andExpect(jsonPath("$.[*].tenderDocCloseDateRevised").value(hasItem(DEFAULT_TENDER_DOC_CLOSE_DATE_REVISED.toString())))
            .andExpect(
                jsonPath("$.[*].tenderReceiptCloseDateOriginal").value(hasItem(DEFAULT_TENDER_RECEIPT_CLOSE_DATE_ORIGINAL.toString()))
            )
            .andExpect(jsonPath("$.[*].tenderReceiptCloseDateRevised").value(hasItem(DEFAULT_TENDER_RECEIPT_CLOSE_DATE_REVISED.toString())))
            .andExpect(jsonPath("$.[*].tenderQueryCloseDateOriginal").value(hasItem(DEFAULT_TENDER_QUERY_CLOSE_DATE_ORIGINAL.toString())))
            .andExpect(jsonPath("$.[*].tenderQueryCloseDateRevised").value(hasItem(DEFAULT_TENDER_QUERY_CLOSE_DATE_REVISED.toString())))
            .andExpect(jsonPath("$.[*].technicalBidOpenDateOriginal").value(hasItem(DEFAULT_TECHNICAL_BID_OPEN_DATE_ORIGINAL.toString())))
            .andExpect(jsonPath("$.[*].technicalBidOpenDateRevised").value(hasItem(DEFAULT_TECHNICAL_BID_OPEN_DATE_REVISED.toString())))
            .andExpect(jsonPath("$.[*].financialBidOpenDateOriginal").value(hasItem(DEFAULT_FINANCIAL_BID_OPEN_DATE_ORIGINAL.toString())))
            .andExpect(jsonPath("$.[*].financialBidOpenDateRevised").value(hasItem(DEFAULT_FINANCIAL_BID_OPEN_DATE_REVISED.toString())))
            .andExpect(jsonPath("$.[*].prequalBidOpenDateOriginal").value(hasItem(DEFAULT_PREQUAL_BID_OPEN_DATE_ORIGINAL.toString())))
            .andExpect(jsonPath("$.[*].prequalBidOpenDateRevised").value(hasItem(DEFAULT_PREQUAL_BID_OPEN_DATE_REVISED.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].prequalTendeBidOpenOriginal").value(hasItem(DEFAULT_PREQUAL_TENDE_BID_OPEN_ORIGINAL.toString())))
            .andExpect(jsonPath("$.[*].prequalTenderBidOpenRevised").value(hasItem(DEFAULT_PREQUAL_TENDER_BID_OPEN_REVISED.toString())))
            .andExpect(jsonPath("$.[*].preBidMeetingDateOriginal").value(hasItem(DEFAULT_PRE_BID_MEETING_DATE_ORIGINAL.toString())))
            .andExpect(jsonPath("$.[*].preBidMeetingDateRevised").value(hasItem(DEFAULT_PRE_BID_MEETING_DATE_REVISED.toString())))
            .andExpect(jsonPath("$.[*].prebidMeetingAddressOriginal").value(hasItem(DEFAULT_PREBID_MEETING_ADDRESS_ORIGINAL.toString())))
            .andExpect(jsonPath("$.[*].prebidMeetingAddressRevised").value(hasItem(DEFAULT_PREBID_MEETING_ADDRESS_REVISED.toString())))
            .andExpect(jsonPath("$.[*].prequalValidityPeriodOriginal").value(hasItem(DEFAULT_PREQUAL_VALIDITY_PERIOD_ORIGINAL.toString())))
            .andExpect(jsonPath("$.[*].prequalValidityPeriodRevised").value(hasItem(DEFAULT_PREQUAL_VALIDITY_PERIOD_REVISED.toString())));
    }

    @Test
    @Transactional
    void getTenderCorrigendum() throws Exception {
        // Initialize the database
        tenderCorrigendumRepository.saveAndFlush(tenderCorrigendum);

        // Get the tenderCorrigendum
        restTenderCorrigendumMockMvc
            .perform(get(ENTITY_API_URL_ID, tenderCorrigendum.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tenderCorrigendum.getId().intValue()))
            .andExpect(jsonPath("$.nitId").value(DEFAULT_NIT_ID.intValue()))
            .andExpect(jsonPath("$.reason").value(DEFAULT_REASON))
            .andExpect(jsonPath("$.historyOrder").value(DEFAULT_HISTORY_ORDER))
            .andExpect(jsonPath("$.tenderDocCloseDateOriginal").value(DEFAULT_TENDER_DOC_CLOSE_DATE_ORIGINAL.toString()))
            .andExpect(jsonPath("$.tenderDocCloseDateRevised").value(DEFAULT_TENDER_DOC_CLOSE_DATE_REVISED.toString()))
            .andExpect(jsonPath("$.tenderReceiptCloseDateOriginal").value(DEFAULT_TENDER_RECEIPT_CLOSE_DATE_ORIGINAL.toString()))
            .andExpect(jsonPath("$.tenderReceiptCloseDateRevised").value(DEFAULT_TENDER_RECEIPT_CLOSE_DATE_REVISED.toString()))
            .andExpect(jsonPath("$.tenderQueryCloseDateOriginal").value(DEFAULT_TENDER_QUERY_CLOSE_DATE_ORIGINAL.toString()))
            .andExpect(jsonPath("$.tenderQueryCloseDateRevised").value(DEFAULT_TENDER_QUERY_CLOSE_DATE_REVISED.toString()))
            .andExpect(jsonPath("$.technicalBidOpenDateOriginal").value(DEFAULT_TECHNICAL_BID_OPEN_DATE_ORIGINAL.toString()))
            .andExpect(jsonPath("$.technicalBidOpenDateRevised").value(DEFAULT_TECHNICAL_BID_OPEN_DATE_REVISED.toString()))
            .andExpect(jsonPath("$.financialBidOpenDateOriginal").value(DEFAULT_FINANCIAL_BID_OPEN_DATE_ORIGINAL.toString()))
            .andExpect(jsonPath("$.financialBidOpenDateRevised").value(DEFAULT_FINANCIAL_BID_OPEN_DATE_REVISED.toString()))
            .andExpect(jsonPath("$.prequalBidOpenDateOriginal").value(DEFAULT_PREQUAL_BID_OPEN_DATE_ORIGINAL.toString()))
            .andExpect(jsonPath("$.prequalBidOpenDateRevised").value(DEFAULT_PREQUAL_BID_OPEN_DATE_REVISED.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.prequalTendeBidOpenOriginal").value(DEFAULT_PREQUAL_TENDE_BID_OPEN_ORIGINAL.toString()))
            .andExpect(jsonPath("$.prequalTenderBidOpenRevised").value(DEFAULT_PREQUAL_TENDER_BID_OPEN_REVISED.toString()))
            .andExpect(jsonPath("$.preBidMeetingDateOriginal").value(DEFAULT_PRE_BID_MEETING_DATE_ORIGINAL.toString()))
            .andExpect(jsonPath("$.preBidMeetingDateRevised").value(DEFAULT_PRE_BID_MEETING_DATE_REVISED.toString()))
            .andExpect(jsonPath("$.prebidMeetingAddressOriginal").value(DEFAULT_PREBID_MEETING_ADDRESS_ORIGINAL.toString()))
            .andExpect(jsonPath("$.prebidMeetingAddressRevised").value(DEFAULT_PREBID_MEETING_ADDRESS_REVISED.toString()))
            .andExpect(jsonPath("$.prequalValidityPeriodOriginal").value(DEFAULT_PREQUAL_VALIDITY_PERIOD_ORIGINAL.toString()))
            .andExpect(jsonPath("$.prequalValidityPeriodRevised").value(DEFAULT_PREQUAL_VALIDITY_PERIOD_REVISED.toString()));
    }

    @Test
    @Transactional
    void getNonExistingTenderCorrigendum() throws Exception {
        // Get the tenderCorrigendum
        restTenderCorrigendumMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTenderCorrigendum() throws Exception {
        // Initialize the database
        tenderCorrigendumRepository.saveAndFlush(tenderCorrigendum);

        int databaseSizeBeforeUpdate = tenderCorrigendumRepository.findAll().size();

        // Update the tenderCorrigendum
        TenderCorrigendum updatedTenderCorrigendum = tenderCorrigendumRepository.findById(tenderCorrigendum.getId()).get();
        // Disconnect from session so that the updates on updatedTenderCorrigendum are not directly saved in db
        em.detach(updatedTenderCorrigendum);
        updatedTenderCorrigendum
            .nitId(UPDATED_NIT_ID)
            .reason(UPDATED_REASON)
            .historyOrder(UPDATED_HISTORY_ORDER)
            .tenderDocCloseDateOriginal(UPDATED_TENDER_DOC_CLOSE_DATE_ORIGINAL)
            .tenderDocCloseDateRevised(UPDATED_TENDER_DOC_CLOSE_DATE_REVISED)
            .tenderReceiptCloseDateOriginal(UPDATED_TENDER_RECEIPT_CLOSE_DATE_ORIGINAL)
            .tenderReceiptCloseDateRevised(UPDATED_TENDER_RECEIPT_CLOSE_DATE_REVISED)
            .tenderQueryCloseDateOriginal(UPDATED_TENDER_QUERY_CLOSE_DATE_ORIGINAL)
            .tenderQueryCloseDateRevised(UPDATED_TENDER_QUERY_CLOSE_DATE_REVISED)
            .technicalBidOpenDateOriginal(UPDATED_TECHNICAL_BID_OPEN_DATE_ORIGINAL)
            .technicalBidOpenDateRevised(UPDATED_TECHNICAL_BID_OPEN_DATE_REVISED)
            .financialBidOpenDateOriginal(UPDATED_FINANCIAL_BID_OPEN_DATE_ORIGINAL)
            .financialBidOpenDateRevised(UPDATED_FINANCIAL_BID_OPEN_DATE_REVISED)
            .prequalBidOpenDateOriginal(UPDATED_PREQUAL_BID_OPEN_DATE_ORIGINAL)
            .prequalBidOpenDateRevised(UPDATED_PREQUAL_BID_OPEN_DATE_REVISED)
            .status(UPDATED_STATUS)
            .prequalTendeBidOpenOriginal(UPDATED_PREQUAL_TENDE_BID_OPEN_ORIGINAL)
            .prequalTenderBidOpenRevised(UPDATED_PREQUAL_TENDER_BID_OPEN_REVISED)
            .preBidMeetingDateOriginal(UPDATED_PRE_BID_MEETING_DATE_ORIGINAL)
            .preBidMeetingDateRevised(UPDATED_PRE_BID_MEETING_DATE_REVISED)
            .prebidMeetingAddressOriginal(UPDATED_PREBID_MEETING_ADDRESS_ORIGINAL)
            .prebidMeetingAddressRevised(UPDATED_PREBID_MEETING_ADDRESS_REVISED)
            .prequalValidityPeriodOriginal(UPDATED_PREQUAL_VALIDITY_PERIOD_ORIGINAL)
            .prequalValidityPeriodRevised(UPDATED_PREQUAL_VALIDITY_PERIOD_REVISED);
        TenderCorrigendumDTO tenderCorrigendumDTO = tenderCorrigendumMapper.toDto(updatedTenderCorrigendum);

        restTenderCorrigendumMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tenderCorrigendumDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCorrigendumDTO))
            )
            .andExpect(status().isOk());

        // Validate the TenderCorrigendum in the database
        List<TenderCorrigendum> tenderCorrigendumList = tenderCorrigendumRepository.findAll();
        assertThat(tenderCorrigendumList).hasSize(databaseSizeBeforeUpdate);
        TenderCorrigendum testTenderCorrigendum = tenderCorrigendumList.get(tenderCorrigendumList.size() - 1);
        assertThat(testTenderCorrigendum.getNitId()).isEqualTo(UPDATED_NIT_ID);
        assertThat(testTenderCorrigendum.getReason()).isEqualTo(UPDATED_REASON);
        assertThat(testTenderCorrigendum.getHistoryOrder()).isEqualTo(UPDATED_HISTORY_ORDER);
        assertThat(testTenderCorrigendum.getTenderDocCloseDateOriginal()).isEqualTo(UPDATED_TENDER_DOC_CLOSE_DATE_ORIGINAL);
        assertThat(testTenderCorrigendum.getTenderDocCloseDateRevised()).isEqualTo(UPDATED_TENDER_DOC_CLOSE_DATE_REVISED);
        assertThat(testTenderCorrigendum.getTenderReceiptCloseDateOriginal()).isEqualTo(UPDATED_TENDER_RECEIPT_CLOSE_DATE_ORIGINAL);
        assertThat(testTenderCorrigendum.getTenderReceiptCloseDateRevised()).isEqualTo(UPDATED_TENDER_RECEIPT_CLOSE_DATE_REVISED);
        assertThat(testTenderCorrigendum.getTenderQueryCloseDateOriginal()).isEqualTo(UPDATED_TENDER_QUERY_CLOSE_DATE_ORIGINAL);
        assertThat(testTenderCorrigendum.getTenderQueryCloseDateRevised()).isEqualTo(UPDATED_TENDER_QUERY_CLOSE_DATE_REVISED);
        assertThat(testTenderCorrigendum.getTechnicalBidOpenDateOriginal()).isEqualTo(UPDATED_TECHNICAL_BID_OPEN_DATE_ORIGINAL);
        assertThat(testTenderCorrigendum.getTechnicalBidOpenDateRevised()).isEqualTo(UPDATED_TECHNICAL_BID_OPEN_DATE_REVISED);
        assertThat(testTenderCorrigendum.getFinancialBidOpenDateOriginal()).isEqualTo(UPDATED_FINANCIAL_BID_OPEN_DATE_ORIGINAL);
        assertThat(testTenderCorrigendum.getFinancialBidOpenDateRevised()).isEqualTo(UPDATED_FINANCIAL_BID_OPEN_DATE_REVISED);
        assertThat(testTenderCorrigendum.getPrequalBidOpenDateOriginal()).isEqualTo(UPDATED_PREQUAL_BID_OPEN_DATE_ORIGINAL);
        assertThat(testTenderCorrigendum.getPrequalBidOpenDateRevised()).isEqualTo(UPDATED_PREQUAL_BID_OPEN_DATE_REVISED);
        assertThat(testTenderCorrigendum.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testTenderCorrigendum.getPrequalTendeBidOpenOriginal()).isEqualTo(UPDATED_PREQUAL_TENDE_BID_OPEN_ORIGINAL);
        assertThat(testTenderCorrigendum.getPrequalTenderBidOpenRevised()).isEqualTo(UPDATED_PREQUAL_TENDER_BID_OPEN_REVISED);
        assertThat(testTenderCorrigendum.getPreBidMeetingDateOriginal()).isEqualTo(UPDATED_PRE_BID_MEETING_DATE_ORIGINAL);
        assertThat(testTenderCorrigendum.getPreBidMeetingDateRevised()).isEqualTo(UPDATED_PRE_BID_MEETING_DATE_REVISED);
        assertThat(testTenderCorrigendum.getPrebidMeetingAddressOriginal()).isEqualTo(UPDATED_PREBID_MEETING_ADDRESS_ORIGINAL);
        assertThat(testTenderCorrigendum.getPrebidMeetingAddressRevised()).isEqualTo(UPDATED_PREBID_MEETING_ADDRESS_REVISED);
        assertThat(testTenderCorrigendum.getPrequalValidityPeriodOriginal()).isEqualTo(UPDATED_PREQUAL_VALIDITY_PERIOD_ORIGINAL);
        assertThat(testTenderCorrigendum.getPrequalValidityPeriodRevised()).isEqualTo(UPDATED_PREQUAL_VALIDITY_PERIOD_REVISED);
    }

    @Test
    @Transactional
    void putNonExistingTenderCorrigendum() throws Exception {
        int databaseSizeBeforeUpdate = tenderCorrigendumRepository.findAll().size();
        tenderCorrigendum.setId(count.incrementAndGet());

        // Create the TenderCorrigendum
        TenderCorrigendumDTO tenderCorrigendumDTO = tenderCorrigendumMapper.toDto(tenderCorrigendum);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTenderCorrigendumMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tenderCorrigendumDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCorrigendumDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderCorrigendum in the database
        List<TenderCorrigendum> tenderCorrigendumList = tenderCorrigendumRepository.findAll();
        assertThat(tenderCorrigendumList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTenderCorrigendum() throws Exception {
        int databaseSizeBeforeUpdate = tenderCorrigendumRepository.findAll().size();
        tenderCorrigendum.setId(count.incrementAndGet());

        // Create the TenderCorrigendum
        TenderCorrigendumDTO tenderCorrigendumDTO = tenderCorrigendumMapper.toDto(tenderCorrigendum);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderCorrigendumMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderCorrigendumDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderCorrigendum in the database
        List<TenderCorrigendum> tenderCorrigendumList = tenderCorrigendumRepository.findAll();
        assertThat(tenderCorrigendumList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTenderCorrigendum() throws Exception {
        int databaseSizeBeforeUpdate = tenderCorrigendumRepository.findAll().size();
        tenderCorrigendum.setId(count.incrementAndGet());

        // Create the TenderCorrigendum
        TenderCorrigendumDTO tenderCorrigendumDTO = tenderCorrigendumMapper.toDto(tenderCorrigendum);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderCorrigendumMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tenderCorrigendumDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TenderCorrigendum in the database
        List<TenderCorrigendum> tenderCorrigendumList = tenderCorrigendumRepository.findAll();
        assertThat(tenderCorrigendumList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTenderCorrigendumWithPatch() throws Exception {
        // Initialize the database
        tenderCorrigendumRepository.saveAndFlush(tenderCorrigendum);

        int databaseSizeBeforeUpdate = tenderCorrigendumRepository.findAll().size();

        // Update the tenderCorrigendum using partial update
        TenderCorrigendum partialUpdatedTenderCorrigendum = new TenderCorrigendum();
        partialUpdatedTenderCorrigendum.setId(tenderCorrigendum.getId());

        partialUpdatedTenderCorrigendum
            .reason(UPDATED_REASON)
            .historyOrder(UPDATED_HISTORY_ORDER)
            .tenderDocCloseDateOriginal(UPDATED_TENDER_DOC_CLOSE_DATE_ORIGINAL)
            .technicalBidOpenDateRevised(UPDATED_TECHNICAL_BID_OPEN_DATE_REVISED)
            .financialBidOpenDateOriginal(UPDATED_FINANCIAL_BID_OPEN_DATE_ORIGINAL)
            .preBidMeetingDateRevised(UPDATED_PRE_BID_MEETING_DATE_REVISED);

        restTenderCorrigendumMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTenderCorrigendum.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTenderCorrigendum))
            )
            .andExpect(status().isOk());

        // Validate the TenderCorrigendum in the database
        List<TenderCorrigendum> tenderCorrigendumList = tenderCorrigendumRepository.findAll();
        assertThat(tenderCorrigendumList).hasSize(databaseSizeBeforeUpdate);
        TenderCorrigendum testTenderCorrigendum = tenderCorrigendumList.get(tenderCorrigendumList.size() - 1);
        assertThat(testTenderCorrigendum.getNitId()).isEqualTo(DEFAULT_NIT_ID);
        assertThat(testTenderCorrigendum.getReason()).isEqualTo(UPDATED_REASON);
        assertThat(testTenderCorrigendum.getHistoryOrder()).isEqualTo(UPDATED_HISTORY_ORDER);
        assertThat(testTenderCorrigendum.getTenderDocCloseDateOriginal()).isEqualTo(UPDATED_TENDER_DOC_CLOSE_DATE_ORIGINAL);
        assertThat(testTenderCorrigendum.getTenderDocCloseDateRevised()).isEqualTo(DEFAULT_TENDER_DOC_CLOSE_DATE_REVISED);
        assertThat(testTenderCorrigendum.getTenderReceiptCloseDateOriginal()).isEqualTo(DEFAULT_TENDER_RECEIPT_CLOSE_DATE_ORIGINAL);
        assertThat(testTenderCorrigendum.getTenderReceiptCloseDateRevised()).isEqualTo(DEFAULT_TENDER_RECEIPT_CLOSE_DATE_REVISED);
        assertThat(testTenderCorrigendum.getTenderQueryCloseDateOriginal()).isEqualTo(DEFAULT_TENDER_QUERY_CLOSE_DATE_ORIGINAL);
        assertThat(testTenderCorrigendum.getTenderQueryCloseDateRevised()).isEqualTo(DEFAULT_TENDER_QUERY_CLOSE_DATE_REVISED);
        assertThat(testTenderCorrigendum.getTechnicalBidOpenDateOriginal()).isEqualTo(DEFAULT_TECHNICAL_BID_OPEN_DATE_ORIGINAL);
        assertThat(testTenderCorrigendum.getTechnicalBidOpenDateRevised()).isEqualTo(UPDATED_TECHNICAL_BID_OPEN_DATE_REVISED);
        assertThat(testTenderCorrigendum.getFinancialBidOpenDateOriginal()).isEqualTo(UPDATED_FINANCIAL_BID_OPEN_DATE_ORIGINAL);
        assertThat(testTenderCorrigendum.getFinancialBidOpenDateRevised()).isEqualTo(DEFAULT_FINANCIAL_BID_OPEN_DATE_REVISED);
        assertThat(testTenderCorrigendum.getPrequalBidOpenDateOriginal()).isEqualTo(DEFAULT_PREQUAL_BID_OPEN_DATE_ORIGINAL);
        assertThat(testTenderCorrigendum.getPrequalBidOpenDateRevised()).isEqualTo(DEFAULT_PREQUAL_BID_OPEN_DATE_REVISED);
        assertThat(testTenderCorrigendum.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testTenderCorrigendum.getPrequalTendeBidOpenOriginal()).isEqualTo(DEFAULT_PREQUAL_TENDE_BID_OPEN_ORIGINAL);
        assertThat(testTenderCorrigendum.getPrequalTenderBidOpenRevised()).isEqualTo(DEFAULT_PREQUAL_TENDER_BID_OPEN_REVISED);
        assertThat(testTenderCorrigendum.getPreBidMeetingDateOriginal()).isEqualTo(DEFAULT_PRE_BID_MEETING_DATE_ORIGINAL);
        assertThat(testTenderCorrigendum.getPreBidMeetingDateRevised()).isEqualTo(UPDATED_PRE_BID_MEETING_DATE_REVISED);
        assertThat(testTenderCorrigendum.getPrebidMeetingAddressOriginal()).isEqualTo(DEFAULT_PREBID_MEETING_ADDRESS_ORIGINAL);
        assertThat(testTenderCorrigendum.getPrebidMeetingAddressRevised()).isEqualTo(DEFAULT_PREBID_MEETING_ADDRESS_REVISED);
        assertThat(testTenderCorrigendum.getPrequalValidityPeriodOriginal()).isEqualTo(DEFAULT_PREQUAL_VALIDITY_PERIOD_ORIGINAL);
        assertThat(testTenderCorrigendum.getPrequalValidityPeriodRevised()).isEqualTo(DEFAULT_PREQUAL_VALIDITY_PERIOD_REVISED);
    }

    @Test
    @Transactional
    void fullUpdateTenderCorrigendumWithPatch() throws Exception {
        // Initialize the database
        tenderCorrigendumRepository.saveAndFlush(tenderCorrigendum);

        int databaseSizeBeforeUpdate = tenderCorrigendumRepository.findAll().size();

        // Update the tenderCorrigendum using partial update
        TenderCorrigendum partialUpdatedTenderCorrigendum = new TenderCorrigendum();
        partialUpdatedTenderCorrigendum.setId(tenderCorrigendum.getId());

        partialUpdatedTenderCorrigendum
            .nitId(UPDATED_NIT_ID)
            .reason(UPDATED_REASON)
            .historyOrder(UPDATED_HISTORY_ORDER)
            .tenderDocCloseDateOriginal(UPDATED_TENDER_DOC_CLOSE_DATE_ORIGINAL)
            .tenderDocCloseDateRevised(UPDATED_TENDER_DOC_CLOSE_DATE_REVISED)
            .tenderReceiptCloseDateOriginal(UPDATED_TENDER_RECEIPT_CLOSE_DATE_ORIGINAL)
            .tenderReceiptCloseDateRevised(UPDATED_TENDER_RECEIPT_CLOSE_DATE_REVISED)
            .tenderQueryCloseDateOriginal(UPDATED_TENDER_QUERY_CLOSE_DATE_ORIGINAL)
            .tenderQueryCloseDateRevised(UPDATED_TENDER_QUERY_CLOSE_DATE_REVISED)
            .technicalBidOpenDateOriginal(UPDATED_TECHNICAL_BID_OPEN_DATE_ORIGINAL)
            .technicalBidOpenDateRevised(UPDATED_TECHNICAL_BID_OPEN_DATE_REVISED)
            .financialBidOpenDateOriginal(UPDATED_FINANCIAL_BID_OPEN_DATE_ORIGINAL)
            .financialBidOpenDateRevised(UPDATED_FINANCIAL_BID_OPEN_DATE_REVISED)
            .prequalBidOpenDateOriginal(UPDATED_PREQUAL_BID_OPEN_DATE_ORIGINAL)
            .prequalBidOpenDateRevised(UPDATED_PREQUAL_BID_OPEN_DATE_REVISED)
            .status(UPDATED_STATUS)
            .prequalTendeBidOpenOriginal(UPDATED_PREQUAL_TENDE_BID_OPEN_ORIGINAL)
            .prequalTenderBidOpenRevised(UPDATED_PREQUAL_TENDER_BID_OPEN_REVISED)
            .preBidMeetingDateOriginal(UPDATED_PRE_BID_MEETING_DATE_ORIGINAL)
            .preBidMeetingDateRevised(UPDATED_PRE_BID_MEETING_DATE_REVISED)
            .prebidMeetingAddressOriginal(UPDATED_PREBID_MEETING_ADDRESS_ORIGINAL)
            .prebidMeetingAddressRevised(UPDATED_PREBID_MEETING_ADDRESS_REVISED)
            .prequalValidityPeriodOriginal(UPDATED_PREQUAL_VALIDITY_PERIOD_ORIGINAL)
            .prequalValidityPeriodRevised(UPDATED_PREQUAL_VALIDITY_PERIOD_REVISED);

        restTenderCorrigendumMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTenderCorrigendum.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTenderCorrigendum))
            )
            .andExpect(status().isOk());

        // Validate the TenderCorrigendum in the database
        List<TenderCorrigendum> tenderCorrigendumList = tenderCorrigendumRepository.findAll();
        assertThat(tenderCorrigendumList).hasSize(databaseSizeBeforeUpdate);
        TenderCorrigendum testTenderCorrigendum = tenderCorrigendumList.get(tenderCorrigendumList.size() - 1);
        assertThat(testTenderCorrigendum.getNitId()).isEqualTo(UPDATED_NIT_ID);
        assertThat(testTenderCorrigendum.getReason()).isEqualTo(UPDATED_REASON);
        assertThat(testTenderCorrigendum.getHistoryOrder()).isEqualTo(UPDATED_HISTORY_ORDER);
        assertThat(testTenderCorrigendum.getTenderDocCloseDateOriginal()).isEqualTo(UPDATED_TENDER_DOC_CLOSE_DATE_ORIGINAL);
        assertThat(testTenderCorrigendum.getTenderDocCloseDateRevised()).isEqualTo(UPDATED_TENDER_DOC_CLOSE_DATE_REVISED);
        assertThat(testTenderCorrigendum.getTenderReceiptCloseDateOriginal()).isEqualTo(UPDATED_TENDER_RECEIPT_CLOSE_DATE_ORIGINAL);
        assertThat(testTenderCorrigendum.getTenderReceiptCloseDateRevised()).isEqualTo(UPDATED_TENDER_RECEIPT_CLOSE_DATE_REVISED);
        assertThat(testTenderCorrigendum.getTenderQueryCloseDateOriginal()).isEqualTo(UPDATED_TENDER_QUERY_CLOSE_DATE_ORIGINAL);
        assertThat(testTenderCorrigendum.getTenderQueryCloseDateRevised()).isEqualTo(UPDATED_TENDER_QUERY_CLOSE_DATE_REVISED);
        assertThat(testTenderCorrigendum.getTechnicalBidOpenDateOriginal()).isEqualTo(UPDATED_TECHNICAL_BID_OPEN_DATE_ORIGINAL);
        assertThat(testTenderCorrigendum.getTechnicalBidOpenDateRevised()).isEqualTo(UPDATED_TECHNICAL_BID_OPEN_DATE_REVISED);
        assertThat(testTenderCorrigendum.getFinancialBidOpenDateOriginal()).isEqualTo(UPDATED_FINANCIAL_BID_OPEN_DATE_ORIGINAL);
        assertThat(testTenderCorrigendum.getFinancialBidOpenDateRevised()).isEqualTo(UPDATED_FINANCIAL_BID_OPEN_DATE_REVISED);
        assertThat(testTenderCorrigendum.getPrequalBidOpenDateOriginal()).isEqualTo(UPDATED_PREQUAL_BID_OPEN_DATE_ORIGINAL);
        assertThat(testTenderCorrigendum.getPrequalBidOpenDateRevised()).isEqualTo(UPDATED_PREQUAL_BID_OPEN_DATE_REVISED);
        assertThat(testTenderCorrigendum.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testTenderCorrigendum.getPrequalTendeBidOpenOriginal()).isEqualTo(UPDATED_PREQUAL_TENDE_BID_OPEN_ORIGINAL);
        assertThat(testTenderCorrigendum.getPrequalTenderBidOpenRevised()).isEqualTo(UPDATED_PREQUAL_TENDER_BID_OPEN_REVISED);
        assertThat(testTenderCorrigendum.getPreBidMeetingDateOriginal()).isEqualTo(UPDATED_PRE_BID_MEETING_DATE_ORIGINAL);
        assertThat(testTenderCorrigendum.getPreBidMeetingDateRevised()).isEqualTo(UPDATED_PRE_BID_MEETING_DATE_REVISED);
        assertThat(testTenderCorrigendum.getPrebidMeetingAddressOriginal()).isEqualTo(UPDATED_PREBID_MEETING_ADDRESS_ORIGINAL);
        assertThat(testTenderCorrigendum.getPrebidMeetingAddressRevised()).isEqualTo(UPDATED_PREBID_MEETING_ADDRESS_REVISED);
        assertThat(testTenderCorrigendum.getPrequalValidityPeriodOriginal()).isEqualTo(UPDATED_PREQUAL_VALIDITY_PERIOD_ORIGINAL);
        assertThat(testTenderCorrigendum.getPrequalValidityPeriodRevised()).isEqualTo(UPDATED_PREQUAL_VALIDITY_PERIOD_REVISED);
    }

    @Test
    @Transactional
    void patchNonExistingTenderCorrigendum() throws Exception {
        int databaseSizeBeforeUpdate = tenderCorrigendumRepository.findAll().size();
        tenderCorrigendum.setId(count.incrementAndGet());

        // Create the TenderCorrigendum
        TenderCorrigendumDTO tenderCorrigendumDTO = tenderCorrigendumMapper.toDto(tenderCorrigendum);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTenderCorrigendumMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tenderCorrigendumDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderCorrigendumDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderCorrigendum in the database
        List<TenderCorrigendum> tenderCorrigendumList = tenderCorrigendumRepository.findAll();
        assertThat(tenderCorrigendumList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTenderCorrigendum() throws Exception {
        int databaseSizeBeforeUpdate = tenderCorrigendumRepository.findAll().size();
        tenderCorrigendum.setId(count.incrementAndGet());

        // Create the TenderCorrigendum
        TenderCorrigendumDTO tenderCorrigendumDTO = tenderCorrigendumMapper.toDto(tenderCorrigendum);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderCorrigendumMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderCorrigendumDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderCorrigendum in the database
        List<TenderCorrigendum> tenderCorrigendumList = tenderCorrigendumRepository.findAll();
        assertThat(tenderCorrigendumList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTenderCorrigendum() throws Exception {
        int databaseSizeBeforeUpdate = tenderCorrigendumRepository.findAll().size();
        tenderCorrigendum.setId(count.incrementAndGet());

        // Create the TenderCorrigendum
        TenderCorrigendumDTO tenderCorrigendumDTO = tenderCorrigendumMapper.toDto(tenderCorrigendum);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderCorrigendumMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderCorrigendumDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TenderCorrigendum in the database
        List<TenderCorrigendum> tenderCorrigendumList = tenderCorrigendumRepository.findAll();
        assertThat(tenderCorrigendumList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTenderCorrigendum() throws Exception {
        // Initialize the database
        tenderCorrigendumRepository.saveAndFlush(tenderCorrigendum);

        int databaseSizeBeforeDelete = tenderCorrigendumRepository.findAll().size();

        // Delete the tenderCorrigendum
        restTenderCorrigendumMockMvc
            .perform(delete(ENTITY_API_URL_ID, tenderCorrigendum.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TenderCorrigendum> tenderCorrigendumList = tenderCorrigendumRepository.findAll();
        assertThat(tenderCorrigendumList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
