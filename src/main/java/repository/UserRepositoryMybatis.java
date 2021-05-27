package repository;

import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("myBatis")
public class UserRepositoryMybatis implements UserRepository{

    private final UserMapper userMapper;

    @Autowired
    public UserRepositoryMybatis(UserMapper userMapper){
        this.userMapper = userMapper;
    }

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