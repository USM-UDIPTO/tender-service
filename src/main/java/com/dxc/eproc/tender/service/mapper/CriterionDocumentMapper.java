package com.dxc.eproc.tender.service.mapper;

import com.dxc.eproc.tender.domain.*;
import com.dxc.eproc.tender.service.dto.CriterionDocumentDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CriterionDocument} and its DTO {@link CriterionDocumentDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CriterionDocumentMapper extends EntityMapper<CriterionDocumentDTO, CriterionDocument> {}
