package com.springBootPagination.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.springBootPagination.model.Customer;

@Repository
@Transactional
public class CustomerRepositoryImpl<T> implements CustomerRepository<T> {

	@Autowired
	EntityManager em;
	
	@Override
	public Customer save(Customer customer) {
		em.persist(customer);
		return customer;
	}

	@Override
	public Page<Customer> getInfo(Pageable pageable) {
		 
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Long> countQuery = cb.createQuery(Long.class); 
			countQuery.select(cb.count(countQuery.from(Customer.class)));
			long count = em.createQuery(countQuery).getSingleResult();
			
			CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
			Root<Customer> r = cq.from(Customer.class);
			cq.select(r);
			TypedQuery<Customer> query = em.createQuery(cq);
			query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
			query.setMaxResults(pageable.getPageSize());
			
			List<Customer> result = query.getResultList();
			
			Page<Customer> page = new PageImpl<Customer>(result, pageable, count);
			return page;
	}

	@Override
	public Page<Customer> getInfoWithPagination(int pageNumber, int pageSize) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
		Root<Customer> root = cq.from(Customer.class);
		cq.select(root);
		
		TypedQuery<Customer> typedQuery = em.createQuery(cq);
		typedQuery.setFirstResult(pageNumber-1);
		typedQuery.setMaxResults(pageSize);
		List<Customer> list = typedQuery.getResultList();
		Page<Customer> page = new PageImpl<Customer>(list);
		return page;
		
	}

	@Override
	public void updateProfile(int id,String profile) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaUpdate<Customer> cu = cb.createCriteriaUpdate(Customer.class);
		Root<Customer> root = cu.from(Customer.class);
		cu.set(root.get("profile"), profile).where(cb.equal(root.get("id"), id));
		 em.createQuery(cu).executeUpdate();		 
	}

	@Override
	public Customer getById(int id) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
		Root<Customer> root = cq.from(Customer.class);
		cq.select(root);
		cq.where(cb.equal(root.get("id"), id));		
		Customer result = em.createQuery(cq).getSingleResult();		
		return result;
	}
}
