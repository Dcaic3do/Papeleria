package com.example.Papeleria.Controller;

import com.example.Papeleria.Model.DetalleVenta;
import com.example.Papeleria.Service.DetalleVentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalle")
public class DetalleVentaController {
    private final DetalleVentaService detalleVentaService;

    public DetalleVentaController(DetalleVentaService detalleVentaService) {
        this.detalleVentaService = detalleVentaService;
    }

    @PostMapping("/guardar")
    public ResponseEntity<DetalleVenta> guardarDetalle(@RequestBody DetalleVenta detalleVenta) {
        return ResponseEntity.ok(detalleVentaService.guardar(detalleVenta));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<DetalleVenta>> listarDetalles() {
        return ResponseEntity.ok(detalleVentaService.listar());
    }

    @GetMapping("/listar/{id_detalle}")
    public ResponseEntity<DetalleVenta> obtenerDetallePorId(@PathVariable long id_detalle) {
        return detalleVentaService.listarPorId(id_detalle)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{id_detalle}")
    public ResponseEntity<Void> eliminarDetalle(@PathVariable long id_detalle) {
        detalleVentaService.eliminar(id_detalle);
        return ResponseEntity.noContent().build();
    }
}
