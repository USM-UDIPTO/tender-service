package com.dxc.eproc.tender.service.mapper;

import com.dxc.eproc.tender.domain.*;
import com.dxc.eproc.tender.service.dto.CriterionDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Criterion} and its DTO {@link CriterionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CriterionMapper extends EntityMapper<CriterionDTO, Criterion> {}
