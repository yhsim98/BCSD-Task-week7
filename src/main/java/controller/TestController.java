package controller;

import annotation.Auth;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.UserService;
import util.JwtUtil;

@Controller
@RequestMapping(value="/user")
public class TestController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;

    // 토큰이 없어도 사용가능한 회원가입 api ( 비로그인 )
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity join(@RequestBody User user){
        if(userService.insertUser(user)) return new ResponseEntity("success", HttpStatus.OK);
        else return new ResponseEntity("fail", HttpStatus.OK);
    }

    // 토큰이 있어야만 사용가능한 조회 api ( 로그인 )
    @Auth
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity userInquiry(@RequestParam("Authorization") String auth){
        return new ResponseEntity("login api", HttpStatus.OK);
    }

    // 토큰을 발급하는 로그인 api
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResponseEntity login(@RequestBody User user) {
        User tmp = null;

        if (user != null)
            tmp = userService.userLogin(user);

        if (tmp == null) return new ResponseEntity("login fail", HttpStatus.OK);
        else return new ResponseEntity(jwtUtil.genJsonWebToken(Long.valueOf(tmp.getId())), HttpStatus.OK);
    }
}
