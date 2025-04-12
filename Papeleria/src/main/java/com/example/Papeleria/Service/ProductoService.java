package com.example.Papeleria.Service;

import com.example.Papeleria.Model.Producto;
import com.example.Papeleria.Repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {
    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public Producto guardar(Producto producto) {
        try {
            if (producto.getNombre() == null || producto.getNombre().isEmpty()) {
                throw new IllegalArgumentException("El nombre del ptoducto no puede estar vacío");
            }
            return productoRepository.save(producto);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el cliente " + e.getMessage(), e);

        }
    }

    public List<Producto> listar(){
        try {
            return productoRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar productos: " + e.getMessage(), e);
        }
    }

    public void eliminar(Long id_producto){
        try {
            if (!productoRepository.existsById(id_producto)) {
                throw new IllegalArgumentException("No se encontró un producto con el ID: " + id_producto);
            }
            productoRepository.deleteById(id_producto);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el producto: " + e.getMessage(), e);
        }
    }

    public Optional<Producto> listarPorId(long id_producto) {
        try {
            Optional<Producto> producto = productoRepository.findById(id_producto);
            if (producto.isEmpty()) {
                throw new IllegalArgumentException("Producto con ID " + id_producto + " no encontrado.");
            }
            return producto;
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el produtco por ID: " + e.getMessage(), e);
        }
    }
}
