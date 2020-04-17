package ch.course223.advanced.domainmodels.role;

import ch.course223.advanced.core.ExtendedNeo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends ExtendedNeo4jRepository<Role> {

}
