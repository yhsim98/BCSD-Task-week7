package repository;

import domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    List<User> getUsers();

    void insertUser(User user);

    User getUserById(Long id);

    void deleteById(Long id);

    void updateUser(User user);
}
