package com.dxc.eproc.tender.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.tender.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TenderGoodsItemsDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TenderGoodsItemsDTO.class);
        TenderGoodsItemsDTO tenderGoodsItemsDTO1 = new TenderGoodsItemsDTO();
        tenderGoodsItemsDTO1.setId(1L);
        TenderGoodsItemsDTO tenderGoodsItemsDTO2 = new TenderGoodsItemsDTO();
        assertThat(tenderGoodsItemsDTO1).isNotEqualTo(tenderGoodsItemsDTO2);
        tenderGoodsItemsDTO2.setId(tenderGoodsItemsDTO1.getId());
        assertThat(tenderGoodsItemsDTO1).isEqualTo(tenderGoodsItemsDTO2);
        tenderGoodsItemsDTO2.setId(2L);
        assertThat(tenderGoodsItemsDTO1).isNotEqualTo(tenderGoodsItemsDTO2);
        tenderGoodsItemsDTO1.setId(null);
        assertThat(tenderGoodsItemsDTO1).isNotEqualTo(tenderGoodsItemsDTO2);
    }
}
