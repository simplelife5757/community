package community.mother.domain.post.service;

import community.mother.domain.account.domain.Account;
import community.mother.domain.account.domain.AccountRepository;
import community.mother.domain.account.exception.AccountNotFoundException;
import community.mother.domain.account.exception.UnAuthorizedException;
import community.mother.domain.account.service.AccountService;
import community.mother.domain.post.domain.Post;
import community.mother.domain.post.domain.PostRepository;
import community.mother.domain.post.dto.request.SavePostParams;
import community.mother.domain.post.dto.request.UpdatePostParams;
import community.mother.domain.post.dto.response.PostResponse;
import community.mother.domain.post.exception.PostNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {
	private final PostRepository postRepository;
	private final AccountRepository accountRepository;

	public PostResponse readPost(Long id) {
		Post post = postRepository.getOne(id);
		return PostResponse.of(post);
	}

	public Long createPost(SavePostParams savePostParams, Long accountId) {
		Account account = findAccountById(accountId);

		Post post = Post.builder()
				.title(savePostParams.getTitle())
				.content(savePostParams.getContent())
				.writer(account)
				.originalFileName(savePostParams.getOriginalFileName()).build();
		account.addPost(post);
		return postRepository.save(post).getId();
	}

	private Post findPostById(Long id) {
		return postRepository.findById(id).orElseThrow(PostNotFoundException::new);
	}

	public void updatePost(Long postId, UpdatePostParams updatePostParams, Long accountId) {
		Post post = findPostById(postId);
		Account account = findAccountById(accountId);

		if (!post.isWriter(account)) {
			throw new UnAuthorizedException();
		}

		account.updatePost(postId, updatePostParams);
		post.update(updatePostParams.getContent());
		postRepository.save(post);
	}

	public void deletePost(Long id, Long accountId) {
		Post post = findPostById(id);
		Account account = findAccountById(accountId);

		if (!post.isWriter(account)) {
			throw new UnAuthorizedException();
		}

		account.deletePost(post);
		postRepository.delete(post);
	}

	private Account findAccountById(Long id) {
		Account account = accountRepository.findById(id).orElseThrow(AccountNotFoundException::new);

		if(account.isDeleted()) {
			throw new AccountNotFoundException();
		}

		return account;
	}
}
