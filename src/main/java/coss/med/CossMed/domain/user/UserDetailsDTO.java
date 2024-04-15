package coss.med.CossMed.domain.user;

public record UserDetailsDTO(Long id, String login) {

	public UserDetailsDTO(User user) {
		this(user.getId(), user.getLogin());
	}
}