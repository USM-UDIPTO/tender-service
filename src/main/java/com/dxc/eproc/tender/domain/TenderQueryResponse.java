package com.dxc.eproc.tender.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TenderQueryResponse.
 */
@Entity
@Table(name = "tender_query_response")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TenderQueryResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "tender_query_id", nullable = false)
    private Long tenderQueryId;

    @NotNull
    @Column(name = "staff_general_info_id", nullable = false)
    private Long staffGeneralInfoId;

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

    public TenderQueryResponse id(Long id) {
        this.id = id;
        return this;
    }

    public Long getTenderQueryId() {
        return this.tenderQueryId;
    }

    public TenderQueryResponse tenderQueryId(Long tenderQueryId) {
        this.tenderQueryId = tenderQueryId;
        return this;
    }

    public void setTenderQueryId(Long tenderQueryId) {
        this.tenderQueryId = tenderQueryId;
    }

    public Long getStaffGeneralInfoId() {
        return this.staffGeneralInfoId;
    }

    public TenderQueryResponse staffGeneralInfoId(Long staffGeneralInfoId) {
        this.staffGeneralInfoId = staffGeneralInfoId;
        return this;
    }

    public void setStaffGeneralInfoId(Long staffGeneralInfoId) {
        this.staffGeneralInfoId = staffGeneralInfoId;
    }

    public String getTenderQueryResponseText() {
        return this.tenderQueryResponseText;
    }

    public TenderQueryResponse tenderQueryResponseText(String tenderQueryResponseText) {
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
        if (!(o instanceof TenderQueryResponse)) {
            return false;
        }
        return id != null && id.equals(((TenderQueryResponse) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TenderQueryResponse{" +
            "id=" + getId() +
            ", tenderQueryId=" + getTenderQueryId() +
            ", staffGeneralInfoId=" + getStaffGeneralInfoId() +
            ", tenderQueryResponseText='" + getTenderQueryResponseText() + "'" +
            "}";
    }
}
