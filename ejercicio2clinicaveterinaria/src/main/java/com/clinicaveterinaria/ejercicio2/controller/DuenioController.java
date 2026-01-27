package com.clinicaveterinaria.ejercicio2.controller;

import com.clinicaveterinaria.ejercicio2.dto.DuenioDTO;
import com.clinicaveterinaria.ejercicio2.service.IDuenioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class DuenioController {

    @Autowired
    private IDuenioService iDuenioService;

    @PostMapping("/duenio/crear")
    public ResponseEntity<String> saveDuenio(@Valid @RequestBody DuenioDTO duenioDTO, BindingResult result) {

        if (result.hasErrors()) {
            String errores = result.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.joining(", \n"));

            return ResponseEntity.badRequest().body("Errores: \n" + errores);

        }

        iDuenioService.saveDuenio(duenioDTO);
        return ResponseEntity.ok("Duenio creado correctamente");
    }

    @GetMapping("/duenio/traer")
    public ResponseEntity<?> getListaDuenios(){

        List<DuenioDTO> lista = iDuenioService.getListaDueniosDTO();

        if (lista.isEmpty()) {

            return ResponseEntity.badRequest().body("No existen duenios en la Base de Datos");
        }

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/duenio/buscar")
    public ResponseEntity<?> getDuenio(@RequestParam Long id){

        DuenioDTO due = iDuenioService.getDuenioDTO(id);

        if (due != null) {

            return ResponseEntity.ok(due);
        }

        return ResponseEntity.badRequest().body("No existe el Duenio de ID: " + id);
    }

    @DeleteMapping("/duenio/borrar")
    public ResponseEntity<?> deleteDuenio(@RequestParam Long id){

        String mensaje = iDuenioService.deleteDuenio(id);

        if (mensaje.toLowerCase().contains("id")) {

            return ResponseEntity.badRequest().body(mensaje);
        }

        return ResponseEntity.ok(mensaje);
    }

    @PutMapping("/duenio/editar")
    public ResponseEntity<?> editDuenio(@Valid @RequestBody DuenioDTO duenioDTO, BindingResult result) {

        if (result.hasErrors()) {
            String errores = result.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.joining(", \n"));
            return ResponseEntity.badRequest().body("Errores: \n" + errores);

        }

        String mensaje = iDuenioService.editDuenio(duenioDTO);

        if (mensaje == null) {

            return ResponseEntity.badRequest().
                    body("El Duenio no se pudo editar, su ID: "
                    + duenioDTO.getIdDuenio() + " no existe en la Base de Datos");
        }

        return ResponseEntity.ok(mensaje);
    }
}
