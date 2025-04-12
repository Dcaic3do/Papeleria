package com.example.Papeleria.Service;

import com.example.Papeleria.Model.Proveedor;
import com.example.Papeleria.Repository.ProveedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorService {
    private final ProveedorRepository proveedorRepository;

    public ProveedorService(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    public Proveedor guardar(Proveedor proveedor) {
        try {
            if (proveedor.getNombre() == null || proveedor.getNombre().isEmpty()) {
                throw new IllegalArgumentException("El nombre del proveedor no puede estar vacío");
            }
            return proveedorRepository.save(proveedor);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el proveedor " + e.getMessage(), e);
        }
    }

    public List<Proveedor> listar(){
        try {
            return proveedorRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar proveedor: " + e.getMessage(), e);
        }
    }

    public void eliminar(Long id_proveedor){
        try {
            if (!proveedorRepository.existsById(id_proveedor)) {
                throw new IllegalArgumentException("No se encontró un proveedor con el ID: " + id_proveedor);
            }
            proveedorRepository.deleteById(id_proveedor);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el proveedor: " + e.getMessage(), e);
        }
    }

    public Optional<Proveedor> listarPorId(long id_proveedor) {
        try {
            Optional<Proveedor> proveedor = proveedorRepository.findById(id_proveedor);
            if (proveedor.isEmpty()) {
                throw new IllegalArgumentException("Proveedor con ID " + id_proveedor + " no encontrado.");
            }
            return proveedor;
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el proveedor por ID: " + e.getMessage(), e);
        }
    }
}
