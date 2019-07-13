package community.mother.notice.service;

import community.mother.notice.domain.Notice;
import community.mother.notice.domain.NoticeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static community.mother.notice.domain.NoticeTest.getNoticeFixture;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class NoticeServiceTest {
	private NoticeService noticeService;

	private @Mock NoticeRepository noticeRepository;

	@BeforeEach
	void setup() {
		noticeService = new NoticeService(noticeRepository);
	}

	@Test
	void createNotice_ValidInput_CreatedNotice() throws Exception {
		// given
		Notice noticeToSave = getNoticeFixture();
		given(noticeRepository.save(any(Notice.class))).willReturn(noticeToSave);

		// when
		Notice savedNotice = noticeService.createNotice(noticeToSave);

		then(savedNotice).isEqualTo(noticeToSave);
	}
}
