package ch.course223.advanced.domainmodels.user;

import ch.course223.advanced.core.ExtendedNodeEntity;
import ch.course223.advanced.domainmodels.role.Role;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;


@NodeEntity
public class User extends ExtendedNodeEntity {

    @Property("email")
    private String email;

    @Property("firstname")
    private String firstName;

    @Property("lastname")
    private String lastName;

    @Property("password")
    private String password;

    @Property("enabled")
    private boolean enabled;

    @Property("locked")
    private boolean locked;

    @Relationship(type = "HAS_ROLE")
    private List<Role> roles;


    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public User setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public boolean isLocked() {
        return locked;
    }

    public User setLocked(boolean locked) {
        this.locked = locked;
        return this;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public User setRoles(List<Role> roles) {
        this.roles = roles;
        return this;
    }
}
