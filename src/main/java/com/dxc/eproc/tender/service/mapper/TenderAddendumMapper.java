package com.dxc.eproc.tender.service.mapper;

import com.dxc.eproc.tender.domain.*;
import com.dxc.eproc.tender.service.dto.TenderAddendumDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TenderAddendum} and its DTO {@link TenderAddendumDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TenderAddendumMapper extends EntityMapper<TenderAddendumDTO, TenderAddendum> {}
