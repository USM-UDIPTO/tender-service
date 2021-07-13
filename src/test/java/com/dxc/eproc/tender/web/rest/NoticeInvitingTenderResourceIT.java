package com.dxc.eproc.tender.web.rest;

import static com.dxc.eproc.tender.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dxc.eproc.tender.IntegrationTest;
import com.dxc.eproc.tender.domain.NoticeInvitingTender;
import com.dxc.eproc.tender.repository.NoticeInvitingTenderRepository;
import com.dxc.eproc.tender.service.dto.NoticeInvitingTenderDTO;
import com.dxc.eproc.tender.service.mapper.NoticeInvitingTenderMapper;
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
 * Integration tests for the {@link NoticeInvitingTenderResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class NoticeInvitingTenderResourceIT {

    private static final Integer DEFAULT_EVALUATION_TYPE = 1;
    private static final Integer UPDATED_EVALUATION_TYPE = 2;

    private static final LocalDate DEFAULT_PREQUAL_VALIDITY_PERIOD = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PREQUAL_VALIDITY_PERIOD = LocalDate.now(ZoneId.systemDefault());

    private static final Boolean DEFAULT_INVITING_STRATEGY = false;
    private static final Boolean UPDATED_INVITING_STRATEGY = true;

    private static final Integer DEFAULT_MIN_NO_BIDS_RECEIVE = 1;
    private static final Integer UPDATED_MIN_NO_BIDS_RECEIVE = 2;

    private static final Integer DEFAULT_PROCURING_ENTITY = 1;
    private static final Integer UPDATED_PROCURING_ENTITY = 2;

    private static final Integer DEFAULT_PROCURING_ENTITY_DESIGNATION = 1;
    private static final Integer UPDATED_PROCURING_ENTITY_DESIGNATION = 2;

    private static final Integer DEFAULT_PROCURING_ENTITY_ADDRESS = 1;
    private static final Integer UPDATED_PROCURING_ENTITY_ADDRESS = 2;

    private static final Integer DEFAULT_INVITING_AUTHORITY = 1;
    private static final Integer UPDATED_INVITING_AUTHORITY = 2;

    private static final Integer DEFAULT_INVITING_AUTHORITY_DESIGNATION = 1;
    private static final Integer UPDATED_INVITING_AUTHORITY_DESIGNATION = 2;

    private static final Integer DEFAULT_INVITING_AUTHORITY_ADDRESS = 1;
    private static final Integer UPDATED_INVITING_AUTHORITY_ADDRESS = 2;

    private static final Boolean DEFAULT_PARTICIPATING_CONDN_YN = false;
    private static final Boolean UPDATED_PARTICIPATING_CONDN_YN = true;

    private static final BigDecimal DEFAULT_TENDER_FEE = new BigDecimal(1);
    private static final BigDecimal UPDATED_TENDER_FEE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_EMD = new BigDecimal(1);
    private static final BigDecimal UPDATED_EMD = new BigDecimal(2);

    private static final Boolean DEFAULT_BID_VALUE_TYPE = false;
    private static final Boolean UPDATED_BID_VALUE_TYPE = true;

    private static final Integer DEFAULT_TECH_WEIGHTAGE = 1;
    private static final Integer UPDATED_TECH_WEIGHTAGE = 2;

    private static final LocalDate DEFAULT_PREQUAL_TENDER_BID_OPEN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PREQUAL_TENDER_BID_OPEN = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_TENDER_DOC_CLOSE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TENDER_DOC_CLOSE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_TENDER_RECEIPT_CLOSE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TENDER_RECEIPT_CLOSE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_TENDER_QUERY_CLOSE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TENDER_QUERY_CLOSE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_TECHNICAL_BID_OPEN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TECHNICAL_BID_OPEN = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_FINANCIAL_BID_OPEN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FINANCIAL_BID_OPEN = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_PUBLISHED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PUBLISHED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_PUBLISHED_BY = 1;
    private static final Integer UPDATED_PUBLISHED_BY = 2;

    private static final LocalDate DEFAULT_RECALLED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_RECALLED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_RECALLED_BY = 1;
    private static final Integer UPDATED_RECALLED_BY = 2;

    private static final LocalDate DEFAULT_BID_SUBMISSION_START_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_BID_SUBMISSION_START_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_BID_VALIDITY_PERIOD = 1;
    private static final Integer UPDATED_BID_VALIDITY_PERIOD = 2;

    private static final Integer DEFAULT_NO_OF_CALLS = 1;
    private static final Integer UPDATED_NO_OF_CALLS = 2;

    private static final LocalDate DEFAULT_PRE_BID_MEETING_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PRE_BID_MEETING_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Boolean DEFAULT_PRE_BID_MEETING_YN = false;
    private static final Boolean UPDATED_PRE_BID_MEETING_YN = true;

    private static final Integer DEFAULT_PREBID_MEETING_ADDRESS = 1;
    private static final Integer UPDATED_PREBID_MEETING_ADDRESS = 2;

    private static final LocalDate DEFAULT_PRE_QUALIFICATION_BID_OPEN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PRE_QUALIFICATION_BID_OPEN = LocalDate.now(ZoneId.systemDefault());

    private static final Boolean DEFAULT_MANDATE_ALL_ITEMS_YN = false;
    private static final Boolean UPDATED_MANDATE_ALL_ITEMS_YN = true;

    private static final Boolean DEFAULT_QUERIES_PUBLISHED = false;
    private static final Boolean UPDATED_QUERIES_PUBLISHED = true;

    private static final String DEFAULT_DENOMINATION_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_DENOMINATION_TYPE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_RETENDERED_YN = false;
    private static final Boolean UPDATED_RETENDERED_YN = true;

    private static final String DEFAULT_PERCENTAGE_RATE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_PERCENTAGE_RATE_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_PERSON = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_PERSON = "BBBBBBBBBB";

    private static final Boolean DEFAULT_SPLIT_EMD_REQUIRED_YN = false;
    private static final Boolean UPDATED_SPLIT_EMD_REQUIRED_YN = true;

    private static final BigDecimal DEFAULT_EMD_BANK_GUARANTEE = new BigDecimal(1);
    private static final BigDecimal UPDATED_EMD_BANK_GUARANTEE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_EMD_CASH = new BigDecimal(1);
    private static final BigDecimal UPDATED_EMD_CASH = new BigDecimal(2);

    private static final Integer DEFAULT_BG_VALIDITY_PERIOD = 1;
    private static final Integer UPDATED_BG_VALIDITY_PERIOD = 2;

    private static final Boolean DEFAULT_HIDE_WEIGHTAGE = false;
    private static final Boolean UPDATED_HIDE_WEIGHTAGE = true;

    private static final Boolean DEFAULT_ITEMWISE_TECH_EVAL_YN = false;
    private static final Boolean UPDATED_ITEMWISE_TECH_EVAL_YN = true;

    private static final Boolean DEFAULT_IS_MULTIPLE_SUPPLIER_SELECTION_ALLOWED = false;
    private static final Boolean UPDATED_IS_MULTIPLE_SUPPLIER_SELECTION_ALLOWED = true;

    private static final LocalDate DEFAULT_TECH_EVAL_START_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TECH_EVAL_START_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_TECH_EVAL_END_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TECH_EVAL_END_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_COMM_EVAL_START_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_COMM_EVAL_START_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_COMM_EVAL_END_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_COMM_EVAL_END_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_EMD_VERIFIED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_EMD_VERIFIED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Boolean DEFAULT_MULTIPLE_CURRENCY_SELECTION_ALLOWED_YN = false;
    private static final Boolean UPDATED_MULTIPLE_CURRENCY_SELECTION_ALLOWED_YN = true;

    private static final Boolean DEFAULT_IS_EDITABLE = false;
    private static final Boolean UPDATED_IS_EDITABLE = true;

    private static final Boolean DEFAULT_IS_EVALUATED = false;
    private static final Boolean UPDATED_IS_EVALUATED = true;

    private static final Boolean DEFAULT_IS_TECH_WEIGHTAGE_ALLOWED = false;
    private static final Boolean UPDATED_IS_TECH_WEIGHTAGE_ALLOWED = true;

    private static final Boolean DEFAULT_IS_TECH_WEIGHTAGE_COMPLETED = false;
    private static final Boolean UPDATED_IS_TECH_WEIGHTAGE_COMPLETED = true;

    private static final Boolean DEFAULT_IS_COMMERCIAL_BID_EDIT_COMPLETED = false;
    private static final Boolean UPDATED_IS_COMMERCIAL_BID_EDIT_COMPLETED = true;

    private static final Boolean DEFAULT_TEMPLATE_YN = false;
    private static final Boolean UPDATED_TEMPLATE_YN = true;

    private static final Long DEFAULT_TEMPLATE_ID = 1L;
    private static final Long UPDATED_TEMPLATE_ID = 2L;

    private static final Integer DEFAULT_PAYMENT_VERIFIED_BY = 1;
    private static final Integer UPDATED_PAYMENT_VERIFIED_BY = 2;

    private static final LocalDate DEFAULT_PAYMENT_VERIFIED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PAYMENT_VERIFIED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Boolean DEFAULT_IS_ITEMWISE_CSR_REQUIRED = false;
    private static final Boolean UPDATED_IS_ITEMWISE_CSR_REQUIRED = true;

    private static final Boolean DEFAULT_IS_BID_VIEW_ENABLED = false;
    private static final Boolean UPDATED_IS_BID_VIEW_ENABLED = true;

    private static final Boolean DEFAULT_IS_NEGOTIATION_REQUIRED = false;
    private static final Boolean UPDATED_IS_NEGOTIATION_REQUIRED = true;

    private static final Boolean DEFAULT_HIGHEST_BIDDER_SELECTION = false;
    private static final Boolean UPDATED_HIGHEST_BIDDER_SELECTION = true;

    private static final Boolean DEFAULT_IS_VARIABLE_EMD_ALLOWED = false;
    private static final Boolean UPDATED_IS_VARIABLE_EMD_ALLOWED = true;

    private static final Integer DEFAULT_NIT_PUBLISHER_CERT_ID = 1;
    private static final Integer UPDATED_NIT_PUBLISHER_CERT_ID = 2;

    private static final Boolean DEFAULT_AUTO_EXTEND_YN = false;
    private static final Boolean UPDATED_AUTO_EXTEND_YN = true;

    private static final Integer DEFAULT_NO_OF_DAYS_EXTEND = 1;
    private static final Integer UPDATED_NO_OF_DAYS_EXTEND = 2;

    private static final Boolean DEFAULT_IS_EXTENSION_AVAILABLE = false;
    private static final Boolean UPDATED_IS_EXTENSION_AVAILABLE = true;

    private static final Boolean DEFAULT_SPECIAL_SCHEME_TENDER = false;
    private static final Boolean UPDATED_SPECIAL_SCHEME_TENDER = true;

    private static final Boolean DEFAULT_IS_BID_VALIDITY_EXPIRY_TASK_CREATED = false;
    private static final Boolean UPDATED_IS_BID_VALIDITY_EXPIRY_TASK_CREATED = true;

    private static final Boolean DEFAULT_EVALUATION_TYPE_FLAG = false;
    private static final Boolean UPDATED_EVALUATION_TYPE_FLAG = true;

    private static final Boolean DEFAULT_QCBS_TENDER_YN = false;
    private static final Boolean UPDATED_QCBS_TENDER_YN = true;

    private static final String DEFAULT_PUBLISHED_USER = "AAAAAAAAAA";
    private static final String UPDATED_PUBLISHED_USER = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_WORLD_BANK_FUNDED = false;
    private static final Boolean UPDATED_IS_WORLD_BANK_FUNDED = true;

    private static final String DEFAULT_EC_CLEARANCE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_EC_CLEARANCE_NUMBER = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_EC_CLEARANCE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_EC_CLEARANCE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/notice-inviting-tenders";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private NoticeInvitingTenderRepository noticeInvitingTenderRepository;

    @Autowired
    private NoticeInvitingTenderMapper noticeInvitingTenderMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNoticeInvitingTenderMockMvc;

    private NoticeInvitingTender noticeInvitingTender;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NoticeInvitingTender createEntity(EntityManager em) {
        NoticeInvitingTender noticeInvitingTender = new NoticeInvitingTender()
            .evaluationType(DEFAULT_EVALUATION_TYPE)
            .prequalValidityPeriod(DEFAULT_PREQUAL_VALIDITY_PERIOD)
            .invitingStrategy(DEFAULT_INVITING_STRATEGY)
            .minNoBidsReceive(DEFAULT_MIN_NO_BIDS_RECEIVE)
            .procuringEntity(DEFAULT_PROCURING_ENTITY)
            .procuringEntityDesignation(DEFAULT_PROCURING_ENTITY_DESIGNATION)
            .procuringEntityAddress(DEFAULT_PROCURING_ENTITY_ADDRESS)
            .invitingAuthority(DEFAULT_INVITING_AUTHORITY)
            .invitingAuthorityDesignation(DEFAULT_INVITING_AUTHORITY_DESIGNATION)
            .invitingAuthorityAddress(DEFAULT_INVITING_AUTHORITY_ADDRESS)
            .participatingCondnYn(DEFAULT_PARTICIPATING_CONDN_YN)
            .tenderFee(DEFAULT_TENDER_FEE)
            .emd(DEFAULT_EMD)
            .bidValueType(DEFAULT_BID_VALUE_TYPE)
            .techWeightage(DEFAULT_TECH_WEIGHTAGE)
            .prequalTenderBidOpen(DEFAULT_PREQUAL_TENDER_BID_OPEN)
            .tenderDocClose(DEFAULT_TENDER_DOC_CLOSE)
            .tenderReceiptClose(DEFAULT_TENDER_RECEIPT_CLOSE)
            .tenderQueryClose(DEFAULT_TENDER_QUERY_CLOSE)
            .technicalBidOpen(DEFAULT_TECHNICAL_BID_OPEN)
            .financialBidOpen(DEFAULT_FINANCIAL_BID_OPEN)
            .publishedDate(DEFAULT_PUBLISHED_DATE)
            .publishedBy(DEFAULT_PUBLISHED_BY)
            .recalledDate(DEFAULT_RECALLED_DATE)
            .recalledBy(DEFAULT_RECALLED_BY)
            .bidSubmissionStartDate(DEFAULT_BID_SUBMISSION_START_DATE)
            .bidValidityPeriod(DEFAULT_BID_VALIDITY_PERIOD)
            .noOfCalls(DEFAULT_NO_OF_CALLS)
            .preBidMeetingDate(DEFAULT_PRE_BID_MEETING_DATE)
            .preBidMeetingYn(DEFAULT_PRE_BID_MEETING_YN)
            .prebidMeetingAddress(DEFAULT_PREBID_MEETING_ADDRESS)
            .preQualificationBidOpen(DEFAULT_PRE_QUALIFICATION_BID_OPEN)
            .mandateAllItemsYn(DEFAULT_MANDATE_ALL_ITEMS_YN)
            .queriesPublished(DEFAULT_QUERIES_PUBLISHED)
            .denominationType(DEFAULT_DENOMINATION_TYPE)
            .retenderedYn(DEFAULT_RETENDERED_YN)
            .percentageRateType(DEFAULT_PERCENTAGE_RATE_TYPE)
            .contactPerson(DEFAULT_CONTACT_PERSON)
            .splitEmdRequiredYn(DEFAULT_SPLIT_EMD_REQUIRED_YN)
            .emdBankGuarantee(DEFAULT_EMD_BANK_GUARANTEE)
            .emdCash(DEFAULT_EMD_CASH)
            .bgValidityPeriod(DEFAULT_BG_VALIDITY_PERIOD)
            .hideWeightage(DEFAULT_HIDE_WEIGHTAGE)
            .itemwiseTechEvalYn(DEFAULT_ITEMWISE_TECH_EVAL_YN)
            .isMultipleSupplierSelectionAllowed(DEFAULT_IS_MULTIPLE_SUPPLIER_SELECTION_ALLOWED)
            .techEvalStartDate(DEFAULT_TECH_EVAL_START_DATE)
            .techEvalEndDate(DEFAULT_TECH_EVAL_END_DATE)
            .commEvalStartDate(DEFAULT_COMM_EVAL_START_DATE)
            .commEvalEndDate(DEFAULT_COMM_EVAL_END_DATE)
            .emdVerifiedDate(DEFAULT_EMD_VERIFIED_DATE)
            .multipleCurrencySelectionAllowedYn(DEFAULT_MULTIPLE_CURRENCY_SELECTION_ALLOWED_YN)
            .isEditable(DEFAULT_IS_EDITABLE)
            .isEvaluated(DEFAULT_IS_EVALUATED)
            .isTechWeightageAllowed(DEFAULT_IS_TECH_WEIGHTAGE_ALLOWED)
            .isTechWeightageCompleted(DEFAULT_IS_TECH_WEIGHTAGE_COMPLETED)
            .isCommercialBidEditCompleted(DEFAULT_IS_COMMERCIAL_BID_EDIT_COMPLETED)
            .templateYn(DEFAULT_TEMPLATE_YN)
            .templateId(DEFAULT_TEMPLATE_ID)
            .paymentVerifiedBy(DEFAULT_PAYMENT_VERIFIED_BY)
            .paymentVerifiedDate(DEFAULT_PAYMENT_VERIFIED_DATE)
            .isItemwiseCsrRequired(DEFAULT_IS_ITEMWISE_CSR_REQUIRED)
            .isBidViewEnabled(DEFAULT_IS_BID_VIEW_ENABLED)
            .isNegotiationRequired(DEFAULT_IS_NEGOTIATION_REQUIRED)
            .highestBidderSelection(DEFAULT_HIGHEST_BIDDER_SELECTION)
            .isVariableEmdAllowed(DEFAULT_IS_VARIABLE_EMD_ALLOWED)
            .nitPublisherCertId(DEFAULT_NIT_PUBLISHER_CERT_ID)
            .autoExtendYn(DEFAULT_AUTO_EXTEND_YN)
            .noOfDaysExtend(DEFAULT_NO_OF_DAYS_EXTEND)
            .isExtensionAvailable(DEFAULT_IS_EXTENSION_AVAILABLE)
            .specialSchemeTender(DEFAULT_SPECIAL_SCHEME_TENDER)
            .isBidValidityExpiryTaskCreated(DEFAULT_IS_BID_VALIDITY_EXPIRY_TASK_CREATED)
            .evaluationTypeFlag(DEFAULT_EVALUATION_TYPE_FLAG)
            .qcbsTenderYn(DEFAULT_QCBS_TENDER_YN)
            .publishedUser(DEFAULT_PUBLISHED_USER)
            .isWorldBankFunded(DEFAULT_IS_WORLD_BANK_FUNDED)
            .ecClearanceNumber(DEFAULT_EC_CLEARANCE_NUMBER)
            .ecClearanceDate(DEFAULT_EC_CLEARANCE_DATE);
        return noticeInvitingTender;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NoticeInvitingTender createUpdatedEntity(EntityManager em) {
        NoticeInvitingTender noticeInvitingTender = new NoticeInvitingTender()
            .evaluationType(UPDATED_EVALUATION_TYPE)
            .prequalValidityPeriod(UPDATED_PREQUAL_VALIDITY_PERIOD)
            .invitingStrategy(UPDATED_INVITING_STRATEGY)
            .minNoBidsReceive(UPDATED_MIN_NO_BIDS_RECEIVE)
            .procuringEntity(UPDATED_PROCURING_ENTITY)
            .procuringEntityDesignation(UPDATED_PROCURING_ENTITY_DESIGNATION)
            .procuringEntityAddress(UPDATED_PROCURING_ENTITY_ADDRESS)
            .invitingAuthority(UPDATED_INVITING_AUTHORITY)
            .invitingAuthorityDesignation(UPDATED_INVITING_AUTHORITY_DESIGNATION)
            .invitingAuthorityAddress(UPDATED_INVITING_AUTHORITY_ADDRESS)
            .participatingCondnYn(UPDATED_PARTICIPATING_CONDN_YN)
            .tenderFee(UPDATED_TENDER_FEE)
            .emd(UPDATED_EMD)
            .bidValueType(UPDATED_BID_VALUE_TYPE)
            .techWeightage(UPDATED_TECH_WEIGHTAGE)
            .prequalTenderBidOpen(UPDATED_PREQUAL_TENDER_BID_OPEN)
            .tenderDocClose(UPDATED_TENDER_DOC_CLOSE)
            .tenderReceiptClose(UPDATED_TENDER_RECEIPT_CLOSE)
            .tenderQueryClose(UPDATED_TENDER_QUERY_CLOSE)
            .technicalBidOpen(UPDATED_TECHNICAL_BID_OPEN)
            .financialBidOpen(UPDATED_FINANCIAL_BID_OPEN)
            .publishedDate(UPDATED_PUBLISHED_DATE)
            .publishedBy(UPDATED_PUBLISHED_BY)
            .recalledDate(UPDATED_RECALLED_DATE)
            .recalledBy(UPDATED_RECALLED_BY)
            .bidSubmissionStartDate(UPDATED_BID_SUBMISSION_START_DATE)
            .bidValidityPeriod(UPDATED_BID_VALIDITY_PERIOD)
            .noOfCalls(UPDATED_NO_OF_CALLS)
            .preBidMeetingDate(UPDATED_PRE_BID_MEETING_DATE)
            .preBidMeetingYn(UPDATED_PRE_BID_MEETING_YN)
            .prebidMeetingAddress(UPDATED_PREBID_MEETING_ADDRESS)
            .preQualificationBidOpen(UPDATED_PRE_QUALIFICATION_BID_OPEN)
            .mandateAllItemsYn(UPDATED_MANDATE_ALL_ITEMS_YN)
            .queriesPublished(UPDATED_QUERIES_PUBLISHED)
            .denominationType(UPDATED_DENOMINATION_TYPE)
            .retenderedYn(UPDATED_RETENDERED_YN)
            .percentageRateType(UPDATED_PERCENTAGE_RATE_TYPE)
            .contactPerson(UPDATED_CONTACT_PERSON)
            .splitEmdRequiredYn(UPDATED_SPLIT_EMD_REQUIRED_YN)
            .emdBankGuarantee(UPDATED_EMD_BANK_GUARANTEE)
            .emdCash(UPDATED_EMD_CASH)
            .bgValidityPeriod(UPDATED_BG_VALIDITY_PERIOD)
            .hideWeightage(UPDATED_HIDE_WEIGHTAGE)
            .itemwiseTechEvalYn(UPDATED_ITEMWISE_TECH_EVAL_YN)
            .isMultipleSupplierSelectionAllowed(UPDATED_IS_MULTIPLE_SUPPLIER_SELECTION_ALLOWED)
            .techEvalStartDate(UPDATED_TECH_EVAL_START_DATE)
            .techEvalEndDate(UPDATED_TECH_EVAL_END_DATE)
            .commEvalStartDate(UPDATED_COMM_EVAL_START_DATE)
            .commEvalEndDate(UPDATED_COMM_EVAL_END_DATE)
            .emdVerifiedDate(UPDATED_EMD_VERIFIED_DATE)
            .multipleCurrencySelectionAllowedYn(UPDATED_MULTIPLE_CURRENCY_SELECTION_ALLOWED_YN)
            .isEditable(UPDATED_IS_EDITABLE)
            .isEvaluated(UPDATED_IS_EVALUATED)
            .isTechWeightageAllowed(UPDATED_IS_TECH_WEIGHTAGE_ALLOWED)
            .isTechWeightageCompleted(UPDATED_IS_TECH_WEIGHTAGE_COMPLETED)
            .isCommercialBidEditCompleted(UPDATED_IS_COMMERCIAL_BID_EDIT_COMPLETED)
            .templateYn(UPDATED_TEMPLATE_YN)
            .templateId(UPDATED_TEMPLATE_ID)
            .paymentVerifiedBy(UPDATED_PAYMENT_VERIFIED_BY)
            .paymentVerifiedDate(UPDATED_PAYMENT_VERIFIED_DATE)
            .isItemwiseCsrRequired(UPDATED_IS_ITEMWISE_CSR_REQUIRED)
            .isBidViewEnabled(UPDATED_IS_BID_VIEW_ENABLED)
            .isNegotiationRequired(UPDATED_IS_NEGOTIATION_REQUIRED)
            .highestBidderSelection(UPDATED_HIGHEST_BIDDER_SELECTION)
            .isVariableEmdAllowed(UPDATED_IS_VARIABLE_EMD_ALLOWED)
            .nitPublisherCertId(UPDATED_NIT_PUBLISHER_CERT_ID)
            .autoExtendYn(UPDATED_AUTO_EXTEND_YN)
            .noOfDaysExtend(UPDATED_NO_OF_DAYS_EXTEND)
            .isExtensionAvailable(UPDATED_IS_EXTENSION_AVAILABLE)
            .specialSchemeTender(UPDATED_SPECIAL_SCHEME_TENDER)
            .isBidValidityExpiryTaskCreated(UPDATED_IS_BID_VALIDITY_EXPIRY_TASK_CREATED)
            .evaluationTypeFlag(UPDATED_EVALUATION_TYPE_FLAG)
            .qcbsTenderYn(UPDATED_QCBS_TENDER_YN)
            .publishedUser(UPDATED_PUBLISHED_USER)
            .isWorldBankFunded(UPDATED_IS_WORLD_BANK_FUNDED)
            .ecClearanceNumber(UPDATED_EC_CLEARANCE_NUMBER)
            .ecClearanceDate(UPDATED_EC_CLEARANCE_DATE);
        return noticeInvitingTender;
    }

    @BeforeEach
    public void initTest() {
        noticeInvitingTender = createEntity(em);
    }

    @Test
    @Transactional
    void createNoticeInvitingTender() throws Exception {
        int databaseSizeBeforeCreate = noticeInvitingTenderRepository.findAll().size();
        // Create the NoticeInvitingTender
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);
        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isCreated());

        // Validate the NoticeInvitingTender in the database
        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeCreate + 1);
        NoticeInvitingTender testNoticeInvitingTender = noticeInvitingTenderList.get(noticeInvitingTenderList.size() - 1);
        assertThat(testNoticeInvitingTender.getEvaluationType()).isEqualTo(DEFAULT_EVALUATION_TYPE);
        assertThat(testNoticeInvitingTender.getPrequalValidityPeriod()).isEqualTo(DEFAULT_PREQUAL_VALIDITY_PERIOD);
        assertThat(testNoticeInvitingTender.getInvitingStrategy()).isEqualTo(DEFAULT_INVITING_STRATEGY);
        assertThat(testNoticeInvitingTender.getMinNoBidsReceive()).isEqualTo(DEFAULT_MIN_NO_BIDS_RECEIVE);
        assertThat(testNoticeInvitingTender.getProcuringEntity()).isEqualTo(DEFAULT_PROCURING_ENTITY);
        assertThat(testNoticeInvitingTender.getProcuringEntityDesignation()).isEqualTo(DEFAULT_PROCURING_ENTITY_DESIGNATION);
        assertThat(testNoticeInvitingTender.getProcuringEntityAddress()).isEqualTo(DEFAULT_PROCURING_ENTITY_ADDRESS);
        assertThat(testNoticeInvitingTender.getInvitingAuthority()).isEqualTo(DEFAULT_INVITING_AUTHORITY);
        assertThat(testNoticeInvitingTender.getInvitingAuthorityDesignation()).isEqualTo(DEFAULT_INVITING_AUTHORITY_DESIGNATION);
        assertThat(testNoticeInvitingTender.getInvitingAuthorityAddress()).isEqualTo(DEFAULT_INVITING_AUTHORITY_ADDRESS);
        assertThat(testNoticeInvitingTender.getParticipatingCondnYn()).isEqualTo(DEFAULT_PARTICIPATING_CONDN_YN);
        assertThat(testNoticeInvitingTender.getTenderFee()).isEqualByComparingTo(DEFAULT_TENDER_FEE);
        assertThat(testNoticeInvitingTender.getEmd()).isEqualByComparingTo(DEFAULT_EMD);
        assertThat(testNoticeInvitingTender.getBidValueType()).isEqualTo(DEFAULT_BID_VALUE_TYPE);
        assertThat(testNoticeInvitingTender.getTechWeightage()).isEqualTo(DEFAULT_TECH_WEIGHTAGE);
        assertThat(testNoticeInvitingTender.getPrequalTenderBidOpen()).isEqualTo(DEFAULT_PREQUAL_TENDER_BID_OPEN);
        assertThat(testNoticeInvitingTender.getTenderDocClose()).isEqualTo(DEFAULT_TENDER_DOC_CLOSE);
        assertThat(testNoticeInvitingTender.getTenderReceiptClose()).isEqualTo(DEFAULT_TENDER_RECEIPT_CLOSE);
        assertThat(testNoticeInvitingTender.getTenderQueryClose()).isEqualTo(DEFAULT_TENDER_QUERY_CLOSE);
        assertThat(testNoticeInvitingTender.getTechnicalBidOpen()).isEqualTo(DEFAULT_TECHNICAL_BID_OPEN);
        assertThat(testNoticeInvitingTender.getFinancialBidOpen()).isEqualTo(DEFAULT_FINANCIAL_BID_OPEN);
        assertThat(testNoticeInvitingTender.getPublishedDate()).isEqualTo(DEFAULT_PUBLISHED_DATE);
        assertThat(testNoticeInvitingTender.getPublishedBy()).isEqualTo(DEFAULT_PUBLISHED_BY);
        assertThat(testNoticeInvitingTender.getRecalledDate()).isEqualTo(DEFAULT_RECALLED_DATE);
        assertThat(testNoticeInvitingTender.getRecalledBy()).isEqualTo(DEFAULT_RECALLED_BY);
        assertThat(testNoticeInvitingTender.getBidSubmissionStartDate()).isEqualTo(DEFAULT_BID_SUBMISSION_START_DATE);
        assertThat(testNoticeInvitingTender.getBidValidityPeriod()).isEqualTo(DEFAULT_BID_VALIDITY_PERIOD);
        assertThat(testNoticeInvitingTender.getNoOfCalls()).isEqualTo(DEFAULT_NO_OF_CALLS);
        assertThat(testNoticeInvitingTender.getPreBidMeetingDate()).isEqualTo(DEFAULT_PRE_BID_MEETING_DATE);
        assertThat(testNoticeInvitingTender.getPreBidMeetingYn()).isEqualTo(DEFAULT_PRE_BID_MEETING_YN);
        assertThat(testNoticeInvitingTender.getPrebidMeetingAddress()).isEqualTo(DEFAULT_PREBID_MEETING_ADDRESS);
        assertThat(testNoticeInvitingTender.getPreQualificationBidOpen()).isEqualTo(DEFAULT_PRE_QUALIFICATION_BID_OPEN);
        assertThat(testNoticeInvitingTender.getMandateAllItemsYn()).isEqualTo(DEFAULT_MANDATE_ALL_ITEMS_YN);
        assertThat(testNoticeInvitingTender.getQueriesPublished()).isEqualTo(DEFAULT_QUERIES_PUBLISHED);
        assertThat(testNoticeInvitingTender.getDenominationType()).isEqualTo(DEFAULT_DENOMINATION_TYPE);
        assertThat(testNoticeInvitingTender.getRetenderedYn()).isEqualTo(DEFAULT_RETENDERED_YN);
        assertThat(testNoticeInvitingTender.getPercentageRateType()).isEqualTo(DEFAULT_PERCENTAGE_RATE_TYPE);
        assertThat(testNoticeInvitingTender.getContactPerson()).isEqualTo(DEFAULT_CONTACT_PERSON);
        assertThat(testNoticeInvitingTender.getSplitEmdRequiredYn()).isEqualTo(DEFAULT_SPLIT_EMD_REQUIRED_YN);
        assertThat(testNoticeInvitingTender.getEmdBankGuarantee()).isEqualByComparingTo(DEFAULT_EMD_BANK_GUARANTEE);
        assertThat(testNoticeInvitingTender.getEmdCash()).isEqualByComparingTo(DEFAULT_EMD_CASH);
        assertThat(testNoticeInvitingTender.getBgValidityPeriod()).isEqualTo(DEFAULT_BG_VALIDITY_PERIOD);
        assertThat(testNoticeInvitingTender.getHideWeightage()).isEqualTo(DEFAULT_HIDE_WEIGHTAGE);
        assertThat(testNoticeInvitingTender.getItemwiseTechEvalYn()).isEqualTo(DEFAULT_ITEMWISE_TECH_EVAL_YN);
        assertThat(testNoticeInvitingTender.getIsMultipleSupplierSelectionAllowed())
            .isEqualTo(DEFAULT_IS_MULTIPLE_SUPPLIER_SELECTION_ALLOWED);
        assertThat(testNoticeInvitingTender.getTechEvalStartDate()).isEqualTo(DEFAULT_TECH_EVAL_START_DATE);
        assertThat(testNoticeInvitingTender.getTechEvalEndDate()).isEqualTo(DEFAULT_TECH_EVAL_END_DATE);
        assertThat(testNoticeInvitingTender.getCommEvalStartDate()).isEqualTo(DEFAULT_COMM_EVAL_START_DATE);
        assertThat(testNoticeInvitingTender.getCommEvalEndDate()).isEqualTo(DEFAULT_COMM_EVAL_END_DATE);
        assertThat(testNoticeInvitingTender.getEmdVerifiedDate()).isEqualTo(DEFAULT_EMD_VERIFIED_DATE);
        assertThat(testNoticeInvitingTender.getMultipleCurrencySelectionAllowedYn())
            .isEqualTo(DEFAULT_MULTIPLE_CURRENCY_SELECTION_ALLOWED_YN);
        assertThat(testNoticeInvitingTender.getIsEditable()).isEqualTo(DEFAULT_IS_EDITABLE);
        assertThat(testNoticeInvitingTender.getIsEvaluated()).isEqualTo(DEFAULT_IS_EVALUATED);
        assertThat(testNoticeInvitingTender.getIsTechWeightageAllowed()).isEqualTo(DEFAULT_IS_TECH_WEIGHTAGE_ALLOWED);
        assertThat(testNoticeInvitingTender.getIsTechWeightageCompleted()).isEqualTo(DEFAULT_IS_TECH_WEIGHTAGE_COMPLETED);
        assertThat(testNoticeInvitingTender.getIsCommercialBidEditCompleted()).isEqualTo(DEFAULT_IS_COMMERCIAL_BID_EDIT_COMPLETED);
        assertThat(testNoticeInvitingTender.getTemplateYn()).isEqualTo(DEFAULT_TEMPLATE_YN);
        assertThat(testNoticeInvitingTender.getTemplateId()).isEqualTo(DEFAULT_TEMPLATE_ID);
        assertThat(testNoticeInvitingTender.getPaymentVerifiedBy()).isEqualTo(DEFAULT_PAYMENT_VERIFIED_BY);
        assertThat(testNoticeInvitingTender.getPaymentVerifiedDate()).isEqualTo(DEFAULT_PAYMENT_VERIFIED_DATE);
        assertThat(testNoticeInvitingTender.getIsItemwiseCsrRequired()).isEqualTo(DEFAULT_IS_ITEMWISE_CSR_REQUIRED);
        assertThat(testNoticeInvitingTender.getIsBidViewEnabled()).isEqualTo(DEFAULT_IS_BID_VIEW_ENABLED);
        assertThat(testNoticeInvitingTender.getIsNegotiationRequired()).isEqualTo(DEFAULT_IS_NEGOTIATION_REQUIRED);
        assertThat(testNoticeInvitingTender.getHighestBidderSelection()).isEqualTo(DEFAULT_HIGHEST_BIDDER_SELECTION);
        assertThat(testNoticeInvitingTender.getIsVariableEmdAllowed()).isEqualTo(DEFAULT_IS_VARIABLE_EMD_ALLOWED);
        assertThat(testNoticeInvitingTender.getNitPublisherCertId()).isEqualTo(DEFAULT_NIT_PUBLISHER_CERT_ID);
        assertThat(testNoticeInvitingTender.getAutoExtendYn()).isEqualTo(DEFAULT_AUTO_EXTEND_YN);
        assertThat(testNoticeInvitingTender.getNoOfDaysExtend()).isEqualTo(DEFAULT_NO_OF_DAYS_EXTEND);
        assertThat(testNoticeInvitingTender.getIsExtensionAvailable()).isEqualTo(DEFAULT_IS_EXTENSION_AVAILABLE);
        assertThat(testNoticeInvitingTender.getSpecialSchemeTender()).isEqualTo(DEFAULT_SPECIAL_SCHEME_TENDER);
        assertThat(testNoticeInvitingTender.getIsBidValidityExpiryTaskCreated()).isEqualTo(DEFAULT_IS_BID_VALIDITY_EXPIRY_TASK_CREATED);
        assertThat(testNoticeInvitingTender.getEvaluationTypeFlag()).isEqualTo(DEFAULT_EVALUATION_TYPE_FLAG);
        assertThat(testNoticeInvitingTender.getQcbsTenderYn()).isEqualTo(DEFAULT_QCBS_TENDER_YN);
        assertThat(testNoticeInvitingTender.getPublishedUser()).isEqualTo(DEFAULT_PUBLISHED_USER);
        assertThat(testNoticeInvitingTender.getIsWorldBankFunded()).isEqualTo(DEFAULT_IS_WORLD_BANK_FUNDED);
        assertThat(testNoticeInvitingTender.getEcClearanceNumber()).isEqualTo(DEFAULT_EC_CLEARANCE_NUMBER);
        assertThat(testNoticeInvitingTender.getEcClearanceDate()).isEqualTo(DEFAULT_EC_CLEARANCE_DATE);
    }

    @Test
    @Transactional
    void createNoticeInvitingTenderWithExistingId() throws Exception {
        // Create the NoticeInvitingTender with an existing ID
        noticeInvitingTender.setId(1L);
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        int databaseSizeBeforeCreate = noticeInvitingTenderRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NoticeInvitingTender in the database
        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkEvaluationTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setEvaluationType(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPrequalValidityPeriodIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setPrequalValidityPeriod(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkInvitingStrategyIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setInvitingStrategy(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkMinNoBidsReceiveIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setMinNoBidsReceive(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkProcuringEntityIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setProcuringEntity(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkProcuringEntityDesignationIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setProcuringEntityDesignation(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkProcuringEntityAddressIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setProcuringEntityAddress(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkInvitingAuthorityIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setInvitingAuthority(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkInvitingAuthorityDesignationIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setInvitingAuthorityDesignation(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkInvitingAuthorityAddressIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setInvitingAuthorityAddress(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkParticipatingCondnYnIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setParticipatingCondnYn(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTenderFeeIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setTenderFee(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEmdIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setEmd(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBidValueTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setBidValueType(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTechWeightageIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setTechWeightage(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPrequalTenderBidOpenIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setPrequalTenderBidOpen(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTenderDocCloseIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setTenderDocClose(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTenderReceiptCloseIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setTenderReceiptClose(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTenderQueryCloseIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setTenderQueryClose(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTechnicalBidOpenIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setTechnicalBidOpen(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkFinancialBidOpenIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setFinancialBidOpen(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPublishedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setPublishedDate(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPublishedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setPublishedBy(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkRecalledDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setRecalledDate(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkRecalledByIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setRecalledBy(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBidSubmissionStartDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setBidSubmissionStartDate(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBidValidityPeriodIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setBidValidityPeriod(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNoOfCallsIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setNoOfCalls(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPreBidMeetingDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setPreBidMeetingDate(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPreBidMeetingYnIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setPreBidMeetingYn(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPrebidMeetingAddressIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setPrebidMeetingAddress(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPreQualificationBidOpenIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setPreQualificationBidOpen(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkMandateAllItemsYnIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setMandateAllItemsYn(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkQueriesPublishedIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setQueriesPublished(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDenominationTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setDenominationType(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkRetenderedYnIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setRetenderedYn(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPercentageRateTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setPercentageRateType(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkContactPersonIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setContactPerson(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSplitEmdRequiredYnIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setSplitEmdRequiredYn(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEmdBankGuaranteeIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setEmdBankGuarantee(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEmdCashIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setEmdCash(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBgValidityPeriodIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setBgValidityPeriod(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkHideWeightageIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setHideWeightage(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkItemwiseTechEvalYnIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setItemwiseTechEvalYn(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkIsMultipleSupplierSelectionAllowedIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setIsMultipleSupplierSelectionAllowed(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTechEvalStartDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setTechEvalStartDate(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTechEvalEndDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setTechEvalEndDate(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCommEvalStartDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setCommEvalStartDate(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCommEvalEndDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setCommEvalEndDate(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEmdVerifiedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setEmdVerifiedDate(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkMultipleCurrencySelectionAllowedYnIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setMultipleCurrencySelectionAllowedYn(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkIsEditableIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setIsEditable(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkIsEvaluatedIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setIsEvaluated(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkIsTechWeightageAllowedIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setIsTechWeightageAllowed(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkIsTechWeightageCompletedIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setIsTechWeightageCompleted(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkIsCommercialBidEditCompletedIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setIsCommercialBidEditCompleted(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTemplateYnIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setTemplateYn(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTemplateIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setTemplateId(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPaymentVerifiedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setPaymentVerifiedBy(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPaymentVerifiedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setPaymentVerifiedDate(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkIsItemwiseCsrRequiredIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setIsItemwiseCsrRequired(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkIsBidViewEnabledIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setIsBidViewEnabled(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkIsNegotiationRequiredIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setIsNegotiationRequired(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkHighestBidderSelectionIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setHighestBidderSelection(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkIsVariableEmdAllowedIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setIsVariableEmdAllowed(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNitPublisherCertIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setNitPublisherCertId(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkAutoExtendYnIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setAutoExtendYn(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNoOfDaysExtendIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setNoOfDaysExtend(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkIsExtensionAvailableIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setIsExtensionAvailable(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSpecialSchemeTenderIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setSpecialSchemeTender(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkIsBidValidityExpiryTaskCreatedIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setIsBidValidityExpiryTaskCreated(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEvaluationTypeFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setEvaluationTypeFlag(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkQcbsTenderYnIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setQcbsTenderYn(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPublishedUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setPublishedUser(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkIsWorldBankFundedIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setIsWorldBankFunded(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEcClearanceNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setEcClearanceNumber(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEcClearanceDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInvitingTenderRepository.findAll().size();
        // set the field null
        noticeInvitingTender.setEcClearanceDate(null);

        // Create the NoticeInvitingTender, which fails.
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllNoticeInvitingTenders() throws Exception {
        // Initialize the database
        noticeInvitingTenderRepository.saveAndFlush(noticeInvitingTender);

        // Get all the noticeInvitingTenderList
        restNoticeInvitingTenderMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(noticeInvitingTender.getId().intValue())))
            .andExpect(jsonPath("$.[*].evaluationType").value(hasItem(DEFAULT_EVALUATION_TYPE)))
            .andExpect(jsonPath("$.[*].prequalValidityPeriod").value(hasItem(DEFAULT_PREQUAL_VALIDITY_PERIOD.toString())))
            .andExpect(jsonPath("$.[*].invitingStrategy").value(hasItem(DEFAULT_INVITING_STRATEGY.booleanValue())))
            .andExpect(jsonPath("$.[*].minNoBidsReceive").value(hasItem(DEFAULT_MIN_NO_BIDS_RECEIVE)))
            .andExpect(jsonPath("$.[*].procuringEntity").value(hasItem(DEFAULT_PROCURING_ENTITY)))
            .andExpect(jsonPath("$.[*].procuringEntityDesignation").value(hasItem(DEFAULT_PROCURING_ENTITY_DESIGNATION)))
            .andExpect(jsonPath("$.[*].procuringEntityAddress").value(hasItem(DEFAULT_PROCURING_ENTITY_ADDRESS)))
            .andExpect(jsonPath("$.[*].invitingAuthority").value(hasItem(DEFAULT_INVITING_AUTHORITY)))
            .andExpect(jsonPath("$.[*].invitingAuthorityDesignation").value(hasItem(DEFAULT_INVITING_AUTHORITY_DESIGNATION)))
            .andExpect(jsonPath("$.[*].invitingAuthorityAddress").value(hasItem(DEFAULT_INVITING_AUTHORITY_ADDRESS)))
            .andExpect(jsonPath("$.[*].participatingCondnYn").value(hasItem(DEFAULT_PARTICIPATING_CONDN_YN.booleanValue())))
            .andExpect(jsonPath("$.[*].tenderFee").value(hasItem(sameNumber(DEFAULT_TENDER_FEE))))
            .andExpect(jsonPath("$.[*].emd").value(hasItem(sameNumber(DEFAULT_EMD))))
            .andExpect(jsonPath("$.[*].bidValueType").value(hasItem(DEFAULT_BID_VALUE_TYPE.booleanValue())))
            .andExpect(jsonPath("$.[*].techWeightage").value(hasItem(DEFAULT_TECH_WEIGHTAGE)))
            .andExpect(jsonPath("$.[*].prequalTenderBidOpen").value(hasItem(DEFAULT_PREQUAL_TENDER_BID_OPEN.toString())))
            .andExpect(jsonPath("$.[*].tenderDocClose").value(hasItem(DEFAULT_TENDER_DOC_CLOSE.toString())))
            .andExpect(jsonPath("$.[*].tenderReceiptClose").value(hasItem(DEFAULT_TENDER_RECEIPT_CLOSE.toString())))
            .andExpect(jsonPath("$.[*].tenderQueryClose").value(hasItem(DEFAULT_TENDER_QUERY_CLOSE.toString())))
            .andExpect(jsonPath("$.[*].technicalBidOpen").value(hasItem(DEFAULT_TECHNICAL_BID_OPEN.toString())))
            .andExpect(jsonPath("$.[*].financialBidOpen").value(hasItem(DEFAULT_FINANCIAL_BID_OPEN.toString())))
            .andExpect(jsonPath("$.[*].publishedDate").value(hasItem(DEFAULT_PUBLISHED_DATE.toString())))
            .andExpect(jsonPath("$.[*].publishedBy").value(hasItem(DEFAULT_PUBLISHED_BY)))
            .andExpect(jsonPath("$.[*].recalledDate").value(hasItem(DEFAULT_RECALLED_DATE.toString())))
            .andExpect(jsonPath("$.[*].recalledBy").value(hasItem(DEFAULT_RECALLED_BY)))
            .andExpect(jsonPath("$.[*].bidSubmissionStartDate").value(hasItem(DEFAULT_BID_SUBMISSION_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].bidValidityPeriod").value(hasItem(DEFAULT_BID_VALIDITY_PERIOD)))
            .andExpect(jsonPath("$.[*].noOfCalls").value(hasItem(DEFAULT_NO_OF_CALLS)))
            .andExpect(jsonPath("$.[*].preBidMeetingDate").value(hasItem(DEFAULT_PRE_BID_MEETING_DATE.toString())))
            .andExpect(jsonPath("$.[*].preBidMeetingYn").value(hasItem(DEFAULT_PRE_BID_MEETING_YN.booleanValue())))
            .andExpect(jsonPath("$.[*].prebidMeetingAddress").value(hasItem(DEFAULT_PREBID_MEETING_ADDRESS)))
            .andExpect(jsonPath("$.[*].preQualificationBidOpen").value(hasItem(DEFAULT_PRE_QUALIFICATION_BID_OPEN.toString())))
            .andExpect(jsonPath("$.[*].mandateAllItemsYn").value(hasItem(DEFAULT_MANDATE_ALL_ITEMS_YN.booleanValue())))
            .andExpect(jsonPath("$.[*].queriesPublished").value(hasItem(DEFAULT_QUERIES_PUBLISHED.booleanValue())))
            .andExpect(jsonPath("$.[*].denominationType").value(hasItem(DEFAULT_DENOMINATION_TYPE)))
            .andExpect(jsonPath("$.[*].retenderedYn").value(hasItem(DEFAULT_RETENDERED_YN.booleanValue())))
            .andExpect(jsonPath("$.[*].percentageRateType").value(hasItem(DEFAULT_PERCENTAGE_RATE_TYPE)))
            .andExpect(jsonPath("$.[*].contactPerson").value(hasItem(DEFAULT_CONTACT_PERSON)))
            .andExpect(jsonPath("$.[*].splitEmdRequiredYn").value(hasItem(DEFAULT_SPLIT_EMD_REQUIRED_YN.booleanValue())))
            .andExpect(jsonPath("$.[*].emdBankGuarantee").value(hasItem(sameNumber(DEFAULT_EMD_BANK_GUARANTEE))))
            .andExpect(jsonPath("$.[*].emdCash").value(hasItem(sameNumber(DEFAULT_EMD_CASH))))
            .andExpect(jsonPath("$.[*].bgValidityPeriod").value(hasItem(DEFAULT_BG_VALIDITY_PERIOD)))
            .andExpect(jsonPath("$.[*].hideWeightage").value(hasItem(DEFAULT_HIDE_WEIGHTAGE.booleanValue())))
            .andExpect(jsonPath("$.[*].itemwiseTechEvalYn").value(hasItem(DEFAULT_ITEMWISE_TECH_EVAL_YN.booleanValue())))
            .andExpect(
                jsonPath("$.[*].isMultipleSupplierSelectionAllowed")
                    .value(hasItem(DEFAULT_IS_MULTIPLE_SUPPLIER_SELECTION_ALLOWED.booleanValue()))
            )
            .andExpect(jsonPath("$.[*].techEvalStartDate").value(hasItem(DEFAULT_TECH_EVAL_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].techEvalEndDate").value(hasItem(DEFAULT_TECH_EVAL_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].commEvalStartDate").value(hasItem(DEFAULT_COMM_EVAL_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].commEvalEndDate").value(hasItem(DEFAULT_COMM_EVAL_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].emdVerifiedDate").value(hasItem(DEFAULT_EMD_VERIFIED_DATE.toString())))
            .andExpect(
                jsonPath("$.[*].multipleCurrencySelectionAllowedYn")
                    .value(hasItem(DEFAULT_MULTIPLE_CURRENCY_SELECTION_ALLOWED_YN.booleanValue()))
            )
            .andExpect(jsonPath("$.[*].isEditable").value(hasItem(DEFAULT_IS_EDITABLE.booleanValue())))
            .andExpect(jsonPath("$.[*].isEvaluated").value(hasItem(DEFAULT_IS_EVALUATED.booleanValue())))
            .andExpect(jsonPath("$.[*].isTechWeightageAllowed").value(hasItem(DEFAULT_IS_TECH_WEIGHTAGE_ALLOWED.booleanValue())))
            .andExpect(jsonPath("$.[*].isTechWeightageCompleted").value(hasItem(DEFAULT_IS_TECH_WEIGHTAGE_COMPLETED.booleanValue())))
            .andExpect(
                jsonPath("$.[*].isCommercialBidEditCompleted").value(hasItem(DEFAULT_IS_COMMERCIAL_BID_EDIT_COMPLETED.booleanValue()))
            )
            .andExpect(jsonPath("$.[*].templateYn").value(hasItem(DEFAULT_TEMPLATE_YN.booleanValue())))
            .andExpect(jsonPath("$.[*].templateId").value(hasItem(DEFAULT_TEMPLATE_ID.intValue())))
            .andExpect(jsonPath("$.[*].paymentVerifiedBy").value(hasItem(DEFAULT_PAYMENT_VERIFIED_BY)))
            .andExpect(jsonPath("$.[*].paymentVerifiedDate").value(hasItem(DEFAULT_PAYMENT_VERIFIED_DATE.toString())))
            .andExpect(jsonPath("$.[*].isItemwiseCsrRequired").value(hasItem(DEFAULT_IS_ITEMWISE_CSR_REQUIRED.booleanValue())))
            .andExpect(jsonPath("$.[*].isBidViewEnabled").value(hasItem(DEFAULT_IS_BID_VIEW_ENABLED.booleanValue())))
            .andExpect(jsonPath("$.[*].isNegotiationRequired").value(hasItem(DEFAULT_IS_NEGOTIATION_REQUIRED.booleanValue())))
            .andExpect(jsonPath("$.[*].highestBidderSelection").value(hasItem(DEFAULT_HIGHEST_BIDDER_SELECTION.booleanValue())))
            .andExpect(jsonPath("$.[*].isVariableEmdAllowed").value(hasItem(DEFAULT_IS_VARIABLE_EMD_ALLOWED.booleanValue())))
            .andExpect(jsonPath("$.[*].nitPublisherCertId").value(hasItem(DEFAULT_NIT_PUBLISHER_CERT_ID)))
            .andExpect(jsonPath("$.[*].autoExtendYn").value(hasItem(DEFAULT_AUTO_EXTEND_YN.booleanValue())))
            .andExpect(jsonPath("$.[*].noOfDaysExtend").value(hasItem(DEFAULT_NO_OF_DAYS_EXTEND)))
            .andExpect(jsonPath("$.[*].isExtensionAvailable").value(hasItem(DEFAULT_IS_EXTENSION_AVAILABLE.booleanValue())))
            .andExpect(jsonPath("$.[*].specialSchemeTender").value(hasItem(DEFAULT_SPECIAL_SCHEME_TENDER.booleanValue())))
            .andExpect(
                jsonPath("$.[*].isBidValidityExpiryTaskCreated").value(hasItem(DEFAULT_IS_BID_VALIDITY_EXPIRY_TASK_CREATED.booleanValue()))
            )
            .andExpect(jsonPath("$.[*].evaluationTypeFlag").value(hasItem(DEFAULT_EVALUATION_TYPE_FLAG.booleanValue())))
            .andExpect(jsonPath("$.[*].qcbsTenderYn").value(hasItem(DEFAULT_QCBS_TENDER_YN.booleanValue())))
            .andExpect(jsonPath("$.[*].publishedUser").value(hasItem(DEFAULT_PUBLISHED_USER)))
            .andExpect(jsonPath("$.[*].isWorldBankFunded").value(hasItem(DEFAULT_IS_WORLD_BANK_FUNDED.booleanValue())))
            .andExpect(jsonPath("$.[*].ecClearanceNumber").value(hasItem(DEFAULT_EC_CLEARANCE_NUMBER)))
            .andExpect(jsonPath("$.[*].ecClearanceDate").value(hasItem(DEFAULT_EC_CLEARANCE_DATE.toString())));
    }

    @Test
    @Transactional
    void getNoticeInvitingTender() throws Exception {
        // Initialize the database
        noticeInvitingTenderRepository.saveAndFlush(noticeInvitingTender);

        // Get the noticeInvitingTender
        restNoticeInvitingTenderMockMvc
            .perform(get(ENTITY_API_URL_ID, noticeInvitingTender.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(noticeInvitingTender.getId().intValue()))
            .andExpect(jsonPath("$.evaluationType").value(DEFAULT_EVALUATION_TYPE))
            .andExpect(jsonPath("$.prequalValidityPeriod").value(DEFAULT_PREQUAL_VALIDITY_PERIOD.toString()))
            .andExpect(jsonPath("$.invitingStrategy").value(DEFAULT_INVITING_STRATEGY.booleanValue()))
            .andExpect(jsonPath("$.minNoBidsReceive").value(DEFAULT_MIN_NO_BIDS_RECEIVE))
            .andExpect(jsonPath("$.procuringEntity").value(DEFAULT_PROCURING_ENTITY))
            .andExpect(jsonPath("$.procuringEntityDesignation").value(DEFAULT_PROCURING_ENTITY_DESIGNATION))
            .andExpect(jsonPath("$.procuringEntityAddress").value(DEFAULT_PROCURING_ENTITY_ADDRESS))
            .andExpect(jsonPath("$.invitingAuthority").value(DEFAULT_INVITING_AUTHORITY))
            .andExpect(jsonPath("$.invitingAuthorityDesignation").value(DEFAULT_INVITING_AUTHORITY_DESIGNATION))
            .andExpect(jsonPath("$.invitingAuthorityAddress").value(DEFAULT_INVITING_AUTHORITY_ADDRESS))
            .andExpect(jsonPath("$.participatingCondnYn").value(DEFAULT_PARTICIPATING_CONDN_YN.booleanValue()))
            .andExpect(jsonPath("$.tenderFee").value(sameNumber(DEFAULT_TENDER_FEE)))
            .andExpect(jsonPath("$.emd").value(sameNumber(DEFAULT_EMD)))
            .andExpect(jsonPath("$.bidValueType").value(DEFAULT_BID_VALUE_TYPE.booleanValue()))
            .andExpect(jsonPath("$.techWeightage").value(DEFAULT_TECH_WEIGHTAGE))
            .andExpect(jsonPath("$.prequalTenderBidOpen").value(DEFAULT_PREQUAL_TENDER_BID_OPEN.toString()))
            .andExpect(jsonPath("$.tenderDocClose").value(DEFAULT_TENDER_DOC_CLOSE.toString()))
            .andExpect(jsonPath("$.tenderReceiptClose").value(DEFAULT_TENDER_RECEIPT_CLOSE.toString()))
            .andExpect(jsonPath("$.tenderQueryClose").value(DEFAULT_TENDER_QUERY_CLOSE.toString()))
            .andExpect(jsonPath("$.technicalBidOpen").value(DEFAULT_TECHNICAL_BID_OPEN.toString()))
            .andExpect(jsonPath("$.financialBidOpen").value(DEFAULT_FINANCIAL_BID_OPEN.toString()))
            .andExpect(jsonPath("$.publishedDate").value(DEFAULT_PUBLISHED_DATE.toString()))
            .andExpect(jsonPath("$.publishedBy").value(DEFAULT_PUBLISHED_BY))
            .andExpect(jsonPath("$.recalledDate").value(DEFAULT_RECALLED_DATE.toString()))
            .andExpect(jsonPath("$.recalledBy").value(DEFAULT_RECALLED_BY))
            .andExpect(jsonPath("$.bidSubmissionStartDate").value(DEFAULT_BID_SUBMISSION_START_DATE.toString()))
            .andExpect(jsonPath("$.bidValidityPeriod").value(DEFAULT_BID_VALIDITY_PERIOD))
            .andExpect(jsonPath("$.noOfCalls").value(DEFAULT_NO_OF_CALLS))
            .andExpect(jsonPath("$.preBidMeetingDate").value(DEFAULT_PRE_BID_MEETING_DATE.toString()))
            .andExpect(jsonPath("$.preBidMeetingYn").value(DEFAULT_PRE_BID_MEETING_YN.booleanValue()))
            .andExpect(jsonPath("$.prebidMeetingAddress").value(DEFAULT_PREBID_MEETING_ADDRESS))
            .andExpect(jsonPath("$.preQualificationBidOpen").value(DEFAULT_PRE_QUALIFICATION_BID_OPEN.toString()))
            .andExpect(jsonPath("$.mandateAllItemsYn").value(DEFAULT_MANDATE_ALL_ITEMS_YN.booleanValue()))
            .andExpect(jsonPath("$.queriesPublished").value(DEFAULT_QUERIES_PUBLISHED.booleanValue()))
            .andExpect(jsonPath("$.denominationType").value(DEFAULT_DENOMINATION_TYPE))
            .andExpect(jsonPath("$.retenderedYn").value(DEFAULT_RETENDERED_YN.booleanValue()))
            .andExpect(jsonPath("$.percentageRateType").value(DEFAULT_PERCENTAGE_RATE_TYPE))
            .andExpect(jsonPath("$.contactPerson").value(DEFAULT_CONTACT_PERSON))
            .andExpect(jsonPath("$.splitEmdRequiredYn").value(DEFAULT_SPLIT_EMD_REQUIRED_YN.booleanValue()))
            .andExpect(jsonPath("$.emdBankGuarantee").value(sameNumber(DEFAULT_EMD_BANK_GUARANTEE)))
            .andExpect(jsonPath("$.emdCash").value(sameNumber(DEFAULT_EMD_CASH)))
            .andExpect(jsonPath("$.bgValidityPeriod").value(DEFAULT_BG_VALIDITY_PERIOD))
            .andExpect(jsonPath("$.hideWeightage").value(DEFAULT_HIDE_WEIGHTAGE.booleanValue()))
            .andExpect(jsonPath("$.itemwiseTechEvalYn").value(DEFAULT_ITEMWISE_TECH_EVAL_YN.booleanValue()))
            .andExpect(
                jsonPath("$.isMultipleSupplierSelectionAllowed").value(DEFAULT_IS_MULTIPLE_SUPPLIER_SELECTION_ALLOWED.booleanValue())
            )
            .andExpect(jsonPath("$.techEvalStartDate").value(DEFAULT_TECH_EVAL_START_DATE.toString()))
            .andExpect(jsonPath("$.techEvalEndDate").value(DEFAULT_TECH_EVAL_END_DATE.toString()))
            .andExpect(jsonPath("$.commEvalStartDate").value(DEFAULT_COMM_EVAL_START_DATE.toString()))
            .andExpect(jsonPath("$.commEvalEndDate").value(DEFAULT_COMM_EVAL_END_DATE.toString()))
            .andExpect(jsonPath("$.emdVerifiedDate").value(DEFAULT_EMD_VERIFIED_DATE.toString()))
            .andExpect(
                jsonPath("$.multipleCurrencySelectionAllowedYn").value(DEFAULT_MULTIPLE_CURRENCY_SELECTION_ALLOWED_YN.booleanValue())
            )
            .andExpect(jsonPath("$.isEditable").value(DEFAULT_IS_EDITABLE.booleanValue()))
            .andExpect(jsonPath("$.isEvaluated").value(DEFAULT_IS_EVALUATED.booleanValue()))
            .andExpect(jsonPath("$.isTechWeightageAllowed").value(DEFAULT_IS_TECH_WEIGHTAGE_ALLOWED.booleanValue()))
            .andExpect(jsonPath("$.isTechWeightageCompleted").value(DEFAULT_IS_TECH_WEIGHTAGE_COMPLETED.booleanValue()))
            .andExpect(jsonPath("$.isCommercialBidEditCompleted").value(DEFAULT_IS_COMMERCIAL_BID_EDIT_COMPLETED.booleanValue()))
            .andExpect(jsonPath("$.templateYn").value(DEFAULT_TEMPLATE_YN.booleanValue()))
            .andExpect(jsonPath("$.templateId").value(DEFAULT_TEMPLATE_ID.intValue()))
            .andExpect(jsonPath("$.paymentVerifiedBy").value(DEFAULT_PAYMENT_VERIFIED_BY))
            .andExpect(jsonPath("$.paymentVerifiedDate").value(DEFAULT_PAYMENT_VERIFIED_DATE.toString()))
            .andExpect(jsonPath("$.isItemwiseCsrRequired").value(DEFAULT_IS_ITEMWISE_CSR_REQUIRED.booleanValue()))
            .andExpect(jsonPath("$.isBidViewEnabled").value(DEFAULT_IS_BID_VIEW_ENABLED.booleanValue()))
            .andExpect(jsonPath("$.isNegotiationRequired").value(DEFAULT_IS_NEGOTIATION_REQUIRED.booleanValue()))
            .andExpect(jsonPath("$.highestBidderSelection").value(DEFAULT_HIGHEST_BIDDER_SELECTION.booleanValue()))
            .andExpect(jsonPath("$.isVariableEmdAllowed").value(DEFAULT_IS_VARIABLE_EMD_ALLOWED.booleanValue()))
            .andExpect(jsonPath("$.nitPublisherCertId").value(DEFAULT_NIT_PUBLISHER_CERT_ID))
            .andExpect(jsonPath("$.autoExtendYn").value(DEFAULT_AUTO_EXTEND_YN.booleanValue()))
            .andExpect(jsonPath("$.noOfDaysExtend").value(DEFAULT_NO_OF_DAYS_EXTEND))
            .andExpect(jsonPath("$.isExtensionAvailable").value(DEFAULT_IS_EXTENSION_AVAILABLE.booleanValue()))
            .andExpect(jsonPath("$.specialSchemeTender").value(DEFAULT_SPECIAL_SCHEME_TENDER.booleanValue()))
            .andExpect(jsonPath("$.isBidValidityExpiryTaskCreated").value(DEFAULT_IS_BID_VALIDITY_EXPIRY_TASK_CREATED.booleanValue()))
            .andExpect(jsonPath("$.evaluationTypeFlag").value(DEFAULT_EVALUATION_TYPE_FLAG.booleanValue()))
            .andExpect(jsonPath("$.qcbsTenderYn").value(DEFAULT_QCBS_TENDER_YN.booleanValue()))
            .andExpect(jsonPath("$.publishedUser").value(DEFAULT_PUBLISHED_USER))
            .andExpect(jsonPath("$.isWorldBankFunded").value(DEFAULT_IS_WORLD_BANK_FUNDED.booleanValue()))
            .andExpect(jsonPath("$.ecClearanceNumber").value(DEFAULT_EC_CLEARANCE_NUMBER))
            .andExpect(jsonPath("$.ecClearanceDate").value(DEFAULT_EC_CLEARANCE_DATE.toString()));
    }

    @Test
    @Transactional
    void getNonExistingNoticeInvitingTender() throws Exception {
        // Get the noticeInvitingTender
        restNoticeInvitingTenderMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewNoticeInvitingTender() throws Exception {
        // Initialize the database
        noticeInvitingTenderRepository.saveAndFlush(noticeInvitingTender);

        int databaseSizeBeforeUpdate = noticeInvitingTenderRepository.findAll().size();

        // Update the noticeInvitingTender
        NoticeInvitingTender updatedNoticeInvitingTender = noticeInvitingTenderRepository.findById(noticeInvitingTender.getId()).get();
        // Disconnect from session so that the updates on updatedNoticeInvitingTender are not directly saved in db
        em.detach(updatedNoticeInvitingTender);
        updatedNoticeInvitingTender
            .evaluationType(UPDATED_EVALUATION_TYPE)
            .prequalValidityPeriod(UPDATED_PREQUAL_VALIDITY_PERIOD)
            .invitingStrategy(UPDATED_INVITING_STRATEGY)
            .minNoBidsReceive(UPDATED_MIN_NO_BIDS_RECEIVE)
            .procuringEntity(UPDATED_PROCURING_ENTITY)
            .procuringEntityDesignation(UPDATED_PROCURING_ENTITY_DESIGNATION)
            .procuringEntityAddress(UPDATED_PROCURING_ENTITY_ADDRESS)
            .invitingAuthority(UPDATED_INVITING_AUTHORITY)
            .invitingAuthorityDesignation(UPDATED_INVITING_AUTHORITY_DESIGNATION)
            .invitingAuthorityAddress(UPDATED_INVITING_AUTHORITY_ADDRESS)
            .participatingCondnYn(UPDATED_PARTICIPATING_CONDN_YN)
            .tenderFee(UPDATED_TENDER_FEE)
            .emd(UPDATED_EMD)
            .bidValueType(UPDATED_BID_VALUE_TYPE)
            .techWeightage(UPDATED_TECH_WEIGHTAGE)
            .prequalTenderBidOpen(UPDATED_PREQUAL_TENDER_BID_OPEN)
            .tenderDocClose(UPDATED_TENDER_DOC_CLOSE)
            .tenderReceiptClose(UPDATED_TENDER_RECEIPT_CLOSE)
            .tenderQueryClose(UPDATED_TENDER_QUERY_CLOSE)
            .technicalBidOpen(UPDATED_TECHNICAL_BID_OPEN)
            .financialBidOpen(UPDATED_FINANCIAL_BID_OPEN)
            .publishedDate(UPDATED_PUBLISHED_DATE)
            .publishedBy(UPDATED_PUBLISHED_BY)
            .recalledDate(UPDATED_RECALLED_DATE)
            .recalledBy(UPDATED_RECALLED_BY)
            .bidSubmissionStartDate(UPDATED_BID_SUBMISSION_START_DATE)
            .bidValidityPeriod(UPDATED_BID_VALIDITY_PERIOD)
            .noOfCalls(UPDATED_NO_OF_CALLS)
            .preBidMeetingDate(UPDATED_PRE_BID_MEETING_DATE)
            .preBidMeetingYn(UPDATED_PRE_BID_MEETING_YN)
            .prebidMeetingAddress(UPDATED_PREBID_MEETING_ADDRESS)
            .preQualificationBidOpen(UPDATED_PRE_QUALIFICATION_BID_OPEN)
            .mandateAllItemsYn(UPDATED_MANDATE_ALL_ITEMS_YN)
            .queriesPublished(UPDATED_QUERIES_PUBLISHED)
            .denominationType(UPDATED_DENOMINATION_TYPE)
            .retenderedYn(UPDATED_RETENDERED_YN)
            .percentageRateType(UPDATED_PERCENTAGE_RATE_TYPE)
            .contactPerson(UPDATED_CONTACT_PERSON)
            .splitEmdRequiredYn(UPDATED_SPLIT_EMD_REQUIRED_YN)
            .emdBankGuarantee(UPDATED_EMD_BANK_GUARANTEE)
            .emdCash(UPDATED_EMD_CASH)
            .bgValidityPeriod(UPDATED_BG_VALIDITY_PERIOD)
            .hideWeightage(UPDATED_HIDE_WEIGHTAGE)
            .itemwiseTechEvalYn(UPDATED_ITEMWISE_TECH_EVAL_YN)
            .isMultipleSupplierSelectionAllowed(UPDATED_IS_MULTIPLE_SUPPLIER_SELECTION_ALLOWED)
            .techEvalStartDate(UPDATED_TECH_EVAL_START_DATE)
            .techEvalEndDate(UPDATED_TECH_EVAL_END_DATE)
            .commEvalStartDate(UPDATED_COMM_EVAL_START_DATE)
            .commEvalEndDate(UPDATED_COMM_EVAL_END_DATE)
            .emdVerifiedDate(UPDATED_EMD_VERIFIED_DATE)
            .multipleCurrencySelectionAllowedYn(UPDATED_MULTIPLE_CURRENCY_SELECTION_ALLOWED_YN)
            .isEditable(UPDATED_IS_EDITABLE)
            .isEvaluated(UPDATED_IS_EVALUATED)
            .isTechWeightageAllowed(UPDATED_IS_TECH_WEIGHTAGE_ALLOWED)
            .isTechWeightageCompleted(UPDATED_IS_TECH_WEIGHTAGE_COMPLETED)
            .isCommercialBidEditCompleted(UPDATED_IS_COMMERCIAL_BID_EDIT_COMPLETED)
            .templateYn(UPDATED_TEMPLATE_YN)
            .templateId(UPDATED_TEMPLATE_ID)
            .paymentVerifiedBy(UPDATED_PAYMENT_VERIFIED_BY)
            .paymentVerifiedDate(UPDATED_PAYMENT_VERIFIED_DATE)
            .isItemwiseCsrRequired(UPDATED_IS_ITEMWISE_CSR_REQUIRED)
            .isBidViewEnabled(UPDATED_IS_BID_VIEW_ENABLED)
            .isNegotiationRequired(UPDATED_IS_NEGOTIATION_REQUIRED)
            .highestBidderSelection(UPDATED_HIGHEST_BIDDER_SELECTION)
            .isVariableEmdAllowed(UPDATED_IS_VARIABLE_EMD_ALLOWED)
            .nitPublisherCertId(UPDATED_NIT_PUBLISHER_CERT_ID)
            .autoExtendYn(UPDATED_AUTO_EXTEND_YN)
            .noOfDaysExtend(UPDATED_NO_OF_DAYS_EXTEND)
            .isExtensionAvailable(UPDATED_IS_EXTENSION_AVAILABLE)
            .specialSchemeTender(UPDATED_SPECIAL_SCHEME_TENDER)
            .isBidValidityExpiryTaskCreated(UPDATED_IS_BID_VALIDITY_EXPIRY_TASK_CREATED)
            .evaluationTypeFlag(UPDATED_EVALUATION_TYPE_FLAG)
            .qcbsTenderYn(UPDATED_QCBS_TENDER_YN)
            .publishedUser(UPDATED_PUBLISHED_USER)
            .isWorldBankFunded(UPDATED_IS_WORLD_BANK_FUNDED)
            .ecClearanceNumber(UPDATED_EC_CLEARANCE_NUMBER)
            .ecClearanceDate(UPDATED_EC_CLEARANCE_DATE);
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(updatedNoticeInvitingTender);

        restNoticeInvitingTenderMockMvc
            .perform(
                put(ENTITY_API_URL_ID, noticeInvitingTenderDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isOk());

        // Validate the NoticeInvitingTender in the database
        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeUpdate);
        NoticeInvitingTender testNoticeInvitingTender = noticeInvitingTenderList.get(noticeInvitingTenderList.size() - 1);
        assertThat(testNoticeInvitingTender.getEvaluationType()).isEqualTo(UPDATED_EVALUATION_TYPE);
        assertThat(testNoticeInvitingTender.getPrequalValidityPeriod()).isEqualTo(UPDATED_PREQUAL_VALIDITY_PERIOD);
        assertThat(testNoticeInvitingTender.getInvitingStrategy()).isEqualTo(UPDATED_INVITING_STRATEGY);
        assertThat(testNoticeInvitingTender.getMinNoBidsReceive()).isEqualTo(UPDATED_MIN_NO_BIDS_RECEIVE);
        assertThat(testNoticeInvitingTender.getProcuringEntity()).isEqualTo(UPDATED_PROCURING_ENTITY);
        assertThat(testNoticeInvitingTender.getProcuringEntityDesignation()).isEqualTo(UPDATED_PROCURING_ENTITY_DESIGNATION);
        assertThat(testNoticeInvitingTender.getProcuringEntityAddress()).isEqualTo(UPDATED_PROCURING_ENTITY_ADDRESS);
        assertThat(testNoticeInvitingTender.getInvitingAuthority()).isEqualTo(UPDATED_INVITING_AUTHORITY);
        assertThat(testNoticeInvitingTender.getInvitingAuthorityDesignation()).isEqualTo(UPDATED_INVITING_AUTHORITY_DESIGNATION);
        assertThat(testNoticeInvitingTender.getInvitingAuthorityAddress()).isEqualTo(UPDATED_INVITING_AUTHORITY_ADDRESS);
        assertThat(testNoticeInvitingTender.getParticipatingCondnYn()).isEqualTo(UPDATED_PARTICIPATING_CONDN_YN);
        assertThat(testNoticeInvitingTender.getTenderFee()).isEqualTo(UPDATED_TENDER_FEE);
        assertThat(testNoticeInvitingTender.getEmd()).isEqualTo(UPDATED_EMD);
        assertThat(testNoticeInvitingTender.getBidValueType()).isEqualTo(UPDATED_BID_VALUE_TYPE);
        assertThat(testNoticeInvitingTender.getTechWeightage()).isEqualTo(UPDATED_TECH_WEIGHTAGE);
        assertThat(testNoticeInvitingTender.getPrequalTenderBidOpen()).isEqualTo(UPDATED_PREQUAL_TENDER_BID_OPEN);
        assertThat(testNoticeInvitingTender.getTenderDocClose()).isEqualTo(UPDATED_TENDER_DOC_CLOSE);
        assertThat(testNoticeInvitingTender.getTenderReceiptClose()).isEqualTo(UPDATED_TENDER_RECEIPT_CLOSE);
        assertThat(testNoticeInvitingTender.getTenderQueryClose()).isEqualTo(UPDATED_TENDER_QUERY_CLOSE);
        assertThat(testNoticeInvitingTender.getTechnicalBidOpen()).isEqualTo(UPDATED_TECHNICAL_BID_OPEN);
        assertThat(testNoticeInvitingTender.getFinancialBidOpen()).isEqualTo(UPDATED_FINANCIAL_BID_OPEN);
        assertThat(testNoticeInvitingTender.getPublishedDate()).isEqualTo(UPDATED_PUBLISHED_DATE);
        assertThat(testNoticeInvitingTender.getPublishedBy()).isEqualTo(UPDATED_PUBLISHED_BY);
        assertThat(testNoticeInvitingTender.getRecalledDate()).isEqualTo(UPDATED_RECALLED_DATE);
        assertThat(testNoticeInvitingTender.getRecalledBy()).isEqualTo(UPDATED_RECALLED_BY);
        assertThat(testNoticeInvitingTender.getBidSubmissionStartDate()).isEqualTo(UPDATED_BID_SUBMISSION_START_DATE);
        assertThat(testNoticeInvitingTender.getBidValidityPeriod()).isEqualTo(UPDATED_BID_VALIDITY_PERIOD);
        assertThat(testNoticeInvitingTender.getNoOfCalls()).isEqualTo(UPDATED_NO_OF_CALLS);
        assertThat(testNoticeInvitingTender.getPreBidMeetingDate()).isEqualTo(UPDATED_PRE_BID_MEETING_DATE);
        assertThat(testNoticeInvitingTender.getPreBidMeetingYn()).isEqualTo(UPDATED_PRE_BID_MEETING_YN);
        assertThat(testNoticeInvitingTender.getPrebidMeetingAddress()).isEqualTo(UPDATED_PREBID_MEETING_ADDRESS);
        assertThat(testNoticeInvitingTender.getPreQualificationBidOpen()).isEqualTo(UPDATED_PRE_QUALIFICATION_BID_OPEN);
        assertThat(testNoticeInvitingTender.getMandateAllItemsYn()).isEqualTo(UPDATED_MANDATE_ALL_ITEMS_YN);
        assertThat(testNoticeInvitingTender.getQueriesPublished()).isEqualTo(UPDATED_QUERIES_PUBLISHED);
        assertThat(testNoticeInvitingTender.getDenominationType()).isEqualTo(UPDATED_DENOMINATION_TYPE);
        assertThat(testNoticeInvitingTender.getRetenderedYn()).isEqualTo(UPDATED_RETENDERED_YN);
        assertThat(testNoticeInvitingTender.getPercentageRateType()).isEqualTo(UPDATED_PERCENTAGE_RATE_TYPE);
        assertThat(testNoticeInvitingTender.getContactPerson()).isEqualTo(UPDATED_CONTACT_PERSON);
        assertThat(testNoticeInvitingTender.getSplitEmdRequiredYn()).isEqualTo(UPDATED_SPLIT_EMD_REQUIRED_YN);
        assertThat(testNoticeInvitingTender.getEmdBankGuarantee()).isEqualTo(UPDATED_EMD_BANK_GUARANTEE);
        assertThat(testNoticeInvitingTender.getEmdCash()).isEqualTo(UPDATED_EMD_CASH);
        assertThat(testNoticeInvitingTender.getBgValidityPeriod()).isEqualTo(UPDATED_BG_VALIDITY_PERIOD);
        assertThat(testNoticeInvitingTender.getHideWeightage()).isEqualTo(UPDATED_HIDE_WEIGHTAGE);
        assertThat(testNoticeInvitingTender.getItemwiseTechEvalYn()).isEqualTo(UPDATED_ITEMWISE_TECH_EVAL_YN);
        assertThat(testNoticeInvitingTender.getIsMultipleSupplierSelectionAllowed())
            .isEqualTo(UPDATED_IS_MULTIPLE_SUPPLIER_SELECTION_ALLOWED);
        assertThat(testNoticeInvitingTender.getTechEvalStartDate()).isEqualTo(UPDATED_TECH_EVAL_START_DATE);
        assertThat(testNoticeInvitingTender.getTechEvalEndDate()).isEqualTo(UPDATED_TECH_EVAL_END_DATE);
        assertThat(testNoticeInvitingTender.getCommEvalStartDate()).isEqualTo(UPDATED_COMM_EVAL_START_DATE);
        assertThat(testNoticeInvitingTender.getCommEvalEndDate()).isEqualTo(UPDATED_COMM_EVAL_END_DATE);
        assertThat(testNoticeInvitingTender.getEmdVerifiedDate()).isEqualTo(UPDATED_EMD_VERIFIED_DATE);
        assertThat(testNoticeInvitingTender.getMultipleCurrencySelectionAllowedYn())
            .isEqualTo(UPDATED_MULTIPLE_CURRENCY_SELECTION_ALLOWED_YN);
        assertThat(testNoticeInvitingTender.getIsEditable()).isEqualTo(UPDATED_IS_EDITABLE);
        assertThat(testNoticeInvitingTender.getIsEvaluated()).isEqualTo(UPDATED_IS_EVALUATED);
        assertThat(testNoticeInvitingTender.getIsTechWeightageAllowed()).isEqualTo(UPDATED_IS_TECH_WEIGHTAGE_ALLOWED);
        assertThat(testNoticeInvitingTender.getIsTechWeightageCompleted()).isEqualTo(UPDATED_IS_TECH_WEIGHTAGE_COMPLETED);
        assertThat(testNoticeInvitingTender.getIsCommercialBidEditCompleted()).isEqualTo(UPDATED_IS_COMMERCIAL_BID_EDIT_COMPLETED);
        assertThat(testNoticeInvitingTender.getTemplateYn()).isEqualTo(UPDATED_TEMPLATE_YN);
        assertThat(testNoticeInvitingTender.getTemplateId()).isEqualTo(UPDATED_TEMPLATE_ID);
        assertThat(testNoticeInvitingTender.getPaymentVerifiedBy()).isEqualTo(UPDATED_PAYMENT_VERIFIED_BY);
        assertThat(testNoticeInvitingTender.getPaymentVerifiedDate()).isEqualTo(UPDATED_PAYMENT_VERIFIED_DATE);
        assertThat(testNoticeInvitingTender.getIsItemwiseCsrRequired()).isEqualTo(UPDATED_IS_ITEMWISE_CSR_REQUIRED);
        assertThat(testNoticeInvitingTender.getIsBidViewEnabled()).isEqualTo(UPDATED_IS_BID_VIEW_ENABLED);
        assertThat(testNoticeInvitingTender.getIsNegotiationRequired()).isEqualTo(UPDATED_IS_NEGOTIATION_REQUIRED);
        assertThat(testNoticeInvitingTender.getHighestBidderSelection()).isEqualTo(UPDATED_HIGHEST_BIDDER_SELECTION);
        assertThat(testNoticeInvitingTender.getIsVariableEmdAllowed()).isEqualTo(UPDATED_IS_VARIABLE_EMD_ALLOWED);
        assertThat(testNoticeInvitingTender.getNitPublisherCertId()).isEqualTo(UPDATED_NIT_PUBLISHER_CERT_ID);
        assertThat(testNoticeInvitingTender.getAutoExtendYn()).isEqualTo(UPDATED_AUTO_EXTEND_YN);
        assertThat(testNoticeInvitingTender.getNoOfDaysExtend()).isEqualTo(UPDATED_NO_OF_DAYS_EXTEND);
        assertThat(testNoticeInvitingTender.getIsExtensionAvailable()).isEqualTo(UPDATED_IS_EXTENSION_AVAILABLE);
        assertThat(testNoticeInvitingTender.getSpecialSchemeTender()).isEqualTo(UPDATED_SPECIAL_SCHEME_TENDER);
        assertThat(testNoticeInvitingTender.getIsBidValidityExpiryTaskCreated()).isEqualTo(UPDATED_IS_BID_VALIDITY_EXPIRY_TASK_CREATED);
        assertThat(testNoticeInvitingTender.getEvaluationTypeFlag()).isEqualTo(UPDATED_EVALUATION_TYPE_FLAG);
        assertThat(testNoticeInvitingTender.getQcbsTenderYn()).isEqualTo(UPDATED_QCBS_TENDER_YN);
        assertThat(testNoticeInvitingTender.getPublishedUser()).isEqualTo(UPDATED_PUBLISHED_USER);
        assertThat(testNoticeInvitingTender.getIsWorldBankFunded()).isEqualTo(UPDATED_IS_WORLD_BANK_FUNDED);
        assertThat(testNoticeInvitingTender.getEcClearanceNumber()).isEqualTo(UPDATED_EC_CLEARANCE_NUMBER);
        assertThat(testNoticeInvitingTender.getEcClearanceDate()).isEqualTo(UPDATED_EC_CLEARANCE_DATE);
    }

    @Test
    @Transactional
    void putNonExistingNoticeInvitingTender() throws Exception {
        int databaseSizeBeforeUpdate = noticeInvitingTenderRepository.findAll().size();
        noticeInvitingTender.setId(count.incrementAndGet());

        // Create the NoticeInvitingTender
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNoticeInvitingTenderMockMvc
            .perform(
                put(ENTITY_API_URL_ID, noticeInvitingTenderDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NoticeInvitingTender in the database
        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchNoticeInvitingTender() throws Exception {
        int databaseSizeBeforeUpdate = noticeInvitingTenderRepository.findAll().size();
        noticeInvitingTender.setId(count.incrementAndGet());

        // Create the NoticeInvitingTender
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNoticeInvitingTenderMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NoticeInvitingTender in the database
        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamNoticeInvitingTender() throws Exception {
        int databaseSizeBeforeUpdate = noticeInvitingTenderRepository.findAll().size();
        noticeInvitingTender.setId(count.incrementAndGet());

        // Create the NoticeInvitingTender
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNoticeInvitingTenderMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the NoticeInvitingTender in the database
        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateNoticeInvitingTenderWithPatch() throws Exception {
        // Initialize the database
        noticeInvitingTenderRepository.saveAndFlush(noticeInvitingTender);

        int databaseSizeBeforeUpdate = noticeInvitingTenderRepository.findAll().size();

        // Update the noticeInvitingTender using partial update
        NoticeInvitingTender partialUpdatedNoticeInvitingTender = new NoticeInvitingTender();
        partialUpdatedNoticeInvitingTender.setId(noticeInvitingTender.getId());

        partialUpdatedNoticeInvitingTender
            .evaluationType(UPDATED_EVALUATION_TYPE)
            .prequalValidityPeriod(UPDATED_PREQUAL_VALIDITY_PERIOD)
            .minNoBidsReceive(UPDATED_MIN_NO_BIDS_RECEIVE)
            .procuringEntityAddress(UPDATED_PROCURING_ENTITY_ADDRESS)
            .invitingAuthority(UPDATED_INVITING_AUTHORITY)
            .tenderFee(UPDATED_TENDER_FEE)
            .bidValueType(UPDATED_BID_VALUE_TYPE)
            .techWeightage(UPDATED_TECH_WEIGHTAGE)
            .tenderDocClose(UPDATED_TENDER_DOC_CLOSE)
            .tenderReceiptClose(UPDATED_TENDER_RECEIPT_CLOSE)
            .recalledDate(UPDATED_RECALLED_DATE)
            .recalledBy(UPDATED_RECALLED_BY)
            .bidSubmissionStartDate(UPDATED_BID_SUBMISSION_START_DATE)
            .bidValidityPeriod(UPDATED_BID_VALIDITY_PERIOD)
            .noOfCalls(UPDATED_NO_OF_CALLS)
            .preBidMeetingDate(UPDATED_PRE_BID_MEETING_DATE)
            .preQualificationBidOpen(UPDATED_PRE_QUALIFICATION_BID_OPEN)
            .denominationType(UPDATED_DENOMINATION_TYPE)
            .retenderedYn(UPDATED_RETENDERED_YN)
            .contactPerson(UPDATED_CONTACT_PERSON)
            .emdBankGuarantee(UPDATED_EMD_BANK_GUARANTEE)
            .emdCash(UPDATED_EMD_CASH)
            .bgValidityPeriod(UPDATED_BG_VALIDITY_PERIOD)
            .techEvalStartDate(UPDATED_TECH_EVAL_START_DATE)
            .techEvalEndDate(UPDATED_TECH_EVAL_END_DATE)
            .commEvalStartDate(UPDATED_COMM_EVAL_START_DATE)
            .commEvalEndDate(UPDATED_COMM_EVAL_END_DATE)
            .emdVerifiedDate(UPDATED_EMD_VERIFIED_DATE)
            .isEditable(UPDATED_IS_EDITABLE)
            .isEvaluated(UPDATED_IS_EVALUATED)
            .isCommercialBidEditCompleted(UPDATED_IS_COMMERCIAL_BID_EDIT_COMPLETED)
            .templateId(UPDATED_TEMPLATE_ID)
            .nitPublisherCertId(UPDATED_NIT_PUBLISHER_CERT_ID)
            .autoExtendYn(UPDATED_AUTO_EXTEND_YN)
            .isExtensionAvailable(UPDATED_IS_EXTENSION_AVAILABLE)
            .specialSchemeTender(UPDATED_SPECIAL_SCHEME_TENDER)
            .evaluationTypeFlag(UPDATED_EVALUATION_TYPE_FLAG)
            .ecClearanceDate(UPDATED_EC_CLEARANCE_DATE);

        restNoticeInvitingTenderMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedNoticeInvitingTender.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedNoticeInvitingTender))
            )
            .andExpect(status().isOk());

        // Validate the NoticeInvitingTender in the database
        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeUpdate);
        NoticeInvitingTender testNoticeInvitingTender = noticeInvitingTenderList.get(noticeInvitingTenderList.size() - 1);
        assertThat(testNoticeInvitingTender.getEvaluationType()).isEqualTo(UPDATED_EVALUATION_TYPE);
        assertThat(testNoticeInvitingTender.getPrequalValidityPeriod()).isEqualTo(UPDATED_PREQUAL_VALIDITY_PERIOD);
        assertThat(testNoticeInvitingTender.getInvitingStrategy()).isEqualTo(DEFAULT_INVITING_STRATEGY);
        assertThat(testNoticeInvitingTender.getMinNoBidsReceive()).isEqualTo(UPDATED_MIN_NO_BIDS_RECEIVE);
        assertThat(testNoticeInvitingTender.getProcuringEntity()).isEqualTo(DEFAULT_PROCURING_ENTITY);
        assertThat(testNoticeInvitingTender.getProcuringEntityDesignation()).isEqualTo(DEFAULT_PROCURING_ENTITY_DESIGNATION);
        assertThat(testNoticeInvitingTender.getProcuringEntityAddress()).isEqualTo(UPDATED_PROCURING_ENTITY_ADDRESS);
        assertThat(testNoticeInvitingTender.getInvitingAuthority()).isEqualTo(UPDATED_INVITING_AUTHORITY);
        assertThat(testNoticeInvitingTender.getInvitingAuthorityDesignation()).isEqualTo(DEFAULT_INVITING_AUTHORITY_DESIGNATION);
        assertThat(testNoticeInvitingTender.getInvitingAuthorityAddress()).isEqualTo(DEFAULT_INVITING_AUTHORITY_ADDRESS);
        assertThat(testNoticeInvitingTender.getParticipatingCondnYn()).isEqualTo(DEFAULT_PARTICIPATING_CONDN_YN);
        assertThat(testNoticeInvitingTender.getTenderFee()).isEqualByComparingTo(UPDATED_TENDER_FEE);
        assertThat(testNoticeInvitingTender.getEmd()).isEqualByComparingTo(DEFAULT_EMD);
        assertThat(testNoticeInvitingTender.getBidValueType()).isEqualTo(UPDATED_BID_VALUE_TYPE);
        assertThat(testNoticeInvitingTender.getTechWeightage()).isEqualTo(UPDATED_TECH_WEIGHTAGE);
        assertThat(testNoticeInvitingTender.getPrequalTenderBidOpen()).isEqualTo(DEFAULT_PREQUAL_TENDER_BID_OPEN);
        assertThat(testNoticeInvitingTender.getTenderDocClose()).isEqualTo(UPDATED_TENDER_DOC_CLOSE);
        assertThat(testNoticeInvitingTender.getTenderReceiptClose()).isEqualTo(UPDATED_TENDER_RECEIPT_CLOSE);
        assertThat(testNoticeInvitingTender.getTenderQueryClose()).isEqualTo(DEFAULT_TENDER_QUERY_CLOSE);
        assertThat(testNoticeInvitingTender.getTechnicalBidOpen()).isEqualTo(DEFAULT_TECHNICAL_BID_OPEN);
        assertThat(testNoticeInvitingTender.getFinancialBidOpen()).isEqualTo(DEFAULT_FINANCIAL_BID_OPEN);
        assertThat(testNoticeInvitingTender.getPublishedDate()).isEqualTo(DEFAULT_PUBLISHED_DATE);
        assertThat(testNoticeInvitingTender.getPublishedBy()).isEqualTo(DEFAULT_PUBLISHED_BY);
        assertThat(testNoticeInvitingTender.getRecalledDate()).isEqualTo(UPDATED_RECALLED_DATE);
        assertThat(testNoticeInvitingTender.getRecalledBy()).isEqualTo(UPDATED_RECALLED_BY);
        assertThat(testNoticeInvitingTender.getBidSubmissionStartDate()).isEqualTo(UPDATED_BID_SUBMISSION_START_DATE);
        assertThat(testNoticeInvitingTender.getBidValidityPeriod()).isEqualTo(UPDATED_BID_VALIDITY_PERIOD);
        assertThat(testNoticeInvitingTender.getNoOfCalls()).isEqualTo(UPDATED_NO_OF_CALLS);
        assertThat(testNoticeInvitingTender.getPreBidMeetingDate()).isEqualTo(UPDATED_PRE_BID_MEETING_DATE);
        assertThat(testNoticeInvitingTender.getPreBidMeetingYn()).isEqualTo(DEFAULT_PRE_BID_MEETING_YN);
        assertThat(testNoticeInvitingTender.getPrebidMeetingAddress()).isEqualTo(DEFAULT_PREBID_MEETING_ADDRESS);
        assertThat(testNoticeInvitingTender.getPreQualificationBidOpen()).isEqualTo(UPDATED_PRE_QUALIFICATION_BID_OPEN);
        assertThat(testNoticeInvitingTender.getMandateAllItemsYn()).isEqualTo(DEFAULT_MANDATE_ALL_ITEMS_YN);
        assertThat(testNoticeInvitingTender.getQueriesPublished()).isEqualTo(DEFAULT_QUERIES_PUBLISHED);
        assertThat(testNoticeInvitingTender.getDenominationType()).isEqualTo(UPDATED_DENOMINATION_TYPE);
        assertThat(testNoticeInvitingTender.getRetenderedYn()).isEqualTo(UPDATED_RETENDERED_YN);
        assertThat(testNoticeInvitingTender.getPercentageRateType()).isEqualTo(DEFAULT_PERCENTAGE_RATE_TYPE);
        assertThat(testNoticeInvitingTender.getContactPerson()).isEqualTo(UPDATED_CONTACT_PERSON);
        assertThat(testNoticeInvitingTender.getSplitEmdRequiredYn()).isEqualTo(DEFAULT_SPLIT_EMD_REQUIRED_YN);
        assertThat(testNoticeInvitingTender.getEmdBankGuarantee()).isEqualByComparingTo(UPDATED_EMD_BANK_GUARANTEE);
        assertThat(testNoticeInvitingTender.getEmdCash()).isEqualByComparingTo(UPDATED_EMD_CASH);
        assertThat(testNoticeInvitingTender.getBgValidityPeriod()).isEqualTo(UPDATED_BG_VALIDITY_PERIOD);
        assertThat(testNoticeInvitingTender.getHideWeightage()).isEqualTo(DEFAULT_HIDE_WEIGHTAGE);
        assertThat(testNoticeInvitingTender.getItemwiseTechEvalYn()).isEqualTo(DEFAULT_ITEMWISE_TECH_EVAL_YN);
        assertThat(testNoticeInvitingTender.getIsMultipleSupplierSelectionAllowed())
            .isEqualTo(DEFAULT_IS_MULTIPLE_SUPPLIER_SELECTION_ALLOWED);
        assertThat(testNoticeInvitingTender.getTechEvalStartDate()).isEqualTo(UPDATED_TECH_EVAL_START_DATE);
        assertThat(testNoticeInvitingTender.getTechEvalEndDate()).isEqualTo(UPDATED_TECH_EVAL_END_DATE);
        assertThat(testNoticeInvitingTender.getCommEvalStartDate()).isEqualTo(UPDATED_COMM_EVAL_START_DATE);
        assertThat(testNoticeInvitingTender.getCommEvalEndDate()).isEqualTo(UPDATED_COMM_EVAL_END_DATE);
        assertThat(testNoticeInvitingTender.getEmdVerifiedDate()).isEqualTo(UPDATED_EMD_VERIFIED_DATE);
        assertThat(testNoticeInvitingTender.getMultipleCurrencySelectionAllowedYn())
            .isEqualTo(DEFAULT_MULTIPLE_CURRENCY_SELECTION_ALLOWED_YN);
        assertThat(testNoticeInvitingTender.getIsEditable()).isEqualTo(UPDATED_IS_EDITABLE);
        assertThat(testNoticeInvitingTender.getIsEvaluated()).isEqualTo(UPDATED_IS_EVALUATED);
        assertThat(testNoticeInvitingTender.getIsTechWeightageAllowed()).isEqualTo(DEFAULT_IS_TECH_WEIGHTAGE_ALLOWED);
        assertThat(testNoticeInvitingTender.getIsTechWeightageCompleted()).isEqualTo(DEFAULT_IS_TECH_WEIGHTAGE_COMPLETED);
        assertThat(testNoticeInvitingTender.getIsCommercialBidEditCompleted()).isEqualTo(UPDATED_IS_COMMERCIAL_BID_EDIT_COMPLETED);
        assertThat(testNoticeInvitingTender.getTemplateYn()).isEqualTo(DEFAULT_TEMPLATE_YN);
        assertThat(testNoticeInvitingTender.getTemplateId()).isEqualTo(UPDATED_TEMPLATE_ID);
        assertThat(testNoticeInvitingTender.getPaymentVerifiedBy()).isEqualTo(DEFAULT_PAYMENT_VERIFIED_BY);
        assertThat(testNoticeInvitingTender.getPaymentVerifiedDate()).isEqualTo(DEFAULT_PAYMENT_VERIFIED_DATE);
        assertThat(testNoticeInvitingTender.getIsItemwiseCsrRequired()).isEqualTo(DEFAULT_IS_ITEMWISE_CSR_REQUIRED);
        assertThat(testNoticeInvitingTender.getIsBidViewEnabled()).isEqualTo(DEFAULT_IS_BID_VIEW_ENABLED);
        assertThat(testNoticeInvitingTender.getIsNegotiationRequired()).isEqualTo(DEFAULT_IS_NEGOTIATION_REQUIRED);
        assertThat(testNoticeInvitingTender.getHighestBidderSelection()).isEqualTo(DEFAULT_HIGHEST_BIDDER_SELECTION);
        assertThat(testNoticeInvitingTender.getIsVariableEmdAllowed()).isEqualTo(DEFAULT_IS_VARIABLE_EMD_ALLOWED);
        assertThat(testNoticeInvitingTender.getNitPublisherCertId()).isEqualTo(UPDATED_NIT_PUBLISHER_CERT_ID);
        assertThat(testNoticeInvitingTender.getAutoExtendYn()).isEqualTo(UPDATED_AUTO_EXTEND_YN);
        assertThat(testNoticeInvitingTender.getNoOfDaysExtend()).isEqualTo(DEFAULT_NO_OF_DAYS_EXTEND);
        assertThat(testNoticeInvitingTender.getIsExtensionAvailable()).isEqualTo(UPDATED_IS_EXTENSION_AVAILABLE);
        assertThat(testNoticeInvitingTender.getSpecialSchemeTender()).isEqualTo(UPDATED_SPECIAL_SCHEME_TENDER);
        assertThat(testNoticeInvitingTender.getIsBidValidityExpiryTaskCreated()).isEqualTo(DEFAULT_IS_BID_VALIDITY_EXPIRY_TASK_CREATED);
        assertThat(testNoticeInvitingTender.getEvaluationTypeFlag()).isEqualTo(UPDATED_EVALUATION_TYPE_FLAG);
        assertThat(testNoticeInvitingTender.getQcbsTenderYn()).isEqualTo(DEFAULT_QCBS_TENDER_YN);
        assertThat(testNoticeInvitingTender.getPublishedUser()).isEqualTo(DEFAULT_PUBLISHED_USER);
        assertThat(testNoticeInvitingTender.getIsWorldBankFunded()).isEqualTo(DEFAULT_IS_WORLD_BANK_FUNDED);
        assertThat(testNoticeInvitingTender.getEcClearanceNumber()).isEqualTo(DEFAULT_EC_CLEARANCE_NUMBER);
        assertThat(testNoticeInvitingTender.getEcClearanceDate()).isEqualTo(UPDATED_EC_CLEARANCE_DATE);
    }

    @Test
    @Transactional
    void fullUpdateNoticeInvitingTenderWithPatch() throws Exception {
        // Initialize the database
        noticeInvitingTenderRepository.saveAndFlush(noticeInvitingTender);

        int databaseSizeBeforeUpdate = noticeInvitingTenderRepository.findAll().size();

        // Update the noticeInvitingTender using partial update
        NoticeInvitingTender partialUpdatedNoticeInvitingTender = new NoticeInvitingTender();
        partialUpdatedNoticeInvitingTender.setId(noticeInvitingTender.getId());

        partialUpdatedNoticeInvitingTender
            .evaluationType(UPDATED_EVALUATION_TYPE)
            .prequalValidityPeriod(UPDATED_PREQUAL_VALIDITY_PERIOD)
            .invitingStrategy(UPDATED_INVITING_STRATEGY)
            .minNoBidsReceive(UPDATED_MIN_NO_BIDS_RECEIVE)
            .procuringEntity(UPDATED_PROCURING_ENTITY)
            .procuringEntityDesignation(UPDATED_PROCURING_ENTITY_DESIGNATION)
            .procuringEntityAddress(UPDATED_PROCURING_ENTITY_ADDRESS)
            .invitingAuthority(UPDATED_INVITING_AUTHORITY)
            .invitingAuthorityDesignation(UPDATED_INVITING_AUTHORITY_DESIGNATION)
            .invitingAuthorityAddress(UPDATED_INVITING_AUTHORITY_ADDRESS)
            .participatingCondnYn(UPDATED_PARTICIPATING_CONDN_YN)
            .tenderFee(UPDATED_TENDER_FEE)
            .emd(UPDATED_EMD)
            .bidValueType(UPDATED_BID_VALUE_TYPE)
            .techWeightage(UPDATED_TECH_WEIGHTAGE)
            .prequalTenderBidOpen(UPDATED_PREQUAL_TENDER_BID_OPEN)
            .tenderDocClose(UPDATED_TENDER_DOC_CLOSE)
            .tenderReceiptClose(UPDATED_TENDER_RECEIPT_CLOSE)
            .tenderQueryClose(UPDATED_TENDER_QUERY_CLOSE)
            .technicalBidOpen(UPDATED_TECHNICAL_BID_OPEN)
            .financialBidOpen(UPDATED_FINANCIAL_BID_OPEN)
            .publishedDate(UPDATED_PUBLISHED_DATE)
            .publishedBy(UPDATED_PUBLISHED_BY)
            .recalledDate(UPDATED_RECALLED_DATE)
            .recalledBy(UPDATED_RECALLED_BY)
            .bidSubmissionStartDate(UPDATED_BID_SUBMISSION_START_DATE)
            .bidValidityPeriod(UPDATED_BID_VALIDITY_PERIOD)
            .noOfCalls(UPDATED_NO_OF_CALLS)
            .preBidMeetingDate(UPDATED_PRE_BID_MEETING_DATE)
            .preBidMeetingYn(UPDATED_PRE_BID_MEETING_YN)
            .prebidMeetingAddress(UPDATED_PREBID_MEETING_ADDRESS)
            .preQualificationBidOpen(UPDATED_PRE_QUALIFICATION_BID_OPEN)
            .mandateAllItemsYn(UPDATED_MANDATE_ALL_ITEMS_YN)
            .queriesPublished(UPDATED_QUERIES_PUBLISHED)
            .denominationType(UPDATED_DENOMINATION_TYPE)
            .retenderedYn(UPDATED_RETENDERED_YN)
            .percentageRateType(UPDATED_PERCENTAGE_RATE_TYPE)
            .contactPerson(UPDATED_CONTACT_PERSON)
            .splitEmdRequiredYn(UPDATED_SPLIT_EMD_REQUIRED_YN)
            .emdBankGuarantee(UPDATED_EMD_BANK_GUARANTEE)
            .emdCash(UPDATED_EMD_CASH)
            .bgValidityPeriod(UPDATED_BG_VALIDITY_PERIOD)
            .hideWeightage(UPDATED_HIDE_WEIGHTAGE)
            .itemwiseTechEvalYn(UPDATED_ITEMWISE_TECH_EVAL_YN)
            .isMultipleSupplierSelectionAllowed(UPDATED_IS_MULTIPLE_SUPPLIER_SELECTION_ALLOWED)
            .techEvalStartDate(UPDATED_TECH_EVAL_START_DATE)
            .techEvalEndDate(UPDATED_TECH_EVAL_END_DATE)
            .commEvalStartDate(UPDATED_COMM_EVAL_START_DATE)
            .commEvalEndDate(UPDATED_COMM_EVAL_END_DATE)
            .emdVerifiedDate(UPDATED_EMD_VERIFIED_DATE)
            .multipleCurrencySelectionAllowedYn(UPDATED_MULTIPLE_CURRENCY_SELECTION_ALLOWED_YN)
            .isEditable(UPDATED_IS_EDITABLE)
            .isEvaluated(UPDATED_IS_EVALUATED)
            .isTechWeightageAllowed(UPDATED_IS_TECH_WEIGHTAGE_ALLOWED)
            .isTechWeightageCompleted(UPDATED_IS_TECH_WEIGHTAGE_COMPLETED)
            .isCommercialBidEditCompleted(UPDATED_IS_COMMERCIAL_BID_EDIT_COMPLETED)
            .templateYn(UPDATED_TEMPLATE_YN)
            .templateId(UPDATED_TEMPLATE_ID)
            .paymentVerifiedBy(UPDATED_PAYMENT_VERIFIED_BY)
            .paymentVerifiedDate(UPDATED_PAYMENT_VERIFIED_DATE)
            .isItemwiseCsrRequired(UPDATED_IS_ITEMWISE_CSR_REQUIRED)
            .isBidViewEnabled(UPDATED_IS_BID_VIEW_ENABLED)
            .isNegotiationRequired(UPDATED_IS_NEGOTIATION_REQUIRED)
            .highestBidderSelection(UPDATED_HIGHEST_BIDDER_SELECTION)
            .isVariableEmdAllowed(UPDATED_IS_VARIABLE_EMD_ALLOWED)
            .nitPublisherCertId(UPDATED_NIT_PUBLISHER_CERT_ID)
            .autoExtendYn(UPDATED_AUTO_EXTEND_YN)
            .noOfDaysExtend(UPDATED_NO_OF_DAYS_EXTEND)
            .isExtensionAvailable(UPDATED_IS_EXTENSION_AVAILABLE)
            .specialSchemeTender(UPDATED_SPECIAL_SCHEME_TENDER)
            .isBidValidityExpiryTaskCreated(UPDATED_IS_BID_VALIDITY_EXPIRY_TASK_CREATED)
            .evaluationTypeFlag(UPDATED_EVALUATION_TYPE_FLAG)
            .qcbsTenderYn(UPDATED_QCBS_TENDER_YN)
            .publishedUser(UPDATED_PUBLISHED_USER)
            .isWorldBankFunded(UPDATED_IS_WORLD_BANK_FUNDED)
            .ecClearanceNumber(UPDATED_EC_CLEARANCE_NUMBER)
            .ecClearanceDate(UPDATED_EC_CLEARANCE_DATE);

        restNoticeInvitingTenderMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedNoticeInvitingTender.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedNoticeInvitingTender))
            )
            .andExpect(status().isOk());

        // Validate the NoticeInvitingTender in the database
        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeUpdate);
        NoticeInvitingTender testNoticeInvitingTender = noticeInvitingTenderList.get(noticeInvitingTenderList.size() - 1);
        assertThat(testNoticeInvitingTender.getEvaluationType()).isEqualTo(UPDATED_EVALUATION_TYPE);
        assertThat(testNoticeInvitingTender.getPrequalValidityPeriod()).isEqualTo(UPDATED_PREQUAL_VALIDITY_PERIOD);
        assertThat(testNoticeInvitingTender.getInvitingStrategy()).isEqualTo(UPDATED_INVITING_STRATEGY);
        assertThat(testNoticeInvitingTender.getMinNoBidsReceive()).isEqualTo(UPDATED_MIN_NO_BIDS_RECEIVE);
        assertThat(testNoticeInvitingTender.getProcuringEntity()).isEqualTo(UPDATED_PROCURING_ENTITY);
        assertThat(testNoticeInvitingTender.getProcuringEntityDesignation()).isEqualTo(UPDATED_PROCURING_ENTITY_DESIGNATION);
        assertThat(testNoticeInvitingTender.getProcuringEntityAddress()).isEqualTo(UPDATED_PROCURING_ENTITY_ADDRESS);
        assertThat(testNoticeInvitingTender.getInvitingAuthority()).isEqualTo(UPDATED_INVITING_AUTHORITY);
        assertThat(testNoticeInvitingTender.getInvitingAuthorityDesignation()).isEqualTo(UPDATED_INVITING_AUTHORITY_DESIGNATION);
        assertThat(testNoticeInvitingTender.getInvitingAuthorityAddress()).isEqualTo(UPDATED_INVITING_AUTHORITY_ADDRESS);
        assertThat(testNoticeInvitingTender.getParticipatingCondnYn()).isEqualTo(UPDATED_PARTICIPATING_CONDN_YN);
        assertThat(testNoticeInvitingTender.getTenderFee()).isEqualByComparingTo(UPDATED_TENDER_FEE);
        assertThat(testNoticeInvitingTender.getEmd()).isEqualByComparingTo(UPDATED_EMD);
        assertThat(testNoticeInvitingTender.getBidValueType()).isEqualTo(UPDATED_BID_VALUE_TYPE);
        assertThat(testNoticeInvitingTender.getTechWeightage()).isEqualTo(UPDATED_TECH_WEIGHTAGE);
        assertThat(testNoticeInvitingTender.getPrequalTenderBidOpen()).isEqualTo(UPDATED_PREQUAL_TENDER_BID_OPEN);
        assertThat(testNoticeInvitingTender.getTenderDocClose()).isEqualTo(UPDATED_TENDER_DOC_CLOSE);
        assertThat(testNoticeInvitingTender.getTenderReceiptClose()).isEqualTo(UPDATED_TENDER_RECEIPT_CLOSE);
        assertThat(testNoticeInvitingTender.getTenderQueryClose()).isEqualTo(UPDATED_TENDER_QUERY_CLOSE);
        assertThat(testNoticeInvitingTender.getTechnicalBidOpen()).isEqualTo(UPDATED_TECHNICAL_BID_OPEN);
        assertThat(testNoticeInvitingTender.getFinancialBidOpen()).isEqualTo(UPDATED_FINANCIAL_BID_OPEN);
        assertThat(testNoticeInvitingTender.getPublishedDate()).isEqualTo(UPDATED_PUBLISHED_DATE);
        assertThat(testNoticeInvitingTender.getPublishedBy()).isEqualTo(UPDATED_PUBLISHED_BY);
        assertThat(testNoticeInvitingTender.getRecalledDate()).isEqualTo(UPDATED_RECALLED_DATE);
        assertThat(testNoticeInvitingTender.getRecalledBy()).isEqualTo(UPDATED_RECALLED_BY);
        assertThat(testNoticeInvitingTender.getBidSubmissionStartDate()).isEqualTo(UPDATED_BID_SUBMISSION_START_DATE);
        assertThat(testNoticeInvitingTender.getBidValidityPeriod()).isEqualTo(UPDATED_BID_VALIDITY_PERIOD);
        assertThat(testNoticeInvitingTender.getNoOfCalls()).isEqualTo(UPDATED_NO_OF_CALLS);
        assertThat(testNoticeInvitingTender.getPreBidMeetingDate()).isEqualTo(UPDATED_PRE_BID_MEETING_DATE);
        assertThat(testNoticeInvitingTender.getPreBidMeetingYn()).isEqualTo(UPDATED_PRE_BID_MEETING_YN);
        assertThat(testNoticeInvitingTender.getPrebidMeetingAddress()).isEqualTo(UPDATED_PREBID_MEETING_ADDRESS);
        assertThat(testNoticeInvitingTender.getPreQualificationBidOpen()).isEqualTo(UPDATED_PRE_QUALIFICATION_BID_OPEN);
        assertThat(testNoticeInvitingTender.getMandateAllItemsYn()).isEqualTo(UPDATED_MANDATE_ALL_ITEMS_YN);
        assertThat(testNoticeInvitingTender.getQueriesPublished()).isEqualTo(UPDATED_QUERIES_PUBLISHED);
        assertThat(testNoticeInvitingTender.getDenominationType()).isEqualTo(UPDATED_DENOMINATION_TYPE);
        assertThat(testNoticeInvitingTender.getRetenderedYn()).isEqualTo(UPDATED_RETENDERED_YN);
        assertThat(testNoticeInvitingTender.getPercentageRateType()).isEqualTo(UPDATED_PERCENTAGE_RATE_TYPE);
        assertThat(testNoticeInvitingTender.getContactPerson()).isEqualTo(UPDATED_CONTACT_PERSON);
        assertThat(testNoticeInvitingTender.getSplitEmdRequiredYn()).isEqualTo(UPDATED_SPLIT_EMD_REQUIRED_YN);
        assertThat(testNoticeInvitingTender.getEmdBankGuarantee()).isEqualByComparingTo(UPDATED_EMD_BANK_GUARANTEE);
        assertThat(testNoticeInvitingTender.getEmdCash()).isEqualByComparingTo(UPDATED_EMD_CASH);
        assertThat(testNoticeInvitingTender.getBgValidityPeriod()).isEqualTo(UPDATED_BG_VALIDITY_PERIOD);
        assertThat(testNoticeInvitingTender.getHideWeightage()).isEqualTo(UPDATED_HIDE_WEIGHTAGE);
        assertThat(testNoticeInvitingTender.getItemwiseTechEvalYn()).isEqualTo(UPDATED_ITEMWISE_TECH_EVAL_YN);
        assertThat(testNoticeInvitingTender.getIsMultipleSupplierSelectionAllowed())
            .isEqualTo(UPDATED_IS_MULTIPLE_SUPPLIER_SELECTION_ALLOWED);
        assertThat(testNoticeInvitingTender.getTechEvalStartDate()).isEqualTo(UPDATED_TECH_EVAL_START_DATE);
        assertThat(testNoticeInvitingTender.getTechEvalEndDate()).isEqualTo(UPDATED_TECH_EVAL_END_DATE);
        assertThat(testNoticeInvitingTender.getCommEvalStartDate()).isEqualTo(UPDATED_COMM_EVAL_START_DATE);
        assertThat(testNoticeInvitingTender.getCommEvalEndDate()).isEqualTo(UPDATED_COMM_EVAL_END_DATE);
        assertThat(testNoticeInvitingTender.getEmdVerifiedDate()).isEqualTo(UPDATED_EMD_VERIFIED_DATE);
        assertThat(testNoticeInvitingTender.getMultipleCurrencySelectionAllowedYn())
            .isEqualTo(UPDATED_MULTIPLE_CURRENCY_SELECTION_ALLOWED_YN);
        assertThat(testNoticeInvitingTender.getIsEditable()).isEqualTo(UPDATED_IS_EDITABLE);
        assertThat(testNoticeInvitingTender.getIsEvaluated()).isEqualTo(UPDATED_IS_EVALUATED);
        assertThat(testNoticeInvitingTender.getIsTechWeightageAllowed()).isEqualTo(UPDATED_IS_TECH_WEIGHTAGE_ALLOWED);
        assertThat(testNoticeInvitingTender.getIsTechWeightageCompleted()).isEqualTo(UPDATED_IS_TECH_WEIGHTAGE_COMPLETED);
        assertThat(testNoticeInvitingTender.getIsCommercialBidEditCompleted()).isEqualTo(UPDATED_IS_COMMERCIAL_BID_EDIT_COMPLETED);
        assertThat(testNoticeInvitingTender.getTemplateYn()).isEqualTo(UPDATED_TEMPLATE_YN);
        assertThat(testNoticeInvitingTender.getTemplateId()).isEqualTo(UPDATED_TEMPLATE_ID);
        assertThat(testNoticeInvitingTender.getPaymentVerifiedBy()).isEqualTo(UPDATED_PAYMENT_VERIFIED_BY);
        assertThat(testNoticeInvitingTender.getPaymentVerifiedDate()).isEqualTo(UPDATED_PAYMENT_VERIFIED_DATE);
        assertThat(testNoticeInvitingTender.getIsItemwiseCsrRequired()).isEqualTo(UPDATED_IS_ITEMWISE_CSR_REQUIRED);
        assertThat(testNoticeInvitingTender.getIsBidViewEnabled()).isEqualTo(UPDATED_IS_BID_VIEW_ENABLED);
        assertThat(testNoticeInvitingTender.getIsNegotiationRequired()).isEqualTo(UPDATED_IS_NEGOTIATION_REQUIRED);
        assertThat(testNoticeInvitingTender.getHighestBidderSelection()).isEqualTo(UPDATED_HIGHEST_BIDDER_SELECTION);
        assertThat(testNoticeInvitingTender.getIsVariableEmdAllowed()).isEqualTo(UPDATED_IS_VARIABLE_EMD_ALLOWED);
        assertThat(testNoticeInvitingTender.getNitPublisherCertId()).isEqualTo(UPDATED_NIT_PUBLISHER_CERT_ID);
        assertThat(testNoticeInvitingTender.getAutoExtendYn()).isEqualTo(UPDATED_AUTO_EXTEND_YN);
        assertThat(testNoticeInvitingTender.getNoOfDaysExtend()).isEqualTo(UPDATED_NO_OF_DAYS_EXTEND);
        assertThat(testNoticeInvitingTender.getIsExtensionAvailable()).isEqualTo(UPDATED_IS_EXTENSION_AVAILABLE);
        assertThat(testNoticeInvitingTender.getSpecialSchemeTender()).isEqualTo(UPDATED_SPECIAL_SCHEME_TENDER);
        assertThat(testNoticeInvitingTender.getIsBidValidityExpiryTaskCreated()).isEqualTo(UPDATED_IS_BID_VALIDITY_EXPIRY_TASK_CREATED);
        assertThat(testNoticeInvitingTender.getEvaluationTypeFlag()).isEqualTo(UPDATED_EVALUATION_TYPE_FLAG);
        assertThat(testNoticeInvitingTender.getQcbsTenderYn()).isEqualTo(UPDATED_QCBS_TENDER_YN);
        assertThat(testNoticeInvitingTender.getPublishedUser()).isEqualTo(UPDATED_PUBLISHED_USER);
        assertThat(testNoticeInvitingTender.getIsWorldBankFunded()).isEqualTo(UPDATED_IS_WORLD_BANK_FUNDED);
        assertThat(testNoticeInvitingTender.getEcClearanceNumber()).isEqualTo(UPDATED_EC_CLEARANCE_NUMBER);
        assertThat(testNoticeInvitingTender.getEcClearanceDate()).isEqualTo(UPDATED_EC_CLEARANCE_DATE);
    }

    @Test
    @Transactional
    void patchNonExistingNoticeInvitingTender() throws Exception {
        int databaseSizeBeforeUpdate = noticeInvitingTenderRepository.findAll().size();
        noticeInvitingTender.setId(count.incrementAndGet());

        // Create the NoticeInvitingTender
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNoticeInvitingTenderMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, noticeInvitingTenderDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NoticeInvitingTender in the database
        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchNoticeInvitingTender() throws Exception {
        int databaseSizeBeforeUpdate = noticeInvitingTenderRepository.findAll().size();
        noticeInvitingTender.setId(count.incrementAndGet());

        // Create the NoticeInvitingTender
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNoticeInvitingTenderMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NoticeInvitingTender in the database
        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamNoticeInvitingTender() throws Exception {
        int databaseSizeBeforeUpdate = noticeInvitingTenderRepository.findAll().size();
        noticeInvitingTender.setId(count.incrementAndGet());

        // Create the NoticeInvitingTender
        NoticeInvitingTenderDTO noticeInvitingTenderDTO = noticeInvitingTenderMapper.toDto(noticeInvitingTender);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNoticeInvitingTenderMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(noticeInvitingTenderDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the NoticeInvitingTender in the database
        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteNoticeInvitingTender() throws Exception {
        // Initialize the database
        noticeInvitingTenderRepository.saveAndFlush(noticeInvitingTender);

        int databaseSizeBeforeDelete = noticeInvitingTenderRepository.findAll().size();

        // Delete the noticeInvitingTender
        restNoticeInvitingTenderMockMvc
            .perform(delete(ENTITY_API_URL_ID, noticeInvitingTender.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NoticeInvitingTender> noticeInvitingTenderList = noticeInvitingTenderRepository.findAll();
        assertThat(noticeInvitingTenderList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
