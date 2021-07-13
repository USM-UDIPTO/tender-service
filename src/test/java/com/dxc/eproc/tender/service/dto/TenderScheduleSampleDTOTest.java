package com.dxc.eproc.tender.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.tender.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TenderScheduleSampleDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TenderScheduleSampleDTO.class);
        TenderScheduleSampleDTO tenderScheduleSampleDTO1 = new TenderScheduleSampleDTO();
        tenderScheduleSampleDTO1.setId(1L);
        TenderScheduleSampleDTO tenderScheduleSampleDTO2 = new TenderScheduleSampleDTO();
        assertThat(tenderScheduleSampleDTO1).isNotEqualTo(tenderScheduleSampleDTO2);
        tenderScheduleSampleDTO2.setId(tenderScheduleSampleDTO1.getId());
        assertThat(tenderScheduleSampleDTO1).isEqualTo(tenderScheduleSampleDTO2);
        tenderScheduleSampleDTO2.setId(2L);
        assertThat(tenderScheduleSampleDTO1).isNotEqualTo(tenderScheduleSampleDTO2);
        tenderScheduleSampleDTO1.setId(null);
        assertThat(tenderScheduleSampleDTO1).isNotEqualTo(tenderScheduleSampleDTO2);
    }
}
