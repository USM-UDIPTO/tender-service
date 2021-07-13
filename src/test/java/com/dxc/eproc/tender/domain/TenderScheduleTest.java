package com.dxc.eproc.tender.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.tender.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TenderScheduleTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TenderSchedule.class);
        TenderSchedule tenderSchedule1 = new TenderSchedule();
        tenderSchedule1.setId(1L);
        TenderSchedule tenderSchedule2 = new TenderSchedule();
        tenderSchedule2.setId(tenderSchedule1.getId());
        assertThat(tenderSchedule1).isEqualTo(tenderSchedule2);
        tenderSchedule2.setId(2L);
        assertThat(tenderSchedule1).isNotEqualTo(tenderSchedule2);
        tenderSchedule1.setId(null);
        assertThat(tenderSchedule1).isNotEqualTo(tenderSchedule2);
    }
}
