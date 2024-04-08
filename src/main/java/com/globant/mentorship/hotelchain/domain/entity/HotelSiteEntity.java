package com.globant.mentorship.hotelchain.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tbl_sedes_hotel")
public class HotelSiteEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "barrio")
    private String neighborhood;
    @Column(name = "direccion")
    private String address;
    @Column(name = "telefonos")
    private String phone;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tbl_ciudades_codigo", referencedColumnName = "codigo")
    private CityEntity cityEntity;
}
