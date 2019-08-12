package community.mother.domain.account.service;

import community.mother.domain.account.domain.Account;
import community.mother.domain.account.domain.AccountRepository;
import community.mother.domain.account.dto.response.AccountDetail;
import community.mother.domain.account.dto.response.AccountListResponse;
import community.mother.domain.account.exception.AccountNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FollowService {
  private final AccountRepository accountRepository;

  public AccountListResponse getFollows(String nickname) {
    Account account = findAccountByNickname(nickname);
    return new AccountListResponse(account.getFollowingAccounts().stream()
        .map(followingAccount -> new AccountDetail(followingAccount.getEmail(), followingAccount.getNickname()))
        .collect(Collectors.toList()));
  }

  public AccountListResponse getFollowed(String nickname) {
    Account account = findAccountByNickname(nickname);
    return new AccountListResponse(account.getFollowedAccounts().stream()
        .map(followedAccount -> new AccountDetail(followedAccount.getEmail(), followedAccount.getNickname()))
        .collect(Collectors.toList()));
  }

  public void createFollow(String followedNickname, Long followerId) {
    Account follower = findAccountById(followerId);
    Account followed = findAccountByNickname(followedNickname);
    follower.addFollowingAccount(followed);
  }

  public void deleteFollow(String followedNickname, Long followerId) {
    Account follower = findAccountById(followerId);
    Account followed = findAccountByNickname(followedNickname);
    follower.deleteFollowingAccount(followed);
  }

  private Account findAccountByNickname(String nickname) {
    Account account = accountRepository.findByNickname(nickname).orElseThrow(AccountNotFoundException::new);

    if(account.isDeleted()) {
      throw new AccountNotFoundException();
    }

    return account;
  }

  private Account findAccountById(Long id) {
    Account account = accountRepository.findById(id).orElseThrow(AccountNotFoundException::new);

    if(account.isDeleted()) {
      throw new AccountNotFoundException();
    }

    return account;
  }
}
