package com.example.Papeleria.Service;

import com.example.Papeleria.Model.Venta;
import com.example.Papeleria.Repository.VentaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VentaService {
    private final VentaRepository ventaRepository;

    public VentaService(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    public Venta guardar(Venta venta) {
        try {
            venta.setFecha(LocalDate.now());
            return ventaRepository.save(venta);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar la venta " + e.getMessage(), e);

        }
    }

    public List<Venta> listar(){
        try {
            return ventaRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar ventas: " + e.getMessage(), e);
        }
    }

    public void eliminar(Long id_ventas){
        try {
            if (!ventaRepository.existsById(id_ventas)) {
                throw new IllegalArgumentException("No se encontró una venta con el ID: " + id_ventas);
            }
            ventaRepository.deleteById(id_ventas);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar la venta: " + e.getMessage(), e);
        }
    }

    public Optional<Venta> listarPorId(long id_venta) {
        try {
            Optional<Venta> venta = ventaRepository.findById(id_venta);
            if (venta.isEmpty()) {
                throw new IllegalArgumentException("Venta con ID " + id_venta + " no encontrado.");
            }
            return venta;
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar Venta por ID: " + e.getMessage(), e);
        }
    }
}
