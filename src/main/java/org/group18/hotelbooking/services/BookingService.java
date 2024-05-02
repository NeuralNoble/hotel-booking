package org.group18.hotelbooking.services;

import org.group18.hotelbooking.dto.BookingDTO;

import java.util.List;

public interface BookingService {

    BookingDTO bookRoom(BookingDTO bookingDTO);

    BookingDTO modifyBooking(Long bookingId, BookingDTO updatedBookingDTO);

    void cancelBooking(Long bookingId);

    List<BookingDTO> getAllBookingsForCustomer(Long customerId);

    List<BookingDTO> getAllBookingsForRoom(Long roomId);
}
