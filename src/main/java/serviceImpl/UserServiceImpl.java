package serviceImpl;

import domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jdk.nashorn.internal.runtime.ECMAException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import repository.UserRepository;
import service.UserService;
import util.JwtUtil;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    @Value("${json.web.token.secret.key}")
    String secret;

    @Autowired
    public UserServiceImpl(@Qualifier("myBatis") UserRepository userRepository, JwtUtil jwtUtil){
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public String userLogin(User user) throws Exception {
        User selectUser = userRepository.getUserByEmail(user.getEmail());

        if(selectUser == null) {
            throw new Exception("account not exist");
        }

        if(!user.getPassWord().equals(selectUser.getPassWord())){
            throw new Exception("password not match");
        }

        return jwtUtil.genJsonWebToken(selectUser.getId());
    }

    @Override
    public String test() { return userRepository.test(); }

    @Override
    public void insertUser(User user) throws Exception {

        User selectUser = userRepository.getUserByEmail(user.getEmail());

        if(selectUser != null) {
            throw new Exception("email already being used");
        }

        userRepository.insertUser(user);
    }

    @Override
    public User getUserInfo(String token) throws Exception {

        Claims claims = Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token.substring(7)).getBody();
        User selectUser = userRepository.getUserById(Long.parseLong(String.valueOf(claims.get("id"))));

        if(selectUser == null){
            throw new Exception("account not exist");
        }

        return selectUser;
    }
}
