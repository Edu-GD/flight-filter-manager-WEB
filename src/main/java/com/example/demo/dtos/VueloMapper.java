package com.example.demo.dtos;

import com.example.demo.models.Vuelo;
import com.example.demo.utils.VueloUtils;

//Clase que se encarga de la transformación de datos entre capas
//Permite convertir objetos DTO en modelos y modelos a DTO
public class VueloMapper {
    //De postman a modelo
    public static Vuelo toModel(VueloRequestDTO dto) {
        Vuelo v = new Vuelo();
        v.setNombreVuelo(dto.getNombreVuelo());
        v.setEmpresa(dto.getEmpresa());
        v.setLugarSalida(dto.getLugarSalida());
        v.setLugarLlegada(dto.getLugarLlegada());
        v.setFechaSalida(dto.getFechaSalida());
        v.setFechaLlegada(dto.getFechaLlegada());
        return v;
    }

    //De modelo al JSON que se mostrará
    public static VueloResponseDTO toDTO(Vuelo v) {
        return new VueloResponseDTO(
                v.getId(),
                v.getNombreVuelo(),
                v.getEmpresa(),
                v.getLugarSalida(),
                v.getLugarLlegada(),
                v.getFechaSalida(),
                v.getFechaLlegada(),
                VueloUtils.calcularDuracionEnDias(v.getFechaSalida(), v.getFechaLlegada()));
    }
}
