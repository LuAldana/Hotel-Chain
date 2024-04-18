package com.globant.mentorship.hotelchain.repository;

import com.globant.mentorship.hotelchain.domain.entity.ObservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IObservationRepository extends JpaRepository<ObservationEntity, Long> {

    boolean existsByTypeAndBookingEntityId(@Param("type") int type, @Param("bookingId") Long bookingId);

    Optional<List<ObservationEntity>> findAllByBookingEntityId(Long bookingId);
}
