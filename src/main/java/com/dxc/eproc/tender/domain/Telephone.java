package com.dxc.eproc.tender.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Telephone.
 */
@Entity
@Table(name = "telephone")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Telephone implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "country_code", nullable = false)
    private Integer countryCode;

    @NotNull
    @Column(name = "area_code", nullable = false)
    private Integer areaCode;

    @NotNull
    @Column(name = "number", nullable = false)
    private String number;

    @NotNull
    @Column(name = "type", nullable = false)
    private String type;

    @NotNull
    @Column(name = "mob_otp", nullable = false)
    private String mobOTP;

    @NotNull
    @Column(name = "mob_otpts", nullable = false)
    private LocalDate mobOTPTS;

    @NotNull
    @Column(name = "mob_otp_expired", nullable = false)
    private Boolean mobOTPExpired;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Telephone id(Long id) {
        this.id = id;
        return this;
    }

    public Integer getCountryCode() {
        return this.countryCode;
    }

    public Telephone countryCode(Integer countryCode) {
        this.countryCode = countryCode;
        return this;
    }

    public void setCountryCode(Integer countryCode) {
        this.countryCode = countryCode;
    }

    public Integer getAreaCode() {
        return this.areaCode;
    }

    public Telephone areaCode(Integer areaCode) {
        this.areaCode = areaCode;
        return this;
    }

    public void setAreaCode(Integer areaCode) {
        this.areaCode = areaCode;
    }

    public String getNumber() {
        return this.number;
    }

    public Telephone number(String number) {
        this.number = number;
        return this;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return this.type;
    }

    public Telephone type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMobOTP() {
        return this.mobOTP;
    }

    public Telephone mobOTP(String mobOTP) {
        this.mobOTP = mobOTP;
        return this;
    }

    public void setMobOTP(String mobOTP) {
        this.mobOTP = mobOTP;
    }

    public LocalDate getMobOTPTS() {
        return this.mobOTPTS;
    }

    public Telephone mobOTPTS(LocalDate mobOTPTS) {
        this.mobOTPTS = mobOTPTS;
        return this;
    }

    public void setMobOTPTS(LocalDate mobOTPTS) {
        this.mobOTPTS = mobOTPTS;
    }

    public Boolean getMobOTPExpired() {
        return this.mobOTPExpired;
    }

    public Telephone mobOTPExpired(Boolean mobOTPExpired) {
        this.mobOTPExpired = mobOTPExpired;
        return this;
    }

    public void setMobOTPExpired(Boolean mobOTPExpired) {
        this.mobOTPExpired = mobOTPExpired;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Telephone)) {
            return false;
        }
        return id != null && id.equals(((Telephone) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Telephone{" +
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
