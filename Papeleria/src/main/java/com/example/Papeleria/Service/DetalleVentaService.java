package com.example.Papeleria.Service;

import com.example.Papeleria.Model.DetalleVenta;
import com.example.Papeleria.Repository.DetalleVentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleVentaService {
    private final DetalleVentaRepository detalleVentaRepository;

    public DetalleVentaService(DetalleVentaRepository detalleVentaRepository) {
        this.detalleVentaRepository = detalleVentaRepository;
    }

    public DetalleVenta guardar(DetalleVenta detalleVenta) {
        try {
            return detalleVentaRepository.save(detalleVenta);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el detalle de venta " + e.getMessage(), e);

        }
    }

    public List<DetalleVenta> listar(){
        try {
            return detalleVentaRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar detalle de venta: " + e.getMessage(), e);
        }
    }

    public void eliminar(Long id_detalle){
        try {
            if (!detalleVentaRepository.existsById(id_detalle)) {
                throw new IllegalArgumentException("No se encontró un detalle de venta con el ID: " + id_detalle);
            }
            detalleVentaRepository.deleteById(id_detalle);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el detalle de venta: " + e.getMessage(), e);
        }
    }

    public Optional<DetalleVenta> listarPorId(long id_detalle) {
        try {
            Optional<DetalleVenta> detalleVenta = detalleVentaRepository.findById(id_detalle);
            if (detalleVenta.isEmpty()) {
                throw new IllegalArgumentException("Detalle de venta con ID " + id_detalle + " no encontrado.");
            }
            return detalleVenta;
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el detalle de venta por ID: " + e.getMessage(), e);
        }
    }
}
