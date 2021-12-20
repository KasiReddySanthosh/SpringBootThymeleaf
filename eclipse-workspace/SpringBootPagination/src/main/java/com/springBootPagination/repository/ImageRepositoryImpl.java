package com.springBootPagination.repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
@Transactional
public class ImageRepositoryImpl<T> implements CommonRepository<T> {

	@Autowired
	EntityManager em;

	@Override
	public T save(T entity) {
		em.persist(entity);
		return entity;
	}

	@Override
	public T getById(String id,Class<T> c) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(c);
		Root<T> root = cq.from(c);
		cq.where(cb.equal(root.get("id"),id));	
		T result = em.createQuery(cq).getSingleResult();
		return result;		
	}


}
