package com.dxc.eproc.tender.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.tender.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TenderScheduleTelephoneDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TenderScheduleTelephoneDTO.class);
        TenderScheduleTelephoneDTO tenderScheduleTelephoneDTO1 = new TenderScheduleTelephoneDTO();
        tenderScheduleTelephoneDTO1.setId(1L);
        TenderScheduleTelephoneDTO tenderScheduleTelephoneDTO2 = new TenderScheduleTelephoneDTO();
        assertThat(tenderScheduleTelephoneDTO1).isNotEqualTo(tenderScheduleTelephoneDTO2);
        tenderScheduleTelephoneDTO2.setId(tenderScheduleTelephoneDTO1.getId());
        assertThat(tenderScheduleTelephoneDTO1).isEqualTo(tenderScheduleTelephoneDTO2);
        tenderScheduleTelephoneDTO2.setId(2L);
        assertThat(tenderScheduleTelephoneDTO1).isNotEqualTo(tenderScheduleTelephoneDTO2);
        tenderScheduleTelephoneDTO1.setId(null);
        assertThat(tenderScheduleTelephoneDTO1).isNotEqualTo(tenderScheduleTelephoneDTO2);
    }
}
