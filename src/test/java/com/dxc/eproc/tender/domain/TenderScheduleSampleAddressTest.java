package com.dxc.eproc.tender.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.tender.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TenderScheduleSampleAddressTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TenderScheduleSampleAddress.class);
        TenderScheduleSampleAddress tenderScheduleSampleAddress1 = new TenderScheduleSampleAddress();
        tenderScheduleSampleAddress1.setId(1L);
        TenderScheduleSampleAddress tenderScheduleSampleAddress2 = new TenderScheduleSampleAddress();
        tenderScheduleSampleAddress2.setId(tenderScheduleSampleAddress1.getId());
        assertThat(tenderScheduleSampleAddress1).isEqualTo(tenderScheduleSampleAddress2);
        tenderScheduleSampleAddress2.setId(2L);
        assertThat(tenderScheduleSampleAddress1).isNotEqualTo(tenderScheduleSampleAddress2);
        tenderScheduleSampleAddress1.setId(null);
        assertThat(tenderScheduleSampleAddress1).isNotEqualTo(tenderScheduleSampleAddress2);
    }
}
