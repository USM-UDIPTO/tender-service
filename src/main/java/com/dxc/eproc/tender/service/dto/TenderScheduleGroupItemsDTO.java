package com.dxc.eproc.tender.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.dxc.eproc.tender.domain.TenderScheduleGroupItems} entity.
 */
public class TenderScheduleGroupItemsDTO implements Serializable {

    private Long id;

    @NotNull
    private Long tenderGoodsIndentItemId;

    @NotNull
    private Long tenderScheduleGroupId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTenderGoodsIndentItemId() {
        return tenderGoodsIndentItemId;
    }

    public void setTenderGoodsIndentItemId(Long tenderGoodsIndentItemId) {
        this.tenderGoodsIndentItemId = tenderGoodsIndentItemId;
    }

    public Long getTenderScheduleGroupId() {
        return tenderScheduleGroupId;
    }

    public void setTenderScheduleGroupId(Long tenderScheduleGroupId) {
        this.tenderScheduleGroupId = tenderScheduleGroupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TenderScheduleGroupItemsDTO)) {
            return false;
        }

        TenderScheduleGroupItemsDTO tenderScheduleGroupItemsDTO = (TenderScheduleGroupItemsDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, tenderScheduleGroupItemsDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TenderScheduleGroupItemsDTO{" +
            "id=" + getId() +
            ", tenderGoodsIndentItemId=" + getTenderGoodsIndentItemId() +
            ", tenderScheduleGroupId=" + getTenderScheduleGroupId() +
            "}";
    }
}
