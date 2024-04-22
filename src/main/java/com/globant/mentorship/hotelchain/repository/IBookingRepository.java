package com.globant.mentorship.hotelchain.repository;

import com.globant.mentorship.hotelchain.domain.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IBookingRepository extends JpaRepository<BookingEntity, Long> {

    boolean existsByCheckInDateAndRoomEntityIdAndUserEntityId(@Param("checkInDate") LocalDate checkInDate,
                                                  @Param("roomId") Long roomId, @Param("userId") Long userId);

    Optional<List<BookingEntity>> findAllByUserEntityId(Long userId);

    @Query("SELECT b.userEntity.id FROM BookingEntity b WHERE b.checkInDate >= :date GROUP BY b.userEntity.id " +
            "ORDER BY COUNT(b.id) DESC LIMIT 1")
    Optional<Long> findUserIdWithMostBookings(@Param("date") LocalDate date);

    @Query("SELECT b FROM BookingEntity b WHERE b.status = :status")
    Optional<List<BookingEntity>> findAllActive(@Param("status") String status);
}
