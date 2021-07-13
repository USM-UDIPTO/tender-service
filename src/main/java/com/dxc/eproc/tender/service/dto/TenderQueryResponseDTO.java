package com.dxc.eproc.tender.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.dxc.eproc.tender.domain.TenderQueryResponse} entity.
 */
public class TenderQueryResponseDTO implements Serializable {

    private Long id;

    @NotNull
    private Long tenderQueryId;

    @NotNull
    private Long staffGeneralInfoId;

    @NotNull
    private String tenderQueryResponseText;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTenderQueryId() {
        return tenderQueryId;
    }

    public void setTenderQueryId(Long tenderQueryId) {
        this.tenderQueryId = tenderQueryId;
    }

    public Long getStaffGeneralInfoId() {
        return staffGeneralInfoId;
    }

    public void setStaffGeneralInfoId(Long staffGeneralInfoId) {
        this.staffGeneralInfoId = staffGeneralInfoId;
    }

    public String getTenderQueryResponseText() {
        return tenderQueryResponseText;
    }

    public void setTenderQueryResponseText(String tenderQueryResponseText) {
        this.tenderQueryResponseText = tenderQueryResponseText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TenderQueryResponseDTO)) {
            return false;
        }

        TenderQueryResponseDTO tenderQueryResponseDTO = (TenderQueryResponseDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, tenderQueryResponseDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TenderQueryResponseDTO{" +
            "id=" + getId() +
            ", tenderQueryId=" + getTenderQueryId() +
            ", staffGeneralInfoId=" + getStaffGeneralInfoId() +
            ", tenderQueryResponseText='" + getTenderQueryResponseText() + "'" +
            "}";
    }
}
