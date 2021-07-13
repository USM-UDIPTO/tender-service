package com.dxc.eproc.tender.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.tender.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TenderCorrigendumTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TenderCorrigendum.class);
        TenderCorrigendum tenderCorrigendum1 = new TenderCorrigendum();
        tenderCorrigendum1.setId(1L);
        TenderCorrigendum tenderCorrigendum2 = new TenderCorrigendum();
        tenderCorrigendum2.setId(tenderCorrigendum1.getId());
        assertThat(tenderCorrigendum1).isEqualTo(tenderCorrigendum2);
        tenderCorrigendum2.setId(2L);
        assertThat(tenderCorrigendum1).isNotEqualTo(tenderCorrigendum2);
        tenderCorrigendum1.setId(null);
        assertThat(tenderCorrigendum1).isNotEqualTo(tenderCorrigendum2);
    }
}
