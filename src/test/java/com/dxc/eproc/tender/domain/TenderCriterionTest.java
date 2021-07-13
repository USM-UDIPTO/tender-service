package com.dxc.eproc.tender.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.tender.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TenderCriterionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TenderCriterion.class);
        TenderCriterion tenderCriterion1 = new TenderCriterion();
        tenderCriterion1.setId(1L);
        TenderCriterion tenderCriterion2 = new TenderCriterion();
        tenderCriterion2.setId(tenderCriterion1.getId());
        assertThat(tenderCriterion1).isEqualTo(tenderCriterion2);
        tenderCriterion2.setId(2L);
        assertThat(tenderCriterion1).isNotEqualTo(tenderCriterion2);
        tenderCriterion1.setId(null);
        assertThat(tenderCriterion1).isNotEqualTo(tenderCriterion2);
    }
}
