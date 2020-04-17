package ch.course223.advanced.domainmodels.role;

import ch.course223.advanced.core.ExtendedNeo4jRepository;
import ch.course223.advanced.core.ExtendedServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends ExtendedServiceImpl<Role> implements  RoleService {
    public RoleServiceImpl(ExtendedNeo4jRepository<Role> repository) {
        super(repository);
    }
}
