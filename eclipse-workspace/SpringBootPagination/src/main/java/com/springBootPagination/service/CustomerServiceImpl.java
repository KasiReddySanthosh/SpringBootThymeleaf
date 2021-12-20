package com.springBootPagination.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springBootPagination.model.Customer;
import com.springBootPagination.repository.CommonRepository;
import com.springBootPagination.repository.CustomerRepository;

@Service
public class CustomerServiceImpl<T> implements CustomerService {

	@Autowired
	CustomerRepository<T> customerRepository;
	
	@Autowired
	CommonRepository<T> imageRepositoty;
	
	@Override
	public Customer customerSave(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Page<Customer> getInfo(Pageable pageable) {
		
		return customerRepository.getInfo(pageable);	
	}

	@Override
	public Page<Customer> getInfowithPagination(int pageNumber, int pageSize) {	
		return customerRepository.getInfoWithPagination(pageNumber, pageSize);
	}

	@Override
	public void updateProfile(int id, String profile) {		
		 customerRepository.updateProfile(id, profile);
	}

	@Override
	public Customer getById(int id) {		
		return customerRepository.getById(id);
	}
	
}
