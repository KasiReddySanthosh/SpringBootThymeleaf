package com.springBootPagination.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.springBootPagination.model.Customer;

public interface CustomerService {

	
	Customer customerSave(Customer customer);
	
	Page<Customer> getInfo(Pageable pageable);
	
	Page<Customer> getInfowithPagination(int pageNumber, int pageSize);

	public void updateProfile(int id, String profile);
	
	Customer getById(int id);
}
