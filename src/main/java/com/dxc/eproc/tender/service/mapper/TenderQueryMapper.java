package com.dxc.eproc.tender.service.mapper;

import com.dxc.eproc.tender.domain.*;
import com.dxc.eproc.tender.service.dto.TenderQueryDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TenderQuery} and its DTO {@link TenderQueryDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TenderQueryMapper extends EntityMapper<TenderQueryDTO, TenderQuery> {}
