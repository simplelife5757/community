package community.mother.notice.service;

import community.mother.notice.domain.Notice;
import community.mother.notice.domain.NoticeRepository;
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
