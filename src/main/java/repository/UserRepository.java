package repository;



import domain.User;

import java.util.List;


public interface UserRepository {

    List<User> getUsers();

    void insertUser(User user);

    User getUserById(Long id);

    void deleteById(Long id);

    void updateUser(User user);

}
