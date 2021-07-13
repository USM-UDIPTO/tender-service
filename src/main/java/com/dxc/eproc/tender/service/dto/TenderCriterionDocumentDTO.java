package com.dxc.eproc.tender.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.dxc.eproc.tender.domain.TenderCriterionDocument} entity.
 */
public class TenderCriterionDocumentDTO implements Serializable {

    private Long id;

    @NotNull
    private Long nitId;

    @NotNull
    private Long tenderCriterionId;

    @NotNull
    private Long criterionId;

    @NotNull
    private String documentName;

    @NotNull
    private Boolean optional;

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

    public Long getTenderCriterionId() {
        return tenderCriterionId;
    }

    public void setTenderCriterionId(Long tenderCriterionId) {
        this.tenderCriterionId = tenderCriterionId;
    }

    public Long getCriterionId() {
        return criterionId;
    }

    public void setCriterionId(Long criterionId) {
        this.criterionId = criterionId;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public Boolean getOptional() {
        return optional;
    }

    public void setOptional(Boolean optional) {
        this.optional = optional;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TenderCriterionDocumentDTO)) {
            return false;
        }

        TenderCriterionDocumentDTO tenderCriterionDocumentDTO = (TenderCriterionDocumentDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, tenderCriterionDocumentDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TenderCriterionDocumentDTO{" +
            "id=" + getId() +
            ", nitId=" + getNitId() +
            ", tenderCriterionId=" + getTenderCriterionId() +
            ", criterionId=" + getCriterionId() +
            ", documentName='" + getDocumentName() + "'" +
            ", optional='" + getOptional() + "'" +
            "}";
    }
}
