package com.globant.mentorship.hotelchain.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tbl_habitaciones")
public class RoomEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "numero")
    private int number;
    @Column(name = "banio_privado")
    private boolean hasPrivateBath;
    @Column(name = "telefono")
    private boolean hasPhone;
    @Column(name = "calefaccion")
    private boolean hasHeating;
    @Column(name = "estado")
    private String status;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tbl_sedes_hotel_id", referencedColumnName = "id")
    private HotelSiteEntity hotelSiteEntity;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tbl_tipos_habitaciones_id", referencedColumnName = "id")
    private RoomTypeEntity roomTypeEntity;
}
