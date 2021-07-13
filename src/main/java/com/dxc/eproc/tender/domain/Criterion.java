package com.dxc.eproc.tender.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Criterion.
 */
@Entity
@Table(name = "criterion")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Criterion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "type", nullable = false)
    private String type;

    @NotNull
    @Column(name = "criterion_type", nullable = false)
    private String criterionType;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @Column(name = "tender_category", nullable = false)
    private String tenderCategory;

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

    public Criterion id(Long id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return this.type;
    }

    public Criterion type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCriterionType() {
        return this.criterionType;
    }

    public Criterion criterionType(String criterionType) {
        this.criterionType = criterionType;
        return this;
    }

    public void setCriterionType(String criterionType) {
        this.criterionType = criterionType;
    }

    public String getName() {
        return this.name;
    }

    public Criterion name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public Criterion description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTenderCategory() {
        return this.tenderCategory;
    }

    public Criterion tenderCategory(String tenderCategory) {
        this.tenderCategory = tenderCategory;
        return this;
    }

    public void setTenderCategory(String tenderCategory) {
        this.tenderCategory = tenderCategory;
    }

    public Boolean getActiveYn() {
        return this.activeYn;
    }

    public Criterion activeYn(Boolean activeYn) {
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
        if (!(o instanceof Criterion)) {
            return false;
        }
        return id != null && id.equals(((Criterion) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Criterion{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", criterionType='" + getCriterionType() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", tenderCategory='" + getTenderCategory() + "'" +
            ", activeYn='" + getActiveYn() + "'" +
            "}";
    }
}
