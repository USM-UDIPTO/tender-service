package com.dxc.eproc.tender.service.mapper;

import com.dxc.eproc.tender.domain.*;
import com.dxc.eproc.tender.service.dto.TenderScheduleTelephoneDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TenderScheduleTelephone} and its DTO {@link TenderScheduleTelephoneDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TenderScheduleTelephoneMapper extends EntityMapper<TenderScheduleTelephoneDTO, TenderScheduleTelephone> {}
