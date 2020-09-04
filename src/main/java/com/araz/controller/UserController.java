package com.araz.controller;

import com.araz.model.User;
import com.araz.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.Id;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String findAll(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user-list";
     }

     @GetMapping("/user-create")
     public String createUserFrom(User user){
        return "user-create";
     }

     @PostMapping("/user-create")
     public String createUser(User user){
        userService.saveUser(user);
        return "redirect:/users";
     }

     @GetMapping("/user-delete/{id}")
     public String deleteUser( @PathVariable ("id") Long id){
        userService.deleteById(id);
        return "redirect:/users";
     }

     @GetMapping("/user-update/{id?")
     public String updateUsers( @PathVariable ("id") Long id, Model model){
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "/user-update";
     }

     @PostMapping("user-update/{id}")
     public String uodateUser(User user){
        userService.saveUser(user);
        return "redirect:/users";
     }


}
