package com.dxc.eproc.tender.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.dxc.eproc.tender.domain.ObjectStore} entity.
 */
public class ObjectStoreDTO implements Serializable {

    private Long id;

    @NotNull
    private Long nitId;

    @NotNull
    private String uuid;

    @NotNull
    private Long referenceId;

    @NotNull
    private Long referenceType;

    @NotNull
    private Boolean activeYn;

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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Long getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(Long referenceId) {
        this.referenceId = referenceId;
    }

    public Long getReferenceType() {
        return referenceType;
    }

    public void setReferenceType(Long referenceType) {
        this.referenceType = referenceType;
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
        if (!(o instanceof ObjectStoreDTO)) {
            return false;
        }

        ObjectStoreDTO objectStoreDTO = (ObjectStoreDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, objectStoreDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ObjectStoreDTO{" +
            "id=" + getId() +
            ", nitId=" + getNitId() +
            ", uuid='" + getUuid() + "'" +
            ", referenceId=" + getReferenceId() +
            ", referenceType=" + getReferenceType() +
            ", activeYn='" + getActiveYn() + "'" +
            "}";
    }
}
