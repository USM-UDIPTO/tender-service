package com.dxc.eproc.tender.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CriterionMapperTest {

    private CriterionMapper criterionMapper;

    @BeforeEach
    public void setUp() {
        criterionMapper = new CriterionMapperImpl();
    }
}
