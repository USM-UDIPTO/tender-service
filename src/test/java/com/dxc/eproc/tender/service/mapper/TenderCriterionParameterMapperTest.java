package com.dxc.eproc.tender.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TenderCriterionParameterMapperTest {

    private TenderCriterionParameterMapper tenderCriterionParameterMapper;

    @BeforeEach
    public void setUp() {
        tenderCriterionParameterMapper = new TenderCriterionParameterMapperImpl();
    }
}
