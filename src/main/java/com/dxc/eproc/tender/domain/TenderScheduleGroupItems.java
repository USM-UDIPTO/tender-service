package com.dxc.eproc.tender.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TenderScheduleGroupItems.
 */
@Entity
@Table(name = "tender_schedule_group_items")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TenderScheduleGroupItems implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "tender_goods_indent_item_id", nullable = false)
    private Long tenderGoodsIndentItemId;

    @NotNull
    @Column(name = "tender_schedule_group_id", nullable = false)
    private Long tenderScheduleGroupId;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TenderScheduleGroupItems id(Long id) {
        this.id = id;
        return this;
    }

    public Long getTenderGoodsIndentItemId() {
        return this.tenderGoodsIndentItemId;
    }

    public TenderScheduleGroupItems tenderGoodsIndentItemId(Long tenderGoodsIndentItemId) {
        this.tenderGoodsIndentItemId = tenderGoodsIndentItemId;
        return this;
    }

    public void setTenderGoodsIndentItemId(Long tenderGoodsIndentItemId) {
        this.tenderGoodsIndentItemId = tenderGoodsIndentItemId;
    }

    public Long getTenderScheduleGroupId() {
        return this.tenderScheduleGroupId;
    }

    public TenderScheduleGroupItems tenderScheduleGroupId(Long tenderScheduleGroupId) {
        this.tenderScheduleGroupId = tenderScheduleGroupId;
        return this;
    }

    public void setTenderScheduleGroupId(Long tenderScheduleGroupId) {
        this.tenderScheduleGroupId = tenderScheduleGroupId;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TenderScheduleGroupItems)) {
            return false;
        }
        return id != null && id.equals(((TenderScheduleGroupItems) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TenderScheduleGroupItems{" +
            "id=" + getId() +
            ", tenderGoodsIndentItemId=" + getTenderGoodsIndentItemId() +
            ", tenderScheduleGroupId=" + getTenderScheduleGroupId() +
            "}";
    }
}
