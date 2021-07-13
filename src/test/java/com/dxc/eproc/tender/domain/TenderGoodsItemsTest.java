package com.dxc.eproc.tender.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.tender.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TenderGoodsItemsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TenderGoodsItems.class);
        TenderGoodsItems tenderGoodsItems1 = new TenderGoodsItems();
        tenderGoodsItems1.setId(1L);
        TenderGoodsItems tenderGoodsItems2 = new TenderGoodsItems();
        tenderGoodsItems2.setId(tenderGoodsItems1.getId());
        assertThat(tenderGoodsItems1).isEqualTo(tenderGoodsItems2);
        tenderGoodsItems2.setId(2L);
        assertThat(tenderGoodsItems1).isNotEqualTo(tenderGoodsItems2);
        tenderGoodsItems1.setId(null);
        assertThat(tenderGoodsItems1).isNotEqualTo(tenderGoodsItems2);
    }
}
