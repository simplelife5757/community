package community.mother.domain.post.controller;

import community.mother.domain.account.domain.CurrentUser;
import community.mother.domain.post.dto.request.SavePostParams;
import community.mother.domain.post.dto.request.UpdatePostParams;
import community.mother.domain.post.dto.response.PostResponse;
import community.mother.domain.post.service.PostService;
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
	public PostResponse readPost(@PathVariable Long id) {
		return postService.readPost(id);
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Long create(@RequestBody SavePostParams savePostParams, @CurrentUser Long accountId) {
		return postService.createPost(savePostParams, accountId);
	}

	@PostMapping("/{id}")
	public void update(@PathVariable Long id, @RequestBody UpdatePostParams updatePostParams, @CurrentUser Long accountId) {
		postService.updatePost(id, updatePostParams, accountId);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id, @CurrentUser Long accountId) {
		postService.deletePost(id, accountId);
	}
}
