package com.dxc.eproc.tender.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.dxc.eproc.tender.domain.TenderScheduleGroup} entity.
 */
public class TenderScheduleGroupDTO implements Serializable {

    private Long id;

    @NotNull
    private Long nitId;

    @NotNull
    private String name;

    @NotNull
    private Boolean mandateBidYn;

    @NotNull
    private Boolean mandateGroupYn;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getMandateBidYn() {
        return mandateBidYn;
    }

    public void setMandateBidYn(Boolean mandateBidYn) {
        this.mandateBidYn = mandateBidYn;
    }

    public Boolean getMandateGroupYn() {
        return mandateGroupYn;
    }

    public void setMandateGroupYn(Boolean mandateGroupYn) {
        this.mandateGroupYn = mandateGroupYn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TenderScheduleGroupDTO)) {
            return false;
        }

        TenderScheduleGroupDTO tenderScheduleGroupDTO = (TenderScheduleGroupDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, tenderScheduleGroupDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TenderScheduleGroupDTO{" +
            "id=" + getId() +
            ", nitId=" + getNitId() +
            ", name='" + getName() + "'" +
            ", mandateBidYn='" + getMandateBidYn() + "'" +
            ", mandateGroupYn='" + getMandateGroupYn() + "'" +
            "}";
    }
}
