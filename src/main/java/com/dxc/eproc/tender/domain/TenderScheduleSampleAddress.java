package com.dxc.eproc.tender.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TenderScheduleSampleAddress.
 */
@Entity
@Table(name = "tender_schedule_sample_address")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TenderScheduleSampleAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "block_number", nullable = false)
    private String blockNumber;

    @NotNull
    @Column(name = "street", nullable = false)
    private String street;

    @NotNull
    @Column(name = "city", nullable = false)
    private String city;

    @NotNull
    @Column(name = "pin", nullable = false)
    private String pin;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TenderScheduleSampleAddress id(Long id) {
        this.id = id;
        return this;
    }

    public String getBlockNumber() {
        return this.blockNumber;
    }

    public TenderScheduleSampleAddress blockNumber(String blockNumber) {
        this.blockNumber = blockNumber;
        return this;
    }

    public void setBlockNumber(String blockNumber) {
        this.blockNumber = blockNumber;
    }

    public String getStreet() {
        return this.street;
    }

    public TenderScheduleSampleAddress street(String street) {
        this.street = street;
        return this;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return this.city;
    }

    public TenderScheduleSampleAddress city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPin() {
        return this.pin;
    }

    public TenderScheduleSampleAddress pin(String pin) {
        this.pin = pin;
        return this;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TenderScheduleSampleAddress)) {
            return false;
        }
        return id != null && id.equals(((TenderScheduleSampleAddress) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TenderScheduleSampleAddress{" +
            "id=" + getId() +
            ", blockNumber='" + getBlockNumber() + "'" +
            ", street='" + getStreet() + "'" +
            ", city='" + getCity() + "'" +
            ", pin='" + getPin() + "'" +
            "}";
    }
}
