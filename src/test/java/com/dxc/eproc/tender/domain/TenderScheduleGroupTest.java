package com.dxc.eproc.tender.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.tender.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TenderScheduleGroupTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TenderScheduleGroup.class);
        TenderScheduleGroup tenderScheduleGroup1 = new TenderScheduleGroup();
        tenderScheduleGroup1.setId(1L);
        TenderScheduleGroup tenderScheduleGroup2 = new TenderScheduleGroup();
        tenderScheduleGroup2.setId(tenderScheduleGroup1.getId());
        assertThat(tenderScheduleGroup1).isEqualTo(tenderScheduleGroup2);
        tenderScheduleGroup2.setId(2L);
        assertThat(tenderScheduleGroup1).isNotEqualTo(tenderScheduleGroup2);
        tenderScheduleGroup1.setId(null);
        assertThat(tenderScheduleGroup1).isNotEqualTo(tenderScheduleGroup2);
    }
}
