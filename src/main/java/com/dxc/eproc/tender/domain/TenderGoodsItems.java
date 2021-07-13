package com.dxc.eproc.tender.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TenderGoodsItems.
 */
@Entity
@Table(name = "tender_goods_items")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TenderGoodsItems implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nit_id", nullable = false)
    private Long nitId;

    @NotNull
    @Column(name = "indent_id", nullable = false)
    private Long indentId;

    @NotNull
    @Column(name = "category_item_id", nullable = false)
    private Long categoryItemId;

    @NotNull
    @Column(name = "entry_order", nullable = false)
    private Integer entryOrder;

    @NotNull
    @Column(name = "item_code", nullable = false)
    private String itemCode;

    @NotNull
    @Column(name = "item_name", nullable = false)
    private String itemName;

    @NotNull
    @Column(name = "net_amt", precision = 21, scale = 2, nullable = false)
    private BigDecimal netAmt;

    @NotNull
    @Column(name = "price", precision = 21, scale = 2, nullable = false)
    private BigDecimal price;

    @NotNull
    @Column(name = "quantity", precision = 21, scale = 2, nullable = false)
    private BigDecimal quantity;

    @NotNull
    @Column(name = "specifications", nullable = false)
    private String specifications;

    @NotNull
    @Column(name = "uom_id", nullable = false)
    private Integer uomId;

    @NotNull
    @Column(name = "uom_name", nullable = false)
    private String uomName;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TenderGoodsItems id(Long id) {
        this.id = id;
        return this;
    }

    public Long getNitId() {
        return this.nitId;
    }

    public TenderGoodsItems nitId(Long nitId) {
        this.nitId = nitId;
        return this;
    }

    public void setNitId(Long nitId) {
        this.nitId = nitId;
    }

    public Long getIndentId() {
        return this.indentId;
    }

    public TenderGoodsItems indentId(Long indentId) {
        this.indentId = indentId;
        return this;
    }

    public void setIndentId(Long indentId) {
        this.indentId = indentId;
    }

    public Long getCategoryItemId() {
        return this.categoryItemId;
    }

    public TenderGoodsItems categoryItemId(Long categoryItemId) {
        this.categoryItemId = categoryItemId;
        return this;
    }

    public void setCategoryItemId(Long categoryItemId) {
        this.categoryItemId = categoryItemId;
    }

    public Integer getEntryOrder() {
        return this.entryOrder;
    }

    public TenderGoodsItems entryOrder(Integer entryOrder) {
        this.entryOrder = entryOrder;
        return this;
    }

    public void setEntryOrder(Integer entryOrder) {
        this.entryOrder = entryOrder;
    }

    public String getItemCode() {
        return this.itemCode;
    }

    public TenderGoodsItems itemCode(String itemCode) {
        this.itemCode = itemCode;
        return this;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return this.itemName;
    }

    public TenderGoodsItems itemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getNetAmt() {
        return this.netAmt;
    }

    public TenderGoodsItems netAmt(BigDecimal netAmt) {
        this.netAmt = netAmt;
        return this;
    }

    public void setNetAmt(BigDecimal netAmt) {
        this.netAmt = netAmt;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public TenderGoodsItems price(BigDecimal price) {
        this.price = price;
        return this;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getQuantity() {
        return this.quantity;
    }

    public TenderGoodsItems quantity(BigDecimal quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getSpecifications() {
        return this.specifications;
    }

    public TenderGoodsItems specifications(String specifications) {
        this.specifications = specifications;
        return this;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public Integer getUomId() {
        return this.uomId;
    }

    public TenderGoodsItems uomId(Integer uomId) {
        this.uomId = uomId;
        return this;
    }

    public void setUomId(Integer uomId) {
        this.uomId = uomId;
    }

    public String getUomName() {
        return this.uomName;
    }

    public TenderGoodsItems uomName(String uomName) {
        this.uomName = uomName;
        return this;
    }

    public void setUomName(String uomName) {
        this.uomName = uomName;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TenderGoodsItems)) {
            return false;
        }
        return id != null && id.equals(((TenderGoodsItems) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TenderGoodsItems{" +
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
