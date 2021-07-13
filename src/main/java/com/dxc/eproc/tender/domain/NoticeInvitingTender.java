package com.dxc.eproc.tender.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A NoticeInvitingTender.
 */
@Entity
@Table(name = "notice_inviting_tender")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NoticeInvitingTender implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "evaluation_type", nullable = false)
    private Integer evaluationType;

    @NotNull
    @Column(name = "prequal_validity_period", nullable = false)
    private LocalDate prequalValidityPeriod;

    @NotNull
    @Column(name = "inviting_strategy", nullable = false)
    private Boolean invitingStrategy;

    @NotNull
    @Column(name = "min_no_bids_receive", nullable = false)
    private Integer minNoBidsReceive;

    @NotNull
    @Column(name = "procuring_entity", nullable = false)
    private Integer procuringEntity;

    @NotNull
    @Column(name = "procuring_entity_designation", nullable = false)
    private Integer procuringEntityDesignation;

    @NotNull
    @Column(name = "procuring_entity_address", nullable = false)
    private Integer procuringEntityAddress;

    @NotNull
    @Column(name = "inviting_authority", nullable = false)
    private Integer invitingAuthority;

    @NotNull
    @Column(name = "inviting_authority_designation", nullable = false)
    private Integer invitingAuthorityDesignation;

    @NotNull
    @Column(name = "inviting_authority_address", nullable = false)
    private Integer invitingAuthorityAddress;

    @NotNull
    @Column(name = "participating_condn_yn", nullable = false)
    private Boolean participatingCondnYn;

    @NotNull
    @Column(name = "tender_fee", precision = 21, scale = 2, nullable = false)
    private BigDecimal tenderFee;

    @NotNull
    @Column(name = "emd", precision = 21, scale = 2, nullable = false)
    private BigDecimal emd;

    @NotNull
    @Column(name = "bid_value_type", nullable = false)
    private Boolean bidValueType;

    @NotNull
    @Column(name = "tech_weightage", nullable = false)
    private Integer techWeightage;

    @NotNull
    @Column(name = "prequal_tender_bid_open", nullable = false)
    private LocalDate prequalTenderBidOpen;

    @NotNull
    @Column(name = "tender_doc_close", nullable = false)
    private LocalDate tenderDocClose;

    @NotNull
    @Column(name = "tender_receipt_close", nullable = false)
    private LocalDate tenderReceiptClose;

    @NotNull
    @Column(name = "tender_query_close", nullable = false)
    private LocalDate tenderQueryClose;

    @NotNull
    @Column(name = "technical_bid_open", nullable = false)
    private LocalDate technicalBidOpen;

    @NotNull
    @Column(name = "financial_bid_open", nullable = false)
    private LocalDate financialBidOpen;

    @NotNull
    @Column(name = "published_date", nullable = false)
    private LocalDate publishedDate;

    @NotNull
    @Column(name = "published_by", nullable = false)
    private Integer publishedBy;

    @NotNull
    @Column(name = "recalled_date", nullable = false)
    private LocalDate recalledDate;

    @NotNull
    @Column(name = "recalled_by", nullable = false)
    private Integer recalledBy;

    @NotNull
    @Column(name = "bid_submission_start_date", nullable = false)
    private LocalDate bidSubmissionStartDate;

    @NotNull
    @Column(name = "bid_validity_period", nullable = false)
    private Integer bidValidityPeriod;

    @NotNull
    @Column(name = "no_of_calls", nullable = false)
    private Integer noOfCalls;

    @NotNull
    @Column(name = "pre_bid_meeting_date", nullable = false)
    private LocalDate preBidMeetingDate;

    @NotNull
    @Column(name = "pre_bid_meeting_yn", nullable = false)
    private Boolean preBidMeetingYn;

    @NotNull
    @Column(name = "prebid_meeting_address", nullable = false)
    private Integer prebidMeetingAddress;

    @NotNull
    @Column(name = "pre_qualification_bid_open", nullable = false)
    private LocalDate preQualificationBidOpen;

    @NotNull
    @Column(name = "mandate_all_items_yn", nullable = false)
    private Boolean mandateAllItemsYn;

    @NotNull
    @Column(name = "queries_published", nullable = false)
    private Boolean queriesPublished;

    @NotNull
    @Column(name = "denomination_type", nullable = false)
    private String denominationType;

    @NotNull
    @Column(name = "retendered_yn", nullable = false)
    private Boolean retenderedYn;

    @NotNull
    @Column(name = "percentage_rate_type", nullable = false)
    private String percentageRateType;

    @NotNull
    @Column(name = "contact_person", nullable = false)
    private String contactPerson;

    @NotNull
    @Column(name = "split_emd_required_yn", nullable = false)
    private Boolean splitEmdRequiredYn;

    @NotNull
    @Column(name = "emd_bank_guarantee", precision = 21, scale = 2, nullable = false)
    private BigDecimal emdBankGuarantee;

    @NotNull
    @Column(name = "emd_cash", precision = 21, scale = 2, nullable = false)
    private BigDecimal emdCash;

    @NotNull
    @Column(name = "bg_validity_period", nullable = false)
    private Integer bgValidityPeriod;

    @NotNull
    @Column(name = "hide_weightage", nullable = false)
    private Boolean hideWeightage;

    @NotNull
    @Column(name = "itemwise_tech_eval_yn", nullable = false)
    private Boolean itemwiseTechEvalYn;

    @NotNull
    @Column(name = "is_multiple_supplier_selection_allowed", nullable = false)
    private Boolean isMultipleSupplierSelectionAllowed;

    @NotNull
    @Column(name = "tech_eval_start_date", nullable = false)
    private LocalDate techEvalStartDate;

    @NotNull
    @Column(name = "tech_eval_end_date", nullable = false)
    private LocalDate techEvalEndDate;

    @NotNull
    @Column(name = "comm_eval_start_date", nullable = false)
    private LocalDate commEvalStartDate;

    @NotNull
    @Column(name = "comm_eval_end_date", nullable = false)
    private LocalDate commEvalEndDate;

    @NotNull
    @Column(name = "emd_verified_date", nullable = false)
    private LocalDate emdVerifiedDate;

    @NotNull
    @Column(name = "multiple_currency_selection_allowed_yn", nullable = false)
    private Boolean multipleCurrencySelectionAllowedYn;

    @NotNull
    @Column(name = "is_editable", nullable = false)
    private Boolean isEditable;

    @NotNull
    @Column(name = "is_evaluated", nullable = false)
    private Boolean isEvaluated;

    @NotNull
    @Column(name = "is_tech_weightage_allowed", nullable = false)
    private Boolean isTechWeightageAllowed;

    @NotNull
    @Column(name = "is_tech_weightage_completed", nullable = false)
    private Boolean isTechWeightageCompleted;

    @NotNull
    @Column(name = "is_commercial_bid_edit_completed", nullable = false)
    private Boolean isCommercialBidEditCompleted;

    @NotNull
    @Column(name = "template_yn", nullable = false)
    private Boolean templateYn;

    @NotNull
    @Column(name = "template_id", nullable = false)
    private Long templateId;

    @NotNull
    @Column(name = "payment_verified_by", nullable = false)
    private Integer paymentVerifiedBy;

    @NotNull
    @Column(name = "payment_verified_date", nullable = false)
    private LocalDate paymentVerifiedDate;

    @NotNull
    @Column(name = "is_itemwise_csr_required", nullable = false)
    private Boolean isItemwiseCsrRequired;

    @NotNull
    @Column(name = "is_bid_view_enabled", nullable = false)
    private Boolean isBidViewEnabled;

    @NotNull
    @Column(name = "is_negotiation_required", nullable = false)
    private Boolean isNegotiationRequired;

    @NotNull
    @Column(name = "highest_bidder_selection", nullable = false)
    private Boolean highestBidderSelection;

    @NotNull
    @Column(name = "is_variable_emd_allowed", nullable = false)
    private Boolean isVariableEmdAllowed;

    @NotNull
    @Column(name = "nit_publisher_cert_id", nullable = false)
    private Integer nitPublisherCertId;

    @NotNull
    @Column(name = "auto_extend_yn", nullable = false)
    private Boolean autoExtendYn;

    @NotNull
    @Column(name = "no_of_days_extend", nullable = false)
    private Integer noOfDaysExtend;

    @NotNull
    @Column(name = "is_extension_available", nullable = false)
    private Boolean isExtensionAvailable;

    @NotNull
    @Column(name = "special_scheme_tender", nullable = false)
    private Boolean specialSchemeTender;

    @NotNull
    @Column(name = "is_bid_validity_expiry_task_created", nullable = false)
    private Boolean isBidValidityExpiryTaskCreated;

    @NotNull
    @Column(name = "evaluation_type_flag", nullable = false)
    private Boolean evaluationTypeFlag;

    @NotNull
    @Column(name = "qcbs_tender_yn", nullable = false)
    private Boolean qcbsTenderYn;

    @NotNull
    @Column(name = "published_user", nullable = false)
    private String publishedUser;

    @NotNull
    @Column(name = "is_world_bank_funded", nullable = false)
    private Boolean isWorldBankFunded;

    @NotNull
    @Column(name = "ec_clearance_number", nullable = false)
    private String ecClearanceNumber;

    @NotNull
    @Column(name = "ec_clearance_date", nullable = false)
    private LocalDate ecClearanceDate;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NoticeInvitingTender id(Long id) {
        this.id = id;
        return this;
    }

    public Integer getEvaluationType() {
        return this.evaluationType;
    }

    public NoticeInvitingTender evaluationType(Integer evaluationType) {
        this.evaluationType = evaluationType;
        return this;
    }

    public void setEvaluationType(Integer evaluationType) {
        this.evaluationType = evaluationType;
    }

    public LocalDate getPrequalValidityPeriod() {
        return this.prequalValidityPeriod;
    }

    public NoticeInvitingTender prequalValidityPeriod(LocalDate prequalValidityPeriod) {
        this.prequalValidityPeriod = prequalValidityPeriod;
        return this;
    }

    public void setPrequalValidityPeriod(LocalDate prequalValidityPeriod) {
        this.prequalValidityPeriod = prequalValidityPeriod;
    }

    public Boolean getInvitingStrategy() {
        return this.invitingStrategy;
    }

    public NoticeInvitingTender invitingStrategy(Boolean invitingStrategy) {
        this.invitingStrategy = invitingStrategy;
        return this;
    }

    public void setInvitingStrategy(Boolean invitingStrategy) {
        this.invitingStrategy = invitingStrategy;
    }

    public Integer getMinNoBidsReceive() {
        return this.minNoBidsReceive;
    }

    public NoticeInvitingTender minNoBidsReceive(Integer minNoBidsReceive) {
        this.minNoBidsReceive = minNoBidsReceive;
        return this;
    }

    public void setMinNoBidsReceive(Integer minNoBidsReceive) {
        this.minNoBidsReceive = minNoBidsReceive;
    }

    public Integer getProcuringEntity() {
        return this.procuringEntity;
    }

    public NoticeInvitingTender procuringEntity(Integer procuringEntity) {
        this.procuringEntity = procuringEntity;
        return this;
    }

    public void setProcuringEntity(Integer procuringEntity) {
        this.procuringEntity = procuringEntity;
    }

    public Integer getProcuringEntityDesignation() {
        return this.procuringEntityDesignation;
    }

    public NoticeInvitingTender procuringEntityDesignation(Integer procuringEntityDesignation) {
        this.procuringEntityDesignation = procuringEntityDesignation;
        return this;
    }

    public void setProcuringEntityDesignation(Integer procuringEntityDesignation) {
        this.procuringEntityDesignation = procuringEntityDesignation;
    }

    public Integer getProcuringEntityAddress() {
        return this.procuringEntityAddress;
    }

    public NoticeInvitingTender procuringEntityAddress(Integer procuringEntityAddress) {
        this.procuringEntityAddress = procuringEntityAddress;
        return this;
    }

    public void setProcuringEntityAddress(Integer procuringEntityAddress) {
        this.procuringEntityAddress = procuringEntityAddress;
    }

    public Integer getInvitingAuthority() {
        return this.invitingAuthority;
    }

    public NoticeInvitingTender invitingAuthority(Integer invitingAuthority) {
        this.invitingAuthority = invitingAuthority;
        return this;
    }

    public void setInvitingAuthority(Integer invitingAuthority) {
        this.invitingAuthority = invitingAuthority;
    }

    public Integer getInvitingAuthorityDesignation() {
        return this.invitingAuthorityDesignation;
    }

    public NoticeInvitingTender invitingAuthorityDesignation(Integer invitingAuthorityDesignation) {
        this.invitingAuthorityDesignation = invitingAuthorityDesignation;
        return this;
    }

    public void setInvitingAuthorityDesignation(Integer invitingAuthorityDesignation) {
        this.invitingAuthorityDesignation = invitingAuthorityDesignation;
    }

    public Integer getInvitingAuthorityAddress() {
        return this.invitingAuthorityAddress;
    }

    public NoticeInvitingTender invitingAuthorityAddress(Integer invitingAuthorityAddress) {
        this.invitingAuthorityAddress = invitingAuthorityAddress;
        return this;
    }

    public void setInvitingAuthorityAddress(Integer invitingAuthorityAddress) {
        this.invitingAuthorityAddress = invitingAuthorityAddress;
    }

    public Boolean getParticipatingCondnYn() {
        return this.participatingCondnYn;
    }

    public NoticeInvitingTender participatingCondnYn(Boolean participatingCondnYn) {
        this.participatingCondnYn = participatingCondnYn;
        return this;
    }

    public void setParticipatingCondnYn(Boolean participatingCondnYn) {
        this.participatingCondnYn = participatingCondnYn;
    }

    public BigDecimal getTenderFee() {
        return this.tenderFee;
    }

    public NoticeInvitingTender tenderFee(BigDecimal tenderFee) {
        this.tenderFee = tenderFee;
        return this;
    }

    public void setTenderFee(BigDecimal tenderFee) {
        this.tenderFee = tenderFee;
    }

    public BigDecimal getEmd() {
        return this.emd;
    }

    public NoticeInvitingTender emd(BigDecimal emd) {
        this.emd = emd;
        return this;
    }

    public void setEmd(BigDecimal emd) {
        this.emd = emd;
    }

    public Boolean getBidValueType() {
        return this.bidValueType;
    }

    public NoticeInvitingTender bidValueType(Boolean bidValueType) {
        this.bidValueType = bidValueType;
        return this;
    }

    public void setBidValueType(Boolean bidValueType) {
        this.bidValueType = bidValueType;
    }

    public Integer getTechWeightage() {
        return this.techWeightage;
    }

    public NoticeInvitingTender techWeightage(Integer techWeightage) {
        this.techWeightage = techWeightage;
        return this;
    }

    public void setTechWeightage(Integer techWeightage) {
        this.techWeightage = techWeightage;
    }

    public LocalDate getPrequalTenderBidOpen() {
        return this.prequalTenderBidOpen;
    }

    public NoticeInvitingTender prequalTenderBidOpen(LocalDate prequalTenderBidOpen) {
        this.prequalTenderBidOpen = prequalTenderBidOpen;
        return this;
    }

    public void setPrequalTenderBidOpen(LocalDate prequalTenderBidOpen) {
        this.prequalTenderBidOpen = prequalTenderBidOpen;
    }

    public LocalDate getTenderDocClose() {
        return this.tenderDocClose;
    }

    public NoticeInvitingTender tenderDocClose(LocalDate tenderDocClose) {
        this.tenderDocClose = tenderDocClose;
        return this;
    }

    public void setTenderDocClose(LocalDate tenderDocClose) {
        this.tenderDocClose = tenderDocClose;
    }

    public LocalDate getTenderReceiptClose() {
        return this.tenderReceiptClose;
    }

    public NoticeInvitingTender tenderReceiptClose(LocalDate tenderReceiptClose) {
        this.tenderReceiptClose = tenderReceiptClose;
        return this;
    }

    public void setTenderReceiptClose(LocalDate tenderReceiptClose) {
        this.tenderReceiptClose = tenderReceiptClose;
    }

    public LocalDate getTenderQueryClose() {
        return this.tenderQueryClose;
    }

    public NoticeInvitingTender tenderQueryClose(LocalDate tenderQueryClose) {
        this.tenderQueryClose = tenderQueryClose;
        return this;
    }

    public void setTenderQueryClose(LocalDate tenderQueryClose) {
        this.tenderQueryClose = tenderQueryClose;
    }

    public LocalDate getTechnicalBidOpen() {
        return this.technicalBidOpen;
    }

    public NoticeInvitingTender technicalBidOpen(LocalDate technicalBidOpen) {
        this.technicalBidOpen = technicalBidOpen;
        return this;
    }

    public void setTechnicalBidOpen(LocalDate technicalBidOpen) {
        this.technicalBidOpen = technicalBidOpen;
    }

    public LocalDate getFinancialBidOpen() {
        return this.financialBidOpen;
    }

    public NoticeInvitingTender financialBidOpen(LocalDate financialBidOpen) {
        this.financialBidOpen = financialBidOpen;
        return this;
    }

    public void setFinancialBidOpen(LocalDate financialBidOpen) {
        this.financialBidOpen = financialBidOpen;
    }

    public LocalDate getPublishedDate() {
        return this.publishedDate;
    }

    public NoticeInvitingTender publishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
        return this;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

    public Integer getPublishedBy() {
        return this.publishedBy;
    }

    public NoticeInvitingTender publishedBy(Integer publishedBy) {
        this.publishedBy = publishedBy;
        return this;
    }

    public void setPublishedBy(Integer publishedBy) {
        this.publishedBy = publishedBy;
    }

    public LocalDate getRecalledDate() {
        return this.recalledDate;
    }

    public NoticeInvitingTender recalledDate(LocalDate recalledDate) {
        this.recalledDate = recalledDate;
        return this;
    }

    public void setRecalledDate(LocalDate recalledDate) {
        this.recalledDate = recalledDate;
    }

    public Integer getRecalledBy() {
        return this.recalledBy;
    }

    public NoticeInvitingTender recalledBy(Integer recalledBy) {
        this.recalledBy = recalledBy;
        return this;
    }

    public void setRecalledBy(Integer recalledBy) {
        this.recalledBy = recalledBy;
    }

    public LocalDate getBidSubmissionStartDate() {
        return this.bidSubmissionStartDate;
    }

    public NoticeInvitingTender bidSubmissionStartDate(LocalDate bidSubmissionStartDate) {
        this.bidSubmissionStartDate = bidSubmissionStartDate;
        return this;
    }

    public void setBidSubmissionStartDate(LocalDate bidSubmissionStartDate) {
        this.bidSubmissionStartDate = bidSubmissionStartDate;
    }

    public Integer getBidValidityPeriod() {
        return this.bidValidityPeriod;
    }

    public NoticeInvitingTender bidValidityPeriod(Integer bidValidityPeriod) {
        this.bidValidityPeriod = bidValidityPeriod;
        return this;
    }

    public void setBidValidityPeriod(Integer bidValidityPeriod) {
        this.bidValidityPeriod = bidValidityPeriod;
    }

    public Integer getNoOfCalls() {
        return this.noOfCalls;
    }

    public NoticeInvitingTender noOfCalls(Integer noOfCalls) {
        this.noOfCalls = noOfCalls;
        return this;
    }

    public void setNoOfCalls(Integer noOfCalls) {
        this.noOfCalls = noOfCalls;
    }

    public LocalDate getPreBidMeetingDate() {
        return this.preBidMeetingDate;
    }

    public NoticeInvitingTender preBidMeetingDate(LocalDate preBidMeetingDate) {
        this.preBidMeetingDate = preBidMeetingDate;
        return this;
    }

    public void setPreBidMeetingDate(LocalDate preBidMeetingDate) {
        this.preBidMeetingDate = preBidMeetingDate;
    }

    public Boolean getPreBidMeetingYn() {
        return this.preBidMeetingYn;
    }

    public NoticeInvitingTender preBidMeetingYn(Boolean preBidMeetingYn) {
        this.preBidMeetingYn = preBidMeetingYn;
        return this;
    }

    public void setPreBidMeetingYn(Boolean preBidMeetingYn) {
        this.preBidMeetingYn = preBidMeetingYn;
    }

    public Integer getPrebidMeetingAddress() {
        return this.prebidMeetingAddress;
    }

    public NoticeInvitingTender prebidMeetingAddress(Integer prebidMeetingAddress) {
        this.prebidMeetingAddress = prebidMeetingAddress;
        return this;
    }

    public void setPrebidMeetingAddress(Integer prebidMeetingAddress) {
        this.prebidMeetingAddress = prebidMeetingAddress;
    }

    public LocalDate getPreQualificationBidOpen() {
        return this.preQualificationBidOpen;
    }

    public NoticeInvitingTender preQualificationBidOpen(LocalDate preQualificationBidOpen) {
        this.preQualificationBidOpen = preQualificationBidOpen;
        return this;
    }

    public void setPreQualificationBidOpen(LocalDate preQualificationBidOpen) {
        this.preQualificationBidOpen = preQualificationBidOpen;
    }

    public Boolean getMandateAllItemsYn() {
        return this.mandateAllItemsYn;
    }

    public NoticeInvitingTender mandateAllItemsYn(Boolean mandateAllItemsYn) {
        this.mandateAllItemsYn = mandateAllItemsYn;
        return this;
    }

    public void setMandateAllItemsYn(Boolean mandateAllItemsYn) {
        this.mandateAllItemsYn = mandateAllItemsYn;
    }

    public Boolean getQueriesPublished() {
        return this.queriesPublished;
    }

    public NoticeInvitingTender queriesPublished(Boolean queriesPublished) {
        this.queriesPublished = queriesPublished;
        return this;
    }

    public void setQueriesPublished(Boolean queriesPublished) {
        this.queriesPublished = queriesPublished;
    }

    public String getDenominationType() {
        return this.denominationType;
    }

    public NoticeInvitingTender denominationType(String denominationType) {
        this.denominationType = denominationType;
        return this;
    }

    public void setDenominationType(String denominationType) {
        this.denominationType = denominationType;
    }

    public Boolean getRetenderedYn() {
        return this.retenderedYn;
    }

    public NoticeInvitingTender retenderedYn(Boolean retenderedYn) {
        this.retenderedYn = retenderedYn;
        return this;
    }

    public void setRetenderedYn(Boolean retenderedYn) {
        this.retenderedYn = retenderedYn;
    }

    public String getPercentageRateType() {
        return this.percentageRateType;
    }

    public NoticeInvitingTender percentageRateType(String percentageRateType) {
        this.percentageRateType = percentageRateType;
        return this;
    }

    public void setPercentageRateType(String percentageRateType) {
        this.percentageRateType = percentageRateType;
    }

    public String getContactPerson() {
        return this.contactPerson;
    }

    public NoticeInvitingTender contactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
        return this;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public Boolean getSplitEmdRequiredYn() {
        return this.splitEmdRequiredYn;
    }

    public NoticeInvitingTender splitEmdRequiredYn(Boolean splitEmdRequiredYn) {
        this.splitEmdRequiredYn = splitEmdRequiredYn;
        return this;
    }

    public void setSplitEmdRequiredYn(Boolean splitEmdRequiredYn) {
        this.splitEmdRequiredYn = splitEmdRequiredYn;
    }

    public BigDecimal getEmdBankGuarantee() {
        return this.emdBankGuarantee;
    }

    public NoticeInvitingTender emdBankGuarantee(BigDecimal emdBankGuarantee) {
        this.emdBankGuarantee = emdBankGuarantee;
        return this;
    }

    public void setEmdBankGuarantee(BigDecimal emdBankGuarantee) {
        this.emdBankGuarantee = emdBankGuarantee;
    }

    public BigDecimal getEmdCash() {
        return this.emdCash;
    }

    public NoticeInvitingTender emdCash(BigDecimal emdCash) {
        this.emdCash = emdCash;
        return this;
    }

    public void setEmdCash(BigDecimal emdCash) {
        this.emdCash = emdCash;
    }

    public Integer getBgValidityPeriod() {
        return this.bgValidityPeriod;
    }

    public NoticeInvitingTender bgValidityPeriod(Integer bgValidityPeriod) {
        this.bgValidityPeriod = bgValidityPeriod;
        return this;
    }

    public void setBgValidityPeriod(Integer bgValidityPeriod) {
        this.bgValidityPeriod = bgValidityPeriod;
    }

    public Boolean getHideWeightage() {
        return this.hideWeightage;
    }

    public NoticeInvitingTender hideWeightage(Boolean hideWeightage) {
        this.hideWeightage = hideWeightage;
        return this;
    }

    public void setHideWeightage(Boolean hideWeightage) {
        this.hideWeightage = hideWeightage;
    }

    public Boolean getItemwiseTechEvalYn() {
        return this.itemwiseTechEvalYn;
    }

    public NoticeInvitingTender itemwiseTechEvalYn(Boolean itemwiseTechEvalYn) {
        this.itemwiseTechEvalYn = itemwiseTechEvalYn;
        return this;
    }

    public void setItemwiseTechEvalYn(Boolean itemwiseTechEvalYn) {
        this.itemwiseTechEvalYn = itemwiseTechEvalYn;
    }

    public Boolean getIsMultipleSupplierSelectionAllowed() {
        return this.isMultipleSupplierSelectionAllowed;
    }

    public NoticeInvitingTender isMultipleSupplierSelectionAllowed(Boolean isMultipleSupplierSelectionAllowed) {
        this.isMultipleSupplierSelectionAllowed = isMultipleSupplierSelectionAllowed;
        return this;
    }

    public void setIsMultipleSupplierSelectionAllowed(Boolean isMultipleSupplierSelectionAllowed) {
        this.isMultipleSupplierSelectionAllowed = isMultipleSupplierSelectionAllowed;
    }

    public LocalDate getTechEvalStartDate() {
        return this.techEvalStartDate;
    }

    public NoticeInvitingTender techEvalStartDate(LocalDate techEvalStartDate) {
        this.techEvalStartDate = techEvalStartDate;
        return this;
    }

    public void setTechEvalStartDate(LocalDate techEvalStartDate) {
        this.techEvalStartDate = techEvalStartDate;
    }

    public LocalDate getTechEvalEndDate() {
        return this.techEvalEndDate;
    }

    public NoticeInvitingTender techEvalEndDate(LocalDate techEvalEndDate) {
        this.techEvalEndDate = techEvalEndDate;
        return this;
    }

    public void setTechEvalEndDate(LocalDate techEvalEndDate) {
        this.techEvalEndDate = techEvalEndDate;
    }

    public LocalDate getCommEvalStartDate() {
        return this.commEvalStartDate;
    }

    public NoticeInvitingTender commEvalStartDate(LocalDate commEvalStartDate) {
        this.commEvalStartDate = commEvalStartDate;
        return this;
    }

    public void setCommEvalStartDate(LocalDate commEvalStartDate) {
        this.commEvalStartDate = commEvalStartDate;
    }

    public LocalDate getCommEvalEndDate() {
        return this.commEvalEndDate;
    }

    public NoticeInvitingTender commEvalEndDate(LocalDate commEvalEndDate) {
        this.commEvalEndDate = commEvalEndDate;
        return this;
    }

    public void setCommEvalEndDate(LocalDate commEvalEndDate) {
        this.commEvalEndDate = commEvalEndDate;
    }

    public LocalDate getEmdVerifiedDate() {
        return this.emdVerifiedDate;
    }

    public NoticeInvitingTender emdVerifiedDate(LocalDate emdVerifiedDate) {
        this.emdVerifiedDate = emdVerifiedDate;
        return this;
    }

    public void setEmdVerifiedDate(LocalDate emdVerifiedDate) {
        this.emdVerifiedDate = emdVerifiedDate;
    }

    public Boolean getMultipleCurrencySelectionAllowedYn() {
        return this.multipleCurrencySelectionAllowedYn;
    }

    public NoticeInvitingTender multipleCurrencySelectionAllowedYn(Boolean multipleCurrencySelectionAllowedYn) {
        this.multipleCurrencySelectionAllowedYn = multipleCurrencySelectionAllowedYn;
        return this;
    }

    public void setMultipleCurrencySelectionAllowedYn(Boolean multipleCurrencySelectionAllowedYn) {
        this.multipleCurrencySelectionAllowedYn = multipleCurrencySelectionAllowedYn;
    }

    public Boolean getIsEditable() {
        return this.isEditable;
    }

    public NoticeInvitingTender isEditable(Boolean isEditable) {
        this.isEditable = isEditable;
        return this;
    }

    public void setIsEditable(Boolean isEditable) {
        this.isEditable = isEditable;
    }

    public Boolean getIsEvaluated() {
        return this.isEvaluated;
    }

    public NoticeInvitingTender isEvaluated(Boolean isEvaluated) {
        this.isEvaluated = isEvaluated;
        return this;
    }

    public void setIsEvaluated(Boolean isEvaluated) {
        this.isEvaluated = isEvaluated;
    }

    public Boolean getIsTechWeightageAllowed() {
        return this.isTechWeightageAllowed;
    }

    public NoticeInvitingTender isTechWeightageAllowed(Boolean isTechWeightageAllowed) {
        this.isTechWeightageAllowed = isTechWeightageAllowed;
        return this;
    }

    public void setIsTechWeightageAllowed(Boolean isTechWeightageAllowed) {
        this.isTechWeightageAllowed = isTechWeightageAllowed;
    }

    public Boolean getIsTechWeightageCompleted() {
        return this.isTechWeightageCompleted;
    }

    public NoticeInvitingTender isTechWeightageCompleted(Boolean isTechWeightageCompleted) {
        this.isTechWeightageCompleted = isTechWeightageCompleted;
        return this;
    }

    public void setIsTechWeightageCompleted(Boolean isTechWeightageCompleted) {
        this.isTechWeightageCompleted = isTechWeightageCompleted;
    }

    public Boolean getIsCommercialBidEditCompleted() {
        return this.isCommercialBidEditCompleted;
    }

    public NoticeInvitingTender isCommercialBidEditCompleted(Boolean isCommercialBidEditCompleted) {
        this.isCommercialBidEditCompleted = isCommercialBidEditCompleted;
        return this;
    }

    public void setIsCommercialBidEditCompleted(Boolean isCommercialBidEditCompleted) {
        this.isCommercialBidEditCompleted = isCommercialBidEditCompleted;
    }

    public Boolean getTemplateYn() {
        return this.templateYn;
    }

    public NoticeInvitingTender templateYn(Boolean templateYn) {
        this.templateYn = templateYn;
        return this;
    }

    public void setTemplateYn(Boolean templateYn) {
        this.templateYn = templateYn;
    }

    public Long getTemplateId() {
        return this.templateId;
    }

    public NoticeInvitingTender templateId(Long templateId) {
        this.templateId = templateId;
        return this;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public Integer getPaymentVerifiedBy() {
        return this.paymentVerifiedBy;
    }

    public NoticeInvitingTender paymentVerifiedBy(Integer paymentVerifiedBy) {
        this.paymentVerifiedBy = paymentVerifiedBy;
        return this;
    }

    public void setPaymentVerifiedBy(Integer paymentVerifiedBy) {
        this.paymentVerifiedBy = paymentVerifiedBy;
    }

    public LocalDate getPaymentVerifiedDate() {
        return this.paymentVerifiedDate;
    }

    public NoticeInvitingTender paymentVerifiedDate(LocalDate paymentVerifiedDate) {
        this.paymentVerifiedDate = paymentVerifiedDate;
        return this;
    }

    public void setPaymentVerifiedDate(LocalDate paymentVerifiedDate) {
        this.paymentVerifiedDate = paymentVerifiedDate;
    }

    public Boolean getIsItemwiseCsrRequired() {
        return this.isItemwiseCsrRequired;
    }

    public NoticeInvitingTender isItemwiseCsrRequired(Boolean isItemwiseCsrRequired) {
        this.isItemwiseCsrRequired = isItemwiseCsrRequired;
        return this;
    }

    public void setIsItemwiseCsrRequired(Boolean isItemwiseCsrRequired) {
        this.isItemwiseCsrRequired = isItemwiseCsrRequired;
    }

    public Boolean getIsBidViewEnabled() {
        return this.isBidViewEnabled;
    }

    public NoticeInvitingTender isBidViewEnabled(Boolean isBidViewEnabled) {
        this.isBidViewEnabled = isBidViewEnabled;
        return this;
    }

    public void setIsBidViewEnabled(Boolean isBidViewEnabled) {
        this.isBidViewEnabled = isBidViewEnabled;
    }

    public Boolean getIsNegotiationRequired() {
        return this.isNegotiationRequired;
    }

    public NoticeInvitingTender isNegotiationRequired(Boolean isNegotiationRequired) {
        this.isNegotiationRequired = isNegotiationRequired;
        return this;
    }

    public void setIsNegotiationRequired(Boolean isNegotiationRequired) {
        this.isNegotiationRequired = isNegotiationRequired;
    }

    public Boolean getHighestBidderSelection() {
        return this.highestBidderSelection;
    }

    public NoticeInvitingTender highestBidderSelection(Boolean highestBidderSelection) {
        this.highestBidderSelection = highestBidderSelection;
        return this;
    }

    public void setHighestBidderSelection(Boolean highestBidderSelection) {
        this.highestBidderSelection = highestBidderSelection;
    }

    public Boolean getIsVariableEmdAllowed() {
        return this.isVariableEmdAllowed;
    }

    public NoticeInvitingTender isVariableEmdAllowed(Boolean isVariableEmdAllowed) {
        this.isVariableEmdAllowed = isVariableEmdAllowed;
        return this;
    }

    public void setIsVariableEmdAllowed(Boolean isVariableEmdAllowed) {
        this.isVariableEmdAllowed = isVariableEmdAllowed;
    }

    public Integer getNitPublisherCertId() {
        return this.nitPublisherCertId;
    }

    public NoticeInvitingTender nitPublisherCertId(Integer nitPublisherCertId) {
        this.nitPublisherCertId = nitPublisherCertId;
        return this;
    }

    public void setNitPublisherCertId(Integer nitPublisherCertId) {
        this.nitPublisherCertId = nitPublisherCertId;
    }

    public Boolean getAutoExtendYn() {
        return this.autoExtendYn;
    }

    public NoticeInvitingTender autoExtendYn(Boolean autoExtendYn) {
        this.autoExtendYn = autoExtendYn;
        return this;
    }

    public void setAutoExtendYn(Boolean autoExtendYn) {
        this.autoExtendYn = autoExtendYn;
    }

    public Integer getNoOfDaysExtend() {
        return this.noOfDaysExtend;
    }

    public NoticeInvitingTender noOfDaysExtend(Integer noOfDaysExtend) {
        this.noOfDaysExtend = noOfDaysExtend;
        return this;
    }

    public void setNoOfDaysExtend(Integer noOfDaysExtend) {
        this.noOfDaysExtend = noOfDaysExtend;
    }

    public Boolean getIsExtensionAvailable() {
        return this.isExtensionAvailable;
    }

    public NoticeInvitingTender isExtensionAvailable(Boolean isExtensionAvailable) {
        this.isExtensionAvailable = isExtensionAvailable;
        return this;
    }

    public void setIsExtensionAvailable(Boolean isExtensionAvailable) {
        this.isExtensionAvailable = isExtensionAvailable;
    }

    public Boolean getSpecialSchemeTender() {
        return this.specialSchemeTender;
    }

    public NoticeInvitingTender specialSchemeTender(Boolean specialSchemeTender) {
        this.specialSchemeTender = specialSchemeTender;
        return this;
    }

    public void setSpecialSchemeTender(Boolean specialSchemeTender) {
        this.specialSchemeTender = specialSchemeTender;
    }

    public Boolean getIsBidValidityExpiryTaskCreated() {
        return this.isBidValidityExpiryTaskCreated;
    }

    public NoticeInvitingTender isBidValidityExpiryTaskCreated(Boolean isBidValidityExpiryTaskCreated) {
        this.isBidValidityExpiryTaskCreated = isBidValidityExpiryTaskCreated;
        return this;
    }

    public void setIsBidValidityExpiryTaskCreated(Boolean isBidValidityExpiryTaskCreated) {
        this.isBidValidityExpiryTaskCreated = isBidValidityExpiryTaskCreated;
    }

    public Boolean getEvaluationTypeFlag() {
        return this.evaluationTypeFlag;
    }

    public NoticeInvitingTender evaluationTypeFlag(Boolean evaluationTypeFlag) {
        this.evaluationTypeFlag = evaluationTypeFlag;
        return this;
    }

    public void setEvaluationTypeFlag(Boolean evaluationTypeFlag) {
        this.evaluationTypeFlag = evaluationTypeFlag;
    }

    public Boolean getQcbsTenderYn() {
        return this.qcbsTenderYn;
    }

    public NoticeInvitingTender qcbsTenderYn(Boolean qcbsTenderYn) {
        this.qcbsTenderYn = qcbsTenderYn;
        return this;
    }

    public void setQcbsTenderYn(Boolean qcbsTenderYn) {
        this.qcbsTenderYn = qcbsTenderYn;
    }

    public String getPublishedUser() {
        return this.publishedUser;
    }

    public NoticeInvitingTender publishedUser(String publishedUser) {
        this.publishedUser = publishedUser;
        return this;
    }

    public void setPublishedUser(String publishedUser) {
        this.publishedUser = publishedUser;
    }

    public Boolean getIsWorldBankFunded() {
        return this.isWorldBankFunded;
    }

    public NoticeInvitingTender isWorldBankFunded(Boolean isWorldBankFunded) {
        this.isWorldBankFunded = isWorldBankFunded;
        return this;
    }

    public void setIsWorldBankFunded(Boolean isWorldBankFunded) {
        this.isWorldBankFunded = isWorldBankFunded;
    }

    public String getEcClearanceNumber() {
        return this.ecClearanceNumber;
    }

    public NoticeInvitingTender ecClearanceNumber(String ecClearanceNumber) {
        this.ecClearanceNumber = ecClearanceNumber;
        return this;
    }

    public void setEcClearanceNumber(String ecClearanceNumber) {
        this.ecClearanceNumber = ecClearanceNumber;
    }

    public LocalDate getEcClearanceDate() {
        return this.ecClearanceDate;
    }

    public NoticeInvitingTender ecClearanceDate(LocalDate ecClearanceDate) {
        this.ecClearanceDate = ecClearanceDate;
        return this;
    }

    public void setEcClearanceDate(LocalDate ecClearanceDate) {
        this.ecClearanceDate = ecClearanceDate;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NoticeInvitingTender)) {
            return false;
        }
        return id != null && id.equals(((NoticeInvitingTender) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NoticeInvitingTender{" +
            "id=" + getId() +
            ", evaluationType=" + getEvaluationType() +
            ", prequalValidityPeriod='" + getPrequalValidityPeriod() + "'" +
            ", invitingStrategy='" + getInvitingStrategy() + "'" +
            ", minNoBidsReceive=" + getMinNoBidsReceive() +
            ", procuringEntity=" + getProcuringEntity() +
            ", procuringEntityDesignation=" + getProcuringEntityDesignation() +
            ", procuringEntityAddress=" + getProcuringEntityAddress() +
            ", invitingAuthority=" + getInvitingAuthority() +
            ", invitingAuthorityDesignation=" + getInvitingAuthorityDesignation() +
            ", invitingAuthorityAddress=" + getInvitingAuthorityAddress() +
            ", participatingCondnYn='" + getParticipatingCondnYn() + "'" +
            ", tenderFee=" + getTenderFee() +
            ", emd=" + getEmd() +
            ", bidValueType='" + getBidValueType() + "'" +
            ", techWeightage=" + getTechWeightage() +
            ", prequalTenderBidOpen='" + getPrequalTenderBidOpen() + "'" +
            ", tenderDocClose='" + getTenderDocClose() + "'" +
            ", tenderReceiptClose='" + getTenderReceiptClose() + "'" +
            ", tenderQueryClose='" + getTenderQueryClose() + "'" +
            ", technicalBidOpen='" + getTechnicalBidOpen() + "'" +
            ", financialBidOpen='" + getFinancialBidOpen() + "'" +
            ", publishedDate='" + getPublishedDate() + "'" +
            ", publishedBy=" + getPublishedBy() +
            ", recalledDate='" + getRecalledDate() + "'" +
            ", recalledBy=" + getRecalledBy() +
            ", bidSubmissionStartDate='" + getBidSubmissionStartDate() + "'" +
            ", bidValidityPeriod=" + getBidValidityPeriod() +
            ", noOfCalls=" + getNoOfCalls() +
            ", preBidMeetingDate='" + getPreBidMeetingDate() + "'" +
            ", preBidMeetingYn='" + getPreBidMeetingYn() + "'" +
            ", prebidMeetingAddress=" + getPrebidMeetingAddress() +
            ", preQualificationBidOpen='" + getPreQualificationBidOpen() + "'" +
            ", mandateAllItemsYn='" + getMandateAllItemsYn() + "'" +
            ", queriesPublished='" + getQueriesPublished() + "'" +
            ", denominationType='" + getDenominationType() + "'" +
            ", retenderedYn='" + getRetenderedYn() + "'" +
            ", percentageRateType='" + getPercentageRateType() + "'" +
            ", contactPerson='" + getContactPerson() + "'" +
            ", splitEmdRequiredYn='" + getSplitEmdRequiredYn() + "'" +
            ", emdBankGuarantee=" + getEmdBankGuarantee() +
            ", emdCash=" + getEmdCash() +
            ", bgValidityPeriod=" + getBgValidityPeriod() +
            ", hideWeightage='" + getHideWeightage() + "'" +
            ", itemwiseTechEvalYn='" + getItemwiseTechEvalYn() + "'" +
            ", isMultipleSupplierSelectionAllowed='" + getIsMultipleSupplierSelectionAllowed() + "'" +
            ", techEvalStartDate='" + getTechEvalStartDate() + "'" +
            ", techEvalEndDate='" + getTechEvalEndDate() + "'" +
            ", commEvalStartDate='" + getCommEvalStartDate() + "'" +
            ", commEvalEndDate='" + getCommEvalEndDate() + "'" +
            ", emdVerifiedDate='" + getEmdVerifiedDate() + "'" +
            ", multipleCurrencySelectionAllowedYn='" + getMultipleCurrencySelectionAllowedYn() + "'" +
            ", isEditable='" + getIsEditable() + "'" +
            ", isEvaluated='" + getIsEvaluated() + "'" +
            ", isTechWeightageAllowed='" + getIsTechWeightageAllowed() + "'" +
            ", isTechWeightageCompleted='" + getIsTechWeightageCompleted() + "'" +
            ", isCommercialBidEditCompleted='" + getIsCommercialBidEditCompleted() + "'" +
            ", templateYn='" + getTemplateYn() + "'" +
            ", templateId=" + getTemplateId() +
            ", paymentVerifiedBy=" + getPaymentVerifiedBy() +
            ", paymentVerifiedDate='" + getPaymentVerifiedDate() + "'" +
            ", isItemwiseCsrRequired='" + getIsItemwiseCsrRequired() + "'" +
            ", isBidViewEnabled='" + getIsBidViewEnabled() + "'" +
            ", isNegotiationRequired='" + getIsNegotiationRequired() + "'" +
            ", highestBidderSelection='" + getHighestBidderSelection() + "'" +
            ", isVariableEmdAllowed='" + getIsVariableEmdAllowed() + "'" +
            ", nitPublisherCertId=" + getNitPublisherCertId() +
            ", autoExtendYn='" + getAutoExtendYn() + "'" +
            ", noOfDaysExtend=" + getNoOfDaysExtend() +
            ", isExtensionAvailable='" + getIsExtensionAvailable() + "'" +
            ", specialSchemeTender='" + getSpecialSchemeTender() + "'" +
            ", isBidValidityExpiryTaskCreated='" + getIsBidValidityExpiryTaskCreated() + "'" +
            ", evaluationTypeFlag='" + getEvaluationTypeFlag() + "'" +
            ", qcbsTenderYn='" + getQcbsTenderYn() + "'" +
            ", publishedUser='" + getPublishedUser() + "'" +
            ", isWorldBankFunded='" + getIsWorldBankFunded() + "'" +
            ", ecClearanceNumber='" + getEcClearanceNumber() + "'" +
            ", ecClearanceDate='" + getEcClearanceDate() + "'" +
            "}";
    }
}
