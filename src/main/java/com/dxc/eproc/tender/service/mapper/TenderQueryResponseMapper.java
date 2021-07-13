package com.dxc.eproc.tender.service.mapper;

import com.dxc.eproc.tender.domain.*;
import com.dxc.eproc.tender.service.dto.TenderQueryResponseDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TenderQueryResponse} and its DTO {@link TenderQueryResponseDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TenderQueryResponseMapper extends EntityMapper<TenderQueryResponseDTO, TenderQueryResponse> {}
