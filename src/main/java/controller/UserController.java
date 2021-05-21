package controller;

import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.UserService;

import java.util.List;

@Controller
@RequestMapping(value="/crud")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(value="/getUser", method = RequestMethod.GET)
    @ResponseBody
    public User findById(@RequestParam Long id){

        User users = userService.findById(id);

        return users;
    }


    @RequestMapping(value="", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getUsers(){

        List<User> userList = userService.getUsers();

        return userList;
    }

    @RequestMapping(value="", method = RequestMethod.POST)
    @ResponseBody
    public String insertUser(@RequestBody User user){

        if (user != null)
            userService.save(user);

        return "ok";
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteById(@PathVariable Long id){

        userService.deleteById(id);

        return "ok";
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public String updateUser(@RequestBody User user) {

        if (user != null)
            userService.updateUser(user);

        return "ok";
    }

}