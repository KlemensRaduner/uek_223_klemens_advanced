package ch.course223.advanced.domainmodels.user;

import ch.course223.advanced.core.ExtendedNeo4jRepository;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends ExtendedNeo4jRepository<User> {

    @Query("MATCH (u:User) where u.email = email RETURN u LIMIT 1")
    Optional<User> findByEmail(@Param("email") String email);
}
