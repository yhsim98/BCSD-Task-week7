package controller;

import annotation.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import util.JwtUtil;

@Controller
public class TestController {


    @Autowired
    private JwtUtil jwtUtil;

    // 토큰이 없어도 사용가능한 api ( 비로그인 )
    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity test(){
        return new ResponseEntity("non login api", HttpStatus.OK);
    }

    // 토큰이 있어야만 사용가능한 api ( 로그인 )
    @Auth
    @ResponseBody
    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public ResponseEntity test2(){
        return new ResponseEntity("login api", HttpStatus.OK);
    }

    // 토큰을 발급하는 api
    @ResponseBody
    @RequestMapping(value = "/token", method = RequestMethod.GET)
    public ResponseEntity test3(){
        return new ResponseEntity(jwtUtil.genJsonWebToken(Long.valueOf(1)), HttpStatus.OK);
    }
    //
}
