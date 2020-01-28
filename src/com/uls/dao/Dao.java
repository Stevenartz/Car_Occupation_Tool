package com.uls.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

	Optional<T> selectById(int id);
	
	List<T> selectAll();
	
	boolean insert(T t);
	
	boolean update(T t);
	
	boolean delete(T t);
	
}
