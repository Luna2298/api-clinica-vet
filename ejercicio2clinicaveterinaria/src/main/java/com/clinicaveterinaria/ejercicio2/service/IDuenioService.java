package com.clinicaveterinaria.ejercicio2.service;

import com.clinicaveterinaria.ejercicio2.dto.DuenioDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface IDuenioService {

     void saveDuenio(DuenioDTO duenioDTO);

     List<DuenioDTO> getListaDueniosDTO();

     DuenioDTO getDuenioDTO(Long id);

     String deleteDuenio(Long id);

     String editDuenio(@Valid DuenioDTO duenioDTO);
}
