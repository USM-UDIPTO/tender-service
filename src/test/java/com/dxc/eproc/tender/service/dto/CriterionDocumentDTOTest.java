package com.dxc.eproc.tender.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.tender.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CriterionDocumentDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CriterionDocumentDTO.class);
        CriterionDocumentDTO criterionDocumentDTO1 = new CriterionDocumentDTO();
        criterionDocumentDTO1.setId(1L);
        CriterionDocumentDTO criterionDocumentDTO2 = new CriterionDocumentDTO();
        assertThat(criterionDocumentDTO1).isNotEqualTo(criterionDocumentDTO2);
        criterionDocumentDTO2.setId(criterionDocumentDTO1.getId());
        assertThat(criterionDocumentDTO1).isEqualTo(criterionDocumentDTO2);
        criterionDocumentDTO2.setId(2L);
        assertThat(criterionDocumentDTO1).isNotEqualTo(criterionDocumentDTO2);
        criterionDocumentDTO1.setId(null);
        assertThat(criterionDocumentDTO1).isNotEqualTo(criterionDocumentDTO2);
    }
}
