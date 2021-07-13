package com.dxc.eproc.tender.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.dxc.eproc.tender.domain.SeriesTable} entity.
 */
public class SeriesTableDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String prefix;

    @NotNull
    private String nextSeries;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getNextSeries() {
        return nextSeries;
    }

    public void setNextSeries(String nextSeries) {
        this.nextSeries = nextSeries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SeriesTableDTO)) {
            return false;
        }

        SeriesTableDTO seriesTableDTO = (SeriesTableDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, seriesTableDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SeriesTableDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", prefix='" + getPrefix() + "'" +
            ", nextSeries='" + getNextSeries() + "'" +
            "}";
    }
}
