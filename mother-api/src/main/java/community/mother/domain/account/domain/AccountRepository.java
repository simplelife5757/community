package community.mother.domain.account.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
	Optional<Account> findByEmail(String email);

	Optional<Account> findByUsername(String username);

  Optional<Account> findByNickname(String nickname);
}
