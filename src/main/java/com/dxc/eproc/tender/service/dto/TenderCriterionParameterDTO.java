package com.dxc.eproc.tender.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.dxc.eproc.tender.domain.TenderCriterionParameter} entity.
 */
public class TenderCriterionParameterDTO implements Serializable {

    private Long id;

    @NotNull
    private Long nitId;

    @NotNull
    private Long indentItemId;

    @NotNull
    private Long tenderCriterionId;

    @NotNull
    @Size(max = 50)
    private String name;

    @NotNull
    @Size(max = 255)
    private String description;

    @NotNull
    private BigDecimal minValue;

    @NotNull
    private BigDecimal maxValue;

    @NotNull
    private String operator;

    @NotNull
    private String dataType;

    @NotNull
    private Boolean optionalYn;

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

    public Long getIndentItemId() {
        return indentItemId;
    }

    public void setIndentItemId(Long indentItemId) {
        this.indentItemId = indentItemId;
    }

    public Long getTenderCriterionId() {
        return tenderCriterionId;
    }

    public void setTenderCriterionId(Long tenderCriterionId) {
        this.tenderCriterionId = tenderCriterionId;
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

    public BigDecimal getMinValue() {
        return minValue;
    }

    public void setMinValue(BigDecimal minValue) {
        this.minValue = minValue;
    }

    public BigDecimal getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(BigDecimal maxValue) {
        this.maxValue = maxValue;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Boolean getOptionalYn() {
        return optionalYn;
    }

    public void setOptionalYn(Boolean optionalYn) {
        this.optionalYn = optionalYn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TenderCriterionParameterDTO)) {
            return false;
        }

        TenderCriterionParameterDTO tenderCriterionParameterDTO = (TenderCriterionParameterDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, tenderCriterionParameterDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TenderCriterionParameterDTO{" +
            "id=" + getId() +
            ", nitId=" + getNitId() +
            ", indentItemId=" + getIndentItemId() +
            ", tenderCriterionId=" + getTenderCriterionId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", minValue=" + getMinValue() +
            ", maxValue=" + getMaxValue() +
            ", operator='" + getOperator() + "'" +
            ", dataType='" + getDataType() + "'" +
            ", optionalYn='" + getOptionalYn() + "'" +
            "}";
    }
}
