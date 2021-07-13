package com.dxc.eproc.tender.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.tender.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CriterionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Criterion.class);
        Criterion criterion1 = new Criterion();
        criterion1.setId(1L);
        Criterion criterion2 = new Criterion();
        criterion2.setId(criterion1.getId());
        assertThat(criterion1).isEqualTo(criterion2);
        criterion2.setId(2L);
        assertThat(criterion1).isNotEqualTo(criterion2);
        criterion1.setId(null);
        assertThat(criterion1).isNotEqualTo(criterion2);
    }
}
