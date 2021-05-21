package serviceImpl;

import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import repository.UserRepository;
import service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(@Qualifier("Mybatis")UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(Long id){
        return userRepository.getUserById(id);
    }

    public List<User> getUsers(){ return userRepository.getUsers(); }

    public void save(User user){ userRepository.insertUser(user); }

    public void deleteById(Long id) { userRepository.deleteById(id); }

    public void updateUser(User user){ userRepository.updateUser(user); }

}
