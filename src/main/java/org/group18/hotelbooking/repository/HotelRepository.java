package org.group18.hotelbooking.repository;


import org.group18.hotelbooking.models.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    // You can add custom query methods here if needed
}
