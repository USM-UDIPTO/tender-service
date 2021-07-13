package com.dxc.eproc.tender.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.tender.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TenderScheduleSampleAddressDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TenderScheduleSampleAddressDTO.class);
        TenderScheduleSampleAddressDTO tenderScheduleSampleAddressDTO1 = new TenderScheduleSampleAddressDTO();
        tenderScheduleSampleAddressDTO1.setId(1L);
        TenderScheduleSampleAddressDTO tenderScheduleSampleAddressDTO2 = new TenderScheduleSampleAddressDTO();
        assertThat(tenderScheduleSampleAddressDTO1).isNotEqualTo(tenderScheduleSampleAddressDTO2);
        tenderScheduleSampleAddressDTO2.setId(tenderScheduleSampleAddressDTO1.getId());
        assertThat(tenderScheduleSampleAddressDTO1).isEqualTo(tenderScheduleSampleAddressDTO2);
        tenderScheduleSampleAddressDTO2.setId(2L);
        assertThat(tenderScheduleSampleAddressDTO1).isNotEqualTo(tenderScheduleSampleAddressDTO2);
        tenderScheduleSampleAddressDTO1.setId(null);
        assertThat(tenderScheduleSampleAddressDTO1).isNotEqualTo(tenderScheduleSampleAddressDTO2);
    }
}
