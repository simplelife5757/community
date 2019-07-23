package community.mother.domain.account.domain;

import community.mother.domain.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
	Optional<Account> findByEmail(Email email);
}
