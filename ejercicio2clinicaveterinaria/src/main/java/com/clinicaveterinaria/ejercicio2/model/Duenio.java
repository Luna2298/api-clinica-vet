package com.clinicaveterinaria.ejercicio2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Duenio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idDuenio;
    private String nombreDuenio;
    private String apellido;
    private String celular;

    public Duenio() {

    }

    public Duenio(Long idDuenio, String nombreDuenio, String apellido, String celular) {

        this.idDuenio = idDuenio;
        this.nombreDuenio = nombreDuenio;
        this.apellido = apellido;
        this.celular = celular;
    }
}
