package com.lotes.lotesbackend.service;

import com.lotes.lotesbackend.dto.CotaDTO;

import java.util.List;
import java.util.Optional;

public interface BaseService <T, ID>{
	public List<T> findAll();

	public List<T> findAll(Integer estatusId);

	public Optional<T> findById(Long ID);

	public T save(T entity);

	public T update(T entity);

	public T delete(Long ID);
}
