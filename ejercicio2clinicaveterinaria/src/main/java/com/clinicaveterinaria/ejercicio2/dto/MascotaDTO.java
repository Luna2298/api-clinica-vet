package com.clinicaveterinaria.ejercicio2.dto;

import com.clinicaveterinaria.ejercicio2.model.Duenio;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MascotaDTO {

    private Long idMascota;
    private String nombreMascota;
    private String especie;
    private String raza;
    private String color;

    private DuenioDTO duenioDTO;

    public MascotaDTO() {

    }

    public MascotaDTO(Long idMascota, String nombreMascota, String especie, String raza, String color, DuenioDTO duenioDTO) {

        this.idMascota = idMascota;
        this.nombreMascota = nombreMascota;
        this.especie = especie;
        this.raza = raza;
        this.color = color;
        this.duenioDTO = duenioDTO;
    }
}
