package com.dxc.eproc.tender.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ObjectStoreMapperTest {

    private ObjectStoreMapper objectStoreMapper;

    @BeforeEach
    public void setUp() {
        objectStoreMapper = new ObjectStoreMapperImpl();
    }
}
