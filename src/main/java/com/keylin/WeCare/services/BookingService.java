package com.keylin.WeCare.services;

import java.util.List;

import com.keylin.WeCare.entities.Booking;

public interface BookingService {
    public List<Booking> getBookingByNannyId(Long nannyId);

    public List<Booking> getBookingByFamilyId(Long familyId);
}
