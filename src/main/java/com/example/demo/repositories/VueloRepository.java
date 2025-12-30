package com.example.demo.repositories;

import com.example.demo.models.Vuelo;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class VueloRepository {
    //Almacén de datos temporal mientras se ejecuta la aplicación
    private final List<Vuelo> vuelos = new ArrayList<>();

    //Constructor que inicializa el repositorio con datos
    public VueloRepository() {
        vuelos.add(new Vuelo(1L, "IB-6841", "Iberia", "Madrid", "Buenos Aires", LocalDate.of(2026, 12, 15), LocalDate.of(2026, 12, 16)));
        vuelos.add(new Vuelo(2L, "EK-202", "Emirates", "Dubai", "New York", LocalDate.of(2026, 6, 10), LocalDate.of(2026, 6, 11)));
        vuelos.add(new Vuelo(3L, "QR-701", "Qatar Airways", "Doha", "New York", LocalDate.of(2026, 9, 5), LocalDate.of(2026, 9, 6)));
        vuelos.add(new Vuelo(4L, "TK-15", "Turkish Airlines", "Estambul", "Sao Paulo", LocalDate.of(2026, 2, 20), LocalDate.of(2026, 2, 21)));
        vuelos.add(new Vuelo(5L, "SQ-22", "Singapore Airlines", "Singapur", "Newark", LocalDate.of(2026, 10, 12), LocalDate.of(2026, 10, 13)));
        vuelos.add(new Vuelo(6L, "AF-006", "Air France", "París", "Los Ángeles", LocalDate.of(2026, 5, 1), LocalDate.of(2026, 5, 2)));
        vuelos.add(new Vuelo(7L, "LH-506", "Lufthansa", "Frankfurt", "Buenos Aires", LocalDate.of(2026, 11, 25), LocalDate.of(2026, 11, 26)));
        vuelos.add(new Vuelo(8L, "JL-001", "Japan Airlines", "Tokio", "San Francisco", LocalDate.of(2026, 4, 10), LocalDate.of(2026, 4, 11)));
        vuelos.add(new Vuelo(9L, "BA-249", "British Airways", "Londres", "Rio de Janeiro", LocalDate.of(2026, 3, 1), LocalDate.of(2026, 3, 2)));
        vuelos.add(new Vuelo(10L, "QF-001", "Qantas", "Sídney", "Londres", LocalDate.of(2026, 1, 20), LocalDate.of(2026, 1, 22)));
    }

    // Devuelve la lista completa de vuelos
    public List<Vuelo> findAll(){
        return vuelos;
    }

    // Añade un nuevo vuelo
    public void save(Vuelo vuelo){
        vuelos.add(vuelo);
    }

    // Devuelve un vuelo por su id
    public Optional<Vuelo> findById(Long id){ // Cambié Integer por int para evitar problemas de comparación
        return vuelos.stream()
                .filter(v -> v.getId().equals(id))
                .findFirst();
    }

    // Elimina el vuelo por id (si existe)
    public void delete(Long id){
        vuelos.removeIf(v -> v.getId().equals(id));
    }

    // Reemplaza los datos que se proporacionan de un vuelo existente (PUT)
    public void update(Long id, Vuelo vueloActualizado) {
        for (int i = 0; i < vuelos.size(); i++) {
            if (vuelos.get(i).getId().equals(id)) {
                vuelos.set(i, vueloActualizado);
                return;
            }
        }
    }

    //Obtiene el ID mas alto actual para poder asignar el nuevo ID
    public Long getLastId() {
        return vuelos.stream()
                .mapToLong(Vuelo::getId)
                .max()
                .orElse(0L);
    }
}