package com.dxc.eproc.tender.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.tender.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TelephoneTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Telephone.class);
        Telephone telephone1 = new Telephone();
        telephone1.setId(1L);
        Telephone telephone2 = new Telephone();
        telephone2.setId(telephone1.getId());
        assertThat(telephone1).isEqualTo(telephone2);
        telephone2.setId(2L);
        assertThat(telephone1).isNotEqualTo(telephone2);
        telephone1.setId(null);
        assertThat(telephone1).isNotEqualTo(telephone2);
    }
}
