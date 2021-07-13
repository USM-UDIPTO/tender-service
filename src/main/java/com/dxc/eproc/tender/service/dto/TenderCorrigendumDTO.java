package com.dxc.eproc.tender.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.dxc.eproc.tender.domain.TenderCorrigendum} entity.
 */
public class TenderCorrigendumDTO implements Serializable {

    private Long id;

    @NotNull
    private Long nitId;

    @NotNull
    private String reason;

    @NotNull
    private Integer historyOrder;

    @NotNull
    private LocalDate tenderDocCloseDateOriginal;

    @NotNull
    private LocalDate tenderDocCloseDateRevised;

    @NotNull
    private LocalDate tenderReceiptCloseDateOriginal;

    @NotNull
    private LocalDate tenderReceiptCloseDateRevised;

    @NotNull
    private LocalDate tenderQueryCloseDateOriginal;

    @NotNull
    private LocalDate tenderQueryCloseDateRevised;

    @NotNull
    private LocalDate technicalBidOpenDateOriginal;

    @NotNull
    private LocalDate technicalBidOpenDateRevised;

    @NotNull
    private LocalDate financialBidOpenDateOriginal;

    @NotNull
    private LocalDate financialBidOpenDateRevised;

    @NotNull
    private LocalDate prequalBidOpenDateOriginal;

    @NotNull
    private LocalDate prequalBidOpenDateRevised;

    @NotNull
    private String status;

    @NotNull
    private LocalDate prequalTendeBidOpenOriginal;

    @NotNull
    private LocalDate prequalTenderBidOpenRevised;

    @NotNull
    private LocalDate preBidMeetingDateOriginal;

    @NotNull
    private LocalDate preBidMeetingDateRevised;

    @NotNull
    private LocalDate prebidMeetingAddressOriginal;

    @NotNull
    private LocalDate prebidMeetingAddressRevised;

    @NotNull
    private LocalDate prequalValidityPeriodOriginal;

    @NotNull
    private LocalDate prequalValidityPeriodRevised;

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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getHistoryOrder() {
        return historyOrder;
    }

    public void setHistoryOrder(Integer historyOrder) {
        this.historyOrder = historyOrder;
    }

    public LocalDate getTenderDocCloseDateOriginal() {
        return tenderDocCloseDateOriginal;
    }

    public void setTenderDocCloseDateOriginal(LocalDate tenderDocCloseDateOriginal) {
        this.tenderDocCloseDateOriginal = tenderDocCloseDateOriginal;
    }

    public LocalDate getTenderDocCloseDateRevised() {
        return tenderDocCloseDateRevised;
    }

    public void setTenderDocCloseDateRevised(LocalDate tenderDocCloseDateRevised) {
        this.tenderDocCloseDateRevised = tenderDocCloseDateRevised;
    }

    public LocalDate getTenderReceiptCloseDateOriginal() {
        return tenderReceiptCloseDateOriginal;
    }

    public void setTenderReceiptCloseDateOriginal(LocalDate tenderReceiptCloseDateOriginal) {
        this.tenderReceiptCloseDateOriginal = tenderReceiptCloseDateOriginal;
    }

    public LocalDate getTenderReceiptCloseDateRevised() {
        return tenderReceiptCloseDateRevised;
    }

    public void setTenderReceiptCloseDateRevised(LocalDate tenderReceiptCloseDateRevised) {
        this.tenderReceiptCloseDateRevised = tenderReceiptCloseDateRevised;
    }

    public LocalDate getTenderQueryCloseDateOriginal() {
        return tenderQueryCloseDateOriginal;
    }

    public void setTenderQueryCloseDateOriginal(LocalDate tenderQueryCloseDateOriginal) {
        this.tenderQueryCloseDateOriginal = tenderQueryCloseDateOriginal;
    }

    public LocalDate getTenderQueryCloseDateRevised() {
        return tenderQueryCloseDateRevised;
    }

    public void setTenderQueryCloseDateRevised(LocalDate tenderQueryCloseDateRevised) {
        this.tenderQueryCloseDateRevised = tenderQueryCloseDateRevised;
    }

    public LocalDate getTechnicalBidOpenDateOriginal() {
        return technicalBidOpenDateOriginal;
    }

    public void setTechnicalBidOpenDateOriginal(LocalDate technicalBidOpenDateOriginal) {
        this.technicalBidOpenDateOriginal = technicalBidOpenDateOriginal;
    }

    public LocalDate getTechnicalBidOpenDateRevised() {
        return technicalBidOpenDateRevised;
    }

    public void setTechnicalBidOpenDateRevised(LocalDate technicalBidOpenDateRevised) {
        this.technicalBidOpenDateRevised = technicalBidOpenDateRevised;
    }

    public LocalDate getFinancialBidOpenDateOriginal() {
        return financialBidOpenDateOriginal;
    }

    public void setFinancialBidOpenDateOriginal(LocalDate financialBidOpenDateOriginal) {
        this.financialBidOpenDateOriginal = financialBidOpenDateOriginal;
    }

    public LocalDate getFinancialBidOpenDateRevised() {
        return financialBidOpenDateRevised;
    }

    public void setFinancialBidOpenDateRevised(LocalDate financialBidOpenDateRevised) {
        this.financialBidOpenDateRevised = financialBidOpenDateRevised;
    }

    public LocalDate getPrequalBidOpenDateOriginal() {
        return prequalBidOpenDateOriginal;
    }

