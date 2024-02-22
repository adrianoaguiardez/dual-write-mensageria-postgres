package br.com.aguiar.dualwrites.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "item_pedido")
public class ItemPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "quantidade")
	private BigDecimal quantidade;

	@Column(name = "preco_unitario")
	private BigDecimal precoUnitario;

	@Column(name = "preco_total")
	private BigDecimal precoTotal;

	@ManyToOne
	@JoinColumn(name = "id_pedido")
	private Pedido pedido;

	@ManyToOne
	@JoinColumn(name = "id_produto")
	private Produto produto;

	public ItemPedido(Produto produto) {
		this.produto = produto;
	}

	public ItemPedido(Produto produto, BigDecimal quantidade) {
		this.produto = produto;
		this.precoUnitario = produto.getPreco();
		this.quantidade = quantidade;
		calcularTotal();

	}

	public void calcularTotal() {

		this.precoTotal = this.precoUnitario.multiply(quantidade);

	}

	public void atualizarQuantidade(BigDecimal novaQuantidade) {
		this.quantidade = novaQuantidade;
		calcularTotal();
	}
}