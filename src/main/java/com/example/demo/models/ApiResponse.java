package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;

//Clase para que todas las respuestas tengan el mismo formato (vuelos filtrados, errores, etc...)
@Data
@AllArgsConstructor
public class ApiResponse<T> {
    //Indica si la operación se realizo correctaemnte
    private boolean success;
    //Mensaje del resultado de la operación
    private String message;
    //Contenido de la respuesta (datos de la respuesta o detalles de error)
    private T data;
}

