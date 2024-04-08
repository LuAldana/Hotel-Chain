package com.globant.mentorship.hotelchain.repository;

import com.globant.mentorship.hotelchain.domain.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IBookingRepository extends JpaRepository<BookingEntity, Long> {

    Optional<BookingEntity> findBookingById(Long id);

}
