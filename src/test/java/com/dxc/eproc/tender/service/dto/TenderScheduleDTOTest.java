package com.dxc.eproc.tender.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.tender.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TenderScheduleDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TenderScheduleDTO.class);
        TenderScheduleDTO tenderScheduleDTO1 = new TenderScheduleDTO();
        tenderScheduleDTO1.setId(1L);
        TenderScheduleDTO tenderScheduleDTO2 = new TenderScheduleDTO();
        assertThat(tenderScheduleDTO1).isNotEqualTo(tenderScheduleDTO2);
        tenderScheduleDTO2.setId(tenderScheduleDTO1.getId());
        assertThat(tenderScheduleDTO1).isEqualTo(tenderScheduleDTO2);
        tenderScheduleDTO2.setId(2L);
        assertThat(tenderScheduleDTO1).isNotEqualTo(tenderScheduleDTO2);
        tenderScheduleDTO1.setId(null);
        assertThat(tenderScheduleDTO1).isNotEqualTo(tenderScheduleDTO2);
    }
}
