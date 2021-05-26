package controller;

import annotation.Auth;
import domain.AuthenticationResponse;
import domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.UserService;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value="/user")
public class TestController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;
    @Value("${json.web.token.secret.key}")
    String secret;

    // 토큰이 없어도 사용가능한 회원가입 api ( 비로그인 )
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity join(@RequestBody User user){
        if(userService.insertUser(user)) return new ResponseEntity("success", HttpStatus.OK);
        else return new ResponseEntity("that email already being used", HttpStatus.OK);
    }

    // 토큰이 있어야만 사용가능한 조회 api ( 로그인 )
    @Auth
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity userInquiry(@RequestHeader(value="Authorization") String token){
        Claims claims = Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token.substring(7)).getBody();
        return new ResponseEntity(userService.getUserInfo(Long.parseLong(String.valueOf(claims.get("id")))), HttpStatus.OK);
    }

    // 토큰을 발급하는 로그인 api
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResponseEntity login(@RequestBody User user) throws Exception {
        if(user == null) throw new Exception("입력 오류");

        User tmp = null;
        tmp = userService.userLogin(user);

        if (tmp == null) return new ResponseEntity("login fail", HttpStatus.OK);
        else return new ResponseEntity(new AuthenticationResponse(jwtUtil.genJsonWebToken(tmp.getId())), HttpStatus.OK);
    }
}
