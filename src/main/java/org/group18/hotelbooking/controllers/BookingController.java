package org.group18.hotelbooking.controllers;

import org.group18.hotelbooking.dto.BookingDTO;
import org.group18.hotelbooking.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//booking controller
@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/book")
    public ResponseEntity<BookingDTO> bookRoom(@RequestBody BookingDTO bookingDTO) {
        BookingDTO createdBooking = bookingService.bookRoom(bookingDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBooking);
    }

    @PutMapping("/{bookingId}")
    public ResponseEntity<BookingDTO> modifyBooking(
            @PathVariable Long bookingId,
            @RequestBody BookingDTO updatedBookingDTO
    ) {
        BookingDTO modifiedBooking = bookingService.modifyBooking(bookingId, updatedBookingDTO);
        if (modifiedBooking != null) {
            return ResponseEntity.ok(modifiedBooking);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Void> cancelBooking(@PathVariable Long bookingId) {
        bookingService.cancelBooking(bookingId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<BookingDTO>> getAllBookingsForCustomer(@PathVariable Long customerId) {
        List<BookingDTO> bookings = bookingService.getAllBookingsForCustomer(customerId);
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/room/{roomId}")
    public ResponseEntity<List<BookingDTO>> getAllBookingsForRoom(@PathVariable Long roomId) {
        List<BookingDTO> bookings = bookingService.getAllBookingsForRoom(roomId);
        return ResponseEntity.ok(bookings);
    }
}
