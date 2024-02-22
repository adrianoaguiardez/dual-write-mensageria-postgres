package br.com.aguiar.dualwrites.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.aguiar.dualwrites.exceptions.ObjectNotFoundException;
import br.com.aguiar.dualwrites.model.Produto;
import br.com.aguiar.dualwrites.payload.request.ProdutoRequest;

@SpringBootTest
public class ProdutoRepositoryTest {
    
    @Mock
    private ProdutoRepository produtoRepository;

    private Optional<Produto> optionalProduto;

    private Produto produto;

    private ProdutoRequest produtoRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startProduto();
    }

    @Test
    void whenFindByIdThenReturnAnProdutoInstance() {
        when(produtoRepository.findById(Mockito.anyLong())).thenReturn(optionalProduto);
        Produto response = produtoRepository.findById(1L).get();
        assertNotNull(response);
        assertEquals(Produto.class, response.getClass());
        assertEquals(1L, response.getId());
        assertEquals("Arroz", response.getNome());
    }

    @Test
    void whenFindByIdThenReturnAnObjectFoundException() {
        when(produtoRepository.findById(anyLong())).thenThrow(new ObjectNotFoundException("Produto não encontrado"));

        try {
            produtoRepository.findById(1L);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals("Produto não encontrado", ex.getMessage());
        }
    }

    @Test
    void whenCreateThenreturnSucess() {
        when(produtoRepository.save(any())).thenReturn(produto);
        Produto response = produtoRepository.save(produtoRequest.toMap());
        assertNotNull(response);
        System.out.println(response.getNome());
        assertEquals(Produto.class, response.getClass());
        assertEquals(1L, response.getId());
        assertEquals(new BigDecimal(10), response.getQuantidade());
        assertEquals("Arroz", response.getNome());
    }

    private void startProduto() {
        produto = new Produto(1L, "Arroz", new BigDecimal("10"), new BigDecimal("10"));
        produtoRequest = new ProdutoRequest(1L, "Arroz", new BigDecimal("10"), new BigDecimal("10"));
        optionalProduto = Optional.of(new Produto(1L, "Arroz",  new BigDecimal("10"), new BigDecimal("10")));
    }

  
}
