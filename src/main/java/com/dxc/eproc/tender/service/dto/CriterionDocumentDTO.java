package com.dxc.eproc.tender.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.dxc.eproc.tender.domain.CriterionDocument} entity.
 */
public class CriterionDocumentDTO implements Serializable {

    private Long id;

    @NotNull
    private String documentName;

    @NotNull
    private String tenderCategory;

    @NotNull
    private Boolean activeYn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getTenderCategory() {
        return tenderCategory;
    }

    public void setTenderCategory(String tenderCategory) {
        this.tenderCategory = tenderCategory;
    }

    public Boolean getActiveYn() {
        return activeYn;
    }

    public void setActiveYn(Boolean activeYn) {
        this.activeYn = activeYn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CriterionDocumentDTO)) {
            return false;
        }

        CriterionDocumentDTO criterionDocumentDTO = (CriterionDocumentDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, criterionDocumentDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CriterionDocumentDTO{" +
            "id=" + getId() +
            ", documentName='" + getDocumentName() + "'" +
            ", tenderCategory='" + getTenderCategory() + "'" +
            ", activeYn='" + getActiveYn() + "'" +
            "}";
    }
}
