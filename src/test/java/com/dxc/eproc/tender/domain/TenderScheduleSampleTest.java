package com.dxc.eproc.tender.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.tender.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TenderScheduleSampleTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TenderScheduleSample.class);
        TenderScheduleSample tenderScheduleSample1 = new TenderScheduleSample();
        tenderScheduleSample1.setId(1L);
        TenderScheduleSample tenderScheduleSample2 = new TenderScheduleSample();
        tenderScheduleSample2.setId(tenderScheduleSample1.getId());
        assertThat(tenderScheduleSample1).isEqualTo(tenderScheduleSample2);
        tenderScheduleSample2.setId(2L);
        assertThat(tenderScheduleSample1).isNotEqualTo(tenderScheduleSample2);
        tenderScheduleSample1.setId(null);
        assertThat(tenderScheduleSample1).isNotEqualTo(tenderScheduleSample2);
    }
}
