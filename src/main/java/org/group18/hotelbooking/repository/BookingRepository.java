package org.group18.hotelbooking.repository;

import org.group18.hotelbooking.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByCustomerCustomerId(Long customerId);
    List<Booking> findByRoomRoomId(Long roomId);
}
