package com.dxc.eproc.tender.service.mapper;

import com.dxc.eproc.tender.domain.*;
import com.dxc.eproc.tender.service.dto.TenderScheduleSampleDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TenderScheduleSample} and its DTO {@link TenderScheduleSampleDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TenderScheduleSampleMapper extends EntityMapper<TenderScheduleSampleDTO, TenderScheduleSample> {}
