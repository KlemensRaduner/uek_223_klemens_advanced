package ch.course223.advanced.domainmodels.relations;

import ch.course223.advanced.domainmodels.authority.Authority;
import ch.course223.advanced.domainmodels.role.Role;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "HAS_AUTHORITY")
public class HasAuthority {

    @StartNode
    private Role role;

    @EndNode
    private Authority authority;
}
