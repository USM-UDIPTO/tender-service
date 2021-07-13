package com.dxc.eproc.tender.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.tender.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class NoticeInvitingTenderTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NoticeInvitingTender.class);
        NoticeInvitingTender noticeInvitingTender1 = new NoticeInvitingTender();
        noticeInvitingTender1.setId(1L);
        NoticeInvitingTender noticeInvitingTender2 = new NoticeInvitingTender();
        noticeInvitingTender2.setId(noticeInvitingTender1.getId());
        assertThat(noticeInvitingTender1).isEqualTo(noticeInvitingTender2);
        noticeInvitingTender2.setId(2L);
        assertThat(noticeInvitingTender1).isNotEqualTo(noticeInvitingTender2);
        noticeInvitingTender1.setId(null);
        assertThat(noticeInvitingTender1).isNotEqualTo(noticeInvitingTender2);
    }
}
