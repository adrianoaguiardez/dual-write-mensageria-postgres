package br.com.aguiar.dualwrites.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NamedQuery(name = "Pedido.pedidosAbertos", query = "select p from Pedido p where p.statusPedido = 'A'")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "pedido")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "valor_total")
	private BigDecimal total = BigDecimal.ZERO;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private StatusPedido statusPedido = StatusPedido.A;

	@Column(name = "data_pedido")
	private LocalDateTime dataPedido = LocalDateTime.now();

	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;

	@JsonIgnore
	@OneToMany(mappedBy = "pedido")
	private List<ItemPedido> produtos = new ArrayList<>();

	public void adicionarItem(Produto produto, BigDecimal quantidade) {

		ItemPedido itemExistente = getItem(produto);
		if (itemExistente != null) {

			atualizarQuantidade(produto, itemExistente.getQuantidade().add(quantidade));
		} else {

			getProdutos().add(new ItemPedido(produto, quantidade));
			calcularTotal();
		}
	}

	public ItemPedido getItem(Produto produto) {
		ItemPedido itemAProcurar = new ItemPedido(produto);

		for (ItemPedido item : getProdutos()) {

			if (item.equals(itemAProcurar)) {
				return item;

			}
		}
		return null;
	}

	public void removerItem(Produto produto) {
		getProdutos().remove(new ItemPedido(produto));
		calcularTotal();

	}

	public void calcularTotal() {

		BigDecimal valorTotal = BigDecimal.ZERO;

		total = BigDecimal.ZERO;

		for (ItemPedido item : getProdutos()) {

			item.calcularTotal();
			valorTotal = valorTotal.add(new BigDecimal(item.getPrecoTotal().doubleValue()));
		}

		this.total = valorTotal;

	}

	public void atualizarQuantidade(Produto produto, BigDecimal novaQuantidade) {
		ItemPedido item = getItem(produto);
		if (item == null) {
			throw new IllegalArgumentException("Item nao encontrado para produto " + produto);
		}

		item.atualizarQuantidade(novaQuantidade);
		calcularTotal();
	}

}