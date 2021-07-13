package com.dxc.eproc.tender.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.tender.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TenderScheduleGroupDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TenderScheduleGroupDTO.class);
        TenderScheduleGroupDTO tenderScheduleGroupDTO1 = new TenderScheduleGroupDTO();
        tenderScheduleGroupDTO1.setId(1L);
        TenderScheduleGroupDTO tenderScheduleGroupDTO2 = new TenderScheduleGroupDTO();
        assertThat(tenderScheduleGroupDTO1).isNotEqualTo(tenderScheduleGroupDTO2);
        tenderScheduleGroupDTO2.setId(tenderScheduleGroupDTO1.getId());
        assertThat(tenderScheduleGroupDTO1).isEqualTo(tenderScheduleGroupDTO2);
        tenderScheduleGroupDTO2.setId(2L);
        assertThat(tenderScheduleGroupDTO1).isNotEqualTo(tenderScheduleGroupDTO2);
        tenderScheduleGroupDTO1.setId(null);
        assertThat(tenderScheduleGroupDTO1).isNotEqualTo(tenderScheduleGroupDTO2);
    }
}
