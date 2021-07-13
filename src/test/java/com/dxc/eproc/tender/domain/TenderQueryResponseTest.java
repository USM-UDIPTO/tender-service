package com.dxc.eproc.tender.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.tender.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TenderQueryResponseTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TenderQueryResponse.class);
        TenderQueryResponse tenderQueryResponse1 = new TenderQueryResponse();
        tenderQueryResponse1.setId(1L);
        TenderQueryResponse tenderQueryResponse2 = new TenderQueryResponse();
        tenderQueryResponse2.setId(tenderQueryResponse1.getId());
        assertThat(tenderQueryResponse1).isEqualTo(tenderQueryResponse2);
        tenderQueryResponse2.setId(2L);
        assertThat(tenderQueryResponse1).isNotEqualTo(tenderQueryResponse2);
        tenderQueryResponse1.setId(null);
        assertThat(tenderQueryResponse1).isNotEqualTo(tenderQueryResponse2);
    }
}
