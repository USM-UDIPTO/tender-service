package com.dxc.eproc.tender.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.tender.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SeriesTableDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SeriesTableDTO.class);
        SeriesTableDTO seriesTableDTO1 = new SeriesTableDTO();
        seriesTableDTO1.setId(1L);
        SeriesTableDTO seriesTableDTO2 = new SeriesTableDTO();
        assertThat(seriesTableDTO1).isNotEqualTo(seriesTableDTO2);
        seriesTableDTO2.setId(seriesTableDTO1.getId());
        assertThat(seriesTableDTO1).isEqualTo(seriesTableDTO2);
        seriesTableDTO2.setId(2L);
        assertThat(seriesTableDTO1).isNotEqualTo(seriesTableDTO2);
        seriesTableDTO1.setId(null);
        assertThat(seriesTableDTO1).isNotEqualTo(seriesTableDTO2);
    }
}
