package com.springBootPagination.service;

import org.springframework.web.multipart.MultipartFile;

import com.springBootPagination.model.Image;

public interface ImageService<T> {

	Image saveImage( MultipartFile image) throws Exception;
	
	Image getById(String id);

}
