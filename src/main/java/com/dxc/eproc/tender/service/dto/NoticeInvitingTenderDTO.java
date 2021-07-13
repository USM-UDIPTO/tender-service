package com.dxc.eproc.tender.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.dxc.eproc.tender.domain.NoticeInvitingTender} entity.
 */
public class NoticeInvitingTenderDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer evaluationType;

    @NotNull
    private LocalDate prequalValidityPeriod;

    @NotNull
    private Boolean invitingStrategy;

    @NotNull
    private Integer minNoBidsReceive;

    @NotNull
    private Integer procuringEntity;

    @NotNull
    private Integer procuringEntityDesignation;

    @NotNull
    private Integer procuringEntityAddress;

    @NotNull
    private Integer invitingAuthority;

    @NotNull
    private Integer invitingAuthorityDesignation;

    @NotNull
    private Integer invitingAuthorityAddress;

    @NotNull
    private Boolean participatingCondnYn;

    @NotNull
    private BigDecimal tenderFee;

    @NotNull
    private BigDecimal emd;

    @NotNull
    private Boolean bidValueType;

    @NotNull
    private Integer techWeightage;

    @NotNull
    private LocalDate prequalTenderBidOpen;

    @NotNull
    private LocalDate tenderDocClose;

    @NotNull
    private LocalDate tenderReceiptClose;

    @NotNull
    private LocalDate tenderQueryClose;

    @NotNull
    private LocalDate technicalBidOpen;

    @NotNull
    private LocalDate financialBidOpen;

    @NotNull
    private LocalDate publishedDate;

    @NotNull
    private Integer publishedBy;

    @NotNull
    private LocalDate recalledDate;

    @NotNull
    private Integer recalledBy;

    @NotNull
    private LocalDate bidSubmissionStartDate;

    @NotNull
    private Integer bidValidityPeriod;

    @NotNull
    private Integer noOfCalls;

    @NotNull
    private LocalDate preBidMeetingDate;

    @NotNull
    private Boolean preBidMeetingYn;

    @NotNull
    private Integer prebidMeetingAddress;

    @NotNull
    private LocalDate preQualificationBidOpen;

    @NotNull
    private Boolean mandateAllItemsYn;

    @NotNull
    private Boolean queriesPublished;

    @NotNull
    private String denominationType;

    @NotNull
    private Boolean retenderedYn;

    @NotNull
    private String percentageRateType;

    @NotNull
    private String contactPerson;

    @NotNull
    private Boolean splitEmdRequiredYn;

    @NotNull
    private BigDecimal emdBankGuarantee;

    @NotNull
    private BigDecimal emdCash;

    @NotNull
    private Integer bgValidityPeriod;

    @NotNull
    private Boolean hideWeightage;

    @NotNull
    private Boolean itemwiseTechEvalYn;

    @NotNull
    private Boolean isMultipleSupplierSelectionAllowed;

    @NotNull
    private LocalDate techEvalStartDate;

    @NotNull
    private LocalDate techEvalEndDate;

    @NotNull
    private LocalDate commEvalStartDate;

    @NotNull
    private LocalDate commEvalEndDate;

    @NotNull
    private LocalDate emdVerifiedDate;

    @NotNull
    private Boolean multipleCurrencySelectionAllowedYn;

    @NotNull
    private Boolean isEditable;

    @NotNull
    private Boolean isEvaluated;

    @NotNull
    private Boolean isTechWeightageAllowed;

    @NotNull
    private Boolean isTechWeightageCompleted;

    @NotNull
    private Boolean isCommercialBidEditCompleted;

    @NotNull
    private Boolean templateYn;

    @NotNull
    private Long templateId;

    @NotNull
    private Integer paymentVerifiedBy;

    @NotNull
    private LocalDate paymentVerifiedDate;

    @NotNull
    private Boolean isItemwiseCsrRequired;

    @NotNull
    private Boolean isBidViewEnabled;

    @NotNull
    private Boolean isNegotiationRequired;

    @NotNull
    private Boolean highestBidderSelection;

    @NotNull
    private Boolean isVariableEmdAllowed;

    @NotNull
    private Integer nitPublisherCertId;

    @NotNull
    private Boolean autoExtendYn;

    @NotNull
    private Integer noOfDaysExtend;

    @NotNull
    private Boolean isExtensionAvailable;

    @NotNull
    private Boolean specialSchemeTender;

    @NotNull
    private Boolean isBidValidityExpiryTaskCreated;

    @NotNull
    private Boolean evaluationTypeFlag;

    @NotNull
    private Boolean qcbsTenderYn;

    @NotNull
    private String publishedUser;

    @NotNull
    private Boolean isWorldBankFunded;

    @NotNull
    private String ecClearanceNumber;

    @NotNull
    private LocalDate ecClearanceDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEvaluationType() {
        return evaluationType;
    }

    public void setEvaluationType(Integer evaluationType) {
        this.evaluationType = evaluationType;
    }

    public LocalDate getPrequalValidityPeriod() {
        return prequalValidityPeriod;
    }

    public void setPrequalValidityPeriod(LocalDate prequalValidityPeriod) {
        this.prequalValidityPeriod = prequalValidityPeriod;
    }

    public Boolean getInvitingStrategy() {
        return invitingStrategy;
    }

    public void setInvitingStrategy(Boolean invitingStrategy) {
        this.invitingStrategy = invitingStrategy;
    }

    public Integer getMinNoBidsReceive() {
        return minNoBidsReceive;
    }

    public void setMinNoBidsReceive(Integer minNoBidsReceive) {
        this.minNoBidsReceive = minNoBidsReceive;
    }

    public Integer getProcuringEntity() {
        return procuringEntity;
    }

    public void setProcuringEntity(Integer procuringEntity) {
        this.procuringEntity = procuringEntity;
    }

    public Integer getProcuringEntityDesignation() {
        return procuringEntityDesignation;
    }

    public void setProcuringEntityDesignation(Integer procuringEntityDesignation) {
        this.procuringEntityDesignation = procuringEntityDesignation;
    }

    public Integer getProcuringEntityAddress() {
        return procuringEntityAddress;
    }

    public void setProcuringEntityAddress(Integer procuringEntityAddress) {
        this.procuringEntityAddress = procuringEntityAddress;
    }

    public Integer getInvitingAuthority() {
        return invitingAuthority;
    }

    public void setInvitingAuthority(Integer invitingAuthority) {
        this.invitingAuthority = invitingAuthority;
    }

    public Integer getInvitingAuthorityDesignation() {
        return invitingAuthorityDesignation;
    }

    public void setInvitingAuthorityDesignation(Integer invitingAuthorityDesignation) {
        this.invitingAuthorityDesignation = invitingAuthorityDesignation;
    }

    public Integer getInvitingAuthorityAddress() {
        return invitingAuthorityAddress;
    }

    public void setInvitingAuthorityAddress(Integer invitingAuthorityAddress) {
        this.invitingAuthorityAddress = invitingAuthorityAddress;
    }

    public Boolean getParticipatingCondnYn() {
        return participatingCondnYn;
    }

    public void setParticipatingCondnYn(Boolean participatingCondnYn) {
        this.participatingCondnYn = participatingCondnYn;
    }

    public BigDecimal getTenderFee() {
        return tenderFee;
    }

    public void setTenderFee(BigDecimal tenderFee) {
        this.tenderFee = tenderFee;
    }

    public BigDecimal getEmd() {
        return emd;
    }

    public void setEmd(BigDecimal emd) {
        this.emd = emd;
    }

    public Boolean getBidValueType() {
        return bidValueType;
    }

    public void setBidValueType(Boolean bidValueType) {
        this.bidValueType = bidValueType;
    }

    public Integer getTechWeightage() {
        return techWeightage;
    }

    public void setTechWeightage(Integer techWeightage) {
        this.techWeightage = techWeightage;
    }

    public LocalDate getPrequalTenderBidOpen() {
        return prequalTenderBidOpen;
    }

    public void setPrequalTenderBidOpen(LocalDate prequalTenderBidOpen) {
        this.prequalTenderBidOpen = prequalTenderBidOpen;
    }

    public LocalDate getTenderDocClose() {
        return tenderDocClose;
    }

    public void setTenderDocClose(LocalDate tenderDocClose) {
        this.tenderDocClose = tenderDocClose;
    }

    public LocalDate getTenderReceiptClose() {
        return tenderReceiptClose;
    }

    public void setTenderReceiptClose(LocalDate tenderReceiptClose) {
        this.tenderReceiptClose = tenderReceiptClose;
    }

    public LocalDate getTenderQueryClose() {
        return tenderQueryClose;
    }

    public void setTenderQueryClose(LocalDate tenderQueryClose) {
        this.tenderQueryClose = tenderQueryClose;
    }

    public LocalDate getTechnicalBidOpen() {
        return technicalBidOpen;
    }

    public void setTechnicalBidOpen(LocalDate technicalBidOpen) {
        this.technicalBidOpen = technicalBidOpen;
    }

    public LocalDate getFinancialBidOpen() {
        return financialBidOpen;
    }

    public void setFinancialBidOpen(LocalDate financialBidOpen) {
        this.financialBidOpen = financialBidOpen;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

    public Integer getPublishedBy() {
        return publishedBy;
    }

    public void setPublishedBy(Integer publishedBy) {
        this.publishedBy = publishedBy;
    }

    public LocalDate getRecalledDate() {
        return recalledDate;
    }

    public void setRecalledDate(LocalDate recalledDate) {
        this.recalledDate = recalledDate;
    }

    public Integer getRecalledBy() {
        return recalledBy;
    }

    public void setRecalledBy(Integer recalledBy) {
        this.recalledBy = recalledBy;
    }

    public LocalDate getBidSubmissionStartDate() {
        return bidSubmissionStartDate;
    }

    public void setBidSubmissionStartDate(LocalDate bidSubmissionStartDate) {
        this.bidSubmissionStartDate = bidSubmissionStartDate;
    }

    public Integer getBidValidityPeriod() {
        return bidValidityPeriod;
    }

    public void setBidValidityPeriod(Integer bidValidityPeriod) {
        this.bidValidityPeriod = bidValidityPeriod;
    }

    public Integer getNoOfCalls() {
        return noOfCalls;
    }

    public void setNoOfCalls(Integer noOfCalls) {
        this.noOfCalls = noOfCalls;
    }

    public LocalDate getPreBidMeetingDate() {
        return preBidMeetingDate;
    }

    public void setPreBidMeetingDate(LocalDate preBidMeetingDate) {
        this.preBidMeetingDate = preBidMeetingDate;
    }

    public Boolean getPreBidMeetingYn() {
        return preBidMeetingYn;
    }

    public void setPreBidMeetingYn(Boolean preBidMeetingYn) {
        this.preBidMeetingYn = preBidMeetingYn;
    }

    public Integer getPrebidMeetingAddress() {
        return prebidMeetingAddress;
    }

    public void setPrebidMeetingAddress(Integer prebidMeetingAddress) {
        this.prebidMeetingAddress = prebidMeetingAddress;
    }

    public LocalDate getPreQualificationBidOpen() {
        return preQualificationBidOpen;
    }

    public void setPreQualificationBidOpen(LocalDate preQualificationBidOpen) {
        this.preQualificationBidOpen = preQualificationBidOpen;
    }

    public Boolean getMandateAllItemsYn() {
        return mandateAllItemsYn;
    }

    public void setMandateAllItemsYn(Boolean mandateAllItemsYn) {
        this.mandateAllItemsYn = mandateAllItemsYn;
    }

    public Boolean getQueriesPublished() {
        return queriesPublished;
    }

    public void setQueriesPublished(Boolean queriesPublished) {
        this.queriesPublished = queriesPublished;
    }

    public String getDenominationType() {
        return denominationType;
    }

    public void setDenominationType(String denominationType) {
        this.denominationType = denominationType;
    }

    public Boolean getRetenderedYn() {
        return retenderedYn;
    }

    public void setRetenderedYn(Boolean retenderedYn) {
        this.retenderedYn = retenderedYn;
    }

    public String getPercentageRateType() {
        return percentageRateType;
    }

    public void setPercentageRateType(String percentageRateType) {
        this.percentageRateType = percentageRateType;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public Boolean getSplitEmdRequiredYn() {
        return splitEmdRequiredYn;
    }

    public void setSplitEmdRequiredYn(Boolean splitEmdRequiredYn) {
        this.splitEmdRequiredYn = splitEmdRequiredYn;
    }

    public BigDecimal getEmdBankGuarantee() {
        return emdBankGuarantee;
    }

    public void setEmdBankGuarantee(BigDecimal emdBankGuarantee) {
        this.emdBankGuarantee = emdBankGuarantee;
    }

    public BigDecimal getEmdCash() {
        return emdCash;
    }

    public void setEmdCash(BigDecimal emdCash) {
        this.emdCash = emdCash;
    }

    public Integer getBgValidityPeriod() {
        return bgValidityPeriod;
    }

    public void setBgValidityPeriod(Integer bgValidityPeriod) {
        this.bgValidityPeriod = bgValidityPeriod;
    }

    public Boolean getHideWeightage() {
        return hideWeightage;
    }

    public void setHideWeightage(Boolean hideWeightage) {
        this.hideWeightage = hideWeightage;
    }

    public Boolean getItemwiseTechEvalYn() {
        return itemwiseTechEvalYn;
    }

    public void setItemwiseTechEvalYn(Boolean itemwiseTechEvalYn) {
        this.itemwiseTechEvalYn = itemwiseTechEvalYn;
    }

    public Boolean getIsMultipleSupplierSelectionAllowed() {
        return isMultipleSupplierSelectionAllowed;
    }

    public void setIsMultipleSupplierSelectionAllowed(Boolean isMultipleSupplierSelectionAllowed) {
        this.isMultipleSupplierSelectionAllowed = isMultipleSupplierSelectionAllowed;
    }

    public LocalDate getTechEvalStartDate() {
        return techEvalStartDate;
    }

    public void setTechEvalStartDate(LocalDate techEvalStartDate) {
        this.techEvalStartDate = techEvalStartDate;
    }

    public LocalDate getTechEvalEndDate() {
        return techEvalEndDate;
    }

    public void setTechEvalEndDate(LocalDate techEvalEndDate) {
        this.techEvalEndDate = techEvalEndDate;
    }

    public LocalDate getCommEvalStartDate() {
        return commEvalStartDate;
    }

    public void setCommEvalStartDate(LocalDate commEvalStartDate) {
        this.commEvalStartDate = commEvalStartDate;
    }

    public LocalDate getCommEvalEndDate() {
        return commEvalEndDate;
    }

    public void setCommEvalEndDate(LocalDate commEvalEndDate) {
        this.commEvalEndDate = commEvalEndDate;
    }

    public LocalDate getEmdVerifiedDate() {
        return emdVerifiedDate;
    }

    public void setEmdVerifiedDate(LocalDate emdVerifiedDate) {
        this.emdVerifiedDate = emdVerifiedDate;
    }

    public Boolean getMultipleCurrencySelectionAllowedYn() {
        return multipleCurrencySelectionAllowedYn;
    }

    public void setMultipleCurrencySelectionAllowedYn(Boolean multipleCurrencySelectionAllowedYn) {
        this.multipleCurrencySelectionAllowedYn = multipleCurrencySelectionAllowedYn;
    }

    public Boolean getIsEditable() {
        return isEditable;
    }

    public void setIsEditable(Boolean isEditable) {
        this.isEditable = isEditable;
    }

    public Boolean getIsEvaluated() {
        return isEvaluated;
    }

    public void setIsEvaluated(Boolean isEvaluated) {
        this.isEvaluated = isEvaluated;
    }

    public Boolean getIsTechWeightageAllowed() {
        return isTechWeightageAllowed;
    }

    public void setIsTechWeightageAllowed(Boolean isTechWeightageAllowed) {
        this.isTechWeightageAllowed = isTechWeightageAllowed;
    }

    public Boolean getIsTechWeightageCompleted() {
        return isTechWeightageCompleted;
    }

    public void setIsTechWeightageCompleted(Boolean isTechWeightageCompleted) {
        this.isTechWeightageCompleted = isTechWeightageCompleted;
    }

    public Boolean getIsCommercialBidEditCompleted() {
        return isCommercialBidEditCompleted;
    }

    public void setIsCommercialBidEditCompleted(Boolean isCommercialBidEditCompleted) {
        this.isCommercialBidEditCompleted = isCommercialBidEditCompleted;
    }

    public Boolean getTemplateYn() {
        return templateYn;
    }

    public void setTemplateYn(Boolean templateYn) {
        this.templateYn = templateYn;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public Integer getPaymentVerifiedBy() {
        return paymentVerifiedBy;
    }

    public void setPaymentVerifiedBy(Integer paymentVerifiedBy) {
        this.paymentVerifiedBy = paymentVerifiedBy;
    }

    public LocalDate getPaymentVerifiedDate() {
        return paymentVerifiedDate;
    }

    public void setPaymentVerifiedDate(LocalDate paymentVerifiedDate) {
        this.paymentVerifiedDate = paymentVerifiedDate;
    }

    public Boolean getIsItemwiseCsrRequired() {
        return isItemwiseCsrRequired;
    }

    public void setIsItemwiseCsrRequired(Boolean isItemwiseCsrRequired) {
        this.isItemwiseCsrRequired = isItemwiseCsrRequired;
    }

    public Boolean getIsBidViewEnabled() {
        return isBidViewEnabled;
    }

    public void setIsBidViewEnabled(Boolean isBidViewEnabled) {
        this.isBidViewEnabled = isBidViewEnabled;
    }

    public Boolean getIsNegotiationRequired() {
        return isNegotiationRequired;
    }

    public void setIsNegotiationRequired(Boolean isNegotiationRequired) {
        this.isNegotiationRequired = isNegotiationRequired;
    }

    public Boolean getHighestBidderSelection() {
        return highestBidderSelection;
    }

    public void setHighestBidderSelection(Boolean highestBidderSelection) {
        this.highestBidderSelection = highestBidderSelection;
    }

    public Boolean getIsVariableEmdAllowed() {
        return isVariableEmdAllowed;
    }

    public void setIsVariableEmdAllowed(Boolean isVariableEmdAllowed) {
        this.isVariableEmdAllowed = isVariableEmdAllowed;
    }

    public Integer getNitPublisherCertId() {
        return nitPublisherCertId;
    }

    public void setNitPublisherCertId(Integer nitPublisherCertId) {
        this.nitPublisherCertId = nitPublisherCertId;
    }

    public Boolean getAutoExtendYn() {
        return autoExtendYn;
    }

    public void setAutoExtendYn(Boolean autoExtendYn) {
        this.autoExtendYn = autoExtendYn;
    }

    public Integer getNoOfDaysExtend() {
        return noOfDaysExtend;
    }

    public void setNoOfDaysExtend(Integer noOfDaysExtend) {
        this.noOfDaysExtend = noOfDaysExtend;
    }

    public Boolean getIsExtensionAvailable() {
        return isExtensionAvailable;
    }

    public void setIsExtensionAvailable(Boolean isExtensionAvailable) {
        this.isExtensionAvailable = isExtensionAvailable;
    }

    public Boolean getSpecialSchemeTender() {
        return specialSchemeTender;
    }

    public void setSpecialSchemeTender(Boolean specialSchemeTender) {
        this.specialSchemeTender = specialSchemeTender;
    }

    public Boolean getIsBidValidityExpiryTaskCreated() {
        return isBidValidityExpiryTaskCreated;
    }

    public void setIsBidValidityExpiryTaskCreated(Boolean isBidValidityExpiryTaskCreated) {
        this.isBidValidityExpiryTaskCreated = isBidValidityExpiryTaskCreated;
    }

    public Boolean getEvaluationTypeFlag() {
        return evaluationTypeFlag;
    }

    public void setEvaluationTypeFlag(Boolean evaluationTypeFlag) {
        this.evaluationTypeFlag = evaluationTypeFlag;
    }

    public Boolean getQcbsTenderYn() {
        return qcbsTenderYn;
    }

    public void setQcbsTenderYn(Boolean qcbsTenderYn) {
        this.qcbsTenderYn = qcbsTenderYn;
    }

    public String getPublishedUser() {
        return publishedUser;
    }

    public void setPublishedUser(String publishedUser) {
        this.publishedUser = publishedUser;
    }

    public Boolean getIsWorldBankFunded() {
        return isWorldBankFunded;
    }

    public void setIsWorldBankFunded(Boolean isWorldBankFunded) {
        this.isWorldBankFunded = isWorldBankFunded;
    }

    public String getEcClearanceNumber() {
        return ecClearanceNumber;
    }

    public void setEcClearanceNumber(String ecClearanceNumber) {
        this.ecClearanceNumber = ecClearanceNumber;
    }

    public LocalDate getEcClearanceDate() {
        return ecClearanceDate;
    }

    public void setEcClearanceDate(LocalDate ecClearanceDate) {
        this.ecClearanceDate = ecClearanceDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NoticeInvitingTenderDTO)) {
            return false;
        }

        NoticeInvitingTenderDTO noticeInvitingTenderDTO = (NoticeInvitingTenderDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, noticeInvitingTenderDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NoticeInvitingTenderDTO{" +
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
