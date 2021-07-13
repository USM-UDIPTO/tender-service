package com.dxc.eproc.tender.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TenderScheduleGroup.
 */
@Entity
@Table(name = "tender_schedule_group")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TenderScheduleGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nit_id", nullable = false)
    private Long nitId;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "mandate_bid_yn", nullable = false)
    private Boolean mandateBidYn;

    @NotNull
    @Column(name = "mandate_group_yn", nullable = false)
    private Boolean mandateGroupYn;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TenderScheduleGroup id(Long id) {
        this.id = id;
        return this;
    }

    public Long getNitId() {
        return this.nitId;
    }

    public TenderScheduleGroup nitId(Long nitId) {
        this.nitId = nitId;
        return this;
    }

    public void setNitId(Long nitId) {
        this.nitId = nitId;
    }

    public String getName() {
        return this.name;
    }

    public TenderScheduleGroup name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getMandateBidYn() {
        return this.mandateBidYn;
    }

    public TenderScheduleGroup mandateBidYn(Boolean mandateBidYn) {
        this.mandateBidYn = mandateBidYn;
        return this;
    }

    public void setMandateBidYn(Boolean mandateBidYn) {
        this.mandateBidYn = mandateBidYn;
    }

    public Boolean getMandateGroupYn() {
        return this.mandateGroupYn;
    }

    public TenderScheduleGroup mandateGroupYn(Boolean mandateGroupYn) {
        this.mandateGroupYn = mandateGroupYn;
        return this;
    }

    public void setMandateGroupYn(Boolean mandateGroupYn) {
        this.mandateGroupYn = mandateGroupYn;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TenderScheduleGroup)) {
            return false;
        }
        return id != null && id.equals(((TenderScheduleGroup) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TenderScheduleGroup{" +
            "id=" + getId() +
            ", nitId=" + getNitId() +
            ", name='" + getName() + "'" +
            ", mandateBidYn='" + getMandateBidYn() + "'" +
            ", mandateGroupYn='" + getMandateGroupYn() + "'" +
            "}";
    }
}
