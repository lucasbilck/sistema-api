package br.com.empresa.sistemaapi.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.empresa.sistemaapi.model.Cliente;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Long> {

	Cliente findOneByCodigo(Long codigo);
}
