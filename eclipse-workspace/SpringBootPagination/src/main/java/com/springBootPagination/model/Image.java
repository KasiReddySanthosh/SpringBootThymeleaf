package com.springBootPagination.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Data
@Table(name= "ImageProfile")
public class Image {
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false)
    private String id;
	
	private String name;
	
	private String fileType;
	
	@Lob
	private byte[] image;
	
	public Image() {
		
	}
	
	public Image(String name, String fileType, byte[] image) {
		super();
		this.name = name;
		this.fileType = fileType;
		this.image = image;
	}
	
	public Image(String id, String name, String fileType, byte[] image) {
		super();
		this.id = id;
		this.name = name;
		this.fileType = fileType;
		this.image = image;
	}


	
}
