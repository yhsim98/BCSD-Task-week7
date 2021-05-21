package repository;

import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("Mybatis")
public class UserRepositoryMybatis implements UserRepository{

    private final UserMapper userMapper;

    @Autowired
    public UserRepositoryMybatis(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    @Override
    public List<User> getUsers() {
        List<User> users = userMapper.getUsers();
        return users;
    }

    @Override
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    @Override
    public User getUserById(Long id) {
        return userMapper.getUserById(id);
    }

    @Override
    public void deleteById(Long id) {
        userMapper.deleteById(id);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

}
