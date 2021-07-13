package com.dxc.eproc.tender.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.dxc.eproc.tender.domain.TenderQuery} entity.
 */
public class TenderQueryDTO implements Serializable {

    private Long id;

    @NotNull
    private Long nitId;

    @NotNull
    private Long supplierGeneralInfoId;

    @NotNull
    private String tenderQueryText;

    @NotNull
    private Long tenderQueryReponseId;

    @NotNull
    private Boolean queriesPublished;

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

    public Long getSupplierGeneralInfoId() {
        return supplierGeneralInfoId;
    }

    public void setSupplierGeneralInfoId(Long supplierGeneralInfoId) {
        this.supplierGeneralInfoId = supplierGeneralInfoId;
    }

    public String getTenderQueryText() {
        return tenderQueryText;
    }

    public void setTenderQueryText(String tenderQueryText) {
        this.tenderQueryText = tenderQueryText;
    }

    public Long getTenderQueryReponseId() {
        return tenderQueryReponseId;
    }

    public void setTenderQueryReponseId(Long tenderQueryReponseId) {
        this.tenderQueryReponseId = tenderQueryReponseId;
    }

    public Boolean getQueriesPublished() {
        return queriesPublished;
    }

    public void setQueriesPublished(Boolean queriesPublished) {
        this.queriesPublished = queriesPublished;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TenderQueryDTO)) {
            return false;
        }

        TenderQueryDTO tenderQueryDTO = (TenderQueryDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, tenderQueryDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TenderQueryDTO{" +
            "id=" + getId() +
            ", nitId=" + getNitId() +
            ", supplierGeneralInfoId=" + getSupplierGeneralInfoId() +
            ", tenderQueryText='" + getTenderQueryText() + "'" +
            ", tenderQueryReponseId=" + getTenderQueryReponseId() +
            ", queriesPublished='" + getQueriesPublished() + "'" +
            "}";
    }
}
