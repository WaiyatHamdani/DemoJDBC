package com.fdmgroup.demojdbc.data;

import java.util.List;

public interface Crudable<T, U> {

	boolean create(T t);
	T read(U u);
	boolean update(T t);
	boolean delete(U u);
	List<T> read();
	
	
}
