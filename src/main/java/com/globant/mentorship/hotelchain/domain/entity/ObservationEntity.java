package com.globant.mentorship.hotelchain.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tbl_observaciones")
public class ObservationEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo")
    private int type;

    @Column(name = "observación")
    private String observation;

    @Column(name = "fecha")
    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tbl_reservas_id", referencedColumnName = "id")
    private BookingEntity bookingEntity;
}