    public void setPrequalBidOpenDateOriginal(LocalDate prequalBidOpenDateOriginal) {
        this.prequalBidOpenDateOriginal = prequalBidOpenDateOriginal;
    }

    public LocalDate getPrequalBidOpenDateRevised() {
        return prequalBidOpenDateRevised;
    }

    public void setPrequalBidOpenDateRevised(LocalDate prequalBidOpenDateRevised) {
        this.prequalBidOpenDateRevised = prequalBidOpenDateRevised;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getPrequalTendeBidOpenOriginal() {
        return prequalTendeBidOpenOriginal;
    }

    public void setPrequalTendeBidOpenOriginal(LocalDate prequalTendeBidOpenOriginal) {
        this.prequalTendeBidOpenOriginal = prequalTendeBidOpenOriginal;
    }

    public LocalDate getPrequalTenderBidOpenRevised() {
        return prequalTenderBidOpenRevised;
    }

    public void setPrequalTenderBidOpenRevised(LocalDate prequalTenderBidOpenRevised) {
        this.prequalTenderBidOpenRevised = prequalTenderBidOpenRevised;
    }

    public LocalDate getPreBidMeetingDateOriginal() {
        return preBidMeetingDateOriginal;
    }

    public void setPreBidMeetingDateOriginal(LocalDate preBidMeetingDateOriginal) {
        this.preBidMeetingDateOriginal = preBidMeetingDateOriginal;
    }

    public LocalDate getPreBidMeetingDateRevised() {
        return preBidMeetingDateRevised;
    }

    public void setPreBidMeetingDateRevised(LocalDate preBidMeetingDateRevised) {
        this.preBidMeetingDateRevised = preBidMeetingDateRevised;
    }

    public LocalDate getPrebidMeetingAddressOriginal() {
        return prebidMeetingAddressOriginal;
    }

    public void setPrebidMeetingAddressOriginal(LocalDate prebidMeetingAddressOriginal) {
        this.prebidMeetingAddressOriginal = prebidMeetingAddressOriginal;
    }

    public LocalDate getPrebidMeetingAddressRevised() {
        return prebidMeetingAddressRevised;
    }

    public void setPrebidMeetingAddressRevised(LocalDate prebidMeetingAddressRevised) {
        this.prebidMeetingAddressRevised = prebidMeetingAddressRevised;
    }

    public LocalDate getPrequalValidityPeriodOriginal() {
        return prequalValidityPeriodOriginal;
    }

    public void setPrequalValidityPeriodOriginal(LocalDate prequalValidityPeriodOriginal) {
        this.prequalValidityPeriodOriginal = prequalValidityPeriodOriginal;
    }

    public LocalDate getPrequalValidityPeriodRevised() {
        return prequalValidityPeriodRevised;
    }

    public void setPrequalValidityPeriodRevised(LocalDate prequalValidityPeriodRevised) {
        this.prequalValidityPeriodRevised = prequalValidityPeriodRevised;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TenderCorrigendumDTO)) {
            return false;
        }

        TenderCorrigendumDTO tenderCorrigendumDTO = (TenderCorrigendumDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, tenderCorrigendumDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TenderCorrigendumDTO{" +
            "id=" + getId() +
            ", nitId=" + getNitId() +
            ", reason='" + getReason() + "'" +
            ", historyOrder=" + getHistoryOrder() +
            ", tenderDocCloseDateOriginal='" + getTenderDocCloseDateOriginal() + "'" +
            ", tenderDocCloseDateRevised='" + getTenderDocCloseDateRevised() + "'" +
            ", tenderReceiptCloseDateOriginal='" + getTenderReceiptCloseDateOriginal() + "'" +
            ", tenderReceiptCloseDateRevised='" + getTenderReceiptCloseDateRevised() + "'" +
            ", tenderQueryCloseDateOriginal='" + getTenderQueryCloseDateOriginal() + "'" +
            ", tenderQueryCloseDateRevised='" + getTenderQueryCloseDateRevised() + "'" +
            ", technicalBidOpenDateOriginal='" + getTechnicalBidOpenDateOriginal() + "'" +
            ", technicalBidOpenDateRevised='" + getTechnicalBidOpenDateRevised() + "'" +
            ", financialBidOpenDateOriginal='" + getFinancialBidOpenDateOriginal() + "'" +
            ", financialBidOpenDateRevised='" + getFinancialBidOpenDateRevised() + "'" +
            ", prequalBidOpenDateOriginal='" + getPrequalBidOpenDateOriginal() + "'" +
            ", prequalBidOpenDateRevised='" + getPrequalBidOpenDateRevised() + "'" +
            ", status='" + getStatus() + "'" +
            ", prequalTendeBidOpenOriginal='" + getPrequalTendeBidOpenOriginal() + "'" +
            ", prequalTenderBidOpenRevised='" + getPrequalTenderBidOpenRevised() + "'" +
            ", preBidMeetingDateOriginal='" + getPreBidMeetingDateOriginal() + "'" +
            ", preBidMeetingDateRevised='" + getPreBidMeetingDateRevised() + "'" +
            ", prebidMeetingAddressOriginal='" + getPrebidMeetingAddressOriginal() + "'" +
            ", prebidMeetingAddressRevised='" + getPrebidMeetingAddressRevised() + "'" +
            ", prequalValidityPeriodOriginal='" + getPrequalValidityPeriodOriginal() + "'" +
            ", prequalValidityPeriodRevised='" + getPrequalValidityPeriodRevised() + "'" +
            "}";
    }
}
