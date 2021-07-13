package com.dxc.eproc.tender.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A SeriesTable.
 */
@Entity
@Table(name = "series_table")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SeriesTable implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "prefix", nullable = false)
    private String prefix;

    @NotNull
    @Column(name = "next_series", nullable = false)
    private String nextSeries;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SeriesTable id(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public SeriesTable name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public SeriesTable prefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getNextSeries() {
        return this.nextSeries;
    }

    public SeriesTable nextSeries(String nextSeries) {
        this.nextSeries = nextSeries;
        return this;
    }

    public void setNextSeries(String nextSeries) {
        this.nextSeries = nextSeries;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SeriesTable)) {
            return false;
        }
        return id != null && id.equals(((SeriesTable) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SeriesTable{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", prefix='" + getPrefix() + "'" +
            ", nextSeries='" + getNextSeries() + "'" +
            "}";
    }
}
