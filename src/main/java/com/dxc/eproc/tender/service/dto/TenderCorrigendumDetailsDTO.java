package com.dxc.eproc.tender.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.dxc.eproc.tender.domain.TenderCorrigendumDetails} entity.
 */
public class TenderCorrigendumDetailsDTO implements Serializable {

    private Long id;

    @NotNull
    private Long tenderCorrigendumId;

    @NotNull
    private String referenceNumber;

    @NotNull
    private String readAs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTenderCorrigendumId() {
        return tenderCorrigendumId;
    }

    public void setTenderCorrigendumId(Long tenderCorrigendumId) {
        this.tenderCorrigendumId = tenderCorrigendumId;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getReadAs() {
        return readAs;
    }

    public void setReadAs(String readAs) {
        this.readAs = readAs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TenderCorrigendumDetailsDTO)) {
            return false;
        }

        TenderCorrigendumDetailsDTO tenderCorrigendumDetailsDTO = (TenderCorrigendumDetailsDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, tenderCorrigendumDetailsDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TenderCorrigendumDetailsDTO{" +
            "id=" + getId() +
            ", tenderCorrigendumId=" + getTenderCorrigendumId() +
            ", referenceNumber='" + getReferenceNumber() + "'" +
            ", readAs='" + getReadAs() + "'" +
            "}";
    }
}
