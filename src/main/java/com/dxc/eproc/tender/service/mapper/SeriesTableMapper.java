package com.dxc.eproc.tender.service.mapper;

import com.dxc.eproc.tender.domain.*;
import com.dxc.eproc.tender.service.dto.SeriesTableDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SeriesTable} and its DTO {@link SeriesTableDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SeriesTableMapper extends EntityMapper<SeriesTableDTO, SeriesTable> {}
