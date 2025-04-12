package com.example.Papeleria.Controller;

import com.example.Papeleria.Model.Proveedor;
import com.example.Papeleria.Service.ProveedorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proveedor")
public class ProveedorController {
    private final ProveedorService proveedorService;

    public ProveedorController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    @PostMapping("/guardar")
    public ResponseEntity<Proveedor> guardarProveedor(@RequestBody Proveedor proveedor) {
        return ResponseEntity.ok(proveedorService.guardar(proveedor));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Proveedor>> listarProveedor() {
        return ResponseEntity.ok(proveedorService.listar());
    }

    @GetMapping("/listar/{id_proveedor}")
    public ResponseEntity<Proveedor> obtenerProveedorPorId(@PathVariable long id_proveedor) {
        return proveedorService.listarPorId(id_proveedor)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{id_proveedor}")
    public ResponseEntity<Void> eliminarProveedor(@PathVariable long id_proveedor) {
        proveedorService.eliminar(id_proveedor);
        return ResponseEntity.noContent().build();
    }
}
