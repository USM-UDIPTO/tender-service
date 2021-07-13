package com.dxc.eproc.tender.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.tender.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TenderScheduleGroupItemsDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TenderScheduleGroupItemsDTO.class);
        TenderScheduleGroupItemsDTO tenderScheduleGroupItemsDTO1 = new TenderScheduleGroupItemsDTO();
        tenderScheduleGroupItemsDTO1.setId(1L);
        TenderScheduleGroupItemsDTO tenderScheduleGroupItemsDTO2 = new TenderScheduleGroupItemsDTO();
        assertThat(tenderScheduleGroupItemsDTO1).isNotEqualTo(tenderScheduleGroupItemsDTO2);
        tenderScheduleGroupItemsDTO2.setId(tenderScheduleGroupItemsDTO1.getId());
        assertThat(tenderScheduleGroupItemsDTO1).isEqualTo(tenderScheduleGroupItemsDTO2);
        tenderScheduleGroupItemsDTO2.setId(2L);
        assertThat(tenderScheduleGroupItemsDTO1).isNotEqualTo(tenderScheduleGroupItemsDTO2);
        tenderScheduleGroupItemsDTO1.setId(null);
        assertThat(tenderScheduleGroupItemsDTO1).isNotEqualTo(tenderScheduleGroupItemsDTO2);
    }
}
