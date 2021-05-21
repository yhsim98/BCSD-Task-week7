package service;


import domain.User;

import java.util.List;


public interface UserService {
    User findById(Long id);

    List<User> getUsers();

    void save(User userInsertDto);

    void deleteById(Long id);

    void updateUser(User userUpdateDto);

}
