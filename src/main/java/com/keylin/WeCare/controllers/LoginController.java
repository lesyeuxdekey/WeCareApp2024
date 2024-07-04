package com.keylin.WeCare.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.keylin.WeCare.entities.Family;
import com.keylin.WeCare.entities.Nanny;
import com.keylin.WeCare.services.FamilyService;
import com.keylin.WeCare.services.NannyService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
    Logger log = LoggerFactory.getLogger(LoginController.class);
    // Logger

    // Injection of the service interface
    @Autowired
    private NannyService nannyService;
    // Injection of the service interface
    @Autowired
    private FamilyService familyService;

    // Business logic: services that the nanny will need or we will need from the
    // nanny
    /* Page of nanny list */

    @GetMapping("/")
    public String showIndex() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        log.info("login");

        return "login";
    }

    @GetMapping("/loggedinN")
    public String showHomePageN(Model model, HttpSession session) {
        log.info("Staring the process to show the homepage of the actual user:");
        Nanny user = (Nanny) session.getAttribute("user");
        log.info("Nanny details:" + user.toString());
        user = nannyService.findByNannyId(user.getId());
        model.addAttribute("user", user);
        return "indexLoggedIn";
    }

    @GetMapping("/loggedinF")
    public String showHomePageF(Model model, HttpSession session) {
        log.info("Staring the process to show the homepage of the actual user:");
        Family user = (Family) session.getAttribute("user");
        log.info("Family details:" + user.toString());
        user = familyService.findByFamilyId(user.getId());
        model.addAttribute("user", user);
        return "indexLoggedIn";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        log.info("Log out method starting");
        session.removeAttribute("user");
        session.invalidate();
        log.info("Invalidate session");
        return "index";
    }
}
