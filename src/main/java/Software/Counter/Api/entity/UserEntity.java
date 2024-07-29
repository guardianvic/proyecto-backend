package Software.Counter.Api.entity;

import jakarta.persistence.*;
import lombok.*;


import java.sql.Date;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "usuario")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "email")
    private String email;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "usuario")
    private String usuario;

    @Column(name = "password")
    private String password;

    @Column(name = "fecha_de_creacion")
    private Date fechaDeCreacion;
    /*
    @Column(name = "rol_id")
    private Long rolId; */


    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "rol_id", nullable = false, updatable = false)
    private RolEntity rolEntities;



}

