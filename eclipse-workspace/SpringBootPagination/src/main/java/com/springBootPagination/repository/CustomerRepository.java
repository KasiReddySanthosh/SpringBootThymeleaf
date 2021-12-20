package com.springBootPagination.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.springBootPagination.model.Customer;



public interface CustomerRepository<T> {

	Customer save(Customer customer);
	
	Page<Customer> getInfo(Pageable pageable);
	
	Page<Customer> getInfoWithPagination(int pageNumber, int pageSize);

	public void updateProfile(int id,String profile);

	public Customer getById(int id);
	
}
