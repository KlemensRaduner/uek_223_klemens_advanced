package ch.course223.advanced.domainmodels.authority;

import ch.course223.advanced.core.ExtendedNodeEntity;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;


@NodeEntity
public class Authority extends ExtendedNodeEntity {

  @Property("name")
  private String name;

  public Authority() {
    super();
  }

  public String getName() {
    return name;
  }

  public Authority setName(String name) {
    this.name = name;
    return this;
  }
}
