package com.globant.mentorship.hotelchain.repository;

import com.globant.mentorship.hotelchain.domain.entity.RoomTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoomTypeRepository extends JpaRepository<RoomTypeEntity, Long> {
}
