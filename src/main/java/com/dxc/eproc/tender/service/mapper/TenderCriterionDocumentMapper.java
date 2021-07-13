package com.dxc.eproc.tender.service.mapper;

import com.dxc.eproc.tender.domain.*;
import com.dxc.eproc.tender.service.dto.TenderCriterionDocumentDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TenderCriterionDocument} and its DTO {@link TenderCriterionDocumentDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TenderCriterionDocumentMapper extends EntityMapper<TenderCriterionDocumentDTO, TenderCriterionDocument> {}
