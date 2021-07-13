package com.dxc.eproc.tender.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TenderQuery.
 */
@Entity
@Table(name = "tender_query")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TenderQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nit_id", nullable = false)
    private Long nitId;

    @NotNull
    @Column(name = "supplier_general_info_id", nullable = false)
    private Long supplierGeneralInfoId;

    @NotNull
    @Column(name = "tender_query_text", nullable = false)
    private String tenderQueryText;

    @NotNull
    @Column(name = "tender_query_reponse_id", nullable = false)
    private Long tenderQueryReponseId;

    @NotNull
    @Column(name = "queries_published", nullable = false)
    private Boolean queriesPublished;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TenderQuery id(Long id) {
        this.id = id;
        return this;
    }

    public Long getNitId() {
        return this.nitId;
    }

    public TenderQuery nitId(Long nitId) {
        this.nitId = nitId;
        return this;
    }

    public void setNitId(Long nitId) {
        this.nitId = nitId;
    }

    public Long getSupplierGeneralInfoId() {
        return this.supplierGeneralInfoId;
    }

    public TenderQuery supplierGeneralInfoId(Long supplierGeneralInfoId) {
        this.supplierGeneralInfoId = supplierGeneralInfoId;
        return this;
    }

    public void setSupplierGeneralInfoId(Long supplierGeneralInfoId) {
        this.supplierGeneralInfoId = supplierGeneralInfoId;
    }

    public String getTenderQueryText() {
        return this.tenderQueryText;
    }

    public TenderQuery tenderQueryText(String tenderQueryText) {
        this.tenderQueryText = tenderQueryText;
        return this;
    }

    public void setTenderQueryText(String tenderQueryText) {
        this.tenderQueryText = tenderQueryText;
    }

    public Long getTenderQueryReponseId() {
        return this.tenderQueryReponseId;
    }

    public TenderQuery tenderQueryReponseId(Long tenderQueryReponseId) {
        this.tenderQueryReponseId = tenderQueryReponseId;
        return this;
    }

    public void setTenderQueryReponseId(Long tenderQueryReponseId) {
        this.tenderQueryReponseId = tenderQueryReponseId;
    }

    public Boolean getQueriesPublished() {
        return this.queriesPublished;
    }

    public TenderQuery queriesPublished(Boolean queriesPublished) {
        this.queriesPublished = queriesPublished;
        return this;
    }

    public void setQueriesPublished(Boolean queriesPublished) {
        this.queriesPublished = queriesPublished;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TenderQuery)) {
            return false;
        }
        return id != null && id.equals(((TenderQuery) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TenderQuery{" +
            "id=" + getId() +
            ", nitId=" + getNitId() +
            ", supplierGeneralInfoId=" + getSupplierGeneralInfoId() +
            ", tenderQueryText='" + getTenderQueryText() + "'" +
            ", tenderQueryReponseId=" + getTenderQueryReponseId() +
            ", queriesPublished='" + getQueriesPublished() + "'" +
            "}";
    }
}
