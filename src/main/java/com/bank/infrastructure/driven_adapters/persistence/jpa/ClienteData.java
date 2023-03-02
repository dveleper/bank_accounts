package com.bank.infrastructure.driven_adapters.persistence.jpa;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cliente")
@Getter
@Setter
public class ClienteData extends PersonaData {
    @Column(nullable = false, length = 10)
    private String contrasena;
    private String estado;

}
