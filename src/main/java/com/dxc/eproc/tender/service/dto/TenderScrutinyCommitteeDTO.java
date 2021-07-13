package com.dxc.eproc.tender.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.dxc.eproc.tender.domain.TenderScrutinyCommittee} entity.
 */
public class TenderScrutinyCommitteeDTO implements Serializable {

    private Long id;

    @NotNull
    private Long nitId;

    @NotNull
    private Long staffId;

    @NotNull
    private Long tenderScrutinyActivityId;

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

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Long getTenderScrutinyActivityId() {
        return tenderScrutinyActivityId;
    }

    public void setTenderScrutinyActivityId(Long tenderScrutinyActivityId) {
        this.tenderScrutinyActivityId = tenderScrutinyActivityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TenderScrutinyCommitteeDTO)) {
            return false;
        }

        TenderScrutinyCommitteeDTO tenderScrutinyCommitteeDTO = (TenderScrutinyCommitteeDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, tenderScrutinyCommitteeDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TenderScrutinyCommitteeDTO{" +
            "id=" + getId() +
            ", nitId=" + getNitId() +
            ", staffId=" + getStaffId() +
            ", tenderScrutinyActivityId=" + getTenderScrutinyActivityId() +
            "}";
    }
}
