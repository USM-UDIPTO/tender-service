package com.dxc.eproc.tender.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.tender.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TenderCriterionParameterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TenderCriterionParameter.class);
        TenderCriterionParameter tenderCriterionParameter1 = new TenderCriterionParameter();
        tenderCriterionParameter1.setId(1L);
        TenderCriterionParameter tenderCriterionParameter2 = new TenderCriterionParameter();
        tenderCriterionParameter2.setId(tenderCriterionParameter1.getId());
        assertThat(tenderCriterionParameter1).isEqualTo(tenderCriterionParameter2);
        tenderCriterionParameter2.setId(2L);
        assertThat(tenderCriterionParameter1).isNotEqualTo(tenderCriterionParameter2);
        tenderCriterionParameter1.setId(null);
        assertThat(tenderCriterionParameter1).isNotEqualTo(tenderCriterionParameter2);
    }
}
