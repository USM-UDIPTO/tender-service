package com.dxc.eproc.tender.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.tender.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TenderQueryResponseDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TenderQueryResponseDTO.class);
        TenderQueryResponseDTO tenderQueryResponseDTO1 = new TenderQueryResponseDTO();
        tenderQueryResponseDTO1.setId(1L);
        TenderQueryResponseDTO tenderQueryResponseDTO2 = new TenderQueryResponseDTO();
        assertThat(tenderQueryResponseDTO1).isNotEqualTo(tenderQueryResponseDTO2);
        tenderQueryResponseDTO2.setId(tenderQueryResponseDTO1.getId());
        assertThat(tenderQueryResponseDTO1).isEqualTo(tenderQueryResponseDTO2);
        tenderQueryResponseDTO2.setId(2L);
        assertThat(tenderQueryResponseDTO1).isNotEqualTo(tenderQueryResponseDTO2);
        tenderQueryResponseDTO1.setId(null);
        assertThat(tenderQueryResponseDTO1).isNotEqualTo(tenderQueryResponseDTO2);
    }
}
