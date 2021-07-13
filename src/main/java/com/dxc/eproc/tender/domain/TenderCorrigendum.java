package com.dxc.eproc.tender.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TenderCorrigendum.
 */
@Entity
@Table(name = "tender_corrigendum")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TenderCorrigendum implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nit_id", nullable = false)
    private Long nitId;

    @NotNull
    @Column(name = "reason", nullable = false)
    private String reason;

    @NotNull
    @Column(name = "history_order", nullable = false)
    private Integer historyOrder;

    @NotNull
    @Column(name = "tender_doc_close_date_original", nullable = false)
    private LocalDate tenderDocCloseDateOriginal;

    @NotNull
    @Column(name = "tender_doc_close_date_revised", nullable = false)
    private LocalDate tenderDocCloseDateRevised;

    @NotNull
    @Column(name = "tender_receipt_close_date_original", nullable = false)
    private LocalDate tenderReceiptCloseDateOriginal;

    @NotNull
    @Column(name = "tender_receipt_close_date_revised", nullable = false)
    private LocalDate tenderReceiptCloseDateRevised;

    @NotNull
    @Column(name = "tender_query_close_date_original", nullable = false)
    private LocalDate tenderQueryCloseDateOriginal;

    @NotNull
    @Column(name = "tender_query_close_date_revised", nullable = false)
    private LocalDate tenderQueryCloseDateRevised;

    @NotNull
    @Column(name = "technical_bid_open_date_original", nullable = false)
    private LocalDate technicalBidOpenDateOriginal;

    @NotNull
    @Column(name = "technical_bid_open_date_revised", nullable = false)
    private LocalDate technicalBidOpenDateRevised;

    @NotNull
    @Column(name = "financial_bid_open_date_original", nullable = false)
    private LocalDate financialBidOpenDateOriginal;

    @NotNull
    @Column(name = "financial_bid_open_date_revised", nullable = false)
    private LocalDate financialBidOpenDateRevised;

    @NotNull
    @Column(name = "prequal_bid_open_date_original", nullable = false)
    private LocalDate prequalBidOpenDateOriginal;

    @NotNull
    @Column(name = "prequal_bid_open_date_revised", nullable = false)
    private LocalDate prequalBidOpenDateRevised;

    @NotNull
    @Column(name = "status", nullable = false)
    private String status;

    @NotNull
    @Column(name = "prequal_tende_bid_open_original", nullable = false)
    private LocalDate prequalTendeBidOpenOriginal;

    @NotNull
    @Column(name = "prequal_tender_bid_open_revised", nullable = false)
    private LocalDate prequalTenderBidOpenRevised;

    @NotNull
    @Column(name = "pre_bid_meeting_date_original", nullable = false)
    private LocalDate preBidMeetingDateOriginal;

    @NotNull
    @Column(name = "pre_bid_meeting_date_revised", nullable = false)
    private LocalDate preBidMeetingDateRevised;

    @NotNull
    @Column(name = "prebid_meeting_address_original", nullable = false)
    private LocalDate prebidMeetingAddressOriginal;

    @NotNull
    @Column(name = "prebid_meeting_address_revised", nullable = false)
    private LocalDate prebidMeetingAddressRevised;

    @NotNull
    @Column(name = "prequal_validity_period_original", nullable = false)
    private LocalDate prequalValidityPeriodOriginal;

    @NotNull
    @Column(name = "prequal_validity_period_revised", nullable = false)
    private LocalDate prequalValidityPeriodRevised;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TenderCorrigendum id(Long id) {
        this.id = id;
        return this;
    }

    public Long getNitId() {
        return this.nitId;
    }

    public TenderCorrigendum nitId(Long nitId) {
        this.nitId = nitId;
        return this;
    }

    public void setNitId(Long nitId) {
        this.nitId = nitId;
    }

    public String getReason() {
        return this.reason;
    }

    public TenderCorrigendum reason(String reason) {
        this.reason = reason;
        return this;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getHistoryOrder() {
        return this.historyOrder;
    }

    public TenderCorrigendum historyOrder(Integer historyOrder) {
        this.historyOrder = historyOrder;
        return this;
    }

    public void setHistoryOrder(Integer historyOrder) {
        this.historyOrder = historyOrder;
    }

    public LocalDate getTenderDocCloseDateOriginal() {
        return this.tenderDocCloseDateOriginal;
    }

    public TenderCorrigendum tenderDocCloseDateOriginal(LocalDate tenderDocCloseDateOriginal) {
        this.tenderDocCloseDateOriginal = tenderDocCloseDateOriginal;
        return this;
    }

    public void setTenderDocCloseDateOriginal(LocalDate tenderDocCloseDateOriginal) {
        this.tenderDocCloseDateOriginal = tenderDocCloseDateOriginal;
    }

    public LocalDate getTenderDocCloseDateRevised() {
        return this.tenderDocCloseDateRevised;
    }

    public TenderCorrigendum tenderDocCloseDateRevised(LocalDate tenderDocCloseDateRevised) {
        this.tenderDocCloseDateRevised = tenderDocCloseDateRevised;
        return this;
    }

    public void setTenderDocCloseDateRevised(LocalDate tenderDocCloseDateRevised) {
        this.tenderDocCloseDateRevised = tenderDocCloseDateRevised;
    }

    public LocalDate getTenderReceiptCloseDateOriginal() {
        return this.tenderReceiptCloseDateOriginal;
    }

    public TenderCorrigendum tenderReceiptCloseDateOriginal(LocalDate tenderReceiptCloseDateOriginal) {
        this.tenderReceiptCloseDateOriginal = tenderReceiptCloseDateOriginal;
        return this;
    }

    public void setTenderReceiptCloseDateOriginal(LocalDate tenderReceiptCloseDateOriginal) {
        this.tenderReceiptCloseDateOriginal = tenderReceiptCloseDateOriginal;
    }

    public LocalDate getTenderReceiptCloseDateRevised() {
        return this.tenderReceiptCloseDateRevised;
    }

    public TenderCorrigendum tenderReceiptCloseDateRevised(LocalDate tenderReceiptCloseDateRevised) {
        this.tenderReceiptCloseDateRevised = tenderReceiptCloseDateRevised;
        return this;
    }

    public void setTenderReceiptCloseDateRevised(LocalDate tenderReceiptCloseDateRevised) {
        this.tenderReceiptCloseDateRevised = tenderReceiptCloseDateRevised;
    }

    public LocalDate getTenderQueryCloseDateOriginal() {
        return this.tenderQueryCloseDateOriginal;
    }

    public TenderCorrigendum tenderQueryCloseDateOriginal(LocalDate tenderQueryCloseDateOriginal) {
        this.tenderQueryCloseDateOriginal = tenderQueryCloseDateOriginal;
        return this;
    }

    public void setTenderQueryCloseDateOriginal(LocalDate tenderQueryCloseDateOriginal) {
        this.tenderQueryCloseDateOriginal = tenderQueryCloseDateOriginal;
    }

    public LocalDate getTenderQueryCloseDateRevised() {
        return this.tenderQueryCloseDateRevised;
    }

    public TenderCorrigendum tenderQueryCloseDateRevised(LocalDate tenderQueryCloseDateRevised) {
        this.tenderQueryCloseDateRevised = tenderQueryCloseDateRevised;
        return this;
    }

    public void setTenderQueryCloseDateRevised(LocalDate tenderQueryCloseDateRevised) {
        this.tenderQueryCloseDateRevised = tenderQueryCloseDateRevised;
    }

    public LocalDate getTechnicalBidOpenDateOriginal() {
        return this.technicalBidOpenDateOriginal;
    }

    public TenderCorrigendum technicalBidOpenDateOriginal(LocalDate technicalBidOpenDateOriginal) {
        this.technicalBidOpenDateOriginal = technicalBidOpenDateOriginal;
        return this;
    }

    public void setTechnicalBidOpenDateOriginal(LocalDate technicalBidOpenDateOriginal) {
        this.technicalBidOpenDateOriginal = technicalBidOpenDateOriginal;
    }

    public LocalDate getTechnicalBidOpenDateRevised() {
        return this.technicalBidOpenDateRevised;
    }

    public TenderCorrigendum technicalBidOpenDateRevised(LocalDate technicalBidOpenDateRevised) {
        this.technicalBidOpenDateRevised = technicalBidOpenDateRevised;
        return this;
    }

    public void setTechnicalBidOpenDateRevised(LocalDate technicalBidOpenDateRevised) {
        this.technicalBidOpenDateRevised = technicalBidOpenDateRevised;
    }

    public LocalDate getFinancialBidOpenDateOriginal() {
        return this.financialBidOpenDateOriginal;
    }

    public TenderCorrigendum financialBidOpenDateOriginal(LocalDate financialBidOpenDateOriginal) {
        this.financialBidOpenDateOriginal = financialBidOpenDateOriginal;
        return this;
    }

    public void setFinancialBidOpenDateOriginal(LocalDate financialBidOpenDateOriginal) {
        this.financialBidOpenDateOriginal = financialBidOpenDateOriginal;
    }

    public LocalDate getFinancialBidOpenDateRevised() {
        return this.financialBidOpenDateRevised;
    }

    public TenderCorrigendum financialBidOpenDateRevised(LocalDate financialBidOpenDateRevised) {
        this.financialBidOpenDateRevised = financialBidOpenDateRevised;
        return this;
    }

    public void setFinancialBidOpenDateRevised(LocalDate financialBidOpenDateRevised) {
        this.financialBidOpenDateRevised = financialBidOpenDateRevised;
    }

    public LocalDate getPrequalBidOpenDateOriginal() {
        return this.prequalBidOpenDateOriginal;
    }

    public TenderCorrigendum prequalBidOpenDateOriginal(LocalDate prequalBidOpenDateOriginal) {
        this.prequalBidOpenDateOriginal = prequalBidOpenDateOriginal;
        return this;
    }

    public void setPrequalBidOpenDateOriginal(LocalDate prequalBidOpenDateOriginal) {
        this.prequalBidOpenDateOriginal = prequalBidOpenDateOriginal;
    }

    public LocalDate getPrequalBidOpenDateRevised() {
        return this.prequalBidOpenDateRevised;
    }

    public TenderCorrigendum prequalBidOpenDateRevised(LocalDate prequalBidOpenDateRevised) {
        this.prequalBidOpenDateRevised = prequalBidOpenDateRevised;
        return this;
    }

    public void setPrequalBidOpenDateRevised(LocalDate prequalBidOpenDateRevised) {
        this.prequalBidOpenDateRevised = prequalBidOpenDateRevised;
    }

    public String getStatus() {
        return this.status;
    }

    public TenderCorrigendum status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getPrequalTendeBidOpenOriginal() {
        return this.prequalTendeBidOpenOriginal;
    }

    public TenderCorrigendum prequalTendeBidOpenOriginal(LocalDate prequalTendeBidOpenOriginal) {
        this.prequalTendeBidOpenOriginal = prequalTendeBidOpenOriginal;
        return this;
    }

    public void setPrequalTendeBidOpenOriginal(LocalDate prequalTendeBidOpenOriginal) {
        this.prequalTendeBidOpenOriginal = prequalTendeBidOpenOriginal;
    }

    public LocalDate getPrequalTenderBidOpenRevised() {
        return this.prequalTenderBidOpenRevised;
    }

    public TenderCorrigendum prequalTenderBidOpenRevised(LocalDate prequalTenderBidOpenRevised) {
        this.prequalTenderBidOpenRevised = prequalTenderBidOpenRevised;
        return this;
    }

    public void setPrequalTenderBidOpenRevised(LocalDate prequalTenderBidOpenRevised) {
        this.prequalTenderBidOpenRevised = prequalTenderBidOpenRevised;
    }

    public LocalDate getPreBidMeetingDateOriginal() {
        return this.preBidMeetingDateOriginal;
    }

    public TenderCorrigendum preBidMeetingDateOriginal(LocalDate preBidMeetingDateOriginal) {
        this.preBidMeetingDateOriginal = preBidMeetingDateOriginal;
        return this;
    }

    public void setPreBidMeetingDateOriginal(LocalDate preBidMeetingDateOriginal) {
        this.preBidMeetingDateOriginal = preBidMeetingDateOriginal;
    }

    public LocalDate getPreBidMeetingDateRevised() {
        return this.preBidMeetingDateRevised;
    }

    public TenderCorrigendum preBidMeetingDateRevised(LocalDate preBidMeetingDateRevised) {
        this.preBidMeetingDateRevised = preBidMeetingDateRevised;
        return this;
    }

    public void setPreBidMeetingDateRevised(LocalDate preBidMeetingDateRevised) {
        this.preBidMeetingDateRevised = preBidMeetingDateRevised;
    }

    public LocalDate getPrebidMeetingAddressOriginal() {
        return this.prebidMeetingAddressOriginal;
    }

    public TenderCorrigendum prebidMeetingAddressOriginal(LocalDate prebidMeetingAddressOriginal) {
        this.prebidMeetingAddressOriginal = prebidMeetingAddressOriginal;
        return this;
    }

    public void setPrebidMeetingAddressOriginal(LocalDate prebidMeetingAddressOriginal) {
        this.prebidMeetingAddressOriginal = prebidMeetingAddressOriginal;
    }

    public LocalDate getPrebidMeetingAddressRevised() {
        return this.prebidMeetingAddressRevised;
    }

    public TenderCorrigendum prebidMeetingAddressRevised(LocalDate prebidMeetingAddressRevised) {
        this.prebidMeetingAddressRevised = prebidMeetingAddressRevised;
        return this;
    }

    public void setPrebidMeetingAddressRevised(LocalDate prebidMeetingAddressRevised) {
        this.prebidMeetingAddressRevised = prebidMeetingAddressRevised;
    }

    public LocalDate getPrequalValidityPeriodOriginal() {
        return this.prequalValidityPeriodOriginal;
    }

    public TenderCorrigendum prequalValidityPeriodOriginal(LocalDate prequalValidityPeriodOriginal) {
        this.prequalValidityPeriodOriginal = prequalValidityPeriodOriginal;
        return this;
    }

    public void setPrequalValidityPeriodOriginal(LocalDate prequalValidityPeriodOriginal) {
        this.prequalValidityPeriodOriginal = prequalValidityPeriodOriginal;
    }

    public LocalDate getPrequalValidityPeriodRevised() {
        return this.prequalValidityPeriodRevised;
    }

    public TenderCorrigendum prequalValidityPeriodRevised(LocalDate prequalValidityPeriodRevised) {
        this.prequalValidityPeriodRevised = prequalValidityPeriodRevised;
        return this;
    }

    public void setPrequalValidityPeriodRevised(LocalDate prequalValidityPeriodRevised) {
        this.prequalValidityPeriodRevised = prequalValidityPeriodRevised;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TenderCorrigendum)) {
            return false;
        }
        return id != null && id.equals(((TenderCorrigendum) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TenderCorrigendum{" +
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
