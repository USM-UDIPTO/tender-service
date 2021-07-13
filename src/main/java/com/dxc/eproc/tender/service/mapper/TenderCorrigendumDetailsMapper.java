package com.dxc.eproc.tender.service.mapper;

import com.dxc.eproc.tender.domain.*;
import com.dxc.eproc.tender.service.dto.TenderCorrigendumDetailsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TenderCorrigendumDetails} and its DTO {@link TenderCorrigendumDetailsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TenderCorrigendumDetailsMapper extends EntityMapper<TenderCorrigendumDetailsDTO, TenderCorrigendumDetails> {}
