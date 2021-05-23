package repository;

import domain.User;
import mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryMybatis implements UserRepository{

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByEmail(String email) {
        return userMapper.getUserByEmail(email);
    }

    @Override
    public String test(){ return userMapper.test(); }

    @Override
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    @Override
    public User getUserById(Long id) {
        return userMapper.getUserById(id);
    }
}