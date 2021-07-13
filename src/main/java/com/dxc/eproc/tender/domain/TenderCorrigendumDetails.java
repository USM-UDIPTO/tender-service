package com.dxc.eproc.tender.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TenderCorrigendumDetails.
 */
@Entity
@Table(name = "tender_corrigendum_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TenderCorrigendumDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "tender_corrigendum_id", nullable = false)
    private Long tenderCorrigendumId;

    @NotNull
    @Column(name = "reference_number", nullable = false)
    private String referenceNumber;

    @NotNull
    @Column(name = "read_as", nullable = false)
    private String readAs;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TenderCorrigendumDetails id(Long id) {
        this.id = id;
        return this;
    }

    public Long getTenderCorrigendumId() {
        return this.tenderCorrigendumId;
    }

    public TenderCorrigendumDetails tenderCorrigendumId(Long tenderCorrigendumId) {
        this.tenderCorrigendumId = tenderCorrigendumId;
        return this;
    }

    public void setTenderCorrigendumId(Long tenderCorrigendumId) {
        this.tenderCorrigendumId = tenderCorrigendumId;
    }

    public String getReferenceNumber() {
        return this.referenceNumber;
    }

    public TenderCorrigendumDetails referenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
        return this;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getReadAs() {
        return this.readAs;
    }

    public TenderCorrigendumDetails readAs(String readAs) {
        this.readAs = readAs;
        return this;
    }

    public void setReadAs(String readAs) {
        this.readAs = readAs;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TenderCorrigendumDetails)) {
            return false;
        }
        return id != null && id.equals(((TenderCorrigendumDetails) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TenderCorrigendumDetails{" +
            "id=" + getId() +
            ", tenderCorrigendumId=" + getTenderCorrigendumId() +
            ", referenceNumber='" + getReferenceNumber() + "'" +
            ", readAs='" + getReadAs() + "'" +
            "}";
    }
}
