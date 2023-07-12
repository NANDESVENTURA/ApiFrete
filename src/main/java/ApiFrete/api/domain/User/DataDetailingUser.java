package ApiFrete.api.domain.User;

import ApiFrete.api.domain.User.User;

public record DataDetailingUser(Long id, String name, String email) {

    public DataDetailingUser(User user) {
        this(user.getId(), user.getName(), user.getEmail());
    }
}
