package com.dxc.eproc.tender.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.dxc.eproc.tender.domain.TenderSchedule} entity.
 */
public class TenderScheduleDTO implements Serializable {

    private Long id;

    @NotNull
    private Long nitId;

    @NotNull
    @Size(max = 50)
    private String tenderNumber;

    @NotNull
    @Size(max = 512)
    private String title;

    @NotNull
    @Size(max = 50)
    private String description;

    @NotNull
    private Integer category;

    @NotNull
    private BigDecimal ecv;

    @NotNull
    private Long indentId;

    @NotNull
    private Long deptId;

    @NotNull
    @Size(max = 25)
    private String status;

    @NotNull
    private String remarks;

    @NotNull
    private Integer parentTenderRef;

    @NotNull
    private Integer noOfCalls;

    @NotNull
    private Integer processId;

    @NotNull
    private BigDecimal csrValue;

    @NotNull
    private Boolean ecvtenderyYn;

    @NotNull
    private Integer certificateId;

    @NotNull
    private Integer paymentsVerified;

    @NotNull
    private LocalDate dtsApprovalDate;

    @NotNull
    private String mandatoryClause;

    @NotNull
    private Integer location;

    @NotNull
    private Integer delegateTo;

    @NotNull
    private Integer isApprovedBySelf;

    @NotNull
    @Size(max = 255)
    private String catWorkCategoryName;

    @NotNull
    @Size(max = 25)
    private String negotiationStatus;

    @NotNull
    private Boolean manualTenderYn;

    @NotNull
    private Long districtId;

    @NotNull
    @Size(max = 50)
    private String draftPublishStatus;

    @NotNull
    private String csrRemarks;

    @NotNull
    private Integer procEntityType;

    @NotNull
    @Size(max = 175)
    private String dtsApprovedBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNitId() {
        return nitId;
    }

    public void setNitId(Long nitId) {
        this.nitId = nitId;
    }

    public String getTenderNumber() {
        return tenderNumber;
    }

    public void setTenderNumber(String tenderNumber) {
        this.tenderNumber = tenderNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public BigDecimal getEcv() {
        return ecv;
    }

    public void setEcv(BigDecimal ecv) {
        this.ecv = ecv;
    }

    public Long getIndentId() {
        return indentId;
    }

    public void setIndentId(Long indentId) {
        this.indentId = indentId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getParentTenderRef() {
        return parentTenderRef;
    }

    public void setParentTenderRef(Integer parentTenderRef) {
        this.parentTenderRef = parentTenderRef;
    }

    public Integer getNoOfCalls() {
        return noOfCalls;
    }

    public void setNoOfCalls(Integer noOfCalls) {
        this.noOfCalls = noOfCalls;
    }

    public Integer getProcessId() {
        return processId;
    }

    public void setProcessId(Integer processId) {
        this.processId = processId;
    }

    public BigDecimal getCsrValue() {
        return csrValue;
    }

    public void setCsrValue(BigDecimal csrValue) {
        this.csrValue = csrValue;
    }

    public Boolean getEcvtenderyYn() {
        return ecvtenderyYn;
    }

    public void setEcvtenderyYn(Boolean ecvtenderyYn) {
        this.ecvtenderyYn = ecvtenderyYn;
    }

    public Integer getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(Integer certificateId) {
        this.certificateId = certificateId;
    }

    public Integer getPaymentsVerified() {
        return paymentsVerified;
    }

    public void setPaymentsVerified(Integer paymentsVerified) {
        this.paymentsVerified = paymentsVerified;
    }

    public LocalDate getDtsApprovalDate() {
        return dtsApprovalDate;
    }

    public void setDtsApprovalDate(LocalDate dtsApprovalDate) {
        this.dtsApprovalDate = dtsApprovalDate;
    }

    public String getMandatoryClause() {
        return mandatoryClause;
    }

    public void setMandatoryClause(String mandatoryClause) {
        this.mandatoryClause = mandatoryClause;
    }

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }

    public Integer getDelegateTo() {
        return delegateTo;
    }

    public void setDelegateTo(Integer delegateTo) {
        this.delegateTo = delegateTo;
    }

    public Integer getIsApprovedBySelf() {
        return isApprovedBySelf;
    }

    public void setIsApprovedBySelf(Integer isApprovedBySelf) {
        this.isApprovedBySelf = isApprovedBySelf;
    }

    public String getCatWorkCategoryName() {
        return catWorkCategoryName;
    }

    public void setCatWorkCategoryName(String catWorkCategoryName) {
        this.catWorkCategoryName = catWorkCategoryName;
    }

    public String getNegotiationStatus() {
        return negotiationStatus;
    }

    public void setNegotiationStatus(String negotiationStatus) {
        this.negotiationStatus = negotiationStatus;
    }

    public Boolean getManualTenderYn() {
        return manualTenderYn;
    }

    public void setManualTenderYn(Boolean manualTenderYn) {
        this.manualTenderYn = manualTenderYn;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public String getDraftPublishStatus() {
        return draftPublishStatus;
    }

    public void setDraftPublishStatus(String draftPublishStatus) {
        this.draftPublishStatus = draftPublishStatus;
    }

    public String getCsrRemarks() {
        return csrRemarks;
    }

    public void setCsrRemarks(String csrRemarks) {
        this.csrRemarks = csrRemarks;
    }

    public Integer getProcEntityType() {
        return procEntityType;
    }

    public void setProcEntityType(Integer procEntityType) {
        this.procEntityType = procEntityType;
    }

    public String getDtsApprovedBy() {
        return dtsApprovedBy;
    }

    public void setDtsApprovedBy(String dtsApprovedBy) {
        this.dtsApprovedBy = dtsApprovedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TenderScheduleDTO)) {
            return false;
        }

        TenderScheduleDTO tenderScheduleDTO = (TenderScheduleDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, tenderScheduleDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TenderScheduleDTO{" +
            "id=" + getId() +
            ", nitId=" + getNitId() +
            ", tenderNumber='" + getTenderNumber() + "'" +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", category=" + getCategory() +
            ", ecv=" + getEcv() +
            ", indentId=" + getIndentId() +
            ", deptId=" + getDeptId() +
            ", status='" + getStatus() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", parentTenderRef=" + getParentTenderRef() +
            ", noOfCalls=" + getNoOfCalls() +
            ", processId=" + getProcessId() +
            ", csrValue=" + getCsrValue() +
            ", ecvtenderyYn='" + getEcvtenderyYn() + "'" +
            ", certificateId=" + getCertificateId() +
            ", paymentsVerified=" + getPaymentsVerified() +
            ", dtsApprovalDate='" + getDtsApprovalDate() + "'" +
            ", mandatoryClause='" + getMandatoryClause() + "'" +
            ", location=" + getLocation() +
            ", delegateTo=" + getDelegateTo() +
            ", isApprovedBySelf=" + getIsApprovedBySelf() +
            ", catWorkCategoryName='" + getCatWorkCategoryName() + "'" +
            ", negotiationStatus='" + getNegotiationStatus() + "'" +
            ", manualTenderYn='" + getManualTenderYn() + "'" +
            ", districtId=" + getDistrictId() +
            ", draftPublishStatus='" + getDraftPublishStatus() + "'" +
            ", csrRemarks='" + getCsrRemarks() + "'" +
            ", procEntityType=" + getProcEntityType() +
            ", dtsApprovedBy='" + getDtsApprovedBy() + "'" +
            "}";
    }
}
