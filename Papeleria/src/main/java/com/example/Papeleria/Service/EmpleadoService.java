package com.example.Papeleria.Service;

import com.example.Papeleria.Model.Cliente;
import com.example.Papeleria.Model.Empleado;
import com.example.Papeleria.Repository.EmpleadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {
    private final EmpleadoRepository empleadoRepository;

    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    public Empleado guardar(Empleado empleado) {
        try {
            if (empleado.getNombre() == null || empleado.getNombre().isEmpty()) {
                throw new IllegalArgumentException("El nombre del empleado no puede estar vacío");
            }
            return empleadoRepository.save(empleado);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el empleado " + e.getMessage(), e);

        }
    }

    public List<Empleado> listar(){
        try {
            return empleadoRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar empleados: " + e.getMessage(), e);
        }
    }

    public void eliminar(Long id_empleado){
        try {
            if (!empleadoRepository.existsById(id_empleado)) {
                throw new IllegalArgumentException("No se encontró un empleado con el ID: " + id_empleado);
            }
            empleadoRepository.deleteById(id_empleado);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el empleado: " + e.getMessage(), e);
        }
    }

    public Optional<Empleado> listarPorId(long id_empleado) {
        try {
            Optional<Empleado> empleado = empleadoRepository.findById(id_empleado);
            if (empleado.isEmpty()) {
                throw new IllegalArgumentException("Empleado con ID " + id_empleado + " no encontrado.");
            }
            return empleado;
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el empleado por ID: " + e.getMessage(), e);
        }
    }
}
