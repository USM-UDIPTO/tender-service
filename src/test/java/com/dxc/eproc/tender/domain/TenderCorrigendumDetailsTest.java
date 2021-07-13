package com.dxc.eproc.tender.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.tender.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TenderCorrigendumDetailsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TenderCorrigendumDetails.class);
        TenderCorrigendumDetails tenderCorrigendumDetails1 = new TenderCorrigendumDetails();
        tenderCorrigendumDetails1.setId(1L);
        TenderCorrigendumDetails tenderCorrigendumDetails2 = new TenderCorrigendumDetails();
        tenderCorrigendumDetails2.setId(tenderCorrigendumDetails1.getId());
        assertThat(tenderCorrigendumDetails1).isEqualTo(tenderCorrigendumDetails2);
        tenderCorrigendumDetails2.setId(2L);
        assertThat(tenderCorrigendumDetails1).isNotEqualTo(tenderCorrigendumDetails2);
        tenderCorrigendumDetails1.setId(null);
        assertThat(tenderCorrigendumDetails1).isNotEqualTo(tenderCorrigendumDetails2);
    }
}
