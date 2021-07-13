package com.dxc.eproc.tender.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.tender.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CriterionDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CriterionDTO.class);
        CriterionDTO criterionDTO1 = new CriterionDTO();
        criterionDTO1.setId(1L);
        CriterionDTO criterionDTO2 = new CriterionDTO();
        assertThat(criterionDTO1).isNotEqualTo(criterionDTO2);
        criterionDTO2.setId(criterionDTO1.getId());
        assertThat(criterionDTO1).isEqualTo(criterionDTO2);
        criterionDTO2.setId(2L);
        assertThat(criterionDTO1).isNotEqualTo(criterionDTO2);
        criterionDTO1.setId(null);
        assertThat(criterionDTO1).isNotEqualTo(criterionDTO2);
    }
}
