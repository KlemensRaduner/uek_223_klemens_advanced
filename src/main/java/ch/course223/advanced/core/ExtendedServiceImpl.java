package ch.course223.advanced.core;

import ch.course223.advanced.error.BadRequestException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public abstract class ExtendedServiceImpl<T extends ExtendedNodeEntity> implements ExtendedService<T> {

	protected ExtendedNeo4jRepository<T> repository;


	public ExtendedServiceImpl(ExtendedNeo4jRepository<T> repository) {
		this.repository = repository;
	}

	@Override
	public T save(T entity) {
		return repository.save(entity);
	}

	@Override
	public Void deleteById(long id) throws NoSuchElementException {
		if(repository.existsById(id)) {
			repository.deleteById(id);
		} else {
			throw new NoSuchElementException(String.format("Entity with ID '%s' could not be found", id));
		}
		return null;
	}

	@Override
	public T updateById(long id, T entity) throws NoSuchElementException, BadRequestException {
		if(repository.existsById(id)) {
			checkUpdatedEntityId(id, entity);

			entity.setId(id);
			return repository.save(entity);
		} else {
			throw new NoSuchElementException(String.format("Entity with ID '%s' could not be found", id));
		}
	}

	@Override
	public List<T> findAll() {
		return (List)repository.findAll();
	}

	@Override
	public T findById(long id) {
		return findOrThrow(repository.findById(id));
	}

	@Override
	public boolean existsById(long id) {
		return repository.existsById(id);
	}

	protected T findOrThrow(Optional<T> optional) throws NoSuchElementException {
		if(optional.isPresent()) {
			return optional.get();
		} else {
			throw new NoSuchElementException("No value present");
		}
	}

	protected void checkUpdatedEntityId(long id, T entity) throws BadRequestException {

		if(entity.getId() != 0) {
			if(id != entity.getId()) {
				throw new BadRequestException(String.format("Path variable ID '%s' and Request body ID '%s' are not equal", id, entity.getId()));
			}
		}
	}

}
