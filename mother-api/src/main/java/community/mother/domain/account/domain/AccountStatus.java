package community.mother.domain.account.domain;

public enum AccountStatus {
	CREATED("created"),
	DELETED("deleted");

	private String userStatus;

	AccountStatus(String userStatus) {
		this.userStatus = userStatus;
	}
}
