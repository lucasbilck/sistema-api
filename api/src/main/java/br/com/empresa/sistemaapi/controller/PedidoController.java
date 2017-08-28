package br.com.empresa.sistemaapi.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.empresa.sistemaapi.dto.PedidoDTO;
import br.com.empresa.sistemaapi.exception.BadRequestResponseException;
import br.com.empresa.sistemaapi.exception.InternalServerErrorResponseException;
import br.com.empresa.sistemaapi.service.interfaces.IPedidoService;
import br.com.empresa.sistemaapi.util.Mensagem;

@RestController
public class PedidoController {

	@Autowired
	private IPedidoService pedidoService;

	@RequestMapping(value="/pedidos", method=RequestMethod.POST, 
			produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			consumes= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Collection<PedidoDTO>> criar(@RequestBody List<PedidoDTO> listaPedidoDTO) {

		List<PedidoDTO> listaPedidoCriadoDTO;

		try {
			listaPedidoCriadoDTO = pedidoService.criar(listaPedidoDTO);

		} catch (BadRequestResponseException e) {
			throw new BadRequestResponseException(e.getMessage());

		} catch (Exception e) {
			throw new InternalServerErrorResponseException(
					Mensagem.get("msg.nao.foi.possivel.criar", new Object[]{"pedido(s)"}));
		}

		return new ResponseEntity<>(listaPedidoCriadoDTO, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/pedidos/criterios", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<PedidoDTO>> listarPorCriterio(@RequestBody PedidoDTO pedidoFiltroDTO){

		List<PedidoDTO> listaPedido;

		try {
			listaPedido = pedidoService.listarPorCriterios(pedidoFiltroDTO);

		} catch (Exception e) {
			throw new InternalServerErrorResponseException(Mensagem.get("msg.nao.foi.possivel.listar", new Object[]{"pedidos"}));
		}

		return new ResponseEntity<>(listaPedido, HttpStatus.OK);
	}
}
