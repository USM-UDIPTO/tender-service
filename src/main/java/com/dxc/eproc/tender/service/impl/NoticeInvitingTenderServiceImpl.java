package com.dxc.eproc.tender.service.impl;

import com.dxc.eproc.tender.domain.NoticeInvitingTender;
import com.dxc.eproc.tender.repository.NoticeInvitingTenderRepository;
import com.dxc.eproc.tender.service.NoticeInvitingTenderService;
import com.dxc.eproc.tender.service.dto.NoticeInvitingTenderDTO;
import com.dxc.eproc.tender.service.mapper.NoticeInvitingTenderMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link NoticeInvitingTender}.
 */
@Service
@Transactional
public class NoticeInvitingTenderServiceImpl implements NoticeInvitingTenderService {

    private final Logger log = LoggerFactory.getLogger(NoticeInvitingTenderServiceImpl.class);

    private final NoticeInvitingTenderRepository noticeInvitingTenderRepository;

    private final NoticeInvitingTenderMapper noticeInvitingTenderMapper;

    public NoticeInvitingTenderServiceImpl(
        NoticeInvitingTenderRepository noticeInvitingTenderRepository,
        NoticeInvitingTenderMapper noticeInvitingTenderMapper
    ) {
        this.noticeInvitingTenderRepository = noticeInvitingTenderRepository;
        this.noticeInvitingTenderMapper = noticeInvitingTenderMapper;
    }

    @Override
    public NoticeInvitingTenderDTO save(NoticeInvitingTenderDTO noticeInvitingTenderDTO) {
        log.debug("Request to save NoticeInvitingTender : {}", noticeInvitingTenderDTO);
        NoticeInvitingTender noticeInvitingTender = noticeInvitingTenderMapper.toEntity(noticeInvitingTenderDTO);
        noticeInvitingTender = noticeInvitingTenderRepository.save(noticeInvitingTender);
        return noticeInvitingTenderMapper.toDto(noticeInvitingTender);
    }

    @Override
    public Optional<NoticeInvitingTenderDTO> partialUpdate(NoticeInvitingTenderDTO noticeInvitingTenderDTO) {
        log.debug("Request to partially update NoticeInvitingTender : {}", noticeInvitingTenderDTO);

        return noticeInvitingTenderRepository
            .findById(noticeInvitingTenderDTO.getId())
            .map(
                existingNoticeInvitingTender -> {
                    noticeInvitingTenderMapper.partialUpdate(existingNoticeInvitingTender, noticeInvitingTenderDTO);

                    return existingNoticeInvitingTender;
                }
            )
            .map(noticeInvitingTenderRepository::save)
            .map(noticeInvitingTenderMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<NoticeInvitingTenderDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NoticeInvitingTenders");
        return noticeInvitingTenderRepository.findAll(pageable).map(noticeInvitingTenderMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<NoticeInvitingTenderDTO> findOne(Long id) {
        log.debug("Request to get NoticeInvitingTender : {}", id);
        return noticeInvitingTenderRepository.findById(id).map(noticeInvitingTenderMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete NoticeInvitingTender : {}", id);
        noticeInvitingTenderRepository.deleteById(id);
    }
}
