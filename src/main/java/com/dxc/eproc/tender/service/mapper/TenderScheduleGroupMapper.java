package com.dxc.eproc.tender.service.mapper;

import com.dxc.eproc.tender.domain.*;
import com.dxc.eproc.tender.service.dto.TenderScheduleGroupDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TenderScheduleGroup} and its DTO {@link TenderScheduleGroupDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TenderScheduleGroupMapper extends EntityMapper<TenderScheduleGroupDTO, TenderScheduleGroup> {}
