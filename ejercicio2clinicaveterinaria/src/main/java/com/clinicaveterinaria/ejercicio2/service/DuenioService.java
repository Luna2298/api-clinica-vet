package com.clinicaveterinaria.ejercicio2.service;

import com.clinicaveterinaria.ejercicio2.dto.DuenioDTO;
import com.clinicaveterinaria.ejercicio2.model.Duenio;
import com.clinicaveterinaria.ejercicio2.repository.IDuenioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DuenioService implements IDuenioService{

    @Autowired
    private IDuenioRepository iDuenioRepository;

    @Override
    public void saveDuenio(DuenioDTO duenioDTO) {

        Duenio duenio = new Duenio();
        duenio.setNombreDuenio(duenioDTO.getNombreDuenio());
        duenio.setApellido(duenioDTO.getApellido());
        duenio.setCelular(duenioDTO.getCelular());

        iDuenioRepository.save(duenio);
    }

    @Override
    public List<DuenioDTO> getListaDueniosDTO() {

        return convertirListaDuenioAListaDTO();

    }

    @Override
    public DuenioDTO getDuenioDTO(Long id) {

        Duenio due = this.getDuenio(id);

        if (due != null){

            return convertirDuenioADuenioDTO(due);
        }

        return null;
    }

    @Override
    public String deleteDuenio(Long id) {

        Duenio du = this.getDuenio(id);

        if (du != null) {

            iDuenioRepository.deleteById(id);
            return "Duenio eliminado correctamente";
        }

        return "No se encontro el Duenio de ID: " + id + " para poder eliminarlo";
    }

    @Override
    public String editDuenio(DuenioDTO duenioDTO) {

        String mensaje = null;
        Duenio duenio = this.getDuenio(duenioDTO.getIdDuenio());

        if (duenio != null) {

            duenio.setNombreDuenio(duenioDTO.getNombreDuenio());
            duenio.setApellido(duenioDTO.getApellido());
            duenio.setCelular(duenioDTO.getCelular());

            iDuenioRepository.save(duenio);
            return mensaje = "Duenio editado correctamente";
        }

        return mensaje;
    }

    private DuenioDTO convertirDuenioADuenioDTO(Duenio due){

        DuenioDTO duenioDTO = new DuenioDTO(due.getIdDuenio(), due.getNombreDuenio(),
                due.getApellido(), due.getCelular());
        return duenioDTO;
    }

    private Duenio getDuenio(Long id){

        Duenio due = iDuenioRepository.findById(id).orElse(null);
        return due;
    }

    private List<DuenioDTO> convertirListaDuenioAListaDTO() {

        List<Duenio> listaDuenios = this.getListaDuenios();

        return listaDuenios.stream()
                .map(a -> new DuenioDTO(a.getIdDuenio(), a.getNombreDuenio(), a.getApellido(), a.getCelular()))
                .collect(Collectors.toList());
    }

    public List<Duenio> getListaDuenios(){

        List<Duenio> listaDuenios = iDuenioRepository.findAll();
        return listaDuenios;
    }

}
