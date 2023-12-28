package com.lotes.lotesbackend.service;

import java.util.List;
import java.util.Optional;

public interface BaseService <T, ID>{
public List<T> findAll();
	
	public Optional<T> findById(Long ID);
	
	public T save(T entity);
	
	public T update(T entity);
}
