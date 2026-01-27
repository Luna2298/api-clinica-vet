package com.clinicaveterinaria.ejercicio2.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MascotaIdNombreDTO {

    private Long idMascota;
    private String nombreMascota;

    public MascotaIdNombreDTO() {

    }

    public MascotaIdNombreDTO(Long idMascota, String nombreMascota) {

        this.idMascota = idMascota;
        this.nombreMascota = nombreMascota;
    }
}
