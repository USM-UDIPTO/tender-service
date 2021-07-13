package com.dxc.eproc.tender.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.tender.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TenderCorrigendumDetailsDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TenderCorrigendumDetailsDTO.class);
        TenderCorrigendumDetailsDTO tenderCorrigendumDetailsDTO1 = new TenderCorrigendumDetailsDTO();
        tenderCorrigendumDetailsDTO1.setId(1L);
        TenderCorrigendumDetailsDTO tenderCorrigendumDetailsDTO2 = new TenderCorrigendumDetailsDTO();
        assertThat(tenderCorrigendumDetailsDTO1).isNotEqualTo(tenderCorrigendumDetailsDTO2);
        tenderCorrigendumDetailsDTO2.setId(tenderCorrigendumDetailsDTO1.getId());
        assertThat(tenderCorrigendumDetailsDTO1).isEqualTo(tenderCorrigendumDetailsDTO2);
        tenderCorrigendumDetailsDTO2.setId(2L);
        assertThat(tenderCorrigendumDetailsDTO1).isNotEqualTo(tenderCorrigendumDetailsDTO2);
        tenderCorrigendumDetailsDTO1.setId(null);
        assertThat(tenderCorrigendumDetailsDTO1).isNotEqualTo(tenderCorrigendumDetailsDTO2);
    }
}
