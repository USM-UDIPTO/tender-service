package com.dxc.eproc.tender.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.tender.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TenderCriterionParameterDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TenderCriterionParameterDTO.class);
        TenderCriterionParameterDTO tenderCriterionParameterDTO1 = new TenderCriterionParameterDTO();
        tenderCriterionParameterDTO1.setId(1L);
        TenderCriterionParameterDTO tenderCriterionParameterDTO2 = new TenderCriterionParameterDTO();
        assertThat(tenderCriterionParameterDTO1).isNotEqualTo(tenderCriterionParameterDTO2);
        tenderCriterionParameterDTO2.setId(tenderCriterionParameterDTO1.getId());
        assertThat(tenderCriterionParameterDTO1).isEqualTo(tenderCriterionParameterDTO2);
        tenderCriterionParameterDTO2.setId(2L);
        assertThat(tenderCriterionParameterDTO1).isNotEqualTo(tenderCriterionParameterDTO2);
        tenderCriterionParameterDTO1.setId(null);
        assertThat(tenderCriterionParameterDTO1).isNotEqualTo(tenderCriterionParameterDTO2);
    }
}
