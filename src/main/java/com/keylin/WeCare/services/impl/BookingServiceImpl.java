package com.keylin.WeCare.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keylin.WeCare.entities.Booking;
import com.keylin.WeCare.repositories.BookingRepository;

@Service
public class BookingServiceImpl {

    private static final Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);

    @Autowired
    private BookingRepository bookingRepository;

    public List<Booking> getBookingByNannyId(Long nannyId) {
        logger.info("Fetching bookings for nanny with id: {}", nannyId);
        return bookingRepository.findByNannyId(nannyId);
    }

    public List<Booking> getBookingByFamilyId(Long familyId) {
        logger.info("Fetching bookings for family with id: {}", familyId);
        return bookingRepository.findByFamilyId(familyId);
    }

}
