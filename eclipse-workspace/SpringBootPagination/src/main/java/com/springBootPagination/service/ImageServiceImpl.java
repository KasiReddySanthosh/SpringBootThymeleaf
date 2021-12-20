package com.springBootPagination.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.springBootPagination.model.Image;
import com.springBootPagination.repository.CommonRepository;
import com.springBootPagination.repository.CustomerRepository;

@Service
public class ImageServiceImpl implements ImageService{

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CommonRepository<Image> imageRepository;
	
	
	@Override
	public Image saveImage(MultipartFile file) throws Exception {	
		String fileType = StringUtils.cleanPath(file.getOriginalFilename());
		Image image = new Image( fileType,file.getContentType(),file.getBytes());
		imageRepository.save(image);
		return image;
	}


	@Override
	public  Image getById(String id) {		
		return imageRepository.getById(id, Image.class);
	}
	
}