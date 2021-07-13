package com.dxc.eproc.tender.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.tender.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TenderQueryTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TenderQuery.class);
        TenderQuery tenderQuery1 = new TenderQuery();
        tenderQuery1.setId(1L);
        TenderQuery tenderQuery2 = new TenderQuery();
        tenderQuery2.setId(tenderQuery1.getId());
        assertThat(tenderQuery1).isEqualTo(tenderQuery2);
        tenderQuery2.setId(2L);
        assertThat(tenderQuery1).isNotEqualTo(tenderQuery2);
        tenderQuery1.setId(null);
        assertThat(tenderQuery1).isNotEqualTo(tenderQuery2);
    }
}
