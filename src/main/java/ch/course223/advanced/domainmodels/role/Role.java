package ch.course223.advanced.domainmodels.role;

import ch.course223.advanced.core.ExtendedNodeEntity;
import ch.course223.advanced.domainmodels.authority.Authority;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

@NodeEntity
public class Role extends ExtendedNodeEntity {

    @Property("name")
    private String name;

    @Relationship(type = "HAS_AUTHORITY")
    private List<Authority> authorities;

    public Role() {
        super();
    }

    public String getName() {
        return name;
    }

    public Role setName(String name) {
        this.name = name;
        return this;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public Role setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
        return this;
    }
}
