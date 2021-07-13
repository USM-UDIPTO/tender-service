package com.dxc.eproc.tender.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TenderCriterionParameter.
 */
@Entity
@Table(name = "tender_criterion_parameter")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TenderCriterionParameter implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nit_id", nullable = false)
    private Long nitId;

    @NotNull
    @Column(name = "indent_item_id", nullable = false)
    private Long indentItemId;

    @NotNull
    @Column(name = "tender_criterion_id", nullable = false)
    private Long tenderCriterionId;

    @NotNull
    @Size(max = 50)
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @NotNull
    @Size(max = 255)
    @Column(name = "description", length = 255, nullable = false)
    private String description;

    @NotNull
    @Column(name = "min_value", precision = 21, scale = 2, nullable = false)
    private BigDecimal minValue;

    @NotNull
    @Column(name = "max_value", precision = 21, scale = 2, nullable = false)
    private BigDecimal maxValue;

    @NotNull
    @Column(name = "operator", nullable = false)
    private String operator;

    @NotNull
    @Column(name = "data_type", nullable = false)
    private String dataType;

    @NotNull
    @Column(name = "optional_yn", nullable = false)
    private Boolean optionalYn;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TenderCriterionParameter id(Long id) {
        this.id = id;
        return this;
    }

    public Long getNitId() {
        return this.nitId;
    }

    public TenderCriterionParameter nitId(Long nitId) {
        this.nitId = nitId;
        return this;
    }

    public void setNitId(Long nitId) {
        this.nitId = nitId;
    }

    public Long getIndentItemId() {
        return this.indentItemId;
    }

    public TenderCriterionParameter indentItemId(Long indentItemId) {
        this.indentItemId = indentItemId;
        return this;
    }

    public void setIndentItemId(Long indentItemId) {
        this.indentItemId = indentItemId;
    }

    public Long getTenderCriterionId() {
        return this.tenderCriterionId;
    }

    public TenderCriterionParameter tenderCriterionId(Long tenderCriterionId) {
        this.tenderCriterionId = tenderCriterionId;
        return this;
    }

    public void setTenderCriterionId(Long tenderCriterionId) {
        this.tenderCriterionId = tenderCriterionId;
    }

    public String getName() {
        return this.name;
    }

    public TenderCriterionParameter name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public TenderCriterionParameter description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getMinValue() {
        return this.minValue;
    }

    public TenderCriterionParameter minValue(BigDecimal minValue) {
        this.minValue = minValue;
        return this;
    }

    public void setMinValue(BigDecimal minValue) {
        this.minValue = minValue;
    }

    public BigDecimal getMaxValue() {
        return this.maxValue;
    }

    public TenderCriterionParameter maxValue(BigDecimal maxValue) {
        this.maxValue = maxValue;
        return this;
    }

    public void setMaxValue(BigDecimal maxValue) {
        this.maxValue = maxValue;
    }

    public String getOperator() {
        return this.operator;
    }

    public TenderCriterionParameter operator(String operator) {
        this.operator = operator;
        return this;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getDataType() {
        return this.dataType;
    }

    public TenderCriterionParameter dataType(String dataType) {
        this.dataType = dataType;
        return this;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Boolean getOptionalYn() {
        return this.optionalYn;
    }

    public TenderCriterionParameter optionalYn(Boolean optionalYn) {
        this.optionalYn = optionalYn;
        return this;
    }

    public void setOptionalYn(Boolean optionalYn) {
        this.optionalYn = optionalYn;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TenderCriterionParameter)) {
            return false;
        }
        return id != null && id.equals(((TenderCriterionParameter) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TenderCriterionParameter{" +
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
