package com.globant.mentorship.hotelchain.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tbl_reservas")
public class BookingEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fecha_ingreso")
    private LocalDateTime checkInDate;
    @Column(name = "fecha_salida")
    private LocalDateTime checkOutDate;
    @Column(name = "estado")
    private String status;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tbl_habitaciones_id", referencedColumnName = "id")
    private RoomEntity roomEntity;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tbl_usuarios_id", referencedColumnName = "id")
    private UserEntity userEntity;
}
