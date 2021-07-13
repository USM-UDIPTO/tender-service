package com.dxc.eproc.tender.service.mapper;

import com.dxc.eproc.tender.domain.*;
import com.dxc.eproc.tender.service.dto.TenderScheduleGroupItemsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TenderScheduleGroupItems} and its DTO {@link TenderScheduleGroupItemsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TenderScheduleGroupItemsMapper extends EntityMapper<TenderScheduleGroupItemsDTO, TenderScheduleGroupItems> {}
