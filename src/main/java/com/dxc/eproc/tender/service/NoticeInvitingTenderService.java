package com.dxc.eproc.tender.service;

import com.dxc.eproc.tender.service.dto.NoticeInvitingTenderDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.dxc.eproc.tender.domain.NoticeInvitingTender}.
 */
public interface NoticeInvitingTenderService {
    /**
     * Save a noticeInvitingTender.
     *
     * @param noticeInvitingTenderDTO the entity to save.
     * @return the persisted entity.
     */
    NoticeInvitingTenderDTO save(NoticeInvitingTenderDTO noticeInvitingTenderDTO);

    /**
     * Partially updates a noticeInvitingTender.
     *
     * @param noticeInvitingTenderDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<NoticeInvitingTenderDTO> partialUpdate(NoticeInvitingTenderDTO noticeInvitingTenderDTO);

    /**
     * Get all the noticeInvitingTenders.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<NoticeInvitingTenderDTO> findAll(Pageable pageable);

    /**
     * Get the "id" noticeInvitingTender.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<NoticeInvitingTenderDTO> findOne(Long id);

    /**
     * Delete the "id" noticeInvitingTender.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
