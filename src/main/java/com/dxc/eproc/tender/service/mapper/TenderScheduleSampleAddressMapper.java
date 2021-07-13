package com.dxc.eproc.tender.service.mapper;

import com.dxc.eproc.tender.domain.*;
import com.dxc.eproc.tender.service.dto.TenderScheduleSampleAddressDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TenderScheduleSampleAddress} and its DTO {@link TenderScheduleSampleAddressDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TenderScheduleSampleAddressMapper extends EntityMapper<TenderScheduleSampleAddressDTO, TenderScheduleSampleAddress> {}
