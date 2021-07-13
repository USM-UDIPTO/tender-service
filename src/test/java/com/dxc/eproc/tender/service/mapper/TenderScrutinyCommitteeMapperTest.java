package com.dxc.eproc.tender.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TenderScrutinyCommitteeMapperTest {

    private TenderScrutinyCommitteeMapper tenderScrutinyCommitteeMapper;

    @BeforeEach
    public void setUp() {
        tenderScrutinyCommitteeMapper = new TenderScrutinyCommitteeMapperImpl();
    }
}
