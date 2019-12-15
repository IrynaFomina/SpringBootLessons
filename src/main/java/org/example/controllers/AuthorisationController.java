package org.example.controllers;

import org.example.domain.Role;
import org.example.domain.User;
import org.example.repository.UserRepository;
import org.example.validation.AuthValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

@Controller
public class AuthorisationController {
@Autowired
private UserRepository userRepository;
    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@RequestParam (name = "username") String name, @RequestParam(name = "password") String password, Model model){
        if (!AuthValidation.isValidUserName(name)){
            model.addAttribute("errorMessage", "User name isn't valid");
        }
        else if (!AuthValidation.isValidPassword(password)){
            model.addAttribute("errorMessage", "Password isn't valid");
        }

        User userFromDB = userRepository.findByUsername(name);

        if (userFromDB != null) {
            model.addAttribute("message","User is already exist");
            return "/login";
        }

        User user = new User();
        user.setUsername(name);
        user.setPassword(password);

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        return "redirect:/login";
    }
}
