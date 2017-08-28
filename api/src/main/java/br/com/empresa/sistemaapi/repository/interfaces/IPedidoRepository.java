package br.com.empresa.sistemaapi.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.com.empresa.sistemaapi.model.Pedido;

@Repository
public interface IPedidoRepository extends JpaRepository<Pedido, Long>, JpaSpecificationExecutor<Pedido> {

	boolean existsByNumeroControle(Long numeroControle);
}
