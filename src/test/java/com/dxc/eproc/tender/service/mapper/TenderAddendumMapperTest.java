package com.dxc.eproc.tender.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TenderAddendumMapperTest {

    private TenderAddendumMapper tenderAddendumMapper;

    @BeforeEach
    public void setUp() {
        tenderAddendumMapper = new TenderAddendumMapperImpl();
    }
}
