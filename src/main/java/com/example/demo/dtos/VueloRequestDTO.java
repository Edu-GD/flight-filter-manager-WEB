package com.example.demo.dtos;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

//DTO utilizado para crear y editar vuelos
//Definimos en cada parametro reglas de validación
@Getter
@Setter
public class VueloRequestDTO {
    @NotBlank(message = "El nombre del vuelo es obligatorio.")
    private String nombreVuelo;
    @NotBlank(message = "La empresa no puede estar vacía.")
    private String empresa;
    @NotBlank(message = "Indique el lugar de salida.")
    private String lugarSalida;
    @NotBlank(message = "Indique el lugar de llegada.")
    private String lugarLlegada;
    @NotNull(message = "Indique la fecha de salida.")
    //Validación de fecha que no permite registrar vuelos con fecha de salida anterior a hoy.
    @FutureOrPresent(message = "La fecha de salida no puede ser una fecha pasada.")
    private LocalDate fechaSalida;
    @NotNull(message = "Indique la fecha de llegada.")
    private LocalDate fechaLlegada;
}

