package com.dxc.eproc.tender.service.mapper;

import com.dxc.eproc.tender.domain.*;
import com.dxc.eproc.tender.service.dto.TenderScrutinyCommitteeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TenderScrutinyCommittee} and its DTO {@link TenderScrutinyCommitteeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TenderScrutinyCommitteeMapper extends EntityMapper<TenderScrutinyCommitteeDTO, TenderScrutinyCommittee> {}
