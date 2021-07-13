package com.dxc.eproc.tender.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.tender.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TenderScrutinyMasterDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TenderScrutinyMasterDTO.class);
        TenderScrutinyMasterDTO tenderScrutinyMasterDTO1 = new TenderScrutinyMasterDTO();
        tenderScrutinyMasterDTO1.setId(1L);
        TenderScrutinyMasterDTO tenderScrutinyMasterDTO2 = new TenderScrutinyMasterDTO();
        assertThat(tenderScrutinyMasterDTO1).isNotEqualTo(tenderScrutinyMasterDTO2);
        tenderScrutinyMasterDTO2.setId(tenderScrutinyMasterDTO1.getId());
        assertThat(tenderScrutinyMasterDTO1).isEqualTo(tenderScrutinyMasterDTO2);
        tenderScrutinyMasterDTO2.setId(2L);
        assertThat(tenderScrutinyMasterDTO1).isNotEqualTo(tenderScrutinyMasterDTO2);
        tenderScrutinyMasterDTO1.setId(null);
        assertThat(tenderScrutinyMasterDTO1).isNotEqualTo(tenderScrutinyMasterDTO2);
    }
}
