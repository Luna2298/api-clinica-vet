package com.clinicaveterinaria.ejercicio2.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MascotaYDuenioDTO {

    private Long idMascota;
    private String nombreMascota;
    private String especie;
    private String raza;
    private String nombreDuenio;
    private String apellidoDuenio;

    public MascotaYDuenioDTO() {

    }

    public MascotaYDuenioDTO(Long idMascota, String nombreMascota,
                             String especie, String raza,
                             String nombreDuenio, String apellidoDuenio) {

        this.idMascota = idMascota;
        this.nombreMascota = nombreMascota;
        this.especie = especie;
        this.raza = raza;
        this.nombreDuenio = nombreDuenio;
        this.apellidoDuenio = apellidoDuenio;
    }
}
