package com.example.demo.controllers;

import com.example.demo.dtos.VueloMapper;
import com.example.demo.dtos.VueloRequestDTO;
import com.example.demo.dtos.VueloResponseDTO;
import com.example.demo.models.ApiResponse;
import com.example.demo.models.Vuelo;
import com.example.demo.services.VueloService;
import com.example.demo.utils.VueloUtils;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

//Endpoint principal para la gestión de los vuelos
@RestController
@RequestMapping("/vuelos")
public class VueloController {
    private final VueloService vueloService;

    public VueloController(VueloService vueloService) {
        this.vueloService = vueloService;
    }

    //GET - Listar todos los vuelos, permite filtros por parametros
    @GetMapping
    public ResponseEntity<ApiResponse<List<VueloResponseDTO>>> getVuelos(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String nombreVuelo,
            @RequestParam(required = false) String empresa,
            @RequestParam(required = false) String lugarSalida,
            @RequestParam(required = false) String lugarLlegada,
            @RequestParam(required = false) String fechaSalida,
            @RequestParam(required = false) String fechaLlegada,
            @RequestParam(required = false) String ordenarPor,
            @RequestParam(required = false, defaultValue = "ASC") String ordenar) {

        //Convertimos la fecha de String a LocalDate
        LocalDate fechaS = VueloUtils.parsearFecha(fechaSalida);
        LocalDate fechaLl = VueloUtils.parsearFecha(fechaLlegada);

        //Obtenemos la lista del Service
        List<VueloResponseDTO> vuelos = vueloService.listarVuelos(id, nombreVuelo, empresa, lugarSalida, lugarLlegada, fechaS, fechaLl, ordenarPor, ordenar);

        //Devolvemos la lista
        return ResponseEntity.ok(new ApiResponse<>(true, "Lista de vuelos obtenida con éxito", vuelos));
    }

    //GET - Metodo para obtener un vuelo por ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<VueloResponseDTO>> obtenerPorId(@PathVariable Long id) {
        Vuelo vuelo = vueloService.buscarPorId(id);
        VueloResponseDTO response = VueloMapper.toDTO(vuelo);
        return ResponseEntity.ok(new ApiResponse<>(true, "Vuelo encontrado", response));
    }

    //POST - Metodo para crear un vuelo
    @PostMapping
    public ResponseEntity<ApiResponse<VueloResponseDTO>> crearVuelo(@Valid @RequestBody VueloRequestDTO dto) {
        Vuelo nuevoVuelo = VueloMapper.toModel(dto);
        vueloService.crearVuelo(nuevoVuelo);

        VueloResponseDTO response = VueloMapper.toDTO(nuevoVuelo);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(true, "Vuelo creado con éxito", response));
    }

    //PUT - Metodo para actualizar un vuelo
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<VueloResponseDTO>> actualizarVuelo(@PathVariable Long id, @Valid @RequestBody VueloRequestDTO dto) {
        Vuelo vueloActualizado = VueloMapper.toModel(dto);
        vueloService.actualizarVuelo(id, vueloActualizado);

        VueloResponseDTO response = VueloMapper.toDTO(vueloActualizado);
        return ResponseEntity.ok(new ApiResponse<>(true, "Vuelo actualizado correctamente", response));
    }

    //DELETE - Metodo para eliminar un vuelo
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarVuelo(@PathVariable Long id) {
        vueloService.borrarVuelo(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Vuelo con ID " + id + " eliminado.", null));
    }
}
