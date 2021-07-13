package com.dxc.eproc.tender.service.mapper;

import com.dxc.eproc.tender.domain.*;
import com.dxc.eproc.tender.service.dto.TenderGoodsItemsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TenderGoodsItems} and its DTO {@link TenderGoodsItemsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TenderGoodsItemsMapper extends EntityMapper<TenderGoodsItemsDTO, TenderGoodsItems> {}
