package com.clinicaveterinaria.ejercicio2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DuenioDTO {

    private Long idDuenio;

    @NotBlank(message = "El nombre del duenio no puede estar vacío")
    @Size(min = 3, max = 50, message = "El nombre del duenio debe tener entre 3 y 50 caracteres")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúñÑ ]+$", message = "Solo se permiten letras")
    private String nombreDuenio;

    @NotBlank(message = "El nombre del duenio no puede estar vacío")
    @Size(min = 3, max = 50, message = "El nombre del duenio debe tener entre 3 y 50 caracteres")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúñÑ ]+$", message = "Solo se permiten letras")
    private String apellido;

    /*
    Validar que un valor sea un número de un formato específico,
    como un número de teléfono que comience con un dígito específico,
    se puede usar la anotación @Pattern con una expresión regular.
    Por ejemplo, para validar que un número de teléfono tenga nueve
    dígitos y comience con el dígito 6:
    @Pattern(regexp = "6[0-9]{8}")
    */
    @Pattern(regexp = "[0-9]{10}")
    private String celular;

    public DuenioDTO() {

    }

    public DuenioDTO(Long idDuenio, String nombreDuenio, String apellido, String celular) {

        this.idDuenio = idDuenio;
        this.nombreDuenio = nombreDuenio;
        this.apellido = apellido;
        this.celular = celular;
    }
}
