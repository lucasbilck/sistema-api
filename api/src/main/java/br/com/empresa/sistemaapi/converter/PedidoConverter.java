package br.com.empresa.sistemaapi.converter;

import br.com.empresa.sistemaapi.dto.PedidoDTO;
import br.com.empresa.sistemaapi.model.Pedido;

public class PedidoConverter {

	public static Pedido paraEntidade(PedidoDTO pedidoDTO) {

		Pedido pedido = new Pedido();

		pedido.setNumeroControle(pedidoDTO.getNumeroControle());
		pedido.setDataCadastro(pedidoDTO.getDataCadastro());
		pedido.setNome(pedidoDTO.getNome());
		pedido.setValor(pedidoDTO.getValor());
		pedido.setQuantidade(pedidoDTO.getQuantidade());

		return pedido;
	}

	public static PedidoDTO paraDTO(Pedido pedido) {

		PedidoDTO pedidoDTO = new PedidoDTO();

		pedidoDTO.setNumeroControle(pedido.getNumeroControle());
		pedidoDTO.setDataCadastro(pedido.getDataCadastro());
		pedidoDTO.setNome(pedido.getNome());
		pedidoDTO.setValor(pedido.getValor());
		pedidoDTO.setQuantidade(pedido.getQuantidade());
		pedidoDTO.setValorTotal(pedido.getValorTotal());

		return pedidoDTO;
	}
}
