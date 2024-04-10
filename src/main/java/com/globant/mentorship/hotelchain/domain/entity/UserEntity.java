package com.globant.mentorship.hotelchain.domain.entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tbl_usuarios")
public class UserEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "documento")
    private Long document;
    @Column(name = "nombres")
    private String name;
    @Column(name = "primer_apellido")
    private String firstLastName;
    @Column(name = "segundo_apellido")
    private String secondLastName;
    @Column(name = "direccion")
    private String address;
    @Column(name = "telefono")
    private String phone;
    @Column(name = "correo")
    private String email;
    @Column(name = "clave")
    private String password;
    @Column(name = "estado")
    private boolean status;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tbl_tipos_documento_id", referencedColumnName = "id")
    private DocumentTypeEntity documentTypeEntity;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tbl_ciudades_codigo", referencedColumnName = "codigo")
    private CityEntity cityEntity;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tbl_tipos_usuarios_id", referencedColumnName = "id")
    private UserTypeEntity userTypeEntity;
}
