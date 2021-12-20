package com.springBootPagination.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springBootPagination.model.Image;
import com.springBootPagination.service.CustomerService;
import com.springBootPagination.service.ImageService;

@RestController
@RequestMapping("/image")
public class ImageController<T> {

	@Autowired
	ImageService<T> imageService;
	
	@Autowired
	CustomerService customerService;

	@PostMapping("/save")
	public ResponseEntity<String> saveImage(
			@RequestPart("file") MultipartFile file) throws Exception {

		Image image = imageService.saveImage(file);
		String imageId = image.getId();
		
		return new ResponseEntity<String>(imageId, HttpStatus.OK);
	}
	
	@GetMapping(value ="/get/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
	public  ResponseEntity<byte[]> getById(@PathVariable String id){
		Image image = imageService.getById(id);
		byte[] imgbyte = image.getImage();	
		return new ResponseEntity<byte[]>(imgbyte,HttpStatus.OK);		
	}
	
}
