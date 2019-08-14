package community.mother.domain.account.security.user;

import community.mother.domain.account.security.social.userconnection.UserConnection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  User findBySocial(UserConnection userConnection);

}