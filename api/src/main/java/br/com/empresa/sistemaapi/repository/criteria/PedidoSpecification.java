package br.com.empresa.sistemaapi.repository.criteria;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import br.com.empresa.sistemaapi.model.Pedido;

public class PedidoSpecification {

	public static Specification<Pedido> byCriterio(Pedido pedido) {

		return new Specification<Pedido>() {

			@Override
			public Predicate toPredicate(Root<Pedido> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

				List<Predicate> predicates = new ArrayList<>();

				if(pedido.getNumeroControle() != null && pedido.getNumeroControle() > 0){
					predicates.add(builder.equal(root.<String>get("numeroControle"), pedido.getNumeroControle()));
				}
				
				if(pedido.getDataCadastro() != null){
					predicates.add(builder.equal(root.<Date>get("dataCadastro"), pedido.getDataCadastro()));
				}

				return builder.and(predicates.toArray(new Predicate[]{}));
			}
		};
	}
}
