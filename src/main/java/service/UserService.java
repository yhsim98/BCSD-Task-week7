package service;

import domain.User;

public interface UserService {
    User userLogin(User user);
    String test();
    boolean insertUser(User user);
    User getUserInfo(Long id);
}
