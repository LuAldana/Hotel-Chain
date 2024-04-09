package com.globant.mentorship.hotelchain.repository;

import com.globant.mentorship.hotelchain.domain.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoomRepository extends JpaRepository<RoomEntity, Long> {

    Optional<RoomEntity> findByNumber(int number);

    @Query("SELECT r.roomTypeEntity.id " +
            "FROM RoomEntity r " +
            "WHERE r.status = 'RESERVADA' " +
            "GROUP BY r.roomTypeEntity.id " +
            "ORDER BY COUNT(r.roomTypeEntity.id) DESC " +
            "LIMIT 1")
    Optional<Long> findTypeMostBooked();
}
