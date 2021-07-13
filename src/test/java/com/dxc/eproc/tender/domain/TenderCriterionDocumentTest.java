package com.dxc.eproc.tender.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.tender.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TenderCriterionDocumentTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TenderCriterionDocument.class);
        TenderCriterionDocument tenderCriterionDocument1 = new TenderCriterionDocument();
        tenderCriterionDocument1.setId(1L);
        TenderCriterionDocument tenderCriterionDocument2 = new TenderCriterionDocument();
        tenderCriterionDocument2.setId(tenderCriterionDocument1.getId());
        assertThat(tenderCriterionDocument1).isEqualTo(tenderCriterionDocument2);
        tenderCriterionDocument2.setId(2L);
        assertThat(tenderCriterionDocument1).isNotEqualTo(tenderCriterionDocument2);
        tenderCriterionDocument1.setId(null);
        assertThat(tenderCriterionDocument1).isNotEqualTo(tenderCriterionDocument2);
    }
}
