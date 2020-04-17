package ch.course223.advanced.core;

import org.springframework.data.repository.CrudRepository;

public interface ExtendedNeo4jRepository<T extends ExtendedNodeEntity> extends CrudRepository<T, Long> {

}
