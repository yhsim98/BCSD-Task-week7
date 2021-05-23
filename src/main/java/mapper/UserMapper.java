package mapper;

import domain.User;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper {
    String test();
    User getUserByEmail(String email);
    void insertUser(User user);
    User getUserById(Long id);
}
