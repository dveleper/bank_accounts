package com.bank.infrastructure.driven_adapters.persistence.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "persona")
@Inheritance(strategy= InheritanceType.JOINED)
@Getter
@Setter
public class PersonaData {
    @Id
    @Column(nullable = false, length = 20)
    private String identificacion;

    @Column(nullable = false, length = 45)
    private String nombre;

    @Column(nullable = false, length = 10)
    private String genero;

    @Column(nullable = false, length = 3)
    private Integer edad;

    @Column(nullable = false, length = 60)
    private String direccion;

    @Column(nullable = false, length = 20)
    private String telefono;
}
