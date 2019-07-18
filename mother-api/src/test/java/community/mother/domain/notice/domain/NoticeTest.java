package community.mother.domain.notice.domain;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssertions.thenThrownBy;

public class NoticeTest {
	public static Notice getNoticeFixture() throws Exception {
		Notice notice = Notice.builder().title("title").content("content").build();
		Field idField = Notice.class.getDeclaredField("id");
		idField.setAccessible(true);
		idField.set(notice, 1L);

		return notice;
	}

	@Test
	void build_ValidInput_ValidOutput() {
		// given
		Notice notice = Notice.builder()
				.title("title")
				.content("content").build();

		// then
		then(notice)
				.hasNoNullFieldsOrPropertiesExcept("id")
				.hasFieldOrPropertyWithValue("title", "title")
				.hasFieldOrPropertyWithValue("content", "content");

		// or then
		then(notice.getTitle()).isEqualTo("title");
		then(notice.getContent()).isEqualTo("content");
	}

	@Test
	void build_EmptyTitle_ThrowException() {
		//given
		thenThrownBy(() ->
			Notice.builder()
					.title("")
					.content("content").build()
		).isInstanceOf(IllegalArgumentException.class);
	}
}
