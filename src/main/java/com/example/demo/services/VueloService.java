package com.example.demo.services;

import com.example.demo.dtos.VueloMapper;
import com.example.demo.dtos.VueloResponseDTO;
import com.example.demo.excepcions.BadRequestException;
import com.example.demo.excepcions.NotFoundException;
import com.example.demo.models.Vuelo;
import com.example.demo.repositories.VueloRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//Servicio que contiene la logica de negocio de la aplicación
//Gestiona validaciones, filtrados y la comunicación con el repositorio
@Service
public class VueloService {
    private final VueloRepository vueloRepository;

    //Añadimos el repositorio por constructor
    public VueloService(VueloRepository vueloRepository) {
        this.vueloRepository = vueloRepository;
    }

    //Verifica que las fechas sean utiles y que no existan nombres duplicados
    private void validarVuelo(Vuelo vuelo, Long idActual) {
        //Validación de fecha salida no puede ser después de la fecha llegada
        if (vuelo.getFechaSalida().isAfter(vuelo.getFechaLlegada())) {
            throw new BadRequestException("La fecha de salida no puede ser posterior a la fecha de llegada.");
        }
        //Evita registrar dos vuelos con el mismo nombre
        boolean duplicado = vueloRepository.findAll().stream()
                .anyMatch(v -> v.getNombreVuelo().equalsIgnoreCase(vuelo.getNombreVuelo())
                        && (idActual == null || !v.getId().equals(idActual)));
        if (duplicado) {
            throw new BadRequestException("El vuelo ya existe");
        }
    }

    //Filtrado y ordenación de los vuelos. Si un parametro es null, no se aplica el filtro.
    public List<VueloResponseDTO> listarVuelos(Long id, String nombre, String empresa, String salida, String llegada, LocalDate fSalida, LocalDate fLlegada, String ordenarPor, String ordenar) {
        Stream<Vuelo> vueloStream = vueloRepository.findAll().stream()
                .filter(v -> id == null || v.getId().equals(id))
                .filter(v -> nombre == null || v.getNombreVuelo().equalsIgnoreCase(nombre))
                .filter(v -> empresa == null || v.getEmpresa().equalsIgnoreCase(empresa))
                .filter(v -> salida == null || v.getLugarSalida().equalsIgnoreCase(salida))
                .filter(v -> llegada == null || v.getLugarLlegada().equalsIgnoreCase(llegada))
                .filter(v -> fSalida == null || !v.getFechaSalida().isBefore(fSalida))
                .filter(v -> fLlegada == null || !v.getFechaLlegada().isAfter(fLlegada));
        Comparator<Vuelo> comparator = Comparator.comparing(Vuelo::getFechaSalida);
        //Ordenación de emrpresa o lugar de salida
        if (ordenarPor != null) {
            if (ordenarPor.equalsIgnoreCase("empresa")) {
                comparator = Comparator.comparing(Vuelo::getEmpresa);
            } else if (ordenarPor.equalsIgnoreCase("lugarLlegada")) {
                comparator = Comparator.comparing(Vuelo::getLugarLlegada);
            }
        }
                if ("DESC".equalsIgnoreCase(ordenar)) {
                    comparator = comparator.reversed();
            }
        //Transformación de modelo a DTO para la respuesta
        return vueloStream.
                sorted(comparator)
                .map(VueloMapper::toDTO)
                .collect(Collectors.toList());
        }

    //Metodo para filtrar 1 solo vuelo por id
    public Vuelo buscarPorId(Long id) {
        return vueloRepository.findById(id).orElseThrow(() -> new NotFoundException("El vuelo no existe"));
    }

    //Metodo para crear un vuelo nuevo verificando la ultima ID almacenada y sumando 1
    public void crearVuelo(Vuelo vuelo) {
        validarVuelo(vuelo, null);
        Long nuevoId = vueloRepository.getLastId() + 1;
        vuelo.setId(nuevoId);
        vueloRepository.save(vuelo);
    }

    //Metodo para borrar un vuelo si existe
    public void borrarVuelo(Long id) {
        this.buscarPorId(id);
        vueloRepository.delete(id);
    }

    //Actualiza los datos de un vuelo si existe, manteniendo su ID
    public void actualizarVuelo(Long id, Vuelo vueloActualizado) {
        this.buscarPorId(id);
        validarVuelo(vueloActualizado, id);
        vueloActualizado.setId(id);
        vueloRepository.update(id, vueloActualizado);
    }
}