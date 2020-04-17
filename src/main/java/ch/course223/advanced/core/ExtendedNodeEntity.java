package ch.course223.advanced.core;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;


public abstract class ExtendedNodeEntity {

	@Id
	@GeneratedValue
	private Long id;

	public ExtendedNodeEntity() {	}

	public ExtendedNodeEntity(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public ExtendedNodeEntity setId(long id) {
		this.id = id;
		return this;
	}
}