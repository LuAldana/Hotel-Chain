package com.globant.mentorship.hotelchain.repository;

import com.globant.mentorship.hotelchain.domain.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICityRepository extends JpaRepository<CityEntity, Long> {

    Optional<CityEntity> findByCode(Long code);
}
