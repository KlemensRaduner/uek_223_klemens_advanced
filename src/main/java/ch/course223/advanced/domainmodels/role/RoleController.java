package ch.course223.advanced.domainmodels.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/roles")
public class RoleController {


    private RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_SEE')")
    public ResponseEntity<Role> findById(@PathVariable long id) {
        Role role = roleService.findById(id);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @GetMapping({"", "/"})
    @PreAuthorize("hasAuthority('ROLE_SEE')")
    public @ResponseBody ResponseEntity<List<Role>> findAll() {
        List<Role> roles = roleService.findAll();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('ROLE_CREATE')")
    public ResponseEntity<Role> create(@Valid @RequestBody Role role) {
        return new ResponseEntity<>(roleService.save(role), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_MODIFY')")
    public ResponseEntity<Role> updateById(@PathVariable long id, @Valid @RequestBody Role role) {
        return new ResponseEntity<>(roleService.updateById(id, role), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_DELETE')")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        roleService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
