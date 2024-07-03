package com.keylin.WeCare.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.keylin.WeCare.entities.Nanny;
import com.keylin.WeCare.services.NannyService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
    Logger log = LoggerFactory.getLogger(LoginController.class);
    // Logger

    // Injection of the service interface
    @Autowired
    private NannyService nannyService;

    // Business logic: services that the nanny will need or we will need from the
    // nanny
    /* Page of nanny list */

    @GetMapping("/login")
    public String login() {
        log.info("login");

        return "login";
    }

    @GetMapping("/loggedin")
    public String showProfile(Model model, HttpSession session) {
        log.info("Staring the process to show the profile of the actual user:");
        Nanny user = (Nanny) session.getAttribute("user");
        log.info("Nanny details:" + user.toString());
        user = nannyService.findByNannyId(user.getId());
        model.addAttribute("user", user);
        return "indexLoggedIn";
    }
}
