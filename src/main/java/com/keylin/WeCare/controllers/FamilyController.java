package com.keylin.WeCare.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.keylin.WeCare.dto.FamilyDto;
import com.keylin.WeCare.entities.Family;
import com.keylin.WeCare.services.FamilyService;

import jakarta.servlet.http.HttpSession;

@Controller
public class FamilyController {
    // Logger

    Logger log = LoggerFactory.getLogger(FamilyController.class);

    // Injection of the service interface
    @Autowired
    private FamilyService familyService;
    // Business logic: services that the family will need or we will need from the
    // family
    /* Page of family list */

    @GetMapping("/n/familyList")
    public String listfamilys(Model model) {
        List<FamilyDto> families = familyService.findAllFamilies();
        log.info("Getting the list of families in the section");
        model.addAttribute("families", families);
        return "familyList";
    }

    /* Show the profile when click in "View more" button in the family list */

    @GetMapping("/f/myprofile")
    public String showProfile(Model model, HttpSession session) {
        log.info("Staring the process to show the profile of the actual user:");
        Family user = (Family) session.getAttribute("user");
        log.info("Family details:" + user.toString());
        user = familyService.findByFamilyId(user.getId());
        model.addAttribute("user", user);
        return "myProfile";
    }

    /* Go to the profile when click in the Family list */
    @GetMapping("/n/{id}/fprofile")
    public String showProfile(@PathVariable("id") Long id, Model model) {
        log.info("Staring the process to show the profile of the family when click on View more:");
        Family family = familyService.findByFamilyId(id);
        model.addAttribute("family", family);
        return "profileFamily";
    }
}
