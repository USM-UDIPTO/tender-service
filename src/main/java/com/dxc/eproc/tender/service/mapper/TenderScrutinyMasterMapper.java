package com.dxc.eproc.tender.service.mapper;

import com.dxc.eproc.tender.domain.*;
import com.dxc.eproc.tender.service.dto.TenderScrutinyMasterDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TenderScrutinyMaster} and its DTO {@link TenderScrutinyMasterDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TenderScrutinyMasterMapper extends EntityMapper<TenderScrutinyMasterDTO, TenderScrutinyMaster> {}
