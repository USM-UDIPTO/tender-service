package com.dxc.eproc.tender.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TenderCriterion.
 */
@Entity
@Table(name = "tender_criterion")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TenderCriterion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nit_id", nullable = false)
    private Long nitId;

    @NotNull
    @Column(name = "criterion_type", nullable = false)
    private String criterionType;

    @NotNull
    @Column(name = "criterion_category", nullable = false)
    private String criterionCategory;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @Column(name = "weight", nullable = false)
    private Integer weight;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TenderCriterion id(Long id) {
        this.id = id;
        return this;
    }

    public Long getNitId() {
        return this.nitId;
    }

    public TenderCriterion nitId(Long nitId) {
        this.nitId = nitId;
        return this;
    }

    public void setNitId(Long nitId) {
        this.nitId = nitId;
    }

    public String getCriterionType() {
        return this.criterionType;
    }

    public TenderCriterion criterionType(String criterionType) {
        this.criterionType = criterionType;
        return this;
    }

    public void setCriterionType(String criterionType) {
        this.criterionType = criterionType;
    }

    public String getCriterionCategory() {
        return this.criterionCategory;
    }

    public TenderCriterion criterionCategory(String criterionCategory) {
        this.criterionCategory = criterionCategory;
        return this;
    }

    public void setCriterionCategory(String criterionCategory) {
        this.criterionCategory = criterionCategory;
    }

    public String getName() {
        return this.name;
    }

    public TenderCriterion name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public TenderCriterion description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getWeight() {
        return this.weight;
    }

    public TenderCriterion weight(Integer weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TenderCriterion)) {
            return false;
        }
        return id != null && id.equals(((TenderCriterion) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TenderCriterion{" +
            "id=" + getId() +
            ", nitId=" + getNitId() +
            ", criterionType='" + getCriterionType() + "'" +
            ", criterionCategory='" + getCriterionCategory() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", weight=" + getWeight() +
            "}";
    }
}
