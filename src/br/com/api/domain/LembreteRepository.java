package br.com.api.domain;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.api.exception.ApiException;
import br.com.api.orm.ConnectionFactory;

public class LembreteRepository {

	public static final int PAGE_LENGTH = 5;

	public List<Lembrete> getByRange(int page) throws ApiException {
		TypedQuery<Lembrete> listQuery = null;
		try {
			listQuery = getTypedQuery();
			listQuery.setFirstResult((page - 1) * PAGE_LENGTH);
			listQuery.setMaxResults(PAGE_LENGTH);
		} catch (Exception e) {
			throw new ApiException(500, "Erro ao buscar os dados no banco de dados. " + e.getMessage());
		}
		return listQuery.getResultList();
	}

	public List<Lembrete> getAll() throws ApiException {

		TypedQuery<Lembrete> listQuery = null;

		try {
			listQuery = getTypedQuery();
			listQuery.setMaxResults(PAGE_LENGTH);
			} catch (Exception e) {
			throw new ApiException(500, "Erro ao buscar os dados no banco de dados. " + e.getMessage());
		}

		return listQuery.getResultList();
	}

	private TypedQuery<Lembrete> getTypedQuery() {

		EntityManager em = ConnectionFactory.getEntityManager();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Lembrete> cQuery = builder.createQuery(Lembrete.class);
		Root<Lembrete> from = cQuery.from(Lembrete.class);
		CriteriaQuery<Lembrete> select = cQuery.select(from);
		TypedQuery<Lembrete> listQuery = em.createQuery(select);

		return listQuery;
	}

}
