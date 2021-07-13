package com.dxc.eproc.tender.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.dxc.eproc.tender.domain.TenderScheduleSample} entity.
 */
public class TenderScheduleSampleDTO implements Serializable {

    private Long id;

    @NotNull
    private Long nitId;

    @NotNull
    private Long addressId;

    @NotNull
    @Size(max = 50)
    private String name;

    @NotNull
    @Size(max = 30)
    private String designation;

    @NotNull
    private LocalDate date;

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

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TenderScheduleSampleDTO)) {
            return false;
        }

        TenderScheduleSampleDTO tenderScheduleSampleDTO = (TenderScheduleSampleDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, tenderScheduleSampleDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TenderScheduleSampleDTO{" +
            "id=" + getId() +
            ", nitId=" + getNitId() +
            ", addressId=" + getAddressId() +
            ", name='" + getName() + "'" +
            ", designation='" + getDesignation() + "'" +
            ", date='" + getDate() + "'" +
            "}";
    }
}
