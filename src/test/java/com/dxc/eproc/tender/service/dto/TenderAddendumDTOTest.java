package com.dxc.eproc.tender.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.tender.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TenderAddendumDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TenderAddendumDTO.class);
        TenderAddendumDTO tenderAddendumDTO1 = new TenderAddendumDTO();
        tenderAddendumDTO1.setId(1L);
        TenderAddendumDTO tenderAddendumDTO2 = new TenderAddendumDTO();
        assertThat(tenderAddendumDTO1).isNotEqualTo(tenderAddendumDTO2);
        tenderAddendumDTO2.setId(tenderAddendumDTO1.getId());
        assertThat(tenderAddendumDTO1).isEqualTo(tenderAddendumDTO2);
        tenderAddendumDTO2.setId(2L);
        assertThat(tenderAddendumDTO1).isNotEqualTo(tenderAddendumDTO2);
        tenderAddendumDTO1.setId(null);
        assertThat(tenderAddendumDTO1).isNotEqualTo(tenderAddendumDTO2);
    }
}
