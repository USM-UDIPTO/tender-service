package com.dxc.eproc.tender.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.tender.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TenderCriterionDocumentDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TenderCriterionDocumentDTO.class);
        TenderCriterionDocumentDTO tenderCriterionDocumentDTO1 = new TenderCriterionDocumentDTO();
        tenderCriterionDocumentDTO1.setId(1L);
        TenderCriterionDocumentDTO tenderCriterionDocumentDTO2 = new TenderCriterionDocumentDTO();
        assertThat(tenderCriterionDocumentDTO1).isNotEqualTo(tenderCriterionDocumentDTO2);
        tenderCriterionDocumentDTO2.setId(tenderCriterionDocumentDTO1.getId());
        assertThat(tenderCriterionDocumentDTO1).isEqualTo(tenderCriterionDocumentDTO2);
        tenderCriterionDocumentDTO2.setId(2L);
        assertThat(tenderCriterionDocumentDTO1).isNotEqualTo(tenderCriterionDocumentDTO2);
        tenderCriterionDocumentDTO1.setId(null);
        assertThat(tenderCriterionDocumentDTO1).isNotEqualTo(tenderCriterionDocumentDTO2);
    }
}
