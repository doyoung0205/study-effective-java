package chapter10.item75;

import java.util.List;
import java.util.Optional;

/**
 * author : haedoang
 * date : 2022/06/25
 * description :
 */
public class UserService {
    private List<User> users;

    public UserService(List<User> userList) {
        this.users = userList;
    }

    public Optional<User> findById(Long id) {
        return this.users.stream()
                .filter(it -> it.getId().equals(id))
                .findFirst();
    }
}
