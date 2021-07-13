package com.dxc.eproc.tender.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.tender.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SeriesTableTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SeriesTable.class);
        SeriesTable seriesTable1 = new SeriesTable();
        seriesTable1.setId(1L);
        SeriesTable seriesTable2 = new SeriesTable();
        seriesTable2.setId(seriesTable1.getId());
        assertThat(seriesTable1).isEqualTo(seriesTable2);
        seriesTable2.setId(2L);
        assertThat(seriesTable1).isNotEqualTo(seriesTable2);
        seriesTable1.setId(null);
        assertThat(seriesTable1).isNotEqualTo(seriesTable2);
    }
}
