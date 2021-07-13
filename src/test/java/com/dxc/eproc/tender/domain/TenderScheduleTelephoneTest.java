package com.dxc.eproc.tender.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.tender.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TenderScheduleTelephoneTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TenderScheduleTelephone.class);
        TenderScheduleTelephone tenderScheduleTelephone1 = new TenderScheduleTelephone();
        tenderScheduleTelephone1.setId(1L);
        TenderScheduleTelephone tenderScheduleTelephone2 = new TenderScheduleTelephone();
        tenderScheduleTelephone2.setId(tenderScheduleTelephone1.getId());
        assertThat(tenderScheduleTelephone1).isEqualTo(tenderScheduleTelephone2);
        tenderScheduleTelephone2.setId(2L);
        assertThat(tenderScheduleTelephone1).isNotEqualTo(tenderScheduleTelephone2);
        tenderScheduleTelephone1.setId(null);
        assertThat(tenderScheduleTelephone1).isNotEqualTo(tenderScheduleTelephone2);
    }
}
