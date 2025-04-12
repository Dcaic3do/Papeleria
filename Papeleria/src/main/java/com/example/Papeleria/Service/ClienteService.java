package com.example.Papeleria.Service;

import com.example.Papeleria.Model.Cliente;
import com.example.Papeleria.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente guardar(Cliente cliente) {
        try {
            if (cliente.getNombre() == null || cliente.getNombre().isEmpty()) {
                throw new IllegalArgumentException("El nombre del cliente no puede estar vacío");
            }
            return clienteRepository.save(cliente);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el cliente " + e.getMessage(), e);

        }
    }

    public List<Cliente> listar(){
            try {
                return clienteRepository.findAll();
            } catch (Exception e) {
                throw new RuntimeException("Error al listar clientes: " + e.getMessage(), e);
            }
    }

    public void eliminar(Long id_cliente){
        try {
            if (!clienteRepository.existsById(id_cliente)) {
                throw new IllegalArgumentException("No se encontró un cliente con el ID: " + id_cliente);
            }
            clienteRepository.deleteById(id_cliente);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el cliente: " + e.getMessage(), e);
        }
    }

    public Optional<Cliente> listarPorId(long id_cliente) {
        try {
            Optional<Cliente> cliente = clienteRepository.findById(id_cliente);
            if (cliente.isEmpty()) {
                throw new IllegalArgumentException("Cliente con ID " + id_cliente + " no encontrado.");
            }
            return cliente;
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el Cliente por ID: " + e.getMessage(), e);
        }
    }
}
