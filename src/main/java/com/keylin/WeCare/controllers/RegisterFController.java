package com.keylin.WeCare.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.keylin.WeCare.entities.Family;
import com.keylin.WeCare.entities.Users;
import com.keylin.WeCare.services.UserService;

@Controller
@RequestMapping("/register/f")
public class RegisterFController {
    // Loggers
    Logger log = LoggerFactory.getLogger(RegisterNController.class);

    // 'Injections' needed
    @Autowired
    private UserService userService;

    // Method Controller
    @GetMapping
    public String getRegisterFForm(Model model) {
        log.info("Accessing to the register page");
        return "registerFamily";
    }

    @PostMapping
    public String registerFamily(Users user, Family family, Model model) {
        log.info("Register user");
        log.info("user:" + user.toString());
        log.info("family:" + family.toString());
        try {
            // Registrar el usuario usando el servicio
            userService.registerUserF(user, family);
            model.addAttribute("message", "User successfully registered");
        } catch (Exception e) {
            log.error("Error while trying to register", e);
            model.addAttribute("error", "Error while trying to register, please try again.");
            return "registerFamily";
        }
        return "login";
    }
}
