package com.dxc.eproc.tender.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TenderScrutinyMasterMapperTest {

    private TenderScrutinyMasterMapper tenderScrutinyMasterMapper;

    @BeforeEach
    public void setUp() {
        tenderScrutinyMasterMapper = new TenderScrutinyMasterMapperImpl();
    }
}
