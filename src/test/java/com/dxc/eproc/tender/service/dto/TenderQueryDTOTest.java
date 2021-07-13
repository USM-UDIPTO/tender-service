package com.dxc.eproc.tender.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.tender.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TenderQueryDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TenderQueryDTO.class);
        TenderQueryDTO tenderQueryDTO1 = new TenderQueryDTO();
        tenderQueryDTO1.setId(1L);
        TenderQueryDTO tenderQueryDTO2 = new TenderQueryDTO();
        assertThat(tenderQueryDTO1).isNotEqualTo(tenderQueryDTO2);
        tenderQueryDTO2.setId(tenderQueryDTO1.getId());
        assertThat(tenderQueryDTO1).isEqualTo(tenderQueryDTO2);
        tenderQueryDTO2.setId(2L);
        assertThat(tenderQueryDTO1).isNotEqualTo(tenderQueryDTO2);
        tenderQueryDTO1.setId(null);
        assertThat(tenderQueryDTO1).isNotEqualTo(tenderQueryDTO2);
    }
}
