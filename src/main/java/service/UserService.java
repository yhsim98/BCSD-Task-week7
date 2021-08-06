package service;

import domain.User;

public interface UserService {
    String userLogin(User user) throws Exception;
    String test();
    void insertUser(User user) throws Exception;
    User getUserInfo() throws Exception;
}
