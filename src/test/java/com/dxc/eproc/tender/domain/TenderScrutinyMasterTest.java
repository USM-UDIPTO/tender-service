package com.dxc.eproc.tender.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.tender.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TenderScrutinyMasterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TenderScrutinyMaster.class);
        TenderScrutinyMaster tenderScrutinyMaster1 = new TenderScrutinyMaster();
        tenderScrutinyMaster1.setId(1L);
        TenderScrutinyMaster tenderScrutinyMaster2 = new TenderScrutinyMaster();
        tenderScrutinyMaster2.setId(tenderScrutinyMaster1.getId());
        assertThat(tenderScrutinyMaster1).isEqualTo(tenderScrutinyMaster2);
        tenderScrutinyMaster2.setId(2L);
        assertThat(tenderScrutinyMaster1).isNotEqualTo(tenderScrutinyMaster2);
        tenderScrutinyMaster1.setId(null);
        assertThat(tenderScrutinyMaster1).isNotEqualTo(tenderScrutinyMaster2);
    }
}
