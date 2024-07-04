package com.keylin.WeCare.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.keylin.WeCare.entities.Family;
import com.keylin.WeCare.entities.Nanny;
import com.keylin.WeCare.services.FamilyService;
import com.keylin.WeCare.services.NannyService;
import com.keylin.WeCare.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class EditProfileController {
    // Loggers
    Logger log = LoggerFactory.getLogger(EditProfileController.class);

    // 'Injections' needed
    @Autowired
    private UserService userService;
    @Autowired
    private FamilyService familyService;
    @Autowired
    private NannyService nannyService;

    // Business logic

    // Get the edit profile model nanny/family

    @GetMapping("/n/edit")
    public String getProfileN(Model model) {
        log.info("Accessing to the model to edit the profile");
        return "myProfileModal";
    }

    @GetMapping("/f/edit")
    public String getProfileF(Model model) {
        log.info("Accessing to the model to edit the profile");
        return "myProfileModal";
    }

    // Update

    // Edit the profile role f

    @PostMapping("/f/edit")
    public String editFamily(HttpSession session, Family family, Model model) {
        log.info("user:" + family.toString());
        Family user = (Family) session.getAttribute("user");
        user = familyService.findByFamilyId(user.getId());
        try {
            // Edit user using the service
            userService.editUserF(user.getId(), family);
            model.addAttribute("message", "User successfully edited");
        } catch (Exception e) {
            log.error("Error while trying to edit", e);
            model.addAttribute("error", "Error while trying to edit your profile, please try again.");
            return "myProfile";
        }
        return "myProfile";
    }

    // Edit the profile role n

    @PostMapping("/n/edit")
    public String editNanny(HttpSession session, Nanny nanny, Model model) {
        log.info("user:" + nanny.toString());
        Nanny user = (Nanny) session.getAttribute("user");
        user = nannyService.findByNannyId(user.getId());
        try {
            // Edit user using the service
            userService.editUserN(user.getId(), nanny);
            model.addAttribute("message", "User successfully edited");
        } catch (Exception e) {
            log.error("Error while trying to edit", e);
            model.addAttribute("error", "Error while trying to edit your profile, please try again.");
            return "myProfile";
        }
        return "myProfile";
    }
}
