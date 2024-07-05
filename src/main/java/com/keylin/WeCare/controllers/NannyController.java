package com.keylin.WeCare.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.keylin.WeCare.dto.NannyDto;
import com.keylin.WeCare.entities.Nanny;
import com.keylin.WeCare.services.NannyService;

import jakarta.servlet.http.HttpSession;

@Controller
// @RequestMapping("/n/")
public class NannyController {
    // Logger

    Logger log = LoggerFactory.getLogger(NannyController.class);

    // Injection of the service interface
    @Autowired
    private NannyService nannyService;

    // Business logic: services that the nanny will need or we will need from the
    // nanny
    /* Page of nanny list */

    @GetMapping("/f/nannyList")
    public String listNannies(Model model) {
        List<NannyDto> nannies = nannyService.findAllNannies();
        log.info("Getting the list of nannies in the section");
        model.addAttribute("nannies", nannies);
        return "nannyList";
    }
    /* Show the profile when click in "View more" button in the nanny list */

    @GetMapping("/n/myprofile")
    public String showProfile(Model model, HttpSession session) {
        log.info("Staring the process to show the profile of the actual user:");
        Nanny user = (Nanny) session.getAttribute("user");
        log.info("Nanny details:" + user.toString());
        user = nannyService.findByNannyId(user.getId());
        model.addAttribute("user", user);
        return "myProfile";
    }

    /* Go to the profile when click in the nanny list */
    @GetMapping("/f/{id}/nprofile")
    public String showProfile(@PathVariable("id") Long id, Model model) {
        log.info("Staring the process to show the profile of the nanny when click on View more:");
        Nanny nanny = nannyService.findByNannyId(id);
        model.addAttribute("nanny", nanny);
        return "profileNanny";
    }
}
