package com.dxc.eproc.tender.service.mapper;

import com.dxc.eproc.tender.domain.*;
import com.dxc.eproc.tender.service.dto.TenderCorrigendumDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TenderCorrigendum} and its DTO {@link TenderCorrigendumDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TenderCorrigendumMapper extends EntityMapper<TenderCorrigendumDTO, TenderCorrigendum> {}
