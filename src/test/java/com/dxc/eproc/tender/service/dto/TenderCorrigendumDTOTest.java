package com.dxc.eproc.tender.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.tender.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TenderCorrigendumDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TenderCorrigendumDTO.class);
        TenderCorrigendumDTO tenderCorrigendumDTO1 = new TenderCorrigendumDTO();
        tenderCorrigendumDTO1.setId(1L);
        TenderCorrigendumDTO tenderCorrigendumDTO2 = new TenderCorrigendumDTO();
        assertThat(tenderCorrigendumDTO1).isNotEqualTo(tenderCorrigendumDTO2);
        tenderCorrigendumDTO2.setId(tenderCorrigendumDTO1.getId());
        assertThat(tenderCorrigendumDTO1).isEqualTo(tenderCorrigendumDTO2);
        tenderCorrigendumDTO2.setId(2L);
        assertThat(tenderCorrigendumDTO1).isNotEqualTo(tenderCorrigendumDTO2);
        tenderCorrigendumDTO1.setId(null);
        assertThat(tenderCorrigendumDTO1).isNotEqualTo(tenderCorrigendumDTO2);
    }
}
