package community.mother.account.domain;

public enum Status {
	CREATED("created"),
	DELETED("deleted");

	private String status;

	Status(String status) {
		this.status = status;
	}
}
