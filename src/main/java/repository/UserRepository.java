package repository;

import domain.User;

public interface UserRepository {
    User getUserByEmail(String email);
    String test();
}
