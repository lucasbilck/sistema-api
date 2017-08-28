package br.com.empresa.sistemaapi.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.empresa.sistemaapi.converter.PedidoConverter;
import br.com.empresa.sistemaapi.dto.PedidoDTO;
import br.com.empresa.sistemaapi.exception.BadRequestResponseException;
import br.com.empresa.sistemaapi.model.Cliente;
import br.com.empresa.sistemaapi.model.Pedido;
import br.com.empresa.sistemaapi.repository.criteria.PedidoSpecification;
import br.com.empresa.sistemaapi.repository.interfaces.IClienteRepository;
import br.com.empresa.sistemaapi.repository.interfaces.IPedidoRepository;
import br.com.empresa.sistemaapi.service.interfaces.IPedidoService;
import br.com.empresa.sistemaapi.util.Mensagem;

@Service
public class PedidoService implements IPedidoService {

	@Autowired
	private IPedidoRepository pedidoRepository;
	
	@Autowired
	private IClienteRepository clienteRepository;

	@Override
	public List<PedidoDTO> criar(List<PedidoDTO> listaPedidoDTO) throws Exception {

		Pedido pedidoCriar;
		List<PedidoDTO> listaPedidoCriado = new ArrayList<>();

		if(listaPedidoDTO.size() > 10) {
			throw new BadRequestResponseException(Mensagem.get("msg.nao.foi.possivel.criar.maximo.dez.pedidos", new Object[]{"pedido"}));
		}

		for (PedidoDTO pedidoDTO : listaPedidoDTO) {
			if (pedidoDTO.getNumeroControle() == null || pedidoDTO.getNumeroControle() == 0
					|| pedidoDTO.getNome() == null || "".equals(pedidoDTO.getNome())
					|| pedidoDTO.getValor() == null || pedidoDTO.getValor() == 0
					|| pedidoDTO.getCodigoCliente() == null || pedidoDTO.getCodigoCliente() == 0
				) {

				throw new BadRequestResponseException(Mensagem.get("msg.nao.foi.possivel.criar.campos.obrigatorios", new Object[]{"pedido(s)"}));
			}
		}

		for (PedidoDTO pedidoDTO : listaPedidoDTO) {
			if (!pedidoRepository.existsByNumeroControle(pedidoDTO.getNumeroControle())) {

				Cliente cliente = clienteRepository.findOneByCodigo(pedidoDTO.getCodigoCliente());

				if (cliente != null) {
					pedidoCriar = PedidoConverter.paraEntidade(pedidoDTO);
					pedidoCriar.setIdCliente(cliente.getIdCliente());

					if (pedidoDTO.getDataCadastro() == null) {
						pedidoCriar.setDataCadastro(Calendar.getInstance().getTime());
					}

					if (pedidoDTO.getQuantidade() == null || pedidoDTO.getQuantidade() == 0) {
						pedidoCriar.setQuantidade(1l);
					}

					pedidoCriar.setValorTotal(pedidoCriar.getValor() * pedidoCriar.getQuantidade());

					if (pedidoCriar.getQuantidade() >= 10) {
						pedidoCriar.setValorTotal(pedidoCriar.getValorTotal() - (pedidoCriar.getValorTotal() * 0.1));

					} else if (pedidoCriar.getQuantidade() > 5) {
						pedidoCriar.setValorTotal(pedidoCriar.getValorTotal() - (pedidoCriar.getValorTotal() * 0.05));
					}

					listaPedidoCriado.add(PedidoConverter.paraDTO(pedidoRepository.save(pedidoCriar)));					
				}
			}
		}

		return listaPedidoCriado;
	}

	@Override
	public List<PedidoDTO> listarPorCriterios(PedidoDTO pedidoFiltroDTO) throws Exception {

		List<PedidoDTO> listaPedido = new ArrayList<>();

		Specification<Pedido> criterios = PedidoSpecification.byCriterio(
			PedidoConverter.paraEntidade(pedidoFiltroDTO)
		);

		for (Pedido pedido : pedidoRepository.findAll(criterios)) {

			PedidoDTO pedidoDTO = PedidoConverter.paraDTO(pedido);			

			Cliente cliente = clienteRepository.findOne(pedido.getIdCliente());
			pedidoDTO.setCodigoCliente(cliente.getCodigo());

			listaPedido.add(pedidoDTO);
		}

		return listaPedido;
	}
}
