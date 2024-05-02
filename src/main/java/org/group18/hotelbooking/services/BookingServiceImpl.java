package org.group18.hotelbooking.services;

import org.group18.hotelbooking.dto.BookingDTO;
import org.group18.hotelbooking.models.Booking;
import org.group18.hotelbooking.models.Customer;
import org.group18.hotelbooking.models.Room;
import org.group18.hotelbooking.repository.BookingRepository;
import org.group18.hotelbooking.repository.CustomerRepository;
import org.group18.hotelbooking.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final CustomerRepository customerRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public BookingServiceImpl(
            BookingRepository bookingRepository,
            CustomerRepository customerRepository,
            RoomRepository roomRepository
    ) {
        this.bookingRepository = bookingRepository;
        this.customerRepository = customerRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public BookingDTO bookRoom(BookingDTO bookingDTO) {
        Booking booking = convertToEntity(bookingDTO);
        Booking savedBooking = bookingRepository.save(booking);
        return convertToDTO(savedBooking);
    }

    @Override
    public BookingDTO modifyBooking(Long bookingId, BookingDTO updatedBookingDTO) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + bookingId));

        // Update booking details
        booking.setCheckInDate(updatedBookingDTO.getCheckInDate());
        booking.setCheckOutDate(updatedBookingDTO.getCheckOutDate());
        booking.setTotalPrice(updatedBookingDTO.getTotalPrice());

        Booking updatedBooking = bookingRepository.save(booking);
        return convertToDTO(updatedBooking);
    }

    @Override
    public void cancelBooking(Long bookingId) {
        bookingRepository.deleteById(bookingId);
    }

    @Override
    public List<BookingDTO> getAllBookingsForCustomer(Long customerId) {
        List<Booking> bookings = bookingRepository.findByCustomerCustomerId(customerId);
        return bookings.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookingDTO> getAllBookingsForRoom(Long roomId) {
        List<Booking> bookings = bookingRepository.findByRoomRoomId(roomId);
        return bookings.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private BookingDTO convertToDTO(Booking booking) {
        return new BookingDTO(
                booking.getBookingId(),
                booking.getCustomer().getCustomerId(),
                booking.getRoom().getRoomId(),
                booking.getCheckInDate(),
                booking.getCheckOutDate(),
                booking.getTotalPrice()
        );
    }

    private Booking convertToEntity(BookingDTO bookingDTO) {
        // Retrieve Customer and Room entities by their IDs
        Customer customer = customerRepository.findById(bookingDTO.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + bookingDTO.getCustomerId()));

        Room room = roomRepository.findById(bookingDTO.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + bookingDTO.getRoomId()));

        return new Booking(
                customer,
                room,
                bookingDTO.getCheckInDate(),
                bookingDTO.getCheckOutDate(),
                bookingDTO.getTotalPrice()
        );
    }
}
