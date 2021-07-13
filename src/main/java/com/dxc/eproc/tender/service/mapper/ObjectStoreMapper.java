package com.dxc.eproc.tender.service.mapper;

import com.dxc.eproc.tender.domain.*;
import com.dxc.eproc.tender.service.dto.ObjectStoreDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ObjectStore} and its DTO {@link ObjectStoreDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ObjectStoreMapper extends EntityMapper<ObjectStoreDTO, ObjectStore> {}
