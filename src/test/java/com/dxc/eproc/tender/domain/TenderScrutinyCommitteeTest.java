package com.dxc.eproc.tender.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.tender.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TenderScrutinyCommitteeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TenderScrutinyCommittee.class);
        TenderScrutinyCommittee tenderScrutinyCommittee1 = new TenderScrutinyCommittee();
        tenderScrutinyCommittee1.setId(1L);
        TenderScrutinyCommittee tenderScrutinyCommittee2 = new TenderScrutinyCommittee();
        tenderScrutinyCommittee2.setId(tenderScrutinyCommittee1.getId());
        assertThat(tenderScrutinyCommittee1).isEqualTo(tenderScrutinyCommittee2);
        tenderScrutinyCommittee2.setId(2L);
        assertThat(tenderScrutinyCommittee1).isNotEqualTo(tenderScrutinyCommittee2);
        tenderScrutinyCommittee1.setId(null);
        assertThat(tenderScrutinyCommittee1).isNotEqualTo(tenderScrutinyCommittee2);
    }
}
