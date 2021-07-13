package com.dxc.eproc.tender.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.tender.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CriterionDocumentTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CriterionDocument.class);
        CriterionDocument criterionDocument1 = new CriterionDocument();
        criterionDocument1.setId(1L);
        CriterionDocument criterionDocument2 = new CriterionDocument();
        criterionDocument2.setId(criterionDocument1.getId());
        assertThat(criterionDocument1).isEqualTo(criterionDocument2);
        criterionDocument2.setId(2L);
        assertThat(criterionDocument1).isNotEqualTo(criterionDocument2);
        criterionDocument1.setId(null);
        assertThat(criterionDocument1).isNotEqualTo(criterionDocument2);
    }
}
