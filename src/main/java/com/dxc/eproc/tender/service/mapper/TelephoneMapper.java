package com.dxc.eproc.tender.service.mapper;

import com.dxc.eproc.tender.domain.*;
import com.dxc.eproc.tender.service.dto.TelephoneDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Telephone} and its DTO {@link TelephoneDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TelephoneMapper extends EntityMapper<TelephoneDTO, Telephone> {}
