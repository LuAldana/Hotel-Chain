package com.globant.mentorship.hotelchain.repository;

import com.globant.mentorship.hotelchain.domain.entity.HotelSiteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IHotelSiteRepository extends JpaRepository<HotelSiteEntity, Long> {

    Optional<HotelSiteEntity> findByNeighborhood(String neighborhood);
}
