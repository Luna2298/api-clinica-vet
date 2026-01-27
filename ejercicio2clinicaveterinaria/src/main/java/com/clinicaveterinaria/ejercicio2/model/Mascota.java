package com.clinicaveterinaria.ejercicio2.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idMascota;
    private String nombreMascota;
    private String especie;
    private String raza;
    private String color;

    @OneToOne
    private Duenio duenio;

    public Mascota() {

    }

    public Mascota(Long idMascota, String nombreMascota, String especie, String raza, String color, Duenio duenio) {

        this.idMascota = idMascota;
        this.nombreMascota = nombreMascota;
        this.especie = especie;
        this.raza = raza;
        this.color = color;
        this.duenio = duenio;
    }
}

