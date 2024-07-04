package com.keylin.WeCare.services.impl;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keylin.WeCare.entities.Booking;
import com.keylin.WeCare.entities.Family;
import com.keylin.WeCare.entities.Nanny;
import com.keylin.WeCare.repositories.BookingRepository;
import com.keylin.WeCare.repositories.FamilyRepository;
import com.keylin.WeCare.repositories.NannyRepository;
import com.keylin.WeCare.services.BookingService;

@Service
public class BookingServiceImpl implements BookingService {

    private static final Logger log = LoggerFactory.getLogger(BookingServiceImpl.class);

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private NannyRepository nannyRepository;

    @Autowired
    private FamilyRepository familyRepository;

    // Business logic

    public List<Booking> getBookingByNannyId(Long nannyId) {
        log.info("Fetching bookings for nanny with id: {}", nannyId);
        return bookingRepository.findByNannyId(nannyId);
    }

    public List<Booking> getBookingByFamilyId(Long familyId) {
        log.info("Fetching bookings for family with id: {}", familyId);
        return bookingRepository.findByFamilyId(familyId);
    }

    // Method of creating a booking from an f user when clicked on "Request match"
    // getting the nanny profile info and the user f info

    @Override
    public Booking createBookingFromF(Booking booking, Long idNanny, Long idFamily) {
        Nanny nanny = nannyRepository.findById(idNanny).get();
        log.info("Getting the nanny datails :" + nanny.toString());
        Family family = familyRepository.findById(idFamily).get();
        log.info("Getting the familydatails :" + family.toString());

        log.info("Creating a new booking");
        booking.setDate(LocalDate.now());
        booking.setPrice(family.getServiceFee());
        booking.setLocation(family.getCity());
        booking.setNanny(nanny);
        booking.setFamily(family);
        return bookingRepository.save(booking);
    }

    // Method of creating a booking from an n user when clicked on "Request match"
    // getting the family profile info and the user n info

    @Override
    public Booking createBookingFromN(Booking booking, Long idNanny, Long idFamily) {
        Nanny nanny = nannyRepository.findById(idNanny).get();
        log.info("Getting the nanny datails :" + nanny.toString());
        Family family = familyRepository.findById(idFamily).get();
        log.info("Getting the familydatails :" + family.toString());

        log.info("Creating a new booking");
        booking.setDate(LocalDate.now());
        booking.setPrice(nanny.getServiceFee());
        booking.setLocation(nanny.getCity());
        booking.setNanny(nanny);
        booking.setFamily(family);

        // Save the booking
        Booking createdBooking = bookingRepository.save(booking);
        log.debug("Booking created successfully" + createdBooking);
        log.info("Booking created successfully");

        return createdBooking;
    }
}
