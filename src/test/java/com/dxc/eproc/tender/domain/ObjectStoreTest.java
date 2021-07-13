package com.dxc.eproc.tender.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.tender.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ObjectStoreTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ObjectStore.class);
        ObjectStore objectStore1 = new ObjectStore();
        objectStore1.setId(1L);
        ObjectStore objectStore2 = new ObjectStore();
        objectStore2.setId(objectStore1.getId());
        assertThat(objectStore1).isEqualTo(objectStore2);
        objectStore2.setId(2L);
        assertThat(objectStore1).isNotEqualTo(objectStore2);
        objectStore1.setId(null);
        assertThat(objectStore1).isNotEqualTo(objectStore2);
    }
}
