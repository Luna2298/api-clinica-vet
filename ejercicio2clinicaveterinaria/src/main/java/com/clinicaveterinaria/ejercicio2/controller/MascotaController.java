package com.clinicaveterinaria.ejercicio2.controller;

import com.clinicaveterinaria.ejercicio2.dto.MascotaDTO;
import com.clinicaveterinaria.ejercicio2.dto.MascotaIdNombreDTO;
import com.clinicaveterinaria.ejercicio2.dto.MascotaYDuenioDTO;
import com.clinicaveterinaria.ejercicio2.service.IMascotaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MascotaController {

    @Autowired
    private IMascotaService iMascotaService;

    @PostMapping("/mascotas/crear")
    private ResponseEntity<String> saveMascota(@Valid @RequestBody MascotaDTO mascotaDTO, BindingResult result){

        if (result.hasErrors()) {

            String errores = result.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.joining(", \n"));

            return ResponseEntity.badRequest().body("Errores: \n" + errores);
        }

        iMascotaService.saveMascota(mascotaDTO);
        return ResponseEntity.ok("Mascota creada correctamente");
    }

    @GetMapping("/mascotas/traer")
    private ResponseEntity<?> getListaMascotas() {

        List<MascotaIdNombreDTO> lista = iMascotaService.getListaMascotasDTO();

        if (lista.isEmpty()) {

            return ResponseEntity.badRequest().body("No existen Mascotas en la Base de Datos");
        }

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/mascotas/buscar")
    private ResponseEntity<?> getMascota(@RequestParam Long id) {

        MascotaIdNombreDTO mas = iMascotaService.getMascotaDTO(id);

        if (mas != null) {

            return ResponseEntity.ok(mas);
        }

        return ResponseEntity.badRequest().body("No se encontro la Mascota de ID: " + id);
    }

    @DeleteMapping("/mascotas/borrar")
    private ResponseEntity<String> deleteMascota(@RequestParam Long id){

        String mensaje = iMascotaService.deleteMascota(id);

        if (mensaje != null) {

            return ResponseEntity.ok(mensaje);
        }

        return ResponseEntity.badRequest().body("La Mascota con ID: "
                + id + " no existe en la BD, por ende no hizo falta eliminar");
    }

    @PutMapping("/mascotas/editar")
    private ResponseEntity<String> editMascota(@Valid @RequestBody MascotaDTO mascotaDTO, BindingResult result) {

        String mensaje = iMascotaService.editMascota(mascotaDTO);

        if (mensaje != null) {

            return ResponseEntity.ok(mensaje);
        }
        return ResponseEntity.badRequest().
                body("No se encontro la Mascota de ID: " + mascotaDTO.getIdMascota() +
                " en la Base de Datos para editar");
    }

    @GetMapping("/mascotas/buscar/palabra")
    private ResponseEntity<?> getMascotaPorEspecieYRaza(
            @RequestParam(required = true, name = "especie") String especie,
            @RequestParam(required = true, name = "raza") String raza) {

        List<MascotaDTO> lista = iMascotaService.buscarMascotaPorEspecieYRaza(especie, raza);

        if (lista.isEmpty()) {

            return ResponseEntity.badRequest()
                    .body("No existen Mascotas en la BD con tipo de raza: " + raza +
                          " y con tipo de especie: "  + especie);
        }

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/mascotas/listacon/duenios")
    private ResponseEntity<?> listaMascotasYDueniosDTO(){

        List<MascotaYDuenioDTO> lista = iMascotaService.listaMascotasYDueniosDTO();

        if (lista.isEmpty()) {

            return ResponseEntity.badRequest().body("No existen Mascotas en la BD");
        }

        return ResponseEntity.ok(lista);
    }

}
