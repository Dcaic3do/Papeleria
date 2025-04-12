package com.example.Papeleria.Controller;

import com.example.Papeleria.Model.Venta;
import com.example.Papeleria.Service.VentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/venta")
public class VentaController {
    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @PostMapping("/guardar")
    public ResponseEntity<Venta> guardarVenta(@RequestBody Venta venta) {
        return ResponseEntity.ok(ventaService.guardar(venta));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Venta>> listarVenta() {
        return ResponseEntity.ok(ventaService.listar());
    }

    @GetMapping("/listar/{id_venta}")
    public ResponseEntity<Venta> obtenerClientePorId(@PathVariable long id_venta) {
        return ventaService.listarPorId(id_venta)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{id_venta}")
    public ResponseEntity<Void> eliminarVenta(@PathVariable long id_venta) {
        ventaService.eliminar(id_venta);
        return ResponseEntity.noContent().build();
    }
}
