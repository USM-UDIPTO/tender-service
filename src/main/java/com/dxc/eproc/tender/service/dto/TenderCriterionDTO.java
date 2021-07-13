package com.dxc.eproc.tender.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.dxc.eproc.tender.domain.TenderCriterion} entity.
 */
public class TenderCriterionDTO implements Serializable {

    private Long id;

    @NotNull
    private Long nitId;

    @NotNull
    private String criterionType;

    @NotNull
    private String criterionCategory;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private Integer weight;

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

    public String getCriterionType() {
        return criterionType;
    }

    public void setCriterionType(String criterionType) {
        this.criterionType = criterionType;
    }

    public String getCriterionCategory() {
        return criterionCategory;
    }

    public void setCriterionCategory(String criterionCategory) {
        this.criterionCategory = criterionCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TenderCriterionDTO)) {
            return false;
        }

        TenderCriterionDTO tenderCriterionDTO = (TenderCriterionDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, tenderCriterionDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TenderCriterionDTO{" +
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
