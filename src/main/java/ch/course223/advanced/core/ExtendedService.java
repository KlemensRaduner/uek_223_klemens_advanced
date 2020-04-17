package ch.course223.advanced.core;

import java.util.List;

public interface ExtendedService<T extends ExtendedNodeEntity> {

	T save(T entity);

	T updateById(long id, T entity);

	Void deleteById(long id);

	List<T> findAll();

	T findById(long id);

	boolean existsById(long id);
}