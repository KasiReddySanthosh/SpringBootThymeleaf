package com.springBootPagination.repository;

public interface CommonRepository<T> {

	public T save(T entity);
	
	public T getById(String id,Class<T> c);
	
}
