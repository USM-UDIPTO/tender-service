package com.dxc.eproc.tender.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.tender.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TenderCriterionDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TenderCriterionDTO.class);
        TenderCriterionDTO tenderCriterionDTO1 = new TenderCriterionDTO();
        tenderCriterionDTO1.setId(1L);
        TenderCriterionDTO tenderCriterionDTO2 = new TenderCriterionDTO();
        assertThat(tenderCriterionDTO1).isNotEqualTo(tenderCriterionDTO2);
        tenderCriterionDTO2.setId(tenderCriterionDTO1.getId());
        assertThat(tenderCriterionDTO1).isEqualTo(tenderCriterionDTO2);
        tenderCriterionDTO2.setId(2L);
        assertThat(tenderCriterionDTO1).isNotEqualTo(tenderCriterionDTO2);
        tenderCriterionDTO1.setId(null);
        assertThat(tenderCriterionDTO1).isNotEqualTo(tenderCriterionDTO2);
    }
}
