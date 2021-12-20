package com.springBootPagination.dto;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import com.springBootPagination.controller.ImageController;
import com.springBootPagination.model.Customer;


@Component
public class UserAssembler implements RepresentationModelAssembler<Customer, EntityModel<Customer>> {

  
	@Override
	public EntityModel<Customer> toModel(Customer entity) {
		 EntityModel<Customer> res = EntityModel.of(entity);
		 userImagelink(res);
		 return res;
	}

	private void userImagelink(EntityModel<Customer> res) {
		 res.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ImageController.class).getById(res.getContent().getProfile()))
				  .withRel("UserImage"));
		
	}

	
}
