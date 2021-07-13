package com.dxc.eproc.tender.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TenderScrutinyCommittee.
 */
@Entity
@Table(name = "tender_scrutiny_committee")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TenderScrutinyCommittee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nit_id", nullable = false)
    private Long nitId;

    @NotNull
    @Column(name = "staff_id", nullable = false)
    private Long staffId;

    @NotNull
    @Column(name = "tender_scrutiny_activity_id", nullable = false)
    private Long tenderScrutinyActivityId;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TenderScrutinyCommittee id(Long id) {
        this.id = id;
        return this;
    }

    public Long getNitId() {
        return this.nitId;
    }

    public TenderScrutinyCommittee nitId(Long nitId) {
        this.nitId = nitId;
        return this;
    }

    public void setNitId(Long nitId) {
        this.nitId = nitId;
    }

    public Long getStaffId() {
        return this.staffId;
    }

    public TenderScrutinyCommittee staffId(Long staffId) {
        this.staffId = staffId;
        return this;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Long getTenderScrutinyActivityId() {
        return this.tenderScrutinyActivityId;
    }

    public TenderScrutinyCommittee tenderScrutinyActivityId(Long tenderScrutinyActivityId) {
        this.tenderScrutinyActivityId = tenderScrutinyActivityId;
        return this;
    }

    public void setTenderScrutinyActivityId(Long tenderScrutinyActivityId) {
        this.tenderScrutinyActivityId = tenderScrutinyActivityId;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TenderScrutinyCommittee)) {
            return false;
        }
        return id != null && id.equals(((TenderScrutinyCommittee) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TenderScrutinyCommittee{" +
            "id=" + getId() +
            ", nitId=" + getNitId() +
            ", staffId=" + getStaffId() +
            ", tenderScrutinyActivityId=" + getTenderScrutinyActivityId() +
            "}";
    }
}
