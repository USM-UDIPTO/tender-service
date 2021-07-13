package com.dxc.eproc.tender.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.tender.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ObjectStoreDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ObjectStoreDTO.class);
        ObjectStoreDTO objectStoreDTO1 = new ObjectStoreDTO();
        objectStoreDTO1.setId(1L);
        ObjectStoreDTO objectStoreDTO2 = new ObjectStoreDTO();
        assertThat(objectStoreDTO1).isNotEqualTo(objectStoreDTO2);
        objectStoreDTO2.setId(objectStoreDTO1.getId());
        assertThat(objectStoreDTO1).isEqualTo(objectStoreDTO2);
        objectStoreDTO2.setId(2L);
        assertThat(objectStoreDTO1).isNotEqualTo(objectStoreDTO2);
        objectStoreDTO1.setId(null);
        assertThat(objectStoreDTO1).isNotEqualTo(objectStoreDTO2);
    }
}
