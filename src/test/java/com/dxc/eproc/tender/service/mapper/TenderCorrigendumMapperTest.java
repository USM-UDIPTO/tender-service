package com.dxc.eproc.tender.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TenderCorrigendumMapperTest {

    private TenderCorrigendumMapper tenderCorrigendumMapper;

    @BeforeEach
    public void setUp() {
        tenderCorrigendumMapper = new TenderCorrigendumMapperImpl();
    }
}
