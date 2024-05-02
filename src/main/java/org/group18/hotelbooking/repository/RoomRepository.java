package org.group18.hotelbooking.repository;



import org.group18.hotelbooking.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    // You can add custom query methods here if needed
}
