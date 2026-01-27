package com.clinicaveterinaria.ejercicio2.service;

import com.clinicaveterinaria.ejercicio2.dto.DuenioDTO;
import com.clinicaveterinaria.ejercicio2.dto.MascotaDTO;
import com.clinicaveterinaria.ejercicio2.dto.MascotaIdNombreDTO;
import com.clinicaveterinaria.ejercicio2.dto.MascotaYDuenioDTO;
import com.clinicaveterinaria.ejercicio2.model.Duenio;
import com.clinicaveterinaria.ejercicio2.model.Mascota;
import com.clinicaveterinaria.ejercicio2.repository.IDuenioRepository;
import com.clinicaveterinaria.ejercicio2.repository.IMascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MascotaService implements IMascotaService{

    @Autowired
    private IMascotaRepository iMascotaRepository;

    @Autowired
    private IDuenioRepository iDuenioRepository;

    @Override
    public void saveMascota(MascotaDTO mascotaDTO) {

        Mascota mas = new Mascota();
        mas.setNombreMascota(mascotaDTO.getNombreMascota());
        mas.setEspecie(mascotaDTO.getEspecie());
        mas.setRaza(mascotaDTO.getRaza());
        mas.setColor(mascotaDTO.getColor());

        DuenioDTO dueDto = mascotaDTO.getDuenioDTO();

        Duenio d = iDuenioRepository.findById(dueDto.getIdDuenio()).
                orElseThrow(() -> new RuntimeException("Duenio con ID: "
                + dueDto.getIdDuenio() + " no encontrado"));

        mas.setDuenio(d);
        iMascotaRepository.save(mas);

    }

    @Override
    public List<MascotaIdNombreDTO> getListaMascotasDTO() {

        List<Mascota> listaMascotas = this.getListaMascotas();

        /*
        Le paso una Lista del Tipo Mascota, y por cada elemento del tipo Mascota,
        va a crear un objeto del Tipo MascotaIdNombreDTO al cual le va a asignar
        los valores del Objeto Mascota que llega de la List<Mascota>.
        Y luego, cada Objeto ya convertido en MascotaIdNombreDTO sera RECOLECTADO
        por el .collect y puesto en una Lista.
        */

        return listaMascotas.stream()
                .map(a -> new MascotaIdNombreDTO(a.getIdMascota(), a.getNombreMascota()))
                .collect(Collectors.toList());

    }

    @Override
    public MascotaIdNombreDTO getMascotaDTO(Long id) {

        Mascota mas = this.getMascota(id);
        MascotaIdNombreDTO dto = null;

        if (mas != null) {

            return dto = convertirMascotaADto(mas);
        }

        return null;
    }

    @Override
    public String deleteMascota(Long id) {

        String mensaje = null;
        Mascota mas = this.getMascota(id);

        if (mas != null) {

            iMascotaRepository.deleteById(mas.getIdMascota());
            return mensaje = "Mascota eliminada correctamente";
        }

        return mensaje;
    }

    @Override
    public String editMascota(MascotaDTO mascotaDTO) {

        String mensaje = null;
        Mascota mas = this.getMascota(mascotaDTO.getIdMascota());

        if (mas != null){

            mas.setNombreMascota(mascotaDTO.getNombreMascota());
            mas.setEspecie(mascotaDTO.getEspecie());
            mas.setRaza(mascotaDTO.getRaza());
            mas.setColor(mascotaDTO.getColor());

            Duenio d = iDuenioRepository.findById
                    (mascotaDTO.getDuenioDTO().getIdDuenio())
                    .orElseThrow(() -> new RuntimeException("Duenio con ID: "
                    + mascotaDTO.getDuenioDTO().getIdDuenio() + " no encontrado"));

            mas.setDuenio(d);

            iMascotaRepository.save(mas);
            return mensaje = "Mascota editada correctamente";
        }
        return null;
    }

    @Override
    public List<MascotaDTO> buscarMascotaPorEspecieYRaza(String especie, String raza) {

        List<MascotaDTO> listaDto = new ArrayList<>();

        for (Mascota mas: this.getListaMascotas()) {

            if (mas.getEspecie().toLowerCase().contains(especie)
                && mas.getRaza().toLowerCase().contains(raza) ) {

                Duenio due = mas.getDuenio();
                DuenioDTO dto = new DuenioDTO
                        (due.getIdDuenio(), due.getNombreDuenio(), due.getApellido(), due.getCelular());

                MascotaDTO m = new MascotaDTO
                        (mas.getIdMascota(), mas.getNombreMascota(), mas.getEspecie(),
                         mas.getRaza(), mas.getColor(), dto);

                listaDto.add(m);

                return listaDto;
            }
        }

        return listaDto;
    }

    @Override
    public List<MascotaYDuenioDTO> listaMascotasYDueniosDTO() {

        List<MascotaYDuenioDTO> lista = new ArrayList<>();

        /*Valido si la Lista que me llega no esta vacia*/
        if (!(this.getListaMascotas().isEmpty())) {

            /*Como no esta vacia, entonces la recorro con el for*/
            for (Mascota mas: this.getListaMascotas()) {

                /*Convierto cada Objeto Mascota al tipo Objeto MascotaYDuenioDTO*/
                MascotaYDuenioDTO m = new MascotaYDuenioDTO(mas.getIdMascota(),
                        mas.getNombreMascota(), mas.getEspecie(), mas.getRaza(),
                        mas.getDuenio().getNombreDuenio(), mas.getDuenio().getApellido());

                /*Agrego el Objeto MascotaYDuenioDTO a la Lista de Tipo MascotaYDuenioDTO*/
                lista.add(m);
            }
        }
        return lista;
    }

    private MascotaIdNombreDTO convertirMascotaADto(Mascota mas) {

        MascotaIdNombreDTO m = new MascotaIdNombreDTO(mas.getIdMascota(), mas.getNombreMascota());
        return m;
    }

    private Mascota getMascota(Long id) {

        Mascota mas = iMascotaRepository.findById(id).orElse(null);
        return mas;
    }

    private List<Mascota> getListaMascotas(){

        return iMascotaRepository.findAll();
    }
}
