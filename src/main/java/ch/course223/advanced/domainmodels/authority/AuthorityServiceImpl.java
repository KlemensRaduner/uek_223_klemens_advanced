package ch.course223.advanced.domainmodels.authority;

import ch.course223.advanced.core.ExtendedNeo4jRepository;
import ch.course223.advanced.core.ExtendedServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl extends ExtendedServiceImpl<Authority> implements AuthorityService {
    public AuthorityServiceImpl(ExtendedNeo4jRepository<Authority> repository) {
        super(repository);
    }
}
