package com.dxc.eproc.tender.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A CriterionDocument.
 */
@Entity
@Table(name = "criterion_document")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CriterionDocument implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "document_name", nullable = false)
    private String documentName;

    @NotNull
    @Column(name = "tender_category", nullable = false)
    private String tenderCategory;

    @NotNull
    @Column(name = "active_yn", nullable = false)
    private Boolean activeYn;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CriterionDocument id(Long id) {
        this.id = id;
        return this;
    }

    public String getDocumentName() {
        return this.documentName;
    }

    public CriterionDocument documentName(String documentName) {
        this.documentName = documentName;
        return this;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getTenderCategory() {
        return this.tenderCategory;
    }

    public CriterionDocument tenderCategory(String tenderCategory) {
        this.tenderCategory = tenderCategory;
        return this;
    }

    public void setTenderCategory(String tenderCategory) {
        this.tenderCategory = tenderCategory;
    }

    public Boolean getActiveYn() {
        return this.activeYn;
    }

    public CriterionDocument activeYn(Boolean activeYn) {
        this.activeYn = activeYn;
        return this;
    }

    public void setActiveYn(Boolean activeYn) {
        this.activeYn = activeYn;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CriterionDocument)) {
            return false;
        }
        return id != null && id.equals(((CriterionDocument) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CriterionDocument{" +
            "id=" + getId() +
            ", documentName='" + getDocumentName() + "'" +
            ", tenderCategory='" + getTenderCategory() + "'" +
            ", activeYn='" + getActiveYn() + "'" +
            "}";
    }
}
