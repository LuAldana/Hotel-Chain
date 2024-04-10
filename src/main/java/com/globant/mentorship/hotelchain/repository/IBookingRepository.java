package com.globant.mentorship.hotelchain.repository;

import com.globant.mentorship.hotelchain.domain.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface IBookingRepository extends JpaRepository<BookingEntity, Long> {

    @Query("SELECT b FROM BookingEntity b " +
            "WHERE b.checkInDate = :checkInDate AND b.roomEntity.id = :roomId AND b.userEntity.id = :userId")
    Optional<BookingEntity> existsByCheckInDateAndRoomIdAndUserId(@Param("checkInDate") LocalDate checkInDate,
                                                  @Param("roomId") Long roomId, @Param("userId") Long userId);
}
