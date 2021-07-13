package com.dxc.eproc.tender.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TenderScheduleTelephone.
 */
@Entity
@Table(name = "tender_schedule_telephone")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TenderScheduleTelephone implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nit_id", nullable = false)
    private Long nitId;

    @NotNull
    @Column(name = "staff_general_info_id", nullable = false)
    private Long staffGeneralInfoId;

    @NotNull
    @Column(name = "tender_query_id", nullable = false)
    private Long tenderQueryId;

    @NotNull
    @Column(name = "tender_query_response_text", nullable = false)
    private String tenderQueryResponseText;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TenderScheduleTelephone id(Long id) {
        this.id = id;
        return this;
    }

    public Long getNitId() {
        return this.nitId;
    }

    public TenderScheduleTelephone nitId(Long nitId) {
        this.nitId = nitId;
        return this;
    }

    public void setNitId(Long nitId) {
        this.nitId = nitId;
    }

    public Long getStaffGeneralInfoId() {
        return this.staffGeneralInfoId;
    }

    public TenderScheduleTelephone staffGeneralInfoId(Long staffGeneralInfoId) {
        this.staffGeneralInfoId = staffGeneralInfoId;
        return this;
    }

    public void setStaffGeneralInfoId(Long staffGeneralInfoId) {
        this.staffGeneralInfoId = staffGeneralInfoId;
    }

    public Long getTenderQueryId() {
        return this.tenderQueryId;
    }

    public TenderScheduleTelephone tenderQueryId(Long tenderQueryId) {
        this.tenderQueryId = tenderQueryId;
        return this;
    }

    public void setTenderQueryId(Long tenderQueryId) {
        this.tenderQueryId = tenderQueryId;
    }

    public String getTenderQueryResponseText() {
        return this.tenderQueryResponseText;
    }

    public TenderScheduleTelephone tenderQueryResponseText(String tenderQueryResponseText) {
        this.tenderQueryResponseText = tenderQueryResponseText;
        return this;
    }

    public void setTenderQueryResponseText(String tenderQueryResponseText) {
        this.tenderQueryResponseText = tenderQueryResponseText;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TenderScheduleTelephone)) {
            return false;
        }
        return id != null && id.equals(((TenderScheduleTelephone) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TenderScheduleTelephone{" +
            "id=" + getId() +
            ", nitId=" + getNitId() +
            ", staffGeneralInfoId=" + getStaffGeneralInfoId() +
            ", tenderQueryId=" + getTenderQueryId() +
            ", tenderQueryResponseText='" + getTenderQueryResponseText() + "'" +
            "}";
    }
}
