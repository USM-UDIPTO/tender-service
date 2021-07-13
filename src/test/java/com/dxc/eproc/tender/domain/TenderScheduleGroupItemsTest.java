package com.dxc.eproc.tender.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.tender.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TenderScheduleGroupItemsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TenderScheduleGroupItems.class);
        TenderScheduleGroupItems tenderScheduleGroupItems1 = new TenderScheduleGroupItems();
        tenderScheduleGroupItems1.setId(1L);
        TenderScheduleGroupItems tenderScheduleGroupItems2 = new TenderScheduleGroupItems();
        tenderScheduleGroupItems2.setId(tenderScheduleGroupItems1.getId());
        assertThat(tenderScheduleGroupItems1).isEqualTo(tenderScheduleGroupItems2);
        tenderScheduleGroupItems2.setId(2L);
        assertThat(tenderScheduleGroupItems1).isNotEqualTo(tenderScheduleGroupItems2);
        tenderScheduleGroupItems1.setId(null);
        assertThat(tenderScheduleGroupItems1).isNotEqualTo(tenderScheduleGroupItems2);
    }
}
