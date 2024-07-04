package com.keylin.WeCare.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.keylin.WeCare.entities.Booking;
import com.keylin.WeCare.entities.Family;
import com.keylin.WeCare.entities.Nanny;
import com.keylin.WeCare.services.BookingService;
import com.keylin.WeCare.services.FamilyService;
import com.keylin.WeCare.services.NannyService;

import jakarta.servlet.http.HttpSession;

@Controller
public class BookingController {
    // Loggers
    Logger log = LoggerFactory.getLogger(BookingController.class);

    // 'Injections' needed
    @Autowired
    private BookingService bookingService;

    @Autowired
    private NannyService nannyService;

    @Autowired
    private FamilyService familyService;

    // Business logic

    // show the bookings for the n user
    @GetMapping("/n/bookings")
    public String getBookingN(HttpSession session, Long idNanny, Model model) {
        Nanny user = (Nanny) session.getAttribute("user");
        user = nannyService.findByNannyId(user.getId());
        idNanny = user.getId();
        List<Booking> booking = bookingService.getBookingByNannyId(idNanny);
        model.addAttribute("booking", booking);
        log.info("show the bookings for the n user");
        return "bookings";
    }

    // show the bookings for the f user
    @GetMapping("/f/bookings")
    public String getBookingF(HttpSession session, Long idFamily, Model model) {
        Family user = (Family) session.getAttribute("user");
        user = familyService.findByFamilyId(user.getId());
        idFamily = user.getId();
        List<Booking> booking = bookingService.getBookingByFamilyId(idFamily);
        model.addAttribute("booking", booking);
        log.info("show the bookings for the n user");
        return "bookings";
    }

    // creating the booking from an n user
    @GetMapping("/n/{idFamily}/requestbooking")
    public String createBookingFromN(HttpSession session, Long idNanny, @PathVariable("idFamily") Long idFamily,
            Booking booking, Model model) {
        log.info("Booking:" + booking.toString());
        Nanny user = (Nanny) session.getAttribute("user");
        user = nannyService.findByNannyId(user.getId());
        idNanny = user.getId();

        try {
            bookingService.createBookingFromN(booking, idNanny, idFamily);
            model.addAttribute("booking", booking);
        } catch (Exception e) {
            log.error("Error while trying to create the booking", e);
            model.addAttribute("error", "Error while trying request the match, please try again.");
            return "profileFamily";
        }
        return "bookings";
    }

    // creating the booking from an n user
    @GetMapping("/f/{idNanny}/requestbooking")
    public String createBookingFromF(HttpSession session, Long idFamily, @PathVariable("idNanny") Long idNanny,
            Booking booking, Model model) {
        log.info("Booking:" + booking.toString());
        Family user = (Family) session.getAttribute("user");
        user = familyService.findByFamilyId(user.getId());
        idFamily = user.getId();

        try {
            bookingService.createBookingFromF(booking, idNanny, idFamily);
            model.addAttribute("booking", booking);
        } catch (Exception e) {
            log.error("Error while trying to create the booking", e);
            model.addAttribute("error", "Error while trying request the match, please try again.");
            return "profileNanny";
        }
        return "bookings";
    }

}
