package com.dxc.eproc.tender.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ObjectStore.
 */
@Entity
@Table(name = "object_store")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ObjectStore implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nit_id", nullable = false)
    private Long nitId;

    @NotNull
    @Column(name = "uuid", nullable = false)
    private String uuid;

    @NotNull
    @Column(name = "reference_id", nullable = false)
    private Long referenceId;

    @NotNull
    @Column(name = "reference_type", nullable = false)
    private Long referenceType;

    @NotNull
    @Column(name = "active_yn", nullable = false)
    private Boolean activeYn;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ObjectStore id(Long id) {
        this.id = id;
        return this;
    }

    public Long getNitId() {
        return this.nitId;
    }

    public ObjectStore nitId(Long nitId) {
        this.nitId = nitId;
        return this;
    }

    public void setNitId(Long nitId) {
        this.nitId = nitId;
    }

    public String getUuid() {
        return this.uuid;
    }

    public ObjectStore uuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Long getReferenceId() {
        return this.referenceId;
    }

    public ObjectStore referenceId(Long referenceId) {
        this.referenceId = referenceId;
        return this;
    }

    public void setReferenceId(Long referenceId) {
        this.referenceId = referenceId;
    }

    public Long getReferenceType() {
        return this.referenceType;
    }

    public ObjectStore referenceType(Long referenceType) {
        this.referenceType = referenceType;
        return this;
    }

    public void setReferenceType(Long referenceType) {
        this.referenceType = referenceType;
    }

    public Boolean getActiveYn() {
        return this.activeYn;
    }

    public ObjectStore activeYn(Boolean activeYn) {
        this.activeYn = activeYn;
        return this;
    }

    public void setActiveYn(Boolean activeYn) {
        this.activeYn = activeYn;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ObjectStore)) {
            return false;
        }
        return id != null && id.equals(((ObjectStore) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ObjectStore{" +
            "id=" + getId() +
            ", nitId=" + getNitId() +
            ", uuid='" + getUuid() + "'" +
            ", referenceId=" + getReferenceId() +
            ", referenceType=" + getReferenceType() +
            ", activeYn='" + getActiveYn() + "'" +
            "}";
    }
}
