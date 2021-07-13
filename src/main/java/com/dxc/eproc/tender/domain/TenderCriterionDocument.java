package com.dxc.eproc.tender.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TenderCriterionDocument.
 */
@Entity
@Table(name = "tender_criterion_document")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TenderCriterionDocument implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nit_id", nullable = false)
    private Long nitId;

    @NotNull
    @Column(name = "tender_criterion_id", nullable = false)
    private Long tenderCriterionId;

    @NotNull
    @Column(name = "criterion_id", nullable = false)
    private Long criterionId;

    @NotNull
    @Column(name = "document_name", nullable = false)
    private String documentName;

    @NotNull
    @Column(name = "optional", nullable = false)
    private Boolean optional;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TenderCriterionDocument id(Long id) {
        this.id = id;
        return this;
    }

    public Long getNitId() {
        return this.nitId;
    }

    public TenderCriterionDocument nitId(Long nitId) {
        this.nitId = nitId;
        return this;
    }

    public void setNitId(Long nitId) {
        this.nitId = nitId;
    }

    public Long getTenderCriterionId() {
        return this.tenderCriterionId;
    }

    public TenderCriterionDocument tenderCriterionId(Long tenderCriterionId) {
        this.tenderCriterionId = tenderCriterionId;
        return this;
    }

    public void setTenderCriterionId(Long tenderCriterionId) {
        this.tenderCriterionId = tenderCriterionId;
    }

    public Long getCriterionId() {
        return this.criterionId;
    }

    public TenderCriterionDocument criterionId(Long criterionId) {
        this.criterionId = criterionId;
        return this;
    }

    public void setCriterionId(Long criterionId) {
        this.criterionId = criterionId;
    }

    public String getDocumentName() {
        return this.documentName;
    }

    public TenderCriterionDocument documentName(String documentName) {
        this.documentName = documentName;
        return this;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public Boolean getOptional() {
        return this.optional;
    }

    public TenderCriterionDocument optional(Boolean optional) {
        this.optional = optional;
        return this;
    }

    public void setOptional(Boolean optional) {
        this.optional = optional;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TenderCriterionDocument)) {
            return false;
        }
        return id != null && id.equals(((TenderCriterionDocument) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TenderCriterionDocument{" +
            "id=" + getId() +
            ", nitId=" + getNitId() +
            ", tenderCriterionId=" + getTenderCriterionId() +
            ", criterionId=" + getCriterionId() +
            ", documentName='" + getDocumentName() + "'" +
            ", optional='" + getOptional() + "'" +
            "}";
    }
}
