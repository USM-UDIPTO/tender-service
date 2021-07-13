package com.dxc.eproc.tender.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.dxc.eproc.tender.domain.TenderGoodsItems} entity.
 */
public class TenderGoodsItemsDTO implements Serializable {

    private Long id;

    @NotNull
    private Long nitId;

    @NotNull
    private Long indentId;

    @NotNull
    private Long categoryItemId;

    @NotNull
    private Integer entryOrder;

    @NotNull
    private String itemCode;

    @NotNull
    private String itemName;

    @NotNull
    private BigDecimal netAmt;

    @NotNull
    private BigDecimal price;

    @NotNull
    private BigDecimal quantity;

    @NotNull
    private String specifications;

    @NotNull
    private Integer uomId;

    @NotNull
    private String uomName;

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

    public Long getIndentId() {
        return indentId;
    }

    public void setIndentId(Long indentId) {
        this.indentId = indentId;
    }

    public Long getCategoryItemId() {
        return categoryItemId;
    }

    public void setCategoryItemId(Long categoryItemId) {
        this.categoryItemId = categoryItemId;
    }

    public Integer getEntryOrder() {
        return entryOrder;
    }

    public void setEntryOrder(Integer entryOrder) {
        this.entryOrder = entryOrder;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getNetAmt() {
        return netAmt;
    }

    public void setNetAmt(BigDecimal netAmt) {
        this.netAmt = netAmt;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public Integer getUomId() {
        return uomId;
    }

    public void setUomId(Integer uomId) {
        this.uomId = uomId;
    }

    public String getUomName() {
        return uomName;
    }

    public void setUomName(String uomName) {
        this.uomName = uomName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TenderGoodsItemsDTO)) {
            return false;
        }

        TenderGoodsItemsDTO tenderGoodsItemsDTO = (TenderGoodsItemsDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, tenderGoodsItemsDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TenderGoodsItemsDTO{" +
            "id=" + getId() +
            ", nitId=" + getNitId() +
            ", indentId=" + getIndentId() +
            ", categoryItemId=" + getCategoryItemId() +
            ", entryOrder=" + getEntryOrder() +
            ", itemCode='" + getItemCode() + "'" +
            ", itemName='" + getItemName() + "'" +
            ", netAmt=" + getNetAmt() +
            ", price=" + getPrice() +
            ", quantity=" + getQuantity() +
            ", specifications='" + getSpecifications() + "'" +
            ", uomId=" + getUomId() +
            ", uomName='" + getUomName() + "'" +
            "}";
    }
}
