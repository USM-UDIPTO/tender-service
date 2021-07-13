package com.dxc.eproc.tender.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.tender.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TenderAddendumTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TenderAddendum.class);
        TenderAddendum tenderAddendum1 = new TenderAddendum();
        tenderAddendum1.setId(1L);
        TenderAddendum tenderAddendum2 = new TenderAddendum();
        tenderAddendum2.setId(tenderAddendum1.getId());
        assertThat(tenderAddendum1).isEqualTo(tenderAddendum2);
        tenderAddendum2.setId(2L);
        assertThat(tenderAddendum1).isNotEqualTo(tenderAddendum2);
        tenderAddendum1.setId(null);
        assertThat(tenderAddendum1).isNotEqualTo(tenderAddendum2);
    }
}
