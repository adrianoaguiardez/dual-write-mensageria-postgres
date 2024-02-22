package br.com.aguiar.dualwrites.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;


import br.com.aguiar.dualwrites.exceptions.ObjectNotFoundException;
import br.com.aguiar.dualwrites.model.Cliente;
import br.com.aguiar.dualwrites.payload.request.ClienteRequest;

@SpringBootTest
public class ClienteRepositoryTest {

   
    @Mock
    private ClienteRepository clienteRepository;

    private Optional<Cliente> optionalCliente;

    private Cliente cliente;

    private ClienteRequest clienteRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startCliente();
    }

    @Test
    void whenFindByIdThenReturnAnClienteInstance() {
        when(clienteRepository.findById(Mockito.anyLong())).thenReturn(optionalCliente);
        Cliente response = clienteRepository.findById(1L).get();
        assertNotNull(response);
        assertEquals(Cliente.class, response.getClass());
        assertEquals(1L, response.getId());
        assertEquals("Adriano", response.getNome());
    }

    @Test
    void whenFindByIdThenReturnAnObjectFoundException() {
        when(clienteRepository.findById(anyLong())).thenThrow(new ObjectNotFoundException("Cliente não encontrado"));

        try {
            clienteRepository.findById(1L);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals("Cliente não encontrado", ex.getMessage());
        }
    }

    @Test
    void whenCreateThenreturnSucess() {
        when(clienteRepository.save(any())).thenReturn(cliente);
        Cliente response = clienteRepository.save(clienteRequest.toMap());
        assertNotNull(response);
        System.out.println(response.getNome());
        assertEquals(Cliente.class, response.getClass());
        assertEquals(1L, response.getId());
        assertEquals("Adriano", response.getNome());
    }

    private void startCliente() {
        cliente = new Cliente(1L, "Adriano");
        clienteRequest = new ClienteRequest(1L, "AdrianoAA");
        optionalCliente = Optional.of(new Cliente(1L, "Adriano"));
    }

  
}
