package ch.course223.advanced.domainmodels.authority;

import ch.course223.advanced.core.ExtendedNeo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends ExtendedNeo4jRepository<Authority> {

}
