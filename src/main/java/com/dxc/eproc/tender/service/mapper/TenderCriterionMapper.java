package com.dxc.eproc.tender.service.mapper;

import com.dxc.eproc.tender.domain.*;
import com.dxc.eproc.tender.service.dto.TenderCriterionDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TenderCriterion} and its DTO {@link TenderCriterionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TenderCriterionMapper extends EntityMapper<TenderCriterionDTO, TenderCriterion> {}
