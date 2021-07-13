package com.dxc.eproc.tender.service.mapper;

import com.dxc.eproc.tender.domain.*;
import com.dxc.eproc.tender.service.dto.TenderScheduleDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TenderSchedule} and its DTO {@link TenderScheduleDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TenderScheduleMapper extends EntityMapper<TenderScheduleDTO, TenderSchedule> {}
