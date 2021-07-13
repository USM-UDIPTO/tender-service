package com.dxc.eproc.tender.service.mapper;

import com.dxc.eproc.tender.domain.*;
import com.dxc.eproc.tender.service.dto.TenderCriterionParameterDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TenderCriterionParameter} and its DTO {@link TenderCriterionParameterDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TenderCriterionParameterMapper extends EntityMapper<TenderCriterionParameterDTO, TenderCriterionParameter> {}
