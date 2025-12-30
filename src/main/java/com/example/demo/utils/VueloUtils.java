package com.example.demo.utils;

import com.example.demo.excepcions.BadRequestException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class VueloUtils {
    //Convierte la cadena de texto a LocalDate
    //Si la string es nula o vacía, devuelve null para no filtrar en el Service
    //Si el formato es incorrecto, lanzamos una excepción
    public static LocalDate parsearFecha(String fechaStr) {
        //Verificamos que tengamos fecha, si el filtro es vacío devolvemos null para que Service no filtre por fecha
        if (fechaStr == null || fechaStr.isBlank()) {
            return null;
        }
        try {
            return LocalDate.parse(fechaStr);
        } catch (DateTimeParseException e) {
            throw new BadRequestException("El formato es inválido. Use yyyy-MM-dd.");
        }
    }

    //Calcula la diferencia en días entra la salida y la llegada
    public static long calcularDuracionEnDias(LocalDate salida, LocalDate llegada) {
        // Verifico que existan las dos fechas
        if (salida != null && llegada != null) {
            // toEpochDay convierte la fecha a un número de días desde el 1 de enero de 1970 (para poder hacer la resta)
            long diasSalida = salida.toEpochDay();
            long diasLlegada = llegada.toEpochDay();
            // Restamos los dias de salida con los dias de llegada para saber el resultado de la duración
            return diasLlegada - diasSalida;
        }
        // Si falta alguna fecha devolvemos 0
        return 0;
    }
}
