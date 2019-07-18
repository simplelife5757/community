package community.mother.domain.notice.service;

import community.mother.domain.notice.domain.Notice;
import community.mother.domain.notice.domain.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeService {
	private final NoticeRepository noticeRepository;

	public Notice createNotice(Notice notice) {
		return noticeRepository.save(notice);
	}
}
