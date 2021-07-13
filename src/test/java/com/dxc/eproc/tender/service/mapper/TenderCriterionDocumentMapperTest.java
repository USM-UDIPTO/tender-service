package com.dxc.eproc.tender.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TenderCriterionDocumentMapperTest {

    private TenderCriterionDocumentMapper tenderCriterionDocumentMapper;

    @BeforeEach
    public void setUp() {
        tenderCriterionDocumentMapper = new TenderCriterionDocumentMapperImpl();
    }
}
