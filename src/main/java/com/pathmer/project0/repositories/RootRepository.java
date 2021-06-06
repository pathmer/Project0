package com.pathmer.project0.repositories;

import java.util.List;

public interface RootRepository<T> {
	
	// CREATE
	public T add(T t);
	
	// READ
	public T getById(Integer id);
	
	public List<T> getAll();
	
	// UPDATE
	public boolean update(T t);
	
	// DELETE
	public boolean delete(T t);
}
