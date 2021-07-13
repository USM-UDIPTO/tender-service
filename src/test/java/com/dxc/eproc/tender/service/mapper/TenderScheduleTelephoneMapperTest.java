package com.dxc.eproc.tender.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TenderScheduleTelephoneMapperTest {

    private TenderScheduleTelephoneMapper tenderScheduleTelephoneMapper;

    @BeforeEach
    public void setUp() {
        tenderScheduleTelephoneMapper = new TenderScheduleTelephoneMapperImpl();
    }
}
