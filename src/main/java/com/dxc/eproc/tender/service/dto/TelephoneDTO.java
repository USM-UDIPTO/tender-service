package com.dxc.eproc.tender.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.dxc.eproc.tender.domain.Telephone} entity.
 */
public class TelephoneDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer countryCode;

    @NotNull
    private Integer areaCode;

    @NotNull
    private String number;

    @NotNull
    private String type;

    @NotNull
    private String mobOTP;

    @NotNull
    private LocalDate mobOTPTS;

    @NotNull
    private Boolean mobOTPExpired;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Integer countryCode) {
        this.countryCode = countryCode;
    }

    public Integer getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(Integer areaCode) {
        this.areaCode = areaCode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMobOTP() {
        return mobOTP;
    }

    public void setMobOTP(String mobOTP) {
        this.mobOTP = mobOTP;
    }

    public LocalDate getMobOTPTS() {
        return mobOTPTS;
    }

    public void setMobOTPTS(LocalDate mobOTPTS) {
        this.mobOTPTS = mobOTPTS;
    }

    public Boolean getMobOTPExpired() {
        return mobOTPExpired;
    }

    public void setMobOTPExpired(Boolean mobOTPExpired) {
        this.mobOTPExpired = mobOTPExpired;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TelephoneDTO)) {
            return false;
        }

        TelephoneDTO telephoneDTO = (TelephoneDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, telephoneDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TelephoneDTO{" +
            "id=" + getId() +
            ", countryCode=" + getCountryCode() +
            ", areaCode=" + getAreaCode() +
            ", number='" + getNumber() + "'" +
            ", type='" + getType() + "'" +
            ", mobOTP='" + getMobOTP() + "'" +
            ", mobOTPTS='" + getMobOTPTS() + "'" +
            ", mobOTPExpired='" + getMobOTPExpired() + "'" +
            "}";
    }
}
