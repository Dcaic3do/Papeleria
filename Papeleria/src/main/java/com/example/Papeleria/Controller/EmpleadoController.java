package com.example.Papeleria.Controller;

import com.example.Papeleria.Model.Empleado;
import com.example.Papeleria.Service.EmpleadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empleado")
public class EmpleadoController {
    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @PostMapping("/guardar")
    public ResponseEntity<Empleado> guardarEmpleado(@RequestBody Empleado empleado) {
        return ResponseEntity.ok(empleadoService.guardar(empleado));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Empleado>> listarEmpleados() {
        return ResponseEntity.ok(empleadoService.listar());
    }

    @GetMapping("/listar/{id_empleado}")
    public ResponseEntity<Empleado> obtenerEmpleadoPorId(@PathVariable long id_empleado) {
        return empleadoService.listarPorId(id_empleado)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{id_empleado}")
    public ResponseEntity<Void> eliminarEmpleado(@PathVariable long id_empleado) {
        empleadoService.eliminar(id_empleado);
        return ResponseEntity.noContent().build();
    }
}
