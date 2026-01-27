package com.clinicaveterinaria.ejercicio2.service;

import com.clinicaveterinaria.ejercicio2.dto.MascotaDTO;
import com.clinicaveterinaria.ejercicio2.dto.MascotaIdNombreDTO;
import com.clinicaveterinaria.ejercicio2.dto.MascotaYDuenioDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface IMascotaService {

    void saveMascota(MascotaDTO mascotaDTO);

    List<MascotaIdNombreDTO> getListaMascotasDTO();

    MascotaIdNombreDTO getMascotaDTO(Long id);

    String deleteMascota(Long id);

    String editMascota(@Valid MascotaDTO mascotaDTO);

    List<MascotaDTO> buscarMascotaPorEspecieYRaza(String especie, String raza);

    List<MascotaYDuenioDTO> listaMascotasYDueniosDTO();
}
