package com.dxc.eproc.tender.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TenderScheduleGroupMapperTest {

    private TenderScheduleGroupMapper tenderScheduleGroupMapper;

    @BeforeEach
    public void setUp() {
        tenderScheduleGroupMapper = new TenderScheduleGroupMapperImpl();
    }
}
