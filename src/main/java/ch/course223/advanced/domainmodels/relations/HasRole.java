package ch.course223.advanced.domainmodels.relations;

import ch.course223.advanced.domainmodels.role.Role;
import ch.course223.advanced.domainmodels.user.User;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "HAS_ROLE")
public class HasRole {

    @StartNode
    private User user;

    @EndNode
    private Role role;
}
