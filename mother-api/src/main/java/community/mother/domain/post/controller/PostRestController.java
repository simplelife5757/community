package community.mother.domain.post.controller;

import community.mother.domain.account.domain.Account;
import community.mother.domain.account.domain.CurrentUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/posts")
@RequiredArgsConstructor
public class PostRestController {
	private final PostService postService;

	@GetMapping("/{id}")
	public PostResponse getPost(@PathVariable Long id) {
		return postService.getPost(id);
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Long create(@RequestBody SavePostParams savePostParams, @CurrentUser Account account) {
		return postService.createPost(savePostParams, account);
	}

	@PostMapping("/{id}")
	public void update(@PathVariable Long id, @RequestBody UpdatePostParams updatePostParams, @CurrentUser Account account) {
		return postService.updatePost(id, updatePostParams, account);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id, @CurrentUser Account account) {
		return postService.deletePost(id, account);
	}
}
