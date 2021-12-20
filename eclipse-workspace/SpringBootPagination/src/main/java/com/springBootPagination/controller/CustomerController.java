package com.springBootPagination.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springBootPagination.dto.UserAssembler;
import com.springBootPagination.model.Customer;
import com.springBootPagination.service.CustomerService;


@RestController
@RequestMapping("/customers")
public class CustomerController<T> {

	@Autowired
	CustomerService customerService;
	
	@Autowired
	UserAssembler userAssembler;
    
	@PostMapping("/save")
	public Customer createCustomer(@RequestBody Customer customer) {
		Customer create = customerService.customerSave(customer);
		
		return create;		
	}
	
	@GetMapping(value = "/getCustomers",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PagedModel<EntityModel<Customer>>> getCustomers(Pageable pageable,PagedResourcesAssembler assembler ){
		
		Page<Customer> page = customerService.getInfo(pageable);
		
		PagedModel<EntityModel<Customer>> resources = assembler.toModel(page,userAssembler);
		return new ResponseEntity<PagedModel<EntityModel<Customer>>>(resources, HttpStatus.ACCEPTED);	 
	}
	
	
	@GetMapping("/page/{pageNumber}/{pageSize}")
	public Page<Customer> getInfoPagination(@PathVariable int pageNumber,@PathVariable int pageSize){
		
		return customerService.getInfowithPagination(pageNumber, pageSize);	
	}
	
	@PutMapping("/update/{id}")
	public void updateImage(@PathVariable int id, @RequestParam String profile){
		customerService.updateProfile(id, profile);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<EntityModel<Customer>> get(@PathVariable int id){
		Customer customer = customerService.getById(id);		
		return new ResponseEntity<EntityModel<Customer>>(userAssembler.toModel(customer),HttpStatus.OK);			
	}
		
}

