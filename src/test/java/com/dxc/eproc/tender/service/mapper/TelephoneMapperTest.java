package com.dxc.eproc.tender.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TelephoneMapperTest {

    private TelephoneMapper telephoneMapper;

    @BeforeEach
    public void setUp() {
        telephoneMapper = new TelephoneMapperImpl();
    }
}
