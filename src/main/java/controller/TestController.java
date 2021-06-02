package controller;

import annotation.Auth;
import domain.AuthenticationResponse;
import domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.UserService;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
@RequestMapping(value="/user")
public class TestController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;


    // 토큰이 없어도 사용가능한 회원가입 api ( 비로그인 )
    @ApiOperation(value="사용자 생성", notes = "신규 사용자 생성")
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody User user){
        try{ //TODO 예외 자동으로 처리되게 만들기
            userService.insertUser(user);
        } catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }
        return new ResponseEntity("success", HttpStatus.CREATED);
    }

    // 토큰이 있어야만 사용가능한 조회 api ( 로그인 )
    @ApiOperation(value="사용자 정보 조회", notes="토큰의 id를 사용하여 사용자 정보 조회")
    @Auth
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity userInquiry(@RequestHeader(value="Authorization") String token){
        User user;
        try{ //TODO 예외 자동으로 처리되게 만들기
            user = userService.getUserInfo(token);
        } catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(user, HttpStatus.OK);
    }

    // 토큰을 발급하는 로그인 api
    @ApiOperation(value="사용자 로그인", notes="사용자 정보를 받고 토큰을 반환")
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody User user) {
        String token;
        try { //TODO 예외 자동으로 처리되게 만들기
            token = userService.userLogin(user);
        } catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(new AuthenticationResponse(token), HttpStatus.OK);
    }
}
