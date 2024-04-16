package com.globant.mentorship.hotelchain.repository;

import com.globant.mentorship.hotelchain.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Long> {

     Optional<UserEntity> findByDocument(Long document);

     @Modifying
     @Query("UPDATE UserEntity u SET u.status = true WHERE u.id = :userId")
     void blockGuest(@Param("userId") Long userId);

}
