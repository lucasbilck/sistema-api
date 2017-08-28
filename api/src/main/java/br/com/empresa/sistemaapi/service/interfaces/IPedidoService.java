package br.com.empresa.sistemaapi.service.interfaces;

import java.util.List;

import br.com.empresa.sistemaapi.dto.PedidoDTO;

public interface IPedidoService {

	List<PedidoDTO> criar(List<PedidoDTO> listaPedidoTO) throws Exception;
	List<PedidoDTO> listarPorCriterios(PedidoDTO pedidoDTO) throws Exception;
}
