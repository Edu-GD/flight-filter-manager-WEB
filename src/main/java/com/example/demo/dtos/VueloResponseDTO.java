package com.example.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

//DTO para definir la estructura al JSON que verá el cliente.
@Getter
@Setter
@AllArgsConstructor
public class VueloResponseDTO {
    private Long id;
    private String nombreVuelo;
    private String empresa;
    private String lugarSalida;
    private String lugarLlegada;
    private LocalDate fechaSalida;
    private LocalDate fechaLlegada;
    private long duracionDias;
}
