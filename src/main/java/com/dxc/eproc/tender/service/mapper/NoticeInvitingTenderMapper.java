package com.dxc.eproc.tender.service.mapper;

import com.dxc.eproc.tender.domain.*;
import com.dxc.eproc.tender.service.dto.NoticeInvitingTenderDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link NoticeInvitingTender} and its DTO {@link NoticeInvitingTenderDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface NoticeInvitingTenderMapper extends EntityMapper<NoticeInvitingTenderDTO, NoticeInvitingTender> {}
