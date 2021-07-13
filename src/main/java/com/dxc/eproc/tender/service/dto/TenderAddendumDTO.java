package com.dxc.eproc.tender.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.dxc.eproc.tender.domain.TenderAddendum} entity.
 */
public class TenderAddendumDTO implements Serializable {

    private Long id;

    @NotNull
    private Long nitId;

    @NotNull
    private String reason;

    @NotNull
    private String description;

    @NotNull
    private String status;

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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TenderAddendumDTO)) {
            return false;
        }

        TenderAddendumDTO tenderAddendumDTO = (TenderAddendumDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, tenderAddendumDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TenderAddendumDTO{" +
            "id=" + getId() +
            ", nitId=" + getNitId() +
            ", reason='" + getReason() + "'" +
            ", description='" + getDescription() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
