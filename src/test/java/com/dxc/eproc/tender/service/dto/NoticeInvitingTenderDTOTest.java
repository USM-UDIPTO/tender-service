package com.dxc.eproc.tender.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.tender.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class NoticeInvitingTenderDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NoticeInvitingTenderDTO.class);
        NoticeInvitingTenderDTO noticeInvitingTenderDTO1 = new NoticeInvitingTenderDTO();
        noticeInvitingTenderDTO1.setId(1L);
        NoticeInvitingTenderDTO noticeInvitingTenderDTO2 = new NoticeInvitingTenderDTO();
        assertThat(noticeInvitingTenderDTO1).isNotEqualTo(noticeInvitingTenderDTO2);
        noticeInvitingTenderDTO2.setId(noticeInvitingTenderDTO1.getId());
        assertThat(noticeInvitingTenderDTO1).isEqualTo(noticeInvitingTenderDTO2);
        noticeInvitingTenderDTO2.setId(2L);
        assertThat(noticeInvitingTenderDTO1).isNotEqualTo(noticeInvitingTenderDTO2);
        noticeInvitingTenderDTO1.setId(null);
        assertThat(noticeInvitingTenderDTO1).isNotEqualTo(noticeInvitingTenderDTO2);
    }
}
